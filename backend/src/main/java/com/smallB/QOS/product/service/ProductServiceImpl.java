package com.smallB.QOS.product.service;

import com.smallB.QOS.product.dao.ProductDao;
import com.smallB.QOS.product.domain.ProductDto;
import com.smallB.QOS.product.error.ProductNotFoundException;
import com.smallB.QOS.product.error.UpdateProductFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static java.util.Objects.isNull;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public ProductDto getProduct(String product_id, String store_id) throws Exception{
        ProductDto productDto = productDao.findProductByProductIdAndStoreId(product_id,store_id);
        if(isNull(productDto)) {
            throw new ProductNotFoundException(product_id);
        }
        return productDto;
    }

    @Override
    public List<ProductDto> getProducts(String store_id) throws Exception {
        List<ProductDto> productDto = productDao.findProductByStoreId(store_id);
        if(isNull(productDto)) {
            //TODO EXCEPTION HANDLER
            throw new ProductNotFoundException("상품");
        }
        return productDto;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) throws Exception{
        ProductDto result = ProductDto.builder()
                .product_id("아이디")
                .store_id(productDto.getStore_id())
                .product_image(productDto.getProduct_image())
                .product_name(productDto.getProduct_name())
                .product_category(productDto.getProduct_category())
                .product_price(productDto.getProduct_price())
                .product_stock(productDto.getProduct_stock())
                .product_state(productDto.isProduct_state())
                .deleted(false)
                .build();

        boolean flag = true;
        while(flag) {
            //상품 ID 생성
            StringBuffer temp = new StringBuffer();
            Random rnd = new Random();
            for (int i = 0; i < 25; i++) {
                int rIndex = rnd.nextInt(3);
                switch (rIndex) {
                    case 0:
                        // a-z
                        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                        break;
                    case 1:
                        // A-Z
                        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                        break;
                    case 2:
                        // 0-9
                        temp.append((rnd.nextInt(10)));
                        break;
                }
            }

            //중복 검사
            ProductDto existed = productDao.productIdDuplicationCheck(temp.toString());

            if (isNull(existed)) {
                flag = false;

                result.setProduct_id(temp.toString());

                //DB insert
                productDao.createProduct(result);
            }
        }
        return result;
    }

    @Override
    public boolean updateProduct(ProductDto productDto) throws  Exception{
        ProductDto existed = productDao.findProductByProductIdAndStoreId(productDto.getProduct_id(),productDto.getStore_id());

        if(isNull(existed)){
            throw new ProductNotFoundException(productDto.getProduct_id());
        }

        boolean result = productDao.updateProductByProductIdAndStoreId(productDto);

        if(result == false) throw new UpdateProductFailException(productDto.getProduct_id());

        return true;
    }
}
