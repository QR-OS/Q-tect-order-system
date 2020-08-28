package com.smallB.QOS.service;

import com.smallB.QOS.dao.UserDao;
import com.smallB.QOS.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public boolean register(UserDto userDto) throws Exception {
        userDao.register(userDto);
        return true;
    }

    @Override
    public UserDto getUserById(String user_id) throws Exception{
        return userDao.getUserById(user_id);
    }
}
