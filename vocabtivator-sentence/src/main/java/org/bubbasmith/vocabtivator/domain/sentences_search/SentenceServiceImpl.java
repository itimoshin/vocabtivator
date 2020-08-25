package org.bubbasmith.vocabtivator.domain.sentences_search;

import org.bubbasmith.vocabtivator.domain.sentences_search.external.SentencesSearch;
import org.bubbasmith.vocabtivator.model.Sentence;
import org.springframework.stereotype.Service;

@Service
public class SentenceServiceImpl implements SentenceService {

    private final SentencesSearch sentencesSearch;

    public SentenceServiceImpl(SentencesSearch sentencesSearch) {
        this.sentencesSearch = sentencesSearch;
    }

    @Override
    public Sentence getSentenceForWord(String word) {
        return sentencesSearch.findSentenceForWord(word);
    }
}
