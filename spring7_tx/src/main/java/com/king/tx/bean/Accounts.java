package com.king.tx.bean;

import lombok.Data;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-14 20:42
 */
@Data
//lombok.Data;
//简化你的get set
//自动生成 get set toString hashCode equals
//需要引入包 IDE需要插件
public class Accounts {
    private int accountid;
    private double balance;


}
