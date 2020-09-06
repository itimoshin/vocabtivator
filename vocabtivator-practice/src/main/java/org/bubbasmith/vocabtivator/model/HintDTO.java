package org.bubbasmith.vocabtivator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HintDTO {

    private String text;
    private Type type;

    public enum Type {
        MEANING, FIRST_LETTER, TOPIC, ANSWER
    }
}
