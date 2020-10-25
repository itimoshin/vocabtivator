package org.bubbasmith.vocabtivator.integration;

import org.bubbasmith.vocabtivator.model.Sentence;

public interface SentenceService {

    Sentence getSentenceForWord(String word);
}
