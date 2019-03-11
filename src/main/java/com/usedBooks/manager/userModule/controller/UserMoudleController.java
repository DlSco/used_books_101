package com.usedBooks.manager.userModule.controller;


import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.userModule.service.UserService;
import com.usedBooks.pojo.User;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Manager/userModule",method = RequestMethod.POST)
public class UserMoudleController {

    @Autowired
    private UserService userService;

    /**
     * 获得列表，动态条件
     * @param user
     * @param page
     * @param limit
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/toList")
    public Result toList(User user,Integer page,Integer limit,String key,String value){
        PageInfo pageInfo = userService.toList(user,page,limit,key,value);
        if(pageInfo.getList()==null){
            return Result.error(new CodeMsg(0,"查询失败！"));
        }
        return Result.success(pageInfo);
    }

    /**
     * 添加
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public Result add(User user){

        if(userService.save(user)>0){
            return Result.success(null);
        }

        return Result.error(new CodeMsg(0,"添加失败!"));
    }

    /**
     * 删除记录
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        if(userService.removeByPrimaryKey(id)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"删除失败！"));
    }

    /**
     * 修改记录
     * @param user
     * @param id
     * @return
     */
    @RequestMapping("/update/{id}")
    public Result update(User user,@PathVariable Integer id){
        user.setId(id);
        if(userService.updateByPrimaryKeySelective(user)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"修改失败"));
    }
}
