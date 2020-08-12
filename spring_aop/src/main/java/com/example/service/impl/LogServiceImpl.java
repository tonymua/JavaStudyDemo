package com.example.service.impl;

import com.example.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LogServiceImpl implements LogService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public int LogMethod(String param) {
        logger.info("---LogService: logMethod invoked, param: {}---", param);
        return random.nextInt();
    }

    @Override
    public void exceptionMethod() throws Exception {
        logger.info("---LogService: exceptionMethod invoked---");
        throw new Exception("Something bad happened!");
    }
}