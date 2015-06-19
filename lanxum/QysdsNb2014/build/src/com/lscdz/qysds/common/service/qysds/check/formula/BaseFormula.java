package com.lscdz.qysds.common.service.qysds.check.formula;

import gnu.jel.CompiledExpression;
import gnu.jel.Evaluator;
import gnu.jel.Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import com.lscdz.qysds.common.service.qysds.db.DBAccess;
import com.lscdz.qysds.common.service.qysds.util.Arith;
import com.lscdz.qysds.common.service.qysds.util.ReportsUtils;
import com.lscdz.qysds.common.service.qysds.util.StringUtils;
import com.lscdz.util.MyLogger;

/**
 * �Զ��屨��-������ʽ��
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�BaseFormula   
 * �������� BaseFormula �������� ������ʽ����������������ع��ߵĻ�������  
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-12-1 ����1:10:59   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-12-1 ����1:10:59   
 * �޸ı�ע��   
 * @version  1.0
 */
public class BaseFormula {

	MyLogger myLogger = new MyLogger(BaseFormula.class);

	/**
	 * ��ʽ���ʽ�п��ܳ��ֵĺ�������
	 */
	public final static String[] formulaOps = { " ", "+", "-", "*", "/", "|",
			"&", "(", ")", ",", "!", "=", ">", "<", "\"", "'" };
	/**
	 * ��ʽ���ʽ�п��ܳ��ֵĺ���(����������)����
	 */
	public final static String[] formulaMethods = { "EVERY", "ABSEVERY", "SUM" };

	/**
	 * ��ʽ���ʽ�п��ܳ��ֵĺ���
	 */
	public final static String formulaOp = " +-*/|&(),!=><\"";

	/**
	 * ������걨����������������Ϣ��ֵ-���շ�ʽ
	 */
	public static String DATA_CONSTANT_KEY_ZSFS = "ZSFS";

	/**
	 * ������걨����������������Ϣ��ֵ-������������
	 */
	public static String DATA_CONSTANT_KEY_SSJJLX = "SSJJLX";

	/**
	 * ������걨����������������Ϣ��ֵ-������ҵ
	 */
	public static String DATA_CONSTANT_KEY_SSHY = "SSHY";

	/**
	 * ������걨����������������Ϣ��ֵ-�ƻ��ƶ�
	 */
	public static String DATA_CONSTANT_KEY_CKZD = "CKZD";

	/**
	 * ������걨����������������Ϣ��ֵ-���ʹ�����ʽ
	 */
	public static String DATA_CONSTANT_KEY_GZGLXS = "GZGLXS";

	/**
	 * ������걨����������������Ϣ��ֵ-��������
	 */
	public static String DATA_CONSTANT_KEY_JMLX = "JMLX";

	/**
	 * ������걨����������������Ϣ��ֵ-˰Դ��ʶ
	 */
	public static String DATA_CONSTANT_KEY_SYBS = "SYBS";
	
	/**
	 * �û���ı���
	 */
	public static String DATA_CONSTANT_KEY_BBMSF = "BBMSF";

	/**
	 * ��ǰ��ʽ���ʽ
	 */
	@SuppressWarnings("unused")
	private String theExpression = "";

	/**
	 * ���ݲ�������
	 */
	private DBAccess dba = null;

	/**
	 * ��ǰ����ʹ�õ�Sql�Ӿ䣬������ʹ�á�
	 */
	protected String sqlStr = "";

	/**
	 * ������Ϣ��������ʹ�á�
	 */
	public String messages = "";

	/**
	 * ������������
	 */
	@SuppressWarnings("rawtypes")
	private Map dataMap = new HashMap();

	/**
	 * �����map��Ϊ3��map ��һ��Ϊ������ һ���Դ���� ������ΪKEY �ڶ���Ϊ������в� �Դ����Ĵ���Ϊkey ������Ϊ������в�
	 * �Ը�������Ϊkey
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map codeMap = new HashMap();

	/**
	 * �����ֶι�����
	 */
	@SuppressWarnings("rawtypes")
	public Map formulaList = new HashMap();

	/**
	 * ��ʹ�õĲ���hashmap
	 */
	@SuppressWarnings("rawtypes")
	public static Map tempVerMap = new HashMap();

