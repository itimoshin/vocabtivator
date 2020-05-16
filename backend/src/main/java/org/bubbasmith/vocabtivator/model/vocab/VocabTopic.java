package org.bubbasmith.vocabtivator.model.vocab;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class VocabTopic {
    private String name;
    private List<VocabEntity> entities;
}
