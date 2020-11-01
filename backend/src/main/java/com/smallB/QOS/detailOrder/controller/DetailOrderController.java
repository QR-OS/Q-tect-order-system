package com.smallB.QOS.detailOrder.controller;

import com.smallB.QOS.detailOrder.service.DetailOrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
