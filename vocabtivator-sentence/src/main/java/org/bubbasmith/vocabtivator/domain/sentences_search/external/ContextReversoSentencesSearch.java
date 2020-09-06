package org.bubbasmith.vocabtivator.domain.sentences_search.external;

import org.bubbasmith.vocabtivator.domain.sentences_search.dao.SentenceRepository;
import org.bubbasmith.vocabtivator.domain.sentences_search.entity.SentenceEntity;
import org.bubbasmith.vocabtivator.model.Sentence;
import org.bubbasmith.vocabtivator.domain.sentences_search.parser.ContextReversoHtmlParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ContextReversoSentencesSearch implements SentencesSearch {

    private final static String SEARCH_URL = "https://context.reverso.net/translation/english-russian/";

    private final ContextReversoHtmlParser contextReversoHtmlParser;
    private final RestTemplate restTemplate;
    private final SentenceRepository sentenceRepository;

    public ContextReversoSentencesSearch(ContextReversoHtmlParser contextReversoHtmlParser,
                                         @Qualifier("htmlRestTemplate") RestTemplate restTemplate,
                                         SentenceRepository sentenceRepository) {
        this.contextReversoHtmlParser = contextReversoHtmlParser;
        this.restTemplate = restTemplate;
        this.sentenceRepository = sentenceRepository;
    }

    public Sentence findSentenceForWord(String word) {
        return sentenceRepository.findRandomForWord(word).map(s -> new Sentence()
                .setText(s.getText())
                .setPlaceholders(s.getPlaceholders())
        ).orElseGet(() -> contextReversoWord(word));
    }

    private Sentence contextReversoWord(String word) {
        String html = restTemplate.getForEntity(SEARCH_URL + word.replaceAll(" ", "+"), String.class).getBody();
        List<Sentence> sentences = contextReversoHtmlParser.parse(html);
        List<SentenceEntity> sentenceEntities = sentences.stream()
                .map(s -> new SentenceEntity()
                        .setText(s.getText())
                        .setPlaceholders(s.getPlaceholders())
                        .setVocabWord(word))
                .collect(Collectors.toList());
        sentenceRepository.saveAll(sentenceEntities);
        return sentences.get(new Random().nextInt(sentences.size()));
    }
}
