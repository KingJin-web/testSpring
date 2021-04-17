package com.king.test;

import com.king.tx.AppConfig;
import com.king.tx.bean.OpRecord;
import com.king.tx.bean.Optypes;


import com.king.tx.dao.OpRecordDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class OpRecordDaoImplTest {

    @Autowired
    private OpRecordDaoImpl opRecordDao;

    @Test
    public void init() {
    }

    @Test
    public void saveOpRecord() {
        Timestamp Time = new Timestamp(new Date().getTime());

        OpRecord opRecord = new OpRecord(4,18.3, Time, Optypes.withdraw.getName()," ");
        opRecordDao.saveOpRecord(opRecord);
    }

    @Test
    public void findAll() {
        System.out.println(opRecordDao.findAll());
    }

    @Test
    public void findById() {
        System.out.println(opRecordDao.findById(2));
    }
}