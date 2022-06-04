package com.applicationpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    private Long id;
    private String description;
//    private Boolean status; instead enabled, should we have status, checked UI
    private Boolean enabled;
    private CompanyDTO company;





}
