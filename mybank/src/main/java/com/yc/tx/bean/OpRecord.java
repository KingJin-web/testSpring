package com.yc.tx.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author King
 */
@Data
public class OpRecord {
    private Integer id;
    private Integer accountid;
    private Double opmoney;
    private Timestamp optime;//TODO:
    private String optype;//TODO:
    private String transferid;
}
