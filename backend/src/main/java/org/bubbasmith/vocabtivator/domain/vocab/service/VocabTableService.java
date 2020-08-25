package org.bubbasmith.vocabtivator.domain.vocab.service;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bubbasmith.vocabtivator.domain.vocab.workbook.generator.GoogleSpreadsheetWorkbookGenerator;
import org.bubbasmith.vocabtivator.domain.vocab.workbook.generator.MultipartFileWorkbookGenerator;
import org.bubbasmith.vocabtivator.model.GoogleSpreadsheet;
import org.bubbasmith.vocabtivator.model.dto.CreatedVocabTableDTO;
import org.bubbasmith.vocabtivator.model.vocab.VocabEntity;
import org.bubbasmith.vocabtivator.model.vocab.VocabTable;
import org.bubbasmith.vocabtivator.model.vocab.VocabTopic;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

@Service
public class VocabTableService {

    private final VocabStorageService vocabStorageService;
    private final MultipartFileWorkbookGenerator multipartFileWorkbookGenerator;
    private final GoogleSpreadsheetWorkbookGenerator googleSpreadsheetWorkbookGenerator;

    public VocabTableService(VocabStorageService vocabStorageService, MultipartFileWorkbookGenerator multipartFileWorkbookGenerator, GoogleSpreadsheetWorkbookGenerator googleSpreadsheetWorkbookGenerator) {
        this.vocabStorageService = vocabStorageService;
        this.multipartFileWorkbookGenerator = multipartFileWorkbookGenerator;
        this.googleSpreadsheetWorkbookGenerator = googleSpreadsheetWorkbookGenerator;
    }

    public CreatedVocabTableDTO extractVocabFromFile(MultipartFile multipartFile) {
        return extractVocab(multipartFileWorkbookGenerator.generateWorkbook(multipartFile));
    }

    public CreatedVocabTableDTO extractVocabFromGoogleSpreadsheet(GoogleSpreadsheet googleSpreadsheet) {
        return extractVocab(googleSpreadsheetWorkbookGenerator.generateWorkbook(googleSpreadsheet));
    }

    @SneakyThrows
    private CreatedVocabTableDTO extractVocab(XSSFWorkbook workbook) {
        Map<String, VocabTopic> topics = StreamSupport.stream(workbook.spliterator(), false)
                .map(this::extractTopicSheet)
                .collect(Collectors.toMap(VocabTopic::getName, Function.identity()));
        VocabTable vocabTable = new VocabTable().setTopics(topics);
        String vocabTableKey = vocabStorageService.putVocabTable(vocabTable);
        return new CreatedVocabTableDTO()
                .setKey(vocabTableKey)
                .setTopics(new ArrayList<>(vocabTable.getTopics().keySet()));
    }

    private VocabTopic extractTopicSheet(Sheet sheet) {
        int emptyRowsInSequence = 0;

        List<VocabEntity> vocabEntityList = new ArrayList<>();
        for (Row row : sheet) {
            if (emptyRowsInSequence > 5) {
                break;
            }
            VocabEntity vocabEntity = extractVocabEntity(row);
            if (vocabEntity.getValue().isEmpty() || this.inMergedRegion(row.getCell(0))) {
                emptyRowsInSequence++;
            } else {
                emptyRowsInSequence = 0;
                vocabEntityList.add(vocabEntity);
            }
        }
        return new VocabTopic()
                .setName(sheet.getSheetName())
                .setEntities(vocabEntityList);
    }

    private VocabEntity extractVocabEntity(Row row) {
        String examplesStringVal = row.getCell(2, CREATE_NULL_AS_BLANK).getStringCellValue();
        return new VocabEntity()
                .setValue(row.getCell(0, CREATE_NULL_AS_BLANK).getStringCellValue())
                .setMeaning(row.getCell(1, CREATE_NULL_AS_BLANK).getStringCellValue())
                .setExamples(examplesStringVal.isEmpty() ? Collections.emptyList()
                        : Arrays.asList(examplesStringVal.split(";")))
                .setUseStrategy(row.getCell(3, CREATE_NULL_AS_BLANK).getStringCellValue());
    }

    private boolean inMergedRegion(Cell cell) {
        List<CellRangeAddress> mergedRegions = cell.getRow().getSheet().getMergedRegions();
        for (CellRangeAddress mergedRegion : mergedRegions) {
            if (cell.getRowIndex() >= mergedRegion.getFirstRow()
                    && cell.getRowIndex() <= mergedRegion.getLastRow()
                    && cell.getColumnIndex() >= mergedRegion.getFirstColumn()
                    && cell.getColumnIndex() <= mergedRegion.getLastColumn()) {
                return true;
            }
        }
        return false;
    }
}
