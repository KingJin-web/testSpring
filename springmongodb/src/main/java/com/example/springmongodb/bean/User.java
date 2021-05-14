package com.example.springmongodb.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-15 00:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private Integer age;
    private String name;
}

class test{
    public static void main(String[] args) {
        User user1 = new User();
        user1.setAge(18);user1.setId(0);user1.setName("卢本伟");
        User user2 = new User(1,12,"蔡徐坤");
        System.out.println(user1);
        System.out.println(user2);
    }
}

