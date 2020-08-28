package com.smallB.QOS.service;

import com.smallB.QOS.dao.UserDao;
import com.smallB.QOS.domain.UserDto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean register(UserDto user) throws Exception;
    public UserDto getUserById(String user_id) throws Exception;
}
