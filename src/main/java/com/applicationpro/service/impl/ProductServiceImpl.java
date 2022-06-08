package com.applicationpro.service.impl;

import com.applicationpro.dto.ProductDTO;
import com.applicationpro.entity.Product;
import com.applicationpro.enums.ProductStatus;
import com.applicationpro.repository.ProductRepository;
import com.applicationpro.service.ProductService;
import com.applicationpro.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final MapperUtil mapperUtil;
    private final ProductRepository productRepository;

    public ProductServiceImpl(MapperUtil mapperUtil, ProductRepository productRepository) {
        this.mapperUtil = mapperUtil;
        this.productRepository = productRepository;
    }

    @Override
    public void create(ProductDTO dto) {

        dto.setProductStatus(ProductStatus.ACTIVE);
        Product product = mapperUtil.convert(dto,new Product());
        productRepository.save(product);

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
    public List<ProductDTO> listAllProducts() {
        return productRepository.findAll().stream().map(
                product -> mapperUtil.convert(product,new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long productId) {
        return null;
    }
}
