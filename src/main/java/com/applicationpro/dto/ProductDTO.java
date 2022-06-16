package com.applicationpro.dto;

import com.applicationpro.enums.InvoiceType;
import com.applicationpro.enums.ProductStatus;
import com.applicationpro.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private CategoryDTO category;
    private Integer quantity;
    private Unit unit;
    private Integer lowLimitAlert;
    private Double tax;
    private CompanyDTO company;
    private ProductStatus productStatus;

    private Double BasePrice;
    private String invoiceType;
    private LocalDate invoiceDate;

}
