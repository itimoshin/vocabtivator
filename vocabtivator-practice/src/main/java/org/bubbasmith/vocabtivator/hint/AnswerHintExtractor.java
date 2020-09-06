package org.bubbasmith.vocabtivator.hint;

import org.bubbasmith.vocabtivator.model.dto.HintDTO;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.springframework.stereotype.Component;

@Component
public class AnswerHintExtractor implements HintExtractor {

    @Override
    public HintDTO getHint(VocabEntity vocabEntity) {
        return new HintDTO(vocabEntity.getValue(), HintDTO.Type.ANSWER);
    }
}
