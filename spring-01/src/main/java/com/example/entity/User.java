package com.example.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class User implements InitializingBean, DisposableBean {

    private String username;
    private String password;
    private Cat cat;

    @Autowired
    public void setCat(Cat cat) {
        System.out.println("自动注入：" + cat);
        this.cat = cat;
    }

    public User() {
        System.out.println("User 构造器...");
    }

    public void initUser() {
        System.out.println("@Bean 初始化");
    }

    public void destroyUser() {
        System.out.println("@Bean 销毁");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean...destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean...afterPropertiesSet");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy...");
    }
}
