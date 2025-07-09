package com.example.dao;

import com.example.datasource.MyDataSource;
import jakarta.annotation.Resource;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@ToString
@Repository
public class DeliveryDao {

    @Resource
    MyDataSource dataSource;

    void save() {
        System.out.println("数据源，保存数据" + dataSource);
    }

}
