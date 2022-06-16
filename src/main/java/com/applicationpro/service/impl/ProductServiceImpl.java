package com.applicationpro.service.impl;

import com.applicationpro.dto.ProductDTO;
import com.applicationpro.entity.Product;
import com.applicationpro.enums.ProductStatus;
import com.applicationpro.repository.ProductRepository;
import com.applicationpro.service.ProductService;
import com.applicationpro.service.StockService;
import com.applicationpro.util.MapperUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final MapperUtil mapperUtil;
    private final ProductRepository productRepository;
    private final StockService stockService;
    private final InvoiceProductServiceImpl invoiceProductService;


    public ProductServiceImpl(MapperUtil mapperUtil, ProductRepository productRepository, StockService stockService,  @Lazy InvoiceProductServiceImpl invoiceProductService) {
        this.mapperUtil = mapperUtil;
        this.productRepository = productRepository;
        this.stockService = stockService;
        this.invoiceProductService = invoiceProductService;
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
        return mapperUtil.convert(productRepository.getById(productId), new ProductDTO());
    }

    @Override
    public List<ProductDTO> listAllActiveProducts() {
        return productRepository.findAllByEnabledIsTrue().stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO()))
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductDTO> listProductStockDetails() {

        List<Product> list = productRepository.findAll();

        return list.stream().map(product -> {

            ProductDTO productDTO = mapperUtil.convert(product,new ProductDTO());

            productDTO.setBasePrice(stockService.getBaseProductPrice(product.getId()));
            productDTO.setInvoiceType(invoiceProductService.getInvoiceType(product.getId()));
            productDTO.setInvoiceDate(stockService.getInvoiceDate(product.getId()));

            return productDTO;

        }).collect(Collectors.toList());



    }

//    @Override
//    public int getProductBasePrice(Long id) {
//        return productRepository.getBasePrice(id);
//    }
//
//    @Override
//    public InvoiceType retrieveInvoiceTypeOfProduct(Long id) {
//
//        return productRepository.getInvoiceTypeOfProduct(id);
//    }

}
