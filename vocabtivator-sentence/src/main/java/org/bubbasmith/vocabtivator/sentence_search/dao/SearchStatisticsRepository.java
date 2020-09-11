package org.bubbasmith.vocabtivator.sentence_search.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SearchStatisticsRepository extends ReactiveMongoRepository<SearchStatisticsEntity, ObjectId> {

    Mono<SearchStatisticsEntity> findByVocabWord(String vocabWord);
}
