package com.usedBooks.manager.auctionModule.service;

import java.util.List;

import com.usedBooks.manager.auctionModule.vo.AuctionHistoryVo;
import com.usedBooks.manager.auctionModule.vo.AuctionVo;
import com.usedBooks.pojo.Auction;
import com.usedBooks.result.Result;
import org.apache.ibatis.session.RowBounds;

public interface AuctionService {
    long countByAuction(Auction auction);

    Auction getByPrimaryKey(Integer id);

    List<Auction> listWithRowbounds(Auction auction, RowBounds rowBounds);

    int removeByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Auction auction);

    int updateByPrimaryKey(Auction auction);

    int removeByAuction(Auction auction);

    int save(Auction auction);

    int saveSelective(Auction auction);

    Result getAuctions(Integer page, Integer length, String classificationId, String userId);

    Result<List<AuctionHistoryVo>> getAuctionHistory(String auctionId);

    Result startAuction(String userId, String auctionId, Double newPrice);

	Result<AuctionVo> getAuctionDetail(String auctionId);
}