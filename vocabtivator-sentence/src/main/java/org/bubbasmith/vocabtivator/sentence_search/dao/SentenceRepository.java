package org.bubbasmith.vocabtivator.sentence_search.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public interface SentenceRepository extends ReactiveMongoRepository<SentenceEntity, ObjectId> {

    @Aggregation({"{$match: {$expr: {$eq: ['$vocabWord', '?0']}}}", "{$sample: {size: 1}}"})
    Mono<SentenceEntity> findRandomForWord(String word);
}
