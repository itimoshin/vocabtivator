package org.bubbasmith.vocabtivator.hint;


import org.bubbasmith.vocabtivator.model.HintDTO;
import org.bubbasmith.vocabtivator.model.VocabDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class MeaningHintExtractor implements HintExtractor {

    @Override
    public HintDTO getHint(VocabDTO vocabDTO) {
        return new HintDTO(Collections.singletonList(vocabDTO.getMeaning()), HintDTO.Type.MEANING);
    }
}
