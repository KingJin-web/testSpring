package com.king.springframework.context;

import com.king.MyAppConfig;
import com.king.springframework.stereotype.*;

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

    public MyAnnotationConfigApplicationContext(Class<?>... componentClasses) throws InvocationTargetException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

//        try {
        this.register(componentClasses);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

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
            System.out.println(" 扫描包路径" + Arrays.toString(basePackages));
            if (cl.isAnnotationPresent(MyComponentScan.class)) {
                MyComponentScan msc = (MyComponentScan) cl.getAnnotation(MyComponentScan.class);
                if (msc.basePackages() != null && msc.basePackages().length > 0) {
                    basePackages = msc.basePackages();

                }
                //处理 @MyBean的情况
                //cl -> AppConfig 对象
                Object obj = cl.newInstance();
                handleAtMyBean(cl, obj);

                for (String basePackage : basePackages) {
                    scanPackageAndSubPackageClasses(basePackage); //扫描所有的子包
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
                    invokeAutowiredMethod(method, obj);
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

    /*
    循环  beanMap中的每个bean , 找到它们每个类中的每个由@Autowired @Resource注解的方法以实现di,
     */
    private void handleDi(Map<String, Object> beanMap, int a) throws InvocationTargetException, IllegalAccessException {
        Collection<Object> objectCollection = beanMap.values();
        for (Object obj : objectCollection) {
            Class cls = obj.getClass();
            Method[] ms = cls.getDeclaredMethods();
            for (Method m : ms) {
                if (m.isAnnotationPresent(MyAutowired.class) && m.getName().startsWith("set")) {
                    invokeAutowiredMethod(m, obj);
                } else if (m.isAnnotationPresent(MyResource.class) && m.getName().startsWith("set")) {
                    invokeResourcedMethod(m, obj);

                }
            }
            Field[] fs = cls.getDeclaredFields();
            for (Field field : fs) {
                if (field.isAnnotationPresent(MyAutowired.class)) {

                } else if (field.isAnnotationPresent(MyResource.class)) {

                }
            }
        }

    }

    private void invokeAutowiredMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1. 取出  m的参数的类型
        //  StudentDao接口类型
        Class typeClass = m.getParameterTypes()[0];
        //2. 从beanMap中循环所有的object,
        Set<String> keys = beanMap.keySet();
        for (String key : keys) {
            // 4.  如果是，则从beanMap取出.
            Object o = beanMap.get(key);
            //3. 判断 这些object 是否为   参数类型的实例  instanceof
            Class[] interfaces = o.getClass().getInterfaces();
            for (Class c : interfaces) {
                System.out.println(c.getName() + "\t" + typeClass);
                if (c == typeClass) {
                    //5. invoke
                    m.invoke(obj, o);
                    break;
                }
            }
        }
    }

    private void invokeResourcedMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1. 取出  MyResource中的name属性值 ,当成   beanId
        MyResource mr = m.getAnnotation(MyResource.class);
        String beanId = mr.name();
        //2. 如果没有，则取出  m方法中参数的类型名, 改成首字小写   当成beanId
        if (beanId == null || beanId.equalsIgnoreCase("")) {
            String pname = m.getParameterTypes()[0].getSimpleName();
            beanId = pname.substring(0, 1).toLowerCase() + pname.substring(1);
        }
        //3. 从beanMap取出
        Object o = beanMap.get(beanId);
        //4. invoke
        m.invoke(obj, o);

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
        System.out.println("包路径" + basePackage + "替换后" + packagePath);
        Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
        //System.out.println(files.hasMoreElements());
        while (files.hasMoreElements()) {
            URL url = files.nextElement();
            System.out.println("配置的扫描路径为" + url.getFile());
            System.out.println("basePackage  " + basePackage + "1");
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
