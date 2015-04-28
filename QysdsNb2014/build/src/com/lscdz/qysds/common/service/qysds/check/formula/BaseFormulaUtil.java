package com.lscdz.qysds.common.service.qysds.check.formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.qysds.db.DBAccess;
import com.lscdz.qysds.common.service.qysds.util.ReportsUtils;
import com.lscdz.qysds.common.service.qysds.util.StringUtils;

/**
 *  自定义报表-基础公式工具类
 * 项目名称：QysdsNb2014   
 * 类名称：BaseFormulaUtil   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-12-1 下午1:26:41   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 下午1:26:41   
 * 修改备注：   
 * @version  1.0
 */
public class BaseFormulaUtil {

	/**
	 * 内存数据容器 map分为两层,第一层为申报表层,以表ID作为KEY ,第二层为报表项层,以报表项ID作为KEY,其中变长项的ID规则按约定进行处理
	 */
	@SuppressWarnings("rawtypes")
	private static Map map = new HashMap();

	/**
	 * 数据操作对象
	 */
	@SuppressWarnings("unused")
	private static DBAccess dba = null;

	/**
	 * 代码表map分为3层map 第一层为代码表层 一般以代码表 表名称为KEY 第二层为代码表行层 以代码表的代码为key 第三层为代码表列层
	 * 以各个列名为key
	 */
	@SuppressWarnings("rawtypes")
	private static Map codeMap = new HashMap();

	/**
	 * 接受传入参数为代码表内存对象的构造行数
	 * 
	 * @param mapIn
	 *            Map 申报表内存对象
	 * @param codeMapIn
	 *            Map 代码表内存对象
	 * @exception FrameException
	 *                系统异常
	 */
	@SuppressWarnings("rawtypes")
	public BaseFormulaUtil(Map mapIn, Map codeMapIn) throws FrameException {
		map = mapIn;
		codeMap = codeMapIn;
		if (map == null || map.size() == 0) {
			throw new FrameException("审核系统传入申报表数据为空!!");
		} else if (codeMap == null || codeMap.size() == 0) {
			throw new FrameException("审核系统传入代码表数据为空!!");
		}

	}

	/**
	 * 接受传入参数为数据库操作对象的构造行数
	 * 
	 * @param map
	 *            HashMap 申报表内存对象
	 * @param dba
	 *            DBAccess 数据库操作对象
	 * @exception FrameException
	 *                系统异常
	 */
	@SuppressWarnings("rawtypes")
	public BaseFormulaUtil(Map mapIn, DBAccess dbAccess) throws FrameException {
		dba = dbAccess;
		map = mapIn;
		if (map == null || map.size() == 0) {
			throw new FrameException("审核系统传入申报表数据为空!!");
		}
		init();
	}

	/**
	 * 初始化方法
	 * @exception FrameException 系统异常
	 */
	private void init() throws FrameException {
		codeMap.clear();
	}

