package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util;

public class DateUtilToChinese {
	
	/**
	 * 将日期转换为一个日期，例如：2014-05-09,2014-05-11转换为2014年5月9日至2014年5月11日
	 * @param beginDate开始日期
	 * @param endDate结束日期
	 * @return
	 */
	public static String convertDate(String beginDate,String endDate){
		String beginStr ="";
		String endStr ="";
		//判断是否两个日期都不为空
		if((beginDate!=null&&!"".equals(beginDate))&&(endDate!=null&&!"".equals(endDate))){
			String[] begins = beginDate.split("-");
			beginStr =begins[0]+" 年 "+begins[1]+" 月 "+begins[2]+" 日 ";
			String[] ends = endDate.split("-");
			endStr =ends[0]+" 年 "+ends[1]+" 月 "+ends[2]+" 日 ";
		}
		return beginStr+" 至 "+endStr;
	}

}
