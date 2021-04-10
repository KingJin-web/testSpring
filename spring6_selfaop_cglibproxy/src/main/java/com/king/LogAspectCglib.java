package com.king;


import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-10 19:54
 */
public class LogAspectCglib implements MethodInterceptor {
    private Object target;

    public LogAspectCglib(Object target) {
        this.target = target;
    }

    public Object createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理类对象" + o.getClass().toString());
        System.out.println("目标类方法" + method);
        System.out.println("方法中参数" + objects);
        System.out.println("代理的方法" + methodProxy);

        if (method.getName().startsWith("add")) {

            log();
        }

        return method.invoke(this.target, objects);
    }

    private void log() {
        System.out.println("before");
        System.out.println("date :" + new Date());
        System.out.println("end");
    }
}
