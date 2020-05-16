package org.bubbasmith.vocabtivator.sentences_search.parser;

import org.bubbasmith.vocabtivator.model.content.Sentence;

import java.util.List;

public interface HtmlParser {

    String PLACEHOLDER_TEMPLATE = "{%s}";

    List<Sentence> parse(String html);
}
