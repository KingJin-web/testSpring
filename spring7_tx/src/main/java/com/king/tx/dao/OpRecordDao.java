package com.king.tx.dao;

import com.king.tx.bean.OpRecord;

import java.util.List;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-17 13:28
 */
public interface OpRecordDao {
    public int saveOpRecord(OpRecord opRecord);

    public List<OpRecord> findAll();

    public OpRecord findById(int id);

    public List<OpRecord> findById2(int id);

}
