package com.ex.res.controllers;

import com.ex.res.bean.Resuser;
import com.ex.res.biz.ResuserBiz;
import com.ex.res.utils.Constants;
import com.ex.res.vo.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.ex.res.utils.Constants.HOMEPAGE;
import static com.ex.res.utils.Constants.LASTVISITEDADDR;

@RestController
public class ResuserController {
    @Autowired
    private ResuserBiz resuserBiz;

    //登录
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel loginOp(HttpSession session, JsonModel jm, String valcode, String username, String pwd) {
        if (valcode == null || "".equals(valcode)) {
            jm.setCode(0);
            jm.setMsg("验证码不能为空...");
            return jm;
        }
        String validateCode = (String) session.getAttribute("validateCode");//在 ValCodeController中生成的标准验证码
        if (!valcode.equalsIgnoreCase(validateCode)) {
            jm.setCode(0);
            jm.setMsg("验证码输入错误...");
            return jm;
        }
        Resuser u = new Resuser();
        u.setUsername(username);
        u.setPwd(pwd);
        Resuser users = resuserBiz.login(u);
        if (users != null) {
            //保存这个用户：在数据库中保存用户状态
            session.setAttribute(Constants.LOGINUSER, users);//目前,都是将当前用户的状态信息(登录状态、购物车、菜品)都在session中
            //TODO:更好的方案是用一个数据库/redis来保存
            jm.setCode(1);
            //再看地址
            if (session.getAttribute(LASTVISITEDADDR) != null) {
                jm.setUrl((String) session.getAttribute(LASTVISITEDADDR));
            } else {
                //没有历史界面，则登录成功后到首页
                jm.setUrl(HOMEPAGE);
            }

        } else {
            jm.setCode(0);
            jm.setMsg("wrong username or password,plase tyr again");
        }
        return jm;
    }


    //退出登录
    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel logout(JsonModel jm, HttpSession session) {
        //从session中移除某键值对
        session.removeAttribute(Constants.LOGINUSER);
        jm.setCode(1);
        return jm;
    }

    //检查用户是否登录
    @RequestMapping(value = "/checkLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel checkLoginOp(JsonModel jm, HttpSession session) {
        if (session.getAttribute(Constants.LOGINUSER) == null) {
            jm.setCode(0);
            jm.setMsg("用户没有登录");
        } else {
            jm.setCode(1);
            Resuser user = (Resuser) session.getAttribute(Constants.LOGINUSER);
            jm.setObj(user);
        }
        return jm;
    }
}
