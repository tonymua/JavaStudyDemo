package com.example.demo.pojo;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "role")
public class Role {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String name;

    @ManyToMany
    private List<User> users;

}