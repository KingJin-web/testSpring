package com.king.spring_rdering.dao;

import com.king.spring_rdering.bean.ResAdmin;
import com.king.spring_rdering.mapper.ResAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-29 19:39
 */
@Repository
public class ResAdminDaoImpl implements ResAdminDao {

    @Autowired
    private ResAdminRepository resAdminRepository;

    @Override
    public ResAdmin findById(int id) {


        ResAdmin resAdmin = new ResAdmin();

        resAdmin.setRaname("蔡徐坤");
        resAdmin.setRapwd("aaaa");
        return resAdminRepository.saveAndFlush(resAdmin);

    }
}
