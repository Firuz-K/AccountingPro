package com.applicationpro.repository;

import com.applicationpro.entity.Product;
import com.applicationpro.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByProduct(Product product);

    @Query("SELECT s FROM Stock s WHERE s.invoiceType='PURCHASE' AND s.remainingQuantity>0 AND s.product.id=:productId ORDER BY s.invoiceDate")
    List<Stock> getPreviousPurchaseStockDataByProductId(@Param("productId") Long productId);

}
