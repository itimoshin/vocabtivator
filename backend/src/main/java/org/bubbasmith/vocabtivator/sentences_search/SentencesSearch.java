package org.bubbasmith.vocabtivator.sentences_search;

import org.bubbasmith.vocabtivator.model.content.Sentence;

import java.util.List;

public interface SentencesSearch {
    List<Sentence> findSentencesForVocab(String word);
}
