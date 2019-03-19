
package com.usedBooks.manager.bookModule.mapper;


import com.usedBooks.mybatis.common.BaseMapper;
import com.usedBooks.pojo.Book;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface BookMapper extends BaseMapper<Book> {

    /**
     * 获取书籍列表,动态条件
     * @param map
     * @return
     */
    List<Book> getList( Map<String,Object> map);

    /**
     * 上架或下架
     * @param id
     * @param code
     * @return
     */
    int updateShelf(@Param("id") Integer id,@Param("code") Integer code);

    /**
     * 查看书籍上下架数据库字段的值
     * @param id
     * @return
     */
    Integer getBookShelfCode(@Param("id") Integer id);
}