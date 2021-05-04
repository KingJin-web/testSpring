package com.king.spring_rdering.test;

import com.king.spring_rdering.bean.ResAdmin;
import com.king.spring_rdering.dao.ResAdminDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ResAdminDaoImplTest {
    @Autowired
    private ResAdminDao resAdminDao;

    @Test
    public void testFind() {
        System.out.println(resAdminDao.findById(1));
        ;
    }

    @Test
    public void testLogin() {
        ResAdmin admin = new ResAdmin();
        admin.setRaname("a");
        admin.setRapwd("a");
        System.out.println(resAdminDao.login(admin));
        ;
    }

    @Test
    public void testUpdate() {

        ResAdmin admin = new ResAdmin();
        admin.setRaid(4);
        admin.setRaname("蔡徐坤");
        admin.setRapwd("aaaaaa");
        System.out.println(resAdminDao.update(admin));
    }
}