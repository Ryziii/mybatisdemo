package com.rysiw.demo.config;

import com.rysiw.demo.common.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Rysiw
 * @date 2021/8/26 23:19
 */
@Configuration
public class InterceptorMvcConfig implements WebMvcConfigurer {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor()).addPathPatterns("/api/v1/**");
    }
}
