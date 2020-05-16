package org.bubbasmith.vocabtivator.model;

import lombok.Data;

@Data
public class GoogleSpreadsheet {
    private String fileId;
    private String oauthToken;
}
