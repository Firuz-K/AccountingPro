package com.applicationpro.service.impl;

import com.applicationpro.dto.ClientVendorDTO;
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
    public List<ClientVendorDTO> listAllClientVendors() {
        return clientVendorRepository.findAll().stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientVendorDTO> listAllActiveClients() {
        return clientVendorRepository.findAllByCompanyTypeAndEnabledIsTrue(CompanyType.CLIENT).stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientVendorDTO> listAllActiveVendors() {
        return clientVendorRepository.findAllByCompanyTypeAndEnabledIsTrue(CompanyType.VENDOR).stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ClientVendorDTO findClientVendorById(Long id) {
        return mapperUtil.convert(clientVendorRepository.getById(id), new ClientVendorDTO());
    }

    @Override
    public void save(ClientVendorDTO clientVendorDto) {
        clientVendorDto.setEnabled(true);
        clientVendorRepository.save(mapperUtil.convert(clientVendorDto, new ClientVendor()));
    }

    @Override
    public void update(ClientVendorDTO clientVendorDto) {
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
