package org.bubbasmith.vocabtivator.vocab.workbook.generator;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface WorkbookGenerator<T> {

    XSSFWorkbook generateWorkbook(T input);
}
