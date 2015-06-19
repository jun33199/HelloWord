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
 *  �Զ��屨��-������ʽ������
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�BaseFormulaUtil   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-12-1 ����1:26:41   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-12-1 ����1:26:41   
 * �޸ı�ע��   
 * @version  1.0
 */
public class BaseFormulaUtil {

	/**
	 * �ڴ��������� map��Ϊ����,��һ��Ϊ�걨���,�Ա�ID��ΪKEY ,�ڶ���Ϊ�������,�Ա�����ID��ΪKEY,���б䳤���ID����Լ�����д���
	 */
	@SuppressWarnings("rawtypes")
	private static Map map = new HashMap();

	/**
	 * ���ݲ�������
	 */
	@SuppressWarnings("unused")
	private static DBAccess dba = null;

	/**
	 * �����map��Ϊ3��map ��һ��Ϊ������ һ���Դ���� ������ΪKEY �ڶ���Ϊ������в� �Դ����Ĵ���Ϊkey ������Ϊ������в�
	 * �Ը�������Ϊkey
	 */
	@SuppressWarnings("rawtypes")
	private static Map codeMap = new HashMap();

	/**
	 * ���ܴ������Ϊ������ڴ����Ĺ�������
	 * 
	 * @param mapIn
	 *            Map �걨���ڴ����
	 * @param codeMapIn
	 *            Map ������ڴ����
	 * @exception FrameException
	 *                ϵͳ�쳣
	 */
	@SuppressWarnings("rawtypes")
	public BaseFormulaUtil(Map mapIn, Map codeMapIn) throws FrameException {
		map = mapIn;
		codeMap = codeMapIn;
		if (map == null || map.size() == 0) {
			throw new FrameException("���ϵͳ�����걨������Ϊ��!!");
		} else if (codeMap == null || codeMap.size() == 0) {
			throw new FrameException("���ϵͳ������������Ϊ��!!");
		}

	}

	/**
	 * ���ܴ������Ϊ���ݿ��������Ĺ�������
	 * 
	 * @param map
	 *            HashMap �걨���ڴ����
	 * @param dba
	 *            DBAccess ���ݿ��������
	 * @exception FrameException
	 *                ϵͳ�쳣
	 */
	@SuppressWarnings("rawtypes")
	public BaseFormulaUtil(Map mapIn, DBAccess dbAccess) throws FrameException {
		dba = dbAccess;
		map = mapIn;
		if (map == null || map.size() == 0) {
			throw new FrameException("���ϵͳ�����걨������Ϊ��!!");
		}
		init();
	}

	/**
	 * ��ʼ������
	 * @exception FrameException ϵͳ�쳣
	 */
	private void init() throws FrameException {
		codeMap.clear();
	}

