
package com.usedBooks.mapper;

import com.usedBooks.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;

@Mapper
public interface OrderMapper {
    /**
     * @param order
     * @return  long
     */
    long countByOrder(Order order);

    /**
     * @param order
     * @return  int
     */
    int deleteByOrder(Order order);

    /**
     * @param id
     * @return  int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @param record
     * @return  int
     */
    int insert(Order record);

    /**
     * @param record
     * @return  int
     */
    int insertSelective(Order record);

    /**
     * @param order
     * @return  java.util.List<com.usedBooks.pojo.Order>
     */
    java.util.List<Order> selectWithRowbounds(Order order, RowBounds rowBounds);

    /**
     * @param id
     * @return  com.usedBooks.pojo.Order
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * @param record
     * @return  int
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * @param record
     * @return  int
     */
    int updateByPrimaryKey(Order record);

    /**
     *
     * @param map      参数的map集合
     * @return
     */
    java.util.List<Order> getList(Map<String,Object> map);
}