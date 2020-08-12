package com.example;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.service.LogService;
import com.example.service.NormalService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogAdviseTest {
    
    @Autowired
    private LogService logService;

    @Autowired
    private NormalService normalService;
    
    @Test
    @PostConstruct
    public void testLogAdvise(){
        logService.LogMethod("LogMethod Test!");
        try {
            logService.exceptionMethod();
        }catch (Exception e){
            
        }
        normalService.normalMethod();
    }
}