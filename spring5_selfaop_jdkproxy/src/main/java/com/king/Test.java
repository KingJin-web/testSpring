package com.king;

import com.king.biz.StudentBiz;
import com.king.biz.StudentBizImpl;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-12 13:32
 */
public class Test {
    private static StudentBiz studentBiz = new StudentBizImpl();
    private static LogAspect logAspect = new LogAspect(studentBiz);
    public static void main(String[] args) {
        Object obj = logAspect.createProxy();
        System.out.println(obj);
        if (obj instanceof StudentBiz){


            StudentBiz s = (StudentBiz) obj;
            s.find("张三强");
            s.add("张三强");
            s.update("张三强");
        }
    }
}
