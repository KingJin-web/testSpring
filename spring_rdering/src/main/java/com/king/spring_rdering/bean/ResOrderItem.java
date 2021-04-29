package com.king.spring_rdering.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @program: testSpring
 * @description: dealprice:成交价   roid:订单号   fid:商品号  num:数量
 * @author: King
 * @create: 2021-04-29 19:11
 */
@Data
@Entity
public class ResOrderItem {
    @Id
    private Integer roiid;
    private Integer roid;
    private Integer fid;
    private BigDecimal dealprice;
    private Integer num;
}
