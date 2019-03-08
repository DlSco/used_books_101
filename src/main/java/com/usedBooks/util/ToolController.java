package com.usedBooks.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ToolController {
	/**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date);
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}

	/**
	 * 元转换成分
	 * 
	 * @param amount
	 * @return
	 */
	public static String getMoney(String amount) {
		if (amount == null) {
			return "";
		}
		// 金额转化为分为单位
		// 处理包含, ￥ 或者$的金额
		String currency = amount.replaceAll("\\$|\\￥|\\,", "");
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
		}
		return amLong.toString();
	}

	public static String getOrderForm(String prefix){
		return prefix + getOrderNum() + getThree();
	}
	
	
}
