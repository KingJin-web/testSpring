package com.king.spring_rdering.controllers;

import com.king.spring_rdering.bean.ResUser;
import com.king.spring_rdering.dao.ResUserDao;
import com.king.spring_rdering.util.Constants;
import com.king.spring_rdering.vo.JsonModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.util.Date;

import static com.king.spring_rdering.util.Constants.*;
import static com.king.spring_rdering.util.Constants.LASTVISITEDADDR;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-05 18:47
 */
@RestController
@Slf4j
@Api(value = "账户操作接口", tags = {"账户操作接口", "控制层"})
public class ResUserController {
    @Autowired
    private ResUserDao resUserDao;

    //登录
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "valcode", value = "验证码", required = true),
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true)}
    )
    public JsonModel
    loginOp(HttpSession session, JsonModel jm, String valcode, String username, String pwd) {
        if (valcode == null || "".equals(valcode)) {
            jm.setCode(0);
            jm.setMsg("验证码不能为空...");
            return jm;
        }
        long nowTime = new Date().getTime(); //oldTime
        long oldTime = (long) session.getAttribute("oldTime");
        if (nowTime - oldTime > 6 * 100000) {
            jm.setCode(0);
            jm.setMsg("验证码已超时...");
            return jm;
        }
        String validateCode = (String) session.getAttribute("validateCode");
        if (!valcode.equalsIgnoreCase(validateCode)) {
            jm.setCode(0);
            jm.setMsg("验证码输入错误...");
            return jm;
        }

        ResUser u = new ResUser();
        u.setUsername(username);
        u.setPwd(pwd);
        // resUserDao.login(u);
        if (resUserDao.login(u)) {
            //保存这个用户：在数据库中保存用户状态
            //TODO 更好的方案是使用一个数据库/Redis 来储存
            session.setAttribute(LOGINUSER, u);
            jm.setCode(1);
            //再看地址
            if (session.getAttribute(LASTVISITEDADDR) != null) {
                jm.setUrl((String) session.getAttribute(LASTVISITEDADDR));
            } else {
                //没有历史界面，则登录成功后到首页
                jm.setUrl(HOMEPAGE);
            }
            jm.setObj("登陆成功");

        } else {
            jm.setCode(0);
            jm.setMsg("wrong username or password,plase tyr again");
        }
        return jm;
    }


    //退出登录
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel logout(JsonModel jm, HttpSession session) {
        //从session中移除某键值对
        session.removeAttribute(Constants.LOGINUSER);
        jm.setCode(1);
        return jm;
    }

    //检查用户是否登录
    @RequestMapping(value = "/checkLogin", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "检查用户是否登录", notes = "检查用户是否登录")
    public JsonModel checkLoginOp(JsonModel jm, HttpSession session) {
        if (session.getAttribute(Constants.LOGINUSER) == null) {
            jm.setCode(0);
            jm.setMsg("用户没有登录");
        } else {
            jm.setCode(1);
            ResUser user = (ResUser) session.getAttribute(Constants.LOGINUSER);
            jm.setObj(user);
        }
        return jm;
    }
}


