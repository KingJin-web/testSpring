package com.example.tx.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-14 21:12
 */
@Data
public class OpRecord {
    private Integer id;
    private Integer accountId;
    private Double opmoney;
    private Timestamp optime;
    private String optype;
    private String transferid;
}
