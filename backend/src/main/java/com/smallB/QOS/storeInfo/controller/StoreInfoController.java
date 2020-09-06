package com.smallB.QOS.storeInfo.controller;

import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import com.smallB.QOS.storeInfo.service.StoreInfoService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.storeInfo.dao")
public class StoreInfoController {

    @Autowired
    private StoreInfoService storeInfoService;

    @PostMapping("/store")
    public ResponseEntity<String> createStore(@RequestBody StoreInfoDto resource) throws Exception {
        System.out.println(resource.getAddress1());
        String message = storeInfoService.createStore(resource);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/store/{store_id}")
    public ResponseEntity<StoreInfoDto> getStore(@PathVariable("store_id") final String store_id) throws Exception {
        StoreInfoDto storeInfoDto = storeInfoService.getOneStoreInfo(store_id);
        return new ResponseEntity<>(storeInfoDto, HttpStatus.OK);
    }

}
