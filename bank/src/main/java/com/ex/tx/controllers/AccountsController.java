package com.ex.tx.controllers;

import com.ex.tx.bean.Accounts;
import com.ex.tx.bean.OpTypes;
import com.ex.tx.service.AccountService;
import com.ex.tx.vo.AccountsVO;
import com.ex.tx.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@Api(value = "账户操作接口", tags = {"账户操作接口", "控制层"})
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/openAccounts", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "开户", notes = "输入金额,开通新账号")
    @ApiImplicitParam(name = "money", value = "金额", required = true, dataType = "Double")
    public @ResponseBody
    ResultVO openAccounts(AccountsVO accountsVO) {
        log.debug("用户请求开户,存入" + accountsVO.getMoney());
        ResultVO rv = new ResultVO();
        try {
            Accounts a = new Accounts();
            double money = 1;
            if (accountsVO.getMoney() != null || accountsVO.getMoney() > 0) {
                money = accountsVO.getMoney();
            }
            Integer id = accountService.openAccount(a, money);
            a.setAccountId(id);
            a.setBalance(money);
            rv.setCode(1);
            rv.setData(a);

        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/deposite", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "存款", notes = "输入金额与账号,将金额存入该账号中")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "money", value = "金额", required = true, dataType = "Double")})
    public @ResponseBody
    ResultVO deposite(AccountsVO accountsVO) {
        log.debug("用户" + accountsVO.getAccountId() + "存入" + accountsVO.getMoney());
        ResultVO rv = new ResultVO();
        try {
            Accounts a = new Accounts();
            double money = 0;
            if (accountsVO.getMoney() != null || accountsVO.getMoney() > 0) {
                money = accountsVO.getMoney();

            }
            a.setAccountId(accountsVO.getAccountId());
            a = accountService.deposite(a, accountsVO.getMoney(), OpTypes.deposite.getName(), null);
            rv.setCode(1);
            rv.setData(a);

        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/withdraw", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "取款", notes = "输入金额与账号,从该账号中取出金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "money", value = "金额", required = true, dataType = "Double")})
    public @ResponseBody
    ResultVO withdraw(AccountsVO accountsVO) {
        log.debug("用户" + accountsVO.getAccountId() + "取出" + accountsVO.getMoney());
        ResultVO rv = new ResultVO();
        try {
            Accounts a = new Accounts();
            double money = 0;
            if (accountsVO.getMoney() != null || accountsVO.getMoney() > 0) {
                money = accountsVO.getMoney();
            }
            a.setAccountId(accountsVO.getAccountId());
            a = accountService.withdraw(a, accountsVO.getMoney(), OpTypes.withdraw.getName(), null);
            rv.setCode(1);
            rv.setData(a);

        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/transfer", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "转账", notes = "根据账户编号及金额, 对方接收账号来完成转账操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "Int"),
            @ApiImplicitParam(name = "money", value = "转账金额", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "inAccountId", value = "对方接收账号", required = true, dataType = "Int")})
    public @ResponseBody
    ResultVO<Accounts> transfer(AccountsVO accountVO) {
        Accounts inAccount = new Accounts();
        inAccount.setAccountId(accountVO.getInAccountId());
        Accounts outAccount = new Accounts();
        outAccount.setAccountId(accountVO.getAccountId());
        ResultVO<Accounts> rv = new ResultVO();
        try {
            Accounts a = accountService.transfer(inAccount, outAccount, accountVO.getMoney());
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/showBalance", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "查询", notes = "根据账户编号, 查询该账号的余额")
    @ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "Int")
    public @ResponseBody
    ResultVO showBalance(AccountsVO accountsVO) {
        log.debug("用户" + accountsVO.getAccountId() + "转给用户" + accountsVO.getInAccountId() + accountsVO.getMoney());
        ResultVO rv = new ResultVO();
        try {
            Accounts a = new Accounts();

            a.setAccountId(accountsVO.getAccountId());

            a = accountService.showBalance(a);

            rv.setCode(1);
            rv.setData(a);

        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

}
