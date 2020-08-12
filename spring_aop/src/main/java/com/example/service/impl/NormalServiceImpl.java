package com.example.service.impl;

import com.example.service.NormalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NormalServiceImpl implements NormalService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void normalMethod() {
        logger.info("---NormalService: someMethod invoked---");
    }
}