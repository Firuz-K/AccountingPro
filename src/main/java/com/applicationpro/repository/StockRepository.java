package com.applicationpro.repository;

import com.applicationpro.dto.StockDTO;
import com.applicationpro.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock,Long> {

    //will continue later
//
//    @Query(value = "Select * from stock_details s join products p on s.id=p.id where " +
//            "p.id = ?1"
//            ,nativeQuery = true)
//    StockDTO findStockByProductId(Long id);
}
