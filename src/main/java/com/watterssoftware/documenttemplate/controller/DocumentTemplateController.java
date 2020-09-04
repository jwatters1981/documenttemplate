package com.watterssoftware.documenttemplate.controller;

import com.watterssoftware.documenttemplate.dto.DocumentTemplateDto;
import com.watterssoftware.documenttemplate.model.DocumentTemplate;
import com.watterssoftware.documenttemplate.service.DocumentTemplateService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
    @PostMapping
    @ResponseStatus(value = CREATED)
    public CompletableFuture<DocumentTemplate> createDocument(@RequestBody DocumentTemplateDto documentTemplate) throws IOException {
        documentTemplate.setContentData("".getBytes());
        CompletableFuture<DocumentTemplate> completableFuture = this.documentTemplateService.createDocumentTemplate(documentTemplate);
        return completableFuture;
    }

    @PutMapping
    public CompletableFuture<DocumentTemplate> updateDocument(@RequestBody DocumentTemplateDto documentTemplate) throws IOException {
        documentTemplate.setContentData("".getBytes());
        CompletableFuture<DocumentTemplate> completableFuture = this.documentTemplateService.uploadDocument(documentTemplate);
        return completableFuture;
    }
}
