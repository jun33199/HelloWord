package com.ttsoft.bjtax.shenbao.fangtu;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.LabelValueXMLVO;
import com.ttsoft.framework.util.StringUtil;

public class Utils {

	private static Logger logger = Logger.getLogger(Utils.class);

	private static final String rowSep = "^$$";

	private static final String colSep = "$^^";

	private static final String labelSep = "!^";
	
	/**
	 * 外资企业登记注册类型代码map
	 */
	private static final Map wzMap=Utils.getWzqyDjzclxdm();
	
	/**
	 * 是否为外资企业
	 * @return 1- 外资 0--非外资
	 */
	public static String isWzqy(String djzclxdm){
		if(wzMap.get(djzclxdm)!=null){
			return ConstantFangtu.WZQY_FLAG_YES;
		}else{
			return ConstantFangtu.WZQY_FLAG_NO;
		}
	}
	
	/**
	 * 将页面传过来的字符串装在map中
	 * 
	 * @param str
	 * @return
	 */
	public static Map toMap(String str) {
		Map m = new HashMap();

		String[][] arr = StringUtil.getTableDataSource(str);
		for (int i = 0; i < arr.length; i++) {
			String[] row = arr[i];
			for (int j = 0; j < row.length; j++) {
				String pair = row[j];
				// logger.debug("pair: " + pair);

				String[] p = StringUtil.splitBySeparator(pair, labelSep);
				// logger.debug("p size: " + p.length);
				// logger.debug("p[0]: " + p[0]);

				if (p.length == 2) {
					m.put(p[0], p[1]);
				} else {
					m.put(p[0], null);
				}

				// logger.debug("m.get(p[0]): " + m.get(p[0]));
			}
		}

		return m;
	}

	/**
	 * 取得应税变更列表
	 * 
	 * @return
	 */
	public static List getYingShuiList() {
		List list = new ArrayList();
		list.add(new LabelValueXMLVO("---", ""));
		list.add(new LabelValueXMLVO("新增", "0"));
		list.add(new LabelValueXMLVO("减少", "1"));
		list.add(new LabelValueXMLVO("原值变更", "2"));
		return list;
	}

	/**
	 * 取得减免列表
	 * 
	 * @return
	 */
	public static List getJianMianList() {
		List list = new ArrayList();
		list.add(new LabelValueXMLVO("---", ""));
		list.add(new LabelValueXMLVO("法定", "0"));
		list.add(new LabelValueXMLVO("大修", "1"));
		list.add(new LabelValueXMLVO("其他", "2"));
		return list;
	}

	/**
	 * 取得减免面积列表
	 * 
	 * @return
	 */
	public static List getJianMianMJList() {
		List list = new ArrayList();
		list.add(new LabelValueXMLVO("---", ""));
		list.add(new LabelValueXMLVO("法定", "0"));
		list.add(new LabelValueXMLVO("搬迁", "1"));
		list.add(new LabelValueXMLVO("荒山", "2"));
		list.add(new LabelValueXMLVO("其他", "3"));
		return list;
	}

	/**
	 * 取得应税面积变更列表
	 * 
	 * @return
	 */
	public static List getYingShuiMJList() {
		List list = new ArrayList();
		list.add(new LabelValueXMLVO("---", ""));
		list.add(new LabelValueXMLVO("新增", "0"));
		list.add(new LabelValueXMLVO("减少", "1"));
		list.add(new LabelValueXMLVO("面积变更", "2"));
		return list;
	}

	/**
	 * 取得代缴列表
	 * 
	 * @return
	 */
	public static List getDaiJiaoList() {
		List list = new ArrayList();
		list.add(new LabelValueXMLVO("否", "1"));
		list.add(new LabelValueXMLVO("是", "0"));
		return list;
	}

