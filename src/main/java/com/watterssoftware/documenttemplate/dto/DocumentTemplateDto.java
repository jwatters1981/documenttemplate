package com.watterssoftware.documenttemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTemplateDto {
    private String templateName;
    private String description;
    private String documentTemplateId;

    private byte[] contentData;


    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DocumentTemplateDto that = (DocumentTemplateDto) o;
        return templateName.equals(that.templateName) && description.equals(that.description) && documentTemplateId.equals(that.documentTemplateId);
    }

    @Override public int hashCode() {
        return Objects.hash(templateName, description, documentTemplateId);
    }
}
