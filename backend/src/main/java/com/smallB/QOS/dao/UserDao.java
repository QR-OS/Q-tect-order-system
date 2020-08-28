package com.smallB.QOS.dao;

import com.smallB.QOS.domain.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    void register (UserDto user) throws Exception;
    UserDto getUserById (String user_id);
}
