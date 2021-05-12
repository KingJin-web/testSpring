package com.ex.res.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单表
 */
@Data
@Entity
public class Resorder implements Serializable {
    private static final long serialVersionUID = -7763447986581996660L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roid;
    private Integer userid;
    private String address;
    private String tel;
    private Timestamp ordertime;
    private Timestamp deliverytime;//po中用的却是Timestamp
    private String ps;
    private Integer status;

    @Transient
    private String deliverytimeString;//vo中界面的参数类型

    public Timestamp getDeliveryTime() {
        Date d = null;
        if (deliverytimeString != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                d = df.parse(deliverytimeString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            d = new Date();
        }
        deliverytime = new Timestamp(d.getTime());
        return deliverytime;
    }
}
