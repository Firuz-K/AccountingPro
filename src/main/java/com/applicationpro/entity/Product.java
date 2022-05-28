package com.applicationpro.entity;

import com.applicationpro.enums.ProductStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(name = "products")
@Where(clause = "is_deleted=false")
public class Product extends BaseEntity{
    private String name;
    private String description;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private Category category; // will relation after Category created

    private Integer qty;
    private String unit;
    private Integer lowLimitAlert;
    private Integer tax;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id")
//    private Company company; // will relation after Category created

//    private Boolean enabled;
//    private Integer createdBy;
//    private Integer updatedBy;
//
//    @Column(columnDefinition = "DATE")
//    private LocalDate createdTime;
//
//    @Column(columnDefinition = "DATE")
//    private LocalDate updatedTime;

    // they exist in base entity

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

  //  private Boolean isDeleted;
    private Integer newColumn; // what is this?




}
