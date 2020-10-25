package com.smallB.QOS.order.service;

import com.smallB.QOS.order.domain.OrderDto;
import org.springframework.stereotype.Service;


@Service
public interface OrderService {
    OrderDto getOrder(String order_id, String store_id) throws Exception;

    OrderDto createOrder(OrderDto orderDto) throws Exception;
}
