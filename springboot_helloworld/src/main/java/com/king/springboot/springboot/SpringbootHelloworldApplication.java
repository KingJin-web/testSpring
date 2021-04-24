package com.king.springboot.springboot;

import com.king.springboot.springboot.properties.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController //@Controller 控制层 @Restful 以rest规范（ 请求方式
@EnableConfigurationProperties(Properties.class)
public class SpringbootHelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootHelloworldApplication.class, args);
    }

    AutoConfigurationImportSelector autoConfigurationImportSelector = new AutoConfigurationImportSelector();

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "小宝贝") String name) {
        return String.format("Hello %s!", name);
    }


}
// 2021-04-18 09:47:50.110  INFO 24616 --- [           main] c.k.s.s.SpringbootHelloworldApplication  : Starting SpringbootHelloworldApplication using Java 1.8.0_271 on DESKTOP-LCJBD6D with PID 24616 (H:\jetbrains\java\testSpring\springboot_helloworld\target\classes started by King in H:\jetbrains\java\testSpring)

