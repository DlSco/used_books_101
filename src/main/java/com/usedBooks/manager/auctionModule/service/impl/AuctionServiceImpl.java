package com.usedBooks.manager.auctionModule.service.impl;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.auctionModule.mapper.AuctionMapper;
import com.usedBooks.manager.auctionModule.service.AuctionService;
import com.usedBooks.manager.auctionModule.vo.AuctionHistoryVo;
import com.usedBooks.manager.auctionModule.vo.AuctionStatusVo;
import com.usedBooks.manager.auctionModule.vo.AuctionVo;
import com.usedBooks.pojo.Auction;
import com.usedBooks.result.Result;
import com.usedBooks.util.DateUtils;
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

	/**
	 * 获取详情
	 * @param auctionId
	 * @return
	 */
    public Result<AuctionVo> getAuctionDetail(String auctionId){
		Result<AuctionVo> result = new Result<>();
    	try{
			if(null == auctionId || auctionId.isEmpty()){
				result.setCode(400);
				result.setMsg("请求参数不合法！");
				return result;
			}

			AuctionVo auctionVo = auctionMapper.getAuctionDetailById(auctionId);

			if(null == auctionVo){
				result.setCode(400);
				result.setMsg("该竞品不存在！");
				return result;
			}

			result.setCode(200);
			result.setMsg("ok");
			result.setData(auctionVo);
			return result;

		}catch (Exception e){
			logger.error("竞拍查看详情操作异常：{}",e);
			result.setCode(500);
			result.setMsg("系统异常！");
			return result;
		}
	}

	public Result startAuction(String userId, String auctionId, Double newPrice){
		Result<String> result = new Result<>();
		// 如何处理并发的方案？暂时不考虑
		// 获取该竞拍的发布信息
		// 没有人竞拍过
		try {
			AuctionStatusVo auctionStatus = auctionMapper.getAuctionStatus(auctionId);
			if (null == auctionStatus) {
				auctionStatus = auctionMapper.getAuctionStatusWhereNoHistory(auctionId);
			}

			if (null == auctionStatus) {
				result.setCode(400);
				result.setMsg("竞拍不存在！");
				return result;
			}

			Date date = new Date();
			if (DateUtils.transferDateTime(auctionStatus.getEndTime()).getTime() < date.getTime()) {
				result.setCode(400);
				result.setMsg("竞拍已结束！");
				return result;
			}
			if (auctionStatus.getCurrentPrice() / 100.0 > newPrice) {
				result.setCode(400);
				result.setMsg("竞拍价格请大于" + auctionStatus.getCurrentPrice() / 100.0 + "！");
				return result;
			}

			auctionMapper.insertAuction(userId,
					auctionId,
					DateUtils.transferDateToString_YMDHMS(date),
					auctionStatus.getCurrentPrice().toString(),
					newPrice * 100 + "");
			result.setCode(200);
			result.setMsg("ok");
			result.setData("竞拍成功！");
			return result;

		}catch (Exception e){
			logger.error("竞拍出错操作异常：{}",e);
			result.setCode(500);
			result.setMsg("系统异常！");
			return result;
		}
	}

	public Result<List<AuctionHistoryVo>> getAuctionHistory(String auctionId){
		Result<List<AuctionHistoryVo>> result = new Result<>();
		if(null == auctionId || auctionId.isEmpty()){
			result.setCode(400);
			result.setMsg("请求参数不合法！");
			return result;
		}

		try{
			List<AuctionHistoryVo> auctionHistory = auctionMapper.getAuctionHistory(auctionId);
			if(null == auctionHistory || auctionHistory.size() == 0){
				result.setCode(201);
				result.setMsg("暂无历史数据！");
				return result;
			}

			result.setCode(200);
			result.setMsg("ok");
			result.setData(auctionHistory);

			return result;

		}catch (Exception e){
			logger.error("历史数据获取数据库操作异常：{}",e);
			result.setCode(500);
			result.setMsg("系统异常！");
			return result;
		}
	}
	/**
	 * 获取列表
	 * 注意这里数据字典的类型现在在mapper中是写死的
	 */
	public Result getAuctions(Integer page, Integer length, String classificationId, String userId){

		Result result = new Result<>();

		if(null == page || null == length || page < 1 || length <1){

			result.setCode(400);
			result.setMsg("请求参数不合法！");
			return result;
		}

		if(null != classificationId && classificationId.isEmpty()){
			classificationId = null;
		}

		if(null != userId && userId.isEmpty()){
			userId = null;
		}


		// 如果类别id 或者用户id为空的就是获取所有的
		try{

			if(page!=null && length!=null){
				PageHelper.startPage(page,length);
			}
			List<AuctionVo> auctions = auctionMapper.getAuctionListByConstant(userId,
					classificationId);

			logger.info("查询结果为：{}",auctions);
			if(null == auctions || auctions.size() == 0){
				result.setCode(201);
				result.setMsg("数据为空！");
				return result;
			}


			result.setCode(200);
			result.setMsg("ok");
			result.setData(new PageInfo(auctions) );

			return result;
		}catch (Exception e){
			logger.error("竞拍列表数据获取数据库操作异常：{}",e);
			result.setCode(500);
			result.setMsg("系统异常！");
			return result;
		}
	}














}