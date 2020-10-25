package org.bubbasmith.vocabtivator.integration.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SentenceRepository extends ReactiveMongoRepository<SentenceEntity, ObjectId> {

    @Aggregation({"{$match: {$expr: {$eq: ['$vocabWord', '?0']}}}", "{$sample: {size: 1}}"})
    Mono<SentenceEntity> findRandomForWord(String word);
}
