package org.bubbasmith.vocabtivator.practice.hint;

import org.bubbasmith.vocabtivator.model.dto.HintDTO;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.bubbasmith.vocabtivator.model.vocab.VocabTopic;

import java.util.List;

public interface HintExtractor {

    HintDTO getHint(VocabEntity vocabEntity, VocabTopic vocabTopic);
}
