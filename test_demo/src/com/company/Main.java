package com.company;

import com.company.domain.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        test_01();
    }

    private static void test_01(){
        String name[]={"甲","乙","丙","丁"};
        String age[]={"0","1","2","3"};
        HashMap<Student,Integer> map=new HashMap<>();
        Random random=new Random();
        for (int i = 0; i <100 ; i++) {
            Student student=new Student(name[random.nextInt(4)],age[random.nextInt(4)]);
            if (map.containsKey(student)){
                map.put(student,map.get(student)+1);
            }else {
                map.put(student,1);
            }
        }
        for (Map.Entry<Student, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey().toString()+":"+entry.getValue());
        }
//        System.out.println(map.toString());
    }
}
