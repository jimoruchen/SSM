package com.example.entity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Dog {

    String name;


    public Dog() {
        System.out.println("Dog constructor");
    }
}
