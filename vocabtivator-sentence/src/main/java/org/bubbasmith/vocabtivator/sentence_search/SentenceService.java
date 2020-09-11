package org.bubbasmith.vocabtivator.sentence_search;

import org.bubbasmith.vocabtivator.model.Sentence;

public interface SentenceService {

    Sentence getSentenceForWord(String word);
}
