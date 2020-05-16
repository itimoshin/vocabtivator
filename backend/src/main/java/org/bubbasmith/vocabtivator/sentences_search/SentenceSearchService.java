package org.bubbasmith.vocabtivator.sentences_search;

import org.bubbasmith.vocabtivator.model.content.Sentence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentenceSearchService {

    private final ContextReversoSentencesSearch contextReversoSentencesSearch;

    public SentenceSearchService(ContextReversoSentencesSearch contextReversoSentencesSearch) {
        this.contextReversoSentencesSearch = contextReversoSentencesSearch;
    }

    public List<Sentence> findSentencesForWord(String word) {
        return contextReversoSentencesSearch.findSentencesForVocab(word);
    }
}
