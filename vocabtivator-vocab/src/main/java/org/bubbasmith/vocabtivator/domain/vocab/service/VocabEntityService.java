package org.bubbasmith.vocabtivator.domain.vocab.service;

import org.bubbasmith.vocabtivator.exception.VocabtivatorNotFoundException;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.bubbasmith.vocabtivator.model.vocab.VocabTable;
import org.bubbasmith.vocabtivator.model.vocab.VocabTopic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
public class VocabEntityService {

    private final VocabStorageService vocabStorageService;

    public VocabEntityService(VocabStorageService vocabStorageService) {
        this.vocabStorageService = vocabStorageService;
    }

    public VocabEntity getRandomWordForTable(String tableKey, List<String> topicsNames) {
        VocabTable vocabTable = ofNullable(vocabStorageService.getVocabTable(tableKey))
                .orElseThrow(() -> new VocabtivatorNotFoundException(format("Vocab table with key %s not found", tableKey)));
        VocabTopic topic = getRandomTopic(vocabTable, topicsNames);
        return getRandom(topic.getEntities()).setTopic(topic.getName());
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
