package com.applicationpro.repository;

import com.applicationpro.dto.common.SoldProductDto;
import com.applicationpro.entity.Invoice;
import com.applicationpro.entity.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    List<InvoiceProduct> findAllByInvoice(Invoice invoice);

    List<InvoiceProduct> findAllByEnabledIsTrue();

    @Query(value = "SELECT new com.applicationpro.dto.common.SoldProductDto(invoiceProduct.product.id , invoiceProduct.product.name , sum (invoiceProduct.quantity) , sum(invoiceProduct.profit) ) FROM InvoiceProduct invoiceProduct WHERE invoiceProduct.invoice.invoiceType = 'SALE' GROUP BY invoiceProduct.product.id,invoiceProduct.product.name")
    List<SoldProductDto> getSoldInvoiceProductList();

}