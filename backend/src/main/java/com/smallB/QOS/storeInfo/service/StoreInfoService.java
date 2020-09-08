package com.smallB.QOS.storeInfo.service;

import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface StoreInfoService {
    String createStore(StoreInfoDto resource) throws Exception;

    StoreInfoDto getOneStoreInfo(String store_id) throws Exception;
}
