package com.yc.tx.controllers;

import com.yc.tx.bean.Accounts;
import com.yc.tx.service.AccountService;
import com.yc.tx.vo.AccountVO;
import com.yc.tx.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-24 20:53
 */
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/openAccount", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ResultVO openAccounts(AccountVO accountVO) {
        ResultVO rv = new ResultVO();
        try {

            Accounts accounts = new Accounts();
            double money = 1;
            if (accountVO.getMoney() != null && accountVO.getMoney() > 0) {
                money = accountVO.getMoney();
            }
            Integer id = accountService.openAccount(accounts, money);
            accounts.setAccountId(id);
            accounts.setBalance(money);
            rv.setCode(1);
            rv.setData(accounts);
        } catch (Exception e) {
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/openAccount", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ResultVO deposite(AccountVO accountVO) {
        ResultVO rv = new ResultVO();
        try {

            Accounts accounts = new Accounts();
            double money = 1;
            if (accountVO.getMoney() != null && accountVO.getMoney() > 0) {
                money = accountVO.getMoney();
            }
            Integer id = accountService.openAccount(accounts, money);
            accounts.setAccountId(id);
            accounts.setBalance(money);
            rv.setCode(1);
            rv.setData(accounts);
        } catch (Exception e) {
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }
}
