package com.smallB.QOS.order.controller;

import com.smallB.QOS.order.domain.OrderDto;
import com.smallB.QOS.order.service.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.product.dao")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/:order_id/:store_id")
    public OrderDto getOrder(@PathVariable @Valid String order_id, @PathVariable @Valid String store_id) throws Exception{
        return orderService.getOrder(order_id,store_id);
    }
}