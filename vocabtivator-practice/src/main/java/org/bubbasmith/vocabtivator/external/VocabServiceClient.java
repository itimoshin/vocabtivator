package org.bubbasmith.vocabtivator.external;

import org.bubbasmith.vocabtivator.model.VocabEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "vocab-service-feign-client", url = "${endpoint.vocab}")
public interface VocabServiceClient {

    @GetMapping("/vocab-table/{tableKey}/random-word")
    VocabEntity getRandomWordForTable(@PathVariable String tableKey, @RequestParam String topics) ;
}
