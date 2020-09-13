package com.smallB.QOS.storeInfo.dao;

import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import com.smallB.QOS.user.domain.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreInfoDao {
    //store 생성
    boolean addStore(StoreInfoDto storeInfoDto) throws Exception;
    //store 사업자번호 받아오기
    StoreInfoDto findStoreByNum(String store_num) throws Exception;

    //유저 아이디로 매장 아이디, 유저 status 가져오기
    UserDto findStoreByUserId(String user_id) throws Exception;
    //매장아이디로 매장 정보 들고오기
    StoreInfoDto findStoreById(String store_id) throws Exception;
}
