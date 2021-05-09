package com.example;

import java.util.Collection;

/**
 * @author:
 * @date: created in 16:07 2020/11/6
 * @version:
 */
public class test {
    public static void main(String[] args) {
        boolean multipleOfX = isMultipleOfX(0, 100);
        System.out.println(multipleOfX);
        System.out.println(0%100);
    }

    public static Double getNumber(Object obj) {
        double item = -1D;
        if (obj == null)
            return item;
        if (obj instanceof Number) {
            item = ((Number)obj).doubleValue();
        } else if (obj instanceof Collection) {
            item = ((Collection)obj).size();
        } else if (obj instanceof String) {
            String str = (String)obj;
            if (cn.com.bsfit.sd.util.pattern.CommonPattern.NUMBER_PATTERN.matcher(str).matches()) {
                item = Double.parseDouble(str);
            }
            /*if (str.matches("^[+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?$"))
            item = Double.parseDouble(str);*/
        }
        return item;
    }

    public static boolean isMultipleOfX(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null)
            return false;
        long x = new Double(getNumber(obj2)).longValue();
        if (x == 0)
            return false;
        return new Double(getNumber(obj1)).longValue() % x == 0;
    }
}