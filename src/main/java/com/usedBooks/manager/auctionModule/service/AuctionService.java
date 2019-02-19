package com.usedBooks.manager.auctionModule.service;

import java.util.List;

import com.usedBooks.pojo.Auction;
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
}