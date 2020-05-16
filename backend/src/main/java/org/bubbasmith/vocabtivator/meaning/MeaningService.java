package org.bubbasmith.vocabtivator.meaning;

import java.util.List;

public interface MeaningService {
    List<String> findSynonyms(String word);
}
