package com.usedBooks.frontStage.order.service.impl;


import com.usedBooks.frontStage.order.mapper.OrderFrontStageMapper;
import com.usedBooks.frontStage.order.service.OrderFrontStageService;
import com.usedBooks.mapper.OrderDetailMapper;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderFrontStageServiceImpl implements OrderFrontStageService {


    @Autowired
    private OrderFrontStageMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public int saveOrder(Order order, OrderDetail orderDetail) {
        int orderId = orderMapper.insert(order);
        if(orderId>0){
            orderDetail.setOrderId(orderId);
            int result = orderDetailMapper.insert(orderDetail);
            if(result>0){
                return 1;
            }
        }
        return 0;
    }




}
