package com.king.spring_rdering.dao;

import com.king.spring_rdering.bean.ResAdmin;
import com.king.spring_rdering.bean.ResUser;
import com.king.spring_rdering.mapper.ResUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

/**
 * @program: testSpring
 * @description: 用户信息实现
 * @author: King
 * @create: 2021-05-04 14:52
 */
@Repository
public class ResUserDaoImpl implements ResUserDao {
    @Autowired
    private ResUserRepository resUserRepository;

    private Example<ResUser> em;

    @Override
    public boolean login(ResUser resUser) {
        em = Example.of(resUser);
        return resUserRepository.findAll(em).size() == 1;
    }

    @Override
    public ResUser update(ResUser resUser) {
        return resUserRepository.save(resUser);
    }
}
