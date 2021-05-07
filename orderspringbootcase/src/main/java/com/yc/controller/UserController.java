package com.yc.controller;

import com.yc.bean.VObean;
import com.yc.biz.UserBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@RestController
@Api(description = "这是操作用户的controller")
@Validated
public class UserController {

    @Autowired
    private UserBiz userBiz;
    @Autowired
    private VObean vObean;

    @PostMapping("/u/t1")
    @ApiOperation(value = "验证登录", notes = "根据username姓名, pwd密码，进行验证")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "传入的姓名参数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "密码参数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "validateCode", value = "验证码参数", required = true, dataType = "String")})
    public VObean Test1(@RequestParam("username") String username,
                        @RequestParam("pwd") String pwd,
                        @RequestParam("validateCode") @NotNull(message = "验证码不能为空") String validateCode,
                        HttpSession session) throws Exception {
        try {
            String code = (String) session.getAttribute("validateCode");
            if (!validateCode.equalsIgnoreCase(code)) {
                vObean.setCode(500);
                vObean.setMsg("登录失败 验证码错误！！");
                return vObean;
            }
            Boolean aBoolean = userBiz.checkuser(username, pwd);
            if (aBoolean) {
                int i = userBiz.getuserid(username, pwd);
                vObean.setCode(200);
                //vObean.setData("登录成功");
                vObean.setMsg("登录成功");
                session.setAttribute("uid", String.valueOf(i));
                String uid = (String) session.getAttribute("uid");
                vObean.setData(uid);
                return vObean;
            } else {
                vObean.setCode(500);
                //vObean.setData("登录成功");
                vObean.setMsg("登录失败 用户名或密码错误");
                return vObean;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return vObean;
        }
    }

    @GetMapping("/u/t2")
    @ApiOperation(value = "退出登录", notes = "直接调用 即可 没登录则提示先登录")
    public VObean Test2(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        if (uid == null || uid.trim().isEmpty()) {
            vObean.setCode(500);
            vObean.setMsg("请先登录");
            return vObean;
        } else {
            session.removeAttribute("uid");
            vObean.setMsg("退出成功");
            vObean.setCode(200);
            return vObean;
        }

    }

    @GetMapping("/u/t3")
    @ApiOperation(value = "检查登录与否", notes = "直接调用 即可 没登录则提示先登录")
    public VObean Test3(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        if (uid == null || uid.trim().isEmpty()) {
            vObean.setCode(200);
            vObean.setMsg("未登录");
            return vObean;
        } else {
            vObean.setCode(200);
            vObean.setMsg("已登录");
            vObean.setData(uid);
            return vObean;
        }
    }
}