	/**
	 * 从数据库中获得代码表。
	 * 
	 * @param sql SQL语句。
	 * @return ArrayList 代码表。
	 * @throws Exception 当底层发生任何异常时抛出。
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private HashMap getCodeTab(String sql) throws FrameException
	{
		HashMap al = new HashMap();
		return al;
	}

	public static double ABS(double d) {
		return Math.abs(d);
	}

	public static int ABS(int d) {
		return Math.abs(d);
	}

	public static long ABS(long d) {
		return Math.abs(d);
	}

	/**
	 * 判断一个企业是否为外资
	 * 
	 * @return 外资 true 其他false
	 */
	public static boolean WZ() {
		if (map.get("DJZCLXDM") == null) {
			return false;
		}
		String djzclxdm = map.get("DJZCLXDM").toString();
		// System.out.println("-----------------------" + LEFT(djzclxdm, 1));
		if (LEFT(djzclxdm, 1).equals("2") || LEFT(djzclxdm, 1).equals("3")) {
			return true;
		} else {
			return false;
		}

	}

	
	/**
	 * 任一个值都和固定一个值比较 如果全满足op为true
	 * 
	 * @param tableid 申报表ID
	 * @param start 开始
	 * @param end 结束
	 * @param op 比较符
	 * @param value  比较值
	 * @return true false
	 * @throws FrameException 系统异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean EVERY(String tableid, int start, int end, String op,double value) throws FrameException {
		try {
			// 转换起始编号
			String indexStart = String.valueOf(start);
			String indexEnd = String.valueOf(end);
			// 获取申报的表数据
			Map tableMap = null;
			if (map.get(tableid) != null) {
				tableMap = (Map) map.get(tableid);
			} else {
				tableMap = new HashMap();
				map.put(tableid, tableMap);
				// return false;
			}
			// 轮寻处理数据
			Iterator keys = tableMap.keySet().iterator();
			String tmpStr = null;
			String[] tmpStrs = null;
			String key = null;
			String index = null;
			Object tmpObj = null;
			while (keys.hasNext()) {
				key = (String) keys.next();
				tmpObj = tableMap.get(key);
				if (tmpObj != null && !tmpObj.equals("")) {
					tmpStr = (String) tmpObj;
					tmpStrs = ReportsUtils.splitItemID(key);
					// 获取根序号
					if (tmpStrs == null) { // 如果是一个非变长行则进行处理
						index = key;
					} else { // 如果是一个变长行则进行处理
						index = tmpStrs[0];
					}
					// 进行范围判定,如过在范围内则进行判定
					if (ReportsUtils.checkIndexIsBetweenRange(index,indexStart, indexEnd)) {
						BaseFormula.tempVerMap.put("T" + tableid + "I" + key,tmpStr);
						// System.out.println("T"+tableid+"I"+key+"|"+tmpStr);
						if (BaseFormula.samplecalculateExpression(tmpStr + op + value).equals("false")) {
							return false;
						}
					}
				} else {
					tableMap.put(key, "0.00");
					// throw new Exception("无法获取报表项域值!!" + tableid);
				}
			}
		} catch (java.lang.ArithmeticException e) {
			return false;
		} catch (Exception e) {
			System.out.println("计算表达式时出错！");
			// e.printStackTrace();
			throw new FrameException("计算表达式时出错！");
		} catch (Throwable t) {
			return false;
		} // end try catch
		return true;
	}

	/**
	 * 任一个值都和固定一个值比较 如果全满足op为true
	 * @param tableid 申报表ID
	 * @param start  开始
	 * @param end 结束
	 * @param op 比较符
	 * @param value 比较值
	 * @return true false 返回结果
	 * @throws FrameException 系统异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean ABSEVERY(String tableid, int start, int end,String op, double value) throws FrameException {
		try {
			// 转换起始编号
			String indexStart = String.valueOf(start);
			String indexEnd = String.valueOf(end);
			// 获取申报的表数据
			Map tableMap = null;
			if (map.get(tableid) != null) {
				tableMap = (Map) map.get(tableid);
			} else {
				return false;
			}
			// 轮寻处理数据
			Iterator keys = tableMap.keySet().iterator();
			String tmpStr = null;
			String[] tmpStrs = null;
			String key = null;
			String index = null;
			Object tmpObj = null;
			while (keys.hasNext()) {
				key = (String) keys.next();
				tmpObj = tableMap.get(key);
				if (tmpObj != null && !tmpObj.equals("")) {
					tmpStr = (String) tmpObj;
					tmpStrs = ReportsUtils.splitItemID(key);
					// 获取根序号
					if (tmpStrs == null) { // 如果是一个非变长行则进行处理
						index = key;
					} else { // 如果是一个变长行则进行处理
						index = tmpStrs[0];
					}
					// 进行范围判定,如过在范围内则进行判定
					if (ReportsUtils.checkIndexIsBetweenRange(index,
							indexStart, indexEnd)) {
						BaseFormula.tempVerMap.put("T" + tableid + "I" + key,tmpStr);
						tmpStr = String.valueOf(ABS(Double.parseDouble(tmpStr)));
						if (BaseFormula.samplecalculateExpression(tmpStr + op + value).equals("false")) {
							return false;
						}
					}
				} else {
					tableMap.put(key, "0.00");
				}
			}
		} catch (java.lang.ArithmeticException e) {
			return false;
		} catch (Exception e) {
			System.out.println("计算表达式时出错！");
			// e.printStackTrace();
			throw new FrameException("计算表达式时出错！");
		} catch (Throwable t) {
			return false;
		} // end try catch
		return true;

	}

	/**
	 * 查询在的数量
	 * 
	 * @param flag1   1
	 * @param flag2 2
	 * @param value value
	 * @return 1
	 */
	@SuppressWarnings("rawtypes")
	public static int COUNTIF(String flag1, String flag2, String value) {
		try {
			int j = 0;
			if (flag1.indexOf(".") == -1) {
				return 0;
			} else {
				String hashStr = flag1.substring(0, flag1.indexOf("."));
				String codeStr = flag1.substring(flag1.indexOf(".") + 1,
						flag1.length());
				ArrayList hashList = new ArrayList();
				hashList = (ArrayList) map.get(hashStr);
				if (hashList != null) {
					for (int i = 0; i < hashList.size(); i++) {
						HashMap hash = new HashMap();
						hash = (HashMap) hashList.get(i);
						if (hash.get(codeStr) == null) {
							return 0;
						}
						if (CODESTRN(flag2, hash.get(codeStr).toString()).equals(value)) {
							j++;
						}
					}
					return j;
				} else {
					return 0;
				}
			}
		} catch (Exception e) {
			return 0;
		}
	}

