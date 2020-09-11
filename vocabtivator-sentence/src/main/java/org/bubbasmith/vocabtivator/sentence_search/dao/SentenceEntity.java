package org.bubbasmith.vocabtivator.sentence_search.dao;


import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("sentence")
@Data
@Accessors(chain = true)
public class SentenceEntity {

    @Id
    private ObjectId id;
    private String text;
    private String vocabWord;
    private List<String> placeholders;
}
