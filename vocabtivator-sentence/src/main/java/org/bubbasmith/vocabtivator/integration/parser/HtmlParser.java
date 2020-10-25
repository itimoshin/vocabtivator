package org.bubbasmith.vocabtivator.integration.parser;

import org.bubbasmith.vocabtivator.model.Sentence;

import java.util.List;

public interface HtmlParser {

    String PLACEHOLDER_TEMPLATE = "{%s}";

    List<Sentence> parse(String html);
}
