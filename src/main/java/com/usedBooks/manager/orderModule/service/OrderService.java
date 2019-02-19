package com.usedBooks.manager.orderModule.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;
import org.apache.ibatis.session.RowBounds;

public interface OrderService {
    long countByOrder(Order order);

    Order getByPrimaryKey(Integer id);

    List<Order> listWithRowbounds(Order order, RowBounds rowBounds);

    int removeByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order order);

    int updateByPrimaryKey(Order order);

    int removeByOrder(Order order);

    int save(Order order);

    int saveSelective(Order order);

    PageInfo getList(Integer page, Integer limit, Order order, String key, String value);

}