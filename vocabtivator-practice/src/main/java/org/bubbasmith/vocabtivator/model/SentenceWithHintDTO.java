package org.bubbasmith.vocabtivator.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SentenceWithHintDTO {
    private Sentence sentence;
    private List<HintDTO> hints;
}
