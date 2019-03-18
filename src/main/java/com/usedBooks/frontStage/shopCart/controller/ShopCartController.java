package com.usedBooks.frontStage.shopCart.controller;


import com.usedBooks.frontStage.shopCart.pojo.ShopCart;
import com.usedBooks.frontStage.shopCart.service.ShopCartService;
import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopCart",method = RequestMethod.POST)
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    /**
     * 添加购物车
     * @param shopCart         购物车实体
     * @param shopCartDetail   购物车详情实体
     * @param originalPrice    原始价格
     * @param price            售价
     * @return
     */
    @RequestMapping("/add")
    public Result add(ShopCart shopCart, ShopCartDetail shopCartDetail, Double originalPrice,Double price){
        if(shopCartService.add(shopCart,shopCartDetail,originalPrice,price)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"添加失败"));
    }

    /**
     * 删除购物车
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        if(shopCartService.delete(id)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"删除失败"));
    }



}
