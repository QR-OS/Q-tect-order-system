package com.smallB.QOS.controller;

import com.smallB.QOS.service.UserService;
import com.smallB.QOS.domain.UserDto;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.dao")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserDto register(@RequestBody UserDto resource) throws Exception{
        userService.register(resource);
        return resource;
    }


}
