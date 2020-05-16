package org.bubbasmith.vocabtivator.practice;

import org.bubbasmith.vocabtivator.exception.VocabtivatorNotFoundException;
import org.bubbasmith.vocabtivator.model.content.Sentence;
import org.bubbasmith.vocabtivator.model.dto.HintDTO;
import org.bubbasmith.vocabtivator.model.dto.SentenceWithHintDTO;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.bubbasmith.vocabtivator.model.vocab.VocabTable;
import org.bubbasmith.vocabtivator.model.vocab.VocabTopic;
import org.bubbasmith.vocabtivator.practice.hint.HintExtractorService;
import org.bubbasmith.vocabtivator.sentences_search.SentenceSearchService;
import org.bubbasmith.vocabtivator.vocab.service.VocabStorageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
public class SentencePracticeService {

    private final VocabStorageService vocabStorageService;
    private final SentenceSearchService sentenceSearchService;
    private final HintExtractorService hintExtractorService;

    public SentencePracticeService(VocabStorageService vocabStorageService,
                                   SentenceSearchService sentenceSearchService, HintExtractorService hintExtractorService) {
        this.vocabStorageService = vocabStorageService;
        this.sentenceSearchService = sentenceSearchService;
        this.hintExtractorService = hintExtractorService;
    }

    public SentenceWithHintDTO findSentenceByRandomWord(String tableKey, List<String> topicsNames) {
        VocabTable vocabTable = ofNullable(vocabStorageService.getVocabTable(tableKey))
                .orElseThrow(() -> new VocabtivatorNotFoundException(format("Vocab table with key %s not found", tableKey)));

        VocabTopic topic = getRandomTopic(vocabTable, topicsNames);
        VocabEntity vocabEntity = getRandom(topic.getEntities());

        Sentence sentence = getRandom(sentenceSearchService.findSentencesForWord(vocabEntity.getValue()));
        List<HintDTO> hints = hintExtractorService.extractHints(vocabEntity, topic);
        return new SentenceWithHintDTO().setSentence(sentence).setHints(hints);
    }

    private VocabTopic getRandomTopic(VocabTable vocabTable, List<String> topicsNames) {
        String randomTopicName = topicsNames.isEmpty() ? getRandom(new ArrayList<>(vocabTable.getTopics().keySet()))
                : getRandom(topicsNames);
        return ofNullable(vocabTable.getTopics().get(randomTopicName))
                .orElseThrow(() -> new VocabtivatorNotFoundException(format(
                        "Topic '%s' for vocab table with key %s not found", randomTopicName, vocabTable)));
    }

    private <T> T getRandom(List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}
