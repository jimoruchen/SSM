package com.example.entity;

import lombok.Data;

@Data
public class Person {
    private String name;
    private int age;
    private String gender;

    public String toString(String name, int age, String gender) {
        return name + " " + age + " " + gender;
    }
}