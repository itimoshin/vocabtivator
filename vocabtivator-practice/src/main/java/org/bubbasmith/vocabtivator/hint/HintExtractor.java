package org.bubbasmith.vocabtivator.hint;


import org.bubbasmith.vocabtivator.model.HintDTO;
import org.bubbasmith.vocabtivator.model.VocabDTO;

public interface HintExtractor {

    HintDTO getHint(VocabDTO vocabDTO);
}
