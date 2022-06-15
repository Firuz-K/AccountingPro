package com.applicationpro.converter;

import com.applicationpro.dto.CompanyDTO;
import com.applicationpro.service.CompanyService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;

public class CompanyDTOConverter implements Converter<String, CompanyDTO> {

    CompanyService companyService;

    public CompanyDTOConverter(@Lazy CompanyService companyService) {
        this.companyService = companyService;
    }


    @Override
    public CompanyDTO convert(String source) {
        if (source == null || source.equals("")){
            return null;
        }

        return companyService.findCompanyById(Long.parseLong(source));
    }
}
