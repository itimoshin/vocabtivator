package org.bubbasmith.vocabtivator.external.dao;

import org.bson.types.ObjectId;
import org.bubbasmith.vocabtivator.external.entity.SentenceEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SentenceRepository extends MongoRepository<SentenceEntity, ObjectId> {

    @Aggregation({"{$match: {$expr: {$eq: ['$vocabWord', '?0']}}}", "{$sample: {size: 1}}"})
    Optional<SentenceEntity> findRandomForWord(String word);
}
