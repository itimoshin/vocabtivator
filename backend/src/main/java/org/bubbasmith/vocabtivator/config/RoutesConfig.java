package org.bubbasmith.vocabtivator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching(proxyTargetClass = true)
public class RoutesConfig {

    @Bean
    public RouteLocator routeLocator(@Value("${endpoint.vocab}") String vocabEndpoint,
                                     @Value("${endpoint.sentence}") String sentenceEndpoint,
                                     RouteLocatorBuilder builder) {
        return builder.routes()
                .route("vocab_route", r -> r
                        .path("/vocab-service/**")
                        .filters(f -> f.rewritePath("^/vocab-service", ""))
                        .uri(vocabEndpoint))
                .route("sentence_service", r -> r
                        .path("/sentence-service/**")
                        .filters(f -> f.rewritePath("^/sentence-service", ""))
                        .uri(vocabEndpoint))
                .build();
    }
}
