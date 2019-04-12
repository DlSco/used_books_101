package com.usedBooks.manager.auctionModule.vo;

import lombok.Data;

@Data
public class ResParamsVo {
	private String userId;
	private Integer page;
	private Integer pageSize;
	private String classificationId;
	private String auctionId;
	private Double oldPrice;
	private Double newPrice;
	private Boolean flag;
}
