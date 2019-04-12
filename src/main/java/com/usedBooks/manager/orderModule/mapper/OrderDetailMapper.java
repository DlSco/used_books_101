 /*
 * @Project Name:TODO 
 * @File Name:OrderDetailMapper
 * @Package Name:com.hhly.sns.commons.common
 * @Date:2019年02月18日 14:06
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.manager.orderModule.mapper;

import com.usedBooks.manager.orderModule.vo.OrderDetailVo;
import com.usedBooks.mybatis.common.BaseMapper;
import com.usedBooks.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

 @Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {


    List<OrderDetailVo> getDetail(@Param("orderId") Integer orderId);
}