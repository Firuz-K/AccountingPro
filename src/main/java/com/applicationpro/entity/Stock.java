package com.applicationpro.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stock_details")
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted=false")
public class Stock extends BaseEntity{


    private int quantity;
    private int remainingQuantity;
    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;




}
