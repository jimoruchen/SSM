package com.example.config;

import com.example.datasource.MyDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class DataSourceConfiguration {

    //利用条件注解，只在某种环境下激活一个组件

    @Profile("dev")     //环境标识
    @Bean
    public MyDataSource dev() {
        MyDataSource dataSource = new MyDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/dev");
        dataSource.setUsername("dev_user");
        dataSource.setPassword("dev_pass");
        return dataSource;
    }

    @Profile("test")
    @Bean
    public MyDataSource test() {
        MyDataSource dataSource = new MyDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        dataSource.setUsername("test_user");
        dataSource.setPassword("test_pass");
        return dataSource;
    }

    @Profile("prod")
    @Bean
    public MyDataSource prod() {
        MyDataSource dataSource = new MyDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/prod");
        dataSource.setUsername("prod_user");
        dataSource.setPassword("prod_pass");
        return dataSource;
    }
}
