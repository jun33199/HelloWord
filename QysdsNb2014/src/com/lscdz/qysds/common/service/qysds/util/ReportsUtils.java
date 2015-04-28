package com.lscdz.qysds.common.service.qysds.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.lang.reflect.Method;

import com.lscdz.qysds.common.service.qysds.check.formula.BaseFormula;

import yangjian.frame.util.FrameException;

/**
 * 自定义报表-工具申明
 * 项目名称：QysdsNb2014   
 * 类名称：ReportsUtils   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-12-1 下午2:58:22   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 下午2:58:22   
 * 修改备注：   
 * @version  1.0
 */
public class ReportsUtils {

	/**
	 * 检查项目编号是否具备子集编号并且在存在子集编号时返回拆分的编号
	 * 
	 * @param iID  String itemID
	 * @return String[] 拆分的编号 null-表示没有子编号，
	 * @throws FrameException  应用异常
	 */
	public static String[] splitItemID(String iID) throws FrameException {
		String[] result = new String[2];
		if (StringUtils.killNull2(iID) == null) {
			throw new FrameException("ITEMID为空！");
		}
		// 如果没有任何子编号则返回null作为结果并进行合法性判定
		if (iID.indexOf(".") == -1) {
			return null;
		}
		// 如果有任何子编号则进行拆分处理并进行合法性判定
		result[0] = iID.substring(0, iID.indexOf("."));
		result[1] = iID.substring(iID.indexOf(".") + 1, iID.length());
		if (StringUtils.killNull2(result[0]) == null || StringUtils.killNull2(result[1]) == null) {
			throw new FrameException("ITEMID编码格式非法！" + iID);
		}
		// 返回值
		return result;
	}

	/**
	 * 判断一个字符串形式的index是否在指定的区间内
	 * 
	 * @param index String 序号
	 * @param startIndex String 开始序号
	 * @param endIndex String 结束序号
	 * @return boolean true-在范围以内,false-在范围以外
	 * @throws FrameException 系统异常
	 */
	public static boolean checkIndexIsBetweenRange(String index,String startIndex, String endIndex) throws FrameException {
		// 0.句柄申明
		boolean rtnFlag = false;
		int iIndex = -1;
		int iStartIndex = -1;
		int iEndIndex = -1;
		// 1.初始化
		iIndex = Integer.parseInt(index);
		iStartIndex = Integer.parseInt(startIndex);
		iEndIndex = Integer.parseInt(endIndex);
		// 2.业务过程
		if (iIndex >= iStartIndex && iIndex <= iEndIndex) {
			rtnFlag = true;
		}
		// 99.返回值
		return rtnFlag;
	}

	/**
	 * 冒泡法排序
	 * 
	 * @param a int[] 入口参数
	 * @param flag 排序参数 true-升序，false-降序
	 * @return int[] 排序完毕
	 */
	public static int[] bubbleSort(int[] a, boolean flag) {
		int temp;
		int size = a.length;
		for (int i = size - 1; i >= 1; i--) {
			for (int j = 0; j < i; j++) {
				if (flag) {
					if (a[j] > a[j + 1]) {
						temp = a[j];
						a[j] = a[j + 1];
						a[j + 1] = temp;
					}
				} else {
					if (a[j] < a[j + 1]) {
						temp = a[j];
						a[j] = a[j + 1];
						a[j + 1] = temp;
					}
				}
			}
		}
		return a;
	}

	public static String getRootItemID(String str) {
		String iid = null;
		if (str.indexOf(".") == -1) {
			iid = str;
		} else {
			iid = StringUtils.killNull2(str.substring(0, str.indexOf(".")));
		}
		return iid;
	}

	/**
	 * 处理TmIn形式的申报表数据引用
	 * @param str String TmIn
	 * @return String[] String[0]="m",String[1]="n",
	 */
	public static String[] parseQuoteTmIn(String str) {
		String[] result = new String[2];
		result[0] = str.substring(1, str.indexOf("I"));
		// System.out.println(result[0]);
		result[1] = str.substring(str.indexOf("I") + 1, str.length());
		// System.out.println(result[1]);
		return result;
	}

