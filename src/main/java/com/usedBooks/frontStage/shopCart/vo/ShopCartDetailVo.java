package com.usedBooks.frontStage.shopCart.vo;

import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;
import lombok.Data;

@Data
public class ShopCartDetailVo extends ShopCartDetail {
    private String bookName;            //书名
    private Double originalPrice;   //原价
    private Double price;           //售价
    private Integer store;          //库存
    private Integer bookId;         //书id
    private String userName;
}
