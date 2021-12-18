package org.bubbasmith.vocabtivator.integration;

import org.bubbasmith.vocabtivator.model.Sentence;
import reactor.core.publisher.Mono;

public interface SentenceService {

    Mono<Sentence> getSentenceForWord(String word);
}
