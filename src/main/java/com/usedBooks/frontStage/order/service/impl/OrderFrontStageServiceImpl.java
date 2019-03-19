package com.usedBooks.frontStage.order.service.impl;


import com.usedBooks.frontStage.order.mapper.OrderFrontStageMapper;
import com.usedBooks.frontStage.order.service.OrderFrontStageService;
import com.usedBooks.manager.orderModule.mapper.OrderDetailMapper;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;
import com.usedBooks.util.ToolController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderFrontStageServiceImpl implements OrderFrontStageService {


    private static Logger logger = LoggerFactory.getLogger(OrderFrontStageServiceImpl.class);
    @Autowired
    private OrderFrontStageMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public int saveOrder(Order order, OrderDetail orderDetail) {
        //设置订单号
        order.setOrderNumber(ToolController.getOrderForm("book101"));
        int orderId = orderMapper.insert(order);
        logger.info("orderId:"+orderId);
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
