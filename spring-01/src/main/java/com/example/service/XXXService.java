package com.example.service;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Data
@ToString
@Service
public class XXXService implements EnvironmentAware, BeanNameAware {

    private Environment env;
    private String name;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    public String getOsType() {
        return env.getProperty("os.name");
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
