package com.usedBooks.manager.auctionModule.vo;

import lombok.Data;

@Data
public class AuctionStatusVo {
	private String beginTime;
	private String endTime;
	private Integer currentPrice;

	public Integer getCurrentPrice(){
		if(null == currentPrice){
			return 0;
		}
		return currentPrice;
	}
}
