package com.smallB.QOS.storeInfo.dao;

import com.smallB.QOS.storeInfo.domain.StoreCategoryDto;
import com.smallB.QOS.storeInfo.domain.StoreIdListDto;
import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import com.smallB.QOS.storeInfo.domain.StoreTypeDto;
import com.smallB.QOS.user.domain.UserDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
    //매장정보 업데이트
    Integer updateStoreInfo(StoreInfoDto storeInfo) throws Exception;

    //카테고리별로 매장 들고오기
    ArrayList<StoreIdListDto> findStoreByCategory(String category) throws Exception;

    ArrayList<StoreInfoDto> findStores() throws Exception;

    ArrayList<StoreTypeDto> findCategoryById(String store_id) throws Exception;
    //해당 카테고리가 매장에 이미 등록되어있는지 확인
    StoreCategoryDto findCategoryByStore(String store_id, String store_type) throws Exception;

    boolean addStoreCategory(String store_id, String store_type) throws Exception;

    boolean deleteStoreCategory(String store_id) throws Exception;
}
