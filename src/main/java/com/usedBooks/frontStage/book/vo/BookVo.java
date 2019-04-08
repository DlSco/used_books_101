package com.usedBooks.frontStage.book.vo;

import com.usedBooks.pojo.Book;
import lombok.Data;

@Data
public class BookVo extends Book {


    //售价或求购价或竞拍
    private Double price;
    //新旧程度
    private Integer bookOldState;
    //类型，1:求购，2：出售，3：竞拍
    private String publishType;

    private String publishTypeName;

    private String classificationName;

    private String  bookOldStateName;

}
