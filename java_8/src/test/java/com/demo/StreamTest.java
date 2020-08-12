package com.demo;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.sun.tools.javac.util.List;

/**
 * @author:
 * @date: created in 14:14 2020/8/6
 * @version:
 */
public class StreamTest {

    @Test
    public void StreamTest_01() {
        Stream<BigInteger> naturals = createNaturalStream();
        naturals.map(n -> n.multiply(n)).limit(100).forEach(System.out::println);
    }

    private Stream<BigInteger> createNaturalStream() {
        return null;
    }

    @Test
    public void StreamTest_02() {
        Stream<String> stringStream = Stream.of("A", "B", "C", "D");
        // forEach()相当于内部循环调用
        // 可传入符合Consumer接口的void accept(T t)的方法引用
        stringStream.forEach(System.out::println);
    }

    @Test
    public void StreamTest_03() {
        Stream<String> stream1 = Arrays.stream(new String[] {"A", "B", "C", "D"});
        // Stream<String> stream2 = Arrays.asList(new String[] {"A", "B", "C", "D"}).stream();
        Stream<String> stream2 = List.of("A", "B", "C", "D").stream();
        stream1.forEach(System.out::println);
        stream2.forEach(System.out::println);
    }

    @Test
    public void StreamTest_04() {
        Stream<Integer> natual = Stream.generate(new NatualSupplier());
        natual.limit(10).forEach(System.out::println);
    }

    class NatualSupplier implements Supplier<Integer> {
        int n = 0;

        @Override
        public Integer get() {
            n++;
            return n;
        }
    }

    @Test
    public void StreamTest_05() {
        // 将int[]数组变为IntStream
        IntStream intStream = Arrays.stream(new int[] {1, 2, 3});
        // 将Stream<String>转换为LongStream
        LongStream longStream = Arrays.asList(new String[] {"1", "2", "3"}).stream().mapToLong(Long::parseLong);
    }

    @Test
    public void StreamTest_06() {
        LongStream longStream = LongStream.generate(new FibonacciSupplier());
        longStream.limit(20).forEach(System.out::println);
    }

    class FibonacciSupplier implements LongSupplier {
        Long n1 = 0L;
        Long n2 = 1L;

        @Override
        public long getAsLong() {
            Long next = n1 + n2;
            n1 = n2;
            n2 = next;
            return n1;
        }
    }

    @Test
    public void MapTest_01() {
        List.of("  Apple ", " pear ", " ORANGE", " BaNaNa ").stream().map(String::trim)// 去空格
            .map(String::toLowerCase)// 变小写
            .sorted(String::compareTo)// 排序
            .forEach(System.out::println);
    }

    @Test
    public void MapTest_02() {
        String[] array =
            new String[] {" 2019-12-31 ", "2018 - 01-09 ", "2020- 05 - 01 ", "2022 - 02 - 01", " 2025-01 -01"};
        /*Arrays.stream(array).map(s -> s.replaceAll("\\s+", "")).map(LocalDate::parse)//\s+表示多个空格
            .forEach(System.out::println);*/
        Arrays.stream(array).map(s -> s.replaceAll("\\s+", ""))
            .map(s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"))).forEach(System.out::println);
    }

    @Test
    public void FilterTest_01() {
        IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter(n -> n % 2 != 0).forEach(System.out::println);
    }

    @Test
    public void FilterTest_02() {
        Stream.generate(new LocalDateSupplier()).limit(365).filter(
            localDate -> localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY)
            .forEach(System.out::println);
                /*localDate -> localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY)
                .count();
        System.out.println(count);*/
    }

    class LocalDateSupplier implements Supplier<LocalDate> {
        LocalDate start = LocalDate.of(2020, 1, 1);
        int n = -1;

        @Override
        public LocalDate get() {
            n++;
            return start.plusDays(n);
        }
    }

    @Test
    public void FilterTest_03() {
        java.util.List<Person> persons = Arrays.asList(new Person("小明", 88), new Person("小黑", 62), new Person("小白", 45),
            new Person("小黄", 78), new Person("小红", 99), new Person("小林", 58));
        persons.stream().filter(person -> person.score >= 60).map(person -> person.name).forEach(System.out::println);
    }

    class Person {
        String name;
        int score;

        public Person(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}