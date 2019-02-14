package com.usedBooks.util;

import java.util.ArrayList;
import java.util.List;

public class ListHandleUtils {

    //将一个list分成几页，获取当前页（pageCode）的 size条数据的 list
    public static List getPartOfList(List allList,Integer pageCode,Integer size){
        if(allList == null){
            return null;
        }
        int totalPage = allList.size() / size;
        // 说明整除
        if(allList.size() % size == 0){
        }else{
            totalPage =  totalPage + 1;
        }
        if(totalPage>=pageCode){
            int start = (pageCode-1)*size;
            if(allList.size()>=pageCode*size){
                int end = (pageCode-1)*size+size;
               return allList.subList(start,end);
            }
            else{
                int end = allList.size();
                return allList.subList(start,end);
            }
        }
        else{
            return null;
        }

    }


}
