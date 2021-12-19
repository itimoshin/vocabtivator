package org.bubbasmith.vocabtivator.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Configuration
@EnableCaching(proxyTargetClass = true)
public class Beans {

    @Bean("htmlRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36")
                .build();
    }

    @Bean("contextReversoWebClient")
    public WebClient contextReversoWebClient() {
        return WebClient.builder().baseUrl("https://context.reverso.net/translation/english-russian/")
                .defaultHeader(HttpHeaders.USER_AGENT, "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36").build();
    }
}
