package com.usedBooks.frontStage.shopCart.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity(name = "shop_cart")
@Data
public class ShopCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;             //id

    private Integer userId;        //买家id

    private Double totalAmount;     //总金额

    private Double originalAmount;  //原始总金额


}
