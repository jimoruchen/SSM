package com.example.entity;

import ch.qos.logback.core.CoreConstants;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class Person {
    private String name;
    private int age;
    private String gender;

    public Person() {
        System.out.println("Person constructor");
    }

}