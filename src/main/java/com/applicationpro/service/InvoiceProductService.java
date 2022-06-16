package com.applicationpro.service;


import com.applicationpro.dto.InvoiceProductDTO;
import com.applicationpro.dto.common.SoldProductDto;
import com.applicationpro.enums.InvoiceType;

import java.util.List;

public interface InvoiceProductService {

    List<InvoiceProductDTO> listAllInvoiceProducts();

    List<InvoiceProductDTO> listAllActiveInvoiceProducts();

    List<InvoiceProductDTO> listInvoiceProductsByInvoiceId(Long invoiceId);

    List<SoldProductDto> listAllSoldProducts();

    InvoiceProductDTO findInvoiceProductById(long invoiceProductId);

    Double getCompanyTotalProfitLoss();

    void save(InvoiceProductDTO invoiceProductDto);

    void update(InvoiceProductDTO invoiceProductDto);

    void delete(Long invoiceProductId);

    InvoiceType getInvoiceType(Long id);

}
