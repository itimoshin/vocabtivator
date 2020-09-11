package org.bubbasmith.vocabtivator.sentence_search;

import org.bubbasmith.vocabtivator.model.Sentence;
import org.bubbasmith.vocabtivator.sentence_search.dao.SearchStatisticsEntity;
import org.bubbasmith.vocabtivator.sentence_search.dao.SearchStatisticsRepository;
import org.bubbasmith.vocabtivator.sentence_search.dao.SentenceEntity;
import org.bubbasmith.vocabtivator.sentence_search.dao.SentenceRepository;
import org.bubbasmith.vocabtivator.sentence_search.external.ExternalSentencesSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
    public Sentence getSentenceForWord(String word) {
        SentenceEntity dbSentence = sentenceRepository.findRandomForWord(word).block();
        if (dbSentence != null) {
            incWord(word);
            return new Sentence()
                    .setText(dbSentence.getText())
                    .setPlaceholders(dbSentence.getPlaceholders());
        } else {
            LOGGER.info("Sentence for the word '{}' not found, searching in external resources...", word);
            List<Sentence> sentences = externalSentencesSearch.findSentencesForWord(word);
            List<SentenceEntity> sentenceEntities = sentences.stream()
                    .map(s -> new SentenceEntity()
                            .setText(s.getText())
                            .setPlaceholders(s.getPlaceholders())
                            .setVocabWord(word))
                    .collect(Collectors.toList());

            sentenceRepository.saveAll(sentenceEntities).collectList()
                    .doOnNext(d -> incWord(word))
                    .subscribe();
            return sentences.get(new Random().nextInt(sentences.size()));
        }
    }

    private void incWord(String word) {
        SearchStatisticsEntity searchStatisticsEntity = searchStatisticsRepository.findByVocabWord(word).blockOptional()
                .orElse(new SearchStatisticsEntity().setVocabWord(word).setSearchDates(new ArrayList<>()));
       searchStatisticsEntity.getSearchDates().add(new Date());
       searchStatisticsRepository.save(searchStatisticsEntity).block();
    }
}
