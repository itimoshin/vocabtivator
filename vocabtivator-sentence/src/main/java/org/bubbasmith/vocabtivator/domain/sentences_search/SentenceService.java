package org.bubbasmith.vocabtivator.domain.sentences_search;

import org.bubbasmith.vocabtivator.model.Sentence;

public interface SentenceService {

    Sentence getSentenceForWord(String word);
}
