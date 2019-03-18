package com.usedBooks.manager.dictionaryModule.mapper;


import com.usedBooks.manager.dictionaryModule.pojo.DictionaryItem;
import com.usedBooks.mybatis.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictionaryItemMapper extends BaseMapper<DictionaryItem> {

    DictionaryItem getDictionaryItemValue(@Param("dictCode") String dictCode, @Param("itemValue") String itemValue);

    List<DictionaryItem> getDictionaryItemByDictCode(@Param("dictCode") String dictCode);
}
