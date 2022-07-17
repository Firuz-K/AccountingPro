package com.applicationpro.repository;

import com.applicationpro.entity.Product;
import com.applicationpro.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByProduct(Product product);

    @Query("SELECT s FROM Stock s WHERE s.invoiceType='PURCHASE' AND s.remainingQuantity>0 AND s.product.id=:productId ORDER BY s.invoiceDate")
    List<Stock> getPreviousPurchaseStockDataByProductId(@Param("productId") Long productId);

    @Query(value = "SELECT s.price from stock_details s join products p on " +
            "s.product_id = p.id where p.id = ?1" ,nativeQuery = true)
    Double getProductPrice(@Param("id") Long id);


    @Query(value = "select sd.invoice_date from stock_details sd join products p on " +
            "sd.id = p.id where sd.product_id = ?1",nativeQuery = true)
    LocalDate getIdate(@Param("product_id") Long id);

}
