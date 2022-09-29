package com.alibaba.arthas.tunnel.server.app.configuration.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author minsin/mintonzhang@163.com
 * @since 2022/9/28
 */
@RequiredArgsConstructor
@Configuration
public class WebIntercptorConfig {

    private final AuthInterceptor authInterceptor;


    @Bean
    public WebMvcConfigurer customWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(authInterceptor).addPathPatterns("/index.html");
            }
        };
    }
}
