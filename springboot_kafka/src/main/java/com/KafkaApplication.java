package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author:
 * @date: created in 20:12 2020/8/3
 * @version:
 */
@SpringBootApplication
@MapperScan(basePackages = "com.demo.mapper")
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class,args);
    }
}