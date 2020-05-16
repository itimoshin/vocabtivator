package org.bubbasmith.vocabtivator.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.bubbasmith.vocabtivator.model.content.Sentence;

import java.util.List;

@Data
@Accessors(chain = true)
public class SentenceWithHintDTO {
    private Sentence sentence;
    private List<HintDTO> hints;
}
