package com.applicationpro.converter;

import com.applicationpro.dto.StockDTO;
import com.applicationpro.service.StockService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;

public class StockDtoConverter implements Converter<String, StockDTO> {

    StockService stockService;

    public StockDtoConverter(@Lazy StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public StockDTO convert(String source) {
        if(source == null || source.equals("")){
            return null;
        }
        return stockService.findByID(Long.parseLong(source));
    }
}
