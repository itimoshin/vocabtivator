package org.bubbasmith.vocabtivator.domain.meaning;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColinsDictionaryMeaningService implements MeaningService {

    private final RestTemplate restTemplate;

    public ColinsDictionaryMeaningService(@Qualifier("htmlRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> findSynonyms(String word) {
        String html = restTemplate.getForEntity("https://www.collinsdictionary.com/dictionary/english/" +
                word.replaceAll(" ", "-"), String.class).getBody();
        return Jsoup.parse(html).select(".def")
                .stream().map(it -> it.text()).collect(Collectors.toList());
    }
}
