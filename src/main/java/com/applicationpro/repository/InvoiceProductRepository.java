package com.applicationpro.repository;

import com.applicationpro.dto.common.SoldProductDto;
import com.applicationpro.entity.Invoice;
import com.applicationpro.entity.InvoiceProduct;
import com.applicationpro.enums.InvoiceType;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    List<InvoiceProduct> findAllByInvoice(Invoice invoice);

    List<InvoiceProduct> findAllByEnabledIsTrue();

    @Query(value = "SELECT new com.applicationpro.dto.common.SoldProductDto(invoiceProduct.product.id , invoiceProduct.product.name , sum (invoiceProduct.quantity) , sum(invoiceProduct.profit) ) FROM InvoiceProduct invoiceProduct WHERE invoiceProduct.invoice.invoiceType = 'SALE' GROUP BY invoiceProduct.product.id,invoiceProduct.product.name")
    List<SoldProductDto> getSoldInvoiceProductList();

    @Query(value = "select i.invoice_type from invoice i join invoice_product ip on " +
            "i.id = ip.invoice_id where ip.product_id = ?1",nativeQuery = true)
    String getInvoiceTypeProduct( @Param("product_id") Long id);
}