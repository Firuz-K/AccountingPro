package com.applicationpro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdTime;
    @Column(nullable = false,updatable = false)
    private Long createdBy;
    @Column(nullable = false)
    private LocalDateTime updatedTime;
    @Column(nullable = false)
    private Long updatedBy;
    private Boolean isDeleted=false;

// Do we need PrePersist and PreUpdate
}
