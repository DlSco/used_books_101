 /*
 * @Project Name:TODO 
 * @File Name:OrderDetailMapper
 * @Package Name:com.hhly.sns.commons.mapper
 * @Date:2019年02月18日 14:06
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.mapper;

import com.usedBooks.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
@Mapper
public interface OrderDetailMapper {
    /**
     * @param orderDetail
     * @return  long
     */
    long countByOrderDetail(OrderDetail orderDetail);

    /**
     * @param orderDetail
     * @return  int
     */
    int deleteByOrderDetail(OrderDetail orderDetail);

    /**
     * @param id
     * @return  int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @param record
     * @return  int
     */
    int insert(OrderDetail record);

    /**
     * @param record
     * @return  int
     */
    int insertSelective(OrderDetail record);

    /**
     * @param orderDetail
     * @return  java.util.List<com.hhly.sns.entity.app.OrderDetail>
     */
    java.util.List<OrderDetail> selectWithRowbounds(OrderDetail orderDetail, RowBounds rowBounds);

    /**
     * @param id
     * @return  com.hhly.sns.entity.app.OrderDetail
     */
    OrderDetail selectByPrimaryKey(Integer id);

    /**
     * @param record
     * @return  int
     */
    int updateByPrimaryKeySelective(OrderDetail record);

    /**
     * @param record
     * @return  int
     */
    int updateByPrimaryKey(OrderDetail record);
}