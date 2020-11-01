package com.smallB.QOS.order.error;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class OrderUpdateFailException extends InvalidValueException {
    public OrderUpdateFailException(String order_id) {super(order_id, ErrorCode.ORDER_UPDATE_FAIL);}
}