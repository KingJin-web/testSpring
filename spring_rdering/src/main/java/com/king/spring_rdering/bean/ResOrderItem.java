package com.king.spring_rdering.bean;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @program: testSpring
 * @description: dealprice:成交价   roid:订单号   fid:商品号  num:数量
 * @author: King
 * @create: 2021-04-29 19:11
 */
@Data
@Entity
@Table(name = "ResOrderItem")
@Proxy(lazy = false)
public class ResOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roiid;

    private Integer roid;
    private Integer fid;
    private BigDecimal dealprice;
    private Integer num;
}
