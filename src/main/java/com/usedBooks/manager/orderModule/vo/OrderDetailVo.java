package com.usedBooks.manager.orderModule.vo;

import com.usedBooks.pojo.OrderDetail;
import lombok.Data;

@Data
public class OrderDetailVo extends OrderDetail {

    private String bookName;
    private Integer  bookId;
    private Double price;
    private Double originalPrice;
}
