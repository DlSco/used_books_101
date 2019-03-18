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

package com.usedBooks.manager.dictionaryModule.pojo;


import javax.persistence.*;
import java.io.Serializable;

 @Entity(name = "dictionary")
 public class Dictionary implements Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;        //字典id

     private String dictCode;   //字典代码

     private String dictName;   //字典名称

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public String getDictCode() {
         return dictCode;
     }

     public void setDictCode(String dictCode) {
         this.dictCode = dictCode;
     }

     public String getDictName() {
         return dictName;
     }

     public void setDictName(String dictName) {
         this.dictName = dictName;
     }
 }