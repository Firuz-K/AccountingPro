package com.applicationpro.repository;

import com.applicationpro.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByEnabledIsTrue();

    @Query(value = "select ip.tax from invoice_product ip join products p on " +
            "ip.product_id = p.id where p.id = ?1",nativeQuery = true)
    Double getTaxFromInvoiceProduct(@Param("id") Long id);
}
