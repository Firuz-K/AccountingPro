package com.applicationpro.repository;

import com.applicationpro.entity.ClientVendor;
import com.applicationpro.entity.Company;
import com.applicationpro.enums.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientVendorRepository extends JpaRepository<ClientVendor, Long> {

    List<ClientVendor> findAllByCompany(Company company);

    List<ClientVendor> findAllByCompanyAndCompanyType(Company company, CompanyType companyType);

    List<ClientVendor> findAllByCompanyAndCompanyTypeAndEnabledIsTrue(Company company, CompanyType companyType);

    List<ClientVendor> findAllByCompanyTypeAndEnabledIsTrue(CompanyType companyType);

}