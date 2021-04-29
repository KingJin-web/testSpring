package com.king.spring_rdering.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-29 19:27
 */
@Data
@Entity
public class ResOrderItemTemp implements Serializable {
    private static final long serialVersionUID = -3402408615310875422L;

    @Id
    private Integer roitid;//  primary key auto_increment,
    private Integer fid;//,
    private Integer num;//,
    private Timestamp quittime; //date,
    private Integer userid;//
}
