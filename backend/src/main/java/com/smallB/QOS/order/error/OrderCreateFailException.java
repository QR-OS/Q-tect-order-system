package com.smallB.QOS.order.error;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class OrderCreateFailException extends InvalidValueException {
    public OrderCreateFailException(){super(ErrorCode.ORDER_CREATION_FAIL);}
}
