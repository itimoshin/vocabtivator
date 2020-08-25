package org.bubbasmith.vocabtivator.web;

import org.bubbasmith.vocabtivator.model.dto.SentenceWithHintDTO;
import org.bubbasmith.vocabtivator.domain.practice.SentencePracticeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("practice")
public class PracticeController {

    private final SentencePracticeService sentencePracticeService;

    public PracticeController(SentencePracticeService sentencePracticeService) {
        this.sentencePracticeService = sentencePracticeService;
    }

    @GetMapping("sentence")
    public SentenceWithHintDTO get(@RequestParam String tableKey, @RequestParam String topics) {
        return sentencePracticeService.findSentenceByRandomWord(tableKey, topics);
    }
}
