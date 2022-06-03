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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company; // I made the return type a list , omer
}

/*
@NoArgsConstructor
@Data
@Entity
@Where(clause = "is_deleted = false")
@Table(name = "categories")
public class Category extends BaseEntity{


    private String description;
    @ManyToOne //should we use the fetch lazy --- why we do not use the many to many ...
    @JoinColumn(name = "company_id")
    private Company company; //private  List<Company> company;
    private Boolean enabled;
 */