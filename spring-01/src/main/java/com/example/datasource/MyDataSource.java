package com.example.datasource;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MyDataSource {

    private String url;
    private String username;
    private String password;
}
