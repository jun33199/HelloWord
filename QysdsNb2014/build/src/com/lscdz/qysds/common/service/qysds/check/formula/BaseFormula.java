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
 * 自定义报表-基础公式类
 * 项目名称：QysdsNb2014   
 * 类名称：BaseFormula   
 * 类描述： BaseFormula 类描述： 包含公式容器、解析器和相关工具的基础申明  
 * 创建人：wangcy 
 * 创建时间：2014-12-1 下午1:10:59   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 下午1:10:59   
 * 修改备注：   
 * @version  1.0
 */
public class BaseFormula {

	MyLogger myLogger = new MyLogger(BaseFormula.class);

	/**
	 * 公式表达式有可能出现的函数数组
	 */
	public final static String[] formulaOps = { " ", "+", "-", "*", "/", "|",
			"&", "(", ")", ",", "!", "=", ">", "<", "\"", "'" };
	/**
	 * 公式表达式有可能出现的函数(与表引用相关)数组
	 */
	public final static String[] formulaMethods = { "EVERY", "ABSEVERY", "SUM" };

	/**
	 * 公式表达式有可能出现的函数
	 */
	public final static String formulaOp = " +-*/|&(),!=><\"";

	/**
	 * 审核用申报表数据容器基础信息减值-征收方式
	 */
	public static String DATA_CONSTANT_KEY_ZSFS = "ZSFS";

	/**
	 * 审核用申报表数据容器基础信息减值-所属经济类型
	 */
	public static String DATA_CONSTANT_KEY_SSJJLX = "SSJJLX";

	/**
	 * 审核用申报表数据容器基础信息减值-所属行业
	 */
	public static String DATA_CONSTANT_KEY_SSHY = "SSHY";

	/**
	 * 审核用申报表数据容器基础信息减值-财会制度
	 */
	public static String DATA_CONSTANT_KEY_CKZD = "CKZD";

	/**
	 * 审核用申报表数据容器基础信息减值-工资管理形式
	 */
	public static String DATA_CONSTANT_KEY_GZGLXS = "GZGLXS";

	/**
	 * 审核用申报表数据容器基础信息减值-减免类型
	 */
	public static String DATA_CONSTANT_KEY_JMLX = "JMLX";

	/**
	 * 审核用申报表数据容器基础信息减值-税源标识
	 */
	public static String DATA_CONSTANT_KEY_SYBS = "SYBS";
	
	/**
	 * 用户填报的报表
	 */
	public static String DATA_CONSTANT_KEY_BBMSF = "BBMSF";

	/**
	 * 当前公式表达式
	 */
	@SuppressWarnings("unused")
	private String theExpression = "";

	/**
	 * 数据操作对象
	 */
	private DBAccess dba = null;

	/**
	 * 当前正在使用的Sql子句，共调试使用。
	 */
	protected String sqlStr = "";

	/**
	 * 错误消息，供调试使用。
	 */
	public String messages = "";

	/**
	 * 数据容器对象
	 */
	@SuppressWarnings("rawtypes")
	private Map dataMap = new HashMap();

	/**
	 * 代码表map分为3层map 第一层为代码表层 一般以代码表 表名称为KEY 第二层为代码表行层 以代码表的代码为key 第三层为代码表列层
	 * 以各个列名为key
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map codeMap = new HashMap();

	/**
	 * 内容字段哈西表
	 */
	@SuppressWarnings("rawtypes")
	public Map formulaList = new HashMap();

	/**
	 * 所使用的参数hashmap
	 */
	@SuppressWarnings("rawtypes")
	public static Map tempVerMap = new HashMap();

