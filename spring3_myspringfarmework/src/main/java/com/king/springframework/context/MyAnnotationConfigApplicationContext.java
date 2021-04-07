package com.king.springframework.context;

import com.king.MyAppConfig;
import com.king.springframework.stereotype.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 11:41
 */
public class MyAnnotationConfigApplicationContext implements MyApplicationContext {
    Map<String, Object> beanMap = new HashMap<>();

    public MyAnnotationConfigApplicationContext(Class<?>... componentClasses) {

        try {
            this.register(componentClasses);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void refresh() {
    }


    private void register(Class<?>[] componentClasses) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException, ClassNotFoundException {
        if (componentClasses == null || componentClasses.length <= 0) {
            throw new RuntimeException("没有指定配置类");
        }

        for (Class cl : componentClasses) {
            //只实现 IOC MyPostConstruct MyPreDesTory
            if (!cl.isAnnotationPresent(MyConfiguration.class)) {
                 continue;
            }
            String[] basePackages = getAppConfigBasePages(cl);
            if (cl.isAnnotationPresent(MyComponentScan.class)) {
                MyComponentScan msc = (MyComponentScan) cl.getAnnotation(MyComponentScan.class);
                if (msc.basePackages() != null && msc.basePackages().length > 0) {
                    basePackages = msc.basePackages();
                    System.out.println(" 扫描包路径" + Arrays.toString(basePackages));
                }
                //处理 @MyBean的情况
                Object obj = cl.newInstance();
                handleAtMyBean(cl, obj);
                for (String basePackage : basePackages) {
                    scanPackageAndSubPackageClasses(basePackage);
                }
                //继续托管bean
                handleManagedBean();
                //版本二 循环 beanMap 中每一个bean 找到他们每个类中的每个@Autowired
                // @Resource 注解方法实现DI
                handleDi(beanMap);
            }


        }
    }


    private void handleDi(Map<String, Object> beanMap) throws InvocationTargetException, IllegalAccessException {
        Collection<Object> objectCollection = beanMap.values();
        for (Object obj : objectCollection) {
            Class cls = obj.getClass();
            Method[] methods = cls.getDeclaredMethods();

            System.out.println("methods :" + Arrays.toString(methods));
            for (Method method : methods) {
                if (method.isAnnotationPresent(MyAutowired.class) && method.getName().startsWith("set")) {
                    invokeResourcedMethod(method, obj);
                } else if (method.isAnnotationPresent(MyResource.class) && method.getName().startsWith("set")) {
                    invokeResourcedMethod(method, obj);
                }
            }

            Field[] fs = cls.getDeclaredFields();
            //  System.out.println("fs :" + Arrays.toString(fs));
            for (Field field : fs) {
                if (field.isAnnotationPresent(MyAutowired.class)) {

                } else if (field.isAnnotationPresent(MyResource.class)) {

                }
            }

        }
    }

    private void invokeResourcedMethod(Method method, Object obj) throws InvocationTargetException, IllegalAccessException {
        MyResource mr = method.getAnnotation(MyResource.class);

        String beanId = mr.name();
        if (beanId == null || beanId.equalsIgnoreCase("")) {
            String pName = method.getParameterTypes()[0].getSimpleName();
            System.out.println("name" + pName);
            beanId = pName.substring(0, 1).toLowerCase() + pName.substring(1);
            System.out.println("beanId" + beanId);

        }
        Object o = beanMap.get(beanId);
        method.invoke(o);

    }

    private Set<Class> manageBeanClasses = new HashSet<>();

    private void handleManagedBean() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        for (Class c : manageBeanClasses) {
            //判断有没有这个注解
            if (c.isAnnotationPresent(MyComponent.class)) {
                saveManagedBean(c);
            } else if (c.isAnnotationPresent(MyService.class)) {
                saveManagedBean(c);
            } else if (c.isAnnotationPresent(MyController.class)) {

            } else if (c.isAnnotationPresent(MyRepository.class)) {
                saveManagedBean(c);
            }
        }
    }

    private void saveManagedBean(Class c) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o = c.newInstance();
        handlePostConstruct(o, c);
        String beanId = c.getSimpleName().substring(0, 1).toLowerCase() + c.getSimpleName().substring(1);
        System.out.println("beanId" + beanId);
        beanMap.put(beanId, o);

    }

    /**
     * 处理 一个bean中的 @MyPostConstruct 对应方法
     *
     * @param o
     * @param c
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void handlePostConstruct(Object o, Class<?> c) throws InvocationTargetException, IllegalAccessException {
        Method[] ms = c.getDeclaredMethods();

        for (Method method : ms) {
            if (method.isAnnotationPresent(MyPostConstruct.class)) {
                method.invoke(o);
            }
        }

    }


    /**
     * 扫描 包和子包
     *
     * @param basePackage
     * @throws IOException
     */
    private void scanPackageAndSubPackageClasses(String basePackage) throws IOException, ClassNotFoundException {
        String packagePath = basePackage.replaceAll("\\.", "/");
        System.out.println("包路径" + basePackage);
        Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
        while (files.hasMoreElements()) {
            URL url = files.nextElement();
            System.out.println("配置的扫描路径为" + url.getFile());
            System.out.println("basePackage  " + basePackage);
            //TODO 递归这些目录 查找。class文件
            findClassesInPackages(url.getFile(), basePackage);

        }


    }

    /**
     * 查找file 下面以及子包所有要托管的class 存到set(manageBeanClasses)
     *
     * @param file
     * @param basePackage
     * @throws ClassNotFoundException
     */
    private void findClassesInPackages(String file, String basePackage) throws ClassNotFoundException {
        File f = new File(file);
        File[] classFiles = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().startsWith(".class") || pathname.isDirectory();
            }
        });

        for (File cf : classFiles) {
            if (cf.isDirectory()) {
                basePackage += "." + cf.getName().substring(cf.getName().lastIndexOf("/"));

                System.out.println(basePackage);
                findClassesInPackages(cf.getAbsolutePath(), basePackage);
            } else {
                URL[] urls = new URL[]{};
                URLClassLoader url = new URLClassLoader(urls);
                Class c = url.loadClass(basePackage + "." + cf.getName().replace(".class", ""));

                manageBeanClasses.add(c);


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
                handlePostConstruct(o, o.getClass());
                beanMap.put(method.getName(), o);
            }
        }
    }


    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }

    public Map<String, Object> getBeanMap() {

        return beanMap;
    }

    public Set<Class> getManageBeanClasses() {

        return manageBeanClasses;
    }
}
