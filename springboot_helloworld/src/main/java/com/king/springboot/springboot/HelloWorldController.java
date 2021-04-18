package com.king.springboot.springboot;

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
    @GetMapping("/a")
    public String hello(@RequestParam(value = "name", defaultValue = "小宝贝") String name) {
        return String.format("Hello %s!", name);
    }
}
