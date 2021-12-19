package org.bubbasmith.vocabtivator.integration.external;

import org.bubbasmith.vocabtivator.integration.dao.SentenceRepository;
import org.bubbasmith.vocabtivator.model.Sentence;
import org.bubbasmith.vocabtivator.integration.parser.ContextReversoHtmlParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Service
public class ContextReversoSentencesSearch implements ExternalSentencesSearch {

    private final static URI SEARCH_URL = URI.create("https://context.reverso.net/translation/english-russian/");

    private final ContextReversoHtmlParser contextReversoHtmlParser;
    private final WebClient webClient;

    public ContextReversoSentencesSearch(ContextReversoHtmlParser contextReversoHtmlParser,
                                         @Qualifier("contextReversoWebClient") WebClient webClient) {
        this.contextReversoHtmlParser = contextReversoHtmlParser;
        this.webClient = webClient;
    }

    public Mono<List<Sentence>> findSentencesForWord(String word) {
        return webClient.get().uri(SEARCH_URL + word.replaceAll(" ", "+"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .exchange()
                .flatMap(it -> it.bodyToMono(String.class))
                .map(contextReversoHtmlParser::parse);
    }
}
