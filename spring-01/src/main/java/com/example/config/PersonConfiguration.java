package com.example.config;

import com.example.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PersonConfiguration {
    //在容器中注册一个自己的组件，方法名就是组件的名字
    @Scope("singleton")
    @Bean("Alex")
    public Person Alex() {
        Person person = new Person();
        person.setName("Alex");
        person.setAge(20);
        person.setGender("male");
        return person;
    }
    @Scope("prototype")
    @Bean("Bob")
    public Person Bob() {
        Person person = new Person();
        person.setName("Bob");
        person.setAge(20);
        person.setGender("male");
        return person;
    }
}
