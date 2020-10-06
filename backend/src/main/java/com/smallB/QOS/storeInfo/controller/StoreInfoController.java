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
    public String createStore(@RequestBody StoreInfoDto resource) throws Exception {
        return storeInfoService.createStore(resource);
    }

    @GetMapping("/store/{user_id}")
    public StoreInfoDto getStore(@PathVariable("user_id") String user_id) throws Exception {
        return storeInfoService.getOneStoreInfo(user_id);
    }

    @PatchMapping("/store/{user_id}")
    public String editStore(@PathVariable("user_id") final String user_id,
                          @RequestBody StoreInfoDto resource) throws Exception {
        return storeInfoService.updateStoreInfo(user_id, resource);
    }

}
