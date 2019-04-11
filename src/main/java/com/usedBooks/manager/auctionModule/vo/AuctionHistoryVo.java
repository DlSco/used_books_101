package com.usedBooks.manager.auctionModule.vo;

import lombok.Data;

@Data
public class AuctionHistoryVo {

	private String userName;
	private String auctionTime;
	private Integer oldPrice;
	private Integer newPrice;

	public Double getOldPrice(){
		if(this.oldPrice != null) {
			return this.oldPrice/100.0;
		}
		return 0d;
	}

	// 计算
	public Double getNewPrice(){
		if(this.newPrice != null) {
			return this.newPrice/100.0;
		}
		return 0d;
	}
}
