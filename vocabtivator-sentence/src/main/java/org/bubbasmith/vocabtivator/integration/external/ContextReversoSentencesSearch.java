package org.bubbasmith.vocabtivator.integration.external;

import org.bubbasmith.vocabtivator.integration.dao.SentenceRepository;
import org.bubbasmith.vocabtivator.model.Sentence;
import org.bubbasmith.vocabtivator.integration.parser.ContextReversoHtmlParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ContextReversoSentencesSearch implements ExternalSentencesSearch {

    private final static String SEARCH_URL = "https://context.reverso.net/translation/english-russian/";

    private final ContextReversoHtmlParser contextReversoHtmlParser;
    private final RestTemplate restTemplate;

    public ContextReversoSentencesSearch(ContextReversoHtmlParser contextReversoHtmlParser,
                                         @Qualifier("htmlRestTemplate") RestTemplate restTemplate,
                                         SentenceRepository sentenceRepository) {
        this.contextReversoHtmlParser = contextReversoHtmlParser;
        this.restTemplate = restTemplate;
    }

    public List<Sentence> findSentencesForWord(String word) {
        String html = restTemplate.getForEntity(SEARCH_URL + word.replaceAll(" ", "+"), String.class).getBody();
        return contextReversoHtmlParser.parse(html);
    }
}
