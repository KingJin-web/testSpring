package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-24 20:44
 */
@Data
public class AccountVO implements Serializable {
    private static final long serialVersionUID = -7434767872348659321L;
    //private static final long
    private Integer accountId;
    private Double money;
    private Integer inAccountId;
}
