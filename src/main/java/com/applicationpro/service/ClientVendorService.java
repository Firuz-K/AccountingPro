package com.applicationpro.service;

import com.applicationpro.dto.ClientVendorDto;

import java.util.List;

public interface ClientVendorService {
    List<ClientVendorDto> listAllClientVendors();

    List<ClientVendorDto> listAllActiveClients();

    List<ClientVendorDto> listAllActiveVendors();

    ClientVendorDto findClientVendorById(Long id);

    void save(ClientVendorDto clientVendorDto);

    void update(ClientVendorDto clientVendorDto);

    void delete(Long id);
}
