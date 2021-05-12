package com.king.spring_rdering.bean;

import lombok.Data;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-08 23:20
 */
@Data
public class People {
   private int 年龄;
   private String 性别;
   private String 姓名;

    public People(int 年龄, String 性别, String 姓名) {
        this.年龄 = 年龄;
        this.性别 = 性别;
        this.姓名 = 姓名;
    }

    public static void main(String[] args) {
        People 人 = new People(18,"男","蔡徐坤");
        System.out.println(人);
    }
}
