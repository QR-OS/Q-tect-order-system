package com.smallB.QOS.storeInfo.service;

import com.google.gson.JsonObject;
import com.smallB.QOS.global.util.CreateRandomStrUtil;
import com.smallB.QOS.storeInfo.dao.StoreInfoDao;
import com.smallB.QOS.storeInfo.domain.*;
import com.smallB.QOS.storeInfo.error.Exception.StoreCategoryNotExistedException;
import com.smallB.QOS.storeInfo.error.Exception.StoreUpdateFailedException;
import com.smallB.QOS.storeInfo.error.Exception.StoreNotExistedException;
import com.smallB.QOS.storeInfo.error.Exception.UnauthorizedUserException;
import com.smallB.QOS.storeInfo.error.Exception.*;
import com.smallB.QOS.user.domain.UserDto;
import com.smallB.QOS.user.error.Exception.UserNotExistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.StoreType;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import java.util.ArrayList;

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

    @Override
    public ArrayList<StoreIdListDto> getStoresByCategory(String category) throws Exception {
        ArrayList<StoreIdListDto> result = storeInfoDao.findStoreByCategory(category);
        return result;
    }

    @Override
    public ArrayList<StoreInfoDto> getStoresById(ArrayList<StoreIdListDto> storeIds) throws Exception {
        ArrayList<StoreInfoDto> result = new ArrayList<>();
        for(int i=0; i<storeIds.size(); i++) {
            String store_id = storeIds.get(i).getStore_id();
            StoreInfoDto store = storeInfoDao.findStoreById(store_id);
            result.add(store);
        }
        if(result.size() == 0){
            throw new StoreCategoryNotExistedException();
        }
        return result;
    }

    @Override
    public ArrayList<StoreInfoDto> getStores() throws Exception {
        ArrayList<StoreInfoDto> result = storeInfoDao.findStores();
        return result;
    }

    @Override
    public ArrayList<StoreTypeDto> getStoreCategory(String user_id) throws Exception {
        ArrayList<StoreTypeDto> storeTypeDto = new ArrayList<>();
        UserDto userDto = storeInfoDao.findStoreByUserId(user_id);

        if(userDto == null) { throw new UserNotExistedException(user_id); }
        if(userDto.getStatus() == 1) { throw new UnauthorizedUserException(user_id); }
        if(userDto.getStore_id() == null) { throw new StoreNotExistedException(); }

        storeTypeDto = storeInfoDao.findCategoryById(userDto.getStore_id());
        return storeTypeDto;
    }

    @Override
    public String createStoreCategory(String store_id, ArrayList<StoreTypeDto> storeTypeDto) throws Exception {
        if(storeTypeDto.size() == 0) throw new StoreCategoryWrongValueException();//category를 등록할게 없을 때
        StoreInfoDto storeResult = storeInfoDao.findStoreById(store_id);//해당 store_id로 된 매장이 등록이 되어있는지 확인
        if(storeResult == null) throw new StoreNotExistedException();//등록된 매장이 없을 때

        int allCreateCnt = 0;
        for(int i=0; i<storeTypeDto.size(); i++) {
            StoreTypeDto typeDto = storeTypeDto.get(i);
            String store_type = typeDto.getStore_type();
            if (store_type == null || store_type == "") throw new StoreCategoryWrongValueException();
        }
        for(int i=0; i<storeTypeDto.size(); i++) {
            StoreTypeDto typeDto = storeTypeDto.get(i);
            String store_type = typeDto.getStore_type();

            StoreCategoryDto storeCategoryDto = storeInfoDao.findCategoryByStore(store_id, store_type);
            if(storeCategoryDto != null) {
                allCreateCnt += 1;
                continue;
            }
            else {
                boolean storeCategoryFlag = storeInfoDao.addStoreCategory(store_id, store_type);
                if (storeCategoryFlag) allCreateCnt += 1;
            }
        }
        if(allCreateCnt == storeTypeDto.size()) {
            JsonObject obj = new JsonObject();
            obj.addProperty("message", "매장 카테고리 등록이 완료되었습니다!");
            return obj.toString();
        }//모든 category들이 다 등록되었을 때
        else throw new StoreCategoryCreateFailedException();//모든 category가 등록되지 않았을 때
    }

    @Override
    public String updateStoreCategory(String user_id, String store_id, ArrayList<StoreTypeDto> storeTypeDto) throws Exception {
        UserDto userDto = storeInfoDao.findStoreByUserId(user_id);
        System.out.println(userDto.getStore_id());
        System.out.println(store_id);
        if(!userDto.getStore_id().equals(store_id)) throw new UnauthorizedUserException(user_id);

        ArrayList<StoreTypeDto> storeTypeDtos = storeInfoDao.findCategoryById(store_id);
        if(storeTypeDtos.size() != 0) {
            Boolean deleteFlag = storeInfoDao.deleteStoreCategory(store_id);
            if (!deleteFlag) throw new StoreCategoryNotDeletedException();
        }

        if(storeTypeDto.size() == 0) throw new StoreCategoryWrongValueException();//category를 등록할게 없을 때
        int allCreateCnt = 0;
        for(int i=0; i<storeTypeDto.size(); i++) {
            StoreTypeDto typeDto = storeTypeDto.get(i);
            String store_type = typeDto.getStore_type();
            if (store_type == null || store_type == "") throw new StoreCategoryWrongValueException();
        }
        for(int i=0; i<storeTypeDto.size(); i++) {
            StoreTypeDto typeDto = storeTypeDto.get(i);
            String store_type = typeDto.getStore_type();

            StoreCategoryDto storeCategoryDto = storeInfoDao.findCategoryByStore(store_id, store_type);
            if(storeCategoryDto != null) {
                allCreateCnt += 1;
                continue;
            }
            else {
                boolean storeCategoryFlag = storeInfoDao.addStoreCategory(store_id, store_type);
                if (storeCategoryFlag) allCreateCnt += 1;
            }
        }
        if(allCreateCnt == storeTypeDto.size()) {
            JsonObject obj = new JsonObject();
            obj.addProperty("message", "매장 카테고리 수정이 완료되었습니다!");
            return obj.toString();
        }//모든 category들이 다 등록되었을 때
        else throw new StoreCategoryCreateFailedException();//모든 category가 등록되지 않았을 때
    }
}
