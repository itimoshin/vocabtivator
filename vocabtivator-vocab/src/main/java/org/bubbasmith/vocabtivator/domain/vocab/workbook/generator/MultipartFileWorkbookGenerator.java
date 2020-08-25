package org.bubbasmith.vocabtivator.domain.vocab.workbook.generator;

import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MultipartFileWorkbookGenerator implements WorkbookGenerator<MultipartFile> {

    @SneakyThrows
    @Override
    public XSSFWorkbook generateWorkbook(MultipartFile input) {
        return new XSSFWorkbook(input.getInputStream());

    }
}
