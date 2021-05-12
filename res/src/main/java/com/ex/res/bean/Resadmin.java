package com.ex.res.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 管理员表
 */
@Data
@Entity//实体类
public class Resadmin implements Serializable {
    private static final long serialVersionUID = 3032527567596964699L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer raid;//用引用类型
    private String raname;
    private String rapwd;
}
