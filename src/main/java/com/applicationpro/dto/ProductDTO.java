package com.applicationpro.dto;

import com.applicationpro.enums.ProductStatus;
import com.applicationpro.enums.Unit;
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
public class ProductDTO {
    private Long id;
    private Boolean enabled;
    private String name;
    private String description;

    private Integer qty;

    private CategoryDTO category;

    private Unit unit;
    private Integer lowLimitAlert;
    private Double tax;
    private CompanyDTO company;
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
