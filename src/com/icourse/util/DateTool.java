package com.icourse.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

	public static String formatDate(Date date,String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	//字符串转日期
	public static Date StrToDate(String dateStr,String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		try {
			date = sdf.parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	//计算差多少分钟
	public static int caclDiffMinutes(Long startTime,Long endTime)
	{
		int diffMinutes = 0;
		Long diffTime = startTime > endTime ? startTime - endTime : endTime - startTime;
		diffMinutes = (int) Math.floor(diffTime/(1000*60));		
		return diffMinutes;
	}
	
}
