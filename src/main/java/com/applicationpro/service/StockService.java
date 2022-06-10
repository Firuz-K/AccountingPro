package com.applicationpro.service;

import com.applicationpro.dto.ProductDTO;
import com.applicationpro.dto.StockDTO;

import java.util.List;

public interface StockService {

    List<StockDTO> listAllStocks();
    StockDTO findByID(Long id);
//    StockDTO findStockByProductId(Long id);

}
