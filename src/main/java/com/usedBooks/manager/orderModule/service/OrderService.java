package com.usedBooks.manager.orderModule.service;


import java.util.List;

import com.usedBooks.pojo.Order;
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
}