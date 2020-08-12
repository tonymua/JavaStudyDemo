package com.demo.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.demo.pojo.Student;

/**
 * @author:
 * @date: created in 9:32 2020/8/4
 * @version:
 */
public class StudentSerializable implements Serializer<Student> {

    @Override
    public byte[] serialize(String topic, Student student) {
        System.out.println("topic: " + topic + " student: " + student);
        byte[] dataArray = null;
        ByteArrayOutputStream byteOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteOutputStream);
            objectOutputStream.writeObject(student);
            dataArray = byteOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (byteOutputStream != null) {
                try {
                    byteOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataArray;
    }

}