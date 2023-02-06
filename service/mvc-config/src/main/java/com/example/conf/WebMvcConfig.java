package com.example.conf;

import com.alibaba.cloud.seata.web.SeataHandlerInterceptor;
import com.example.filter.LoginUserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginUserInfoInterceptor()).addPathPatterns("/**").order(0);
        registry.addInterceptor(new SeataHandlerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
