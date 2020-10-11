package com.smallB.QOS.productCategory.service;

import com.smallB.QOS.productCategory.domain.ProductCategoryDto;

import java.util.List;


public interface ProductCategoryService {
    List<ProductCategoryDto> getProducts(String store_id) throws Exception;
    Boolean CreateProduct(ProductCategoryDto productCategoryDto) throws Exception;
}
