package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Student;
import com.github.pagehelper.PageInfo;

/**
 * @author:
 * @date: created in 12:49 2020/7/29
 * @version:
 */
public interface StudentService {
    // 查询所有
    List<Student> queryAll();
    
    // 根据id查询
    Student queryById(String id);
    
    void saveStudent(Student student);
    
    void updateStudent(Student student);
    
    // 根据id删除
    void deleteStudent(String id);
    
    PageInfo<Student> queryByPage(Integer start, Integer rows, Integer id, Integer age, String keyWord);
}