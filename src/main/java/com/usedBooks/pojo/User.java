 /*
 * @Project Name:TODO 
 * @File Name:User
 * @Package Name:com.usedBooks.pojo
 * @Date:2019年03月03日 11:14
 * @Creator:hengzi
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.pojo;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

 /**
  *
  * This class was generated by MyBatis Generator.
  *
  * @table user
  * @author hengzi
  * @date 2019年03月03日 11:14
  */
 @Data
 @Entity(name="user")
 public class User implements Serializable {
     /**
      * Database Column Remarks:
      *   主键ID
      * user.id
      */
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     /**
      * Database Column Remarks:
      *   用户编号
      * user.user_no
      */
     private String userNo;

     /**
      * Database Column Remarks:
      *   用户名
      * user.user_name
      */
     private String userName;

     /**
      * Database Column Remarks:
      *   电话号码
      * user.phone
      */
     private String phone;

     /**
      * Database Column Remarks:
      *   性别 1：男，2：女
      * user.gender
      */
     private Integer gender;

     /**
      * Database Column Remarks:
      *   密码
      * user.password
      */
     private String password;

     /**
      * Database Column Remarks:
      *   盐值
      * user.salt
      */
     private String salt;

     /**
      * Database Column Remarks:
      *   邮箱
      * user.email
      */
     private String email;
     /**
      * 是否冻结，1：是，0：否
      */
     private Integer isDelete;

     /**
      * user
      */
     private static final long serialVersionUID = 1L;


 }