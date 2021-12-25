package org.bubbasmith.vocabtivator.integration.external

import org.bubbasmith.vocabtivator.integration.parser.ContextReversoHtmlParser
import org.bubbasmith.vocabtivator.model.Sentence
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import java.net.URI

@Service
class ContextReversoSentencesSearch(
        private val contextReversoHtmlParser: ContextReversoHtmlParser,
        @Qualifier("contextReversoWebClient")  private val webClient: WebClient): ExternalSentencesSearch {

    private val searchUrl: URI = URI.create("https://context.reverso.net/translation/english-russian/")
    
    override suspend fun findSentencesForWord(word: String): List<Sentence> {
        val body = webClient.get().uri(searchUrl.toString() + word.replace(" ".toRegex(), "+"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .awaitExchange()
                .awaitBody<String>()
        return contextReversoHtmlParser.parse(body)

    }
}