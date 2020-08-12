package com.demo.serializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.kafka.common.serialization.Deserializer;

import com.demo.pojo.Student;

/**
 * @author:
 * @date: created in 9:41 2020/8/4
 * @version:
 */
public class StudentDeserializer implements Deserializer<Student> {
    @Override
    public Student deserialize(String topic, byte[] bytes) {
        Student student = null;
        ByteArrayInputStream byteInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteInputStream);
            student = (Student)objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (byteInputStream != null) {
                try {
                    byteInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return student;
    }
}