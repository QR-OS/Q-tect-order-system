package com.smallB.QOS.order.dao;

import com.smallB.QOS.order.domain.OrderDto;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
    OrderDto findOrder(String order_id, String store_id) throws Exception;

    boolean createOrder(OrderDto orderDto) throws Exception;

}
