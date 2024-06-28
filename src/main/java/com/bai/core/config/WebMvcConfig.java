package com.bai.core.config;

import com.bai.core.interceptor.UserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author burtbai
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private UserInfoInterceptor userInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInfoInterceptor);//.addPathPatterns("/**");
    }
}
