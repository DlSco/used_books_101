package com.usedBooks.manager.bookModule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.usedBooks.mapper.BookMapper;
import com.usedBooks.manager.bookModule.service.BookService;
import com.usedBooks.pojo.Book;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    public Book getByPrimaryKey(Integer id) {
        return this.bookMapper.selectByPrimaryKey(id);
    }

    public long countByBook(Book book) {
        long count = this.bookMapper.countByBook(book);
        logger.debug("count: {}", count);
        return count;
    }

    public List<Book> listWithRowbounds(Book book, RowBounds rowBounds) {
        return this.bookMapper.selectWithRowbounds(book,rowBounds);
    }

    public int removeByPrimaryKey(Integer id) {
        return this.bookMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Book book) {
        return this.bookMapper.updateByPrimaryKeySelective(book);
    }

    public int updateByPrimaryKey(Book book) {
        return this.bookMapper.updateByPrimaryKey(book);
    }

    public int removeByBook(Book book) {
        return this.bookMapper.deleteByBook(book);
    }

    public int save(Book book) {
        return this.bookMapper.insert(book);
    }

    public int saveSelective(Book book) {
        return this.bookMapper.insertSelective(book);
    }

    /**
     * 获取书籍列表 ，动态条件
     * @param key
     * @param value
     * @param book
     * @param sname
     * @param sortRule
     * @return
     */
    @Override
    public List<Book> getList(String key,String value,Book book,String sname,Integer sortRule) {
        value = "%" + value+ "%";
        Map<String,Object> map = new HashMap<>();
        map.put("key",key);
        map.put("value",value);
        map.put("type",book.getType());
        map.put("isDrop",book.getIsDrop());
        map.put("id",book.getId());
        map.put("status",book.getStatus());
        map.put("sname",sname);
        map.put("sortRule",sortRule==null?"ASC":(sortRule==1?"ASC":"DESC"));
        return bookMapper.getList(map);
    }

    @Override
    public int updateShelf(Integer id) {
        Integer code = null;
        code = bookMapper.getBookShelfCode(id);
        return bookMapper.updateShelf(id,code==1?0:1);
    }
}