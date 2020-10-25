package org.bubbasmith.vocabtivator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HintDTO {

    private List<String> data;
    private Type type;

    public enum Type {
        MEANING, TOPIC, ANSWER, SYNONYM
    }
}
