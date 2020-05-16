package org.bubbasmith.vocabtivator.sentences_search;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContextReversoSentencesSearchTest {

    @Autowired
    private ContextReversoSentencesSearch contextReversoSentencesSearch;

    @Test
    void searchWord() {
        contextReversoSentencesSearch.findSentencesForVocab("know by sight");
    }
}
