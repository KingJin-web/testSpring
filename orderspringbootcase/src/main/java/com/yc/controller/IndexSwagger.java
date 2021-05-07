package com.yc.controller;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexSwagger {

    @GetMapping("/hello")
    @ApiOperation("这是测试swagger的get方法")
    public String test1(){
        return "hello swagger2";
    }

    @PostMapping("/h2")
    @ApiOperation("这是测试带参数的方法")
    public String test2(String name){
        return "hello swagger2"+name;
    }

    @GetMapping("/h3")
    @ApiOperation("这是测试500错误的方法")
    public String test3(){
        int i=1/0;
        return "hello swagger2";
    }


    @GetMapping("/h4")
    public  String test4(){
        return "redirect:/index.html";
    }

}
