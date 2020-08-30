package com.smallB.QOS.filter;

import com.smallB.QOS.error.UnauthorizedException;
import com.smallB.QOS.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        final String token = (request.getHeader(HEADER_AUTH).substring("Bearer ".length()));

        if(token != null && jwtUtil.isUsable(token)){
            System.out.println("JwtInterceptor class");
            return true;
        }else{
            System.out.println("JwtInterceptor class");
            throw new UnauthorizedException();
        }
    }
}
