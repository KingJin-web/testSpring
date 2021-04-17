package com.king.tx.bean;

public enum Optypes {
    deposite("deposite", 1), withdraw("withdraw", 2), transfer("transfer", 3);
    private String name;
    private int index;

    Optypes(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Optypes{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }

    Optypes() {

    }

    public String getName() {
        return name;
    }
}
