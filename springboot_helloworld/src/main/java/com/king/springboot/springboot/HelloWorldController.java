package com.king.springboot.springboot;

import com.king.springboot.springboot.properties.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-18 10:55
 */
@SpringBootApplication
@RestController
public class HelloWorldController {
    //创建日志对象
    private static Log log = LogFactory.getLog(HelloWorldController.class);
    @Autowired
    private Environment environment;

    @Value("${king.tname}")
    private String tname;

    @Autowired
    private Properties properties;

    @GetMapping("/a")
    public String hello(@RequestParam(value = "name", defaultValue = "小宝贝") String name) {
        log.info("info" + environment);
        log.debug("debug");
        log.error("error");
        log.fatal("fatal");

        log.info("用户路径" + environment.getProperty("user.home"));
        log.info("java路径" + environment.getProperty("java.home"));
        log.info("java路径" + environment.getProperty("JAVA_HOME"));
        log.info("``````" + tname);
        log.info("properties:" + properties.getTname() + " " + properties.getAge());
        log.info("environment中是否也有:" + environment.getProperty("king.tname") + environment.getProperty("king.age"));
        return String.format("Hello %s!" + "\n%s", name, environment);
    }
}
