package com.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * @author:
 * @date: created in 11:44 2020/8/6
 * @version:
 */
public class LambdaTest {

    @Test
    public void CompareTest() {
        String[] array = new String[] {"Apple", "Orange", "Banana", "Lemon"};
        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(String.join(",", array));
    }

    @Test
    public void CompareTest_01() {
        String[] array = new String[] {"Apple", "Orange", "Banana", "Lemon"};
        Arrays.sort(array, (s1, s2) -> {
            return s1.compareTo(s2);
        });
        System.out.println(String.join(",", array));
    }

    @Test
    public void CompareTest_02() {
        String[] array = new String[] {"Apple", "Orange", "Banana", "Lemon"};
        Arrays.sort(array, (s1, s2) -> s1.compareTo(s2));
        System.out.println(String.join(",", array));
    }

    @Test
    public void CompareTest_03(){
        String[] array = new String[] { "apple", "Orange", "Banana", "lemon" };
        Arrays.sort(array,(s1,s2)->s1.compareToIgnoreCase(s2));
        System.out.println(String.join(",",array));
    }

    @Test
    public void CompareTest_04(){
        String[] array = new String[] { "apple", "Orange", "Banana", "lemon" };
        Arrays.sort(array,LambdaTest::compare);
        System.out.println(String.join(",",array));
    }

    @Test
    public void CompareTest_05(){
        String[] array = new String[] { "apple", "Orange", "Banana", "lemon" };
        Arrays.sort(array,String::compareToIgnoreCase);
        System.out.println(String.join(",",array));
    }

    private static int compare(String s1, String s2){
        return s1.compareToIgnoreCase(s2);
    }
    class Person{
        String name;
        public Person(String name) {
            this.name = name;
        }
    }
    @Test
    public void Test_06(){
        List<String> names= Arrays.asList(new String[]{"Bob", "Alice", "Time"});
        List<Person> persons=new ArrayList<>();
        for (String name : names) {
            persons.add(new Person(name));
        }
        System.out.println(persons);
    }

    @Test
    public void Test_07(){
        List<String> names= Arrays.asList(new String[]{"Bob", "Alice", "Time"});
        List<Person> persons = names.stream().map(Person::new).collect(Collectors.toList());
    }
}