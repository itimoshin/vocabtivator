package org.bubbasmith.vocabtivator.domain.practice.hint;

import org.bubbasmith.vocabtivator.model.dto.HintDTO;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;

public interface HintExtractor {

    HintDTO getHint(VocabEntity vocabEntity);
}
