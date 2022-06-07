package com.applicationpro.repository;

import com.applicationpro.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    //List<Company> findAllCompanies();
    List<Company> findAll();
}
