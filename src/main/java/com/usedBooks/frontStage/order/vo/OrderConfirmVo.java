package com.usedBooks.frontStage.order.vo;

import com.usedBooks.frontStage.shopCart.vo.ShopCartDetailVo;
import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmVo {


    /**
     * Database Column Remarks:
     *   卖家用户名
     * order.seller
     */
    private String seller;

    /**
     * Database Column Remarks:
     *   卖家id
     * order.seller_id
     */
    private Integer sellerId;
    private Double totalPrice;
    private Integer BookAmount;
    private List<ShopCartDetailVo> cartDetailVos;
}
