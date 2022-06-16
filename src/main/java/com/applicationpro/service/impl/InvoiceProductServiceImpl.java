package com.applicationpro.service.impl;

import com.applicationpro.dto.InvoiceProductDTO;
import com.applicationpro.dto.common.SoldProductDto;
import com.applicationpro.entity.InvoiceProduct;
import com.applicationpro.enums.InvoiceType;
import com.applicationpro.repository.InvoiceProductRepository;
import com.applicationpro.repository.InvoiceRepository;
import com.applicationpro.service.InvoiceProductService;
import com.applicationpro.service.ProductService;
import com.applicationpro.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class  InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final MapperUtil mapperUtil;
    private final InvoiceRepository invoiceRepository;
    private final ProductService productService;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, MapperUtil mapperUtil, InvoiceRepository invoiceRepository, ProductService productService) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.mapperUtil = mapperUtil;
        this.invoiceRepository = invoiceRepository;
        this.productService = productService;
    }

    @Override
    public List<InvoiceProductDTO> listAllInvoiceProducts() {
        return invoiceProductRepository.findAll().stream()
                .map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceProductDTO> listAllActiveInvoiceProducts() {
        return invoiceProductRepository.findAllByEnabledIsTrue().stream()
                .map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceProductDTO> listInvoiceProductsByInvoiceId(Long invoiceId) {
        return invoiceProductRepository.findAllByInvoice(invoiceRepository.getById(invoiceId)).stream()
                .map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SoldProductDto> listAllSoldProducts() {
        return invoiceProductRepository.getSoldInvoiceProductList();
    }

    @Override
    public InvoiceProductDTO findInvoiceProductById(long invoiceProductId) {
        return mapperUtil.convert(invoiceProductRepository.getById(invoiceProductId), new InvoiceProductDTO());
    }

    @Override
    public Double getCompanyTotalProfitLoss() {
        return listAllSoldProducts().stream()
                .map(SoldProductDto::getProfitLoss)
                .reduce(Double::sum).orElse(0.00);
    }

    @Override
    public void save(InvoiceProductDTO invoiceProductDto) {
        invoiceProductDto.setEnabled(false);
        invoiceProductRepository.save(mapperUtil.convert(invoiceProductDto, new InvoiceProduct()));
    }

    @Override
    public void update(InvoiceProductDTO invoiceProductDto) {
        invoiceProductRepository.save(mapperUtil.convert(invoiceProductDto, new InvoiceProduct()));
    }

    @Override
    public void delete(Long invoiceProductId) {
//        InvoiceProduct invoiceProduct = invoiceProductRepository.getById(invoiceProductId);
//        invoiceProduct.setEnabled(false);
//        invoiceProduct.setIsDeleted(true);
//        invoiceProductRepository.save(invoiceProduct);
        invoiceProductRepository.deleteById(invoiceProductId);
    }

    @Override
    public InvoiceType getInvoiceType(Long id) {
        return invoiceProductRepository.getInvoiceTypeProduct(id);
    }
}
