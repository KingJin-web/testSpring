package com.king.spring_rdering.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @program: testSpring
 * @description: 用户
 * @author: King
 * @create: 2021-04-29 18:40
 */
@Data
@Entity
public class ResUser implements Serializable {
    private static final long serialVersionUID = -3532352203559882495L;
    @Id
    private Integer userid;
    private String username;
    private String pwd;
    private String email;
}
