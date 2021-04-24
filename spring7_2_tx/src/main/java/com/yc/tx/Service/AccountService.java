package com.yc.tx.Service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;

import java.util.List;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-17 16:26
 */
public interface AccountService {
    Integer openAccount(Accounts account,double money);
    Accounts deposite(Accounts account,double money,String optype,String transferid);
    Accounts withdraw(Accounts account,double money,String optype,String transferid);
    Accounts transfer(Accounts inAccount,Accounts outAccount,double money);
    Accounts showBalance(Accounts account);
    List<OpRecord> findById(Accounts account);

}
