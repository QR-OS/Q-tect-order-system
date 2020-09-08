package com.smallB.QOS.storeInfo.dao;

import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreInfoDao {
    //store 생성
    boolean addStore(StoreInfoDto storeInfoDto) throws Exception;
    //store 사업자번호 받아오기
    StoreInfoDto findStoreByNum(String store_num) throws Exception;

    StoreInfoDto findStoreById(String store_id) throws Exception;
}
