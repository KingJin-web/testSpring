package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-24 20:46
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 5222448769463550222L;
    private Integer code;
    private T data;
    private String msg;
}
