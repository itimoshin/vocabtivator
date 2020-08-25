package org.bubbasmith.vocabtivator.domain.practice;

import org.bubbasmith.vocabtivator.domain.practice.hint.HintExtractorService;
import org.bubbasmith.vocabtivator.domain.sentences_search.SentenceSearchService;
import org.bubbasmith.vocabtivator.domain.sentences_search.VocabService;
import org.bubbasmith.vocabtivator.model.Sentence;
import org.bubbasmith.vocabtivator.model.dto.HintDTO;
import org.bubbasmith.vocabtivator.model.dto.SentenceWithHintDTO;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentencePracticeService {

    private final VocabService vocabService;
    private final SentenceSearchService sentenceSearchService;
    private final HintExtractorService hintExtractorService;

    public SentencePracticeService(VocabService vocabService,
                                   SentenceSearchService sentenceSearchService,
                                   HintExtractorService hintExtractorService) {
        this.vocabService = vocabService;
        this.sentenceSearchService = sentenceSearchService;
        this.hintExtractorService = hintExtractorService;
    }

    public SentenceWithHintDTO findSentenceByRandomWord(String tableKey, String topics) {
        VocabEntity vocabEntity = vocabService.getRandomVocabWord(tableKey, topics);
        Sentence sentence = sentenceSearchService.findSentencesForWord(vocabEntity.getValue());
        List<HintDTO> hints = hintExtractorService.extractHints(vocabEntity);
        return new SentenceWithHintDTO().setSentence(sentence).setHints(hints);
    }
}
