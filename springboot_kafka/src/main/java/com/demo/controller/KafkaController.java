package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.pojo.Student;

/**
 * @author:
 * @date: created in 20:01 2020/8/3
 * @version:
 */

@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /*@GetMapping("send/{message}")
    public void send(@PathVariable(name = "message") String message) {
        kafkaTemplate.send("Hello-Kafka", message);
    }*/

    @PostMapping("student/save")
    public void save(@RequestBody Student student) {
        kafkaTemplate.send("topic01", student);
    }
}