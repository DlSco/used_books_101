package com.usedBooks.frontStage.order.vo;

import com.usedBooks.frontStage.shopCart.vo.ShopCartDetailVo;
import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmVo {

    private String seller;
    private Integer sellerId;
    private Double totalPrice;
    private Integer BookAmount;
    private List<ShopCartDetailVo> cartDetailVos;
}
