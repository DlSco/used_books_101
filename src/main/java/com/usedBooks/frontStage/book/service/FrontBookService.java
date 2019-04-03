package com.usedBooks.frontStage.book.service;

import com.usedBooks.frontStage.book.vo.BookVo;
import com.usedBooks.pojo.Book;
import com.usedBooks.pojo.Publish;

import java.util.List;

public interface FrontBookService {

    /**
     * 保存
     * @param book
     * @param publish
     * @return
     */
    int save(Book book,Publish publish);

    /**
     * 删除
     * @return
     */
    int removeBook(Integer id);

    /**
     * 修改
     * @param book
     * @param publish
     * @return
     */
    int modifyBook(Book book,Publish publish);

    /**
     * 获取列表
     * @return
     */
    List<BookVo> toList();

    /**
     * 获取某一个书籍Vo
     * @return
     */
    BookVo toDetail();
}
