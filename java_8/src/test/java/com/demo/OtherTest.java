package com.demo;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.sun.tools.javac.util.List;

/**
 * @author:
 * @date: created in 20:17 2020/8/6
 * @version:
 */
public class OtherTest {

    @Test
    public void OtherTest_01() {
        java.util.List<String> list = List.of("Orange", "apple", "Banana").stream().sorted(String::compareToIgnoreCase)
            .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void OtherTest_02() {
        java.util.List<String> list =
            List.of("A", "B", "A", "C", "B", "D").stream().distinct().collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void OtherTest_03() {
        java.util.List<String> list = List.of("A", "B", "C", "D", "E", "F").stream().skip(2)// 跳过A,B
            .limit(3)// 截取C,D,E
            .collect(Collectors.toList());
        System.out.println(list);// [C, D, E]
    }

    @Test
    public void OtherTest_04() {
        Stream<java.util.List<Integer>> stream =
            Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9));
        // Stream<Integer> integerStream = stream.flatMap(Collection::stream);
        Stream<Integer> integerStream = stream.flatMap(list -> list.stream());
    }

    @Test
    public void OtherTest_05() {
        Stream<String> stream = Stream.of("Orange", "apple", "Banana");
        String[] result = stream.parallel()// 变成一个可以并行处理的Stream
            .sorted(String::compareToIgnoreCase)// 可以进行并行排序
            .toArray(String[]::new);
        System.out.println(Arrays.toString(result));
    }
}