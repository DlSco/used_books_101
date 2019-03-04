package com.usedBooks.frontStage.message.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.frontStage.message.mapper.MessageInherit;
import com.usedBooks.frontStage.message.service.MessageService;
import com.usedBooks.mapper.MessageMapper;
import com.usedBooks.pojo.Message;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageInherit messageInherit;

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    public Message getByPrimaryKey(Integer id) {
        return this.messageMapper.selectByPrimaryKey(id);
    }

    public long countByMessage(Message message) {
        long count = this.messageMapper.countByMessage(message);
        logger.debug("count: {}", count);
        return count;
    }

    public List<Message> listWithRowbounds(Message message, RowBounds rowBounds) {
        return this.messageMapper.selectWithRowbounds(message,rowBounds);
    }

    public int removeByPrimaryKey(Integer id) {
        return this.messageMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Message message) {
        return this.messageMapper.updateByPrimaryKeySelective(message);
    }

    public int updateByPrimaryKey(Message message) {
        return this.messageMapper.updateByPrimaryKey(message);
    }

    public int removeByMessage(Message message) {
        return this.messageMapper.deleteByMessage(message);
    }

    public int save(Message message) {
        return this.messageMapper.insert(message);
    }

    public int saveSelective(Message message) {
        return this.messageMapper.insertSelective(message);
    }

    @Override
    public PageInfo<Message> toList(Integer page, Integer limit,Message message) {

        if(page!=null&&limit!=null){
            PageHelper.startPage(page,limit);
        }


        List<Message> list = messageMapper.selectWithRowbounds(null,new RowBounds());
        return new PageInfo<>(list);
    }

    @Override
    public int deleteByBatch(Integer ids[]){
        return messageInherit.deleteByBatch(ids);
    }
}