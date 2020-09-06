package org.bubbasmith.vocabtivator.external;

import org.bubbasmith.vocabtivator.model.Sentence;

public interface SentenceService {

    Sentence getSentenceForWord(String word);
}
