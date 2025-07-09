package com.example.config;

import com.example.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean(initMethod = "initUser", destroyMethod = "destroyUser")
    public User user() {
        return new User();
    }
}
