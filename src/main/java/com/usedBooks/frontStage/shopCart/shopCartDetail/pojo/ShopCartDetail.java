package com.usedBooks.frontStage.shopCart.shopCartDetail.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "shop_cart_detail")
public class ShopCartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                     //id

    private Integer shopCartId;             //购物车id

    private Integer bookId;                 //书籍id

    private Integer  purchaseQuantity;        //购买数量

    private Integer sellerId;               //卖家id
}
