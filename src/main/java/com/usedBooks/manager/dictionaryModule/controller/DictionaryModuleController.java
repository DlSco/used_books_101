package com.usedBooks.manager.dictionaryModule.controller;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.dictionaryModule.pojo.Dictionary;
import com.usedBooks.manager.dictionaryModule.pojo.DictionaryItem;
import com.usedBooks.manager.dictionaryModule.service.DictionaryItemService;
import com.usedBooks.manager.dictionaryModule.service.DictionaryService;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Pager;
import com.usedBooks.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dictionary",method = RequestMethod.POST)
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
    private DictionaryItemService dictionaryItemService;
    @RequestMapping("/toList")
    public Result toList(Integer page, Integer limit, Dictionary dictionary, String keyword){
        Pager pager = dictionaryService.toList(page,limit,dictionary,keyword);
        if(pager.getRows()==null){
            return Result.error(new CodeMsg(1,"查询失败"));
        }
        return Result.success(pager);
    }

    /**
     * 字典项目列表
     * @param page
     * @param limit
     * @param dictId
     * @return
     */
    @RequestMapping("/toDetail/{dictId}")
    public Result toDetail(Integer page, Integer limit,@PathVariable Integer dictId){

        PageInfo pageInfo = dictionaryItemService.getByDictId(page,limit,dictId);
        if(pageInfo.getList()==null){
            return Result.error(new CodeMsg(1,"查询失败"));
        }
        return Result.success(new Pager(pageInfo));
    }

    /**
     * 根据id删除字典
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable(value = "id") Integer id){

        if(dictionaryService.removeByPrimaryKey(id)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(1,"删除失败"));
    }

    /**
     * 根据id删除字典项目
     * @param id
     * @return
     */
    @RequestMapping("/deleteDictionaryItem/{id}")
    public Result deleteDictionaryItem(@PathVariable Integer id){
       if(dictionaryItemService.removeByPrimaryKey(id)>0){
           return Result.success(null);
       }
       return Result.error(new CodeMsg(1,"删除失败"));
    }

    /**
     * 修改字典表
     * @param dictionary
     * @return
     */
    @RequestMapping("/updateDictionary")
    public Result updateDictionary(Dictionary dictionary){

        //判断数据库是否有相同的记录
        Dictionary temp = new Dictionary();
        temp.setDictCode(dictionary.getDictCode());
        if(temp.getDictCode()==null || temp.getDictCode().equals("")){
            return Result.error(new CodeMsg(1,"字典代码不能为空"));
        }
        if(dictionaryService.countByDictionary(temp)>0){
            return Result.error(new CodeMsg(1,"修改失败，已含有此纪录"));
        }
        if(dictionaryService.updateByPrimaryKeySelective(dictionary)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(1,"修改失败"));
    }

    /**
     * 修改字典项目表
     * @param dictionaryItem
     * @return
     */
    @RequestMapping("/updateDictionaryItem")
    public Result updateDictionaryItem(DictionaryItem dictionaryItem){

        if(dictionaryItem.getItemName()==null || dictionaryItem.getItemName()==""){
            return Result.error(new CodeMsg(1,"字典项目名不能没值"));
        }
        DictionaryItem temp = new DictionaryItem();
        temp.setItemName(dictionaryItem.getItemName());
        //判断数据库是否有相同的记录
        if(dictionaryItemService.countByDictionaryData(temp)>0){
            return Result.error(new CodeMsg(1,"修改失败，已含有此纪录"));
        }
        if(dictionaryItemService.updateByPrimaryKeySelective(dictionaryItem)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(1,"修改失败"));
    }

    /**
     * 添加字典
     * @param dictionary
     * @return
     */
    @RequestMapping("/addDictionary")
    public Result addDictionary(Dictionary dictionary){

        Dictionary temp = new Dictionary();
        temp.setDictCode(dictionary.getDictCode());
        if(temp.getDictCode()==null || temp.getDictCode().equals("")){
            return Result.error(new CodeMsg(1,"字典代码不能为空"));
        }
        //判断数据库是否有相同的记录
        if(dictionaryService.countByDictionary(temp)>0){
            return Result.error(new CodeMsg(1,"添加失败，已含有此纪录"));
        }
        if(dictionaryService.save(dictionary)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(1,"添加失败"));
    }

    /**
     * 添加字典项目
     * @param dictionaryItem
     * @return
     */
    @RequestMapping("/addDictionaryItem")
    public Result addDictionaryItem(DictionaryItem dictionaryItem){
        if(dictionaryItem.getItemName()==null || dictionaryItem.getItemName()==""){
            return Result.error(new CodeMsg(1,"字典项目名不能没值"));
        }
        DictionaryItem temp = new DictionaryItem();
        temp.setItemName(dictionaryItem.getItemName());
        //判断数据库是否有相同的记录
        if(dictionaryItemService.countByDictionaryData(temp)>0){
            return Result.error(new CodeMsg(1,"添加失败，已含有此纪录"));
        }
        if(dictionaryItemService.save(dictionaryItem)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(1,"添加失败"));
    }

    /**
     *根据以下条件获得DictionaryItem
     * @param dictCode        字典代码
     * @param itemValue       字典项目值
     * @return
     */
    @RequestMapping("/getDictionaryItemValue")
    public Result getDictionaryItemValue(String dictCode,String itemValue){
        return Result.success(dictionaryItemService.getDictionaryItemValue(dictCode,itemValue));
    }

    /**
     * 根据一下条件获得DictionaryItem
     * @param dictCode
     * @return
     */
    @RequestMapping("/getDictionaryItemByDictCode")
    public Result getDictionaryItemByDictCode(String dictCode){
        return Result.success(dictionaryItemService.getDictionaryItemByDictCode(dictCode));
    }
}
