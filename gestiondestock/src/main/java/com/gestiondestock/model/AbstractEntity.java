package com.gestiondestock.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public  class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @CreatedDate
    @Column(name = "creationDate",nullable = false,updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    private Instant LastModifiedDate;








}
