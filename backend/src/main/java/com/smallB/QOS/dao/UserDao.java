package com.smallB.QOS.dao;

import com.smallB.QOS.domain.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    void addUser (UserDto user) throws Exception;
    UserDto findUserById (String user_id) throws Exception;
}
