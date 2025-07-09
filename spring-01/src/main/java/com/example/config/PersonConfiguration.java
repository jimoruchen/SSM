package com.example.config;

import com.example.condition.MacCondition;
import com.example.condition.WindowsCondition;
import com.example.entity.Person;
import org.springframework.context.annotation.*;

@Configuration
public class PersonConfiguration {

    @Primary
    @Conditional(WindowsCondition.class)
    @Bean("Bill")
    public Person Bill() {
        Person person = new Person();
        person.setName("Bill");
        person.setAge(18);
        person.setGender("male");
        return person;
    }

    @Conditional(MacCondition.class)
    @Bean("Jobs")
    public Person Jobs() {
        Person person = new Person();
        person.setName("Jobs");
        person.setAge(18);
        person.setGender("male");
        return person;
    }

    // 在容器中注册一个自己的组件，方法名就是组件的名字
    @Lazy
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
