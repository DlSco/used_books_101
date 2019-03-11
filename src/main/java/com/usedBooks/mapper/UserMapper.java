 /*
 * @Project Name:TODO 
 * @File Name:UserMapper
 * @Package Name:com.usedBooks.mapper
 * @Date:2019年03月08日 17:35
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.mapper;

import com.usedBooks.pojo.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

 public interface UserMapper {
     /**
      * @param user
      * @return  long
      */
     long countByUser(User user);

     /**
      * @param user
      * @return  int
      */
     int deleteByUser(User user);

     /**
      * @param id
      * @return  int
      */
     int deleteByPrimaryKey(Integer id);

     /**
      * @param record
      * @return  int
      */
     int insert(User record);

     /**
      * @param record
      * @return  int
      */
     int insertSelective(User record);

     /**
      * @param user
      * @return  java.util.List<com.usedBooks.pojo.User>
      */
     java.util.List<User> selectWithRowbounds(User user, RowBounds rowBounds);

     /**
      * @param id
      * @return  com.usedBooks.pojo.User
      */
     User selectByPrimaryKey(Integer id);

     /**
      * @param record
      * @return  int
      */
     int updateByPrimaryKeySelective(User record);

     /**
      * @param record
      * @return  int
      */
     int updateByPrimaryKey(User record);

     List<User> toList(Map<String,Object> map);
 }