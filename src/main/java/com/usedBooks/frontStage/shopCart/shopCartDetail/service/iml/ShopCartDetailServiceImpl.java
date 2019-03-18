package com.usedBooks.frontStage.shopCart.shopCartDetail.service.iml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.frontStage.shopCart.shopCartDetail.mapper.ShopCartDetailMapper;
import com.usedBooks.frontStage.shopCart.shopCartDetail.pojo.ShopCartDetail;
import com.usedBooks.frontStage.shopCart.shopCartDetail.service.ShopCartDetailService;
import com.usedBooks.frontStage.shopCart.vo.ShopCartDetailVo;
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
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return shopCartDetailMapper.deleteByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(ShopCartDetail shopCartDetail) {
        return shopCartDetailMapper.updateByPrimaryKey(shopCartDetail);
    }

    @Override
    public PageInfo toList(Integer page, Integer limit, ShopCartDetail shopCartDetail) {
        if(page!=null && limit!=null){
            PageHelper.startPage(page,limit);
        }
        Map<String,Object> map = MyBeanUtils.beanToMap(shopCartDetail);
        List<ShopCartDetailVo> list = shopCartDetailMapper.toList(map);
        return new PageInfo(list);
    }
}
