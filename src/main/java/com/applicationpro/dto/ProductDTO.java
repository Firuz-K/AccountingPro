package com.applicationpro.dto;

import com.applicationpro.entity.Category;
import com.applicationpro.enums.ProductStatus;
import com.applicationpro.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;

    private Integer qty;
    private Unit unit;
    private Integer lowLimitAlert;
    private Category category;

    private ProductStatus productStatus;

    public ProductDTO(String name, String description, Integer qty, Unit unit, Integer lowLimitAlert, Category category, ProductStatus productStatus) {
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.unit = unit;
        this.lowLimitAlert = lowLimitAlert;
        this.category = category;
        this.productStatus = productStatus;
    }
}
