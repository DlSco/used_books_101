package com.usedBooks.manager.dictionaryModule.service;

import com.usedBooks.manager.dictionaryModule.pojo.Dictionary;
import com.usedBooks.result.Pager;

import java.util.List;

public interface DictionaryService {

    List<Dictionary> select(Dictionary dictionary);

    long countByDictionary(Dictionary dictionary);

    Dictionary getByPrimaryKey(Integer id);

    int removeByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dictionary dictionary);

    int save(Dictionary dictionary);

    Pager<Dictionary> toList(Integer page, Integer limit, Dictionary dictionary, String keyword);


}