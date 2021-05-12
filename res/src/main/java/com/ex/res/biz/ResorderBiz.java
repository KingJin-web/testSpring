package com.ex.res.biz;

import com.ex.res.bean.Resorder;
import com.ex.res.vo.Cartitem;

import java.util.Map;

public interface ResorderBiz {

    /**
     * 下订单
     *
     * @param resorder:订单信息
     * @param shopCart:购物车,对应订单项
     */
    public Integer completeOrder(Resorder resorder, Map<Integer, Cartitem> shopCart);
}
