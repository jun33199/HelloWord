package com.lscdz.qysds.common.service.qysds.check.qysds;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.qysds.bo.CheckResult;
import com.lscdz.qysds.common.service.qysds.bo.config.Formula;
import com.lscdz.qysds.common.service.qysds.bo.qysds.Jbxx;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsItemDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsTableDeclare;
import com.lscdz.qysds.common.service.qysds.check.Checker;
import com.lscdz.qysds.common.service.qysds.check.formula.BaseFormula;
import com.lscdz.qysds.common.service.qysds.check.formula.ResourceAdaptor;
import com.lscdz.qysds.common.service.qysds.check.formula.Result;
import com.lscdz.qysds.common.service.qysds.db.DBAccess;
import com.lscdz.qysds.common.service.qysds.util.ReportsUtils;
import com.lscdz.qysds.common.service.qysds.util.StringUtils;

public class QysdsChecker extends Checker {

	/**
	 * 构造函数
	 * @param idba 数据库
	 */
	public QysdsChecker(DBAccess idba) {
		super(idba);
	}

	/**
	 * 纳税人税源标识
	 */
	public final static String CODE_QYSDS_SYBS_D = "1";
	public final static String CODE_NAME_QYSDS_SYBS_D = "地";

	public final static String CODE_QYSDS_SYBS_Z = "2";
	public final static String CODE_NAME_QYSDS_SYBS_Z = "总";

	public final static String CREPORTS_VERSION_QYSDS_2009 = "20090101";

