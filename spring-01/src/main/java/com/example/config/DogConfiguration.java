package com.example.config;

import com.example.entity.Dog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DogConfiguration {

    @ConditionalOnMissingBean(name = "Alex")
    @Bean
    public Dog dog() {
        Dog dog = new Dog();
        dog.setName("dog");
        return dog;
    }

    @Bean
    public Dog dog1() {
        Dog dog = new Dog();
        dog.setName("dog1");
        return dog;
    }

    @Bean
    public Dog dog2() {
        Dog dog = new Dog();
        dog.setName("dog2");
        return dog;
    }
}
