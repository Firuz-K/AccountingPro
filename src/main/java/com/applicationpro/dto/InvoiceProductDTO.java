package com.applicationpro.dto;

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
public class InvoiceProductDTO {
    private Long id;
    private InvoiceDTO invoice;
    private ProductDTO product;
    private String name;
    private Integer quantity;
    private Double price;
    private Double tax;
    private Double profit;
    private Boolean enabled;
}
