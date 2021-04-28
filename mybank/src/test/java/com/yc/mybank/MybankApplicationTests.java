package com.yc.mybank;

import com.yc.tx.dao.OpRecordDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybankApplicationTests {
    @Autowired
    OpRecordDaoImpl opRecordDao ;
    @Test
    void contextLoads() {

        System.out.println(opRecordDao.findAll());;
    }

}