	/**
	 * �����ݿ��л�ô����
	 * 
	 * @param sql SQL��䡣
	 * @return ArrayList �����
	 * @throws Exception ���ײ㷢���κ��쳣ʱ�׳���
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
	 * �ж�һ����ҵ�Ƿ�Ϊ����
	 * 
	 * @return ���� true ����false
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
	 * ��һ��ֵ���͹̶�һ��ֵ�Ƚ� ���ȫ����opΪtrue
	 * 
	 * @param tableid �걨��ID
	 * @param start ��ʼ
	 * @param end ����
	 * @param op �ȽϷ�
	 * @param value  �Ƚ�ֵ
	 * @return true false
	 * @throws FrameException ϵͳ�쳣
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean EVERY(String tableid, int start, int end, String op,double value) throws FrameException {
		try {
			// ת����ʼ���
			String indexStart = String.valueOf(start);
			String indexEnd = String.valueOf(end);
			// ��ȡ�걨�ı�����
			Map tableMap = null;
			if (map.get(tableid) != null) {
				tableMap = (Map) map.get(tableid);
			} else {
				tableMap = new HashMap();
				map.put(tableid, tableMap);
				// return false;
			}
			// ��Ѱ��������
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
					// ��ȡ�����
					if (tmpStrs == null) { // �����һ���Ǳ䳤������д���
						index = key;
					} else { // �����һ���䳤������д���
						index = tmpStrs[0];
					}
					// ���з�Χ�ж�,����ڷ�Χ��������ж�
					if (ReportsUtils.checkIndexIsBetweenRange(index,indexStart, indexEnd)) {
						BaseFormula.tempVerMap.put("T" + tableid + "I" + key,tmpStr);
						// System.out.println("T"+tableid+"I"+key+"|"+tmpStr);
						if (BaseFormula.samplecalculateExpression(tmpStr + op + value).equals("false")) {
							return false;
						}
					}
				} else {
					tableMap.put(key, "0.00");
					// throw new Exception("�޷���ȡ��������ֵ!!" + tableid);
				}
			}
		} catch (java.lang.ArithmeticException e) {
			return false;
		} catch (Exception e) {
			System.out.println("������ʽʱ����");
			// e.printStackTrace();
			throw new FrameException("������ʽʱ����");
		} catch (Throwable t) {
			return false;
		} // end try catch
		return true;
	}

	/**
	 * ��һ��ֵ���͹̶�һ��ֵ�Ƚ� ���ȫ����opΪtrue
	 * @param tableid �걨��ID
	 * @param start  ��ʼ
	 * @param end ����
	 * @param op �ȽϷ�
	 * @param value �Ƚ�ֵ
	 * @return true false ���ؽ��
	 * @throws FrameException ϵͳ�쳣
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean ABSEVERY(String tableid, int start, int end,String op, double value) throws FrameException {
		try {
			// ת����ʼ���
			String indexStart = String.valueOf(start);
			String indexEnd = String.valueOf(end);
			// ��ȡ�걨�ı�����
			Map tableMap = null;
			if (map.get(tableid) != null) {
				tableMap = (Map) map.get(tableid);
			} else {
				return false;
			}
			// ��Ѱ��������
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
					// ��ȡ�����
					if (tmpStrs == null) { // �����һ���Ǳ䳤������д���
						index = key;
					} else { // �����һ���䳤������д���
						index = tmpStrs[0];
					}
					// ���з�Χ�ж�,����ڷ�Χ��������ж�
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
			System.out.println("������ʽʱ����");
			// e.printStackTrace();
			throw new FrameException("������ʽʱ����");
		} catch (Throwable t) {
			return false;
		} // end try catch
		return true;

	}

	/**
	 * ��ѯ�ڵ�����
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
				throw new Exception("��һ���������ԣ����ѯ��ʽ����");
			}
			if (codeMap.get(str[0]) == null) {
				throw new Exception("��һ���������ԣ�û�ж�Ӧ�Ĵ����");
			}
			HashMap table = (HashMap) codeMap.get(str[0]);
			if (table.get(key) == null) {
				return "";
				// throw new Exception("�ڶ����������ԣ��˴�����в����ڵĴ���" + key);
			}
			HashMap row = (HashMap) table.get(key);
			if (row.get(str[1]) == null) {
				throw new Exception("��һ���������ԣ��˴������û�и���");
			}
			return row.get(str[1]).toString();
		} catch (Exception e) {
			System.out.println("������ʽʱ����");
			// e.printStackTrace();
			/** 2004-09-04 by yyx */
			throw new FrameException("������ʽʱ����");
		}

	}

	/**
	 * �ж������ַ����Ƿ����
	 * 
	 * @param str1 Դ�ַ���
	 * @param str2  �Ƚ��ַ���
	 * @return �Ƚ�Դ�ַ�����ȡֵ�ͱȽ��ַ����ıȽ�
	 */
	public static boolean EQUAL(String str1, String str2) {
		return str1.equals(str2);
	}

	/**
	 * �ж�����double��ֵ�Ƿ����
	 * 
	 * @param str1  Դ�ַ���
	 * @param str2 �Ƚ��ַ���
	 * @return �Ƚ�Դ�ַ�����ȡֵ�ͱȽ��ַ����ıȽ�
	 */
	public static boolean EQUAL(double str1, double str2) {
		return str1 == str2;
	}

	/**
	 * �Ƿ����
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean CONTAINS(String str1, String str2) {
		return str1.contains(str2);
	}
	/**
	 * �ж������ַ����Ƿ����
	 * 
	 * @param str1  Դ�ַ���
	 * @param str2  �Ƚ��ַ���
	 * @return �Ƚ�Դ�ַ�����ȡֵ�ͱȽ��ַ����ıȽ�
	 */
	public static boolean NOTEQUAL(String str1, String str2) {
		return !str1.equals(str2);
	}

	/**
	 * ���ַ�������ȡ����ΪI���ַ��� ���Ļ�����0��ʼ
	 * 
	 * @param str Դ�ַ���
	 * @param i ȡֵ����
	 * @return Դ�ַ�����ȡֵ
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
	 * ���ַ�������ȡ����ΪI���ַ��� ���Ļ�����0��ʼ
	 * 
	 * @param str Դ�ַ���
	 * @param i  ȡֵ����
	 * @param value �Ƚ��ַ���
	 * @return �Ƚ�Դ�ַ�����ȡֵ�ͱȽ��ַ����ıȽ�
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
	 * ���ַ������ҷ�ȡ����ΪI���ַ��� ���Ļ�����0��ʼ
	 * 
	 * @param str Դ�ַ���
	 * @param i ȡֵ����
	 * @return Դ�ַ���ȡֵ
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
	 * ���ַ������ҷ�ȡ����ΪI���ַ��� ���Ļ�����0��ʼ
	 * 
	 * @param str Դ�ַ���
	 * @param i ȡֵ����
	 * @param value �Ƚ��ַ���
	 * @return �Ƚ�Դ�ַ�����ȡֵ�ͱȽ��ַ����ıȽ�
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
	 * ���ַ������м�λ��sȡ����ΪI���ַ��� ���Ļ�����0��ʼ
	 * 
	 * @param str Դ�ַ���
	 * @param s  ȡֵ��ʼλ��
	 * @param i  ȡֵ����
	 * @return Դ�ַ�����ȡֵ
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
	 * ���ַ������м�λ��sȡ����ΪI���ַ��� ���Ļ�����0��ʼ
	 * 
	 * @param str Դ�ַ���
	 * @param s ȡֵ��ʼλ��
	 * @param i ȡֵ����
	 * @param value �Ƚ��ַ���
	 * @return �Ƚ�Դ�ַ�����ȡֵ�ͱȽ��ַ����ıȽ�
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
	 * ȡС�����Ķ���λ
	 * 
	 * @param d ��ֵ
	 * @param i С�����λ��
	 * @return round�����ֵ
	 */
	public static double ROUND(double d, int i) {
		double s = 10.00;
		for (int j = 1; j < i; j++) {
			s = s * 10;
		}
		return Math.round(d * s) / s;
	}

	/**
	 * ȡС�����Ķ���λ
	 * 
	 * @param d  ��ֵ
	 * @return round�����ֵ
	 */
	public static double ROUND(double d) {

		return Math.round(d * 100.00) / 100;
	}

	/**
	 * �߼���ϵ ���boolΪtrue ����booltrue ���򷵻�boolfalse
	 * 
	 * @param bool �ж��߼����ʽ
	 * @param booltrue �߼����ʽ1
	 * @param boolfalse �߼����ʽ2
	 * @return ���boolΪtrue ����booltrue ���򷵻�boolfalse
	 */
	public static boolean IF(boolean bool, boolean booltrue, boolean boolfalse) {
		if (bool) {
			return booltrue;
		} else {
			return boolfalse;
		}
	}

	/**
	 * �߼���ϵAND ���boolȫ��Ϊtrue ����true ���򷵻�false
	 * 
	 * @param bool �ж��߼����ʽ
	 * @return ���boolΪtrue ����true ���򷵻�false
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
	 * �߼���ϵOR ���bool��true ����true ���򷵻�false
	 * 
	 * @param bool
	 *            �ж��߼����ʽ
	 * @return ���bool��true ����true ���򷵻�false
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
	 * ��Ӻ���
	 * 
	 * @param arg
	 *            Ҫ��ӵ�ֵ
	 * @return ���ֵ��
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
	 * ����ָ���걨��ļ����start�ӵ�end
	 * 
	 * @param tableid
	 *            �걨�� ID
	 * @param start
	 *            ��ʼ
	 * @param end
	 *            ����
	 * @return ���ֵ��
	 * @exception FrameException
	 *                ϵͳ�쳣
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static double SUM(String tableid, int start, int end)
			throws FrameException {
		double result = 0.00;
		try {
			// ת����ʼ���
			String indexStart = String.valueOf(start);
			String indexEnd = String.valueOf(end);
			// ��ȡ�걨�ı�����
			Map tableMap = null;
			if (map.get(tableid) != null) {
				tableMap = (Map) map.get(tableid);
			} else {
				tableMap = new HashMap();
				map.put(tableid, tableMap);
				// throw new FrameException("SUM()�������,�޷�ȡ���������걨������!" +
				// tableid);
			}
			// ��Ѱ��������
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
					// ��ȡ�����
					if (tmpStrs == null) { // �����һ���Ǳ䳤������д���
						index = key;
					} else { // �����һ���䳤������д���
						index = tmpStrs[0];
					}
					// ���з�Χ�ж�,����ڷ�Χ��������ж�
					if (ReportsUtils.checkIndexIsBetweenRange(index,indexStart, indexEnd)) {
						BaseFormula.tempVerMap.put("T" + tableid + "I" + key,tmpStr);
						// System.out.println("[T"+tableid+"I"+key+"|"+key+"]"+tmpStr);
						result = result + Double.parseDouble(tmpStr);
						// System.out.println("[T"+tableid+"I"+key+"|"+key+"]"+result);
					}
				} else {
					tableMap.put(key, "0.00");
					// throw new Exception("�޷���ȡ��������ֵ!!" + tableid);
				}
			}
			result = new Double(Math.round(result * 100.00) / 100.00).doubleValue();
		} catch (java.lang.ArithmeticException e) {
			// e.printStackTrace();
			throw new FrameException(e.getMessage());
		} catch (Exception e) {
			System.out.println("������ʽʱ����");
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
	 * ȡ���������ַ������ֽ�С��һ��
	 * 
	 * @param str1 String str1,����Ϊ����
	 * @param str2 String str2,����Ϊ����
	 * @return String ��С��һ��
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
	 * ȡ���������ַ������ֽ�С��һ��
	 * 
	 * @param str1 double str1,����Ϊ����
	 * @param str2 double str2,����Ϊ����
	 * @return double ��С��һ��
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
	 * ȡ���������ַ������ֽϴ��һ��
	 * 
	 * @param str1  String str1,����Ϊ����
	 * @param str2  String str2,����Ϊ����
	 * @return String �ϴ��һ��
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
	 * ��������Ĳ����Ƿ�Ϊ�� ������򷵻�0.00
	 * 
	 * @param str1
	 * @return ����ǿ��򷵻�0.00�����򷵻�str1
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
