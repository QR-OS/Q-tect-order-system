package com.smallB.QOS.productCategory.service;

import com.smallB.QOS.product.domain.ProductDto;
import com.smallB.QOS.productCategory.dao.ProductCategoryDao;
import com.smallB.QOS.productCategory.domain.ProductCategoryDto;
import com.smallB.QOS.productCategory.error.ProductCategoryDuplicatedException;
import com.smallB.QOS.productCategory.error.ProductCategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategoryDto> getProducts(String store_id) throws Exception{
        List<ProductCategoryDto> productCategoryDto = productCategoryDao.findProductCategoryByStoreId(store_id);

        if(isNull(productCategoryDto)){
            throw new ProductCategoryNotFoundException();
        }
        return productCategoryDto;
    }

    @Override
    public Boolean CreateProduct(ProductCategoryDto productCategoryDto) throws Exception{
        ProductCategoryDto exist = productCategoryDao.findProductCategory(productCategoryDto);

        if(isNull(exist)){
            productCategoryDao.CreateProductCategory(productCategoryDto);
            return true;
        }else{
            throw new ProductCategoryDuplicatedException();
        }
    }
}
