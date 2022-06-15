package com.applicationpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockDTO {

    private Long id;
    private LocalDate date;
    private Integer quantity;
    private Integer remainingQuantity;
    private Double price;
    private ProductDTO product;

}

