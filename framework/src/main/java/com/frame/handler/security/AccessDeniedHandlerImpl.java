package com.frame.handler.security;

import com.frame.config.ResponseResult;
import com.frame.enums.AppHttpCodeEnum;
import com.frame.utils.WebUtils;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * {@code @description:} 认证失败处理类
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    private final Gson gson = new Gson();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        //        打印
        accessDeniedException.printStackTrace();
//        认证失败 给前端
        var responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        WebUtils.renderString(response, gson.toJson(responseResult));
    }
}
