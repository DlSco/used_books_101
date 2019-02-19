package com.usedBooks.manager.bookModule.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.bookModule.service.BookService;
import com.usedBooks.pojo.Book;
import com.usedBooks.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Manager/bookModule")
public class BookController {

    private final Logger logger= LoggerFactory.getLogger(BookController.class) ;
    @Autowired
    private BookService bookService;
    @RequestMapping(value="/getList",method = RequestMethod.POST)
    public Result getList(Integer page,Integer limit,String key,String value,Integer type,Integer isDrop){
        List<Book> list = null;
        if(page!=null && limit!=null){
            PageHelper.startPage(page,limit);
        }
        list = bookService.getList(key,value,type,isDrop);
        PageInfo pageInfo = new PageInfo(list,5);
        return Result.success(pageInfo);
    }
}
