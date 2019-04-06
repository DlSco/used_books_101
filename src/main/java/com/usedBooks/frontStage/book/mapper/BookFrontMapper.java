package com.usedBooks.frontStage.book.mapper;

import com.usedBooks.frontStage.book.vo.BookDetailVo;
import com.usedBooks.frontStage.book.vo.BookVo;
import com.usedBooks.mybatis.common.BaseMapper;
import com.usedBooks.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookFrontMapper extends BaseMapper<Book> {
    /**
     * 书籍列表（动态条件）
     * @param map
     * @return
     */
    List<BookVo> toList(Map<String, Object> map);

    BookDetailVo toDetail(@Param("id") Integer id);
}
