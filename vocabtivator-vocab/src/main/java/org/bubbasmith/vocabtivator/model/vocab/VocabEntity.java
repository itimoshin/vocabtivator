package org.bubbasmith.vocabtivator.model.vocab;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class VocabEntity {
    private String value;
    private String meaning;
    private List<String> examples;
    private String useStrategy;
}
