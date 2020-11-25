package com.smallB.QOS.product.dao;

import com.smallB.QOS.product.domain.ProductDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    ProductDto findProductByProductIdAndStoreId(String product_id, String store_id) throws  Exception;

    List<ProductDto> findProductByStoreId(String store_id) throws Exception;

    void createProduct(ProductDto productDto) throws Exception;

    ProductDto productIdDuplicationCheck(String product_id) throws Exception;

    boolean updateProductByProductIdAndStoreId(ProductDto productDto) throws Exception;

    List<String> findProductCategory(String store_id) throws Exception;

    boolean deleteProductByProductIdAndStoreId(String product_id, String store_id) throws Exception;
}
