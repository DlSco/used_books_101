package com.usedBooks.manager.bookModule.service;

import java.util.List;

import com.usedBooks.manager.bookModule.vo.BookForManagerVo;
import com.usedBooks.pojo.Book;
import com.usedBooks.pojo.Publish;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.RequestParam;

public interface BookService {
    long countByBook(Book book);

    Book getByPrimaryKey(Integer id);

    List<Book> listWithRowbounds(Book book, RowBounds rowBounds);

    int removeByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book book);

    int updateByPrimaryKey(Book book);

    int removeByBook(Book book);

    int save(Book book);

    int saveSelective(Book book);

    List<BookForManagerVo> getList(Publish publish, String keyword, Book book, String sname, Integer sortRule);

    int updateShelf(Integer bookId);
}