package com.smallB.QOS.user.service;

import com.smallB.QOS.user.dao.UserDao;
import com.smallB.QOS.user.domain.SessionRequestDto;
import com.smallB.QOS.user.domain.UserDto;
import com.smallB.QOS.user.error.Exception.UnauthorizedException;
import com.smallB.QOS.user.error.Exception.UserExistedException;
import com.smallB.QOS.user.error.Exception.UserNotExistedException;
import com.smallB.QOS.user.error.Exception.UserUpdateFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

import static java.util.Objects.isNull;

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
        String encodedPassword = PasswordEncoder().encode(userDto.getUser_pw());

        UserDto newUser = UserDto.builder()
                .user_id(userDto.getUser_id())
                .user_name(userDto.getUser_name())
                .user_email(userDto.getUser_email())
                .user_ph(userDto.getUser_ph())
                .user_pw(encodedPassword)
                .store_id(userDto.getStore_id())
                .status(userDto.getStatus())
                .build();
        userDao.addUser(newUser);
        return true;
    }

    private PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDto getUserById(@Valid String user_id) throws Exception{
        UserDto userDto = userDao.findUserById(user_id);
        if(isNull(userDto)){
            throw new UserNotExistedException(user_id);
        }
        return userDto;
    }

    @Override
    public UserDto authenticate(SessionRequestDto sessionRequestDto) throws Exception{
        UserDto userDto = userDao.findUserById(sessionRequestDto.getUser_id());

        if(isNull(userDto)){
            throw new UnauthorizedException();
        }
        PasswordEncoder passwordEncoder = PasswordEncoder();

        if(!passwordEncoder.matches(sessionRequestDto.getUser_pw(), userDto.getUser_pw())){
            throw new UnauthorizedException();
        }
        return userDto;
    }

    @Override
    public Boolean updateUser(@Valid UserDto user) throws Exception{

        if(!isNull(user.getUser_pw())){
            user.setUser_pw(PasswordEncoder().encode(user.getUser_pw()));
        }

        int result = userDao.updateUser(user);

        if(result==0){
            throw new UserUpdateFailedException();
        }

        return true;
    }

    public UserDto getUserByStoreId(@Valid String store_id) throws Exception{
        UserDto userDto = userDao.findUserByStoreId(store_id);
        if(isNull(userDto)) {
            throw new UserNotExistedException();
        }
        return userDto;
    }
}
