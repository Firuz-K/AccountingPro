package com.applicationpro.entity;

import com.applicationpro.enums.ProductStatus;
import com.applicationpro.enums.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
@Where(clause = "is_deleted=false")
public class Product extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private Integer lowLimitAlert;
    private Double tax;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

}
