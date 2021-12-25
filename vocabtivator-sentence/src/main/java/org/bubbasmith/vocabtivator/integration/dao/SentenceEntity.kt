package org.bubbasmith.vocabtivator.integration.dao

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("sentence")
data class SentenceEntity(
        @Id val id: ObjectId? = null,
        val text: String,
        val vocabWord: String,
        val placeholders: List<String>) {
}