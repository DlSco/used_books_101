package com.usedBooks.frontStage.order.mapper;

import com.usedBooks.frontStage.order.vo.OrderConfirmVo;
import com.usedBooks.mybatis.common.BaseMapper;
import com.usedBooks.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderFrontStageMapper extends BaseMapper<Order> {


    Integer getStore(@Param("publishId") Integer publishId, @Param("publishType") Integer publishType);

    List<OrderConfirmVo> toOrderConfirmVoList(@Param("ids") List<Integer> ids );
}
