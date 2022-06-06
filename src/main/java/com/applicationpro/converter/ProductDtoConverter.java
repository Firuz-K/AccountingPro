package com.applicationpro.converter;

import com.applicationpro.dto.ProductDTO;
import com.applicationpro.service.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;

public class ProductDtoConverter implements Converter<String, ProductDTO> {
    ProductService productService;

    public ProductDtoConverter(@Lazy  ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductDTO convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }

        return productService.findById(Long.parseLong(source));
    }
}
