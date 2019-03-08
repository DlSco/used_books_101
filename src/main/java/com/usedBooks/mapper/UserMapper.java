 /*
 * @Project Name:TODO 
 * @File Name:UserMapper
 * @Package Name:com.usedBooks.mapper
 * @Date:2019年03月03日 11:14
 * @Creator:hengzi
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.mapper;

import com.usedBooks.pojo.User;
import org.apache.ibatis.session.RowBounds;

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
     int deleteByPrimaryKey(String id);

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
     User selectByPrimaryKey(String id);

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
 }