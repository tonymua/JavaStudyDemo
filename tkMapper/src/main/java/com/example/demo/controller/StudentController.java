package com.example.demo.controller;

import com.example.demo.pojo.Student;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.StudentService;

import javax.smartcardio.Card;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("student")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("queryAll")
    public String queryAll() {
        return studentService.queryAll().toString();
    }

    @GetMapping("queryById/{id}")
    public String queryById(@PathVariable("id") String id) {
        return studentService.queryById(id).toString();
    }

    @PostMapping("save")
    public void saveStudent(@RequestBody @Valid Student student) {
        if (student.getId() != null) {
            studentService.updateStudent(student);
        } else {
            studentService.saveStudent(student);
        }
    }

    @GetMapping("delete/{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        studentService.deleteStudent(id);
    }

    /*
     * @GetMapping("queryByPage/{start}/{rows}") public String queryByPage(@PathVariable("start") Integer start,
     */
    @ApiOperation(value = "分页查询", notes = "分页查询，id，age，keyWord")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "start", value = "分页结果起始页", dataType = "Integer"),
        @ApiImplicitParam(paramType = "query", name = "rows", value = "每页行数", dataType = "Integer"),
        @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "Integer"),
        @ApiImplicitParam(paramType = "query", name = "age", value = "age", dataType = "Integer"),
        @ApiImplicitParam(paramType = "query", name = "keyWord", value = "名字中关键字", dataType = "String")})
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = Student.class, responseContainer = "List")})
    @GetMapping("queryByPage")
    public String queryByPage(@RequestParam(name = "start", required = false) Integer start,
        @RequestParam(name = "rows", required = false) Integer rows,
        @RequestParam(name = "id", required = false) Integer id,
        @RequestParam(name = "age", required = false) Integer age,
        @RequestParam(name = "keyWord", required = false) String keyWord) {
        PageInfo<Student> studentPageInfo = studentService.queryByPage(start, rows, id, age, keyWord);
        long total = studentPageInfo.getTotal();
        int pages = studentPageInfo.getPages();
        List<Student> list = studentPageInfo.getList();
        StringBuilder sb = new StringBuilder();
        for (Student student : list) {
            sb.append(student + "\n");
        }
        sb.append("总条数:" + total + "\n" + "总页数:" + pages);
        return sb.toString();
    }
}