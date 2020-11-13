package com.smallB.QOS.order.controller;

import com.smallB.QOS.order.domain.OrderDto;
import com.smallB.QOS.order.domain.OrderStateUpdateDto;
import com.smallB.QOS.order.service.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.Order.dao")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{order_id}/{store_id}")
    public ResponseEntity<?> getOrder(@PathVariable @Valid String order_id, @PathVariable @Valid String store_id) throws Exception{
        Object res = orderService.getOrder(order_id,store_id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/orders/{store_id}")
    public ResponseEntity<?> getOrders(@PathVariable @Valid String store_id) throws Exception{
        Object res = orderService.getOrders(store_id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/order/{user_id}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable @Valid String user_id) throws Exception{
        System.out.println("in");
        Object res = orderService.getOrdersByUserId(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderDto orderDto) throws Exception{
        Object res = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PatchMapping("/order/{order_id}/{store_id}")
    public ResponseEntity<?> updateOrder(@PathVariable @Valid String order_id, @PathVariable @Valid String store_id, @RequestBody @Valid OrderStateUpdateDto orderStateUpdateDto) throws Exception{
        Object res = orderService.updateOrder(order_id,store_id,orderStateUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}


