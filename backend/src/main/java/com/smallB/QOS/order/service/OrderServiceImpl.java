package com.smallB.QOS.order.service;

import com.smallB.QOS.order.dao.OrderDao;
import com.smallB.QOS.order.domain.OrderDto;
import com.smallB.QOS.order.domain.OrderStateUpdateDto;
import com.smallB.QOS.order.error.OrderCreateFailException;
import com.smallB.QOS.order.error.OrderNotFoundException;
import com.smallB.QOS.order.error.OrderUpdateFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public List<OrderDto> getOrders(String store_id) throws Exception{
        List<OrderDto> orderDtos = orderDao.findOrders(store_id);
        if(orderDtos.size()==0){
            throw new OrderNotFoundException(store_id);
        }
        return orderDtos;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception{
        orderDto.setOrder_time(new Date());

        orderDao.createOrder(orderDto);

        if(isNull(orderDto.getOrder_id())){
            throw new OrderCreateFailException();
        }
        return orderDto;
    }

    @Override
    public OrderDto updateOrder(String order_id, String store_id, OrderStateUpdateDto orderStateUpdateDto) throws Exception{
        //find orderInfo
        OrderDto orderdto = orderDao.findOrder(order_id,store_id);

        if(isNull(orderdto)){
            throw new OrderNotFoundException(store_id);
        }

        orderStateUpdateDto.setOrder_id(Integer.parseInt(order_id));
        orderStateUpdateDto.setStore_id(store_id);

        if(orderStateUpdateDto.getOrder_state()!=null){
            orderdto.setOrder_state(orderStateUpdateDto.getOrder_state());
        }
        if(isNull(orderStateUpdateDto.getOrder_type())){
            orderdto.setOrder_type(orderStateUpdateDto.getOrder_type());
        }

        int result = orderDao.updateOrder(orderStateUpdateDto);

        if(result==0){
            throw new OrderUpdateFailException(order_id);
        }

        return orderdto;
    }
}
