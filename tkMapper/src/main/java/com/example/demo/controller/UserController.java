package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

import oracle.jdbc.proxy.annotation.Post;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("queryAll")
    public String queryAll() {
        return userService.queryAll().toString();
    }

    // @PathVariable常用于Get Delete http://localhost:8080/user/queryById/2
    @GetMapping("queryById/{id}")
    public String queryById(@PathVariable("id") String id) {
        return userService.queryById(id).toString();
    }

    @GetMapping("selectAll")
    public String selectAll() {
        return userService.selectAll().toString();
    }

    // @RequestParam常用与Post http://localhost:8080/user/selectById?id=1
    @GetMapping("selectById")
    public String selectById(@RequestParam("id") String id) {
        return userService.selectById(id).toString();
    }

    @PostMapping("saveUser")
    public void saveUser(@RequestBody @Valid User user,
        @RequestParam(name = "roleIds", required = false) Integer[] roleIds) {
        for (Integer roleId : roleIds) {
            if (roleService.queryRole(roleId) == null) {
                throw new RuntimeException("选择的角色不存在！");
            }
        }
        userService.saveUser(user);
        Map<String, Object> saveParams = new HashMap<>();
        saveParams.put("user_id", user.getId());
        saveParams.put("roleIds", roleIds);
        userService.saveRole(saveParams);
    }

}