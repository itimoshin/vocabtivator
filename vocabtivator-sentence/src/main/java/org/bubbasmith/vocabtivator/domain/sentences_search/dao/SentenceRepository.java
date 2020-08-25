package org.bubbasmith.vocabtivator.domain.sentences_search.dao;

import org.bson.types.ObjectId;
import org.bubbasmith.vocabtivator.domain.sentences_search.entity.SentenceEntity;
import org.bubbasmith.vocabtivator.model.Sentence;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SentenceRepository extends MongoRepository<SentenceEntity, ObjectId> {

    @Aggregation({"{$match: {$expr: {$eq: ['$vocabWord', '?0']}}}", "{$sample: {size: 1}}"})
    Optional<SentenceEntity> findRandomForWord(String word);
}
