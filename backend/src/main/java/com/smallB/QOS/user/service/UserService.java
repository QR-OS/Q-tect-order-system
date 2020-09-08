package com.smallB.QOS.user.service;

import com.smallB.QOS.user.domain.SessionRequestDto;
import com.smallB.QOS.user.domain.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean register(UserDto user) throws Exception;
    UserDto getUserById(String user_id) throws Exception;
    UserDto authenticate(SessionRequestDto sessionRequestDto) throws Exception;
    Boolean updateUser(UserDto user) throws Exception;
}
