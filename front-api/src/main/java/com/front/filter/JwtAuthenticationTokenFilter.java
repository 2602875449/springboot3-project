package com.front.filter;


import com.frame.config.ResponseResult;
import com.frame.enums.AppHttpCodeEnum;
import com.frame.utils.JwtUtil;
import com.frame.utils.RedisCache;
import com.frame.utils.WebUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    public final RedisCache redisCache;
    private final Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
//获取请求头的token
        var token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
//            说明需要登录直接放行
            filterChain.doFilter(request, response);
        }
        Claims claims;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
//            token超时 非法·
//            响应告诉前端需要重新登录

            return;
        }
//        解析获取userId
        var userId = (String) claims.getSubject();
//在redis 中获取信息
        var loginUser = redisCache.getCacheObject("blogLogin" + userId);
        if (Objects.isNull(loginUser)) {
//            说明登录过期，提示登录过期
            var responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, gson.toJson(responseResult));
            return;
        }
//        存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        放行
        filterChain.doFilter(request, response);
    }
}
