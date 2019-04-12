package com.usedBooks.frontStage.order.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestVo {

    private Integer buyerId;//买家id
    private List<Integer> shopCartDetailId;//购物车详情id集合
    private Integer sellerId;//卖家id
    private Double totalAmount;//总金额
    private Double actualAmount;//实际总金额
    private String deliveryMethod;//投递方式
}
