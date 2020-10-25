package org.bubbasmith.vocabtivator.hint.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContextReversoSynonymHtmlParser implements SynonymHtmlParser {

    @Override
    public List<String> parse(String html) {
        Elements synonymsBlocks = Jsoup.parse(html).select(".word-opt");
        // return the first block (if exists), which matches synonyms for the same part of speech (noun, verb, etc.)
        if(!synonymsBlocks.isEmpty()) {
            return synonymsBlocks.get(0).select(".word-box>li>.synonym.relevant")
                    .stream().map(Element::text).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
