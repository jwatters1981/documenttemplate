package com.watterssoftware.documenttemplate.service;

import com.watterssoftware.documenttemplate.command.CreateDocumentTemplateCommand;
import com.watterssoftware.documenttemplate.command.UpdateDocumentTemplateCommand;
import com.watterssoftware.documenttemplate.dto.DocumentTemplateDto;
import com.watterssoftware.documenttemplate.event.UpdateDocumentTemplateEvent;
import com.watterssoftware.documenttemplate.model.DocumentTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@AllArgsConstructor
public class DocumentTemplateService {
    private final CommandGateway commandGateway;

    public CompletableFuture<DocumentTemplate> createDocumentTemplate(DocumentTemplateDto documentTemplateDto)
    {
        return this.commandGateway.send(new CreateDocumentTemplateCommand(
                UUID.randomUUID(),
                documentTemplateDto.getTemplateName(),
                documentTemplateDto.getDescription(), documentTemplateDto.getContentData()
        ));
    }

    public CompletableFuture<DocumentTemplate> uploadDocument(DocumentTemplateDto documentTemplateDto) {
        return this.commandGateway.send(new UpdateDocumentTemplateCommand( UUID.fromString(documentTemplateDto.getDocumentTemplateId()),
                documentTemplateDto.getTemplateName(),
                documentTemplateDto.getDescription(), documentTemplateDto.getContentData()));
    }
}
