package com.king.spring_rdering.test;

import com.king.spring_rdering.bean.ResOrder;
import com.king.spring_rdering.dao.ResFoodDao;
import com.king.spring_rdering.dao.ResOrderDao;
import com.king.spring_rdering.enums.OrderStatusEnum;
import com.king.spring_rdering.vo.CartItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ResOrderDaoImplTest {

    @Autowired
    private ResOrderDao resOrderDao;

    @Test
    public void findAll() {
        resOrderDao.findAll();
    }

    @Test
    public void findById() {
    }

    @Test
    public void deleteById() {
    }

    @Autowired
    private ResFoodDao resFoodDao;

    @Test
    public void completeOrder() {
        ResOrder resOrder = new ResOrder();
        resOrder.setUserid(1);
        resOrder.setStatus(OrderStatusEnum.NEW.getCode());
        resOrder.setTel("123456789");
        resOrder.setAddress("Address");
        resOrder.setPs("aa");
        resOrder.setOrdertime(new Timestamp(new Date().getTime()));

        Map<Integer, CartItem> cart = new HashMap<>();
        Integer fid1 = 11;
        CartItem c1 = new CartItem();
        c1.setFood(resFoodDao.findByFid(fid1));
        c1.setNum(1);
        cart.put(fid1, c1);
        Integer fid2 = 12;
        CartItem c2 = new CartItem();
        c2.setFood(resFoodDao.findByFid(fid2));
        c2.setNum(1);

        cart.put(fid2, c2);


        System.out.println(resOrderDao.completeOrder(resOrder, cart));
    }
}