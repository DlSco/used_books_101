
package com.usedBooks.mapper;


import com.usedBooks.pojo.Auction;
import org.apache.ibatis.session.RowBounds;

public interface AuctionMapper {
    /**
     * @param auction
     * @return  long
     */
    long countByAuction(Auction auction);

    /**
     * @param auction
     * @return  int
     */
    int deleteByAuction(Auction auction);

    /**
     * @param id
     * @return  int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @param record
     * @return  int
     */
    int insert(Auction record);

    /**
     * @param record
     * @return  int
     */
    int insertSelective(Auction record);

    /**
     * @param auction
     * @return  java.util.List<com.hhly.sns.entity.app.Auction>
     */
    java.util.List<Auction> selectWithRowbounds(Auction auction, RowBounds rowBounds);

    /**
     * @param id
     * @return  com.hhly.sns.entity.app.Auction
     */
    Auction selectByPrimaryKey(Integer id);

    /**
     * @param record
     * @return  int
     */
    int updateByPrimaryKeySelective(Auction record);

    /**
     * @param record
     * @return  int
     */
    int updateByPrimaryKey(Auction record);
}