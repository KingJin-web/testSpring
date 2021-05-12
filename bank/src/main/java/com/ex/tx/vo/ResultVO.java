package com.ex.tx.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -5379353105073251953L;

    private Integer code;
    private T data;
    private String msg;
}
