package com.king.springframework.context;

import com.king.MyAppConfig;
import com.king.springframework.stereotype.MyBean;
import com.king.springframework.stereotype.MyComponentScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 11:41
 */
public class MyAnnotationConfigApplicationContext implements MyApplicationContext {
    Map<String, Object> beanMap = new HashMap<>();

    public MyAnnotationConfigApplicationContext(Class<?>... componentClasses) throws IllegalAccessException, InvocationTargetException, InstantiationException {

        this.register(componentClasses);
        this.refresh();
    }

    private void refresh() {
    }

    private void register(Class<?>[] componentClasses) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (componentClasses == null || componentClasses.length <= 0) {
            throw new RuntimeException("");
        }
        for (Class cl : componentClasses) {
            if (!cl.isAssignableFrom(MyAppConfig.class)) {
                continue;
            }
            String[] basePackage = getAppConfigBasePages(cl);
            if (cl.isAnnotationPresent(MyComponentScan.class)) {
                MyComponentScan msc = (MyComponentScan) cl.getAnnotation(MyComponentScan.class);
                if (msc.basePackages() != null && msc.basePackages().length > 0) {
                    basePackage = msc.basePackages();
                    System.out.println(" basePackage" + Arrays.toString(basePackage));
                }
                //处理 @MyBean的情况
                Object obj = cl.newInstance();
                handleAtMyBean(cl, obj);
            }


        }
    }

    private String[] getAppConfigBasePages(Class cl) {
        String[] paths = new String[1];
        paths[0] = cl.getPackage().getName();
        return paths;
    }

    private void handleAtMyBean(Class cls, Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[] ms = cls.getDeclaredMethods();

        for (Method method : ms) {
            if (method.isAnnotationPresent(MyBean.class)) {
                Object o = method.invoke(obj);
                beanMap.put(method.getName(), o);
            }
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
