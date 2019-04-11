package com.usedBooks.manager.auctionModule.vo;

import lombok.Data;

@Data
public class AuctionStatusVo {
	private String beginTime;
	private String endTime;
	private Integer currentPrice;
}
