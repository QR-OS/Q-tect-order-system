package com.smallB.QOS.product.error;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class ProductNotFoundException extends InvalidValueException {
    public ProductNotFoundException(String product_id){
        super(product_id, ErrorCode.PRODUCT_NOT_FOUND);
    }

}