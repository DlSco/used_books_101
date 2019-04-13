package com.usedBooks.manager.auctionModule.vo;

import lombok.Data;

@Data
public class AuctionVo {
	private String auctionId;
	private String beginTime;
	private String endTime;
	private String bookName;
	private String publishHouse;
	private String author;
	private String isbn;
	private String pictureUrl;
	private String originalPrice;
	private String price;
	private Integer currentAuctionPrice;
	private String userName;
	private String remark;
//	private String bookOldState;
	private String bookOldStateName;
	private String classificationName;
	private Integer userId;
//	private String classificationId;


//	public Double getOriginalPrice(){
//		if(this.originalPrice != null){
//			return this.originalPrice/100.0;
//		}
//		return 0.0d;
//	}
	public Double getCurrentAuctionPrice(){
		if(this.currentAuctionPrice != null){
			return this.currentAuctionPrice/100.0;
		}
		return 0.0d;
	}
//	public Double getPrice(){
//		if(this.price != null){
//			return this.price/100.0;
//		}
//		return 0.0d;
//	}



}
