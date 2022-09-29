package com.alibaba.arthas.tunnel.server.app.configuration.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author minsin/mintonzhang@163.com
 * @since 2022/9/28
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Value("${login.username}")
    private String username;
    @Value("${login.password}")
    private String password;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        boolean hasAuth = StringUtils.isNotBlank(authorization);
        if (hasAuth) {
            byte[] bytes = Base64Utils.decodeFromString(authorization.substring(6));
            String userAndPass = new String(bytes);
            String[] upArr = userAndPass.split(":");
            if (upArr.length == 2) {
                String uname = upArr[0];
                String uPassword = upArr[1];
                if (uname.equals(username) && password.equals(uPassword)) {
                    return true;
                }
            }
        }
        response.addHeader("WWW-Authenticate", "Basic realm=\"input Swagger Basic userName & password \"");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

}
