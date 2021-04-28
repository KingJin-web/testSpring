package com.yc.tx.dao;

import com.yc.tx.bean.OpRecord;

import java.util.List;

public interface OpRecordDao {

    /**
     * 记录日志
     *
     * @param opRecord
     */
    public void saveOpRecord(OpRecord opRecord);

    /**
     * 查询所有日志
     *
     * @return
     */
    public List<OpRecord> findAll();

    /**
     * 查询对应id的日志
     *
     * @param accountid
     * @return
     */
    public List<OpRecord> findByAccountid(int accountid);

}
