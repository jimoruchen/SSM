package com.example.controller;

import com.example.entity.Person;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    /**
     * 自动装配流程（先按照类型，再按照名称）
     * 1、按照类型找到这个组件：
     *      1.0、只有且只找到一个，直接注入，名字无所谓
     *      1.1、找到多个再按照名字去找；变量名就是名字
     *          1.1.0、找到直接注入
     *          1.1.1、找不到报错
     */
    @Autowired      //自动装配，原理：Spring调用容器 .getBean()
    UserService userService;

    @Autowired
    Person Bill;
}
