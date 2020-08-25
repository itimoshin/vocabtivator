package org.bubbasmith.vocabtivator.domain.practice.hint;

import org.bubbasmith.vocabtivator.model.dto.HintDTO;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.springframework.stereotype.Component;

@Component
public class MeaningHintExtractor implements HintExtractor {

    @Override
    public HintDTO getHint(VocabEntity vocabEntity) {
        return new HintDTO(vocabEntity.getMeaning(), HintDTO.Type.MEANING);
    }
}
