package org.bubbasmith.vocabtivator.external.external;

import org.bubbasmith.vocabtivator.model.Sentence;

import java.util.List;

public interface SentencesSearch {
    Sentence findSentenceForWord(String word);
}
