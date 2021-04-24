package com.yc.dao;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.OpRecordDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-17 14:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class })
public class TestOprecordDao {
    @Autowired
    private OpRecordDao accountDao;

    @Test
    public void testSave(){
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountId(1);
        opRecord.setOpmoney(1.0);
        opRecord.setOptype(OpTypes.deposite.getName());
        opRecord.setOptime(new Timestamp(new Date().getTime()));
        opRecord.setTransferid(" ");
        accountDao.saveOpRecord(opRecord);
    }

    @Test
    public void testAll(){
        List<OpRecord> list=accountDao.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void testByAccountId(){
        List<OpRecord> list=accountDao.findByAccountid(1);
        Assert.assertNotEquals(0,list.size());
    }

}
