package com.applicationpro.service;

import com.applicationpro.dto.ProductDTO;
import com.applicationpro.enums.InvoiceType;

import java.util.List;

public interface ProductService {

    void create(ProductDTO dto);

    void cancel();

    void edit(ProductDTO dto);

    void save(ProductDTO dto);

    void delete(Long is);

    List<ProductDTO> listAllProducts();

    ProductDTO findById(Long productId);

    List<ProductDTO> listAllActiveProducts();

    List<ProductDTO> listProductStockDetails();

//    int getProductBasePrice(Long id);
//
//    InvoiceType retrieveInvoiceTypeOfProduct(Long id);




}
