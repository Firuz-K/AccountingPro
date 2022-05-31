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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer qty;
    private String unit;
    private Integer lowLimitAlert;
    private Integer tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private Integer newColumn; // what is this?




}