	/**
	 * ���췽��
	 * 
	 * @param adb
	 *            �걨������
	 * @param dba
	 *            �������Ӷ���
	 * @roseuid 3E813443029B
	 */
	@SuppressWarnings("rawtypes")
	public BaseFormula(Map adb, DBAccess dba) {
		this.dataMap = adb;
		this.dba = dba;
		if (ResourceManager.isDebug()) {
			myLogger.log("----------------------------��������˰�˻�����Ϣ----------------------------");
			myLogger.log("CKZD="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_CKZD));
			myLogger.log("ZSFS="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_ZSFS));
			myLogger.log("SSJJLX="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SSJJLX));
			myLogger.log("SSHY="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SSHY));
			myLogger.log("GZGLXS="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_GZGLXS));
			myLogger.log("JMLX="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_JMLX));
			myLogger.log("SYBS="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SYBS));
			myLogger.log("BBMSF="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_BBMSF));
			myLogger.log("----------------------------��������˰�˻�����Ϣ----------------------------");
		}
		init();
	}

	@SuppressWarnings("rawtypes")
	private void init() {
		this.codeMap = new HashMap();
	}

	/**
	 * ���췽�������ɹ�ʽ����,���������㡣
	 * 
	 * @param map
	 *            �걨�����ݶ���
	 * @param codeMap
	 *            ��������ݶ���
	 */
	@SuppressWarnings("rawtypes")
	public BaseFormula(Map map, Map codeMap) {
		this.dataMap = map;
		this.codeMap = codeMap;
	}

	/**
	 * ����ָ�����ʽ��ֵ��
	 * 
	 * @param expression
	 *            ������ı��ʽ
	 * @return ���ʽ����ֵ
	 * @throws Exception
	 *             ����ʧ��
	 * @roseuid 3E81344303C7
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List calculateExpression(String expression, String[] methodNames)
			throws Exception {
		List rtnList = new ArrayList();
		Result returnResult = new Result();
		// �滻��Ĺ�ʽ���ʽ
		String rtnFomula = "";

		try {

			Class[] staticLib = new Class[2];
			staticLib[0] = Class.forName("java.lang.Math");
			staticLib[1] = Class.forName("com.lscdz.qysds.common.service.qysds.check.formula.BaseFormulaUtil");
			Library lib = new Library(staticLib, null,null,null,null);
			lib.markStateDependent("random", null);
			CompiledExpression expr_c = null;
			// modified by guoxh, 20070112
			// List formatExpression =
			// this.getFormatFormula(expression,methodNames);
			// new BaseFormulaUtil(this.dataMap, this.dba);
			// tempVerMap = new HashMap(); //ȡ����ı��ʽ�д���ı������ͱ���ֵ
			// for (int i = 0; i < formatExpression.size(); i++) {
			// loger.write("==>[formatExpression" + i + "]=" +
			// (String) formatExpression.get(i),
			// ILog.LOG_LEVEL_DEBUG_LOW);
			// expr_c = Evaluator.compile((String) formatExpression.get(i),
			// lib);
			// Object result = null;
			// if (expr_c != null) {
			// result = expr_c.evaluate(null);
			// myLogger.log("���õ������������Ľ��[" + result + "]");
			// }
			// returnResult = new Result();
			// returnResult.setResult(result);
			// returnResult.setVarMap(tempVerMap);
			// rtnList.add(returnResult);
			// }
			// add by guoxh, 20070112
			Map formatMap = this.getFormatFormula(expression, methodNames);
			
			new BaseFormulaUtil(this.dataMap, this.dba);
			tempVerMap = new HashMap(); // ȡ����ı��ʽ�д���ı������ͱ���ֵ
			Iterator fi = formatMap.keySet().iterator();
			String key = "";
			while (fi.hasNext()) {
				key = (String) fi.next();
				//myLogger.log("==>[formatExpression" + key + "]="+ (String) formatMap.get(key));
				// ��¼��ǰ����Ϣ
				Map m = new HashMap();
				m.put(key, key);
				// ��ʽ���ʽ
				// String rtnFomula = (String) formatMap.get(key);
				// rtnFomula = ReportsUtils.replaceEqualOp(rtnFomula);
				rtnFomula = ReportsUtils.replaceEqualOp((String) formatMap.get(key));
				// myLogger.log("rtnFomula: "+rtnFomula);
				// ������ʽ
				expr_c = Evaluator.compile(rtnFomula, lib);
				Object result = null;
				if (expr_c != null) {
					result = expr_c.evaluate(null);
					// myLogger.log("���õ������������Ľ��[" + result + "]");
				}
				returnResult = new Result();
				returnResult.setResult(result);
				returnResult.setVarMap(m);// ��ŵ�ǰ���ʽ������Ϣ
				// myLogger.log("setVarMap:"+m);
				rtnList.add(returnResult);
			}

		} catch (FrameException e) {
			myLogger.log("������ʽʱ����");
			myLogger.log("��ʽ��" + expression + "���滻��" + rtnFomula);
			e.printStackTrace();
			// throw e;
			returnResult = new Result();
			returnResult.setResult(new Boolean(false));
			returnResult.setVarMap(tempVerMap);
			rtnList.add(returnResult);
		} catch (java.lang.ArithmeticException e) {
			myLogger.log("������ʽʱ����");
			myLogger.log("��ʽ��" + expression + "���滻��" + rtnFomula);
			e.printStackTrace();
			returnResult = new Result();
			returnResult.setResult(new Boolean(false));
			returnResult.setVarMap(tempVerMap);
			rtnList.add(returnResult);
		} catch (Exception e) {
			myLogger.log("������ʽʱ����");
			myLogger.log("��ʽ��" + expression + "���滻��" + rtnFomula);
			e.printStackTrace();
			// throw e;
			returnResult = new Result();
			returnResult.setResult(new Boolean(false));
			returnResult.setVarMap(tempVerMap);
			rtnList.add(returnResult);
		} catch (Throwable t) {
			myLogger.log("������ʽʱ����");
			myLogger.log("��ʽ��" + expression + "���滻��" + rtnFomula);
			t.printStackTrace();
			// Debug.out(t.getMessage());
			returnResult = new Result();
			returnResult.setResult(new Boolean(false));
			returnResult.setVarMap(tempVerMap);
			rtnList.add(returnResult);
		} // end try catch
			// myLogger.log("returnResult[" + returnResult + "]");
		return rtnList;
	}

	/**
	 * ����ָ�����ʽ��ֵ��
	 * 
	 * @param expression  ������ı��ʽ
	 * @return ���ʽ����ֵ
	 * @throws FrameException  ����ʧ��
	 * @roseuid 3E81344303C7
	 */
	@SuppressWarnings("rawtypes")
	public static String samplecalculateExpression(String expression)throws FrameException {
		try {
			// ���������ӳ����ģ��
			Class[] staticLib = new Class[1];
			staticLib[0] = Class.forName("java.lang.Math");
			Library lib = new Library(staticLib, null,null,null,null);
			lib.markStateDependent("random", null);
			CompiledExpression expr_c = null;
			expr_c = Evaluator.compile(expression, lib);
			Object result = null;
			if (expr_c != null) {
				result = expr_c.evaluate(null);
			}
			return result.toString();
		} catch (FrameException e) {
			System.out.println("������ʽʱ����");
			e.printStackTrace();
			throw e;
		} catch (java.lang.ArithmeticException e) {
			return "false";
		} catch (Exception e) {
			System.out.println("������ʽʱ����");
			e.printStackTrace();
			throw new FrameException("������ʽʱ����");
		} catch (Throwable t) {
			return "false";
		} // end try catch
	}

	/**
	 * ����ʽ���ʽ�е�Ԥ�����������ֵ�滻������"ZSFS"��T1I5��T1HJI4���滻Ϊ�걨���е����ݡ� ����걨��û���������滻Ϊ0
	 * 
	 * @param formula String ��ʽ���ʽ
	 * @return ArrayList �滻��Ĺ�ʽ���ʽ������Ԥ����ı���������
	 * @throws Exception ϵͳ�쳣
	 * @todo ��δ����������ı������뷵��ֵ,��Ҫ���.this.tempVerMap
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public Map getFormatFormula(String formula, String[] methodNames) throws FrameException {
		// �������
		List rtnList = new ArrayList();
		List varList = new ArrayList();
		Map formulaMap = new HashMap();
		int[] indexs = null;
		// ҵ�����
		try {
			// 0.����ǰ��ʽΪ����ַ�����
			String[] fs = formula.split(""); // ����ɵ��ַ�����Ĺ�ʽ���ʽ,ע��:�˴�fs�ĵ�һ��Ԫ��Ϊ"",formula�ĵ�һ���ַ�Ӧ�ô�fs[1]��ʼ
			// 1.���ݲ����������ֵ�ǰ��ʽΪ�Ӵ�
			// /1.0.������в���������
			String op;
			Map map_op_index = new HashMap();
			int index = -1;
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
			indexs = new int[map_op_index.size()];
			String tmpStr;
			int tmpIndex1_1 = 0;
			while (iterator.hasNext()) {
				tmpStr = (String) iterator.next();
				indexs[tmpIndex1_1] = Integer.parseInt(tmpStr);
				tmpIndex1_1++;
			}
			indexs = ReportsUtils.bubbleSort(indexs, true); // ��ȡ��������Ժ��index����
			// /1.2.��ȡ�����Ӵ�����������ʱ����
			String tmpStr_1_2;
			varList = BaseFormula.getAllVarAndConDeclareName(formula, indexs); // ��ȡ�ǲ������Ӵ�
			// //��������δ�����Ӵ�
			for (int i = 0; i < varList.size(); i++) {
				tmpStr_1_2 = (String) varList.get(i);
				if ((!(BaseFormula.DATA_CONSTANT_KEY_ZSFS.equals(tmpStr_1_2)
						|| BaseFormula.DATA_CONSTANT_KEY_SSJJLX
								.equals(tmpStr_1_2)
						|| BaseFormula.DATA_CONSTANT_KEY_SSHY
								.equals(tmpStr_1_2)
						|| BaseFormula.DATA_CONSTANT_KEY_CKZD
								.equals(tmpStr_1_2)
						|| BaseFormula.DATA_CONSTANT_KEY_GZGLXS
								.equals(tmpStr_1_2)
						|| BaseFormula.DATA_CONSTANT_KEY_JMLX
								.equals(tmpStr_1_2)
						|| BaseFormula.DATA_CONSTANT_KEY_SYBS
								.equals(tmpStr_1_2) 
						|| BaseFormula.DATA_CONSTANT_KEY_BBMSF
								.equals(tmpStr_1_2) || tmpStr_1_2.indexOf("T") != -1))
						|| ReportsUtils.checkVarNameIsMethodNames(tmpStr_1_2,
								methodNames)) { // ���ΪԤ��������򱨱�����������Ϊ�������б���
					varList.remove(i--);
				}
			}

			// 2.��������Ӵ��Ƿ�ΪԤ�������
			String tmpStr_2 = null;
			// System.out.println("varList.size()=" + varList.size());
			// /2.0.���Ϊ������ϢԤ�������������滻������Ϣ
			System.out.println("-----------------------���������Ϣ����--------------------------");

			for (int i = 0; i < varList.size(); i++) {
				tmpStr_2 = (String) varList.get(i);
				//System.out.println("��ʽ����=" + tmpStr_2);
				String tmpStr_2_0 = null;
				if (BaseFormula.DATA_CONSTANT_KEY_ZSFS.equals(tmpStr_2)) {
					if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_ZSFS) != null) {
						tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_ZSFS);
						// formula = StringUtils.replaceAll(formula, tmpStr_2,
						// tmpStr_2_0);
						// Modified by Guoxh, 2006.12.20
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
					} else {
						throw new FrameException("��ʽ����Ԥ��ʽ���������շ�ʽ�޷���ȡ��!!!["+ formula + "]");
					}
					varList.remove(i--);
				} else if (BaseFormula.DATA_CONSTANT_KEY_SSJJLX
						.equals(tmpStr_2)) {
					if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SSJJLX) != null) {
						tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SSJJLX);
						// formula = StringUtils.replaceAll(formula, tmpStr_2,
						// tmpStr_2_0);
						// Modified by Guoxh, 2006.12.20
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
					} else {
						throw new FrameException("��ʽ����Ԥ��ʽ�������������������޷���ȡ��!!!["+ formula + "]");
					}
					varList.remove(i--);
				} else if (BaseFormula.DATA_CONSTANT_KEY_SSHY.equals(tmpStr_2)) {
					if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SSHY) != null) {
						tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SSHY);
						// formula = StringUtils.replaceAll(formula, tmpStr_2,
						// tmpStr_2_0);
						// Modified by Guoxh, 2006.12.20
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
					} else {
						throw new FrameException("��ʽ����Ԥ��ʽ������������ҵ�޷���ȡ��!!!["+ formula + "]");
					}
					varList.remove(i--);
				} else if (BaseFormula.DATA_CONSTANT_KEY_CKZD.equals(tmpStr_2)) {
					if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_CKZD) != null) {
						tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_CKZD);
						// formula = StringUtils.replaceAll(formula, tmpStr_2,
						// tmpStr_2_0);
						// Modified by Guoxh, 2006.12.20
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
					} else {
						throw new FrameException("��ʽ����Ԥ��ʽ�����󣨲ƻ��ƶ��޷���ȡ��!!!["+ formula + "]");
					}
					varList.remove(i--);
				} else if (BaseFormula.DATA_CONSTANT_KEY_GZGLXS
						.equals(tmpStr_2)) {
					if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_GZGLXS) != null) {
						tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_GZGLXS);
						// formula = StringUtils.replaceAll(formula, tmpStr_2,
						// tmpStr_2_0);
						// Modified by Guoxh, 2006.12.20
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
					} else {
						throw new FrameException("��ʽ����Ԥ��ʽ�����󣨹��ʹ�����ʽ�޷���ȡ��!!!["+ formula + "]");
					}
					varList.remove(i--);
				} else if (BaseFormula.DATA_CONSTANT_KEY_JMLX.equals(tmpStr_2)) {
					if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_JMLX) != null) {
						tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_JMLX);
						// formula = StringUtils.replaceAll(formula, tmpStr_2,
						// tmpStr_2_0);
						// Modified by Guoxh, 2006.12.20
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
					} else {
						throw new FrameException("��ʽ����Ԥ��ʽ�����󣨼�����ʽ�޷���ȡ��!!!["+ formula + "]");
					}
					varList.remove(i--);
				} else if (BaseFormula.DATA_CONSTANT_KEY_SYBS.equals(tmpStr_2)) {
					/**
					 * ���˰Դ��ʶ ���б�17��ʱ��Ա�17�ķ�������ϼƽ��б�����֤ wangcy 2014-01-07
					 */
					if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SYBS) != null) {
						tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SYBS);
						// formula = StringUtils.replaceAll(formula, tmpStr_2,
						// tmpStr_2_0);
						// Modified by Guoxh, 2006.12.20
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
					} else {
						throw new FrameException("��ʽ����Ԥ��ʽ�����󣨼�����ʽ�޷���ȡ��!!!["+ formula + "]");
					}
					varList.remove(i--);
				}else if (BaseFormula.DATA_CONSTANT_KEY_BBMSF.equals(tmpStr_2)) {
						/**
						 * ���˰Դ��ʶ ���б�17��ʱ��Ա�17�ķ�������ϼƽ��б�����֤ wangcy 2014-01-07
						 */
						if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_BBMSF) != null) {
							tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_BBMSF);
							// formula = StringUtils.replaceAll(formula, tmpStr_2,
							// tmpStr_2_0);
							// Modified by Guoxh, 2006.12.20
							formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
						} else {
							throw new FrameException("��ʽ����Ԥ��ʽ������!!!["+ formula + "]");
						}
						varList.remove(i--);
					}
			}
			// ��ȡ�������ñ���������List(parVarList)
			// for(int i=0;i<varList.size();i++){
			// parVarList.add(new String(((String)varList.get(i)).getBytes()));
			// }
			// ������ٴ��ڱ�����ֱ�ӽ���ʽ���뷵���б�
			if (varList.size() == 0) {
				rtnList.add(formula);
			}
			// /2.1.���Ϊ�䳤��ϼƶ������ȡ���кϼƽ�����ֵ�滻(TmHJIn)
			System.out.println("-----------------------���Ϊ�䳤��ϼƶ������ȡ���кϼƽ�����ֵ�滻(TmHJIn)--------------------------");
			for (int i = 0; i < varList.size(); i++) {
				tmpStr_2 = (String) varList.get(i);
				//System.out.println("��ʽ����=" + tmpStr_2);
				if (tmpStr_2.indexOf("T") != -1 && tmpStr_2.indexOf("HJI") != -1) {
					String[] ti = ReportsUtils.parseQuoteTmHJIn(tmpStr_2);
					if (this.dataMap.get(ti[0]) != null) {
						Map tMap = (Map) this.dataMap.get(ti[0]);
						String itemID = ti[1];
						// ��Ѱ��ȡItemID������ȡ��Ӧ��value
						Iterator iterator_2_1 = tMap.keySet().iterator();
						double result_2_2 = 0.00;
						String itemVlaue;
						String itemKey;
						while (iterator_2_1.hasNext()) {
							itemKey = (String) iterator_2_1.next();
							if (itemID.equals(ReportsUtils.getRootItemID(itemKey))) {
								itemVlaue = (String) (tMap.get(itemKey));
								System.out.println("itemKey=" + itemKey+ ",itemVlaue=" + itemVlaue);
								// modified by guoxh, 2006.12.25
								result_2_2 += Double.parseDouble(checkItemValue(itemVlaue));
							}
						}
						// ���ϼ����滻�빫ʽ
						// formula = StringUtils.replaceAll(formula,
						// tmpStr_2,Arith.round(result_2_2, 2) + "");
						// formula =
						// ReportsUtils.replaceQuoteVarInFormula(formula,
						// tmpStr_2, Arith.roundStrByDouble(result_2_2, 2) +
						// "");
						// formula =
						// ReportsUtils.replaceQuoteVarInFormula(formula,
						// tmpStr_2, Arith.roundStrByDouble(result_2_2, 2) +
						// "");
						// modified by Guoxh, 2007.01.24
						// ���ȱ���4λ,�Ա�֧�ְٷֱ�
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2,Arith.roundStrByDouble(result_2_2, 4) + "");
						varList.remove(i--);

					} else {
						// formula = StringUtils.replaceAll(formula,
						// tmpStr_2,"0.00");
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, "0.00");
						varList.remove(i--);
					}
				}

			}
			// ������ٴ��ڱ�����ֱ�ӽ���ʽ���뷵���б�
			if (varList.size() == 0) {
				rtnList.add(formula);
			}
			// /2.2.���Ϊ����������ȡ���кϼƽ�����ֵ�滻(TmIn),����������ı���
			System.out.println("-----------------------���Ϊ����������ȡ���кϼƽ�����ֵ�滻(TmIn),����������ı���--------------------------");
			root22: 
			for (int i = 0; i < varList.size(); i++) {
				tmpStr_2 = (String) varList.get(i); // T4I1
				//System.out.println("��ʽ����=" + tmpStr_2);

				if (tmpStr_2.indexOf("T") != -1&& tmpStr_2.indexOf("HJI") == -1) {
					String[] ti = ReportsUtils.parseQuoteTmIn(tmpStr_2);
					if (this.dataMap.get(ti[0]) != null) {
						Map tMap = (Map) this.dataMap.get(ti[0]);
						String itemID = ti[1];
						// ��Ѱ��ȡItemID������ȡ��Ӧ��value
						Iterator iterator_2_1 = tMap.keySet().iterator();
						String itemVlaue;
						String itemKey;
						boolean flag22 = true;
						while (iterator_2_1.hasNext()) {
							itemKey = (String) iterator_2_1.next();
							if (itemID.equals(ReportsUtils.getRootItemID(itemKey))) { // ??������λʱ����������//��Ѱ����item��key����Ƿ���itemID��ͷ������������ϼ�
								itemVlaue = (String) (tMap.get(itemKey));
								flag22 = false;
								if (ReportsUtils.splitItemID(itemKey) == null) { // �ж��Ƿ�������,������Ļ����д���
								// System.out.println("�滻ǰ��ʽ:" + formula);
									// formula =
									// StringUtils.replaceAll(formula,tmpStr_2,Arith.round(itemVlaue,
									// 2) + "");
									// Modified by Guoxh, 2006.12.25
									// formula = ReportsUtils.
									// replaceQuoteVarInFormula(formula,
									// tmpStr_2,
									// Arith.roundStr(itemVlaue, 2) + "");
									// ��itemVlaue�����Ƿ�Ϊ�յ��ж�(Modified by Guoxh,
									// 2006.12.25)
									// formula =
									// ReportsUtils.replaceQuoteVarInFormula(formula,
									// tmpStr_2,
									// arithRound(itemVlaue, 2));
									// modified by Guoxh, 2007.01.24
									// ���ȱ���4λ,�Ա�֧�ְٷֱ�
									formula = ReportsUtils.replaceQuoteVarInFormula(formula,tmpStr_2,arithRound(itemVlaue, 4));
									System.out.println("tmpStr_2=" + tmpStr_2
											+ ",itemKey=" + itemKey
											+ ",itemVlaue=" + itemVlaue
											+ ",Arith.round(itemVlaue, 2)="
											+ this.arithRound(itemVlaue, 2));
									System.out.println("�滻��ʽ:" + formula);
									varList.remove(i--);
									continue root22;
								}
							}
						}
						if (flag22) {
							// formula = StringUtils.replaceAll(formula,
							// tmpStr_2, "0.00");
							formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, "0.00");
							varList.remove(i--);
							continue root22;
						}

					} else {
						// formula = StringUtils.replaceAll(formula, tmpStr_2,
						// "0.00");
						// Modified by Guoxh, 2006.12.20
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, "0.00");
						varList.remove(i--);
						continue root22;
					}
				} else {
					throw new FrameException("��ʽ����Ԥ��ʽ������!!![" + formula + "]");
				}

			}
			// ������ٴ��ڱ�����ֱ�ӽ���ʽ���뷵���б�
			if (varList.size() == 0) {
				rtnList.add(formula);
			}
			// /2.3.���Ϊ����������ȡ���кϼƽ�����ֵ�滻(TmIn),��������ı���
			// System.out.println("-----------------------���Ϊ����������ȡ���кϼƽ�����ֵ�滻(TmIn),��������ı���--------------------------");
			// //������ڶ���дε���������,����Ҫ�������Ƴ���Ҫ�滻�Ĺ�ʽ���д���
			String tmpFormula;
			formulaMap.put("1", formula);
			String baseFormula = formula;
			String[] itemKeys = null;
			root23: for (int i = 0; i < varList.size(); i++) {
				tmpStr_2 = (String) varList.get(i); // ��ȡ��ǰ����
				//System.out.println("��ʽ����=" + tmpStr_2);
				if (tmpStr_2.indexOf("T") != -1 && tmpStr_2.indexOf("HJI") == -1) {
					String[] ti = ReportsUtils.parseQuoteTmIn(tmpStr_2);
					if (this.dataMap.get(ti[0]) != null) {
						Map tMap = (Map) this.dataMap.get(ti[0]);// ��ȡ����������
						String itemID = ti[1];
						// ��Ѱ��ȡItemID������ȡ��Ӧ��value
						Iterator iterator_2_1 = tMap.keySet().iterator();
						String itemVlaue;
						String itemKey;
						boolean flag23 = true;
						while (iterator_2_1.hasNext()) {// ��Ѱ����Item���ҷ���������item
							itemKey = (String) iterator_2_1.next();
							if (itemID.equals(ReportsUtils.getRootItemID(itemKey))) { // ��Ѱ����item��key����Ƿ���itemID��ͷ�����������д���
								itemVlaue = (String) (tMap.get(itemKey));
								flag23 = false;
								itemKeys = ReportsUtils.splitItemID(itemKey);// ��ȡ��ֺ���Ӵ�
								if (itemKeys != null) {// �ж��Ƿ�������,�ǵĻ����й�ʽ���Ʋ�����
									// ������������ȡ��Ӧ��������ʽ�����û�����Ʋ���������
									if (formulaMap.get(itemKeys[1]) != null) {
										tmpFormula = (String) formulaMap.get(itemKeys[1]);
									} else {
										tmpFormula = new String(baseFormula.getBytes());
										formulaMap.put(itemKeys[1], tmpFormula);
									}
									// tmpFormula =
									// ReportsUtils.replaceQuoteVarInFormula(tmpFormula,
									// tmpStr_2,
									// arithRound(itemVlaue, 2) + "");
									// modified by Guoxh, 2007.01.24
									// ���ȱ���4λ,�Ա�֧�ְٷֱ�
									tmpFormula = ReportsUtils.replaceQuoteVarInFormula(tmpFormula, tmpStr_2,arithRound(itemVlaue, 4)+ "");
									formulaMap.put(itemKeys[1], tmpFormula);// �滻����Ժ󽫹�ʽ�����Ӧ����������ʽ����
									System.out.println("tmpStr_2=" + tmpStr_2
											+ ",itemKey=" + itemKey
											+ ",itemVlaue=" + itemVlaue
											+ ",Arith.round(itemVlaue, 2)="
											+ arithRound(itemVlaue, 2));
									System.out.println("����������Ǻϼ����ã��滻��ʽ:"+ tmpFormula);

								}
							}
						}
						if (flag23) {
							formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, "0.00");
							formulaMap.put("1", formula);
						}
					} else {
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, "0.00");
						formulaMap.put("1", formula);
						varList.remove(i--);
						continue root23;
					}
				} else {
					throw new FrameException("��ʽ����Ԥ��ʽ������!!![" + formula + "]");
				}
				varList.remove(i--);
				// ������ֵ�б�

			}
		} catch (Exception ex) {
			throw new FrameException(ex.getMessage());
			// ex.printStackTrace();
		} // end try catch
			// add by Guoxh, 2006.01.16
			// ѭ���������1��Map���Ա��û���滻�ı�����'0.00'�����滻
		if (formulaMap.size() > 1) {
			replaceVariables(formulaMap);
		}
		// ����Map
		return formulaMap;

		// ������ֵ�б�
		// System.out.println("=============");
		// System.out.println(formulaMap);
		// System.out.println("=============");
		// String rtnFomula = null;
		// List rtnFinalList = new ArrayList();
		// Iterator fIte = formulaMap.keySet().iterator();
		// while (fIte.hasNext()) {
		// rtnFomula = (String) formulaMap.get(fIte.next());
		// rtnFomula = ReportsUtils.replaceEqualOp(rtnFomula);
		// rtnFinalList.add(rtnFomula);
		// }
		// //����ֵ
		// return rtnFinalList;

	}

	/**
	 * ѭ�����б��ʽ���Ա��û���滻�ı�����'0.00'�����滻 �������ƣ�T2I1;T13I78
	 * 
	 * @param formulaMap
	 *            ��Ҫ���д���ı��ʽ����
	 * @author guoxh, 2007.01.16
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void replaceVariables(Map formulaMap) {
		// �����ʽ
		Pattern pattern = Pattern.compile("TA[0-9]{1,}I[0-9]{1,}");
		Matcher hasTI = null;
		// ��ʽ���ʽ
		String rtnFomula = null;
		// ѭ����������
		Iterator fIte = formulaMap.keySet().iterator();
		String key = "";
		while (fIte.hasNext()) {
			key = fIte.next().toString();
			rtnFomula = (String) formulaMap.get(key);
			// myLogger.log("rtnFomula = "+rtnFomula);
			hasTI = pattern.matcher(rtnFomula);
			// ��ʽ���ʽ������б���������'0.00'�滻
			if (hasTI.find(0)) {
				rtnFomula = hasTI.replaceAll("0.00");
				myLogger.log("hasTI: " + rtnFomula);
				formulaMap.put(key, rtnFomula);
			}
		}
	}

	/**
	 * �������ֵΪ�գ��򷵻ء�0.00��
	 * 
	 * @param itemVlaue
	 * @param scale
	 * @return String "0.00(itemVlaueΪ��)"����"Arith.roundStr(itemVlaue, scale)"
	 */
	private String arithRound(String itemVlaue, int scale) {
		if (itemVlaue == null || itemVlaue.equals("")) {
			itemVlaue = "0.00";
		} else {
			itemVlaue = Arith.roundStr(itemVlaue, scale);
		}
		return itemVlaue;
	}

	private String checkItemValue(String itemVlaue) {
		if (itemVlaue == null || itemVlaue.equals("")) {
			itemVlaue = "0.00";
		}
		return itemVlaue;
	}

	/**
	 * ����ʽ������,���ո������걨��ID�����������걨�����õĹ�ʽ���˵�
	 * 
	 * @param formulas
	 *            List ȫ����ʽ
	 * @param tID
	 *            String �걨���ID
	 * @return List ���˺�ĵ���ʽ�б�
	 * @throws FrameException
	 *             ϵͳ�쳣
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static List formulaSingleTableFilter(List formulas, String tID)throws FrameException {
		System.out.println("===============================�����ǵ�����˵Ĺ��˷�����ʼ==================================");
		try {
			String[] methodNames = ReportsUtils.getClassMethodNames("com.lscdz.qysds.common.service.qysds.check.formula.formula.BaseFormulaUtil");
			String formula;
			root: // ѭ�����ڵ�
			for (int s = 0; s < formulas.size(); s++) {
				System.out.println("class=" + formulas.get(s).getClass());
				formula = (String) formulas.get(s);
				System.out.println("[" + s + "]" + formula);
				// 0.����ǰ��ʽΪ����ַ�����
				String[] fs = formula.split(""); // ����ɵ��ַ�����Ĺ�ʽ���ʽ,ע��:�˴�fs�ĵ�һ��Ԫ��Ϊ"",formula�ĵ�һ���ַ�Ӧ�ô�fs[1]��ʼ
				System.out.print("==>");
				for (int i = 0; i < fs.length; i++) {
					System.out.print(fs[i] + "'");
				}

				// 1.���ݲ����������ֵ�ǰ��ʽΪ�Ӵ�
				// /1.0.������в���������
				String op;
				Map map_op_index = new HashMap();
				int index = -1;
				for (int i = 0; i < BaseFormula.formulaOps.length; i++) {
					op = BaseFormula.formulaOps[i];
					for (int j = 0; j < fs.length; j++) {
						if (op.equals(fs[j])) {
							map_op_index.put(String.valueOf(j),String.valueOf(j));
						}
					}
				}
				// /1.1.�������������ȥ����������
				Iterator iterator = map_op_index.keySet().iterator();
				int[] indexs = new int[map_op_index.size()];
				String tmpStr;
				int tmpIndex1_1 = 0;
				while (iterator.hasNext()) {
					tmpStr = (String) iterator.next();
					indexs[tmpIndex1_1] = Integer.parseInt(tmpStr);
					tmpIndex1_1++;
				}
				indexs = ReportsUtils.bubbleSort(indexs, true); // ��ȡ��������Ժ��index����
				System.out.print("==>");
				for (int i = 0; i < indexs.length; i++) {
					System.out.print(indexs[i] + "'");
				}
				// /1.3.��ȡ�����Ӵ�
				List varList = BaseFormula.getAllVarAndConDeclareNameindex(
						formula, indexs);
				// /1.3.�������Ӵ������д���
				String varStr = null;
				for (int i = 0; i < varList.size(); i++) {
					String[] varStrs = ((String) varList.get(i)).split(":;");
					// varStr = (String) varList.get(i);
					varStr = varStrs[0];
					// myLogger.log(tmpStr_1_2);
					if (!(BaseFormula.DATA_CONSTANT_KEY_ZSFS.equals(varStr)
							|| BaseFormula.DATA_CONSTANT_KEY_SSJJLX
									.equals(varStr)
							|| BaseFormula.DATA_CONSTANT_KEY_SSHY
									.equals(varStr)
							|| BaseFormula.DATA_CONSTANT_KEY_CKZD
									.equals(varStr)
							|| BaseFormula.DATA_CONSTANT_KEY_GZGLXS
									.equals(varStr)
							|| BaseFormula.DATA_CONSTANT_KEY_JMLX
									.equals(varStr) 
							|| BaseFormula.DATA_CONSTANT_KEY_SYBS
								.equals(varStr)
							|| BaseFormula.DATA_CONSTANT_KEY_BBMSF
								.equals(varStr))) { // �������Ԥ�����������д���
						if (varStr.indexOf("T") != -1 && !ReportsUtils.checkVarNameIsMethodNames(varStr, methodNames)) { // ������걨����������д���
							/**
							 * @todo ������걨����������д���
							 */
							if (varStr.indexOf("T") != -1 && varStr.indexOf("HJI") != -1) {
								String[] ti = ReportsUtils
										.parseQuoteTmHJIn(varStr);
								if (!tID.equals(ti[0])) {
									formulas.remove(s--);
									continue root;
								}
							} else if (varStr.indexOf("T") != -1 && varStr.indexOf("HJI") == -1 && varStr.indexOf("I") != -1) {
								String[] ti = ReportsUtils.parseQuoteTmIn(varStr);
								if (!tID.equals(ti[0])) {
									formulas.remove(s--);
									continue root;
								}
							}

						} else if (ReportsUtils.checkVarNameIsMethodNames(varStr, methodNames)) { // ����Ǻ�����������д���
							/**
							 * @todo ����Ǻ�����������д���
							 */
							String tmpPar;
							for (int j = 0; j < BaseFormula.formulaMethods.length; j++) {
								if (BaseFormula.formulaMethods[j].equals(varStr)) {
									tmpPar = formula.substring(indexs[i] + 1,indexs[i] + 2);
									String tmp = formula.substring(Integer.parseInt(varStrs[1]) + 2);
									// myLogger.log("+++++"+tmp.substring(0,tmp.indexOf("\"")));
									tmpPar = tmp.substring(0, tmp.indexOf("\""));
									if (!tID.equals(tmpPar)) {
										formulas.remove(s--);
										continue root;
									}
								}
							}
						} else {
							// do nothing
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		System.out.println("===============================�����ǵ�����˵Ĺ��˷�������==================================");
		return formulas;
	}

	/**
	 * ����op���ڲ��Ϊ��ǵ��ַ��������е�����λ�öԹ�ʽ�����з֣���ȡȫ���ı��������Ӵ�
	 * ע�⣺index�����д洢��op������λ�ñ�ʵ�ʹ�ʽ�е�op������λ�ÿ���һλ������
	 * 
	 * @param formula String
	 * @param indexs int[]
	 * @return List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List getAllVarAndConDeclareName(String formula, int[] indexs) {
		List varList = new ArrayList();
		int beginIndex;
		int endIndex;
		String tmpStr_1_2;
		for (int i = 0; i < indexs.length; i++) {
			if (indexs.length == 1) {
				beginIndex = 0;
				endIndex = indexs[i] - 1;
				tmpStr_1_2 = formula.substring(beginIndex, endIndex);
				if (StringUtils.killNull2(tmpStr_1_2) != null) {
					varList.add(tmpStr_1_2);
				}
				beginIndex = indexs[i];
				endIndex = formula.length();
				tmpStr_1_2 = formula.substring(beginIndex, endIndex);
				if (StringUtils.killNull2(tmpStr_1_2) != null) {
					varList.add(tmpStr_1_2);
				}
			} else {
				if (i == 0) {
					beginIndex = 0;
					endIndex = indexs[i] - 1;
					tmpStr_1_2 = formula.substring(beginIndex, endIndex);
					if (StringUtils.killNull2(tmpStr_1_2) != null) {
						varList.add(tmpStr_1_2);
					}
				} else if (i == indexs.length - 1) {
					beginIndex = indexs[i - 1];
					endIndex = indexs[i] - 1;
					tmpStr_1_2 = formula.substring(beginIndex, endIndex);
					if (StringUtils.killNull2(tmpStr_1_2) != null) {
						varList.add(tmpStr_1_2);
					}
					beginIndex = indexs[i];
					endIndex = formula.length();
					tmpStr_1_2 = formula.substring(beginIndex, endIndex);
					if (StringUtils.killNull2(tmpStr_1_2) != null) {
						varList.add(tmpStr_1_2);
					}
				} else {
					beginIndex = indexs[i - 1];
					endIndex = indexs[i] - 1;
					tmpStr_1_2 = formula.substring(beginIndex, endIndex);
					if (StringUtils.killNull2(tmpStr_1_2) != null) {
						varList.add(tmpStr_1_2);
					}
				}
			}
		}

		System.out.print("==>[��������]");
		for (int i = 0; i < varList.size(); i++) {
			System.out.print(varList.get(i) + "'");
		}
		return varList;
	}

	/**
	 * ����op���ڲ��Ϊ��ǵ��ַ��������е�����λ�öԹ�ʽ�����з֣���ȡȫ���ı��������Ӵ�
	 * ע�⣺index�����д洢��op������λ�ñ�ʵ�ʹ�ʽ�е�op������λ�ÿ���һλ������
	 * 
	 * @param formula
	 *            String
	 * @param indexs
	 *            int[]
	 * @return List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List getAllVarAndConDeclareNameindex(String formula,
			int[] indexs) {
		List varList = new ArrayList();
		int beginIndex;
		int endIndex;
		String tmpStr_1_2;
		for (int i = 0; i < indexs.length; i++) {
			if (indexs.length == 1) {
				beginIndex = 0;
				endIndex = indexs[i] - 1;
				tmpStr_1_2 = formula.substring(beginIndex, endIndex);
				if (StringUtils.killNull2(tmpStr_1_2) != null) {
					varList.add(tmpStr_1_2 + ":;" + endIndex);
				}
				beginIndex = indexs[i];
				endIndex = formula.length();
				tmpStr_1_2 = formula.substring(beginIndex, endIndex);
				if (StringUtils.killNull2(tmpStr_1_2) != null) {
					varList.add(tmpStr_1_2 + ":;" + endIndex);
				}
			} else {
				if (i == 0) {
					beginIndex = 0;
					endIndex = indexs[i] - 1;
					tmpStr_1_2 = formula.substring(beginIndex, endIndex);
					if (StringUtils.killNull2(tmpStr_1_2) != null) {
						varList.add(tmpStr_1_2 + ":;" + endIndex);
					}
				} else if (i == indexs.length - 1) {
					beginIndex = indexs[i - 1];
					endIndex = indexs[i] - 1;
					tmpStr_1_2 = formula.substring(beginIndex, endIndex);
					if (StringUtils.killNull2(tmpStr_1_2) != null) {
						varList.add(tmpStr_1_2 + ":;" + endIndex);
					}
					beginIndex = indexs[i];
					endIndex = formula.length();
					tmpStr_1_2 = formula.substring(beginIndex, endIndex);
					if (StringUtils.killNull2(tmpStr_1_2) != null) {
						varList.add(tmpStr_1_2 + ":;" + endIndex);
					}
				} else {
					beginIndex = indexs[i - 1];
					endIndex = indexs[i] - 1;
					tmpStr_1_2 = formula.substring(beginIndex, endIndex);
					if (StringUtils.killNull2(tmpStr_1_2) != null) {
						varList.add(tmpStr_1_2 + ":;" + endIndex);
					}
				}
			}
		}

		System.out.print("==>[��������]");
		for (int i = 0; i < varList.size(); i++) {
			System.out.print(varList.get(i) + "'");
		}

		return varList;
	}

	@SuppressWarnings("unused")
	private String getVarName(String var) {
		if (var.length() < 6) {
			return var;
		}
		if (BaseFormula.DATA_CONSTANT_KEY_ZSFS.equals(var)) {
			return "���շ�ʽ����(ZSFS)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_SSJJLX.equals(var)) {
			return "�����������ʹ���(SSJJLX)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_SSHY.equals(var)) {
			return "������ҵ����(JNSKFSDM)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_CKZD.equals(var)) {
			return "�ƻ��ƶȴ���(CKZD)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_GZGLXS.equals(var)) {
			return "���ʹ�����ʽ����(GZGLXS)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_JMLX.equals(var)) {
			return "�������ʹ���(JMLX)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_SYBS.equals(var)) {
			return "˰Դ��ʶ����(SYBS)";
		}else if (BaseFormula.DATA_CONSTANT_KEY_BBMSF.equals(var)) {
			return "�����(BBMSF)";
		} else {
			return var;
		}
	}

	public static void main(String[] args) {
		// String tid = "2";
		String formula = "T2I1=SUM(\"2\",3,6)";
		// formula = "T2I7<=T2I4+T2I5";
		// formula = "IF(EQUAL(\"CKZD\",\"02\"),(T2I8=SUM(\"2\",3,6)),true)";
		// formula = "T2I1=T2I2/(1-T2I3)*T2I3";
		// formula = "T2I1=MIN(T2I2,T2I3)";
		// formula = "T2I1==T2HJI2+T2HJI3";
		// formula = "T2I1==T2I2+T2I3";
		// formula = "IF(T2I8<=0,(T2I4=ABS(T2I5)),true)";
		// formula = "IF(T2I10-T2I11>0,(T2I102=T2I10-T2I11),T2I12=0)";
		//formula = "T2I11+T2I12";
		  formula = "TA107014I18=TA107014I16+TA107014I17";
	        //formula = "SUM(\"2\",2,24)-T2I25";
	        Map tmp = new HashMap();
	        Map tmpTable = new HashMap();
	        tmp.put("CKZD", "02");
	     
	        tmpTable.put("18.1", "0.00");
	        tmpTable.put("17.2", "0.00");
		tmp.put("A107014", tmpTable);
		BaseFormula bf = new BaseFormula(tmp, tmp);
		try {
			String[] methodNames = ReportsUtils
					.getClassMethodNames("com.lscdz.qysds.common.service.qysds.check.formula.BaseFormulaUtil");
			List list = bf.calculateExpression(formula, methodNames);
			 for (int i = 0; i < list.size(); i++) {
			 Result result = (Result) list.get(i);
			System.out.println(result.getResult());
			System.out.println(result.getVarMap());
			 }
			// get remark2
			// String r2 = "T2I6|��6;SUM(\"2\",1,5)|��1+��2+��3+��4+��5";
			// myLogger.log("Remark2: "+r2);
			// String[] rs = r2.split(";");
			// String[] fs = null;
			// String remark2 = "";
			// Result result = null;
			// for(int m=0,n=rs.length; m<n; m++){
			// myLogger.log("rs[m]:"+rs[m]);
			// fs = rs[m].split("\\|");
			// String tmpStr = fs[0];
			// myLogger.log("tmpStr:"+tmpStr);
			// if(fs[0].lastIndexOf("T")>0){
			// //�����������ϵ�T
			// }else if(fs[0].indexOf("T")==0){
			// //ֻ��һ��T
			// tmpStr = tmpStr + "+"+fs[0]+"-"+fs[0];
			// }
			// myLogger.log("tmp: "+tmpStr);
			// List remarkList = bf.calculateExpression(tmpStr,methodNames);
			// result = (Result) remarkList.get(0);
			// remark2 = remark2+
			// fs[1]+"="+((Double)result.getResult()).toString() +"  ";
			// }
			// myLogger.log("R2::"+remark2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 10.00==MIN(20.00,10.00)
		// //�����ǵ��Է���
		// List t = new ArrayList();
		// t.add("10.00==MIN(20.00,10.00)");
		// t.add("T3I1>=T3I5");
		// t.add("T4I1>=T4HJI7");
		// t.add("SUM('1',1,6)");
		//
		// �����ǵ��Է���2
		// try {
		// List t = ResourceAdaptor.getAllDebugFormulasByWangshang("001",
		// "20060101", null);
		// List filterList = BaseFormula.formulaSingleTableFilter(t, "1");
		// for (int i = 0; i < filterList.size(); i++) {
		// myLogger.log("[" + i + "]" + filterList.get(i));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// �����ǵ��Է���3
		// �����ǵ��Է���3 - guoxh,2006.12.07
		// List list = new ArrayList();
		// list.add("IF(EQUAL(\"CKZD\",\"16\"),(T21I17=SUM(\"21\",18,23)),true)");
		// list.add("IF(EQUAL(\"CKZD\",\"15\"),(T13I1=SUM(\"13\",2,15)),true)");
		// String id = "21";
		// try {
		// List l = BaseFormula.formulaSingleTableFilter(list, id);
		// myLogger.log("l.size():"+l.size());
		// for(int i=0,j=l.size(); i<j; i++){
		// myLogger.log(i+" - "+l.get(i));
		// }
		// } catch (FrameException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
