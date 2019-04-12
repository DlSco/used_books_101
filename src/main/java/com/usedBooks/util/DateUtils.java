package com.usedBooks.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * 日期字符串转化为Date
	 * 
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * String日期 转 Date日期
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static Date transferDateTime(String dateStr) {
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
	/**
	 * Date 转化为String，格式为yyyy-MM-dd
	 * @param date
	 * @return
	 */
    public static String transferDateToString(Date date){
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date);
        return dateStr;
    }
    /**
     * Date 转化为String，格式为yyyy-MM-dd HH:mm:ss
     */
    public static String transferDateToString_YMDHMS(Date date)
    {
    	String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date);
        return dateStr;
    }
    /**
     * Date 转化为String，格式为yyyy-MM
     */
    public static String transferDateToString_YM(Date date)
    {
    	String format = "yyyy-MM";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date);
        return dateStr;
    }
    /**
     * 获取某年某月第一天
     */
    
    public static String getFisrtDayOfMonth(Integer year,Integer month)
    {
      Calendar cal = Calendar.getInstance();
      //设置年份
      cal.set(Calendar.YEAR,year);
      //设置月份
      cal.set(Calendar.MONTH, month-1);
      //获取某月最小天数
      int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
      //设置日历中月份的最小天数
      cal.set(Calendar.DAY_OF_MONTH, firstDay);
      //格式化日期
      String firstDayOfMonth = transferDateToString(cal.getTime());
      return firstDayOfMonth;
    }
    /**
     * 获取某年某月最后一天
     */
    public static String getLastDayOfMonth(Integer year,Integer month)
    {
      Calendar cal = Calendar.getInstance();
      //设置年份
      cal.set(Calendar.YEAR,year);
      //设置月份
      cal.set(Calendar.MONTH, month-1);
      //获取某月最大天数
      int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      //设置日历中月份的最大天数
      cal.set(Calendar.DAY_OF_MONTH, lastDay);
      //格式化日期
      String lastDayOfMonth = transferDateToString(cal.getTime());
      return lastDayOfMonth;
    }
    /**
     * 对时间进行减天数
     */
    public static String operateDate(Integer value,Date date)
    {
    	//减value
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -value);
        String beginDate = transferDateToString(calendar.getTime());
		return beginDate;
    }
}
