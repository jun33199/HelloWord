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
	 * ������ҵ�Ǽ�ע�����ʹ���map
	 */
	private static final Map wzMap=Utils.getWzqyDjzclxdm();
	
	/**
	 * �Ƿ�Ϊ������ҵ
	 * @return 1- ���� 0--������
	 */
	public static String isWzqy(String djzclxdm){
		if(wzMap.get(djzclxdm)!=null){
			return ConstantFangtu.WZQY_FLAG_YES;
		}else{
			return ConstantFangtu.WZQY_FLAG_NO;
		}
	}
	
	/**
	 * ��ҳ�洫�������ַ���װ��map��
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
	 * ȡ��Ӧ˰����б�
	 * 
	 * @return
	 */
	public static List getYingShuiList() {
		List list = new ArrayList();
		list.add(new LabelValueXMLVO("---", ""));
		list.add(new LabelValueXMLVO("����", "0"));
		list.add(new LabelValueXMLVO("����", "1"));
		list.add(new LabelValueXMLVO("ԭֵ���", "2"));
		return list;
	}

	/**
	 * ȡ�ü����б�
	 * 
	 * @return
	 */
	public static List getJianMianList() {
		List list = new ArrayList();
		list.add(new LabelValueXMLVO("---", ""));
		list.add(new LabelValueXMLVO("����", "0"));
		list.add(new LabelValueXMLVO("����", "1"));
		list.add(new LabelValueXMLVO("����", "2"));
		return list;
	}

	/**
	 * ȡ�ü�������б�
	 * 
	 * @return
	 */
	public static List getJianMianMJList() {
		List list = new ArrayList();
		list.add(new LabelValueXMLVO("---", ""));
		list.add(new LabelValueXMLVO("����", "0"));
		list.add(new LabelValueXMLVO("��Ǩ", "1"));
		list.add(new LabelValueXMLVO("��ɽ", "2"));
		list.add(new LabelValueXMLVO("����", "3"));
		return list;
	}

	/**
	 * ȡ��Ӧ˰�������б�
	 * 
	 * @return
	 */
	public static List getYingShuiMJList() {
		List list = new ArrayList();
		list.add(new LabelValueXMLVO("---", ""));
		list.add(new LabelValueXMLVO("����", "0"));
		list.add(new LabelValueXMLVO("����", "1"));
		list.add(new LabelValueXMLVO("������", "2"));
		return list;
	}

	/**
	 * ȡ�ô����б�
	 * 
	 * @return
	 */
	public static List getDaiJiaoList() {
		List list = new ArrayList();
		list.add(new LabelValueXMLVO("��", "1"));
		list.add(new LabelValueXMLVO("��", "0"));
		return list;
	}

	public static List getRegionList() {
		List list = new ArrayList();

		list.add(new LabelValueXMLVO("������", "01"));
		list.add(new LabelValueXMLVO("������", "02"));
		list.add(new LabelValueXMLVO("������", "03"));
		list.add(new LabelValueXMLVO("������", "04"));
		list.add(new LabelValueXMLVO("������", "05"));
		list.add(new LabelValueXMLVO("������", "06"));
		list.add(new LabelValueXMLVO("��̨��", "07"));
		list.add(new LabelValueXMLVO("ʯ��ɽ��", "08"));
		list.add(new LabelValueXMLVO("��ͷ����", "09"));
		list.add(new LabelValueXMLVO("��ɽ", "10"));
		list.add(new LabelValueXMLVO("��ƽ��", "11"));
		list.add(new LabelValueXMLVO("ͨ����", "12"));
		list.add(new LabelValueXMLVO("˳����", "13"));
		list.add(new LabelValueXMLVO("������", "14"));
		list.add(new LabelValueXMLVO("��ɽ��", "15"));
		list.add(new LabelValueXMLVO("������", "16"));
		list.add(new LabelValueXMLVO("������", "17"));
		list.add(new LabelValueXMLVO("ƽ����", "18"));
		list.add(new LabelValueXMLVO("������", "19"));
		list.add(new LabelValueXMLVO("������", "20"));
		list.add(new LabelValueXMLVO("��վ", "21"));

		return list;
	}

	public static List getRegionZlqxList() {
		List list = new ArrayList();

		list.add(new LabelValueBean("������", "01"));
		list.add(new LabelValueBean("������", "02"));
		list.add(new LabelValueBean("������", "03"));
		list.add(new LabelValueBean("������", "04"));
		list.add(new LabelValueBean("������", "05"));
		list.add(new LabelValueBean("������", "06"));
		list.add(new LabelValueBean("��̨��", "07"));
		list.add(new LabelValueBean("ʯ��ɽ��", "08"));
		list.add(new LabelValueBean("��ͷ����", "09"));
		list.add(new LabelValueBean("��ɽ", "10"));
		list.add(new LabelValueBean("��ƽ��", "11"));
		list.add(new LabelValueBean("ͨ����", "12"));
		list.add(new LabelValueBean("˳����", "13"));
		list.add(new LabelValueBean("������", "14"));
		list.add(new LabelValueBean("��ɽ��", "15"));
		list.add(new LabelValueBean("������", "16"));
		list.add(new LabelValueBean("������", "17"));
		list.add(new LabelValueBean("ƽ����", "18"));
		list.add(new LabelValueBean("������", "19"));
		list.add(new LabelValueBean("������", "20"));
		list.add(new LabelValueBean("��վ", "21"));

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
	 * ת��string �� number �����Ϊ�գ�����0
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
		map.put("210","���ʾ�Ӫ��ҵ���ۻ�ġ�̨�ʣ�");          
		map.put("220","������Ӫ��ҵ���ۻ�ġ�̨�ʣ�");          
		map.put("230","�ۡ��ġ�̨�̶��ʾ�Ӫ��ҵ");              
		map.put("240","�ۡ��ġ�̨��Ͷ�ʹɷ����޹�˾");          
		map.put("250","�ۡ��ġ�̨��פ�������");                
		map.put("260","�ۡ��ġ�̨�а����̼��ṩ���񣨳а��̣�");
		map.put("270","�ۡ��ġ�̨������ҵ");                    
		map.put("280","�ۡ��ġ�̨���б�������");                
		map.put("290","�����ۡ��ġ�̨��ҵ");                    
		map.put("310","������ʾ�Ӫ��ҵ");                      
		map.put("320","���������Ӫ��ҵ");                      
		map.put("330","���ʣ����ʣ���ҵ");                      
		map.put("340","����Ͷ�ʹɷ����޹�˾");                  
		map.put("350","�����פ�������");                      
		map.put("360","����а����̼��ṩ���񣨳а��̣�");      
		map.put("370","���������ҵ");                          
		map.put("380","������б�������");                      
		map.put("390","���������ҵ");    
		return map;
	}
}
