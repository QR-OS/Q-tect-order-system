package com.smallB.QOS.service;

import com.smallB.QOS.domain.UserDto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface UserService {
    boolean register(UserDto user) throws Exception;

}
