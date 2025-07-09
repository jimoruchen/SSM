package com.example.dao;

import com.example.entity.Dog;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@ToString
@Repository
public class UserDao {

    Dog dog1;

//    public UserDao(Dog dog) {         //构造器注入
//        this.dog1 = dog;
//    }

    @Autowired
    public void setDog(@Qualifier("dog1") Dog dog) {        //set方法注入
        System.out.println(dog);
        this.dog1 = dog;
    }
}
