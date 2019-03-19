package com.usedBooks.manager.auctionModule.service.impl;

import java.util.List;

import com.usedBooks.manager.auctionModule.mapper.AuctionMapper;
import com.usedBooks.manager.auctionModule.service.AuctionService;
import com.usedBooks.pojo.Auction;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    private AuctionMapper auctionMapper;

    private static final Logger logger = LoggerFactory.getLogger(AuctionServiceImpl.class);

    public Auction getByPrimaryKey(Integer id) {
        return this.auctionMapper.selectByPrimaryKey(id);
    }

    public long countByAuction(Auction auction) {
        long count = this.auctionMapper.selectCount(auction);
        logger.debug("count: {}", count);
        return count;
    }

    public List<Auction> listWithRowbounds(Auction auction, RowBounds rowBounds) {
        return this.auctionMapper.selectByRowBounds(auction,rowBounds);
    }

    public int removeByPrimaryKey(Integer id) {
        return this.auctionMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Auction auction) {
        return this.auctionMapper.updateByPrimaryKeySelective(auction);
    }

    public int updateByPrimaryKey(Auction auction) {
        return this.auctionMapper.updateByPrimaryKey(auction);
    }

    public int removeByAuction(Auction auction) {
        return this.auctionMapper.delete(auction);
    }

    public int save(Auction auction) {
        return this.auctionMapper.insert(auction);
    }

    public int saveSelective(Auction auction) {
        return this.auctionMapper.insertSelective(auction);
    }
}