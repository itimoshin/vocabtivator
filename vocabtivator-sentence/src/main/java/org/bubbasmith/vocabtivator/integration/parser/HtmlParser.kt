package org.bubbasmith.vocabtivator.integration.parser

import org.bubbasmith.vocabtivator.model.Sentence

interface HtmlParser {
    fun parse(html: String): List<Sentence>

    companion object {
        const val PLACEHOLDER_TEMPLATE = "{%s}"
    }
}