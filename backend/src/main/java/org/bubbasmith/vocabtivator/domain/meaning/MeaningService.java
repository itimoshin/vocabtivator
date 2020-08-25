package org.bubbasmith.vocabtivator.domain.meaning;

import java.util.List;

public interface MeaningService {
    List<String> findSynonyms(String word);
}
