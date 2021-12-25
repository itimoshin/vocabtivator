package org.bubbasmith.vocabtivator.integration.dao

import lombok.Data
import lombok.experimental.Accessors
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("search_statistics")
@Data
@Accessors(chain = true)
class SearchStatisticsEntity(
        @Id val id: ObjectId? = null,
        val vocabWord: String,
        val searchDates: List<Date>) {
}