package com.usedBooks.manager.orderModule.service.impl;

import java.util.List;

import com.usedBooks.mapper.OrderDetailMapper;
import com.usedBooks.manager.orderModule.service.OrderDetailService;
import com.usedBooks.pojo.OrderDetail;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderDetailServiceImpl.class);

    public OrderDetail getByPrimaryKey(Integer id) {
        return this.orderDetailMapper.selectByPrimaryKey(id);
    }

    public long countByOrderDetail(OrderDetail orderDetail) {
        long count = this.orderDetailMapper.countByOrderDetail(orderDetail);
        logger.debug("count: {}", count);
        return count;
    }

    public List<OrderDetail> listWithRowbounds(OrderDetail orderDetail, RowBounds rowBounds) {
        return this.orderDetailMapper.selectWithRowbounds(orderDetail,rowBounds);
    }

    public int removeByPrimaryKey(Integer id) {
        return this.orderDetailMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(OrderDetail orderDetail) {
        return this.orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
    }

    public int updateByPrimaryKey(OrderDetail orderDetail) {
        return this.orderDetailMapper.updateByPrimaryKey(orderDetail);
    }

    public int removeByOrderDetail(OrderDetail orderDetail) {
        return this.orderDetailMapper.deleteByOrderDetail(orderDetail);
    }

    public int save(OrderDetail orderDetail) {
        return this.orderDetailMapper.insert(orderDetail);
    }

    public int saveSelective(OrderDetail orderDetail) {
        return this.orderDetailMapper.insertSelective(orderDetail);
    }
}