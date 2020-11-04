package com.smallB.QOS.storeInfo.controller;

import com.smallB.QOS.storeInfo.domain.StoreIdListDto;
import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import com.smallB.QOS.storeInfo.error.Exception.CategoryNotExistedException;
import com.smallB.QOS.storeInfo.error.Exception.UnauthorizedCategoryException;
import com.smallB.QOS.storeInfo.service.StoreInfoService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PatchMapping("/store/{user_id}")
    public String editStore(@Valid @PathVariable("user_id") final String user_id,
                          @RequestBody StoreInfoDto resource) throws Exception {
        return storeInfoService.updateStoreInfo(user_id, resource);
    }

    @GetMapping("/store")
    public ArrayList<StoreInfoDto> getStoresByCategory(@Valid @RequestParam(value="category", required=false) String category) throws Exception {
        ArrayList<StoreIdListDto> storeIds = new ArrayList<>();
        if(category == "") throw new UnauthorizedCategoryException();
        if (category != null) {
            storeIds = storeInfoService.getStoresByCategory(category);
            return storeInfoService.getStoresById(storeIds);
        }
        else return storeInfoService.getStores();
    }
}
