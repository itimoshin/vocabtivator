package org.bubbasmith.vocabtivator.vocab.workbook.generator;

import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bubbasmith.vocabtivator.model.GoogleSpreadsheet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;

@Service
public class GoogleSpreadsheetWorkbookGenerator implements WorkbookGenerator<GoogleSpreadsheet> {

    @SneakyThrows
    @Override
    public XSSFWorkbook generateWorkbook(GoogleSpreadsheet googleSpreadsheet) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + googleSpreadsheet.getOauthToken());
        HttpEntity entity = new HttpEntity(headers);
        byte[] arr = new RestTemplate().exchange(
                String.format("https://www.googleapis.com/drive/v3/files/%s/export?mimeType=application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", googleSpreadsheet.getFileId()), HttpMethod.GET, entity, byte[].class).getBody();
        return new XSSFWorkbook(new ByteArrayInputStream(arr));
    }
}
