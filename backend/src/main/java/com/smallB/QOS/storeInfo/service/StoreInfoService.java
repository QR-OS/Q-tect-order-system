package com.smallB.QOS.storeInfo.service;

import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface StoreInfoService {
    String createStore(StoreInfoDto resource) throws Exception;

    StoreInfoDto getOneStoreInfo(String user_id) throws Exception;

    String updateStoreInfo(String user_id, StoreInfoDto resource) throws Exception;
}
