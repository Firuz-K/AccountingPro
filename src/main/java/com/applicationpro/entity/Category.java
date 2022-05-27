package com.applicationpro.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Category extends BaseEntity {

    private String description;
    private Boolean enabled;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id")
//    private Company company;
}
