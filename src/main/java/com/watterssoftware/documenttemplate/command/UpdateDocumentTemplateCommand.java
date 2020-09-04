package com.watterssoftware.documenttemplate.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDocumentTemplateCommand {

    @TargetAggregateIdentifier
    private UUID documentTemplateID;
    private String documentTemplateName;
    private String userId;
    private byte[] contentData;

}
