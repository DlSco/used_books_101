package com.usedBooks.frontStage.message.controller;

import com.github.pagehelper.PageInfo;
import com.usedBooks.frontStage.message.service.MessageService;
import com.usedBooks.pojo.Message;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/message",method = RequestMethod.POST)
public class MessageController {

    private final Logger logger= LoggerFactory.getLogger(MessageController.class) ;


    @Autowired
    private MessageService messageService;

    /**
     * 留言
     * @param message
     * @return
     */
    @RequestMapping("/add")
    public Result sendMessage(Message message){

        if(messageService.save(message)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"留言失败"));
    }

    /**
     * 删除留言
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer id){

        if(messageService.removeByPrimaryKey(id)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"删除留言失败"));
    }

    /**
     *留言列表
     */
    @RequestMapping("/toList")
    public Result toList(Integer page,Integer limit,Message message){
        PageInfo<Message> pageInfo = messageService.toList(page,limit,message);
        return Result.success(pageInfo);
    }

    /**
     * 批量删除留言
     * @param ids
     * @return
     */
    @RequestMapping("/deleteByBatch")
    public Result deleteByBatch(@RequestParam Integer ids[]){
        if(messageService.deleteByBatch(ids)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"批量删除留言失败"));
    }
}
