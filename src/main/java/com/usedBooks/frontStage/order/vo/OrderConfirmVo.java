package com.usedBooks.frontStage.order.vo;

import com.usedBooks.frontStage.shopCart.vo.ShopCartDetailVo;
import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmVo {

    private Integer shopCartId;
    private String seller;
    private Integer sellerId;
    private Double totalAmount;
    private Integer bookAmount;
    private List<ShopCartDetailVo> cartDetailVos;
}
