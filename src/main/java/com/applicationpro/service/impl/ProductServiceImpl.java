package com.applicationpro.service.impl;

import com.applicationpro.dto.ProductDTO;
import com.applicationpro.service.ProductService;
import com.applicationpro.util.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final MapperUtil mapperUtil;

    public ProductServiceImpl(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }

    @Override
    public void create(ProductDTO dto) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void edit(ProductDTO dto) {

    }

    @Override
    public void save(ProductDTO dto) {

    }

    @Override
    public void delete(Long is) {

    }

    @Override
    public ProductDTO findById(Long productId) {
        return null;
    }
}
