package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:
 * @date: created in 17:10 2021/3/11
 * @version:
 */
public class Test4 {
    public static void main(String[] args) {
        Pattern DATE_PATTERN = Pattern.compile("(?<=_).*?(?=_F|_I|_D)");
        Matcher matcher = DATE_PATTERN.matcher("PFA2_201809062200_X.zip");
        System.out.println(matcher.find());
    }
}