package org.bubbasmith.vocabtivator.hint;


import org.bubbasmith.vocabtivator.model.HintDTO;
import org.bubbasmith.vocabtivator.model.VocabDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HintExtractorService {

    private final List<HintExtractor> hintExtractors;

    public HintExtractorService(List<HintExtractor> hintExtractors) {
        this.hintExtractors = hintExtractors;
    }

    public List<HintDTO> extractHints(VocabDTO vocabDTO) {
        return hintExtractors.stream().map(e -> e.getHint(vocabDTO))
                .filter(h -> !h.getData().isEmpty()).collect(Collectors.toList());
    }
}
