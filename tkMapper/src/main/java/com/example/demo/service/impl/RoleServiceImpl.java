package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.RoleMapper;
import com.example.demo.pojo.Role;
import com.example.demo.service.RoleService;

/**
 * @author:
 * @date: created in 16:38 2020/8/5
 * @version:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public String queryRole(Integer roleId) {
        return roleMapper.queryRole(roleId);
    }

    @Override
    public void addRole(Role role) {
        roleMapper.insert(role);
    }
}