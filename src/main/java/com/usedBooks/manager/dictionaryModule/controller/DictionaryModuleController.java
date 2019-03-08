package com.usedBooks.manager.dictionaryModule.controller;


import com.usedBooks.pojo.Dictionary;
import com.usedBooks.pojo.DictionaryData;
import com.usedBooks.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Manager/dictionaryModule",method = RequestMethod.POST)
public class DictionaryModuleController {

    /**
     * 字典列表
     * @param page        当前页
     * @param limit       条数
     * @param dictionary  字典实体
     * @return
     */
    @RequestMapping("/toList")
    public Result toList(Integer page, Integer limit, Dictionary dictionary){
        return null;
    }

    /**
     * 字典值列表
     * @param page
     * @param limit
     * @param dictionaryData
     * @return
     */
    @RequestMapping("/toDetail")
    public Result toDetail(Integer page, Integer limit, DictionaryData dictionaryData){
        return null;
    }

    public Result delete(String dicCode){
        return null;
    }

    public Result deleteDictonaryData(Integer id){
        return null;
    }

}
