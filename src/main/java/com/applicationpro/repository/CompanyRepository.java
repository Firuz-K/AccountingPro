package com.applicationpro.repository;

import com.applicationpro.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAll();

    //@Query(value = "SELECT * FROM companies", nativeQuery = true);
    //List<Company> retrieveAllCompanies();

    Company findCompanyByTitle(String title);

}
