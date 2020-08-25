package org.bubbasmith.vocabtivator.domain.sentences_search;

import org.bubbasmith.vocabtivator.model.Sentence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SentenceSearchService {

    private final RestTemplate sentenceServiceRestTemplate;

    public SentenceSearchService(RestTemplate sentenceServiceRestTemplate) {
        this.sentenceServiceRestTemplate = sentenceServiceRestTemplate;
    }

    public Sentence findSentencesForWord(String word) {
        return sentenceServiceRestTemplate.getForObject("/sentence?word={word}", Sentence.class, Map.of("word", word));
    }
}
