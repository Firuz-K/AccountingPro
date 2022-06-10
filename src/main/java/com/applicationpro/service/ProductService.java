package com.applicationpro.service;

import com.applicationpro.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void create(ProductDTO dto);
    void cancel();
    void edit(ProductDTO dto);
    void save(ProductDTO dto);
    void delete(Long is);
    List<ProductDTO> listAllProducts();
    ProductDTO findById(Long productId);
}
