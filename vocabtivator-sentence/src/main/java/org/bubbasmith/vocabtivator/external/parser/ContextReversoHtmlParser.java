package org.bubbasmith.vocabtivator.external.parser;

import org.bubbasmith.vocabtivator.model.Sentence;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContextReversoHtmlParser implements HtmlParser {

    @Override
    public List<Sentence> parse(String html) {
        return Jsoup.parse(html).select(".example>.src>.text")
                .stream().map(this::extractSentence).collect(Collectors.toList());
    }

    private Sentence extractSentence(Element element) {
        StringBuilder text = new StringBuilder();
        List<String> placeholders = new ArrayList<>();

        for (Node childNode : element.childNodes()) {
            if (childNode instanceof TextNode) {
                text.append(((TextNode) childNode).text());
            } else if (childNode instanceof Element) {
                if ("em".equals(((Element) childNode).tagName())) {
                    text.append(String.format(PLACEHOLDER_TEMPLATE, placeholders.size()));
                    placeholders.add(((Element) childNode).text());
                }
            }
        }

        return new Sentence()
                .setText(text.toString())
                .setPlaceholders(placeholders);
    }
}
