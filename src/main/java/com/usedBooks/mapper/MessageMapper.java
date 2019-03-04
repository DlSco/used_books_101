 /*
 * @Project Name:TODO 
 * @File Name:MessageMapper
 * @Package Name:com.usedBooks.mapper
 * @Date:2019年03月04日 10:36
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.mapper;

import com.usedBooks.pojo.Message;
import org.apache.ibatis.session.RowBounds;
 public interface MessageMapper {
     /**
      * @param message
      * @return  long
      */
     long countByMessage(Message message);

     /**
      * @param message
      * @return  int
      */
     int deleteByMessage(Message message);

     /**
      * @param id
      * @return  int
      */
     int deleteByPrimaryKey(Integer id);

     /**
      * @param record
      * @return  int
      */
     int insert(Message record);

     /**
      * @param record
      * @return  int
      */
     int insertSelective(Message record);

     /**
      * @param message
      * @return  java.util.List<com.usedBooks.pojo.Message>
      */
     java.util.List<Message> selectWithRowbounds(Message message, RowBounds rowBounds);

     /**
      * @param id
      * @return  com.usedBooks.pojo.Message
      */
     Message selectByPrimaryKey(Integer id);

     /**
      * @param record
      * @return  int
      */
     int updateByPrimaryKeySelective(Message record);

     /**
      * @param record
      * @return  int
      */
     int updateByPrimaryKey(Message record);




 }