package com.usedBooks.manager.auctionModule.controller;


import com.usedBooks.manager.auctionModule.service.AuctionService;
import com.usedBooks.manager.auctionModule.vo.AuctionHistoryVo;
import com.usedBooks.manager.auctionModule.vo.AuctionVo;
import com.usedBooks.manager.auctionModule.vo.ResParamsVo;
import com.usedBooks.result.Result;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auction")
public class AuctionController {

	@Autowired
	private AuctionService auctionService;

	/**
	 * 分页获取所有的列表
	 * @return
	 */
	@PostMapping("/list")
	public Result<List<AuctionVo>> getAuctions(ResParamsVo resParamsVo){

		log.info("resParamsVo:{}",resParamsVo);
		return auctionService.getAuctions(resParamsVo.getPage(),
				resParamsVo.getPageSize(), resParamsVo.getClassificationId(),resParamsVo.getUserId(),resParamsVo.getFlag());
	}

	@GetMapping("/history/{auctionId}")
	public Result<List<AuctionHistoryVo>> getAuctionHistory(@PathVariable("auctionId") String auctionId){
		return auctionService.getAuctionHistory(auctionId);
	}
	@GetMapping("/detail/{auctionId}")
	public Result<AuctionVo> getAuctionDetail(@PathVariable("auctionId") String auctionId){
		return auctionService.getAuctionDetail(auctionId);
	}

	/**
	 * 开始竞拍
	 * @param resParamsVo
	 * @return
	 */
	@PostMapping("/doAuction")
	public Result doAuction( ResParamsVo resParamsVo){
		return auctionService.startAuction(resParamsVo.getUserId(),resParamsVo.getAuctionId(),
				resParamsVo.getNewPrice());
	}

	/**
	 * 查看竞拍详情
	 */

//	/**
//	 * 查询竞拍状态
//	 * @param resParamsVo
//	 * @return
//	 */
//	@GetMapping("/doAuction")
//	public Result getAuctionStatus(@RequestBody ResParamsVo resParamsVo){
//
//	}

}


