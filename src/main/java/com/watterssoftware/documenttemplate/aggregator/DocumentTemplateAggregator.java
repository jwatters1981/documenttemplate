package com.watterssoftware.documenttemplate.aggregator;

import com.watterssoftware.documenttemplate.command.CreateDocumentTemplateCommand;
import com.watterssoftware.documenttemplate.command.UpdateDocumentTemplateCommand;
import com.watterssoftware.documenttemplate.event.CreateDocumentTemplateEvent;
import com.watterssoftware.documenttemplate.event.UpdateDocumentTemplateEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
@Slf4j
public class DocumentTemplateAggregator {

    @AggregateIdentifier
    private UUID documentTemplateID;
    private String documentTemplateName;
    private String userId;
    private byte[] contentData;

    @CommandHandler public DocumentTemplateAggregator(CreateDocumentTemplateCommand createDocumentTemplateCommand) {
        AggregateLifecycle.apply(new CreateDocumentTemplateEvent(createDocumentTemplateCommand.getDocumentTemplateID(), createDocumentTemplateCommand.getDocumentTemplateName(), createDocumentTemplateCommand.getUserId(), createDocumentTemplateCommand.getContentData()));
        log.info("CreateDocumentTemplateCommand created");
    }

    @CommandHandler public void handle(UpdateDocumentTemplateCommand updateDocumentTemplateCommand) {
        AggregateLifecycle.apply(new UpdateDocumentTemplateEvent(updateDocumentTemplateCommand.getDocumentTemplateID().toString(), updateDocumentTemplateCommand.getDocumentTemplateName(), updateDocumentTemplateCommand.getUserId(), updateDocumentTemplateCommand.getContentData()));
    }

    @EventSourcingHandler public void on(CreateDocumentTemplateEvent event) {
        this.documentTemplateID = event.getDocumentTemplateID();
        this.userId = event.getUserId();
        this.contentData = event.getContentData();
    }

    @EventSourcingHandler public void on(UpdateDocumentTemplateCommand event) {
        this.documentTemplateID = event.getDocumentTemplateID();
        this.userId = event.getUserId();
        this.contentData = event.getContentData();
    }
}
