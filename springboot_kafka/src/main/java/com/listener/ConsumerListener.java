package com.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pojo.Student;
import com.service.StudentService;

/**
 * @author:
 * @date: created in 20:09 2020/8/3
 * @version:
 */
@Component
public class ConsumerListener {
    @Autowired
    private StudentService studentService;

    /*@KafkaListener(topics = "Hello-Kafka")
    public void solveMessage(String message) {
        System.out.println(message);
    }*/

    @KafkaListener(topics = "topic01")
    public void save(Student student){
        //入库方法
        studentService.saveStudent(student);
        System.out.println(student.toString());
    }
}