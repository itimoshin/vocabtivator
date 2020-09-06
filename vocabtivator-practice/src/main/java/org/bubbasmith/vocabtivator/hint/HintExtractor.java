package org.bubbasmith.vocabtivator.hint;


import org.bubbasmith.vocabtivator.model.HintDTO;
import org.bubbasmith.vocabtivator.model.VocabEntity;

public interface HintExtractor {

    HintDTO getHint(VocabEntity vocabEntity);
}
