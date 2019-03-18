package com.usedBooks.frontStage.shopCart.service.impl;

import com.usedBooks.frontStage.shopCart.mapper.ShopCartMapper;
import com.usedBooks.frontStage.shopCart.pojo.ShopCart;
import com.usedBooks.frontStage.shopCart.service.ShopCartService;
import com.usedBooks.frontStage.shopCart.shopCartDetail.mapper.ShopCartDetailMapper;
import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private ShopCartDetailMapper shopCartDetailMapper;
    @Override
    public int add(ShopCart shopCart, ShopCartDetail shopCartDetail, Double originalPrice, Double price) {

        //判断该用户下是否存在购物车
        boolean isExist  = true;
        List<ShopCart> shopCartList = shopCartMapper.select(shopCart);
        if(shopCartList == null){
            isExist = false;
        }
        if(isExist){
            //若存在
            shopCart = shopCartList.get(0);
            //添加购物车详情
            if(shopCartDetailMapper.insert(shopCartDetail)>0){
                //更新购物车表
                shopCart.setTotalAmount(shopCart.getTotalAmount()+price);
                shopCart.setOriginalAmount(shopCart.getOriginalAmount()+originalPrice);
                return shopCartMapper.updateByPrimaryKey(shopCart);
            }

        }else{
            //若不存在
            shopCart.setOriginalAmount(originalPrice);
            shopCart.setTotalAmount(price);
            //添加购物车，返回主键id
            Integer shopCartId = shopCartMapper.insertUseGeneratedKeys(shopCart);
            if(shopCartId>0){
                //添加购物车详情
                shopCartDetail.setShopCartId(shopCartId);
                return shopCartDetailMapper.insert(shopCartDetail);
            }

        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        ShopCartDetail shopCartDetail = new ShopCartDetail();
        shopCartDetail.setShopCartId(id);
        if(shopCartDetailMapper.delete(shopCartDetail)>0){
            return shopCartMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

}
