package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author:
 * @date: created in 20:50 2020/8/10
 * @version:
 */
public class ForDemo {

    public static Boolean isNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "bilibili", "of", "codesheep", "5", "at", "BILIBILI", "codesheep",
            "23", "CHEERS", "6");
        // String finalResult = getResult(list);
        String finalResult = list.stream()// 首先将列表转化为Stream流
            .filter(s -> !isNum(s))// 首先筛选出字母型字符串
            .filter(s -> s.length() >= 5)// 其次筛选出长度>=5的字符串
            .map(s -> s.toLowerCase())// 字符串统一转小写
            // .sorted(String::compareTo)
            .distinct()// 去重
            .sorted(Comparator.naturalOrder()).collect(Collectors.joining("❤"));
        System.out.println(finalResult);
    }

    private static String getResult(List<String> list) {
        // 先定义一个具备按字母排序功能的Set容器，Set本身即可去重
        Set<String> stringSet = new TreeSet<>(String::compareTo);
        // 以下for循环完成元素去重、大小写转换、长度判断等操作
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.length() >= 5 && !isNum(s)) {
                String sLower = s.toLowerCase();
                stringSet.add(sLower);
            }
        }
        // 以下for循环完成连词成句
        StringBuilder result = new StringBuilder();
        for (String s : stringSet) {
            result.append(s);
            result.append("❤");
        }
        // 去掉最后一个多余连接符
        return result.substring(0, result.length() - 1).toString();
    }
}