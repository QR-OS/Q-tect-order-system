package com.smallB.QOS.storeInfo.service;

import com.smallB.QOS.global.util.CreateRandomStrUtil;
import com.smallB.QOS.storeInfo.dao.StoreInfoDao;
import com.smallB.QOS.storeInfo.domain.StoreInfoDto;
import com.smallB.QOS.storeInfo.error.Exception.StoreUpdateFailedException;
import com.smallB.QOS.storeInfo.error.Exception.StoreNotExistedException;
import com.smallB.QOS.storeInfo.error.Exception.UnauthorizedUserException;
import com.smallB.QOS.user.domain.UserDto;
import com.smallB.QOS.user.error.Exception.UserNotExistedException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Map;
import java.util.Random;

import static java.util.Objects.nonNull;

@Service
public class StoreInfoServiceImpl implements StoreInfoService{

    @Autowired
    private StoreInfoDao storeInfoDao;

    //반환값으로 store_id를 보내주세요
    @Override
    public String createStore (StoreInfoDto resource) throws Exception {

        StoreInfoDto store = storeInfoDao.findStoreByNum(resource.getStore_num());
        if(nonNull(store)) return "이미 등록된 사업자번호입니다. 사업자번호를 다시 확인해주세요!";

        //store_id 랜덤 문자열 생성
        CreateRandomStrUtil createRandomStrUtil = new CreateRandomStrUtil();
        String randomStr = createRandomStrUtil.CreateRandomStr(25);

        resource.setStore_id(randomStr);


        boolean storeFlag = storeInfoDao.addStore(resource);
        if(storeFlag) return "매장등록이 완료되었습니다!";
        else return "매장등록에 실패하였습니다!";
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

}
