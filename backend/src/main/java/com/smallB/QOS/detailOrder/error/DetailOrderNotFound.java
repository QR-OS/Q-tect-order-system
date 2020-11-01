package com.smallB.QOS.detailOrder.error;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class DetailOrderNotFound  extends InvalidValueException {
    public DetailOrderNotFound(String order_id) {super(order_id, ErrorCode.DETAILORDER_NOT_FOUND);}
}
