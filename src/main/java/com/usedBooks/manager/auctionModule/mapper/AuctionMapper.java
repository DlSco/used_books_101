package com.usedBooks.manager.auctionModule.mapper;

import com.usedBooks.manager.auctionModule.vo.AuctionHistoryVo;
import com.usedBooks.manager.auctionModule.vo.AuctionStatusVo;
import com.usedBooks.manager.auctionModule.vo.AuctionVo;
import com.usedBooks.mybatis.common.BaseMapper;
import com.usedBooks.pojo.Auction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuctionMapper extends BaseMapper<Auction> {

	public List<AuctionVo> getAuctionListByConstant(@Param("userId") String userId,
													@Param("classification") String classification,
													@Param("flag") Boolean flag
													);

	public List<AuctionHistoryVo> getAuctionHistory(@Param("auctionId") String auctionId);

	public void insertAuction(@Param("userId") String userId,
							  @Param("auctionId") String auctionId,
							  @Param("auctionTime") String auctionTime,
							  @Param("oldPrice") String oldPrice,
							  @Param("newPrice") String newPrice
	);

	public AuctionStatusVo getAuctionStatus(@Param("auctionId") String auctionId);
	public AuctionVo getAuctionDetailById(@Param("auctionId") String auctionId);

	public AuctionStatusVo getAuctionStatusWhereNoHistory(@Param("auctionId") String auctionId);
}

