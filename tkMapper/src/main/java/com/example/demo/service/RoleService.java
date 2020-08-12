package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Role;

public interface RoleService {
    List<Role> selectAll();

    String queryRole(Integer roleId);

    void addRole(Role role);
}
