package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserMapper userMapper;
    
    @Override
    public List<User> queryAll() {
        return userMapper.selectAll();
    }
    
    @Override
    public User queryById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
    
    @Override
    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public void saveRole(Map<String, Object> saveParams) {
        userMapper.saveRole(saveParams);
    }

}