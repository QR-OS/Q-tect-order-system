package com.smallB.QOS.product.service;

import com.smallB.QOS.product.domain.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto getProduct(String product_id, String store_id) throws Exception;

    List<ProductDto> getProducts(String store_id) throws Exception;

    ProductDto createProduct(ProductDto productDto) throws Exception;

    boolean updateProduct(ProductDto productDto) throws  Exception;

    List<String> getCategory(String store_id) throws Exception;
}
