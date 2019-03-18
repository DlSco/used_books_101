package com.usedBooks.manager.dictionaryModule.service;

import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.dictionaryModule.pojo.DictionaryItem;

import java.util.List;

public interface DictionaryItemService {

    long countByDictionaryData(DictionaryItem dictionaryData);

    DictionaryItem getByPrimaryKey(Integer id);

    int removeByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DictionaryItem dictionaryData);

    int save(DictionaryItem dictionaryData);

    /**
     * 获得字典值列表，根据字典id
     * @param page
     * @param limit
     * @param dictId
     * @return
     */
    PageInfo getByDictId(Integer page, Integer limit, Integer dictId);

    DictionaryItem getDictionaryItemValue(String dictCode, String itemValue);

    List<DictionaryItem> getDictionaryItemByDictCode(String dictCode);
}