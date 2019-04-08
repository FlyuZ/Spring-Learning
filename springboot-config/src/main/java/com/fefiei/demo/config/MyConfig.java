package com.fefiei.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "myconfig")//前缀
@PropertySource(value = "classpath:myconfig.properties")//配置文件路径
public class MyConfig {

    @Value("${test}")//需要使用@value注解来注入，否则是null
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
