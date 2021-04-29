package com.king.spring_rdering.bean;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: testSpring
 * @description: normprice:原价  realprice:现价   description:简介 detail详细的
 * @author: King
 * @create: 2021-04-29 18:42
 */
@Data
@Entity
@Table(name="ResFood")
@Proxy(lazy = false)
public class ResFood implements Serializable {
    private static final long serialVersionUID = 8426633128515035414L;
    @Id
    private Integer fid;
    private String fname;
    private BigDecimal normprice;//:原价
    private BigDecimal realprice;//:现价
    private String description;//:简介
    private String detail;//详细的
}
