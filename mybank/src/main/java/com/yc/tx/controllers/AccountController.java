package com.yc.tx.controllers;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.service.AccountService;
import com.yc.tx.vo.AccountVO;
import com.yc.tx.vo.ResultVO;
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

import java.util.List;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-24 20:53
 */
@Controller
@Slf4j
@Api(value = "账户操作接口", tags = {"账户操作接口", "控制层"})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/openAccount", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "开户", notes = "开户")
    @ApiImplicitParams({@ApiImplicitParam(name = "money", value = "存款金额", required = true)})
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

    @RequestMapping(value = "/deposite", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("存款")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "存款账号", required = true),
            @ApiImplicitParam(name = "money", value = "存款金额", required = true)

    })
    public @ResponseBody
    ResultVO deposite(AccountVO accountVO) {
        ResultVO<Accounts> rv = new ResultVO();
        Accounts accounts = new Accounts();
        accounts.setBalance(accountVO.getMoney());
        accounts.setAccountId(accountVO.getAccountId());
        try {

            rv.setCode(1);
            rv.setData(accountService.deposite(accounts, accountVO.getMoney(), OpTypes.deposite.getName(), "存钱"));
        } catch (Exception e) {
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }


    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ApiOperation(value = "取款", notes = "根据账户编号及金额来完成取款操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "账户编号", required = true),
            @ApiImplicitParam(name = "money", value = "操作金额", required = true)})
    public @ResponseBody
    ResultVO<Accounts> withdraw(AccountVO accountVO) {
        System.out.println(accountVO);
        ResultVO<Accounts> rv = new ResultVO();
        try {
            Accounts account = new Accounts();
            account.setAccountId(accountVO.getAccountId());
            account.setBalance(accountVO.getMoney());
            Accounts a = accountService.withdraw(account, account.getBalance(), OpTypes.withdraw.getName(), "");
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    @ApiOperation(value = "转账", notes = "根据账户编号及金额, 对方接收账号来完成转账操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true),
            @ApiImplicitParam(name = "money", value = "转账金额", required = true),
            @ApiImplicitParam(name = "inAccountId", value = "对方接收账号", required = true)})
    public @ResponseBody
    ResultVO<Accounts> transfer(AccountVO accountVO) {
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

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ApiOperation(value = "查询", notes = "根据账户编号查询订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true)})
    public @ResponseBody
    ResultVO<List<OpRecord>> findById(AccountVO accountVO) {
        ResultVO<List<OpRecord>> rv = new ResultVO();
        Accounts accounts = new Accounts();
        accounts.setAccountId(accountVO.getAccountId());
        System.out.println(accounts);
        try {
            List<OpRecord> opRecordList = accountService.findById(accounts);
            rv.setCode(1);
            rv.setData(opRecordList);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }
}
