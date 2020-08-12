package com.example.demo.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * @author:
 * @date: created in 14:54 2020/8/5
 * @version:
 */

@Data
@Table(name = "user")
@Entity
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @NotNull(message = "传入的姓名为null，请传值")
    @NotEmpty(message = "传入的姓名为空字符串，请传值")
    private String username;
    @Length(min = 5,max = 15,message = "密码不能短于5位")
    private String password;

    @ManyToMany
    private List<Role> roles;

}