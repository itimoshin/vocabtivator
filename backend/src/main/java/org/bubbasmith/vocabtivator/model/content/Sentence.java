package org.bubbasmith.vocabtivator.model.content;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Sentence {
    private String text;
    private List<String> placeholders;
}
