package com.applicationpro.service.impl;

import com.applicationpro.dto.ClientVendorDto;
import com.applicationpro.entity.ClientVendor;
import com.applicationpro.enums.CompanyType;
import com.applicationpro.repository.ClientVendorRepository;
import com.applicationpro.service.ClientVendorService;
import com.applicationpro.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {

    private final ClientVendorRepository clientVendorRepository;
    private final MapperUtil mapperUtil;

    public ClientVendorServiceImpl(ClientVendorRepository clientVendorRepository, MapperUtil mapperUtil) {
        this.clientVendorRepository = clientVendorRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ClientVendorDto> listAllClientVendors() {
        return clientVendorRepository.findAll().stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientVendorDto> listAllActiveClients() {
        return clientVendorRepository.findAllByCompanyTypeAndEnabledIsTrue(CompanyType.CLIENT).stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientVendorDto> listAllActiveVendors() {
        return clientVendorRepository.findAllByCompanyTypeAndEnabledIsTrue(CompanyType.VENDOR).stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDto()))
                .collect(Collectors.toList());
    }

    @Override
    public ClientVendorDto findClientVendorById(Long id) {
        return mapperUtil.convert(clientVendorRepository.getById(id), new ClientVendorDto());
    }

    @Override
    public void save(ClientVendorDto clientVendorDto) {
        clientVendorDto.setEnabled(true);
        clientVendorRepository.save(mapperUtil.convert(clientVendorDto, new ClientVendor()));
    }

    @Override
    public void update(ClientVendorDto clientVendorDto) {
        clientVendorRepository.save(mapperUtil.convert(clientVendorDto, new ClientVendor()));
    }

    // todo: May be invoices related to this client vendor should be also deleted
    @Override
    public void delete(Long id) {
        ClientVendor clientVendor = clientVendorRepository.getById(id);
        clientVendor.setIsDeleted(true);
        clientVendorRepository.save(clientVendor);
    }
}
