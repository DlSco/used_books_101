 /*
 * @Project Name:TODO 
 * @File Name:DictionaryMapper
 * @Package Name:com.usedBooks.common
 * @Date:2019年03月07日 21:05
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.mapper;

import com.usedBooks.pojo.Dictionary;
import org.apache.ibatis.session.RowBounds;

 public interface DictionaryMapper {
     /**
      * @param dictionary
      * @return  long
      */
     long countByDictionary(Dictionary dictionary);

     /**
      * @param dictionary
      * @return  int
      */
     int deleteByDictionary(Dictionary dictionary);

     /**
      * @param id
      * @return  int
      */
     int deleteByPrimaryKey(Integer id);

     /**
      * @param record
      * @return  int
      */
     int insert(Dictionary record);

     /**
      * @param record
      * @return  int
      */
     int insertSelective(Dictionary record);

     /**
      * @param dictionary
      * @return  java.util.List<com.usedBooks.pojo.Dictionary>
      */
     java.util.List<Dictionary> selectWithRowbounds(Dictionary dictionary, RowBounds rowBounds);

     /**
      * @param id
      * @return  com.usedBooks.pojo.Dictionary
      */
     Dictionary selectByPrimaryKey(Integer id);

     /**
      * @param record
      * @return  int
      */
     int updateByPrimaryKeySelective(Dictionary record);

     /**
      * @param record
      * @return  int
      */
     int updateByPrimaryKey(Dictionary record);
 }