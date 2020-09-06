package org.bubbasmith.vocabtivator.hint;


import org.bubbasmith.vocabtivator.model.HintDTO;
import org.bubbasmith.vocabtivator.model.VocabEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HintExtractorService {

    private final List<HintExtractor> hintExtractors;

    public HintExtractorService(List<HintExtractor> hintExtractors) {
        this.hintExtractors = hintExtractors;
    }

    public List<HintDTO> extractHints(VocabEntity vocabEntity) {
        return hintExtractors.stream().map(e -> e.getHint(vocabEntity))
                .filter(h -> !h.getText().isEmpty()).collect(Collectors.toList());
    }
}
