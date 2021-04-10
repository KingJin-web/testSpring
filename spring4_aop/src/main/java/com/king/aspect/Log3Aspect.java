package com.king.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect//切面类:你要增强的功能写在这里
@Component//IOC 注解,以实现让spring托管的功能
@Order(value = 1)
public class Log3Aspect {

    @Around("execution(* com.king.biz.StudentBizImpl.find*(..))")
    public Object compute(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("**********compute2进入了   增强。。。");
        long start = System.currentTimeMillis();
        Object retVal=pjp.proceed();
        long end=System.currentTimeMillis();
        System.out.println("**********compute2退出增强了。。。");
        System.out.println("时长为"+(end-start));
        return retVal;
    }

}
