package com.smallB.QOS.user.controller;

import com.smallB.QOS.user.domain.SessionRequestDto;
import com.smallB.QOS.user.domain.SessionResponseDto;
import com.smallB.QOS.user.domain.UserDto;
import com.smallB.QOS.user.service.UserService;
import com.smallB.QOS.global.util.JwtUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.user.dao")
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<SessionResponseDto> create(@RequestBody @Valid SessionRequestDto sessionRequestDto) throws Exception {

        UserDto userDto = userService.authenticate(sessionRequestDto);

        String accessToken= jwtUtil.createToken(sessionRequestDto);

        String url = "/login";

        return ResponseEntity.created(new URI(url))
                .body(SessionResponseDto.builder()
                        .accessToken(accessToken)
                        .build());
    }
}
