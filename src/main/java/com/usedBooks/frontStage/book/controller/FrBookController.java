package com.usedBooks.frontStage.book.controller;

import com.github.pagehelper.PageInfo;
import com.usedBooks.frontStage.book.service.BrowseService;
import com.usedBooks.frontStage.book.service.FrontBookService;
import com.usedBooks.frontStage.book.vo.BookSearchVo;
import com.usedBooks.pojo.Book;
import com.usedBooks.pojo.Publish;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/book")
public class FrBookController {

    @Autowired
    private FrontBookService frontBookService;
    @Autowired
    private BrowseService browseService;

    /**
     *
     * @param book   Book 实体
     * @return       结果集
     */
    @RequestMapping(value="/addBook",method = RequestMethod.POST)
    public Result addBook(Book book, Publish publish, HttpServletRequest request, String beginTime,String endTime ){
        if(frontBookService.save(book,publish,beginTime,endTime)>0){
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
        if(book.getId() == null || publishId == null ){
            return Result.error(new CodeMsg(0,"请检查bookId，publishId的非空情况"));
        }

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
    public Result toList(Integer page, Integer limit, BookSearchVo bookSearchVo,String keyword,
                         @RequestParam(defaultValue = "update_time") String sname,
                         Integer sortRule){

        log.info(bookSearchVo.getClassification()+"");
        PageInfo pageInfo = frontBookService.toList(page,limit,keyword,bookSearchVo,sname,sortRule);
        return Result.success(pageInfo);
    }


    /**
     * 进入详情
     * @param id
     * @param publishType
     * @return
     */

    @RequestMapping("/toDetail/{id}")
    public Result toDetail(@PathVariable Integer id,Integer publishType){
        if(id == null || publishType ==null){
            return Result.error(new CodeMsg(500700,"参数不能为空"));
        }
        try {

            return Result.success(frontBookService.toDetail(id,publishType));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        if(frontBookService.removeBook(id)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"删除失败"));
    }

    /**
     * 首页获取热门书籍列表
     */

    @RequestMapping("/toHotBookList")
    public Result toHotBookList(Integer page, Integer limit){

        return Result.success(frontBookService.toHotBookList(page,limit));

    }
}
