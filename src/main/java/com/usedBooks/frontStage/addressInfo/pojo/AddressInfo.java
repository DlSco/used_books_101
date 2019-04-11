package com.usedBooks.frontStage.addressInfo.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="addressInfo")
@Data
public class AddressInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                //id

    private String receiver;

    private String receivingPhone;

    private Integer province;

    private String addressDetail;

    private Integer userId;
}
