package org.bubbasmith.vocabtivator.domain.sentences_search;

import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class VocabService {

    private final RestTemplate vocabeServiceRestTemplate;

    public VocabService(@Qualifier("vocabServiceRestTemplate") RestTemplate vocabServiceRestTemplate) {
        this.vocabeServiceRestTemplate = vocabServiceRestTemplate;
    }

    public VocabEntity getRandomVocabWord(String tableKey, String topics) {
        return vocabeServiceRestTemplate.getForObject("/vocab-table/{tableKey}/random-word?topics={topics}", VocabEntity.class,
                Map.of("tableKey", tableKey, "topics", topics));
    }
}
