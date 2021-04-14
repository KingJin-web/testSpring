package com.king.tx.bean;

import lombok.Data;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-14 20:42
 */
@Data
public class Accounts {
    private int accountid;
    private double balance;

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
