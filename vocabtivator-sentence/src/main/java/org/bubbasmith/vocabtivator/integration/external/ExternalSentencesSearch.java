package org.bubbasmith.vocabtivator.integration.external;

import org.bubbasmith.vocabtivator.model.Sentence;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExternalSentencesSearch {
    Mono<List<Sentence>> findSentencesForWord(String word);
}
