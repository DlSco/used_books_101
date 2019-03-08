package com.usedBooks.manager.dictionaryModule.service.impl;

import com.usedBooks.manager.dictionaryModule.service.DictionaryService;
import com.usedBooks.mapper.DictionaryMapper;
import com.usedBooks.pojo.Dictionary;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    private static final Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    public Dictionary getByPrimaryKey(Integer id) {
        return this.dictionaryMapper.selectByPrimaryKey(id);
    }

    public long countByDictionary(Dictionary dictionary) {
        long count = this.dictionaryMapper.countByDictionary(dictionary);
        logger.debug("count: {}", count);
        return count;
    }

    public List<Dictionary> listWithRowbounds(Dictionary dictionary, RowBounds rowBounds) {
        return this.dictionaryMapper.selectWithRowbounds(dictionary,rowBounds);
    }

    public int removeByPrimaryKey(Integer id) {
        return this.dictionaryMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Dictionary dictionary) {
        return this.dictionaryMapper.updateByPrimaryKeySelective(dictionary);
    }

    public int updateByPrimaryKey(Dictionary dictionary) {
        return this.dictionaryMapper.updateByPrimaryKey(dictionary);
    }

    public int removeByDictionary(Dictionary dictionary) {
        return this.dictionaryMapper.deleteByDictionary(dictionary);
    }

    public int save(Dictionary dictionary) {
        return this.dictionaryMapper.insert(dictionary);
    }

    public int saveSelective(Dictionary dictionary) {
        return this.dictionaryMapper.insertSelective(dictionary);
    }
}