package com.usedBooks.manager.bookModule.service;

import java.util.List;

import com.usedBooks.pojo.Book;
import com.usedBooks.result.Result;
import org.apache.ibatis.session.RowBounds;

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

    List<Book> getList(String key, String value, Integer type, Integer isDrop);
}