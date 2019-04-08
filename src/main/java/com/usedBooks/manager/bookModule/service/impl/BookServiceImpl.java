package com.usedBooks.manager.bookModule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.usedBooks.manager.bookModule.mapper.BookMapper;
import com.usedBooks.manager.bookModule.service.BookService;
import com.usedBooks.manager.bookModule.vo.BookForManagerVo;
import com.usedBooks.pojo.Book;
import com.usedBooks.pojo.Publish;
import com.usedBooks.util.DicConstants;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private DicConstants dicConstants;

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    public Book getByPrimaryKey(Integer id) {
        return this.bookMapper.selectByPrimaryKey(id);
    }

    public long countByBook(Book book) {
        long count = this.bookMapper.selectCount(book);
        logger.debug("count: {}", count);
        return count;
    }

    public List<Book> listWithRowbounds(Book book, RowBounds rowBounds) {
        return this.bookMapper.selectByRowBounds(book,rowBounds);
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
        return this.bookMapper.delete(book);
    }

    public int save(Book book) {
        return this.bookMapper.insert(book);
    }

    public int saveSelective(Book book) {
        return this.bookMapper.insertSelective(book);
    }

    /**
     * 获取书籍列表 ，动态条件
     * @param keyword
     * @param publish
     * @param book
     * @param sname
     * @param sortRule
     * @return
     */
    @Override
    public List<BookForManagerVo> getList(Publish publish, String keyword, Book book, String sname, Integer sortRule) {
        Map<String,Object> map = new HashMap<>();
        map.put("type",publish.getPublishType());
        map.put("isDrop",publish.getIsDrop());
        map.put("status",publish.getStatus());
        map.put("sname",sname);
        map.put("keyword",null==keyword?null:"%"+keyword+"%");
        map.put("sortRule",sortRule==null?"ASC":(sortRule==1?"ASC":"DESC"));
        List<BookForManagerVo> list = bookMapper.getList(map);
        for(BookForManagerVo vo:list){

            vo.setClassificationName(dicConstants.getItemName("classification",vo.getClassification()+""));
            vo.setBookOldStateName(dicConstants.getItemName("bookOldState",vo.getBookOldState()+""));
            vo.setPublishTypeName(dicConstants.getItemName("publishType",vo.getPublishType()+""));
            vo.setIsDropName(dicConstants.getItemName("isDrop",vo.getIsDrop()+""));
            vo.setStatusName(dicConstants.getItemName("status",vo.getStatus()+""));
        }
        return list;
    }

    @Override
    public int updateShelf(Integer bookId) {
        Integer code = null;
        code = bookMapper.getBookShelfCode(bookId);
        return bookMapper.updateShelf(bookId,code==1?0:1);
    }
}