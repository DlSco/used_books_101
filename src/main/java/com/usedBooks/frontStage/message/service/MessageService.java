package com.usedBooks.frontStage.message.service;

import com.github.pagehelper.PageInfo;
import com.usedBooks.pojo.Message;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface MessageService {
    long countByMessage(Message message);

    Message getByPrimaryKey(Integer id);

    List<Message> listWithRowbounds(Message message, RowBounds rowBounds);

    int removeByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message message);

    int updateByPrimaryKey(Message message);

    int removeByMessage(Message message);

    int save(Message message);

    int saveSelective(Message message);

    /**
     * 上面是mybatis上生成的service，下面的是我自己写
     */

    /**
     * 获得列表
     * @param page   当前页
     * @param limit  显示的条数
     * @return
     */
    PageInfo<Message> toList(Integer page, Integer limit,Message message);

    /**
     * 批量删除
     * @param ids    留言id集合
     * @return
     */
    int deleteByBatch(Integer ids[]);
 }