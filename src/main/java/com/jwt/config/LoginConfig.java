package com.jwt.config;

import com.jwt.intecerpet.UserInfoDetaiIntecerpet;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by fyw on 2020/7/7.
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoDetaiIntecerpet()).addPathPatterns("/**");
    }
}
