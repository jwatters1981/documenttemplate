package com.watterssoftware.documenttemplate.event;

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
    private byte[] contentData;
}
