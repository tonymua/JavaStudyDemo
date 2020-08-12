package com.example.demo.mapper;

import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends Mapper<User> {
    @Override
    List<User> selectAll();

    User selectById(String id);

    void saveUser(User user);

    void saveRole(Map<String, Object> saveParams);
}
