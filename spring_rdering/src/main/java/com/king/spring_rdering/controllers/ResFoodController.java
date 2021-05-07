package com.king.spring_rdering.controllers;

import com.king.spring_rdering.bean.ResFood;
import com.king.spring_rdering.bean.ResOrder;
import com.king.spring_rdering.bean.ResUser;
import com.king.spring_rdering.dao.ResFoodDao;
import com.king.spring_rdering.dao.ResOrderDao;
import com.king.spring_rdering.vo.CartItem;
import com.king.spring_rdering.vo.JsonModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.king.spring_rdering.util.Constants;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static com.king.spring_rdering.util.Constants.RESFOODLIST;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-05 14:43
 */
@RestController
@Slf4j
@Api(value = "网络订餐系统操作接口", tags = "控制层")
public class ResFoodController {
    @Autowired
    private ResFoodDao ResFoodDao;
    @Autowired
    private ResOrderDao resOrderDao;

    @RequestMapping(value = "/findAllFoods", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "查询所有菜品", notes = "查询所有的菜品")
    public JsonModel findAllFoods(HttpSession session, JsonModel jm) {//这里的方法的参数不是从界面取的,而是由springmvc创建后传入的空对象
        List<ResFood> list = ResFoodDao.findAll();
        session.setAttribute(RESFOODLIST, list);
        //返回jsonModel
        jm.setCode(1);
        jm.setObj(list);
        return jm;
    }

    @RequestMapping(value = "/findFoodByFid", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "根据编号查找菜品", notes = "根据fid查找菜品")
    @ApiImplicitParams({@ApiImplicitParam(name = "fid", value = "菜品编号", required = true)})
    public JsonModel findFoodByFid(HttpSession session, ResFood ResFood, JsonModel jm) {
        List<ResFood> list = null;
        if (session.getAttribute(RESFOODLIST) != null) {
            list = (List<ResFood>) session.getAttribute(RESFOODLIST);
        } else {
            //没有,则查
            list = ResFoodDao.findAll();
            session.setAttribute(RESFOODLIST, list);
        }
        for (ResFood ResFood1 : list) {
            if (ResFood.getFid().equals(ResFood1.getFid())) {
                jm.setCode(1);
                jm.setObj(ResFood1);
                return jm;
            }
        }
        jm.setCode(0);
        return jm;
    }

    @RequestMapping(value = "confirmOrder", method = RequestMethod.POST)
    public JsonModel confirmOrder(HttpSession session, ResOrder resorder, JsonModel jm) {
        if (session.getAttribute(Constants.LOGINUSER) == null) {
            jm.setCode(0);
            jm.setMsg("user has not been logined...");
            return jm;
        }
        //查询用户id，从session中取出登录用户
        ResUser resUser = (ResUser) session.getAttribute(Constants.LOGINUSER);
        resorder.setUserid(resUser.getUserid());
        //准备  Resorderitem数据
        if (session.getAttribute(Constants.CART) == null) {
            jm.setCode(0);
            jm.setMsg("you have not buy any thing..");
            return jm;
        }
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute(Constants.CART);

        //完成订单（将订单和购物车里的订单项存入数据库）
        resOrderDao.completeOrder(resorder, cart);
        //删除session存入的购物车
        session.removeAttribute(Constants.CART);
        jm.setCode(1);
        return jm;
    }
}
