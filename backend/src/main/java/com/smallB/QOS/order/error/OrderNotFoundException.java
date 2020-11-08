package com.smallB.QOS.order.error;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class OrderNotFoundException  extends InvalidValueException {
    public OrderNotFoundException(String order_id) {super(order_id, ErrorCode.ORDER_NOT_FOUND);}
}
