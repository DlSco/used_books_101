package com.usedBooks.manager.bookModule.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.bookModule.service.BookService;
import com.usedBooks.pojo.Book;
import com.usedBooks.pojo.Publish;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param keyword    关键字
     * @param publish    Publish实体
     * @Param book       Book类实体
     * @return           result实体
     */
    @RequestMapping(value="/getList",method = RequestMethod.POST)
    public Result getList(Integer page, Integer limit,Publish publish, String keyword,
                          Book book , @RequestParam(defaultValue = "update_time") String sname,
                          Integer sortRule){
        if(page!=null && limit!=null){
            PageHelper.startPage(page,limit);
        }
        List<Book> list = bookService.getList(publish,keyword,book,sname,sortRule);
        PageInfo pageInfo = new PageInfo(list);
        logger.info(pageInfo.toString());
        return Result.success(pageInfo);
    }

    /**
     * 修改上下架
     * @param bookId    书本id
     * @return
     */
    @RequestMapping("/updateShelf/{bookId}")
    public Result updateShelf(@PathVariable Integer bookId){
       if(bookService.updateShelf(bookId)>0) {
            return Result.success(null);
       }
       return Result.error(new CodeMsg(0,"上架或下架失败"));
    }

}
