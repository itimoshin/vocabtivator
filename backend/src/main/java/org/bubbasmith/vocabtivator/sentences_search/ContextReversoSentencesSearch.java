package org.bubbasmith.vocabtivator.sentences_search;

import org.bubbasmith.vocabtivator.model.content.Sentence;
import org.bubbasmith.vocabtivator.sentences_search.parser.ContextReversoHtmlParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ContextReversoSentencesSearch implements SentencesSearch {

    private final static String SEARCH_URL = "https://context.reverso.net/translation/english-russian/";

    private final ContextReversoHtmlParser contextReversoHtmlParser;
    private final RestTemplate restTemplate;

    public ContextReversoSentencesSearch(ContextReversoHtmlParser contextReversoHtmlParser,
                                         @Qualifier("htmlRestTemplate") RestTemplate restTemplate) {
        this.contextReversoHtmlParser = contextReversoHtmlParser;
        this.restTemplate = restTemplate;
    }

    @Cacheable("context_reverso_sentences")
    public List<Sentence> findSentencesForVocab(String word) {
        String html = restTemplate.getForEntity(SEARCH_URL + word.replaceAll(" ", "+"), String.class).getBody();
        return contextReversoHtmlParser.parse(html);
    }
}
