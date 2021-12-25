package org.bubbasmith.vocabtivator.integration.parser

import org.bubbasmith.vocabtivator.model.Sentence
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ContextReversoHtmlParser : HtmlParser {
    override fun parse(html: String): List<Sentence> {
        return Jsoup.parse(html).select(".example>.src>.text")
                .stream().map { element: Element -> extractSentence(element) }.collect(Collectors.toList())
    }

    private fun extractSentence(element: Element): Sentence {
        val text = StringBuilder()
        val placeholders: MutableList<String> = ArrayList()
        for (childNode in element.childNodes()) {
            if (childNode is TextNode) {
                text.append(childNode.text())
            } else if (childNode is Element) {
                if ("em" == childNode.tagName()) {
                    text.append(String.format(HtmlParser.PLACEHOLDER_TEMPLATE, placeholders.size))
                    placeholders.add(childNode.text())
                }
            }
        }
        return Sentence(text.toString(), placeholders)
    }
}