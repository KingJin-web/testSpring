package com.king.spring_rdering.controllers;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-05 19:20
 */

import com.king.spring_rdering.util.VerifyCodeUtil;
import com.king.spring_rdering.util.VerifyCodeUtils;
import com.king.spring_rdering.util.generateCode;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 图片验证码
 */
@Controller       // contentType="image/jpeg"       因为这里是一张验证码图片   但要在 session中保存生成的标准验证     验证码图片破解难度
@Api(value = "验证码接口", tags = {"验证"})
public class ValCodeController {

    @RequestMapping(value = "/verifyCodeServlet", method = RequestMethod.GET)
    public void valcode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String vcode = VerifyCodeUtils.outputImage(resp);
        //获得验证码的时间
        Date date = new Date();
       // System.out.println(date.getTime());
        long time = date.getTime();
        session.setAttribute("validateCode", vcode);

        session.setAttribute("oldTime", time);
        System.out.println(vcode);
    }
    @RequestMapping(value = "/verifyCodeServlet2", method = RequestMethod.GET)
    public void valcode2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String vcode = VerifyCodeUtil.outputImage(resp);
        //获得验证码的时间
        Date date = new Date();
        // System.out.println(date.getTime());
        long time = date.getTime();
        session.setAttribute("validateCode", vcode);

        session.setAttribute("oldTime", time);
        System.out.println(vcode);
    }

    @RequestMapping(value = "/verifyCodeServlet3", method = RequestMethod.GET)
    public void valcode3(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        String vcode = generateCode.outputImage(resp);
        //获得验证码的时间
        Date date = new Date();
        // System.out.println(date.getTime());
        long time = date.getTime();
        session.setAttribute("validateCode", vcode);

        session.setAttribute("oldTime", time);
        System.out.println(vcode);
    }
}
