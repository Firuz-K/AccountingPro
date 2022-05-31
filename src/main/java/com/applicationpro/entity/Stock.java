package com.applicationpro.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_details")
@NoArgsConstructor
@AllArgsConstructor
public class Stock {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "i_date",columnDefinition = "DATE")
    private LocalDateTime time;

    private int quantity;
    private int remainingQuantity;
    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
