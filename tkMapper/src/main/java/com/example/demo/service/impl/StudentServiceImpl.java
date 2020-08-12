package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

/**
 * @author:
 * @date: created in 12:49 2020/7/29
 * @version:
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Student> queryAll() {
        return studentMapper.selectAll();
    }

    @Override
    public Student queryById(String id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveStudent(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void deleteStudent(String id) {
        studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Student> queryByPage(Integer start, Integer rows, Integer id, Integer age, String keyWord) {
        // Page<Student> page = null;
        if (start != null & rows != null) {
            // 分页
            if (start <= 1) {
                start = 1;
            }
            if (rows <= 0) {
                rows = 5;
            }
            PageHelper.startPage(start, rows);
        }
        /*Example example = Example.builder(Student.class).select()
            .where(Sqls.custom().andLike("name", "%" + keyWord + "%")).orderByAsc("name")
            .build();
        example.and().andEqualTo("age",age);*/

        /*// weekend动态sql
        Weekend weekend = new Weekend(Student.class);
        WeekendCriteria<Student,Object> criteria = weekend.weekendCriteria();
        if (id != null) {
            // 匹配id
            criteria.andEqualTo(Student::getId, id);
        }
        if (age != null) {
            // 匹配age
            criteria.andEqualTo(Student::getAge, age);
        }
        if (!StringUtils.isEmpty(keyWord)) {
            criteria.orLike(Student::getName, "%" + keyWord + "%");
            weekend.and(criteria);
        //            weekend.or(criteria);
        }*/
        Example example = new Example(Student.class);
        example.and().andLike("name", "%" + keyWord + "%").andEqualTo("id", id);
        example.orderBy("age").desc();
        List<Student> list = studentMapper.selectByExample(example);
        PageInfo<Student> studentPageInfo = new PageInfo<>(list);
        return studentPageInfo;
        /*
         * PageHelper.offsetPage(page, rows); List<Student> list = studentMapper.selectAll(); PageInfo<Student> studentPageInfo
         * = new PageInfo<>(list); return studentPageInfo.getList();
         */
        // return new PageInfo<>(this.queryAll()).getList();

    }
}