package com.example.service.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.service.ExpiredService;

@Service
public class ExpiredServiceImpl implements ExpiredService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private Random random = new Random(System.currentTimeMillis());
    
    @Override
    public void expiredTimeMethod() {
        logger.info("---SomeService: someMethod invoked---");
        try {
            //模拟耗时任务
            Thread.sleep(random.nextInt(500));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}