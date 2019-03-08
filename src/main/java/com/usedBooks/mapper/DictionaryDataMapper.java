 /*
 * @Project Name:TODO 
 * @File Name:DictionaryDataMapper
 * @Package Name:com.usedBooks.common
 * @Date:2019年03月07日 21:05
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.mapper;

import com.usedBooks.pojo.DictionaryData;
import org.apache.ibatis.session.RowBounds;

 public interface DictionaryDataMapper {
     /**
      * @param dictionaryData
      * @return  long
      */
     long countByDictionaryData(DictionaryData dictionaryData);

     /**
      * @param dictionaryData
      * @return  int
      */
     int deleteByDictionaryData(DictionaryData dictionaryData);

     /**
      * @param id
      * @return  int
      */
     int deleteByPrimaryKey(Integer id);

     /**
      * @param record
      * @return  int
      */
     int insert(DictionaryData record);

     /**
      * @param record
      * @return  int
      */
     int insertSelective(DictionaryData record);

     /**
      * @param dictionaryData
      * @return  java.util.List<com.usedBooks.pojo.DictionaryData>
      */
     java.util.List<DictionaryData> selectWithRowbounds(DictionaryData dictionaryData, RowBounds rowBounds);

     /**
      * @param id
      * @return  com.usedBooks.pojo.DictionaryData
      */
     DictionaryData selectByPrimaryKey(Integer id);

     /**
      * @param record
      * @return  int
      */
     int updateByPrimaryKeySelective(DictionaryData record);

     /**
      * @param record
      * @return  int
      */
     int updateByPrimaryKey(DictionaryData record);
 }