package com.king.tx.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-14 21:13
 */
@Data
public class OpRecord {
    private Integer id;
    private Integer accountid;
    private Double opmoney;
    private Timestamp optime;
    private String optype;

    private String transferid;

    public OpRecord( Integer accountid, Double opmoney, Timestamp optime, String optype, String transferid) {

        this.accountid = accountid;
        this.opmoney = opmoney;
        this.optime = optime;
        this.optype = optype;
        this.transferid = transferid;
    }

    public OpRecord() {
    }
}
