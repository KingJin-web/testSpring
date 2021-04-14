package com.king.tx.dao;

import org.springframework.stereotype.Component;

/**
 * @program: maven_1
 * @description:
 * @author: King
 * @create: 2021-04-04 14:18
 */
@Component
public interface StudentDao {
    public int add(String name);

    public void update(String name);


}
