package com.example.tx.bean;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-17 14:13
 */
//枚举类型
public enum OpTypes {

    deposite("deposite",1),withdraw("withdraw",2),transfer("transfer",3);

    private String name;
    private int index;

    private OpTypes(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return this.index+"_"+this.name;
    }

    public String getName() {
        return name;
    }
}
