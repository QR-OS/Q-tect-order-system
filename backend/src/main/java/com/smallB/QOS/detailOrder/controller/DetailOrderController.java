package com.smallB.QOS.detailOrder.controller;

import com.smallB.QOS.detailOrder.domain.DetailOrderDto;
import com.smallB.QOS.detailOrder.service.DetailOrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.detailOrder.dao")
public class DetailOrderController {
    @Autowired
    private DetailOrderService detailOrderService;

    @GetMapping("/detailorder/{order_id}/{user_id}")
    public ResponseEntity<?> getDetailOrder(@PathVariable @Valid String order_id, @PathVariable @Valid String user_id) throws Exception{
        Object res = detailOrderService.getDetailOrder(order_id,user_id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/detailorder")
    public ResponseEntity<?> createDetailOrder(@RequestBody @Valid DetailOrderDto detailOrderDto) throws Exception{
        Object res = detailOrderService.createDetailOrder(detailOrderDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
