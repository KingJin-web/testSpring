package com.king;

import com.king.bean.HelloWorld;
import com.king.springframework.stereotype.MyBean;
import com.king.springframework.stereotype.MyComponentScan;
import com.king.springframework.stereotype.MyConfiguration;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 11:47
 */
@MyConfiguration
@MyComponentScan(basePackages = {"com.king.tx.dao", "com.king.biz","com.king.bean"})
public class MyAppConfig {

    @MyBean
    public HelloWorld hw() {
        return new HelloWorld();
    }
}
