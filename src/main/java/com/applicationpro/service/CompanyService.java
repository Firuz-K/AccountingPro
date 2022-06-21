package com.applicationpro.service;

import com.applicationpro.dto.CompanyDTO;
import com.applicationpro.entity.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> listAllCompanies();

    List<CompanyDTO> findAll();
    List<CompanyDTO> listAllActiveCompanies();

    CompanyDTO findCompanyById(Long id);

    void save(CompanyDTO companyDTO);

    void update(CompanyDTO companyDTO);

    void delete(CompanyDTO companyDTO);


    CompanyDTO findByCompanyTitle(String title);
}
