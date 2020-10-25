package org.bubbasmith.vocabtivator.common.vocab.workbook.generator;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface WorkbookGenerator<T> {

    XSSFWorkbook generateWorkbook(T input);
}
