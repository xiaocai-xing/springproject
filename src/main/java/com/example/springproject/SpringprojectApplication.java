package com.example.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringprojectApplication.class, args);

        System.out.println("项目启动成功!\n" +
                "开心快乐每一天\n" +
                "永不报错");
    }

}
