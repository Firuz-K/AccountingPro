package com.applicationpro.service;



import com.applicationpro.dto.ClientVendorDTO;
import com.applicationpro.dto.InvoiceDTO;
import com.applicationpro.dto.common.TotalInvoiceDto;
import com.applicationpro.enums.InvoiceType;

import java.util.List;

public interface InvoiceService {

    List<InvoiceDTO> listAllInvoices();

    List<InvoiceDTO> listAllInvoices(InvoiceType invoiceType);

    List<InvoiceDTO> listAllNonDraftInvoicesOrderByDate();

    List<TotalInvoiceDto> listAllTotalInvoices(InvoiceType invoiceType);

    TotalInvoiceDto findTotalInvoiceById(Long id);

    InvoiceDTO findInvoiceById(Long id);

    void approve(Long id) throws Exception;

    void update(InvoiceDTO invoiceDto);

    void delete(Long id);

    InvoiceDTO createDraftInvoice(InvoiceType invoiceType, ClientVendorDTO clientVendorDto);

    void save(InvoiceDTO invoiceDto);

    Double getCompanyTotalAmount(InvoiceType invoiceType);

    Double getCompanyTotalTax();

}
