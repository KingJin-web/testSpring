package com.king.starter.testmysqlstarter;

import com.yc.mysql_connectionspringbootstarter.IDBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.Collection;

@SpringBootApplication
@RestController
public class TestmysqlstarterApplication {
    @Autowired
    private IDBHelper dbHelper;
    @GetMapping("/conn")
    public String testConn(){
        Connection connection = dbHelper.getConnection();
        System.out.println(connection.toString());
        return connection.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestmysqlstarterApplication.class, args);
    }


}
