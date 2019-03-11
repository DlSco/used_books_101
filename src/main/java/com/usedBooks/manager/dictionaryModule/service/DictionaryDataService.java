package com.usedBooks.manager.dictionaryModule.service;

import com.github.pagehelper.PageInfo;
import com.usedBooks.pojo.DictionaryData;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DictionaryDataService {
    long countByDictionaryData(DictionaryData dictionaryData);

    DictionaryData getByPrimaryKey(Integer id);

    List<DictionaryData> listWithRowbounds(DictionaryData dictionaryData, RowBounds rowBounds);

    int removeByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DictionaryData dictionaryData);

    int updateByPrimaryKey(DictionaryData dictionaryData);

    int removeByDictionaryData(DictionaryData dictionaryData);

    int save(DictionaryData dictionaryData);

    int saveSelective(DictionaryData dictionaryData);


    /**
     * 获得字典值列表，根据字典id
     * @param page
     * @param limit
     * @param dictId
     * @return
     */
    PageInfo getByDictId(Integer page, Integer limit, Integer dictId);

}