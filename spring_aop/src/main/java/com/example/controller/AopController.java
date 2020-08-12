package com.example.controller;

import com.example.annotation.AuthChecker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop/http")
public class AopController {

    @GetMapping("alive")
    public String alive(){
        return "服务一切正常";
    }

    @AuthChecker
    @GetMapping("login")
    public String login(){
        return "登录成功！";
    }
}