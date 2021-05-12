package com.ex.res.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜单
 */
@Data
@Entity
public class Resfood implements Serializable {

    private static final long serialVersionUID = -9167978915427345021L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fid;
    private String fname;
    private BigDecimal normprice;//金额计算,不能用Double,要用BigDecimal
    private BigDecimal realprice;
    private String detail;
    private String fphoto;

}
