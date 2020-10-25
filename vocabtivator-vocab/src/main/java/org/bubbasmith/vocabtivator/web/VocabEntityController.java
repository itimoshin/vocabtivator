package org.bubbasmith.vocabtivator.web;

import org.bubbasmith.vocabtivator.common.vocab.service.VocabEntityService;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/vocab-table")
public class VocabEntityController {

    private final VocabEntityService vocabEntityService;

    public VocabEntityController(VocabEntityService vocabEntityService) {
        this.vocabEntityService = vocabEntityService;
    }

    @GetMapping("/{tableKey}/random-word")
    public VocabEntity getRandomWordForTable(@PathVariable String tableKey, @RequestParam String topics) {
        return vocabEntityService.getRandomWordForTable(tableKey, topics.isEmpty() ? Collections.emptyList() : Arrays.asList(topics.split(",")));
    }
}
