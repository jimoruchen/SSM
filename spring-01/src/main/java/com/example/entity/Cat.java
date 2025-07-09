package com.example.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@PropertySource("classpath:cat.properties")
@ConfigurationProperties(prefix = "cat")
@ToString
@Data
@Component
public class Cat {

    /**
     * 1、@value("字面值")：直接赋值
     * 2、@value("${key}")：从配置文件动态取出某一项的值
     * 3、@value("#{SpEL}")：Spring表达式
     */
    @Value("大黄")
    private String name;
    //@Value("${cat.age}")
    private int age;
    @Value("#{T(java.util.UUID).randomUUID().toString()}")
    private String id;
    @Value("#{'Hello World!'.substring(0, 5)}")
    private String message;

    public Cat() {
        System.out.println("Cat构造器...");
    }
}
