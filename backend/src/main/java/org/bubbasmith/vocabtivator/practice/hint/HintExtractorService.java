package org.bubbasmith.vocabtivator.practice.hint;

import org.bubbasmith.vocabtivator.model.dto.HintDTO;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.bubbasmith.vocabtivator.model.vocab.VocabTopic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HintExtractorService {

    private final List<HintExtractor> hintExtractors;

    public HintExtractorService(List<HintExtractor> hintExtractors) {
        this.hintExtractors = hintExtractors;
    }

    public List<HintDTO> extractHints(VocabEntity vocabEntity, VocabTopic vocabTopic) {
        return hintExtractors.stream().map(e -> e.getHint(vocabEntity, vocabTopic))
                .filter(h -> !h.getText().isEmpty()).collect(Collectors.toList());
    }
}
