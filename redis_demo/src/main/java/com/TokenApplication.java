package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author:
 * @date: created in 22:53 2021/3/25
 * @version:
 */
@SpringBootApplication
@EnableCaching
public class TokenApplication {
    public static void main(String[] args) {
        SpringApplication.run(TokenApplication.class);
    }
}