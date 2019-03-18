package com.usedBooks.frontStage.shopCart.service;

import com.usedBooks.frontStage.shopCart.pojo.ShopCart;
import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;

public interface ShopCartService {

    int add(ShopCart shopCart,ShopCartDetail shopCartDetails,Double originalPrice, Double price);

    int delete(Integer id);
}
