package com.example.tx.dao;

import com.example.tx.bean.OpRecord;

import java.util.List;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-14 21:11
 */
public interface OpRecordDao{

    void saveOpRecord(OpRecord opRecord);

    List<OpRecord> findAll() ;

    List<OpRecord> findByAccountid(int accountid);
}
