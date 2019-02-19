package com.usedBooks.manager.orderModule.controller;


import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.orderModule.service.OrderDetailService;
import com.usedBooks.manager.orderModule.service.OrderService;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;
import com.usedBooks.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Manager/orderModule")
public class OrderController {

    private final Logger logger= LoggerFactory.getLogger(OrderController.class) ;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService detailService;
    @RequestMapping("/getList")
    public Result getList(Integer page,Integer limit,Order order,String key,String value){

        PageInfo<Order> pageInfo = orderService.getList(page,limit,order,key,value);
        logger.info(pageInfo.toString());
        return Result.success(pageInfo);
    }

    @RequestMapping("/getDetail")
    public Result getDetail(Integer page,Integer limit,Integer orderId){

        PageInfo<OrderDetail> pageInfo = detailService.getDetail(page,limit,orderId);
        logger.info(pageInfo.toString());
        return Result.success(pageInfo);
    }

}
