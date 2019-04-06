package com.usedBooks.frontStage.book.vo;

import lombok.Data;

@Data
public class BookSearchVo {

    private Integer bookId;            //书本id
    private Integer userId;            //用户id，代表是谁发布的
    private Integer status;    //1:待审核，2：审核通过
    private Integer isDrop;    //1:已下架 ，0:未下架
    private Integer publishType;//类型，1:求购，2：出售，3：竞拍

}
