package com.usedBooks.manager.bookModule.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.bookModule.service.BookService;
import com.usedBooks.pojo.Book;
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
@RequestMapping("/Manager/bookModule")
public class BookController {

    private final Logger logger= LoggerFactory.getLogger(BookController.class) ;
    @Autowired
    private BookService bookService;

    /**
     *
     * @param page       第几页
     * @param limit      请求的条数
     * @param key        关键字(数据库)
     * @param value      搜素值
     * @Param book       Book类实体
     * @return           result实体
     */
    @RequestMapping(value="/getList",method = RequestMethod.POST)
    public Result getList(Integer page,Integer limit,String key,
                          String value,Book book ,String sname,Integer sortRule){
        if(page!=null && limit!=null){
            PageHelper.startPage(page,limit);
        }
        List<Book> list = bookService.getList(key,value,book,sname,sortRule);
        PageInfo pageInfo = new PageInfo(list);
        logger.info(pageInfo.toString());
        return Result.success(pageInfo);
    }

    /**
     * 修改上下架
     * @param id    书本id
     * @return
     */
    @RequestMapping("/updateShelf/{id}")
    public Result updateShelf(@PathVariable Integer id){
       if(bookService.updateShelf(id)>0) {
            return Result.success(null);
       }
       return Result.error(new CodeMsg(0,"上架或下架失败"));
    }

}
