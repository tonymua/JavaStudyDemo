package com.demo.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * @author:
 * @date: created in 12:47 2020/7/29
 * @version:
 */
@Data
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String name;
    private Integer age;
}