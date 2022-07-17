package com.applicationpro.service.impl;

import com.applicationpro.dto.ProductDTO;
import com.applicationpro.dto.StockDTO;
import com.applicationpro.repository.StockRepository;
import com.applicationpro.service.StockService;
import com.applicationpro.util.MapperUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final MapperUtil mapperUtil;

    public StockServiceImpl(@Lazy StockRepository stockRepository, MapperUtil mapperUtil) {
        this.stockRepository = stockRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<StockDTO> listAllStocks() {
        return stockRepository.findAll().stream()
                .map(stock -> mapperUtil.convert(stock, new StockDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public StockDTO findByID(Long id) {
        return mapperUtil.convert(stockRepository.findById(id).get(), new StockDTO());
    }

    @Override
    public Double getBaseProductPrice(Long id) {
        return stockRepository.getProductPrice(id);
    }

    @Override
    public LocalDate getInvoiceDate(Long id) {
        return stockRepository.getIdate(id);
    }

//    @Override
//    public StockDTO findStockByProductId(Long id) {
//        return stockRepository.findStockByProductId(id);
//    }
}
