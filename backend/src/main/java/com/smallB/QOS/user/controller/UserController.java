package com.smallB.QOS.user.controller;

import com.smallB.QOS.user.service.UserService;
import com.smallB.QOS.user.domain.UserDto;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.user.dao")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserDto register(@RequestBody @Valid UserDto resource) throws Exception{
        userService.register(resource);
        return resource;
    }

    @GetMapping("/user/{user_id}")
    public UserDto getUser(@PathVariable @Valid String user_id) throws Exception{
        return userService.getUserById(user_id);
    }

    @PatchMapping("/user")
    public UserDto updateUser(@RequestBody @Valid UserDto resource) throws Exception{
        userService.updateUser(resource);
        return resource;
    }

    @GetMapping("/user/store_id/{store_id}")
    public UserDto getUserByStoreId(@PathVariable @Valid String store_id) throws Exception{
        return userService.getUserByStoreId(store_id);
    }
}
