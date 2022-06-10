package com.applicationpro.dto;

import com.applicationpro.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockDTO {

    private Long id;
    private LocalDate date;
    private int quantity;
    private int remainingQuantity;
    private BigDecimal price;
    private Product product;

}

