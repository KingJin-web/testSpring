package com.king.spring_rdering.bean;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @program;// testSpring
 * @description;// 订单表
 * @author;// King
 * @create;// 2021-04-29 18;//44
 */
@Data
@Entity
@Proxy(lazy = false)
@Table(name = "ResOrder")
public class ResOrder implements Serializable {
    private static final long serialVersionUID = 1381107447916970085L;
    @Id
    private Integer roid;//订单号
    private Integer userid;//外键，下单的用户编号
    private String address;
    private String tel;
    private Timestamp ordertime;//下单时间
    private Timestamp deliverytime;
   // private String deliverytype;//送货方式
   // private String payment;//支付方式,
    private String ps;// ps附言
    private Integer status;
}
/**
 * roid int  primary key auto_increment,
 * 	userid int,
 * 	address varchar(500),
 * 	tel varchar( 100),
 * 	ordertime TIMESTAMP,
 * 	deliverytime TIMESTAMP,
 * 	ps varchar( 2000),
 * 	status int
 */
