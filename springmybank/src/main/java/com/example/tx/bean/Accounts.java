package com.example.tx.bean;

import lombok.Data;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-14 20:22
 */
@Data//使用lombok自动生成get/set方法
public class Accounts {
    private Integer accountId;//最好使用包装类对象
    private Double balance;
}
