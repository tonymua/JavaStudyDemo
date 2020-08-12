package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Role;
import com.example.demo.service.RoleService;

/**
 * @author:
 * @date: created in 16:36 2020/8/5
 * @version:
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("addRole")
    public void addRole(@RequestBody Role role){
        roleService.addRole(role);
    }

    @GetMapping("selectAll")
    public String selectAll() {
        return roleService.selectAll().toString();
    }
}