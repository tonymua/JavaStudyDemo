package com.example.demo.service;

import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //查询所有用户
    List<User> queryAll();

    //根据id查询用户
    User queryById(String id);

    List<User> selectAll();

    User selectById(String id);

    void saveUser(User user);
    void saveRole(Map<String, Object> saveParams);
}