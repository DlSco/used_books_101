package com.usedBooks.frontStage.book.controller;

import com.usedBooks.frontStage.book.mapper.BookFrontMapper;
import com.usedBooks.manager.bookModule.service.BookService;
import com.usedBooks.pojo.Book;
import com.usedBooks.pojo.Publish;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class FrBookController {


    private final Logger logger= LoggerFactory.getLogger(FrBookController.class) ;
    @Autowired
    private BookService bookService;
    @Autowired
    BookFrontMapper frontMapper;

    /**
     *
     * @param book   Book 实体
     * @return       结果集
     */
    @RequestMapping(value="/addBook",method = RequestMethod.POST)
    public Result addBook(Book book, Publish publish){
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
        List<Book> list = frontMapper.select(null);
        return Result.success(list);
    }



    @RequestMapping("/toDetail/{id}")
    public Result toDetail(@PathVariable Integer id){
        Book book = new Book();
        book.setId(id);
        List<Book> list = bookService.getList(null,null,book,null,null);
        if(list!=null){
            return Result.success(list.get(0));
        }
        return Result.error(new CodeMsg(0,"查询失败!"));
    }



}
