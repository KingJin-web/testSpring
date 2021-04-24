package com.king.springboot.springboot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/a")
    public String hello(@RequestParam(value = "name", defaultValue = "小宝贝") String name) {
        log.info("info");
        log.debug("debug");
        log.error("error");
        log.fatal("fatal");

        return String.format("Hello %s!", name);
    }
}
