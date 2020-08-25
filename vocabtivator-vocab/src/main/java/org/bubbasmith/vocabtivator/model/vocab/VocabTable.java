package org.bubbasmith.vocabtivator.model.vocab;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class VocabTable {
    public Map<String, VocabTopic> topics;
}
