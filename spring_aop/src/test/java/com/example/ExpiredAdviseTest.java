package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.service.ExpiredService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpiredAdviseTest {
    @Autowired
    private ExpiredService expiredService;
    
    @Test
    // @PostConstruct 被@PostConstruct修饰的方法会在bean创建完成的时候运行，并且只会被服务器执行一次。
    public void testExpiredTime() {
        expiredService.expiredTimeMethod();
    }
}