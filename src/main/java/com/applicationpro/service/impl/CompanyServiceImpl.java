package com.applicationpro.service.impl;

import com.applicationpro.dto.CompanyDTO;
import com.applicationpro.entity.Company;
import com.applicationpro.repository.CompanyRepository;
import com.applicationpro.service.CompanyService;
import com.applicationpro.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final MapperUtil mapperUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository, MapperUtil mapperUtil) {
        this.companyRepository = companyRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CompanyDTO> listAllCompanies() {
        return companyRepository.findAll().stream()
                                           .map(company -> mapperUtil.convert(company, new CompanyDTO()))
                                            .collect(Collectors.toList());
    }

    @Override
    public List<CompanyDTO> findAll() {
        return companyRepository.findAll().stream()
                .map(company -> mapperUtil.convert(company, new CompanyDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CompanyDTO> listAllActiveCompanies() {
        return null;
    }

    @Override
    public CompanyDTO findCompanyById(Long id) {
        Company company = companyRepository.findById(id).get();
        return mapperUtil.convert(company,new CompanyDTO());
    }

    @Override
    public void save(CompanyDTO companyDTO) {
        companyDTO.setEnabled(true);

        companyRepository.save(mapperUtil.convert(companyDTO, new Company()));
    }

    @Override
    public void update(CompanyDTO companyDTO) {

    }

    @Override
    public void delete(CompanyDTO companyDTO) {

    }

    @Override
    public CompanyDTO findByCompanyTitle(String title) {

        Company company = companyRepository.findCompanyByTitle(title);
        return mapperUtil.convert(company,new CompanyDTO());

    }
}
