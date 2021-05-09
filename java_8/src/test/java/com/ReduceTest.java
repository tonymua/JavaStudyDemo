package com;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

import com.sun.tools.javac.util.List;

/**
 * @author:
 * @date: created in 19:23 2020/8/6
 * @version:
 */
public class ReduceTest {

    @Test
    public void ReduceTest_01() {
        // 求和 初始值必须为0
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(0, (acc, n) -> acc + n);
        System.out.println(reduce);
    }

    @Test
    public void ReduceTest_02() {
        // 求积 初始值必须为1
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(1, (acc, n) -> acc * n);
        System.out.println(reduce);
    }


}