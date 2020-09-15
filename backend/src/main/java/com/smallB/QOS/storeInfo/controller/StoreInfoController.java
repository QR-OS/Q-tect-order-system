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

    @GetMapping("/store/{user_id}")
    public StoreInfoDto getStore(@PathVariable("user_id") String user_id) throws Exception {
        StoreInfoDto storeInfoDto = storeInfoService.getOneStoreInfo(user_id);
        return storeInfoDto;
    }

    @PatchMapping("/store/{user_id}")
    public String editStore(@PathVariable("user_id") final String user_id,
                          @RequestBody StoreInfoDto resource) throws Exception {
        String result = storeInfoService.updateStoreInfo(user_id, resource);
        return result;
    }

}
