package com.applicationpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockDTO {

    private Long id;
    private LocalDateTime date;
    private Integer quantity;
    private Integer remainingQuantity;
    private int price;
    private ProductDTO product;

}

