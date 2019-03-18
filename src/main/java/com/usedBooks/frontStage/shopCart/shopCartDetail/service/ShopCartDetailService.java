package com.usedBooks.frontStage.shopCart.shopCartDetail.service;

import com.github.pagehelper.PageInfo;
import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;

public interface ShopCartDetailService {

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(ShopCartDetail shopCartDetail);

    PageInfo toList(Integer page, Integer limit, ShopCartDetail shopCartDetail);
}
