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
 * �Զ��屨��-��������
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�ReportsUtils   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-12-1 ����2:58:22   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-12-1 ����2:58:22   
 * �޸ı�ע��   
 * @version  1.0
 */
public class ReportsUtils {

	/**
	 * �����Ŀ����Ƿ�߱��Ӽ���Ų����ڴ����Ӽ����ʱ���ز�ֵı��
	 * 
	 * @param iID  String itemID
	 * @return String[] ��ֵı�� null-��ʾû���ӱ�ţ�
	 * @throws FrameException  Ӧ���쳣
	 */
	public static String[] splitItemID(String iID) throws FrameException {
		String[] result = new String[2];
		if (StringUtils.killNull2(iID) == null) {
			throw new FrameException("ITEMIDΪ�գ�");
		}
		// ���û���κ��ӱ���򷵻�null��Ϊ��������кϷ����ж�
		if (iID.indexOf(".") == -1) {
			return null;
		}
		// ������κ��ӱ������в�ִ������кϷ����ж�
		result[0] = iID.substring(0, iID.indexOf("."));
		result[1] = iID.substring(iID.indexOf(".") + 1, iID.length());
		if (StringUtils.killNull2(result[0]) == null || StringUtils.killNull2(result[1]) == null) {
			throw new FrameException("ITEMID�����ʽ�Ƿ���" + iID);
		}
		// ����ֵ
		return result;
	}

	/**
	 * �ж�һ���ַ�����ʽ��index�Ƿ���ָ����������
	 * 
	 * @param index String ���
	 * @param startIndex String ��ʼ���
	 * @param endIndex String �������
	 * @return boolean true-�ڷ�Χ����,false-�ڷ�Χ����
	 * @throws FrameException ϵͳ�쳣
	 */
	public static boolean checkIndexIsBetweenRange(String index,String startIndex, String endIndex) throws FrameException {
		// 0.�������
		boolean rtnFlag = false;
		int iIndex = -1;
		int iStartIndex = -1;
		int iEndIndex = -1;
		// 1.��ʼ��
		iIndex = Integer.parseInt(index);
		iStartIndex = Integer.parseInt(startIndex);
		iEndIndex = Integer.parseInt(endIndex);
		// 2.ҵ�����
		if (iIndex >= iStartIndex && iIndex <= iEndIndex) {
			rtnFlag = true;
		}
		// 99.����ֵ
		return rtnFlag;
	}

