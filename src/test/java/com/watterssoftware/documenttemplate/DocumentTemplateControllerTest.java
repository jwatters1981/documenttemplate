package com.watterssoftware.documenttemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.watterssoftware.documenttemplate.controller.DocumentTemplateController;
import com.watterssoftware.documenttemplate.dto.DocumentTemplateDto;
import com.watterssoftware.documenttemplate.model.DocumentTemplate;
import com.watterssoftware.documenttemplate.service.DocumentTemplateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(DocumentTemplateController.class)
public class DocumentTemplateControllerTest {
    public static final String DOC_NAME = "Doc Name";
    public static final String DOC_DESC = "Doc Desc";
    public static final String SOME_XML = "some xml";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentTemplateService service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        //set up the expected data
        UUID expectedID = UUID.randomUUID();
        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain", SOME_XML.getBytes());
        DocumentTemplate documentTemplate = new DocumentTemplate();
        documentTemplate.setDocumentTemplateId(expectedID);
        documentTemplate.setName(DOC_NAME);
        documentTemplate.setDescription(DOC_DESC);
        documentTemplate.setContentData("some xml".getBytes());

        //mock service response
        when(service.createDocumentTemplate(Mockito.any(DocumentTemplateDto.class))).thenReturn(CompletableFuture.completedFuture(documentTemplate));

        //call the rest api in an async way
        MvcResult  result = this.mockMvc.perform(multipart("/template").file(firstFile).param("documentName", "Doc Name").param(
                "documentDescription", "Doc Desc")).andReturn();
        mockMvc.perform(asyncDispatch(result)).andDo(print()).andExpect(status().isCreated()).andExpect(content().string(containsString(expectedID.toString())));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

