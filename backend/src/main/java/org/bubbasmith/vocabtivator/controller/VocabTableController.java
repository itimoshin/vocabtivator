package org.bubbasmith.vocabtivator.controller;

import org.bubbasmith.vocabtivator.model.GoogleSpreadsheet;
import org.bubbasmith.vocabtivator.model.dto.CreatedVocabTableDTO;
import org.bubbasmith.vocabtivator.vocab.service.VocabTableService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("vocab-table")
public class VocabTableController {

    private final VocabTableService vocabTableService;

    public VocabTableController(VocabTableService vocabTableService) {
        this.vocabTableService = vocabTableService;
    }

    @PostMapping("google")
    public CreatedVocabTableDTO uploadGoogleSpreadsheet(@RequestBody GoogleSpreadsheet googleSpreadsheet) {
        return vocabTableService.extractVocabFromGoogleSpreadsheet(googleSpreadsheet);
    }

    @PostMapping(value = "xls")
    public CreatedVocabTableDTO uploadXlsFile(@RequestPart("file") MultipartFile file) {
        return vocabTableService.extractVocabFromFile(file);
    }
}
