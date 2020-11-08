package com.smallB.QOS.order.dao;

import com.smallB.QOS.order.domain.OrderDto;
import com.smallB.QOS.order.domain.OrderStateUpdateDto;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderDao {
    OrderDto findOrder(String order_id, String store_id) throws Exception;
    List<OrderDto> findOrders(String store_id) throws Exception;
    void createOrder(OrderDto orderDto) throws Exception;
    int updateOrder(OrderStateUpdateDto orderStateUpdateDto) throws Exception;
}
