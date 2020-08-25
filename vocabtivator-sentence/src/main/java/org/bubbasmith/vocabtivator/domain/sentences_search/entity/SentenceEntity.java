package org.bubbasmith.vocabtivator.domain.sentences_search.entity;


import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
