package com.applicationpro.converter;

import com.applicationpro.dto.ClientVendorDTO;
import com.applicationpro.service.ClientVendorService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ClientVendorDtoConverter implements Converter<String, ClientVendorDTO> {

    ClientVendorService clientVendorService;

    public ClientVendorDtoConverter(@Lazy ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @Override
    public ClientVendorDTO convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }
        return clientVendorService.findClientVendorById(Long.parseLong(source));
    }
}
