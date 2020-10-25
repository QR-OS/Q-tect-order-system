package com.smallB.QOS.productCategory.dao;

import com.smallB.QOS.productCategory.domain.ProductCategoryDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryDao {
    void CreateProductCategory(ProductCategoryDto productCategoryDto) throws Exception;

    List<ProductCategoryDto> findProductCategoryByStoreId(String store_id) throws Exception;

    ProductCategoryDto findProductCategory(ProductCategoryDto productCategoryDto) throws Exception;
}