	/**
	 * 主检查方法
	 * 
	 * @param contents 报表内容 com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param formulas  公式内容 com.syax.creports.bo.qysds.Formula
	 * @return 检查结果，结果为空或没有成员则表示检查通过 com.syax.creports.bo.CheckResult
	 * @exception FrameException 审核异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List checkMain(Object contents, List formulas) throws FrameException {

		List rtnList = new ArrayList();
		CheckResult cr;
		try {
			// -1.获取工具类的全部方法名
			// com.lscdz.qysds.common.service.qysds.check.formula
			String[] methodNames = ReportsUtils.getClassMethodNames("com.lscdz.qysds.common.service.qysds.check.formula.BaseFormulaUtil");
			// 0.转换类型
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) contents;
			// 1.获取全部公式
			// System.out.println("=================共" + formulas.size()
			// +"条审核公式===================");
			Formula v_formula;
			// 2.申报表数据转换
			Map dataMap = new HashMap();
			Jbxx jbxx = qrd.getJbxx();
			// /2.0.处理基础数据
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_CKZD, jbxx.getCkzd());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_ZSFS, jbxx.getZsfs());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_SSJJLX, jbxx.getSsjjlx());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_SSHY, jbxx.getSshy());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_GZGLXS, jbxx.getGzglxs());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_JMLX, jbxx.getJmlx());
//			String sybs = "";
			
			
//			/**
//			 * 添加税源标识 含有表17的时候对表17的分配比例合计进行必填验证 wangcy 2014-01-07
//			 */
//			if (jbxx.getBbmsf() != null  && Integer.valueOf(qrd.getSknd()).intValue() > 2012 && jbxx.getBbmsf().indexOf("17") > -1) {
//				System.out.println("用户填写报表Tid:" + jbxx.getBbmsf());
//				sybs = CODE_QYSDS_SYBS_Z;
//			} else {
//				sybs = CODE_QYSDS_SYBS_D;
//			}
			
			
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_SYBS, jbxx.getSybs());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_BBMSF, jbxx.getBbmsf());
			// /2.1.处理申报表数据
			Map tableMap = null;
			Iterator tableIDs = qrd.getTableContentList().keySet().iterator();
			Iterator itemIDs;
			String tmpTableID;
			String tmpItemID;
			QysdsReportsTableDeclare qrtd = null;
			QysdsReportsItemDeclare qrid = null;
			while (tableIDs.hasNext()) {
				tmpTableID = (String) tableIDs.next();
				qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().get(tmpTableID);
				// /2.0.处理表数据容器
				if (dataMap.get(tmpTableID) != null) {
					tableMap = (Map) dataMap.get(tmpTableID);
				} else {
					tableMap = new HashMap();
					dataMap.put(tmpTableID, tableMap);
				}
				// /2.1.处理项数据
				itemIDs = qrtd.getCellContentList().keySet().iterator();
				while (itemIDs.hasNext()) {
					tmpItemID = (String) itemIDs.next();
					qrid = (QysdsReportsItemDeclare) qrtd.getCellContentList().get(tmpItemID);
					tableMap.put(tmpItemID, qrid.getItemValue());
				}
			}

			// 3.进行审核
			BaseFormula bf = new BaseFormula(dataMap, this.idba);
			String formula;
			List formulaCalcList = null;
			Result result = null;
			// 纳税人系统级别
			int xtjb = StringUtils.getInt(jbxx.getXtjb(), 0);
			System.out.println("纳税人系统级别：" + xtjb + "-" + jbxx.getJsjdm());
			for (int a = 0; a < formulas.size(); a++) {
				// 公式
				v_formula = (Formula) formulas.get(a);
				if(v_formula.getFormulalevel().equals("999")){
					continue;
				}
				// add by Guoxh, 2007.01.18
				// 根据纳税人系统级别和公式级别，对公式进行过滤
//				System.out.println("公式级别：" + v_formula.getFormulalevel());
				// 判断公式级别是否大于等于纳税人系统级别。如果是则审核该公式，否则不审核
				if (StringUtils.getInt(v_formula.getFormulalevel(), 999) >= xtjb) {
					formula = v_formula.getFormulacontent();
					//System.out.println("[审核公式]" + formula);
					formulaCalcList = bf.calculateExpression(formula,methodNames);
					//System.out.println("[审核公式处理结果条数]" + formulaCalcList.size());
					for (int i = 0; i < formulaCalcList.size(); i++) {
						result = (Result) formulaCalcList.get(i);
						//System.out.println("==>[审核公式处理结果]" + result.getResult()+ "("+ result.getResult().getClass().toString()+ ")");
						if (!((Boolean) result.getResult()).booleanValue()) {
							cr = new CheckResult();
							cr.setResult(false);
							// modified by guoxh, 2007.1.5
							// cr.setResultDescription("检查公式[" + formula +
							// "], "+v_formula.getRemark1()
							// +hintRemark2(methodNames, bf,
							// v_formula.getRemark2(), result.getVarMap()));
							cr.setResultDescription(v_formula.getRemark1()+ hintRemark2(methodNames, bf,v_formula.getRemark2(),result.getVarMap()));
							cr.setCheckBy(formula);
							// cr.setResultDescription(formula);
							rtnList.add(cr);
						}
					}
				}// end if[公式过滤]
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtnList;

	}

	/**
	 * 公式提示信息
	 * 
	 * @param methodNames
	 * @param bf
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private String hintRemark2(String[] methodNames, BaseFormula bf, String r2,Map varMap) throws Exception {
		if (r2 == null || r2.equals("")) {
			return "";
		}
		Result result;
		// get remark2
		// String r2 = "T1I6|行6;SUM(\"1\",1,5)|行1+行2+行3+行4+行5";
		// System.out.println("Remark2: "+r2);
		// System.out.println("varMap: "+varMap);
		String[] rs = r2.split(";");
		String[] fs = null;
		String remark2 = "";
		// 格式化数字
		// modified by Guoxh, 2007.01.24
		// 精度保留4位,以便支持含有百分比的计算
		DecimalFormat df = new DecimalFormat("#.####");
		for (int m = 0, n = rs.length; m < n; m++) {
			fs = rs[m].split("\\|");
			String tmp = fs[0];
			int t = fs[0].lastIndexOf("T");
			if (t > 0) {
				// 存在两个以上的T或者T不在第一个位置上（存在其他运算符号:sum）
			} else if (t == 0) {
				// 只有一个T；需要封装成公式
				tmp = tmp + "+" + fs[0] + "-" + fs[0];
			} else {
				// 没有T
			}
			System.out.println("公式提示信息hintRemark2公式:" + tmp);

			List remarkList = bf.calculateExpression(tmp, methodNames);
			for (int i = 0; i < remarkList.size(); i++) {
				result = (Result) remarkList.get(i);
				// 梁龙武修改20070113
				System.out.println("==>[审核公式处理结果]" + result.getResult() + "("+ result.getResult().getClass().toString() + ")");

				if (!(result.getResult().getClass().isInstance(new Boolean(false)))) {

					// 只有和错误表达式匹配的Map才是当前行的提示信息
					if (result.getVarMap().equals(varMap)) {
						// remark2 = remark2+
						// fs[1]+"="+(Math.round(((Double)result.getResult()).doubleValue()*100)/100.0)
						// +"； ";
						remark2 = remark2+ fs[1]+ "="+ (df.format(((Double) result.getResult()).doubleValue())) + "； ";
					}
				}
				// 梁龙武修改20070113
			}
			// modified by guoxh, 20070112
			// remark2 = remark2+
			// fs[1]+"="+((Double)result.getResult()).toString() +"； ";
			// remark2 = remark2+
			// fs[1]+"="+(Math.round(((Double)result.getResult()).doubleValue()*100)/100.0)
			// +"； ";
		}
		return "   表中：" + remark2;
	}

	/**
	 * 主检查方法
	 * 
	 * @param contents 报表内容 com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param fsdm 方式代码 参见Constants.CREPORTS_SYSTEM_FS_WANGSHANG,Constants. CREPORTS_SYSTEM_FS_SHANGMENG
	 * @return 检查结果，结果为空或没有成员则表示检查通过
	 * @exception FrameException
	 *                审核异常
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public List checkMain(Object contents, String fsdm) throws FrameException {
		List rtnList = new ArrayList();
		Formula v_formula;
		List parList = new ArrayList();
		try {
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) contents;
			// 1.获取全部公式
			// 2.转换公式类型
			parList = ResourceAdaptor.getAllFormulas(fsdm, qrd.getAppid(),qrd.getVersion(), this.idba);
			// 3.调用方法
			rtnList = this.checkMain(contents, parList);

		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtnList;
	}

	/**
	 * 单表检查方法
	 * 
	 * @param contents 报表内容，成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param formulas 公式内容，成员为com.syax.creports.bo.config.Formula
	 * @return 检查结果，结果为空或没有成员则表示检查通过
	 * @exception FrameException 审核异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List checkSingeTable(Object contents, List formulas)throws FrameException {
		List rtnList = new ArrayList();
		Formula v_formula;
		List parList = new ArrayList();
		try {
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) contents;
			// 1.获取全部公式
			parList = formulas;
			// 2.
			// 3.过滤公式
			// /3.1.过滤公式
			List strList = new ArrayList();
			for (int i = 0; i < parList.size(); i++) {
				v_formula = (Formula) parList.get(i);
				strList.add(v_formula.getFormulacontent());
			}
			Iterator iteratr3 = qrd.getTableContentList().keySet().iterator();
			if (iteratr3.hasNext()) {
				strList = BaseFormula.formulaSingleTableFilter(strList,((QysdsReportsTableDeclare) (qrd.getTableContentList().get(iteratr3.next()))).getTableId());
				System.runFinalization();
			} else {
				throw new FrameException("调用参数错误,没有找到表对象!!!");
			}
			// /3.2.二次过滤公式
			for (int i = 0; i < parList.size(); i++) {
				v_formula = (Formula) parList.get(i);
				boolean flag = true;
				for (int j = 0; j < strList.size(); j++) {
					if (v_formula.getFormulacontent().equals(
							(String) strList.get(j))) {
						flag = false;
					}
				}
				if (flag) {
					parList.remove(i--);
				}
			}
			// 4.调用方法
			rtnList = this.checkMain(contents, parList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtnList;

	}

	/**
	 * 单表检查方法
	 * 
	 * @param contents 报表内容
	 * @param fsdm 方式代码 参见Constants.CREPORTS_SYSTEM_FS_WANGSHANG,Constants.CREPORTS_SYSTEM_FS_SHANGMENG
	 * @return 检查结果，结果为空或没有成员则表示检查通过
	 * @exception FrameException 审核异常
	 */
	@SuppressWarnings("rawtypes")
	public List checkSingeTable(Object contents, String fsdm)
			throws FrameException {
		List rtnList = new ArrayList();
		List parList = new ArrayList();
		try {
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) contents;
			// 1.获取全部公式
			// 2.转换公式类型
			parList = ResourceAdaptor.getAllFormulas(fsdm, qrd.getAppid(),qrd.getVersion(), this.idba);
			// 3.调用方法
			rtnList = this.checkSingeTable(contents, parList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtnList;
	}

}
