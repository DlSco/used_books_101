package com.usedBooks.manager.orderModule.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.orderModule.mapper.OrderDetailMapper;
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
        long count = this.orderDetailMapper.selectCount(orderDetail);
        logger.debug("count: {}", count);
        return count;
    }

    public List<OrderDetail> listWithRowbounds(OrderDetail orderDetail, RowBounds rowBounds) {
        return this.orderDetailMapper.selectByRowBounds(orderDetail,rowBounds);
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
        return this.orderDetailMapper.delete(orderDetail);
    }

    public int save(OrderDetail orderDetail) {
        return this.orderDetailMapper.insert(orderDetail);
    }

    public int saveSelective(OrderDetail orderDetail) {
        return this.orderDetailMapper.insertSelective(orderDetail);
    }

    @Override
    public PageInfo<OrderDetail> getDetail(Integer page, Integer limit, Integer orderId) {

        //分页
        if(page!=null&&limit!=null){
            PageHelper.startPage(page,limit);
        }
        List<OrderDetail>list = orderDetailMapper.getDetail(orderId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

}