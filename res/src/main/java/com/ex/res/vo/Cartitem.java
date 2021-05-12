package com.ex.res.vo;

import com.ex.res.bean.Resfood;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车项
 */
public class Cartitem implements Serializable {
    private static final long serialVersionUID = 3174059153183218892L;
    private Resfood resfood;
    private int num;
    private BigDecimal smallCount;

    public BigDecimal getSmallCount() {
        this.smallCount = resfood.getRealprice().multiply(new BigDecimal(num));
        return smallCount;
    }

    public Resfood getResfood() {
        return resfood;
    }

    public void setResfood(Resfood resfood) {
        this.resfood = resfood;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
