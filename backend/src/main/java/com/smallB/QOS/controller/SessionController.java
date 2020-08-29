package com.smallB.QOS.controller;

import com.smallB.QOS.domain.SessionRequestDto;
import com.smallB.QOS.domain.SessionResponseDto;
import com.smallB.QOS.domain.UserDto;
import com.smallB.QOS.service.UserService;
import com.smallB.QOS.util.JwtUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.dao")
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(@RequestBody SessionRequestDto sessionRequestDto) throws Exception {

        UserDto userDto = userService.authenticate(sessionRequestDto.getUser_id(),sessionRequestDto.getUser_pw());

        String accessToken= jwtUtil.createToken(userDto.getUser_id(),userDto.getUser_pw());

        String url = "/session";

        return ResponseEntity.created(new URI(url))
                .body(SessionResponseDto.builder()
                        .accessToken(accessToken)
                        .build());
    }
}
