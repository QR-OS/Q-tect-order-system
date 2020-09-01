package com.smallB.QOS.user.service;

import com.smallB.QOS.user.domain.UserDto;
import com.smallB.QOS.user.error.UserNotExistedExceptiondWrongException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean register(UserDto user) throws Exception;
    public UserDto getUserById(String user_id) throws Exception;
    public UserDto authenticate(String user_id, String user_pw) throws Exception, UserNotExistedExceptiondWrongException;
}
