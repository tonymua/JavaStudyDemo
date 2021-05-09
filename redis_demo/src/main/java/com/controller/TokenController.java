package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.TokenAnnotation;
import com.service.TokenService;

/**
 * @author:
 * @date: created in 22:49 2021/3/25
 * @version:
 */
@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @GetMapping("/get")
    public Object  getToken(){
        String token = tokenService.createToken();
        return token;
    }


    @TokenAnnotation
    @GetMapping("/check")
    public Object testIdempotence() {
        String token = "SUCCESS!";
        return token ;
    }
}