package com.usedBooks.manager.dictionaryModule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.dictionaryModule.mapper.DictionaryItemMapper;
import com.usedBooks.manager.dictionaryModule.pojo.DictionaryItem;
import com.usedBooks.manager.dictionaryModule.service.DictionaryItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class DictionaryItemServiceImpl implements DictionaryItemService {
    @Autowired
    private DictionaryItemMapper dictionaryItemMapper;

    private static final Logger logger = LoggerFactory.getLogger(DictionaryItemServiceImpl.class);

    public DictionaryItem getByPrimaryKey(Integer id) {
        return this.dictionaryItemMapper.selectByPrimaryKey(id);
    }

    public long countByDictionaryData(DictionaryItem dictionaryData) {
        long count = this.dictionaryItemMapper.selectCount(dictionaryData);
        logger.debug("count: {}", count);
        return count;
    }

    public List<DictionaryItem> select(DictionaryItem dictionaryData) {
        return this.dictionaryItemMapper.select(dictionaryData);
    }

    public int removeByPrimaryKey(Integer id) {
        return this.dictionaryItemMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(DictionaryItem dictionaryItem) {
       return dictionaryItemMapper.updateByPrimaryKeySelective(dictionaryItem);
    }

    public int save(DictionaryItem dictionaryItem) {
        return dictionaryItemMapper.insert(dictionaryItem);
    }

    @Override
    public PageInfo getByDictId(Integer page, Integer limit, Integer dictId) {
        if(page!=null&&limit!=null){
            PageHelper.startPage(page,limit);
        }
        DictionaryItem dictionaryData = new DictionaryItem();
        dictionaryData.setDictId(dictId);
        List<DictionaryItem> list = dictionaryItemMapper.select( dictionaryData);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public DictionaryItem getDictionaryItemValue(String dictCode, String itemValue) {
        return dictionaryItemMapper.getDictionaryItemValue(dictCode,itemValue);
    }

    @Override
    public List<DictionaryItem> getDictionaryItemByDictCode(String dictCode) {
        return dictionaryItemMapper.getDictionaryItemByDictCode(dictCode);
    }

}