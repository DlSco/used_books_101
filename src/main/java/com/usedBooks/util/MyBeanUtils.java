package com.usedBooks.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

public class MyBeanUtils {

    //将请求参数转换为map
    public static Map<String,Object> beanToMap(Object object){
        Map<String,Object> map = new TreeMap<>();
        Field[] field = object.getClass().getDeclaredFields();
        for(int i= 0;i<field.length;i++) {
            //将属性设为可访问
            field[i].setAccessible(true);
            try {
                map.put(field[i].getName(),field[i].get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                map = null;
            }
        }
        return map;
    }
}
