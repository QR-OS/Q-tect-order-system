package com.smallB.QOS.dao;

import com.smallB.QOS.dto.UserDTO;

public interface UserDAO {
    void register(UserDTO param) throws Exception;

}