	/**
	 * 处理TmHJIn形式的申报表数据引用
	 * 
	 * @param str
	 *            String TmHJIn
	 * @return String[] String[0]="m",String[1]="n",
	 */
	public static String[] parseQuoteTmHJIn(String str) {
		String[] result = new String[2];
		result[0] = str.substring(1, str.indexOf("HJI"));
		// System.out.println(result[0]);
		result[1] = str.substring(str.indexOf("I") + 1, str.length());
		// System.out.println(result[1]);
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String replaceEqualOp(String formula) throws Exception {
		String rtnFomula = null;
		String[] rtnFomulas = null;
		int[] rtnOpIndexs = null;
		Map rthOpMap = new HashMap();
		StringBuffer rtnSb;
		rtnFomula = formula; // 初始化
		rtnFomulas = rtnFomula.split("");
		// System.out.println("rtnFomulas.length="+rtnFomulas.length);
		// /获取全部的"="的indexs
		rootEqual: for (int i = 0; i < rtnFomulas.length; i++) {
			if ("=".equals(rtnFomulas[i])) {
				rthOpMap.put(String.valueOf(i), String.valueOf(i));
				continue rootEqual;
			}
		}
		// /整理"="的indexs
		Iterator iterator = rthOpMap.keySet().iterator();
		rtnOpIndexs = new int[rthOpMap.size()];
		String tmpStr;
		int tmpIndex1_1 = 0;
		while (iterator.hasNext()) {
			tmpStr = (String) iterator.next();
			rtnOpIndexs[tmpIndex1_1] = Integer.parseInt(tmpStr);
			tmpIndex1_1++;
		}
		// 获取排序完毕以后的index数组
		rtnOpIndexs = ReportsUtils.bubbleSort(rtnOpIndexs, true);
		// 开始处理"="
		for (int i = 0; i < rtnOpIndexs.length; i++) {
			if (rtnFomulas.length <= 3) { // /如果"="处于字符串的第一个进行处理(公式长度小于等于2)
				throw new FrameException("公式过短,表达式非法!" + rtnFomula);
			} else if (rtnOpIndexs[i] == 1 && rtnFomulas.length > 3) { // /如果"="处于字符串的第一个进行处理(公式长度大于2)
				if (!("=".equals(rtnFomulas[rtnOpIndexs[i] + 1]))) { // 如果当前"="的后一个字符也为"="则不处理,否则替换"="为"=="
					rtnFomulas[rtnOpIndexs[i]] = "==";
				}
			} else if (rtnOpIndexs[i] == rtnFomulas.length - 1
					&& rtnFomulas.length > 3) { // /如果"="处于字符串的最后一个进行处理(公式长度大于2)
				if (!(">".equals(rtnFomulas[rtnOpIndexs[i] - 1])
						|| "<".equals(rtnFomulas[rtnOpIndexs[i] - 1])
						|| "!".equals(rtnFomulas[rtnOpIndexs[i] - 1]) || "="
							.equals(rtnFomulas[rtnOpIndexs[i] - 1]))) {
					rtnFomulas[rtnOpIndexs[i]] = "==";
				}
			} else if (rtnOpIndexs[i] != rtnFomulas.length - 1
					&& rtnOpIndexs[i] != 1 && rtnFomulas.length > 3) { // /如果"="处于字符串的中间进行处理(公式长度大于2)
				if (!("=".equals(rtnFomulas[rtnOpIndexs[i] + 1])
						|| ">".equals(rtnFomulas[rtnOpIndexs[i] - 1])
						|| "<".equals(rtnFomulas[rtnOpIndexs[i] - 1])
						|| "!".equals(rtnFomulas[rtnOpIndexs[i] - 1]) || "="
							.equals(rtnFomulas[rtnOpIndexs[i] - 1]))) {
					rtnFomulas[rtnOpIndexs[i]] = "==";
				}
			}
		}
		// rtnFomulas数组中的"="全部处理完毕,进行拼装
		rtnSb = new StringBuffer();
		for (int i = 0; i < rtnFomulas.length; i++) {
			rtnSb.append(rtnFomulas[i]);
		}
		rtnFomula = rtnSb.toString();
		return rtnFomula;
	}

	/**
	 * 将指定公式中的变量引用替换为值
	 * 
	 * @param formula String 目标公式
	 * @param var String 变量名称
	 * @param value String 变量值
	 * @return String 替换完毕的公式
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String replaceQuoteVarInFormula(String formula, String var,
			String value) {
		String rtnFormula = null;
		int beginIndex;
		int endIndex;
		String tmpStr;
		StringBuffer sb = new StringBuffer();
		String op = null;
		// 0.获取所有操作符的index
		String[] fs = formula.split(""); // 整理成单字符数组的公式表达式,注意:此处fs的第一个元素为"",formula的第一个字符应该从fs[1]开始
		// 1.根据操作符定义拆分当前公式为子串
		// /1.0.获得所有操作符坐标
		Map map_op_index = new HashMap();
		for (int i = 0; i < BaseFormula.formulaOps.length; i++) {
			op = BaseFormula.formulaOps[i];
			for (int j = 0; j < fs.length; j++) {
				if (op.equals(fs[j])) {
					map_op_index.put(String.valueOf(j), String.valueOf(j));
				}
			}
		}
		// /1.1.进行坐标排序和去除冗余坐标
		Iterator iterator = map_op_index.keySet().iterator();
		int[] indexs = new int[map_op_index.size()];
		int tmpIndex1_1 = 0;
		while (iterator.hasNext()) {
			tmpStr = (String) iterator.next();
			indexs[tmpIndex1_1] = Integer.parseInt(tmpStr);
			tmpIndex1_1++;
		}
		indexs = ReportsUtils.bubbleSort(indexs, true); // 获取排序完毕以后的index数组

		// 2.替换变量
		for (int i = 0; i < indexs.length; i++) {
			op = formula.substring(indexs[i] - 1, indexs[i]);
			if (indexs.length == 1) {
				beginIndex = 0;
				endIndex = indexs[i] - 1;
				tmpStr = formula.substring(beginIndex, endIndex);
				if (tmpStr.equals(var)) {
					sb.append(value);
				} else {
					sb.append(tmpStr);
				}
				sb.append(op);
				beginIndex = indexs[i];
				endIndex = formula.length();
				tmpStr = formula.substring(beginIndex, endIndex);
				if (tmpStr.equals(var)) {
					sb.append(value);
				} else {
					sb.append(tmpStr);
				}
			} else {
				if (i == 0) {
					beginIndex = 0;
					endIndex = indexs[i] - 1;
					tmpStr = formula.substring(beginIndex, endIndex);
					if (tmpStr.equals(var)) {
						sb.append(value);
					} else {
						sb.append(tmpStr);
					}
					sb.append(op);
				} else if (i == indexs.length - 1) {
					beginIndex = indexs[i - 1];
					endIndex = indexs[i] - 1;
					tmpStr = formula.substring(beginIndex, endIndex);
					if (tmpStr.equals(var)) {
						sb.append(value);
					} else {
						sb.append(tmpStr);
					}
					sb.append(op);
					beginIndex = indexs[i];
					endIndex = formula.length();
					tmpStr = formula.substring(beginIndex, endIndex);
					if (tmpStr.equals(var)) {
						sb.append(value);
					} else {
						sb.append(tmpStr);
					}
				} else {
					beginIndex = indexs[i - 1];
					endIndex = indexs[i] - 1;
					tmpStr = formula.substring(beginIndex, endIndex);
					if (tmpStr.equals(var)) {
						sb.append(value);
					} else {
						sb.append(tmpStr);
					}
					sb.append(op);
				}
			}
		}
		rtnFormula = sb.toString();
		return rtnFormula;
	}

	/**
	 * 根据指定的资源获取全部方法名称
	 * 
	 * @param url String 资源全路径
	 * @return String[] 方法名
	 * @throws FrameException 系统异常
	 */
	@SuppressWarnings("rawtypes")
	public static String[] getClassMethodNames(String url) throws FrameException {
		String[] rtnNames = null;
		// com.syax.creports.check.formula.BaseFormulaUtil
		// System.out.println("-------------获取["+url+"]工具的全部方法名----------------");
		Class param=null;
		try {
			param = Class.forName(url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FrameException("获取指定资源出错："+url);
		}
		if(param==null){
			throw new FrameException("没有获取到指定资源："+url);
		}
		Method[] methods = param.getMethods();
		rtnNames = new String[methods.length];
		for (int i = 0; i < methods.length; i++) {
			rtnNames[i] = methods[i].getName();
		}
		return rtnNames;
	}

	public static boolean checkVarNameIsMethodNames(String varName,
			String[] methodNames) {
		boolean flag = false;
		for (int i = 0; i < methodNames.length; i++) {
			if (varName.equals(methodNames[i])) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		try {
			String[] strs = ReportsUtils.getClassMethodNames("com.syax.creports.check.formula.BaseFormulaUtil");
			for (int i = 0; i < strs.length; i++) {
				System.out.println(strs[i]);
			}
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
		}
	}

}
