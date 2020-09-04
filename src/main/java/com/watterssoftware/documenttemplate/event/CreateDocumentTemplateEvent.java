package com.watterssoftware.documenttemplate.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateDocumentTemplateEvent {
    private UUID documentTemplateID;
    private String documentTemplateName;
    private String userId;
    @JsonIgnore
    private byte[] contentData;
}
