package com.smallB.QOS.global.interceptor;

import com.smallB.QOS.user.error.Exception.UnauthorizedException;
import com.smallB.QOS.global.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String token = (request.getHeader(HEADER_AUTH).substring("Bearer ".length()));

        if(token != null && jwtUtil.isUsable(token)){
            return true;
        }else{
            throw new UnauthorizedException();
        }
    }
}
