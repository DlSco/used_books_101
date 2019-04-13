package com.usedBooks.frontStage.order.controller;


import com.usedBooks.frontStage.order.service.OrderFrontStageService;
import com.usedBooks.frontStage.order.vo.OrderRequestVo;
import com.usedBooks.manager.orderModule.service.OrderService;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping(value="/order",method = RequestMethod.POST)
@RestController
public class OrderFrontStageController {

    @Autowired
    private OrderFrontStageService orderFrontStageService;
    @Autowired
    private OrderService orderService;

    /**
     * 单个下单
     * @param order
     * @param orderDetail
     * @return
     */

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

    /**
     * 判断库存
     */
    @RequestMapping("/checkStoreEnough")
    public Result  checkStoreEnough(Integer buyQuantity,Integer publishId){
        if(!orderFrontStageService.checkStoreEnough(buyQuantity,publishId)){
            return Result.error(new CodeMsg(0,"库存不足"));
        }
        return Result.success(null);
    }

    /**
     * 购物车下单
     */
    @RequestMapping("/addByShopCart")
    public Result addByShopCart(@RequestParam("requestList[]") List<OrderRequestVo> requestList){

        log.info("传过来的数据：{}",requestList.toString());
        return Result.success(orderFrontStageService.addOrderByShopCart(requestList));

    }

    /**
     * 订单确认页数据
     */
    @RequestMapping("/getOrderConfirmData")
    public Result getOrderConfirmData(@RequestParam("shopCartDetailIds") List<Integer> shopCartDetailIds){

        log.info("传过来的数据：{}",shopCartDetailIds.toString());
        return Result.success(orderFrontStageService.getOrderConfirmData(shopCartDetailIds));
    }


    @RequestMapping("/toDetail/{orderId}")
    public Result getDetail(@PathVariable Integer orderId,Integer addressInfoId){

        Map map = orderFrontStageService.getDetail(orderId,addressInfoId);
        log.info(map.toString());
        return Result.success(map);
    }
}


