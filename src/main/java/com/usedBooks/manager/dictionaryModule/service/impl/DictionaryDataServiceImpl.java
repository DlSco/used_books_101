package com.usedBooks.manager.dictionaryModule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.dictionaryModule.service.DictionaryDataService;
import com.usedBooks.mapper.DictionaryDataMapper;
import com.usedBooks.pojo.DictionaryData;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DictionaryDataServiceImpl implements DictionaryDataService {
    @Autowired
    private DictionaryDataMapper dictionaryDataMapper;

    private static final Logger logger = LoggerFactory.getLogger(DictionaryDataServiceImpl.class);

    public DictionaryData getByPrimaryKey(Integer id) {
        return this.dictionaryDataMapper.selectByPrimaryKey(id);
    }

    public long countByDictionaryData(DictionaryData dictionaryData) {
        long count = this.dictionaryDataMapper.countByDictionaryData(dictionaryData);
        logger.debug("count: {}", count);
        return count;
    }

    public List<DictionaryData> listWithRowbounds(DictionaryData dictionaryData, RowBounds rowBounds) {
        return this.dictionaryDataMapper.selectWithRowbounds(dictionaryData,rowBounds);
    }

    public int removeByPrimaryKey(Integer id) {
        return this.dictionaryDataMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(DictionaryData dictionaryData) {
        return this.dictionaryDataMapper.updateByPrimaryKeySelective(dictionaryData);
    }

    public int updateByPrimaryKey(DictionaryData dictionaryData) {
        return this.dictionaryDataMapper.updateByPrimaryKey(dictionaryData);
    }

    public int removeByDictionaryData(DictionaryData dictionaryData) {
        return this.dictionaryDataMapper.deleteByDictionaryData(dictionaryData);
    }

    public int save(DictionaryData dictionaryData) {
        return this.dictionaryDataMapper.insert(dictionaryData);
    }

    public int saveSelective(DictionaryData dictionaryData) {
        return this.dictionaryDataMapper.insertSelective(dictionaryData);
    }

    @Override
    public PageInfo getByDictId(Integer page, Integer limit, Integer dictId) {
        if(page!=null&&limit!=null){
            PageHelper.startPage(page,limit);
        }

        List<DictionaryData> list = dictionaryDataMapper.getByDictId( dictId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


}