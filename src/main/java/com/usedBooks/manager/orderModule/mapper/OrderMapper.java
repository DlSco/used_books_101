
package com.usedBooks.manager.orderModule.mapper;

import com.usedBooks.mybatis.common.BaseMapper;
import com.usedBooks.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     *
     * @param map      参数的map集合
     * @return
     */
    java.util.List<Order> getList(Map<String,Object> map);
}