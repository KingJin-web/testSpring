package com.king.starter.testmystarter;

import com.king.starter.hellostarter.services.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author King
 */
@SpringBootApplication
@RestController
public class TestmystarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestmystarterApplication.class, args);
    }

    //@Resource
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String say(){
        return helloService.say();
    }

}
