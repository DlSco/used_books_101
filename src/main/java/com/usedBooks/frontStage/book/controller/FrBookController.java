package com.usedBooks.frontStage.book.controller;

import com.usedBooks.manager.bookModule.service.BookService;
import com.usedBooks.pojo.Book;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class FrBookController {


    private final Logger logger= LoggerFactory.getLogger(FrBookController.class) ;
    @Autowired
    private BookService bookService;

    /**
     *
     * @param book   Book 实体
     * @return       结果集
     */
    @RequestMapping(value="/addBook",method = RequestMethod.POST)
    public Result addBook(Book book){
        if(bookService.save(book)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"添加书籍失败"));
    }

    /**
     *
     * @param book   Book实体
     * @return       结果集
     */
    @RequestMapping(value="/modify",method = RequestMethod.POST)
    public Result modify (Book book){
        if(bookService.updateByPrimaryKey(book)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"修改书籍信息失败"));
    }


    /**
     *   前台方面的获取书籍list
     */
    @RequestMapping(value="/getList")
    public Result toList(){
        return null;
    }


}
