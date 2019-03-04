package com.usedBooks.frontStage.order.controller;


import com.usedBooks.frontStage.order.service.OrderFrontStageService;
import com.usedBooks.manager.orderModule.service.OrderService;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/order",method = RequestMethod.POST)
@RestController
public class OrderFrontStageController {

    @Autowired
    private OrderFrontStageService orderFrontStageService;
    @Autowired
    private OrderService orderService;
   /* @RequestMapping("/toList")
    public Result toList(Order order){
        return null;
    }*/

    @PostMapping("/add")
    public Result add(Order order, OrderDetail orderDetail){
        if(orderFrontStageService.saveOrder(order,orderDetail)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"下订单失败!!"));
    }

    /**
     * 通用的发货，申请取消订单，修改订单有效性的接口
     * @param order
     * @return
     */
    @RequestMapping(value = "/updateOrder",method = RequestMethod.POST)
    public Result updateOrder(Order order){
        if(orderService.updateByPrimaryKeySelective(order)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"修改失败"));
    }
}


