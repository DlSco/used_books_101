 /*
 * @Project Name:TODO 
 * @File Name:DictionaryItem
 * @Package Name:com.usedBooks.pojo
 * @Date:2019年03月07日 21:05
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.manager.dictionaryModule.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;



 @Entity(name="dictionary_item")
 public class DictionaryItem implements Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;          //字典项目id


     private String itemValue;    //字典项目值


     private Integer dictId;      //字典id


     private String itemName;     //字典项目名

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public String getItemValue() {
         return itemValue;
     }

     public void setItemValue(String itemValue) {
         this.itemValue = itemValue;
     }

     public Integer getDictId() {
         return dictId;
     }

     public void setDictId(Integer dictId) {
         this.dictId = dictId;
     }

     public String getItemName() {
         return itemName;
     }

     public void setItemName(String itemName) {
         this.itemName = itemName;
     }


 }