package com.usedBooks.frontStage.order.service.impl;


import com.usedBooks.exception.GlobalException;
import com.usedBooks.frontStage.book.mapper.PublishMapper;
import com.usedBooks.frontStage.order.enums.PublishEnum;
import com.usedBooks.frontStage.order.mapper.OrderFrontStageMapper;
import com.usedBooks.frontStage.order.service.OrderFrontStageService;
import com.usedBooks.frontStage.order.vo.OrderConfirmVo;
import com.usedBooks.frontStage.order.vo.OrderRequestVo;
import com.usedBooks.frontStage.shopCart.mapper.ShopCartMapper;
import com.usedBooks.frontStage.shopCart.shopCartDetail.mapper.ShopCartDetailMapper;
import com.usedBooks.frontStage.shopCart.vo.ShopCartDetailVo;
import com.usedBooks.frontStage.user.mapper.UserFrontMapper;
import com.usedBooks.manager.orderModule.mapper.OrderDetailMapper;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;
import com.usedBooks.pojo.Publish;
import com.usedBooks.pojo.User;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.util.ToolController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@Transactional
public class OrderFrontStageServiceImpl implements OrderFrontStageService {


    private static Logger logger = LoggerFactory.getLogger(OrderFrontStageServiceImpl.class);
    @Autowired
    private OrderFrontStageMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ShopCartDetailMapper shopCartDetailMapper;
    @Autowired
    private ShopCartMapper shopCartMapper ;
    @Autowired
    private UserFrontMapper userFrontMapper;
    @Autowired
    private PublishMapper publishMapper;

    /**
     * 单个购买
     * @param  order           订单实体
     * @param orderDetail     订单详情实体
     * @return
     */
    @Override
    public int saveOrder(Order order, OrderDetail orderDetail) {
        Integer store = orderMapper.getStore(orderDetail.getPublishId(), PublishEnum.PUBLISH_SELL.getPublishCode());
        //先判断库存
        if(orderDetail.getQuantity()>store){
            throw new GlobalException(new CodeMsg(0,"库存不足"));
        }
        order.setIsCancel(0);
        order.setIsValid(1);
        order.setState(1);
        order.setTime(new Date());
        //设置订单号
        order.setOrderNumber(ToolController.getOrderForm("book101"));
        log.info("order:{}",order);
        orderMapper.insertUseGeneratedKeys(order);
        //返回的订单id
        int orderId = order.getId();
        logger.info("orderId:"+orderId);

        if(orderId>0){
            orderDetail.setOrderId(orderId);
            log.info("orderDetail:{}",orderDetail);
            int result = orderDetailMapper.insert(orderDetail);
            if(result>0){
                if( store > 0 ){
                    //改库存
                    Publish publish = new Publish();
                    publish.setQuantity(store-1);
                    publish.setId(orderDetail.getPublishId());
                    publishMapper.updateByPrimaryKeySelective(publish);
                }
                return 1;
            }
        }else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return 0;
    }


    /**
     * 获取库存
     */
    public boolean checkStoreEnough(Integer buyQuantity,Integer publishId){

        Integer store = orderMapper.getStore(publishId, PublishEnum.PUBLISH_SELL.getPublishCode());
        if(store == 0){
            throw new GlobalException(new CodeMsg(500,"库存为0"));
        }
        if(buyQuantity<=store){
            return true;
        }
        return false;
    }

    /**
     * 购物车下单
     * @return
     */
    @Override
    public int addOrderByShopCart(List<OrderRequestVo> requestList) {

        Date date = new Date();
        //解析数据
        for(OrderRequestVo orderRequestVo :requestList){
            if(orderRequestVo.getBuyerId() == null || orderRequestVo.getActualAmount() == null
                    || orderRequestVo.getTotalAmount()==null || orderRequestVo.getSellerId() == null){
                throw new GlobalException(new CodeMsg(500,"请检查参数非空情况"));
            }
            Order order = this.setOrder(orderRequestVo,date);
            orderMapper.insertUseGeneratedKeys(order);
            //返回的订单id
            int orderId = order.getId();
            log.info("返回的订单id：{}",orderId);
            //获取购物车详情信息
            List<ShopCartDetailVo> shopCartDetailVos = shopCartDetailMapper.findByIds(orderRequestVo.getShopCartDetailId());
            log.info("shopCartDetailVos:[]",shopCartDetailVos);
            List<OrderDetail> list = this.setOrderDetail(shopCartDetailVos,orderId);

            orderDetailMapper.insertList(list);
        }
        return 1;
    }



    /**
     * 设置订单对象
     * @return
     */
    private Order setOrder(OrderRequestVo orderRequestVo,Date date ){

        User buyer = userFrontMapper.selectByPrimaryKey(orderRequestVo.getBuyerId());
        User seller = userFrontMapper.selectByPrimaryKey(orderRequestVo.getSellerId());
        Order order = new Order();
        order.setIsValid(1);
        order.setIsCancel(0);
        order.setOrderNumber(ToolController.getOrderForm("book101"));
        order.setActualAmount(orderRequestVo.getActualAmount());
        order.setBuyerId(orderRequestVo.getBuyerId());
        order.setBuyer(buyer.getUserName());
        order.setState(1);
        order.setOrderAmount(orderRequestVo.getTotalAmount());
        order.setTime(date);
        order.setDeliveryMethod(orderRequestVo.getDeliveryMethod());
        order.setSeller(seller.getUserName());
        order.setSellerId(seller.getId());
        order.setSellerPhone(seller.getPhone());
        log.info("order:{}",order);
        return order;
    }

    /**
     * 设置订单详情对象
     * @param orderId
     * @return
     */
    private List<OrderDetail> setOrderDetail(List<ShopCartDetailVo> list,Integer orderId){

        List<OrderDetail> orderDetails = new LinkedList<>();
        for(ShopCartDetailVo tempVo:list){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setPublishId(tempVo.getBookId());
            orderDetail.setQuantity(tempVo.getPurchaseQuantity());
            //判断库存
            if(orderDetail.getQuantity() > orderMapper.getStore(orderDetail.getPublishId(),
                    PublishEnum.PUBLISH_SELL.getPublishCode())){
                throw new GlobalException(new CodeMsg(0,"库存不足"));
            }

            orderDetail.setTotalprice(tempVo.getPrice()*tempVo.getPurchaseQuantity());
            orderDetails.add(orderDetail);
        }

        return orderDetails;
    }

    /**
     * 订单确认页数据
     */

    public Map<String,Object> getOrderConfirmData(List<Integer> shopCartDetailIds){

        List<OrderConfirmVo> list = orderMapper.toOrderConfirmVoList(shopCartDetailIds);
        Double totalOrderAmount = 0.00;
        for(OrderConfirmVo orderConfirmVo : list){
            totalOrderAmount = totalOrderAmount + orderConfirmVo.getTotalAmount();
        }
        log.info("totalOrderAmount,总订单金额：{}",totalOrderAmount);
        Map<String,Object> map = new HashMap<>();
        map.put("orderConfirmData",list);
        map.put("totalOrderAmount",totalOrderAmount);
        return map;
    }

}
