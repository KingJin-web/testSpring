package com.king.spring_rdering.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-04 17:08
 */
@Data
public class roidVO implements Serializable {
    private static final long serialVersionUID = -2935614167923720403L;
    private Integer roid,
            fid,
            num;
    private BigDecimal dealprice;
    private String fname;

    public roidVO(Integer roid, Integer fid, Integer num, BigDecimal dealprice, String fname) {
        this.roid = roid;
        this.fid = fid;
        this.num = num;
        this.dealprice = dealprice;
        this.fname = fname;
    }
}
