package com.fefiei.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "web.feifei")//前缀
public class MyProperties {
    @Value("${title}")
    private String title;
    /**
     * 获取个人描述
     */
    @Value("${description}")
    private String description;
    /**
     * 获取获取个人标题
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置获取个人标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * 获取获取个人描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置获取个人描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
