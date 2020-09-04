package com.watterssoftware.documenttemplate.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDocumentTemplateEvent {
    private String documentTemplateID;
    private String documentTemplateName;
    private String userId;
    private byte[] contentData;
}
