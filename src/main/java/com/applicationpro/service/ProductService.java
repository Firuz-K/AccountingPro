package com.applicationpro.service;

import com.applicationpro.dto.ProductDTO;

public interface ProductService {

    void create(ProductDTO dto);
    void cancel();
    void edit(ProductDTO dto);
    void save(ProductDTO dto);
    void delete(Long is);

    ProductDTO findById(Long productId);
}
