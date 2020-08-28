package com.smallB.QOS.service;

import com.smallB.QOS.dao.UserDao;
import com.smallB.QOS.domain.UserDto;
import com.smallB.QOS.error.UserExistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public boolean register(UserDto userDto) throws Exception {
        Optional<UserDto> existed = Optional.ofNullable(userDao.findUserById(userDto.getUser_id()));
        if(existed.isPresent()){
            throw new UserExistedException(userDto.getUser_id());
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getUser_pw());
        UserDto newUser = UserDto.builder()
                .user_id(userDto.getUser_id())
                .user_name(userDto.getUser_name())
                .user_email(userDto.getUser_email())
                .user_ph(userDto.getUser_ph())
                .user_pw(encodedPassword)
                .build();
        userDao.addUser(newUser);
        return true;
    }

    @Override
    public UserDto getUserById(String user_id) throws Exception{
        return userDao.findUserById(user_id);
    }
}
