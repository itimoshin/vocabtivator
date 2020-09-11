package org.bubbasmith.vocabtivator.sentence_search.external;

import org.bubbasmith.vocabtivator.model.Sentence;

import java.util.List;

public interface ExternalSentencesSearch {
    List<Sentence> findSentencesForWord(String word);
}
