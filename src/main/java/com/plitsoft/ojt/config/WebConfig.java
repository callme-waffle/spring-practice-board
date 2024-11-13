package com.plitsoft.ojt.config;

import com.plitsoft.ojt.global.interceptor.InterceptController;
import com.plitsoft.ojt.global.interceptor.VersionInterceptControl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(defaultInterceptor()).addPathPatterns("/*");
    }

    @Bean
    public InterceptController defaultInterceptor() {
        return new InterceptController();
    }

}
