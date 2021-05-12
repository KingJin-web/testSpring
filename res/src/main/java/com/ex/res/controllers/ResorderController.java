package com.ex.res.controllers;

import com.ex.res.bean.Resfood;
import com.ex.res.biz.ResfoodBiz;
import com.ex.res.utils.Constants;
import com.ex.res.vo.Cartitem;
import com.ex.res.vo.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.ex.res.utils.Constants.CART;
import static com.ex.res.utils.Constants.RESFOODLIST;

@RestController
public class ResorderController {

    @Autowired
    private ResfoodBiz resfoodBiz;

    @RequestMapping(value = "/orderJson", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel orderJsonOp(HttpServletRequest request, JsonModel jm, HttpSession session) {
        conmonOrder(request, session);
        jm.setCode(1);
        return jm;
    }

    private void conmonOrder(HttpServletRequest request, HttpSession session) {
        //取出要加入购物车的商品id和数量
        int fid = Integer.parseInt(request.getParameter("fid"));
        int num = Integer.parseInt(request.getParameter("num"));

        //取出菜品列表,以查找对应的菜品
        List<Resfood> list = null;
        if (session.getAttribute(RESFOODLIST) != null) {
            list = (List<Resfood>) session.getAttribute(RESFOODLIST);
        } else {
            //没有,则查
            list = resfoodBiz.findAll();
            session.setAttribute(RESFOODLIST, list);
        }
        Resfood resfood = null;
        for (Resfood resfood1 : list) {
            if (resfood1.getFid() == fid) {
                resfood = resfood1;
                break;
            }
        }

        //购物车跟用户相关,所以存session
        Map<Integer, Cartitem> cartitemMap = null;
        //是否是第一次购买,如果是,则 session中是没有存购物车
        if (session.getAttribute(CART) != null) {
            cartitemMap = (Map<Integer, Cartitem>) session.getAttribute(CART);
        } else {
            cartitemMap = new HashMap<>();
        }

        //看这个购物车是否有id
        Cartitem cartitem = null;
        if (cartitemMap.containsKey(fid)) {
            //证明用户已经购买了这个菜,则数量增加
            cartitem = cartitemMap.get(fid);
            int count = cartitem.getNum() + num;
            cartitem.setNum(count);
        } else {
            //还没有买过,则创建一个Cartitem存到map中
            cartitem = new Cartitem();
            cartitem.setResfood(resfood);
            cartitem.setNum(num);
        }

        if (cartitem.getNum() <= 0) {//如果购买项的数量 <= 0,则从购物车删除
            cartitemMap.remove(fid);
        } else {
            cartitem.getSmallCount();
            cartitemMap.put(fid, cartitem);
        }
        //将cart存到session中
        session.setAttribute(CART, cartitemMap);

    }

    @RequestMapping(value = "/getCartInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel getCartInfoOp(HttpServletRequest request, HttpSession session, JsonModel jm) {
        List<Cartitem> list = new ArrayList<>();
        if (session.getAttribute(Constants.CART) != null) {
            jm.setCode(1);
            //变更,将对象改为list,方便后面循环取值
            Map<Integer, Cartitem> cartitemMap = null;
            cartitemMap = (Map<Integer, Cartitem>) session.getAttribute(CART);
            Set<Integer> set = cartitemMap.keySet();
            Iterator<Integer> integerIterator = set.iterator();
            while (integerIterator.hasNext()) {
                int x = integerIterator.next();//x在这里就是cartitemMap中的键 fid
                list.add(cartitemMap.get(x));
            }
            jm.setObj(list);
        } else {
            jm.setCode(0);
        }
        return jm;
    }

    @RequestMapping(value = "/deloder", method = RequestMethod.GET)
    public JsonModel deloder(HttpSession session, JsonModel jm, Resfood resfood) {
        int fid = resfood.getFid();
        //购物车跟用户相关,所以存session
        Map<Integer, Cartitem> cartitemMap = null;
        if (session.getAttribute(CART) != null) {
            cartitemMap = (Map<Integer, Cartitem>) session.getAttribute(CART);
        } else {
            cartitemMap = new HashMap<>();
        }
        if (cartitemMap.containsKey(fid)) {
            cartitemMap.remove(fid);
            jm.setCode(1);
        } else {
            jm.setCode(0);
        }
        //将cartitemMap存到session中
        session.setAttribute(CART, cartitemMap);
        return jm;
    }

    @RequestMapping(value = "/clearAll", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel clearAllOp(HttpSession session, JsonModel jm) {
        session.removeAttribute(CART);
        jm.setCode(1);
        return jm;
    }
}
