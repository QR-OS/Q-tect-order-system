package com.smallB.QOS.order.service;

import com.smallB.QOS.detailOrder.dao.DetailOrderDao;
import com.smallB.QOS.order.dao.OrderDao;
import com.smallB.QOS.order.domain.OrderDto;
import com.smallB.QOS.order.domain.OrderHistoryDto;
import com.smallB.QOS.order.domain.OrderStateUpdateDto;
import com.smallB.QOS.order.error.OrderCreateFailException;
import com.smallB.QOS.order.error.OrderNotFoundException;
import com.smallB.QOS.order.error.OrderUpdateFailException;
import com.smallB.QOS.storeInfo.dao.StoreInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StoreInfoDao storeInfoDao;

    @Autowired
    private DetailOrderDao detailOrderDao;

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

    @Override
    public List<OrderHistoryDto> getOrdersByUserId(String user_id) throws Exception{
        System.out.println(user_id);
        List<OrderDto> orderDtos = orderDao.findOrdersByUserId(user_id);
        if(orderDtos.size()==0){
            throw new OrderNotFoundException(user_id);
        }
        List<OrderHistoryDto> orderHistoryDtos = new ArrayList<>();

        for(OrderDto order: orderDtos){
            OrderHistoryDto tmp= new OrderHistoryDto();
            if(order.getBook_time()!=null) tmp.setBook_time(order.getBook_time());
            tmp.setOrder_type(order.getOrder_type());
            tmp.setOrder_time(order.getOrder_time());
            tmp.setOrder_id(order.getOrder_id());
            tmp.setTotal_price(order.getTotal_price());
            tmp.setPay_type(order.getPay_type());
            tmp.setOrder_state(order.getOrder_state());
            tmp.setStore_id(order.getStore_id());
            tmp.setUser_id(order.getUser_id());
            System.out.println(order.getStore_id());
            tmp.setStore_name(storeInfoDao.findStoreByStoreId(order.getStore_id()).getStore_name());
            System.out.println(order.getStore_id());
            System.out.println(order.getUser_id());
            System.out.println(order.getOrder_id());
            tmp.setCeo_product_name(detailOrderDao.findOneDetailOrder(tmp.getOrder_id(),tmp.getUser_id()).getProduct_name());
            tmp.setDetail_order_count(detailOrderDao.findDetailOrder(tmp.getOrder_id(),tmp.getUser_id()).size());

            orderHistoryDtos.add(tmp);
        }
        return orderHistoryDtos;
    }
}

