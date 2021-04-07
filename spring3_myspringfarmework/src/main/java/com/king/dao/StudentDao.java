package com.king.dao;

import com.king.springframework.stereotype.MyComponent;
import org.springframework.stereotype.Component;

/**
 * @program: maven_1
 * @description:
 * @author: King
 * @create: 2021-04-04 14:18
 */

public interface StudentDao {
    public int add(String name);

    public void update(String name);


}
