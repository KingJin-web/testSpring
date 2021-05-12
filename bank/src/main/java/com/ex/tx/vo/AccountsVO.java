package com.ex.tx.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountsVO implements Serializable {

    private static final long serialVersionUID = -8895742291346249579L;

    private Integer accountId;
    private Double money;
    private Integer inAccountId;

}
