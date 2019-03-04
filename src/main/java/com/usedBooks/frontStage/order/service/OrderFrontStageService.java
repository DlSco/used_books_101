package com.usedBooks.frontStage.order.service;

import com.github.pagehelper.PageInfo;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;

public interface OrderFrontStageService {

    /**
     * 下订单
     * @param  order           订单实体
     * @param orderDetail     订单详情实体
     */

    int saveOrder(Order order, OrderDetail orderDetail);





}
