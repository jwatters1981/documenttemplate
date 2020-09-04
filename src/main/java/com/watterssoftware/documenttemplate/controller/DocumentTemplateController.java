package com.watterssoftware.documenttemplate.controller;

import com.watterssoftware.documenttemplate.dto.DocumentTemplateDto;
import com.watterssoftware.documenttemplate.exception.DocumentTemplateException;
import com.watterssoftware.documenttemplate.model.DocumentTemplate;
import com.watterssoftware.documenttemplate.service.DocumentTemplateService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/template")
@Api(value = "Document Template upload")
@AllArgsConstructor
@Slf4j
public class DocumentTemplateController {

    private final DocumentTemplateService documentTemplateService;


    @PostMapping @ResponseStatus(value = CREATED) public CompletableFuture<DocumentTemplate> createDocument(@RequestParam("documentName") String documentName, @RequestParam("documentDescription") String documentDescription, @RequestParam("file") MultipartFile file) {
        log.info("Uploading template file named: " + documentName);
        DocumentTemplateDto documentTemplate = populateDocumentTemplateDTO(documentName, documentDescription, file);
        return this.documentTemplateService.createDocumentTemplate(documentTemplate);
    }

    @PutMapping public CompletableFuture<DocumentTemplate> updateDocument(@RequestParam("documentName") String documentName, @RequestParam("documentDescription") String documentDescription, @RequestParam("file") MultipartFile file) {
        log.info("Updating template file named: " + documentName);
        DocumentTemplateDto documentTemplate = populateDocumentTemplateDTO(documentName, documentDescription, file);
        return this.documentTemplateService.uploadDocument(documentTemplate);
    }

    private DocumentTemplateDto populateDocumentTemplateDTO(@RequestParam("documentName") String documentName, @RequestParam("documentDescription") String documentDescription, @RequestParam("file") MultipartFile file) {
        DocumentTemplateDto documentTemplate = new DocumentTemplateDto();
        documentTemplate.setTemplateName(documentName);
        documentTemplate.setDescription(documentDescription);
        try {
            documentTemplate.setContentData(file.getBytes());
        } catch (IOException e) {
            throw new DocumentTemplateException("Error Uploading file " + file.getName(), e);
        }
        return documentTemplate;
    }
}
