package com.applicationpro.dto;

import com.applicationpro.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;

//    private CategoryDTO category; // will relation after Category created

    private Integer qty;
    private String unit;
    private Integer lowLimitAlert;
    private Integer tax;

//    private CompanyDTO company; // will relation after Category created

    private Boolean enabled;
    private Integer createdBy;
    private Integer updatedBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedTime;


    private ProductStatus productStatus;

    private Boolean isDeleted;
    private Integer newColumn;

    private String edit;

    private String addProduct;

    // not sure maybe we can discuss later
//    private String management;
//    private String stockManagement;
//    private String invoice;
//    private String reporting;
//    private String administration;

//    public ProductDTO(String name, String description, CategoryDTO category, Integer qty, String unit, Integer lowLimitAlert, Integer tax, CompanyDTO company, Boolean enabled, Integer createdBy, Integer updatedBy, LocalDateTime createdTime, LocalDateTime updatedTime, ProductStatus productStatus, Boolean isDeleted, Integer newColumn) {
//        this.name = name;
//        this.description = description;
//        this.category = category;
//        this.qty = qty;
//        this.unit = unit;
//        this.lowLimitAlert = lowLimitAlert;
//        this.tax = tax;
//        this.company = company;
//        this.enabled = enabled;
//        this.createdBy = createdBy;
//        this.updatedBy = updatedBy;
//        this.createdTime = createdTime;
//        this.updatedTime = updatedTime;
//        this.productStatus = productStatus;
//        this.isDeleted = isDeleted;
//        this.newColumn = newColumn;
//    }
}
