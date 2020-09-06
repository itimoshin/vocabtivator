package org.bubbasmith.vocabtivator.web;

import org.bubbasmith.vocabtivator.domain.sentences_search.SentenceServiceClient;
import org.bubbasmith.vocabtivator.domain.sentences_search.VocabServiceClient;
import org.bubbasmith.vocabtivator.hint.HintExtractorService;
import org.bubbasmith.vocabtivator.model.HintDTO;
import org.bubbasmith.vocabtivator.model.Sentence;
import org.bubbasmith.vocabtivator.model.SentenceWithHintDTO;
import org.bubbasmith.vocabtivator.model.VocabEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentencePracticeService {

    private final VocabServiceClient vocabServiceClient;
    private final SentenceServiceClient sentenceServiceClient;
    private final HintExtractorService hintExtractorService;

    public SentencePracticeService(VocabServiceClient vocabServiceClient,
                                   SentenceServiceClient sentenceServiceClient,
                                   HintExtractorService hintExtractorService) {
        this.vocabServiceClient = vocabServiceClient;
        this.sentenceServiceClient = sentenceServiceClient;
        this.hintExtractorService = hintExtractorService;
    }

    public SentenceWithHintDTO findSentenceByRandomWord(String tableKey, String topics) {
        VocabEntity randomWord = vocabServiceClient.getRandomWordForTable(tableKey, topics);
        Sentence sentence = sentenceServiceClient.getSentenceForWord(randomWord.getValue());
        List<HintDTO> hints = hintExtractorService.extractHints(randomWord);
        return new SentenceWithHintDTO().setHints(hints).setSentence(sentence);
    }
}
