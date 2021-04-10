package com.king.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

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
public class LogAspect {
    //切入点声明
    @Pointcut("execution(* com.king.biz.StudentBizImpl.add* (..))") // the pointcut expression
    private void add() {
    } // the pointcut signature

    @Pointcut("execution(* com.king.biz.StudentBizImpl.update* (..))") // the pointcut expression
    private void update() {
    } // the pointcut signature

    @Pointcut("add() || update()") // the pointcut expression
    private void addAndUpdate() {
    } // the pointcut signature


    /**
     * 前置增强
     */
    @Before("com.king.aspect.LogAspect.addAndUpdate()") // the pointcut expression
    private void log() {
        System.out.println("前置增强");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSSXXX");
        String d = simpleDateFormat.format(date);
        System.out.println("执行时间" + d);
        System.out.println("----------------log1--------------------");
        System.out.println("===结束====");
    }

    /**
     * 后置增强
     * After returning advice runs when a matched method execution returns normally. You can declare it by using the
     *
     * @AfterReturning("com.xyz.myapp.CommonPointcuts.dataAccessOperation()") public void doAccessCheck() {
     * // ...
     * }
     */
    @AfterReturning("com.king.aspect.LogAspect.addAndUpdate()")
    private void bye() {
        System.out.println("bye");
    }

    /**
     * @return
     * @Around("com.xyz.myapp.CommonPointcuts.businessService()") public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
     * // start stopwatch
     * Object retVal = pjp.proceed();
     * // stop stopwatch
     * return retVal;
     * }
     */
    @Around("execution(* com.king.biz.StudentBizImpl.find* (..))")
    private Object compute(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("**********compute2开始    增强了。。。");

        long start = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long end = System.currentTimeMillis();

        System.out.println("运行时长" + (end - start));
        System.out.println("**********compute2退出增强了。。。");
        return retVal;

    }

}
