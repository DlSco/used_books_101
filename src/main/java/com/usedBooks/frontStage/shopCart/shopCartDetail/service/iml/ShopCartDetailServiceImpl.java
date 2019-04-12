package com.usedBooks.frontStage.shopCart.shopCartDetail.service.iml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.exception.GlobalException;
import com.usedBooks.frontStage.order.service.OrderFrontStageService;
import com.usedBooks.frontStage.shopCart.shopCartDetail.mapper.ShopCartDetailMapper;
import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;
import com.usedBooks.frontStage.shopCart.shopCartDetail.service.ShopCartDetailService;
import com.usedBooks.frontStage.shopCart.vo.ShopCartDetailVo;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.util.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopCartDetailServiceImpl implements ShopCartDetailService {

    @Autowired
    private ShopCartDetailMapper shopCartDetailMapper;
    @Autowired
    private OrderFrontStageService orderFrontStageService;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return shopCartDetailMapper.deleteByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(ShopCartDetail shopCartDetail) {
        if(!orderFrontStageService.checkStoreEnough(shopCartDetail.getPurchaseQuantity(),shopCartDetail.getPublishId())){
            throw new GlobalException(new CodeMsg(0,"修改失败，修改量超过库存"));
        }
        return shopCartDetailMapper.updateByPrimaryKey(shopCartDetail);
    }

    @Override
    public PageInfo toList(Integer page, Integer limit, ShopCartDetail shopCartDetail,Integer userId) {
        if(page!=null && limit!=null){
            PageHelper.startPage(page,limit);
        }
        Map<String,Object> map = MyBeanUtils.beanToMap(shopCartDetail);
        map.put("userId",userId);
        List<ShopCartDetailVo> list = shopCartDetailMapper.toList(map);
        return new PageInfo(list);
    }
}
