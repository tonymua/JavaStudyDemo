package com.example.demo.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * @author:
 * @date: created in 12:47 2020/7/29
 * @version:
 */
@Data
@Table(name = "student")
public class Student {
    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    @NotNull(message = "传入的姓名为null，请传值")
    @NotEmpty(message = "传入的姓名为空字符串，请传值")
    private String name;
    @Min(value = 0, message = "传入的年龄有误")
    @Max(value = 200, message = "传入的年龄有误")
    private Integer age;
}