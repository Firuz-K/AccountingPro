package com.applicationpro.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
//    @JoinTable(name = "category_company_rel",
//            joinColumns = @JoinColumn(name="category_id"),
//            inverseJoinColumns = @JoinColumn(name = "company_id"))
//    private List<Company> companies;

    @ManyToOne (fetch = FetchType.LAZY)
    private Company company;

}
