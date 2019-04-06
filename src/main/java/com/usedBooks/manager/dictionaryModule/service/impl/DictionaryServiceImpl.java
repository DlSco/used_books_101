package com.usedBooks.manager.dictionaryModule.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.dictionaryModule.mapper.DictionaryItemMapper;
import com.usedBooks.manager.dictionaryModule.mapper.DictionaryMapper;
import com.usedBooks.manager.dictionaryModule.pojo.Dictionary;
import com.usedBooks.manager.dictionaryModule.pojo.DictionaryItem;
import com.usedBooks.manager.dictionaryModule.service.DictionaryService;
import com.usedBooks.result.Pager;
import com.usedBooks.util.DicConstants;
import com.usedBooks.util.MyBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private DictionaryItemMapper dictionaryItemMapper;

    @Autowired
    private DicConstants dicConstants;


    private static final Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    public Dictionary getByPrimaryKey(Integer id) {
        return this.dictionaryMapper.selectByPrimaryKey(id);
    }

    public long countByDictionary(Dictionary dictionary) {
        long count = this.dictionaryMapper.selectCount(dictionary);
        logger.debug("count: {}", count);
        return count;
    }

    public List<Dictionary> select(Dictionary dictionary) {
        return this.dictionaryMapper.select(dictionary);
    }

    @Transactional
    public int removeByPrimaryKey(Integer id) {
        try {
            DictionaryItem erpDictionaryItem = new DictionaryItem();
            erpDictionaryItem.setDictId(id);
            dictionaryItemMapper.delete(erpDictionaryItem);
             this.dictionaryMapper.deleteByPrimaryKey(id);
            dicConstants.getDicMap().clear();
            dicConstants.init();
            logger.info(dicConstants.getDicMap().toString());
            return 1;
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }
        return 0;
    }

    public int updateByPrimaryKeySelective(Dictionary dictionary) {

        try {
            dictionaryMapper.updateByPrimaryKeySelective(dictionary);
            dicConstants.getDicMap().clear();
            dicConstants.init();
            logger.info(dicConstants.getDicMap().toString());
            return 1;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();


        }
        return 0;
    }


    public int save(Dictionary dictionary) {
        try {
            dictionaryMapper.insert(dictionary);
            dicConstants.getDicMap().clear();
            dicConstants.init();
            logger.info(dicConstants.getDicMap().toString());
            return 1;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }
        return 0;
    }


    @Override
    public Pager toList(Integer page, Integer limit, Dictionary dictionary, String keyword) {
        if(page!=null&&limit!=null){
            PageHelper.startPage(page,limit);
        }
        Map<String,Object> map = MyBeanUtils.beanToMap(dictionary);
        map.put("keyword",null==keyword?null:"%"+keyword+"%");
        List<Dictionary> list = dictionaryMapper.toList(map);
        return new Pager(new PageInfo(list));
    }


}