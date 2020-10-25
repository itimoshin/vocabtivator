package org.bubbasmith.vocabtivator.hint;


import org.bubbasmith.vocabtivator.model.HintDTO;
import org.bubbasmith.vocabtivator.model.VocabDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AnswerHintExtractor implements HintExtractor {

    @Override
    public HintDTO getHint(VocabDTO vocabDTO) {
        return new HintDTO(Collections.singletonList(vocabDTO.getValue()), HintDTO.Type.ANSWER);
    }
}
