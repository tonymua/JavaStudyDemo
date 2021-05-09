package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.StudentMapper;
import com.pojo.Student;
import com.service.StudentService;

/**
 * @author:
 * @date: created in 10:18 2020/8/4
 * @version:
 */

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void saveStudent(Student student) {
        studentMapper.insert(student);
    }
}