	public static double ASNUM(String str) {
		double d;
		try {
			d = Double.parseDouble(str);
		} catch (Exception e) {
			return 0;
		}

		return d;
	}

	@SuppressWarnings("rawtypes")
	public static String CODESTRN(String flag, String key) throws FrameException {
		try {
			String[] str = StringUtils.split(flag, ".");
			if (str.length != 2) {
				throw new Exception("第一个参数不对，请查询公式帮助");
			}
			if (codeMap.get(str[0]) == null) {
				throw new Exception("第一个参数不对，没有对应的代码表");
			}
			HashMap table = (HashMap) codeMap.get(str[0]);
			if (table.get(key) == null) {
				return "";
				// throw new Exception("第二个参数不对，此代码表中不存在的代码" + key);
			}
			HashMap row = (HashMap) table.get(key);
			if (row.get(str[1]) == null) {
				throw new Exception("第一个参数不对，此代码表中没有该列");
			}
			return row.get(str[1]).toString();
		} catch (Exception e) {
			System.out.println("计算表达式时出错！");
			// e.printStackTrace();
			/** 2004-09-04 by yyx */
			throw new FrameException("计算表达式时出错！");
		}

	}

	/**
	 * 判断两个字符串是否相等
	 * 
	 * @param str1 源字符串
	 * @param str2  比较字符串
	 * @return 比较源字符串的取值和比较字符串的比较
	 */
	public static boolean EQUAL(String str1, String str2) {
		return str1.equals(str2);
	}

	/**
	 * 判断两个double数值是否相等
	 * 
	 * @param str1  源字符串
	 * @param str2 比较字符串
	 * @return 比较源字符串的取值和比较字符串的比较
	 */
	public static boolean EQUAL(double str1, double str2) {
		return str1 == str2;
	}

	/**
	 * 是否包含
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean CONTAINS(String str1, String str2) {
		return str1.contains(str2);
	}
	/**
	 * 判断两个字符串是否相等
	 * 
	 * @param str1  源字符串
	 * @param str2  比较字符串
	 * @return 比较源字符串的取值和比较字符串的比较
	 */
	public static boolean NOTEQUAL(String str1, String str2) {
		return !str1.equals(str2);
	}

