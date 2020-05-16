package org.bubbasmith.vocabtivator.synonym;

import org.bubbasmith.vocabtivator.meaning.ColinsDictionaryMeaningService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ColinsDictionaryMeaningServiceTest {

    @Autowired
    private ColinsDictionaryMeaningService thesaurusSynonymService;

    @Test
    public void test() {
        thesaurusSynonymService.findSynonyms("bite the bullet");
    }
}
