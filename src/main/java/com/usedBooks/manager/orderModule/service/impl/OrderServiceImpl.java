package com.usedBooks.manager.orderModule.service.impl;

import java.util.List;

import com.usedBooks.mapper.OrderMapper;
import com.usedBooks.manager.orderModule.service.OrderService;
import com.usedBooks.pojo.Order;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public Order getByPrimaryKey(Integer id) {
        return this.orderMapper.selectByPrimaryKey(id);
    }

    public long countByOrder(Order order) {
        long count = this.orderMapper.countByOrder(order);
        logger.debug("count: {}", count);
        return count;
    }

    public List<Order> listWithRowbounds(Order order, RowBounds rowBounds) {
        return this.orderMapper.selectWithRowbounds(order,rowBounds);
    }

    public int removeByPrimaryKey(Integer id) {
        return this.orderMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Order order) {
        return this.orderMapper.updateByPrimaryKeySelective(order);
    }

    public int updateByPrimaryKey(Order order) {
        return this.orderMapper.updateByPrimaryKey(order);
    }

    public int removeByOrder(Order order) {
        return this.orderMapper.deleteByOrder(order);
    }

    public int save(Order order) {
        return this.orderMapper.insert(order);
    }

    public int saveSelective(Order order) {
        return this.orderMapper.insertSelective(order);
    }
}