	/**
	 * 从字符串的左方取长度为I的字符串 串的基数从0开始
	 * 
	 * @param str 源字符串
	 * @param i 取值长度
	 * @return 源字符串的取值
	 */
	public static String LEFT(String str, int i) {
		if (str.length() == 0) {
			return "";
		}
		if (i > str.length()) {
			return "";
		}

		return str.substring(0, i);
	}

	/**
	 * 从字符串的左方取长度为I的字符串 串的基数从0开始
	 * 
	 * @param str 源字符串
	 * @param i  取值长度
	 * @param value 比较字符串
	 * @return 比较源字符串的取值和比较字符串的比较
	 */
	public static boolean LEFT(String str, int i, String value) {
		if (str.length() == 0) {
			return false;
		}
		if (i > str.length()) {
			return false;
		}

		return str.substring(0, i).equals(value);
	}

	/**
	 * 从字符串的右方取长度为I的字符串 串的基数从0开始
	 * 
	 * @param str 源字符串
	 * @param i 取值长度
	 * @return 源字符串取值
	 */
	public static String RIGHT(String str, int i) {
		if (str.length() == 0) {
			return "";
		}
		if (i > str.length()) {
			return "";
		}
		return str.substring(str.length() - i, str.length());
	}

	/**
	 * 从字符串的右方取长度为I的字符串 串的基数从0开始
	 * 
	 * @param str 源字符串
	 * @param i 取值长度
	 * @param value 比较字符串
	 * @return 比较源字符串的取值和比较字符串的比较
	 */
	public static boolean RIGHT(String str, int i, String value) {
		if (str.length() == 0) {
			return false;
		}
		if (i > str.length()) {
			return false;
		}
		return str.substring(str.length() - i, str.length()).equals(value);
	}

	/**
	 * 从字符串的中间位置s取长度为I的字符串 串的基数从0开始
	 * 
	 * @param str 源字符串
	 * @param s  取值开始位置
	 * @param i  取值长度
	 * @return 源字符串的取值
	 */

	public static String MID(String str, int s, int i) {
		if (str.length() == 0) {
			return "";
		}
		if (i > str.length()) {
			return "";
		}
		if (s > str.length()) {
			return "";
		}
		if (s > i) {
			return "";
		}

		return str.substring(s, i);
	}

	public static int FIND(String str, String str2) {
		if (str == null || str2 == null) {
			return -1;
		}
		return str2.indexOf(str + " ");
	}

	/**
	 * 从字符串的中间位置s取长度为I的字符串 串的基数从0开始
	 * 
	 * @param str 源字符串
	 * @param s 取值开始位置
	 * @param i 取值长度
	 * @param value 比较字符串
	 * @return 比较源字符串的取值和比较字符串的比较
	 */
	public static boolean MID(String str, int s, int i, String value) {
		if (str.length() == 0) {
			return false;
		}
		if (i > str.length()) {
			return false;
		}
		if (s > str.length()) {
			return false;
		}
		if (s > i) {
			return false;
		}

		return str.substring(s, i).equals(value);
	}

	/**
	 * 取小数点后的多少位
	 * 
	 * @param d 数值
	 * @param i 小数点后位数
	 * @return round后的数值
	 */
	public static double ROUND(double d, int i) {
		double s = 10.00;
		for (int j = 1; j < i; j++) {
			s = s * 10;
		}
		return Math.round(d * s) / s;
	}

	/**
	 * 取小数点后的多少位
	 * 
	 * @param d  数值
	 * @return round后的数值
	 */
	public static double ROUND(double d) {

		return Math.round(d * 100.00) / 100;
	}

