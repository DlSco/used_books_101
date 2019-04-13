package com.usedBooks.frontStage.shopCart.service.impl;

import com.usedBooks.exception.GlobalException;
import com.usedBooks.frontStage.order.service.OrderFrontStageService;
import com.usedBooks.frontStage.shopCart.mapper.ShopCartMapper;
import com.usedBooks.frontStage.shopCart.pojo.ShopCart;
import com.usedBooks.frontStage.shopCart.service.ShopCartService;
import com.usedBooks.frontStage.shopCart.shopCartDetail.mapper.ShopCartDetailMapper;
import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;
import com.usedBooks.result.CodeMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private ShopCartDetailMapper shopCartDetailMapper;
    @Autowired
    private OrderFrontStageService orderFrontStageService;
    @Override
    public int add(ShopCart shopCart, ShopCartDetail shopCartDetail, Double originalPrice, Double price) {

        //判断库存
        if(!orderFrontStageService.checkStoreEnough(shopCartDetail.getPurchaseQuantity(),shopCartDetail.getPublishId())){
            throw new GlobalException(new CodeMsg(0,"添加失败，库存不足"));
        }
        //判断该用户下是否存在购物车
        boolean isExist  = true;
        List<ShopCart> shopCartList = shopCartMapper.select(shopCart);
        if(shopCartList.size() == 0){
            isExist = false;
        }
        if(isExist){
            shopCart = shopCartList.get(0);
            //若存在
            ShopCartDetail temp = new ShopCartDetail();
            temp.setPublishId(shopCartDetail.getPublishId());
            List<ShopCartDetail> list= shopCartDetailMapper.select(temp);
            log.info("购物车详情列表：{}" ,list.toString());
            //添加购物车详情
            shopCartDetail.setShopCartId(shopCart.getId());
            if(list.size()>0){
                BeanUtils.copyProperties(list.get(0),shopCartDetail);
                log.info("从数据库的购物车详情记录复制到当前bean：{}",shopCartDetail);
            }
            Integer result = 0;
            if(shopCartDetail.getId() == null || shopCartDetail.getId() == 0){

                //执行添加操作
                result = shopCartDetailMapper.insert(shopCartDetail);

            }else{
                if(orderFrontStageService.checkStoreEnough(shopCartDetail.getPurchaseQuantity()+1,shopCartDetail.getPublishId())){
                    shopCartDetail.setPurchaseQuantity(shopCartDetail.getPurchaseQuantity()+1);
                }
                result = shopCartDetailMapper.updateByPrimaryKeySelective(shopCartDetail);
            }
            if(result>0){
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
            Integer result = shopCartMapper.insertUseGeneratedKeys(shopCart);
            if(result>0){
                //添加购物车详情
                shopCartDetail.setShopCartId(shopCart.getId());
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
