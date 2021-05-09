package com;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * @author:
 * @date: created in 19:47 2020/8/6
 * @version:
 */
public class CollectionTest {

    @Test
    public void CollectionTest_01() {
        Stream<String> stream = Stream.of("Apple", "", null, "Pear", "  ", "Orange");
        List<String> list = stream.filter(s -> s != null && s.trim().length() != 0).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void CollectionTest_02() {
        Stream<String> stream = Stream.of("APPL:Apple", "MSFT:Microsoft");
        Map<String, String> map = stream.collect(Collectors.toMap(
            // 把元素s映射为key
            s -> s.substring(0, s.indexOf(":")),
            // 把元素s映射为value s.indexOf(":")获取:索引所在的位置
            s -> s.substring(s.indexOf(":") + 1)));
        System.out.println(map);
    }

    @Test
    public void CollectionTest_03() {
        com.sun.tools.javac.util.List<String> list = com.sun.tools.javac.util.List.of("Apple", "Banana", "Blackberry",
            "Coconut", "Avocado", "Cherry", "Apricots");
        Map<String, List<String>> groups =
            list.stream().collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList()));
        System.out.println(groups);
    }

    @Test
    public void CollectionTest_04() {
        Stream<Student> studentStream =
            Stream.of(new Student(2, 3, "小明", 80), new Student(3, 1, "小王", 90), new Student(1, 2, "小强", 100),
                new Student(3, 1, "小红", 90), new Student(1, 2, "小黄", 100), new Student(2, 3, "小黑", 80),
                new Student(1, 2, "小军", 100), new Student(2, 3, "小乔", 80), new Student(3, 1, "小林", 90));
        Map<Integer, Map<Integer, Map<Integer, List<Student>>>> group = studentStream.collect(
                Collectors.groupingBy(s -> s.gradeId,
                        Collectors.groupingBy(s -> s.classId,
                                Collectors.groupingBy(s -> s.score)))
        );
        System.out.println(group.toString());
    }


    class Student {
        int gradeId; // 年级

        int classId; // 班级

        String name; // 名字

        int score; // 分数

        public Student(int gradeId, int classId, String name, int score) {
            this.gradeId = gradeId;
            this.classId = classId;
            this.name = name;
            this.score = score;
        }
    }
}