package org.bubbasmith.vocabtivator.web

import org.bubbasmith.vocabtivator.integration.SentenceService
import org.bubbasmith.vocabtivator.model.Sentence
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sentence")
class SentenceController(private val sentenceService: SentenceService) {
    @GetMapping
    suspend fun getSentenceForWord(@RequestParam word: String): Sentence {
        return sentenceService.getSentenceForWord(word)
    }
}