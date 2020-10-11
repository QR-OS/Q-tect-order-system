package com.smallB.QOS.storeInfo.service;

import com.google.gson.JsonObject;
import com.smallB.QOS.global.util.CreateRandomStrUtil;
import com.smallB.QOS.storeInfo.dao.StoreInfoDao;
import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import com.smallB.QOS.storeInfo.error.Exception.*;
import com.smallB.QOS.user.domain.UserDto;
import com.smallB.QOS.user.error.Exception.UserNotExistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import static java.util.Objects.nonNull;

@Service
public class StoreInfoServiceImpl implements StoreInfoService{

    @Autowired
    private StoreInfoDao storeInfoDao;

    //반환값으로 store_id를 보내주세요
    @Override
    public String createStore (StoreInfoDto resource) throws Exception {

        StoreInfoDto store = storeInfoDao.findStoreByNum(resource.getStore_num());
        if(nonNull(store)) { throw new StoreAlreadyExistException(); }

        //store_id 랜덤 문자열 생성 및 중복 체크
        boolean flag = true;
        CreateRandomStrUtil createRandomStrUtil = new CreateRandomStrUtil();
        while(flag) {
            String randomStr = createRandomStrUtil.CreateRandomStr(25);
            StoreInfoDto storeResult = storeInfoDao.findStoreById(randomStr);
            if (storeResult == null) {
                flag = false;
                resource.setStore_id(randomStr);
            }
        }

        boolean storeFlag = storeInfoDao.addStore(resource);
        if(storeFlag) return resource.getStore_id();
        else { throw new StoreRegisterFailedException(); }
    }

    @Override
    public StoreInfoDto getOneStoreInfo(@Valid String user_id) throws Exception {
        UserDto userDto = storeInfoDao.findStoreByUserId(user_id);

        if(userDto == null) { throw new UserNotExistedException(user_id); }
        if(userDto.getStatus() == 1) { throw new UnauthorizedUserException(user_id); }
        if(userDto.getStore_id() == null) { throw new StoreNotExistedException(); }

        StoreInfoDto storeResult = storeInfoDao.findStoreById(userDto.getStore_id());
        if(storeResult == null) {
            throw new StoreNotExistedException();   //db에 store가 안들어가있어서 터짐
        }
        return storeResult;
    }

    @Override
    public String updateStoreInfo(String user_id, StoreInfoDto storeInfo) throws Exception {
        UserDto userDto = storeInfoDao.findStoreByUserId(user_id);

        System.out.println(1);
        if(userDto == null) { throw new UserNotExistedException(user_id); }
        if(userDto.getStatus() == 1) { throw new UnauthorizedUserException(user_id); }
        if(userDto.getStore_id() == null) { throw new StoreNotExistedException(); }

        storeInfo.setStore_id(userDto.getStore_id());

        Integer result = storeInfoDao.updateStoreInfo(storeInfo);
        System.out.println(result);

        if(result == 0) {
            throw new StoreUpdateFailedException();
        }

        JsonObject obj = new JsonObject();
        obj.addProperty("message", "Store update success!");
        return obj.toString();
    }


}
