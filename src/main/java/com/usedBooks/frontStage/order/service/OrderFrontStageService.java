package com.usedBooks.frontStage.order.service;

import com.usedBooks.frontStage.order.vo.OrderConfirmVo;
import com.usedBooks.frontStage.order.vo.OrderRequestVo;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;

import java.util.List;
import java.util.Map;

public interface OrderFrontStageService {

    /**
     * 下订单
     * @param  order           订单实体
     * @param orderDetail     订单详情实体
     */

    int saveOrder(Order order, OrderDetail orderDetail);

    /**
     * 判断库存
     * @param buyQuantity
     * @param publishId
     * @return
     */
    boolean checkStoreEnough(Integer buyQuantity,Integer publishId);



    int addOrderByShopCart(List<OrderRequestVo> requestList);

    Map<String,Object> getOrderConfirmData(List<Integer> shopCartDetailIds);


}
