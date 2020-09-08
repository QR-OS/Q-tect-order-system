package com.smallB.QOS.user.dao;

import com.smallB.QOS.user.domain.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    void addUser (UserDto user) throws Exception;
    UserDto findUserById (String user_id) throws Exception;
    int updateUser(UserDto user) throws  Exception;
}
