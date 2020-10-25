package com.smallB.QOS.order.service;

import com.smallB.QOS.order.dao.OrderDao;
import com.smallB.QOS.order.domain.OrderDto;
import com.smallB.QOS.order.error.OrderCreateFailException;
import com.smallB.QOS.order.error.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Objects.isNull;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;

    @Override
    public OrderDto getOrder(String order_id, String store_id) throws Exception{
        OrderDto orderDto = orderDao.findOrder(order_id,store_id);
        if(isNull(orderDto)){
            throw new OrderNotFoundException(order_id);
        }
        return orderDto;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception{
        orderDto.setOrder_time(new Date());

        Boolean result = orderDao.createOrder(orderDto);
        if(result != true) {
            throw new OrderCreateFailException();
        }
        return orderDto;
    }
}
