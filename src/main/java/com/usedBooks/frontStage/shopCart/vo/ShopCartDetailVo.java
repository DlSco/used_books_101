package com.usedBooks.frontStage.shopCart.vo;

import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;
import lombok.Data;

@Data
public class ShopCartDetailVo extends ShopCartDetail {
    private String name;
    private Double originalPrice;
    private Double price;
}
