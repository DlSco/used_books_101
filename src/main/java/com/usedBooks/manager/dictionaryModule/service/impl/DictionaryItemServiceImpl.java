package com.usedBooks.manager.dictionaryModule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.dictionaryModule.mapper.DictionaryItemMapper;
import com.usedBooks.manager.dictionaryModule.pojo.DictionaryItem;
import com.usedBooks.manager.dictionaryModule.service.DictionaryItemService;
import com.usedBooks.util.DicConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DictionaryItemServiceImpl implements DictionaryItemService {
    @Autowired
    private DictionaryItemMapper dictionaryItemMapper;
    @Autowired
    private DicConstants dicConstants;

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
        try {
            if(this.dictionaryItemMapper.deleteByPrimaryKey(id)>0){
                dicConstants.getDicMap().clear();
                dicConstants.init();
                logger.info(dicConstants.getDicMap().toString());
                return 1;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return 0;
    }

    public int updateByPrimaryKeySelective(DictionaryItem dictionaryItem) {

        try {
            if(dictionaryItemMapper.updateByPrimaryKeySelective(dictionaryItem)>0){
                dicConstants.getDicMap().clear();
                dicConstants.init();
                logger.info(dicConstants.getDicMap().toString());
                return 1;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return 0;
    }

    public int save(DictionaryItem dictionaryItem) {

        try {
            if(dictionaryItemMapper.insert(dictionaryItem)>0){
                dicConstants.getDicMap().clear();
                dicConstants.init();
                logger.info(dicConstants.getDicMap().toString());
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return 0;
    }

    /**
     * 根据字典id获取字典项目列表
     * @param page
     * @param limit
     * @param dictId
     * @return
     */
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

    /**
     * 根据一下条件获得字典项目
     * @param dictCode       字典代码
     * @param itemValue      字典项目值
     * @return
     */
    @Override
    public DictionaryItem getDictionaryItemValue(String dictCode, String itemValue) {
        return dictionaryItemMapper.getDictionaryItemValue(dictCode,itemValue);
    }


    /**
     * 根据一下条件获得字典项目
     * @param dictCode 字典代码
     * @return
     */
    @Override
    public List<DictionaryItem> getDictionaryItemByDictCode(String dictCode) {
        return dictionaryItemMapper.getDictionaryItemByDictCode(dictCode);
    }

}