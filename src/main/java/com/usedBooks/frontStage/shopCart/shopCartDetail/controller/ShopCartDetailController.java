package com.usedBooks.frontStage.shopCart.shopCartDetail.controller;


import com.github.pagehelper.PageInfo;
import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;
import com.usedBooks.frontStage.shopCart.shopCartDetail.service.ShopCartDetailService;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ShopCartDetail")
@RestController
public class ShopCartDetailController {
    @Autowired
    private ShopCartDetailService shopCartDetailService;
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        if(shopCartDetailService.deleteByPrimaryKey(id)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"删除购物车详情失败"));
    }

    @RequestMapping("/update/{id}")
    public Result update(@PathVariable Integer id, ShopCartDetail shopCartDetail){
        shopCartDetail.setId(id);
        if(shopCartDetailService.updateByPrimaryKey(shopCartDetail)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"修改失败"));
    }

    @RequestMapping("/toList")
    public Result toList(Integer page,Integer limit,ShopCartDetail shopCartDetail){
        PageInfo pageInfo = shopCartDetailService.toList(page,limit,shopCartDetail);
        if(pageInfo.getList()==null){
            return Result.error  (new CodeMsg(0,"查询失败!"));
        }
        return Result.success(pageInfo);
    }


}