	/**
	 * ð�ݷ�����
	 * 
	 * @param a int[] ��ڲ���
	 * @param flag ������� true-����false-����
	 * @return int[] �������
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
	 * ����TmIn��ʽ���걨����������
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
	 * ����TmHJIn��ʽ���걨����������
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
		rtnFomula = formula; // ��ʼ��
		rtnFomulas = rtnFomula.split("");
		// System.out.println("rtnFomulas.length="+rtnFomulas.length);
		// /��ȡȫ����"="��indexs
		rootEqual: for (int i = 0; i < rtnFomulas.length; i++) {
			if ("=".equals(rtnFomulas[i])) {
				rthOpMap.put(String.valueOf(i), String.valueOf(i));
				continue rootEqual;
			}
		}
		// /����"="��indexs
		Iterator iterator = rthOpMap.keySet().iterator();
		rtnOpIndexs = new int[rthOpMap.size()];
		String tmpStr;
		int tmpIndex1_1 = 0;
		while (iterator.hasNext()) {
			tmpStr = (String) iterator.next();
			rtnOpIndexs[tmpIndex1_1] = Integer.parseInt(tmpStr);
			tmpIndex1_1++;
		}
		// ��ȡ��������Ժ��index����
		rtnOpIndexs = ReportsUtils.bubbleSort(rtnOpIndexs, true);
		// ��ʼ����"="
		for (int i = 0; i < rtnOpIndexs.length; i++) {
			if (rtnFomulas.length <= 3) { // /���"="�����ַ����ĵ�һ�����д���(��ʽ����С�ڵ���2)
				throw new FrameException("��ʽ����,���ʽ�Ƿ�!" + rtnFomula);
			} else if (rtnOpIndexs[i] == 1 && rtnFomulas.length > 3) { // /���"="�����ַ����ĵ�һ�����д���(��ʽ���ȴ���2)
				if (!("=".equals(rtnFomulas[rtnOpIndexs[i] + 1]))) { // �����ǰ"="�ĺ�һ���ַ�ҲΪ"="�򲻴���,�����滻"="Ϊ"=="
					rtnFomulas[rtnOpIndexs[i]] = "==";
				}
			} else if (rtnOpIndexs[i] == rtnFomulas.length - 1
					&& rtnFomulas.length > 3) { // /���"="�����ַ��������һ�����д���(��ʽ���ȴ���2)
				if (!(">".equals(rtnFomulas[rtnOpIndexs[i] - 1])
						|| "<".equals(rtnFomulas[rtnOpIndexs[i] - 1])
						|| "!".equals(rtnFomulas[rtnOpIndexs[i] - 1]) || "="
							.equals(rtnFomulas[rtnOpIndexs[i] - 1]))) {
					rtnFomulas[rtnOpIndexs[i]] = "==";
				}
			} else if (rtnOpIndexs[i] != rtnFomulas.length - 1
					&& rtnOpIndexs[i] != 1 && rtnFomulas.length > 3) { // /���"="�����ַ������м���д���(��ʽ���ȴ���2)
				if (!("=".equals(rtnFomulas[rtnOpIndexs[i] + 1])
						|| ">".equals(rtnFomulas[rtnOpIndexs[i] - 1])
						|| "<".equals(rtnFomulas[rtnOpIndexs[i] - 1])
						|| "!".equals(rtnFomulas[rtnOpIndexs[i] - 1]) || "="
							.equals(rtnFomulas[rtnOpIndexs[i] - 1]))) {
					rtnFomulas[rtnOpIndexs[i]] = "==";
				}
			}
		}
		// rtnFomulas�����е�"="ȫ���������,����ƴװ
		rtnSb = new StringBuffer();
		for (int i = 0; i < rtnFomulas.length; i++) {
			rtnSb.append(rtnFomulas[i]);
		}
		rtnFomula = rtnSb.toString();
		return rtnFomula;
	}

	/**
	 * ��ָ����ʽ�еı��������滻Ϊֵ
	 * 
	 * @param formula String Ŀ�깫ʽ
	 * @param var String ��������
	 * @param value String ����ֵ
	 * @return String �滻��ϵĹ�ʽ
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
		// 0.��ȡ���в�������index
		String[] fs = formula.split(""); // ����ɵ��ַ�����Ĺ�ʽ���ʽ,ע��:�˴�fs�ĵ�һ��Ԫ��Ϊ"",formula�ĵ�һ���ַ�Ӧ�ô�fs[1]��ʼ
		// 1.���ݲ����������ֵ�ǰ��ʽΪ�Ӵ�
		// /1.0.������в���������
		Map map_op_index = new HashMap();
		for (int i = 0; i < BaseFormula.formulaOps.length; i++) {
			op = BaseFormula.formulaOps[i];
			for (int j = 0; j < fs.length; j++) {
				if (op.equals(fs[j])) {
					map_op_index.put(String.valueOf(j), String.valueOf(j));
				}
			}
		}
		// /1.1.�������������ȥ����������
		Iterator iterator = map_op_index.keySet().iterator();
		int[] indexs = new int[map_op_index.size()];
		int tmpIndex1_1 = 0;
		while (iterator.hasNext()) {
			tmpStr = (String) iterator.next();
			indexs[tmpIndex1_1] = Integer.parseInt(tmpStr);
			tmpIndex1_1++;
		}
		indexs = ReportsUtils.bubbleSort(indexs, true); // ��ȡ��������Ժ��index����

		// 2.�滻����
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
	 * ����ָ������Դ��ȡȫ����������
	 * 
	 * @param url String ��Դȫ·��
	 * @return String[] ������
	 * @throws FrameException ϵͳ�쳣
	 */
	@SuppressWarnings("rawtypes")
	public static String[] getClassMethodNames(String url) throws FrameException {
		String[] rtnNames = null;
		// com.syax.creports.check.formula.BaseFormulaUtil
		// System.out.println("-------------��ȡ["+url+"]���ߵ�ȫ��������----------------");
		Class param=null;
		try {
			param = Class.forName(url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FrameException("��ȡָ����Դ����"+url);
		}
		if(param==null){
			throw new FrameException("û�л�ȡ��ָ����Դ��"+url);
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
