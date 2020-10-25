package com.smallB.QOS.order.service;

import com.smallB.QOS.order.domain.OrderDto;

public interface OrderService {
    OrderDto getOrder(String order_id, String store_id) throws Exception;
}
