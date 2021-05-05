package com.king.spring_rdering.vo;

import com.king.spring_rdering.bean.ResFood;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: testSpring
 * @description: 购物车
 * @author: King
 * @create: 2021-05-04 18:45
 */
public class CartItem implements Serializable {

    private static final long serialVersionUID = -7343644775775816091L;

    private ResFood food;
    private int num;
    private BigDecimal smallCount;

    public BigDecimal getSmallCount(){
        this.smallCount = food.getRealprice().multiply(new BigDecimal(num));
        return smallCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ResFood getFood() {
        return food;
    }

    public void setFood(ResFood food) {
        this.food = food;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setSmallCount(BigDecimal smallCount) {
        this.smallCount = smallCount;
    }
}
