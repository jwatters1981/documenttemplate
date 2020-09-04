package com.watterssoftware.documenttemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DocumentTemplate {
    @Id
    @Column(name = "DOCUMENT_TEMPLATE_ID")
    private UUID documentTemplateId;

    @Column(name = "TEMPLATE_NAME")
    private String name;

    @Column(name = "TEMPLATE_DESCRIPTION")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;

    @Column(name = "CONTENT_DATA")
    @Lob
    @XmlTransient
    private byte[] contentData;
}
