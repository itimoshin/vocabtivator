package org.bubbasmith.vocabtivator.hint.parser;

import org.bubbasmith.vocabtivator.model.Sentence;

import java.util.List;

public interface SynonymHtmlParser {

    String PLACEHOLDER_TEMPLATE = "{%s}";

    List<String> parse(String html);
}
