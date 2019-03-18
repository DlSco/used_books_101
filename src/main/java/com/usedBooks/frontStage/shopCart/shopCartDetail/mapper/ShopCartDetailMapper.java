package com.usedBooks.frontStage.shopCart.shopCartDetail.mapper;

import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;
import com.usedBooks.frontStage.shopCart.vo.ShopCartDetailVo;
import com.usedBooks.mybatis.common.BaseMapper;

import java.util.List;
import java.util.Map;

public interface ShopCartDetailMapper extends BaseMapper<ShopCartDetail> {

    List<ShopCartDetailVo> toList(Map<String,Object> map);
}
