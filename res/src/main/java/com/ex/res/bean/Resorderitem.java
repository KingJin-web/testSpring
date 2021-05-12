package com.ex.res.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
public class Resorderitem implements Serializable {
    private static final long serialVersionUID = 4928521554310804369L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roiid;
    private Integer roid;
    private Integer fid;
    private BigDecimal dealprice;
    private Integer num;
}
