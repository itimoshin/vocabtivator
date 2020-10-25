package org.bubbasmith.vocabtivator.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class VocabDTO {
    private String value;
    private String meaning;
    private List<String> examples;
    private String useStrategy;
    private String topic;
}
