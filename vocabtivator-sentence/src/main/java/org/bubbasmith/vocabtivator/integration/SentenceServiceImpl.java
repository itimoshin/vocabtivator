package org.bubbasmith.vocabtivator.integration;

import org.bubbasmith.vocabtivator.model.Sentence;
import org.bubbasmith.vocabtivator.integration.dao.SearchStatisticsEntity;
import org.bubbasmith.vocabtivator.integration.dao.SearchStatisticsRepository;
import org.bubbasmith.vocabtivator.integration.dao.SentenceEntity;
import org.bubbasmith.vocabtivator.integration.dao.SentenceRepository;
import org.bubbasmith.vocabtivator.integration.external.ExternalSentencesSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SentenceServiceImpl implements SentenceService {

    private final ExternalSentencesSearch externalSentencesSearch;
    private final SentenceRepository sentenceRepository;
    private final SearchStatisticsRepository searchStatisticsRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(SentenceServiceImpl.class);

    public SentenceServiceImpl(ExternalSentencesSearch externalSentencesSearch,
                               SentenceRepository sentenceRepository,
                               SearchStatisticsRepository searchStatisticsRepository) {
        this.externalSentencesSearch = externalSentencesSearch;
        this.sentenceRepository = sentenceRepository;
        this.searchStatisticsRepository = searchStatisticsRepository;
    }

    @Override
    public Mono<Sentence> getSentenceForWord(String word) {
        return sentenceRepository.findRandomForWord(word).flatMap(randomSentence -> {
            if (randomSentence != null) {
                return incWordUsageCount(word).thenReturn(new Sentence()
                        .setText(randomSentence.getText())
                        .setPlaceholders(randomSentence.getPlaceholders()));
            } else {
                LOGGER.info("Sentence for the word '{}' not found, searching in external resources...", word);
                return Mono.just(externalSentencesSearch.findSentencesForWord(word)).flatMap(sentences -> {
                    List<SentenceEntity> sentenceEntities = sentences.stream()
                            .map(s -> new SentenceEntity()
                                    .setText(s.getText())
                                    .setPlaceholders(s.getPlaceholders())
                                    .setVocabWord(word))
                            .collect(Collectors.toList());

                    return sentenceRepository.saveAll(sentenceEntities).then(incWordUsageCount(word)).thenReturn(sentences.get(new Random().nextInt(sentences.size())));
                });
            }
        });
    }

    private Mono<SearchStatisticsEntity> incWordUsageCount(String word) {
        return searchStatisticsRepository.findByVocabWord(word)
                .or(Mono.just(new SearchStatisticsEntity().setVocabWord(word).setSearchDates(new ArrayList<>())))
                .map(s -> {
                    s.getSearchDates().add(new Date());
                    return s;
                })
                .flatMap(searchStatisticsRepository::save);
    }
}
