package com.usedBooks.manager.dictionaryModule.service;

import com.usedBooks.pojo.Dictionary;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DictionaryService {
    long countByDictionary(Dictionary dictionary);

    Dictionary getByPrimaryKey(Integer id);

    List<Dictionary> listWithRowbounds(Dictionary dictionary, RowBounds rowBounds);

    int removeByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dictionary dictionary);

    int updateByPrimaryKey(Dictionary dictionary);

    int removeByDictionary(Dictionary dictionary);

    int save(Dictionary dictionary);

    int saveSelective(Dictionary dictionary);
}