package com.king;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-09 19:34
 */
@Aspect
@Component //要托管给 spring
public class LogAspect implements InvocationHandler {

    private Object target;

    public LogAspect(Object target) {
        this.target = target;
    }

    public Object createProxy() {
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类对象" + proxy.getClass());
        System.out.println("目标类方法" + method);
        System.out.println("方法中参数" + args);

        if (method.getName().startsWith("add")) {

            log();
        }

        return method.invoke(this.target, args);
    }

    private void log() {
        System.out.println("before");
        System.out.println("date :" + new Date());
        System.out.println("end");
    }
}
