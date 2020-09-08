package com.smallB.QOS.storeInfo.service;

import com.smallB.QOS.storeInfo.dao.StoreInfoDao;
import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.util.Objects.nonNull;

@Service
public class StoreInfoServiceImpl implements StoreInfoService{

    @Autowired
    private StoreInfoDao storeInfoDao;

    @Override
    public String createStore (StoreInfoDto resource) throws Exception {

        StoreInfoDto store = storeInfoDao.findStoreByNum(resource.getStore_num());
        if(nonNull(store)) return "이미 등록된 사업자번호입니다. 사업자번호를 다시 확인해주세요!";

        //store_id 랜덤 문자열 생성
        String randomStr = "";
        Random random = new Random();
        for(int i = 0; i < 25; i++) {
            int point = random.nextInt(3);
            if(point == 0){
                randomStr += (char)((random.nextInt(26)) + 65);
            }
            else if(point == 1) {
                randomStr += (char)((random.nextInt(26)) + 97);
            }
            else {
                randomStr += (char)((random.nextInt(10)) + 48);
            }
        }

        resource.setStore_id(randomStr);

        boolean storeFlag = storeInfoDao.addStore(resource);
        if(storeFlag) return "매장등록이 완료되었습니다!";
        else return "매장등록에 실패하였습니다!";
    }

    @Override
    public StoreInfoDto getOneStoreInfo(String store_id) throws Exception {
        StoreInfoDto result = storeInfoDao.findStoreById(store_id);
        if(result == null) {

        }
        return result;
    }

}
