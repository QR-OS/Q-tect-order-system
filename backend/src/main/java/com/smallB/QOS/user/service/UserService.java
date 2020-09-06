package com.smallB.QOS.user.service;

import com.smallB.QOS.user.domain.SessionRequestDto;
import com.smallB.QOS.user.domain.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean register(UserDto user) throws Exception;
    public UserDto getUserById(String user_id) throws Exception;
    public UserDto authenticate(SessionRequestDto sessionRequestDto) throws Exception;
}
