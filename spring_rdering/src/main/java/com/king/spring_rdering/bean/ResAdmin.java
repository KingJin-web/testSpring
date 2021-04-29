package com.king.spring_rdering.bean;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: testSpring
 * @description: resadmin
 * @author: King
 * @create: 2021-04-29 18:37
 */
@Data
@Entity
@Table(name="ResAdmin")
@Proxy(lazy = false)
public class ResAdmin implements Serializable {
    private static final long serialVersionUID = 3787802763096026770L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "raid")
    private Integer raid;
    @Column(name = "raname")
    private String raname;
    @Column(name = "rapwd")
    private String rapwd;

    @Override
    public String toString() {
        return "ResAdmin{" +
                "raid=" + raid +
                ", raname='" + raname + '\'' +
                ", rapwd='" + rapwd + '\'' +
                '}';
    }
}
