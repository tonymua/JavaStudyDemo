package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Role;

@Repository
public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectAll();

    String queryRole(Integer roleId);

}