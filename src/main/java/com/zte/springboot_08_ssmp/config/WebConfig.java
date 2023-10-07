package com.zte.springboot_08_ssmp.config;

import com.zte.springboot_08_ssmp.interceptor.PrivilegeInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new PrivilegeInterceptor()).addPathPatterns("/zizhihotel/**");
    }

}
