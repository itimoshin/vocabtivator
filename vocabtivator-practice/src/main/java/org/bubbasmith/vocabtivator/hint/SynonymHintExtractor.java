package org.bubbasmith.vocabtivator.hint;


import org.bubbasmith.vocabtivator.hint.parser.ContextReversoSynonymHtmlParser;
import org.bubbasmith.vocabtivator.model.HintDTO;
import org.bubbasmith.vocabtivator.model.VocabDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SynonymHintExtractor implements HintExtractor {

    private final static String SEARCH_URL = "https://synonyms.reverso.net/synonym/en/";

    private final RestTemplate htmlRestTemplate;
    private final ContextReversoSynonymHtmlParser synonymHtmlParser;

    public SynonymHintExtractor(RestTemplate htmlRestTemplate, ContextReversoSynonymHtmlParser synonymHtmlParser) {
        this.htmlRestTemplate = htmlRestTemplate;
        this.synonymHtmlParser = synonymHtmlParser;
    }

    @Override
    public HintDTO getHint(VocabDTO vocabDTO) {
        String html = htmlRestTemplate.getForEntity(SEARCH_URL + vocabDTO.getValue().replaceAll(" ", "+"), String.class).getBody();
        return new HintDTO(synonymHtmlParser.parse(html), HintDTO.Type.SYNONYM);
    }
}
