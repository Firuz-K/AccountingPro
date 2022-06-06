package com.applicationpro.converter;

import com.applicationpro.dto.ClientVendorDto;
import com.applicationpro.service.ClientVendorService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;

public class ClientVendorDtoConverter implements Converter<String, ClientVendorDto> {

    ClientVendorService clientVendorService;

    public ClientVendorDtoConverter(@Lazy ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @Override
    public ClientVendorDto convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }
        return clientVendorService.findClientVendorById(Long.parseLong(source));
    }
}
