package com.smallB.QOS.controller;

import com.smallB.QOS.dao.UserDAO;
import com.smallB.QOS.dto.UserDTO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.dao")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/user")
    public UserDTO register(@RequestBody UserDTO resource) throws Exception{
        userDAO.register(resource);
        return resource;
    }


}
