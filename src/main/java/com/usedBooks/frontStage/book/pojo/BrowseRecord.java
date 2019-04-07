package com.usedBooks.frontStage.book.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 浏览记录表
 */
@Data
@Entity(name="brows_record")
public class BrowseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//id

    private Integer bookId;//书籍id

    private Integer publishTyep;//发布类型

    private Integer browseNum;//浏览数

}
