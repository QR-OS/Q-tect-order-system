package com.smallB.QOS.storeInfo.service;

import com.smallB.QOS.storeInfo.dao.StoreInfoDao;
import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class StoreInfoServiceImpl implements StoreInfoService{

    @Autowired
    private StoreInfoDao storeInfoDao;

    @Override
    public String createStore (StoreInfoDto resource) throws Exception {
        System.out.println(resource.getStore_num());
        StoreInfoDto store = storeInfoDao.findStoreByNum(resource.getStore_num());
        System.out.println(store);
        if(nonNull(store)) return "이미 등록된 사업자번호입니다. 사업자번호를 다시 확인해주세요!";

        boolean storeFlag = storeInfoDao.addStore(resource);
        if(storeFlag) return "매장등록이 완료되었습니다!";
        else return "매장등록에 실패하였습니다!";
    }


}
