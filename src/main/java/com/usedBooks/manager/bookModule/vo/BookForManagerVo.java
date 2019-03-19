package com.usedBooks.manager.bookModule.vo;

import com.usedBooks.pojo.Book;
import lombok.Data;

import java.util.Date;

@Data
public class BookForManagerVo extends Book {

    Integer publishId;         //发布id

    Integer bookId;            //书本id

    Integer userId;            //用户id，代表是谁发布的

    private Integer status;    //1:待审核，2：审核通过

    private Integer isDrop;    //1:已下架 ，0:未下架

    private Date onshelfTime;  //上架时间

    private Date dropshelfTime; //下架时间

    private String description;//描述

    private Date updateTime;//修改时间

    private Date createTime;//创建时间

    private String remark;//发布人说

    private Integer quantity;//数量

    private Integer publishType;//类型，1:求购，2：出售，3：竞拍

    private Double price;//售价或求购价或竞拍

    private Integer bookOldState;//新旧程度

    private String UserName;//用户名
}
