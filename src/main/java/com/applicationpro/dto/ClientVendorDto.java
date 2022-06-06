package com.applicationpro.dto;

import com.applicationpro.enums.CompanyType;
import com.applicationpro.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Data
public class ClientVendorDto {
    private Long id;
    private String companyName;
    private String phone;
    private String email;
    private CompanyDTO company;
    private CompanyType type;
    private String zipCode;
    private String address;
    private State state;
    private Boolean enabled;
}