	/**
	 * 逻辑关系 如果bool为true 返回booltrue 否则返回boolfalse
	 * 
	 * @param bool 判断逻辑表达式
	 * @param booltrue 逻辑表达式1
	 * @param boolfalse 逻辑表达式2
	 * @return 如果bool为true 返回booltrue 否则返回boolfalse
	 */
	public static boolean IF(boolean bool, boolean booltrue, boolean boolfalse) {
		if (bool) {
			return booltrue;
		} else {
			return boolfalse;
		}
	}

	/**
	 * 逻辑关系AND 如果bool全部为true 返回true 否则返回false
	 * 
	 * @param bool 判断逻辑表达式
	 * @return 如果bool为true 返回true 否则返回false
	 */

	public static boolean AND(boolean[] bool) {
		boolean result = true;
		int i = 0;
		while (!result || i > bool.length) {
			result = result && bool[i];
			i++;
		}
		return result;
	}

	public static boolean AND(boolean bool0, boolean bool1) {
		boolean result[] = { bool0, bool1 };
		return AND(result);
	}

	public static boolean AND(boolean bool0, boolean bool1, boolean bool2) {
		boolean result[] = { bool0, bool1, bool2 };

		return AND(result);
	}

	public static boolean AND(boolean bool0, boolean bool1, boolean bool2,
			boolean bool3) {
		boolean result[] = { bool0, bool1, bool2, bool3 };

		return AND(result);
	}

	public static boolean AND(boolean bool0, boolean bool1, boolean bool2,
			boolean bool3, boolean bool4) {
		boolean result[] = { bool0, bool1, bool2, bool3, bool4 };

		return AND(result);
	}

	/**
	 * 逻辑关系OR 如果bool有true 返回true 否则返回false
	 * 
	 * @param bool
	 *            判断逻辑表达式
	 * @return 如果bool有true 返回true 否则返回false
	 */
	public static boolean OR(boolean[] bool) {
		boolean result = false;
		int i = 0;
		while (result || i > bool.length) {
			result = result || bool[i];
			i++;
		}
		return result;
	}

	public static boolean OR(boolean bool0, boolean bool1) {
		boolean result[] = { bool0, bool1 };

		return OR(result);
	}

	public static boolean OR(boolean bool0, boolean bool1, boolean bool2) {
		boolean result[] = { bool0, bool1, bool2 };

		return OR(result);
	}

	public static boolean OR(boolean bool0, boolean bool1, boolean bool2,
			boolean bool3) {
		boolean result[] = { bool0, bool1, bool2, bool3 };

		return OR(result);
	}

	public static boolean OR(boolean bool0, boolean bool1, boolean bool2,
			boolean bool3, boolean bool4) {
		boolean result[] = { bool0, bool1, bool2, bool3, bool4 };

		return OR(result);
	}

	/**
	 * 相加函数
	 * 
	 * @param arg
	 *            要相加的值
	 * @return 相加值；
	 */
	public static double SUM(double[] arg) {
		double result = 0;
		for (int i = 0; i <= arg.length; i++) {
			result += arg[i];
		}
		result = new Double(Math.round(result * 100.00) / 100.00).doubleValue();
		return result;
	}

