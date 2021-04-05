package com.test.d0404;

import com.AppConfig;
import com.huwei.bean.Container;
import com.mimi.bean.Person;
import com.mimi.bean.PersonBmiTool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @RunWith：用于指定junit运行环境，是junit提供给其他框架测试环境接口扩展，为了便于使用spring的依赖注入，spring提供了org.springframework.test.context.junit4.SpringJUnit4ClassRunner作为Junit测试环境
 * @ContextConfiguration({"classpath:applicationContext.xml","classpath:spring/buyer/applicationContext-service.xml"})
 * 导入配置文件，这里我的applicationContext配置文件是根据模块来分类的。如果有多个模块就引入多个“applicationContext-service.xml”文件。如果所有的都是写在“applicationContext.xml”中则这样导入：
 * @ContextConfiguration(locations = "classpath:applicationContext.xml")
 * @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
 * @Transactional:这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！
 *
 * AbstractTransactionalDataSourceSpringContextTests要想构建这一系列的无污染纯绿色事务测试框架就必须找
 *
 * 到这个基类！（即所有事务均不生效）
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ContainerTest2 {

    @Autowired
    private ApplicationContext ac;
    @Autowired
    private Container c;
    @Autowired
    private Random r;
    @Autowired
    private PersonBmiTool pbt;



    @Before
    public void setUp() throws Exception {
        Environment environment = ac.getEnvironment();
        System.out.println(environment.getProperty("user.home"));
        System.out.println(environment.getProperty("user.dir"));
//        ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        c = (Container) ac.getBean("container");
//        pbt = (PersonBmiTool) ac.getBean("personBmiTool");
//        r = (Random) ac.getBean("r");
//        c.setMeasurable(pbt);
    }

    @After
    public void tearDown() throws Exception {
        c.setMeasurable(pbt);   //   设置测量工具，并初始化所有的容器数据

        Person p1 = new Person("张三", 1.70, 80);
        Person p2 = new Person("李四", 1.70, 60);     //bmi最小
        Person p3 = new Person("王五", 1.60, 90);     // bmi值大
        Person p4 = new Person("赵六", 1.66, 90);
        Person p5 = new Person("田七", 1.65, 90);
        Person p6 = new Person("王八", 1.67, 190);

        c.save(p1);
        c.save(p2);
        c.save(p3);
        c.save(p4);
        c.save(p5);
        c.save(p6);


        for (int i = 0; i < 1000; i++) {
            //  Math.random()   生成 0-1之间的小数
            Person p7 = new Person("王八" + i, 1 + Math.random(), r.nextInt(80) + 30);
            c.save(p7);
        }

        Person max=(Person)c.getMax();   //取最大值
        Person min=(Person)c.getMin();   //最最小值

        System.out.println("最大值:"+  max.getName() );
        System.out.println("最小值:"+ min.getName());

        System.out.println("平均bmi:"+ c.getAvg() );

        Object[] objs=c.getObjs();
        for(   Object o: objs ){
            Person pp=(Person)o;
            System.out.println(    pp.getName()+"\t"+pp.getHeight()+"\t"+pp.getWeight() +"\t"+   pbt.measure(   pp  )  );
        }

    }

    @Test
    public void getObjvalue() {
    }

    @Test
    public void getObjs() {
    }








    Map<String,Object> map = new HashMap<String, Object>();

    List<Map<String, Object>> list = new ArrayList<>();
    @Test
    public void setMap() {
        int a[] = new int[]{1,2,3};
        map.put("1",1);
        map.put("2","b");
        map.put("3",Arrays.toString(a));

        list.add(map);
        list.add(map);
        System.out.println(map);
        System.out.println(list);


    }

}