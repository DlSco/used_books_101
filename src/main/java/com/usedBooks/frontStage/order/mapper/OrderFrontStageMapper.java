package com.usedBooks.frontStage.order.mapper;

import com.usedBooks.mybatis.common.BaseMapper;
import com.usedBooks.pojo.Order;

public interface OrderFrontStageMapper extends BaseMapper<Order> {


    Integer getStore(Integer bookId,Integer publishType);
}
