
package com.usedBooks.mapper;


import com.usedBooks.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BookMapper {
    /**
     * @param book
     * @return  long
     */
    long countByBook(Book book);

    /**
     * @param book
     * @return  int
     */
    int deleteByBook(Book book);

    /**
     * @param id
     * @return  int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @param record
     * @return  int
     */
    int insert(Book record);

    /**
     * @param record
     * @return  int
     */
    int insertSelective(Book record);

    /**
     * @param book
     * @return  java.util.List<com.hhly.sns.entity.app.Book>
     */
    List<Book> selectWithRowbounds(Book book, RowBounds rowBounds);

    /**
     * @param id
     * @return  com.hhly.sns.entity.app.Book
     */
    Book selectByPrimaryKey(Integer id);

    /**
     * @param record
     * @return  int
     */
    int updateByPrimaryKeySelective(Book record);

    /**
     * @param record
     * @return  int
     */
    int updateByPrimaryKey(Book record);

    List<Book> getList( Map<String,Object> map);
}