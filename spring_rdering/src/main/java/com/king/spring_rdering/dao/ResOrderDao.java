package com.king.spring_rdering.dao;

import com.king.spring_rdering.bean.ResOrder;

import com.king.spring_rdering.vo.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ResOrderDao {
    //查询所有订单
    public List<ResOrder> findAll();

    //查询订单
    public ResOrder findById(int roid);

    //删除订单
    public void deleteById(int roid);


    /**
     * 下订单
     *
     * @param resOrder 订单信息
     * @param shopCart 购物车 对应订单项
     */
    public Integer completeOrder(ResOrder resOrder, Map<Integer, CartItem> shopCart);

}
