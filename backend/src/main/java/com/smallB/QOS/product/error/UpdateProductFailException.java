package com.smallB.QOS.product.error;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class UpdateProductFailException extends InvalidValueException {
    public UpdateProductFailException(String product_id){
        super(product_id, ErrorCode.PRODUCT_UPDATE_FAILED);
    }
}