	/**
	 * 对于指定申报表的计算从start加到end
	 * 
	 * @param tableid
	 *            申报表 ID
	 * @param start
	 *            开始
	 * @param end
	 *            结束
	 * @return 相加值；
	 * @exception FrameException
	 *                系统异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static double SUM(String tableid, int start, int end)
			throws FrameException {
		double result = 0.00;
		try {
			// 转换起始编号
			String indexStart = String.valueOf(start);
			String indexEnd = String.valueOf(end);
			// 获取申报的表数据
			Map tableMap = null;
			if (map.get(tableid) != null) {
				tableMap = (Map) map.get(tableid);
			} else {
				tableMap = new HashMap();
				map.put(tableid, tableMap);
				// throw new FrameException("SUM()计算出错,无法取到申明的申报表数据!" +
				// tableid);
			}
			// 轮寻处理数据
			Iterator keys = tableMap.keySet().iterator();
			String tmpStr = null;
			String[] tmpStrs = null;
			String key = null;
			String index = null;
			Object tmpObj = null;
			while (keys.hasNext()) {
				key = (String) keys.next();
				tmpObj = tableMap.get(key);
				if (tmpObj != null && !tmpObj.equals("")) {
					tmpStr = (String) tmpObj;
					tmpStrs = ReportsUtils.splitItemID(key);
					// 获取根序号
					if (tmpStrs == null) { // 如果是一个非变长行则进行处理
						index = key;
					} else { // 如果是一个变长行则进行处理
						index = tmpStrs[0];
					}
					// 进行范围判定,如果在范围内则进行判定
					if (ReportsUtils.checkIndexIsBetweenRange(index,indexStart, indexEnd)) {
						BaseFormula.tempVerMap.put("T" + tableid + "I" + key,tmpStr);
						// System.out.println("[T"+tableid+"I"+key+"|"+key+"]"+tmpStr);
						result = result + Double.parseDouble(tmpStr);
						// System.out.println("[T"+tableid+"I"+key+"|"+key+"]"+result);
					}
				} else {
					tableMap.put(key, "0.00");
					// throw new Exception("无法获取报表项域值!!" + tableid);
				}
			}
			result = new Double(Math.round(result * 100.00) / 100.00).doubleValue();
		} catch (java.lang.ArithmeticException e) {
			// e.printStackTrace();
			throw new FrameException(e.getMessage());
		} catch (Exception e) {
			System.out.println("计算表达式时出错！");
			// e.printStackTrace();
			throw new FrameException(e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
			throw new FrameException(t.getMessage());
		} // end try catch

		System.out.println("SUM(String tableid, int start, int end)=" + result);
		return result;
	}

	/**
	 * 取两个输入字符串数字较小的一个
	 * 
	 * @param str1 String str1,必须为数字
	 * @param str2 String str2,必须为数字
	 * @return String 较小的一个
	 */
	public static String MIN(String str1, String str2) {
		String result = null;
		str1 = checkValue(str1);
		str2 = checkValue(str2);
		if (Double.parseDouble(str1) >= Double.parseDouble(str2)) {
			result = str2;
		} else {
			result = str1;
		}
		return result;
	}

	/**
	 * 取两个输入字符串数字较小的一个
	 * 
	 * @param str1 double str1,必须为数字
	 * @param str2 double str2,必须为数字
	 * @return double 较小的一个
	 */
	public static double MIN(double str1, double str2) {
		double result = 0.00;
		if (str1 >= str2) {
			result = str2;
		} else {
			result = str1;
		}
		return result;
	}

	/**
	 * 取两个输入字符串数字较大的一个
	 * 
	 * @param str1  String str1,必须为数字
	 * @param str2  String str2,必须为数字
	 * @return String 较大的一个
	 */
	public static String MAX(String str1, String str2) {
		String result = null;
		str1 = checkValue(str1);
		str2 = checkValue(str2);
		if (Double.parseDouble(str1) >= Double.parseDouble(str2)) {
			result = str1;
		} else {
			result = str2;
		}
		return result;
	}

	/**
	 * 检验输入的参数是否为空 如果是则返回0.00
	 * 
	 * @param str1
	 * @return 如果是空则返回0.00；否则返回str1
	 */
	private static String checkValue(String str1) {
		if (str1 == null || str1.equals("")) {
			str1 = "0.00";
		}
		return str1;
	}

//	public static void main(String[] arg) {
//		Map map = new HashMap();
//		map.put("1", "3");
//		map.put("2", "4");
//		map.put("3", "5");
//		map.put("4", "6");
//		Map tableMap = new HashMap();
//		tableMap.put("1", map);
//		try {
//			BaseFormulaUtil bef = new BaseFormulaUtil(tableMap, tableMap);
//			boolean flag = bef.EVERY("1", 1, 4, ">=", 5);
//			System.out.println(flag);
//		} catch (Exception e) {
//			// e.printStackTrace();
//		}
//	}

}
