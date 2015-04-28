package com.ttsoft.bjtax.smsb.lwpk.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SJJHWHCommon {
	//结果集
	//时间列表
	List zxsjList = new ArrayList();
	//类型列表
	List zxlxList = new ArrayList();
	
	//查询列表
	//月度列表
	List cxydList = new ArrayList();
	//类型列表
	List cxlxList = new ArrayList();
	//类型列表
	List cxlxmcList = new ArrayList();

	//时间列表
	public List getZxsjList() {
		
		zxsjList.add("00:00");
		zxsjList.add("01:00");
		zxsjList.add("02:00");
		zxsjList.add("03:00");
		zxsjList.add("04:00");
		zxsjList.add("05:00");
		zxsjList.add("06:00");
		zxsjList.add("07:00");
		zxsjList.add("08:00");
		zxsjList.add("09:00");
		zxsjList.add("10:00");
		zxsjList.add("11:00");
		zxsjList.add("12:00");
		zxsjList.add("13:00");
		zxsjList.add("14:00");
		zxsjList.add("15:00");
		zxsjList.add("16:00");
		zxsjList.add("17:00");
		zxsjList.add("18:00");
		zxsjList.add("19:00");
		zxsjList.add("20:00");
		zxsjList.add("21:00");
		zxsjList.add("22:00");
		zxsjList.add("23:00");
		return zxsjList;
	}
	//类型列表
	public List getZxlxList() {
		Map map = new HashMap();
		map.put("01", "生成待扣信息时间");	
		map.put("02", "发送扣款信息时间");	
		zxlxList.add(map);
		
		return zxlxList;
	}
	//月度列表
	public List getCxydList() {
		cxydList.add("00");
		cxydList.add("01");
		cxydList.add("02");
		cxydList.add("03");
		cxydList.add("04");
		cxydList.add("05");
		cxydList.add("06");
		cxydList.add("07");
		cxydList.add("08");
		cxydList.add("09");
		cxydList.add("10");
		cxydList.add("11");
		cxydList.add("12");
		return cxydList;
	}
	//查询任务类型列表
	public List getCxlxList() {
		cxlxList.add("00");
		cxlxList.add("01");
		cxlxList.add("02");
		return cxlxList;
	}
	//查询任务类型名称列表
	public List getCxlxmcList() {
		cxlxmcList.add("全部");
		cxlxmcList.add("生成待扣信息时间");
		cxlxmcList.add("发送扣款信息时间");
		return cxlxmcList;
	}
	
}
