package org.bubbasmith.vocabtivator.practice.hint;

import org.bubbasmith.vocabtivator.model.dto.HintDTO;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.bubbasmith.vocabtivator.model.vocab.VocabTopic;
import org.springframework.stereotype.Component;

@Component
public class TopicHintExtractor implements HintExtractor {

    @Override
    public HintDTO getHint(VocabEntity vocabEntity, VocabTopic vocabTopic) {
        return new HintDTO(vocabTopic.getName(), HintDTO.Type.TOPIC);
    }
}
