 /*
 * @Project Name:TODO 
 * @File Name:OrderMapper
 * @Package Name:com.hhly.sns.commons.mapper
 * @Date:2019年02月18日 14:06
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.mapper;

import com.usedBooks.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
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
     * @return  java.util.List<com.hhly.sns.entity.app.Order>
     */
    java.util.List<Order> selectWithRowbounds(Order order, RowBounds rowBounds);

    /**
     * @param id
     * @return  com.hhly.sns.entity.app.Order
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
}