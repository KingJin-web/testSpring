package com.ex.res.controllers;

import com.ex.res.bean.Resfood;
import com.ex.res.bean.Resorder;
import com.ex.res.bean.Resuser;
import com.ex.res.biz.ResfoodBiz;
import com.ex.res.biz.ResorderBiz;
import com.ex.res.vo.Cartitem;
import com.ex.res.vo.JsonModel;
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
import java.util.List;
import java.util.Map;

import static com.ex.res.utils.Constants.*;


@RestController
@Slf4j
@Api(description = "网络订餐系统操作接口", tags = "控制层")
public class ResfoodController {
    @Autowired
    private ResfoodBiz resfoodBiz;
    @Autowired
    private ResorderBiz resorderBiz;

    @RequestMapping(value = "/findAllFoods", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "查询所有菜品", notes = "查询所有的菜品")
    public JsonModel findAllFoods(HttpSession session, JsonModel jm) {//这里的方法的参数不是从界面取的,而是由springmvc创建后传入的空对象
        List<Resfood> list = resfoodBiz.findAll();
        session.setAttribute(RESFOODLIST, list);
        //返回jsonModel
        jm.setCode(1);
        jm.setObj(list);
        return jm;
    }

    @RequestMapping(value = "/findFoodByFid", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "根据编号查找菜品", notes = "根据fid查找菜品")
    @ApiImplicitParams({@ApiImplicitParam(name = "fid", value = "菜品编号", required = true)})
    public JsonModel findFoodByFid(HttpSession session, Resfood resfood, JsonModel jm) {
        List<Resfood> list = null;
        if (session.getAttribute(RESFOODLIST) != null) {
            list = (List<Resfood>) session.getAttribute(RESFOODLIST);
        } else {
            //没有,则查
            list = resfoodBiz.findAll();
            session.setAttribute(RESFOODLIST, list);
        }
        for (Resfood resfood1 : list) {
            if (resfood.getFid().equals(resfood1.getFid())) {
                jm.setCode(1);
                jm.setObj(resfood1);
                return jm;
            }
        }
        jm.setCode(0);
        return jm;
    }

    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
    public JsonModel confirmOrder(HttpSession session, Resorder resorder, JsonModel jm) {
        if (session.getAttribute(LOGINUSER) == null) {
            jm.setCode(0);
            jm.setMsg("user has not been logined...");
            return jm;
        }
        //查询用户id，从session中取出登录用户
        Resuser resuser = (Resuser) session.getAttribute(LOGINUSER);
        resorder.setUserid(resuser.getUserid());
        //准备  Resorderitem数据
        if (session.getAttribute(CART) == null || ((Map<Integer, Cartitem>) session.getAttribute(CART)).size() <= 0) {
            jm.setCode(0);
            jm.setMsg("you have not buy any thing..");
            return jm;
        }
        Map<Integer, Cartitem> cart = (Map<Integer, Cartitem>) session.getAttribute(CART);
        try {

            //完成订单（将订单和购物车里的订单项存入数据库）
            resorderBiz.completeOrder(resorder, cart);
            //删除session存入的购物车
            session.removeAttribute(CART);
            jm.setCode(1);
        } catch (Exception e) {
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        return jm;
    }
}
