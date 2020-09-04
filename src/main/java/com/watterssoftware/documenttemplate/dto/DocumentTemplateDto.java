package com.watterssoftware.documenttemplate.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTemplateDto {
    private String templateName;
    private String description;
    private String documentTemplateId;

    private byte[] contentData;


}
