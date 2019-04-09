package com.usedBooks.frontStage.book.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.usedBooks.pojo.Book;
import lombok.Data;

import java.util.Date;

@Data
public class BookVo extends Book {


    //售价或求购价或竞拍
    private Double price;
    //新旧程度
    private Integer bookOldState;
    //类型，1:求购，2：出售，3：竞拍
    private String publishType;
    //1:待审，2：审核通核过
    private Integer status;
    //1:已下架 ，0:未下架
    private Integer isDrop;

    private String userName;

    private Integer userId;

    private Date createTime;


    private String publishTypeName;

    private String classificationName;

    private String  bookOldStateName;

    private String statusName;

    private String isDropName;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
