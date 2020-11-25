package com.smallB.QOS.storeInfo.controller;

import com.smallB.QOS.storeInfo.domain.*;
import com.smallB.QOS.storeInfo.error.Exception.StoreCategoryParameterWrongValueException;
import com.smallB.QOS.storeInfo.service.StoreInfoService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.storeInfo.dao")
public class StoreInfoController {

    @Autowired
    private StoreInfoService storeInfoService;

    @PostMapping("/store")
    public String createStore(@Valid @RequestBody StoreInfoDto resource) throws Exception {
        return storeInfoService.createStore(resource);
    }

    @GetMapping("/store/{user_id}")
    public StoreInfoDto getStore(@Valid @PathVariable("user_id") String user_id) throws Exception {
        return storeInfoService.getOneStoreInfo(user_id);
    }

    @GetMapping("/store/info/{store_id}")
    public StoreInfoDto getStoreByStoreId(@PathVariable("store_id") String store_id) throws Exception{
        return storeInfoService.getOneStoreInfoByStoreId(store_id);
    }

    @PatchMapping("/store/{user_id}")
    public String editStore(@Valid @PathVariable("user_id") final String user_id,
                          @RequestBody StoreInfoDto resource) throws Exception {
        return storeInfoService.updateStoreInfo(user_id, resource);
    }

    @GetMapping("/store")
    public ArrayList<StoreInfoDto> getStoresByCategory(@RequestParam(value="category", required=false) String category) throws Exception {
        ArrayList<StoreIdListDto> storeIds = new ArrayList<>();
        if(category == "") throw new StoreCategoryParameterWrongValueException();
        if (category != null) {
            storeIds = storeInfoService.getStoresByCategory(category);
            return storeInfoService.getStoresById(storeIds);
        }
        else return storeInfoService.getStores();
    }

    @GetMapping("/store/category/{user_id}")
    public ArrayList<StoreTypeDto> getStoreCategory(@PathVariable("user_id") String user_id) throws Exception {
        return storeInfoService.getStoreCategory(user_id);
    }

    @PostMapping("/store/category")
    public String createStoreCategory(@RequestBody StoreCategoryArrayDto storeCategoryArrayDto) throws Exception {
        return storeInfoService.createStoreCategory(storeCategoryArrayDto.getStore_id(), storeCategoryArrayDto.getStoreType());
    }

    @PatchMapping("/store/category/{user_id}")
    public String editStoreCategory(@PathVariable("user_id") String user_id, @RequestBody StoreCategoryArrayDto storeCategoryArrayDto) throws Exception {
        return storeInfoService.updateStoreCategory(user_id, storeCategoryArrayDto.getStore_id(), storeCategoryArrayDto.getStoreType());
    }
}
