package org.bubbasmith.vocabtivator.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CreatedVocabTableDTO {
    private String key;
    private List<String> topics;
}
