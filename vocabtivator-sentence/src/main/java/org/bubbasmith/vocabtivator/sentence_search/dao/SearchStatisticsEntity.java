package org.bubbasmith.vocabtivator.sentence_search.dao;


import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("search_statistics")
@Data
@Accessors(chain = true)
public class SearchStatisticsEntity {

    @Id
    private ObjectId id;
    private String vocabWord;
    private List<Date> searchDates;
}
