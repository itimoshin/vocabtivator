package org.bubbasmith.vocabtivator.sentence_search;

import org.bubbasmith.vocabtivator.model.Sentence;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "sentence-service-feign-client", url = "${endpoint.sentence}")
public interface SentenceServiceClient {

    @GetMapping("/sentence")
    Sentence getSentenceForWord(@RequestParam String word);
}
