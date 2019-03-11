package com.usedBooks.manager.dictionaryModule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.dictionaryModule.service.DictionaryService;
import com.usedBooks.mapper.DictionaryDataMapper;
import com.usedBooks.mapper.DictionaryMapper;
import com.usedBooks.pojo.Dictionary;
import com.usedBooks.pojo.DictionaryData;
import com.usedBooks.util.MyBeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private DictionaryDataMapper dictionaryDataMapper;

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

    @Transactional
    public int removeByPrimaryKey(Integer id) {
        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setDictId(id);
        if(dictionaryDataMapper.deleteByDictionaryData(dictionaryData)>0){
            return this.dictionaryMapper.deleteByPrimaryKey(id);
        }
        return 0;
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

    @Override
    public PageInfo toList(Integer page,Integer limit,Dictionary dictionary,String key,String value) {
        if(page!=null&&limit!=null){
            PageHelper.startPage(page,limit);
        }
        Map<String,Object> map = MyBeanUtils.beanToMap(dictionary);
        map.put("key",key);
        map.put("value",value);
        List<Dictionary> list = dictionaryMapper.toList(map);
        return new PageInfo(list);
    }
}