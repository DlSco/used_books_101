 /*
 * @Project Name:TODO 
 * @File Name:StudentsMapper
 * @Package Name:com.hhly.sns.commons.mapper
 * @Date:2019年02月14日 16:22
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.frontStage.students.mapper;

import com.usedBooks.pojo.Students;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

 public interface StudentsMapper {
     /**
      * @param students
      * @return  long
      */
     long countByStudents(Students students);

     /**
      * @param students
      * @return  int
      */
     int deleteByStudents(Students students);

     /**
      * @param id
      * @return  int
      */
     int deleteByPrimaryKey(Integer id);

     /**
      * @param record
      * @return  int
      */
     int insert(Students record);

     /**
      * @param record
      * @return  int
      */
     int insertSelective(Students record);

     /**
      * @param students
      * @return  java.util.List<com.hhly.sns.entity.app.Students>
      */
     java.util.List<Students> selectWithRowbounds(Students students, RowBounds rowBounds);

     /**
      * @param id
      * @return  com.hhly.sns.entity.app.Students
      */
     //@Select("select * from students where id = #{id}")
     Students selectByPrimaryKey(@Param("id") Integer id);

     /**
      * @param record
      * @return  int
      */
     int updateByPrimaryKeySelective(Students record);

     /**
      * @param record
      * @return  int
      */
     int updateByPrimaryKey(Students record);
 }