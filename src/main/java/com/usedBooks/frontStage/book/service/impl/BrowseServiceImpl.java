package com.usedBooks.frontStage.book.service.impl;

import com.usedBooks.frontStage.book.mapper.BrowseMapper;
import com.usedBooks.frontStage.book.pojo.BrowseRecord;
import com.usedBooks.frontStage.book.service.BrowseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class BrowseServiceImpl implements BrowseService {

    @Autowired
    private BrowseMapper browseMapper;
    @Override
    public int addBrowse( Integer incr,BrowseRecord browseRecord) {

        BrowseRecord tableRecord = browseMapper.select(browseRecord).get(0);
        if(tableRecord!=null){
            tableRecord.setBrowseNum(incr+tableRecord.getBrowseNum());
            return browseMapper.updateByPrimaryKeySelective(tableRecord);
        }else{
            browseRecord.setBrowseNum(incr);
            return browseMapper.insert(browseRecord);
        }

    }
}
