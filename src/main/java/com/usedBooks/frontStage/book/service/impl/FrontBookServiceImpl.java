package com.usedBooks.frontStage.book.service.impl;

import com.usedBooks.frontStage.book.mapper.BookFrontMapper;
import com.usedBooks.frontStage.book.mapper.PublishMapper;
import com.usedBooks.frontStage.book.service.FrontBookService;
import com.usedBooks.frontStage.book.vo.BookVo;
import com.usedBooks.pojo.Book;
import com.usedBooks.pojo.Publish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FrontBookServiceImpl implements FrontBookService {

    @Autowired
    private BookFrontMapper bookFrontMapper;

    @Autowired
    private PublishMapper publishMapper;
    @Override
    public int save(Book book,Publish publish) {
        Integer bookId = bookFrontMapper.insertUseGeneratedKeys(book);
        if(bookId>0){
            publish.setBookId(bookId);
            return publishMapper.insert(publish);
        }
        return 0;
    }

    @Override
    public int removeBook(Integer id ) {
        Publish publish = new Publish();
        publish.setBookId(id);
        Integer result = publishMapper.delete(publish);
        if(result>0){
            return bookFrontMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public int modifyBook(Book book, Publish publish) {
        Integer result = bookFrontMapper.updateByPrimaryKeySelective(book);
        if(result>0){
            return publishMapper.updateByPrimaryKeySelective(publish);
        }
        return 0;
    }

    @Override
    public List<BookVo> toList() {
        return null;
    }

    @Override
    public BookVo toDetail(Integer id) {

        return (BookVo) bookFrontMapper.selectByPrimaryKey(id);
    }
}
