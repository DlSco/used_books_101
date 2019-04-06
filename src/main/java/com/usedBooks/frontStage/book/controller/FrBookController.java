package com.usedBooks.frontStage.book.controller;

import com.usedBooks.frontStage.book.mapper.BookFrontMapper;
import com.usedBooks.frontStage.book.service.FrontBookService;
import com.usedBooks.frontStage.book.vo.BookVo;
import com.usedBooks.manager.bookModule.service.BookService;
import com.usedBooks.pojo.Book;
import com.usedBooks.pojo.Publish;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class FrBookController {

    @Autowired
    BookFrontMapper frontMapper;
    @Autowired
    private FrontBookService frontBookService;

    /**
     *
     * @param book   Book 实体
     * @return       结果集
     */
    @RequestMapping(value="/addBook",method = RequestMethod.POST)
    public Result addBook(Book book, Publish publish){
        if(frontBookService.save(book,publish)>0){
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
    public Result modify (Book book,Publish publish,Integer publishId){
        publish.setId(publishId);
        if(frontBookService.modifyBook(book,publish)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"修改书籍信息失败"));
    }


    /**
     *   前台方面的获取书籍list
     */
    @RequestMapping(value="/toList")
    public Result toList(){
        List<Book> list = frontMapper.select(null);
        return Result.success(list);
    }



    @RequestMapping("/toDetail/{id}")
    public Result toDetail(@PathVariable Integer id){
        Book book = new Book();
        book.setId(id);
        BookVo bookVo = frontBookService.toDetail(id);
        if(bookVo!=null){
            return Result.success(bookVo);
        }
        return Result.error(new CodeMsg(0,"查询失败!"));
    }

    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        if(frontBookService.removeBook(id)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"删除失败"));
    }


}
