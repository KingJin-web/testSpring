package com.king.spring_rdering.enums;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-04 19:21
 */
import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    NEW(0, "新订单"), FINISHED(1, "已完成"), CANCEL(2, "已取消");

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
