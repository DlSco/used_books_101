 /*
 * @Project Name:TODO 
 * @File Name:Dictionary
 * @Package Name:com.usedBooks.pojo
 * @Date:2019年03月07日 21:05
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.pojo;

import java.io.Serializable;

 /**
  *
  * This class was generated by MyBatis Generator.
  *
  * @table dictionary
  * @author Vakoe
  * @date 2019年03月07日 21:05
  */
 public class Dictionary implements Serializable {
     /**
      * Database Column Remarks:
      *   id
      * dictionary.id
      */
     private Integer id;

     /**
      * Database Column Remarks:
      *   字典名称
      * dictionary.dict_name
      */
     private String dictName;

     /**
      * dictionary
      */
     private static final long serialVersionUID = 1L;

     /**
      * @return  java.lang.Integer  id
      */
     public Integer getId() {
         return id;
     }

     /**
      * @param java.lang.Integer  id
      */
     public void setId(Integer id) {
         this.id = id;
     }

     /**
      * @return  java.lang.String  dictName
      */
     public String getDictName() {
         return dictName;
     }

     /**
      * @param java.lang.String  dictName
      */
     public void setDictName(String dictName) {
         this.dictName = dictName;
     }
 }