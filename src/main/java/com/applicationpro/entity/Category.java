package com.applicationpro.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categories") // should we keep the name as is?
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
// do we need all arg constructor?
public class Category extends BaseEntity {

    private String description;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private List<Company> company; // I made the return type a list , omer
}
