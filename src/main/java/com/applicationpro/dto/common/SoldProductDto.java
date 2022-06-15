package com.applicationpro.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Data
public class SoldProductDto {
    private Long productId;
    private String productName;
    private Long soldQuantity;
    private Double profitLoss;
}
