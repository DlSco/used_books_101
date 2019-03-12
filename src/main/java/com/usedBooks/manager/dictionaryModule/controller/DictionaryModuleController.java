package com.usedBooks.manager.dictionaryModule.controller;


import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.dictionaryModule.service.DictionaryDataService;
import com.usedBooks.manager.dictionaryModule.service.DictionaryService;
import com.usedBooks.pojo.Dictionary;
import com.usedBooks.pojo.DictionaryData;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictionaryDataService dictionaryDataService;
    @RequestMapping("/toList")
    public Result toList(Integer page, Integer limit, Dictionary dictionary,String key,String value){
        PageInfo pageInfo = dictionaryService.toList(page,limit,dictionary,key,value);
        if(pageInfo.getList()==null){
            return Result.error(new CodeMsg(0,"查询失败"));
        }
        return Result.success(pageInfo);
    }

    /**
     * 字典值列表
     * @param page
     * @param limit
     * @param dictId
     * @return
     */
    @RequestMapping("/toDetail/{dictId}")
    public Result toDetail(Integer page, Integer limit,Integer dictId){

        PageInfo pageInfo = dictionaryDataService.getByDictId(page,limit,dictId);
        if(pageInfo.getList()==null){
            return Result.error(new CodeMsg(0,"查询失败"));
        }
        return Result.success(pageInfo);
    }

    /**
     * 根据id删除字典
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){

        if(dictionaryService.removeByPrimaryKey(id)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"删除失败"));
    }

    /**
     * 根据id删除字典值
     * @param dictId
     * @return
     */
    @RequestMapping("/deleteDictionaryData/{dictId}")
    public Result deleteDictionaryData(@PathVariable Integer dictId){
       if(dictionaryDataService.removeByPrimaryKey(dictId)>0){
           return Result.success(null);
       }
       return Result.error(new CodeMsg(0,"删除失败"));
    }

    /**
     * 修改字典
     * @param dictionary
     * @return
     */
    @RequestMapping("/updateDictionary/{id}")
    public Result updateDictionary(@PathVariable Integer id,Dictionary dictionary){
        dictionary.setId(id);
        if(dictionaryService.updateByPrimaryKey(dictionary)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"修改失败"));
    }

    /**
     * 修改字典值
     * @param id
     * @param dictionaryData
     * @return
     */
    @RequestMapping("/updateDictionaryData/{id}")
    public Result updateDictionaryData(@PathVariable Integer id,DictionaryData dictionaryData){
        dictionaryData.setId(id);
        if(dictionaryDataService.updateByPrimaryKey(dictionaryData)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"修改失败"));
    }

    @RequestMapping("/addDictionary")
    public Result addDictionary(Dictionary dictionary){
        if(dictionaryService.save(dictionary)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"添加失败"));
    }

    @RequestMapping("/addDictionaryData")
    public Result addDictionaryData(DictionaryData dictionaryData){
        if(dictionaryDataService.save(dictionaryData)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"添加失败"));
    }

}
