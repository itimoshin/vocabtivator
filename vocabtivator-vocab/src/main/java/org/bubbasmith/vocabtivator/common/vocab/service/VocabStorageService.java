package org.bubbasmith.vocabtivator.common.vocab.service;

import org.bubbasmith.vocabtivator.model.vocab.VocabTable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VocabStorageService {

    private final Map<String, VocabTable> vocabTableMap = new ConcurrentHashMap<>();

    public VocabTable getVocabTable(String key) {
        return vocabTableMap.get(key);
    }

    /**
     * @param vocabTable
     * @return key
     */
    public String putVocabTable(VocabTable vocabTable) {
        String key = generateKey();
        vocabTableMap.put(key, vocabTable);
        return key;
    }

    private String generateKey() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
