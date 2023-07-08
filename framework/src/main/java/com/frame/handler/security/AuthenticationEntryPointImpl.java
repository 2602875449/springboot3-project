package com.frame.handler.security;


import com.frame.config.ResponseResult;
import com.frame.enums.AppHttpCodeEnum;
import com.frame.utils.WebUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @description: 认证失败处理类
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final Gson gson = new Gson();


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
//        打印
        authException.printStackTrace();
        ResponseResult responseResult;
//        认证失败 给前端
//        InternalAuthenticationServiceException
//        BadCredentialsException
        if (authException instanceof BadCredentialsException) {
            responseResult = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(), authException.getMessage());
        } else if (authException instanceof InternalAuthenticationServiceException) {
            responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN.getCode(), authException.getMessage());
        } else {
            responseResult = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), "认证授权失败！");
        }
        WebUtils.renderString(response, gson.toJson(responseResult));
    }
}
