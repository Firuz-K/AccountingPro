package com.applicationpro.service;

import com.applicationpro.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> listAllCompanies();

    List<CompanyDTO> listAllActiveCompanies();

    CompanyDTO findCompanyById(Long id);

    void save(CompanyDTO companyDTO);

    void update(CompanyDTO companyDTO);

    void delete(CompanyDTO companyDTO);
}