	/**
	 * 构造方法
	 * 
	 * @param adb
	 *            申报表数据
	 * @param dba
	 *            数据连接对象
	 * @roseuid 3E813443029B
	 */
	@SuppressWarnings("rawtypes")
	public BaseFormula(Map adb, DBAccess dba) {
		this.dataMap = adb;
		this.dba = dba;
		if (ResourceManager.isDebug()) {
			myLogger.log("----------------------------以下是纳税人基础信息----------------------------");
			myLogger.log("CKZD="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_CKZD));
			myLogger.log("ZSFS="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_ZSFS));
			myLogger.log("SSJJLX="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SSJJLX));
			myLogger.log("SSHY="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SSHY));
			myLogger.log("GZGLXS="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_GZGLXS));
			myLogger.log("JMLX="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_JMLX));
			myLogger.log("SYBS="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SYBS));
			myLogger.log("BBMSF="+ dataMap.get(BaseFormula.DATA_CONSTANT_KEY_BBMSF));
			myLogger.log("----------------------------以上是纳税人基础信息----------------------------");
		}
		init();
	}

	@SuppressWarnings("rawtypes")
	private void init() {
		this.codeMap = new HashMap();
	}

	/**
	 * 构造方法，生成公式对象,供仅仅计算。
	 * 
	 * @param map
	 *            申报表数据对象
	 * @param codeMap
	 *            代码表数据对象
	 */
	@SuppressWarnings("rawtypes")
	public BaseFormula(Map map, Map codeMap) {
		this.dataMap = map;
		this.codeMap = codeMap;
	}

	/**
	 * 计算指定表达式的值。
	 * 
	 * @param expression
	 *            待计算的表达式
	 * @return 表达式计算值
	 * @throws Exception
	 *             计算失败
	 * @roseuid 3E81344303C7
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List calculateExpression(String expression, String[] methodNames)
			throws Exception {
		List rtnList = new ArrayList();
		Result returnResult = new Result();
		// 替换后的公式表达式
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
			// tempVerMap = new HashMap(); //取错误的表达式中代入的变量名和变量值
			// for (int i = 0; i < formatExpression.size(); i++) {
			// loger.write("==>[formatExpression" + i + "]=" +
			// (String) formatExpression.get(i),
			// ILog.LOG_LEVEL_DEBUG_LOW);
			// expr_c = Evaluator.compile((String) formatExpression.get(i),
			// lib);
			// Object result = null;
			// if (expr_c != null) {
			// result = expr_c.evaluate(null);
			// myLogger.log("调用第三方包产生的结果[" + result + "]");
			// }
			// returnResult = new Result();
			// returnResult.setResult(result);
			// returnResult.setVarMap(tempVerMap);
			// rtnList.add(returnResult);
			// }
			// add by guoxh, 20070112
			Map formatMap = this.getFormatFormula(expression, methodNames);
			
			new BaseFormulaUtil(this.dataMap, this.dba);
			tempVerMap = new HashMap(); // 取错误的表达式中代入的变量名和变量值
			Iterator fi = formatMap.keySet().iterator();
			String key = "";
			while (fi.hasNext()) {
				key = (String) fi.next();
				//myLogger.log("==>[formatExpression" + key + "]="+ (String) formatMap.get(key));
				// 记录当前行信息
				Map m = new HashMap();
				m.put(key, key);
				// 公式表达式
				// String rtnFomula = (String) formatMap.get(key);
				// rtnFomula = ReportsUtils.replaceEqualOp(rtnFomula);
				rtnFomula = ReportsUtils.replaceEqualOp((String) formatMap.get(key));
				// myLogger.log("rtnFomula: "+rtnFomula);
				// 计算表达式
				expr_c = Evaluator.compile(rtnFomula, lib);
				Object result = null;
				if (expr_c != null) {
					result = expr_c.evaluate(null);
					// myLogger.log("调用第三方包产生的结果[" + result + "]");
				}
				returnResult = new Result();
				returnResult.setResult(result);
				returnResult.setVarMap(m);// 存放当前表达式的行信息
				// myLogger.log("setVarMap:"+m);
				rtnList.add(returnResult);
			}

		} catch (FrameException e) {
			myLogger.log("计算表达式时出错！");
			myLogger.log("公式：" + expression + "；替换后：" + rtnFomula);
			e.printStackTrace();
			// throw e;
			returnResult = new Result();
			returnResult.setResult(new Boolean(false));
			returnResult.setVarMap(tempVerMap);
			rtnList.add(returnResult);
		} catch (java.lang.ArithmeticException e) {
			myLogger.log("计算表达式时出错！");
			myLogger.log("公式：" + expression + "；替换后：" + rtnFomula);
			e.printStackTrace();
			returnResult = new Result();
			returnResult.setResult(new Boolean(false));
			returnResult.setVarMap(tempVerMap);
			rtnList.add(returnResult);
		} catch (Exception e) {
			myLogger.log("计算表达式时出错！");
			myLogger.log("公式：" + expression + "；替换后：" + rtnFomula);
			e.printStackTrace();
			// throw e;
			returnResult = new Result();
			returnResult.setResult(new Boolean(false));
			returnResult.setVarMap(tempVerMap);
			rtnList.add(returnResult);
		} catch (Throwable t) {
			myLogger.log("计算表达式时出错！");
			myLogger.log("公式：" + expression + "；替换后：" + rtnFomula);
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
	 * 计算指定表达式的值。
	 * 
	 * @param expression  待计算的表达式
	 * @return 表达式计算值
	 * @throws FrameException  计算失败
	 * @roseuid 3E81344303C7
	 */
	@SuppressWarnings("rawtypes")
	public static String samplecalculateExpression(String expression)throws FrameException {
		try {
			// 以下是例子程序的模仿
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
			System.out.println("计算表达式时出错！");
			e.printStackTrace();
			throw e;
		} catch (java.lang.ArithmeticException e) {
			return "false";
		} catch (Exception e) {
			System.out.println("计算表达式时出错！");
			e.printStackTrace();
			throw new FrameException("计算表达式时出错！");
		} catch (Throwable t) {
			return "false";
		} // end try catch
	}

	/**
	 * 将公式表达式中的预定义变量进行值替换，例如"ZSFS"、T1I5、T1HJI4等替换为申报表中的数据。 如果申报表没有数据则将替换为0
	 * 
	 * @param formula String 公式表达式
	 * @return ArrayList 替换后的公式表达式（不含预定义的变量）集合
	 * @throws Exception 系统异常
	 * @todo 尚未将参与运算的变量加入返回值,需要完成.this.tempVerMap
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public Map getFormatFormula(String formula, String[] methodNames) throws FrameException {
		// 句柄申明
		List rtnList = new ArrayList();
		List varList = new ArrayList();
		Map formulaMap = new HashMap();
		int[] indexs = null;
		// 业务过程
		try {
			// 0.整理当前公式为半角字符数组
			String[] fs = formula.split(""); // 整理成单字符数组的公式表达式,注意:此处fs的第一个元素为"",formula的第一个字符应该从fs[1]开始
			// 1.根据操作符定义拆分当前公式为子串
			// /1.0.获得所有操作符坐标
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
			// /1.1.进行坐标排序和去除冗余坐标
			Iterator iterator = map_op_index.keySet().iterator();
			indexs = new int[map_op_index.size()];
			String tmpStr;
			int tmpIndex1_1 = 0;
			while (iterator.hasNext()) {
				tmpStr = (String) iterator.next();
				indexs[tmpIndex1_1] = Integer.parseInt(tmpStr);
				tmpIndex1_1++;
			}
			indexs = ReportsUtils.bubbleSort(indexs, true); // 获取排序完毕以后的index数组
			// /1.2.获取所有子串并放置入临时容器
			String tmpStr_1_2;
			varList = BaseFormula.getAllVarAndConDeclareName(formula, indexs); // 获取非操作符子串
			// //过滤所有未引用子串
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
								methodNames)) { // 如果为预定义变量或报表项引用则作为变量进行保存
					varList.remove(i--);
				}
			}

			// 2.检查所有子串是否为预定义变量
			String tmpStr_2 = null;
			// System.out.println("varList.size()=" + varList.size());
			// /2.0.如果为基础信息预定义变量则进行替换基础信息
			System.out.println("-----------------------处理基础信息常量--------------------------");

			for (int i = 0; i < varList.size(); i++) {
				tmpStr_2 = (String) varList.get(i);
				//System.out.println("公式变量=" + tmpStr_2);
				String tmpStr_2_0 = null;
				if (BaseFormula.DATA_CONSTANT_KEY_ZSFS.equals(tmpStr_2)) {
					if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_ZSFS) != null) {
						tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_ZSFS);
						// formula = StringUtils.replaceAll(formula, tmpStr_2,
						// tmpStr_2_0);
						// Modified by Guoxh, 2006.12.20
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
					} else {
						throw new FrameException("公式解析预格式化错误（征收方式无法获取）!!!["+ formula + "]");
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
						throw new FrameException("公式解析预格式化错误（所属经济类型无法获取）!!!["+ formula + "]");
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
						throw new FrameException("公式解析预格式化错误（所属行业无法获取）!!!["+ formula + "]");
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
						throw new FrameException("公式解析预格式化错误（财会制度无法获取）!!!["+ formula + "]");
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
						throw new FrameException("公式解析预格式化错误（工资管理形式无法获取）!!!["+ formula + "]");
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
						throw new FrameException("公式解析预格式化错误（减免形式无法获取）!!!["+ formula + "]");
					}
					varList.remove(i--);
				} else if (BaseFormula.DATA_CONSTANT_KEY_SYBS.equals(tmpStr_2)) {
					/**
					 * 添加税源标识 含有表17的时候对表17的分配比例合计进行必填验证 wangcy 2014-01-07
					 */
					if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SYBS) != null) {
						tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_SYBS);
						// formula = StringUtils.replaceAll(formula, tmpStr_2,
						// tmpStr_2_0);
						// Modified by Guoxh, 2006.12.20
						formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
					} else {
						throw new FrameException("公式解析预格式化错误（减免形式无法获取）!!!["+ formula + "]");
					}
					varList.remove(i--);
				}else if (BaseFormula.DATA_CONSTANT_KEY_BBMSF.equals(tmpStr_2)) {
						/**
						 * 添加税源标识 含有表17的时候对表17的分配比例合计进行必填验证 wangcy 2014-01-07
						 */
						if (this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_BBMSF) != null) {
							tmpStr_2_0 = (String) this.dataMap.get(BaseFormula.DATA_CONSTANT_KEY_BBMSF);
							// formula = StringUtils.replaceAll(formula, tmpStr_2,
							// tmpStr_2_0);
							// Modified by Guoxh, 2006.12.20
							formula = ReportsUtils.replaceQuoteVarInFormula(formula, tmpStr_2, tmpStr_2_0);
						} else {
							throw new FrameException("公式解析预格式化错误!!!["+ formula + "]");
						}
						varList.remove(i--);
					}
			}
			// 获取所有引用变量到参数List(parVarList)
			// for(int i=0;i<varList.size();i++){
			// parVarList.add(new String(((String)varList.get(i)).getBytes()));
			// }
			// 如果不再存在变量则直接将公式加入返回列表
			if (varList.size() == 0) {
				rtnList.add(formula);
			}
			// /2.1.如果为变长项合计定义则获取所有合计进行域值替换(TmHJIn)
			System.out.println("-----------------------如果为变长项合计定义则获取所有合计进行域值替换(TmHJIn)--------------------------");
			for (int i = 0; i < varList.size(); i++) {
				tmpStr_2 = (String) varList.get(i);
				//System.out.println("公式变量=" + tmpStr_2);
				if (tmpStr_2.indexOf("T") != -1 && tmpStr_2.indexOf("HJI") != -1) {
					String[] ti = ReportsUtils.parseQuoteTmHJIn(tmpStr_2);
					if (this.dataMap.get(ti[0]) != null) {
						Map tMap = (Map) this.dataMap.get(ti[0]);
						String itemID = ti[1];
						// 轮寻获取ItemID，并获取对应的value
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
						// 将合计数替换入公式
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
						// 精度保留4位,以便支持百分比
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
			// 如果不再存在变量则直接将公式加入返回列表
			if (varList.size() == 0) {
				rtnList.add(formula);
			}
			// /2.2.如果为所有项定义则获取所有合计进行域值替换(TmIn),处理不含子项的变量
			System.out.println("-----------------------如果为所有项定义则获取所有合计进行域值替换(TmIn),处理不含子项的变量--------------------------");
			root22: 
			for (int i = 0; i < varList.size(); i++) {
				tmpStr_2 = (String) varList.get(i); // T4I1
				//System.out.println("公式变量=" + tmpStr_2);

				if (tmpStr_2.indexOf("T") != -1&& tmpStr_2.indexOf("HJI") == -1) {
					String[] ti = ReportsUtils.parseQuoteTmIn(tmpStr_2);
					if (this.dataMap.get(ti[0]) != null) {
						Map tMap = (Map) this.dataMap.get(ti[0]);
						String itemID = ti[1];
						// 轮寻获取ItemID，并获取对应的value
						Iterator iterator_2_1 = tMap.keySet().iterator();
						String itemVlaue;
						String itemKey;
						boolean flag22 = true;
						while (iterator_2_1.hasNext()) {
							itemKey = (String) iterator_2_1.next();
							if (itemID.equals(ReportsUtils.getRootItemID(itemKey))) { // ??处理增位时产生的问题//轮寻所有item的key检查是否由itemID开头，如果是则计入合计
								itemVlaue = (String) (tMap.get(itemKey));
								flag22 = false;
								if (ReportsUtils.splitItemID(itemKey) == null) { // 判读是否是子项,是子项的话进行处理
								// System.out.println("替换前公式:" + formula);
									// formula =
									// StringUtils.replaceAll(formula,tmpStr_2,Arith.round(itemVlaue,
									// 2) + "");
									// Modified by Guoxh, 2006.12.25
									// formula = ReportsUtils.
									// replaceQuoteVarInFormula(formula,
									// tmpStr_2,
									// Arith.roundStr(itemVlaue, 2) + "");
									// 对itemVlaue进行是否为空的判断(Modified by Guoxh,
									// 2006.12.25)
									// formula =
									// ReportsUtils.replaceQuoteVarInFormula(formula,
									// tmpStr_2,
									// arithRound(itemVlaue, 2));
									// modified by Guoxh, 2007.01.24
									// 精度保留4位,以便支持百分比
									formula = ReportsUtils.replaceQuoteVarInFormula(formula,tmpStr_2,arithRound(itemVlaue, 4));
									System.out.println("tmpStr_2=" + tmpStr_2
											+ ",itemKey=" + itemKey
											+ ",itemVlaue=" + itemVlaue
											+ ",Arith.round(itemVlaue, 2)="
											+ this.arithRound(itemVlaue, 2));
									System.out.println("替换后公式:" + formula);
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
					throw new FrameException("公式解析预格式化错误!!![" + formula + "]");
				}

			}
			// 如果不再存在变量则直接将公式加入返回列表
			if (varList.size() == 0) {
				rtnList.add(formula);
			}
			// /2.3.如果为所有项定义则获取所有合计进行域值替换(TmIn),处理含子项的变量
			// System.out.println("-----------------------如果为所有项定义则获取所有合计进行域值替换(TmIn),处理含子项的变量--------------------------");
			// //如果存在多个行次的子项引用,则需要反复复制出需要替换的公式进行处理
			String tmpFormula;
			formulaMap.put("1", formula);
			String baseFormula = formula;
			String[] itemKeys = null;
			root23: for (int i = 0; i < varList.size(); i++) {
				tmpStr_2 = (String) varList.get(i); // 获取当前变量
				//System.out.println("公式变量=" + tmpStr_2);
				if (tmpStr_2.indexOf("T") != -1 && tmpStr_2.indexOf("HJI") == -1) {
					String[] ti = ReportsUtils.parseQuoteTmIn(tmpStr_2);
					if (this.dataMap.get(ti[0]) != null) {
						Map tMap = (Map) this.dataMap.get(ti[0]);// 获取表数据容器
						String itemID = ti[1];
						// 轮寻获取ItemID，并获取对应的value
						Iterator iterator_2_1 = tMap.keySet().iterator();
						String itemVlaue;
						String itemKey;
						boolean flag23 = true;
						while (iterator_2_1.hasNext()) {// 轮寻所有Item查找符合条件的item
							itemKey = (String) iterator_2_1.next();
							if (itemID.equals(ReportsUtils.getRootItemID(itemKey))) { // 轮寻所有item的key检查是否由itemID开头，如果是则进行处理
								itemVlaue = (String) (tMap.get(itemKey));
								flag23 = false;
								itemKeys = ReportsUtils.splitItemID(itemKey);// 获取拆分后的子串
								if (itemKeys != null) {// 判读是否是子项,是的话进行公式复制并处理
									// 根据子行数获取对应的行数公式，如果没有则复制并置入容器
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
									// 精度保留4位,以便支持百分比
									tmpFormula = ReportsUtils.replaceQuoteVarInFormula(tmpFormula, tmpStr_2,arithRound(itemVlaue, 4)+ "");
									formulaMap.put(itemKeys[1], tmpFormula);// 替换完毕以后将公式置入对应的子行数公式容器
									System.out.println("tmpStr_2=" + tmpStr_2
											+ ",itemKey=" + itemKey
											+ ",itemVlaue=" + itemVlaue
											+ ",Arith.round(itemVlaue, 2)="
											+ arithRound(itemVlaue, 2));
									System.out.println("（处理含子项非合计引用）替换后公式:"+ tmpFormula);

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
					throw new FrameException("公式解析预格式化错误!!![" + formula + "]");
				}
				varList.remove(i--);
				// 处理返回值列表

			}
		} catch (Exception ex) {
			throw new FrameException(ex.getMessage());
			// ex.printStackTrace();
		} // end try catch
			// add by Guoxh, 2006.01.16
			// 循环结果大于1的Map，以便把没有替换的变量用'0.00'进行替换
		if (formulaMap.size() > 1) {
			replaceVariables(formulaMap);
		}
		// 返回Map
		return formulaMap;

		// 处理返回值列表
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
		// //返回值
		// return rtnFinalList;

	}

	/**
	 * 循环所有表达式，以便把没有替换的变量用'0.00'进行替换 变量类似：T2I1;T13I78
	 * 
	 * @param formulaMap
	 *            需要进行处理的表达式集合
	 * @author guoxh, 2007.01.16
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void replaceVariables(Map formulaMap) {
		// 表达形式
		Pattern pattern = Pattern.compile("TA[0-9]{1,}I[0-9]{1,}");
		Matcher hasTI = null;
		// 公式表达式
		String rtnFomula = null;
		// 循环所有数据
		Iterator fIte = formulaMap.keySet().iterator();
		String key = "";
		while (fIte.hasNext()) {
			key = fIte.next().toString();
			rtnFomula = (String) formulaMap.get(key);
			// myLogger.log("rtnFomula = "+rtnFomula);
			hasTI = pattern.matcher(rtnFomula);
			// 公式表达式如果含有变量，则用'0.00'替换
			if (hasTI.find(0)) {
				rtnFomula = hasTI.replaceAll("0.00");
				myLogger.log("hasTI: " + rtnFomula);
				formulaMap.put(key, rtnFomula);
			}
		}
	}

	/**
	 * 如果输入值为空，则返回“0.00”
	 * 
	 * @param itemVlaue
	 * @param scale
	 * @return String "0.00(itemVlaue为空)"或者"Arith.roundStr(itemVlaue, scale)"
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
	 * 单表公式过滤器,按照给出的申报表ID将存在其它申报表引用的公式过滤掉
	 * 
	 * @param formulas
	 *            List 全部公式
	 * @param tID
	 *            String 申报表表ID
	 * @return List 过滤后的单表公式列表
	 * @throws FrameException
	 *             系统异常
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static List formulaSingleTableFilter(List formulas, String tID)throws FrameException {
		System.out.println("===============================以下是单表审核的过滤方法开始==================================");
		try {
			String[] methodNames = ReportsUtils.getClassMethodNames("com.lscdz.qysds.common.service.qysds.check.formula.formula.BaseFormulaUtil");
			String formula;
			root: // 循环根节点
			for (int s = 0; s < formulas.size(); s++) {
				System.out.println("class=" + formulas.get(s).getClass());
				formula = (String) formulas.get(s);
				System.out.println("[" + s + "]" + formula);
				// 0.整理当前公式为半角字符数组
				String[] fs = formula.split(""); // 整理成单字符数组的公式表达式,注意:此处fs的第一个元素为"",formula的第一个字符应该从fs[1]开始
				System.out.print("==>");
				for (int i = 0; i < fs.length; i++) {
					System.out.print(fs[i] + "'");
				}

				// 1.根据操作符定义拆分当前公式为子串
				// /1.0.获得所有操作符坐标
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
				// /1.1.进行坐标排序和去除冗余坐标
				Iterator iterator = map_op_index.keySet().iterator();
				int[] indexs = new int[map_op_index.size()];
				String tmpStr;
				int tmpIndex1_1 = 0;
				while (iterator.hasNext()) {
					tmpStr = (String) iterator.next();
					indexs[tmpIndex1_1] = Integer.parseInt(tmpStr);
					tmpIndex1_1++;
				}
				indexs = ReportsUtils.bubbleSort(indexs, true); // 获取排序完毕以后的index数组
				System.out.print("==>");
				for (int i = 0; i < indexs.length; i++) {
					System.out.print(indexs[i] + "'");
				}
				// /1.3.获取所有子串
				List varList = BaseFormula.getAllVarAndConDeclareNameindex(
						formula, indexs);
				// /1.3.对所有子串并进行处理
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
								.equals(varStr))) { // 如果不是预定义变量则进行处理
						if (varStr.indexOf("T") != -1 && !ReportsUtils.checkVarNameIsMethodNames(varStr, methodNames)) { // 如果是申报表引用则进行处理
							/**
							 * @todo 如果是申报表引用则进行处理
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

						} else if (ReportsUtils.checkVarNameIsMethodNames(varStr, methodNames)) { // 如果是函数引用则进行处理
							/**
							 * @todo 如果是函数引用则进行处理
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
		System.out.println("===============================以下是单表审核的过滤方法结束==================================");
		return formulas;
	}

	/**
	 * 根据op的在拆分为半角单字符的数组中的索引位置对公式进行切分，获取全部的变量或常量子串
	 * 注意：index数组中存储的op的索引位置比实际公式中的op的索引位置靠后一位！！！
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

		System.out.print("==>[变量定义]");
		for (int i = 0; i < varList.size(); i++) {
			System.out.print(varList.get(i) + "'");
		}
		return varList;
	}

	/**
	 * 根据op的在拆分为半角单字符的数组中的索引位置对公式进行切分，获取全部的变量或常量子串
	 * 注意：index数组中存储的op的索引位置比实际公式中的op的索引位置靠后一位！！！
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

		System.out.print("==>[变量定义]");
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
			return "征收方式代码(ZSFS)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_SSJJLX.equals(var)) {
			return "所属经济类型代码(SSJJLX)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_SSHY.equals(var)) {
			return "所属行业代码(JNSKFSDM)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_CKZD.equals(var)) {
			return "财会制度代码(CKZD)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_GZGLXS.equals(var)) {
			return "工资管理形式代码(GZGLXS)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_JMLX.equals(var)) {
			return "减免类型代码(JMLX)";
		} else if (BaseFormula.DATA_CONSTANT_KEY_SYBS.equals(var)) {
			return "税源标识代码(SYBS)";
		}else if (BaseFormula.DATA_CONSTANT_KEY_BBMSF.equals(var)) {
			return "填报报表(BBMSF)";
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
			// String r2 = "T2I6|行6;SUM(\"2\",1,5)|行1+行2+行3+行4+行5";
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
			// //存在两个以上的T
			// }else if(fs[0].indexOf("T")==0){
			// //只有一个T
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
		// //以下是调试方法
		// List t = new ArrayList();
		// t.add("10.00==MIN(20.00,10.00)");
		// t.add("T3I1>=T3I5");
		// t.add("T4I1>=T4HJI7");
		// t.add("SUM('1',1,6)");
		//
		// 以下是调试方法2
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
		// 以下是调试方法3
		// 以下是调试方法3 - guoxh,2006.12.07
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
