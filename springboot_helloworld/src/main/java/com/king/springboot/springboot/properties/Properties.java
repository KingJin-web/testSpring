package com.king.springboot.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-24 10:29
 */
@ConfigurationProperties(prefix = "king")
public class Properties {
    private String tname;
    private int age;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
