package com.watterssoftware.documenttemplate.projection;

import com.watterssoftware.documenttemplate.event.CreateDocumentTemplateEvent;
import com.watterssoftware.documenttemplate.event.UpdateDocumentTemplateEvent;
import com.watterssoftware.documenttemplate.model.DocumentTemplate;
import com.watterssoftware.documenttemplate.query.FindDocumentTemplateQuery;
import com.watterssoftware.documenttemplate.repository.DocumentTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class DocumentTemplateProjection {

    private final DocumentTemplateRepository repository;

    @EventHandler
    public void on(CreateDocumentTemplateEvent event) {
        log.info("Handling CreateDocumentTemplateEvent {}", event.getDocumentTemplateID());
        DocumentTemplate documentTemplate = new DocumentTemplate(
                event.getDocumentTemplateID(),
                event.getDocumentTemplateName(),
                event.getUserId(), new Date(), event.getContentData());

        this.repository.save(documentTemplate);
        log.info("Document Template Saved "+documentTemplate.getDocumentTemplateId());
    }

    @EventHandler
    public void on(UpdateDocumentTemplateEvent event)
    {
        log.info("UpdateDocumentTemplateEvent update called {}",event.getDocumentTemplateName());
        DocumentTemplate documentTemplate = new DocumentTemplate(
                UUID.fromString(event.getDocumentTemplateID()),
                event.getDocumentTemplateName(),
                event.getUserId(), new Date(), event.getContentData());
        this.repository.save(documentTemplate);
    }

    @QueryHandler
    public DocumentTemplate handle(FindDocumentTemplateQuery query) {
        log.debug("Handling FindDocumentTemplateQuery query: {}", query);
        return this.repository.findById(query.getDocumentTemplateId()).orElseThrow();
    }
}
