package com.xcode.apiPassenger.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())

                // 攔截路徑(必須加入token)
                .addPathPatterns("/**")

                // 不攔截路徑(不用加入token)
                .excludePathPatterns("/noauth");
    }
}
