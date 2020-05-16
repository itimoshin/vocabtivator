package org.bubbasmith.vocabtivator.practice.hint;

import org.bubbasmith.vocabtivator.model.dto.HintDTO;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.bubbasmith.vocabtivator.model.vocab.VocabTopic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstLetterHintExtractor implements HintExtractor {

    @Override
    public HintDTO getHint(VocabEntity vocabEntity, VocabTopic vocabTopic) {
        return new HintDTO(vocabEntity.getValue().replaceAll("(?<=\\S)(\\S)", "_"), HintDTO.Type.FIRST_LETTER);
    }
}
