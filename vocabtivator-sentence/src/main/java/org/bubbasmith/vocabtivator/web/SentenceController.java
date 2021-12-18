package org.bubbasmith.vocabtivator.web;

import org.bubbasmith.vocabtivator.integration.SentenceService;
import org.bubbasmith.vocabtivator.model.Sentence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentence")
public class SentenceController {

    private final SentenceService sentenceService;

    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping
    public Sentence getSentenceForWord(@RequestParam String word) {
        return sentenceService.getSentenceForWord(word).block();
    }
}