	public static List getRegionList() {
		List list = new ArrayList();

		list.add(new LabelValueXMLVO("东城区", "01"));
		list.add(new LabelValueXMLVO("西城区", "02"));
		list.add(new LabelValueXMLVO("崇文区", "03"));
		list.add(new LabelValueXMLVO("宣武区", "04"));
		list.add(new LabelValueXMLVO("朝阳区", "05"));
		list.add(new LabelValueXMLVO("海淀区", "06"));
		list.add(new LabelValueXMLVO("丰台区", "07"));
		list.add(new LabelValueXMLVO("石景山区", "08"));
		list.add(new LabelValueXMLVO("门头沟区", "09"));
		list.add(new LabelValueXMLVO("燕山", "10"));
		list.add(new LabelValueXMLVO("昌平区", "11"));
		list.add(new LabelValueXMLVO("通州区", "12"));
		list.add(new LabelValueXMLVO("顺义区", "13"));
		list.add(new LabelValueXMLVO("大兴区", "14"));
		list.add(new LabelValueXMLVO("房山区", "15"));
		list.add(new LabelValueXMLVO("怀柔区", "16"));
		list.add(new LabelValueXMLVO("密云区", "17"));
		list.add(new LabelValueXMLVO("平谷区", "18"));
		list.add(new LabelValueXMLVO("延庆区", "19"));
		list.add(new LabelValueXMLVO("开发区", "20"));
		list.add(new LabelValueXMLVO("西站", "21"));

		return list;
	}

	public static List getRegionZlqxList() {
		List list = new ArrayList();

		list.add(new LabelValueBean("东城区", "01"));
		list.add(new LabelValueBean("西城区", "02"));
		list.add(new LabelValueBean("崇文区", "03"));
		list.add(new LabelValueBean("宣武区", "04"));
		list.add(new LabelValueBean("朝阳区", "05"));
		list.add(new LabelValueBean("海淀区", "06"));
		list.add(new LabelValueBean("丰台区", "07"));
		list.add(new LabelValueBean("石景山区", "08"));
		list.add(new LabelValueBean("门头沟区", "09"));
		list.add(new LabelValueBean("燕山", "10"));
		list.add(new LabelValueBean("昌平区", "11"));
		list.add(new LabelValueBean("通州区", "12"));
		list.add(new LabelValueBean("顺义区", "13"));
		list.add(new LabelValueBean("大兴区", "14"));
		list.add(new LabelValueBean("房山区", "15"));
		list.add(new LabelValueBean("怀柔区", "16"));
		list.add(new LabelValueBean("密云区", "17"));
		list.add(new LabelValueBean("平谷区", "18"));
		list.add(new LabelValueBean("延庆区", "19"));
		list.add(new LabelValueBean("开发区", "20"));
		list.add(new LabelValueBean("西站", "21"));

		return list;
	}
	
	public static List getSlList() {
		List list = new ArrayList();

		list.add(new LabelValueXMLVO("30.00", "30.00"));
		list.add(new LabelValueXMLVO("24.00", "24.00"));
		list.add(new LabelValueXMLVO("18.00", "18.00"));
		list.add(new LabelValueXMLVO("12.00", "12.00"));
		list.add(new LabelValueXMLVO("3.00", "3.00"));
		list.add(new LabelValueXMLVO("1.50", "1.50"));
		
		return list;
	}
	
	/**
	 * 转换string 到 number ，如果为空，返回0
	 * 
	 * @param str
	 * @return
	 */
	public static BigDecimal string2Number(String str) {
		if (str == null || str.trim().equals("")) {
			return new BigDecimal(0);
		}
		return new BigDecimal(str);
	}

	public static String formatNumber(String s) {
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			BigDecimal bd = new BigDecimal(s);
			return df.format(bd);
		} catch (Exception e) {
			//e.printStackTrace();
			return s;
		}

	}
	
	/**
	 * 
	 * @return
	 */
	private static Map getWzqyDjzclxdm(){
		Map map=new HashMap();
		map.put("210","合资经营企业（港或澳、台资）");          
		map.put("220","合作经营企业（港或澳、台资）");          
		map.put("230","港、澳、台商独资经营企业");              
		map.put("240","港、澳、台商投资股份有限公司");          
		map.put("250","港、澳、台常驻代表机构");                
		map.put("260","港、澳、台承包工程及提供劳务（承包商）");
		map.put("270","港、澳、台运输企业");                    
		map.put("280","港、澳、台银行北京分行");                
		map.put("290","其他港、澳、台企业");                    
		map.put("310","中外合资经营企业");                      
		map.put("320","中外合作经营企业");                      
		map.put("330","外资（独资）企业");                      
		map.put("340","外商投资股份有限公司");                  
		map.put("350","外国常驻代表机构");                      
		map.put("360","外国承包工程及提供劳务（承包商）");      
		map.put("370","外国运输企业");                          
		map.put("380","外国银行北京分行");                      
		map.put("390","其他外国企业");    
		return map;
	}
}
