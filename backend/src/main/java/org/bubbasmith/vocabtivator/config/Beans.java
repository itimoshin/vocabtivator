package org.bubbasmith.vocabtivator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching(proxyTargetClass = true)
public class Beans {


    @Bean("sentenceServiceRestTemplate")
    public RestTemplate sentenceServiceRestTemplate(@Value("${vocabtivator.sentence-service.url}") String rootUrl) {
        return new RestTemplateBuilder().rootUri(rootUrl).build();
    }

    @Bean("vocabServiceRestTemplate")
    public RestTemplate vocabServiceRestTemplate(@Value("${vocabtivator.vocab-service.url}") String rootUrl) {
        return new RestTemplateBuilder().rootUri(rootUrl).build();
    }
}
