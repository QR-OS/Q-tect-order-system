package com.smallB.QOS.storeInfo.service;
import com.smallB.QOS.storeInfo.domain.StoreIdListDto;
import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import com.smallB.QOS.storeInfo.domain.StoreTypeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface StoreInfoService {
    String createStore(StoreInfoDto resource) throws Exception;

    StoreInfoDto getOneStoreInfo(String user_id) throws Exception;

    String updateStoreInfo(String user_id, StoreInfoDto resource) throws Exception;

    ArrayList<StoreIdListDto> getStoresByCategory(String category) throws Exception;

    ArrayList<StoreInfoDto> getStoresById(ArrayList<StoreIdListDto> storeIds) throws Exception;

    ArrayList<StoreInfoDto> getStores() throws Exception;

    String createStoreCategory(String store_id, ArrayList<StoreTypeDto> storeType) throws Exception;

    String updateStoreCategory(String store_id, ArrayList<StoreTypeDto> storeType) throws Exception;

    ArrayList<StoreTypeDto> getStoreCategory(String user_id) throws Exception;
}
