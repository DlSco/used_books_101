package com.usedBooks.manager.orderModule.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;
import org.apache.ibatis.session.RowBounds;

public interface OrderDetailService {
    long countByOrderDetail(OrderDetail orderDetail);

    OrderDetail getByPrimaryKey(Integer id);

    List<OrderDetail> listWithRowbounds(OrderDetail orderDetail, RowBounds rowBounds);

    int removeByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDetail orderDetail);

    int updateByPrimaryKey(OrderDetail orderDetail);

    int removeByOrderDetail(OrderDetail orderDetail);

    int save(OrderDetail orderDetail);

    int saveSelective(OrderDetail orderDetail);

    PageInfo<OrderDetail> getDetail(Integer page, Integer limit, Integer orderId);
}