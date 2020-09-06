package org.bubbasmith.vocabtivator.domain.sentences_search;

import org.bubbasmith.vocabtivator.model.VocabEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "vocab-service-feign-client", url = "${endpoint.vocab}")
public interface VocabServiceClient {

    @GetMapping
    VocabEntity getRandomWordForTable(@PathVariable String tableKey, @RequestParam String topics) ;
}
