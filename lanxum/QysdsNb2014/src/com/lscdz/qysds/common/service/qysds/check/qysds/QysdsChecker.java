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
	 * ���캯��
	 * @param idba ���ݿ�
	 */
	public QysdsChecker(DBAccess idba) {
		super(idba);
	}

	/**
	 * ��˰��˰Դ��ʶ
	 */
	public final static String CODE_QYSDS_SYBS_D = "1";
	public final static String CODE_NAME_QYSDS_SYBS_D = "��";

	public final static String CODE_QYSDS_SYBS_Z = "2";
	public final static String CODE_NAME_QYSDS_SYBS_Z = "��";

	public final static String CREPORTS_VERSION_QYSDS_2009 = "20090101";

	/**
	 * ����鷽��
	 * 
	 * @param contents �������� com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param formulas  ��ʽ���� com.syax.creports.bo.qysds.Formula
	 * @return ����������Ϊ�ջ�û�г�Ա���ʾ���ͨ�� com.syax.creports.bo.CheckResult
	 * @exception FrameException ����쳣
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List checkMain(Object contents, List formulas) throws FrameException {

		List rtnList = new ArrayList();
		CheckResult cr;
		try {
			// -1.��ȡ�������ȫ��������
			// com.lscdz.qysds.common.service.qysds.check.formula
			String[] methodNames = ReportsUtils.getClassMethodNames("com.lscdz.qysds.common.service.qysds.check.formula.BaseFormulaUtil");
			// 0.ת������
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) contents;
			// 1.��ȡȫ����ʽ
			// System.out.println("=================��" + formulas.size()
			// +"����˹�ʽ===================");
			Formula v_formula;
			// 2.�걨������ת��
			Map dataMap = new HashMap();
			Jbxx jbxx = qrd.getJbxx();
			// /2.0.�����������
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_CKZD, jbxx.getCkzd());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_ZSFS, jbxx.getZsfs());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_SSJJLX, jbxx.getSsjjlx());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_SSHY, jbxx.getSshy());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_GZGLXS, jbxx.getGzglxs());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_JMLX, jbxx.getJmlx());
//			String sybs = "";
			
			
//			/**
//			 * ���˰Դ��ʶ ���б�17��ʱ��Ա�17�ķ�������ϼƽ��б�����֤ wangcy 2014-01-07
//			 */
//			if (jbxx.getBbmsf() != null  && Integer.valueOf(qrd.getSknd()).intValue() > 2012 && jbxx.getBbmsf().indexOf("17") > -1) {
//				System.out.println("�û���д����Tid:" + jbxx.getBbmsf());
//				sybs = CODE_QYSDS_SYBS_Z;
//			} else {
//				sybs = CODE_QYSDS_SYBS_D;
//			}
			
			
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_SYBS, jbxx.getSybs());
			dataMap.put(BaseFormula.DATA_CONSTANT_KEY_BBMSF, jbxx.getBbmsf());
			// /2.1.�����걨������
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
				// /2.0.�������������
				if (dataMap.get(tmpTableID) != null) {
					tableMap = (Map) dataMap.get(tmpTableID);
				} else {
					tableMap = new HashMap();
					dataMap.put(tmpTableID, tableMap);
				}
				// /2.1.����������
				itemIDs = qrtd.getCellContentList().keySet().iterator();
				while (itemIDs.hasNext()) {
					tmpItemID = (String) itemIDs.next();
					qrid = (QysdsReportsItemDeclare) qrtd.getCellContentList().get(tmpItemID);
					tableMap.put(tmpItemID, qrid.getItemValue());
				}
			}

			// 3.�������
			BaseFormula bf = new BaseFormula(dataMap, this.idba);
			String formula;
			List formulaCalcList = null;
			Result result = null;
			// ��˰��ϵͳ����
			int xtjb = StringUtils.getInt(jbxx.getXtjb(), 0);
			System.out.println("��˰��ϵͳ����" + xtjb + "-" + jbxx.getJsjdm());
			for (int a = 0; a < formulas.size(); a++) {
				// ��ʽ
				v_formula = (Formula) formulas.get(a);
				if(v_formula.getFormulalevel().equals("999")){
					continue;
				}
				// add by Guoxh, 2007.01.18
				// ������˰��ϵͳ����͹�ʽ���𣬶Թ�ʽ���й���
//				System.out.println("��ʽ����" + v_formula.getFormulalevel());
				// �жϹ�ʽ�����Ƿ���ڵ�����˰��ϵͳ�������������˸ù�ʽ���������
				if (StringUtils.getInt(v_formula.getFormulalevel(), 999) >= xtjb) {
					formula = v_formula.getFormulacontent();
					//System.out.println("[��˹�ʽ]" + formula);
					formulaCalcList = bf.calculateExpression(formula,methodNames);
					//System.out.println("[��˹�ʽ����������]" + formulaCalcList.size());
					for (int i = 0; i < formulaCalcList.size(); i++) {
						result = (Result) formulaCalcList.get(i);
						//System.out.println("==>[��˹�ʽ������]" + result.getResult()+ "("+ result.getResult().getClass().toString()+ ")");
						if (!((Boolean) result.getResult()).booleanValue()) {
							cr = new CheckResult();
							cr.setResult(false);
							// modified by guoxh, 2007.1.5
							// cr.setResultDescription("��鹫ʽ[" + formula +
							// "], "+v_formula.getRemark1()
							// +hintRemark2(methodNames, bf,
							// v_formula.getRemark2(), result.getVarMap()));
							cr.setResultDescription(v_formula.getRemark1()+ hintRemark2(methodNames, bf,v_formula.getRemark2(),result.getVarMap()));
							cr.setCheckBy(formula);
							// cr.setResultDescription(formula);
							rtnList.add(cr);
						}
					}
				}// end if[��ʽ����]
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtnList;

	}

	/**
	 * ��ʽ��ʾ��Ϣ
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
		// String r2 = "T1I6|��6;SUM(\"1\",1,5)|��1+��2+��3+��4+��5";
		// System.out.println("Remark2: "+r2);
		// System.out.println("varMap: "+varMap);
		String[] rs = r2.split(";");
		String[] fs = null;
		String remark2 = "";
		// ��ʽ������
		// modified by Guoxh, 2007.01.24
		// ���ȱ���4λ,�Ա�֧�ֺ��аٷֱȵļ���
		DecimalFormat df = new DecimalFormat("#.####");
		for (int m = 0, n = rs.length; m < n; m++) {
			fs = rs[m].split("\\|");
			String tmp = fs[0];
			int t = fs[0].lastIndexOf("T");
			if (t > 0) {
				// �����������ϵ�T����T���ڵ�һ��λ���ϣ����������������:sum��
			} else if (t == 0) {
				// ֻ��һ��T����Ҫ��װ�ɹ�ʽ
				tmp = tmp + "+" + fs[0] + "-" + fs[0];
			} else {
				// û��T
			}
			System.out.println("��ʽ��ʾ��ϢhintRemark2��ʽ:" + tmp);

			List remarkList = bf.calculateExpression(tmp, methodNames);
			for (int i = 0; i < remarkList.size(); i++) {
				result = (Result) remarkList.get(i);
				// �������޸�20070113
				System.out.println("==>[��˹�ʽ������]" + result.getResult() + "("+ result.getResult().getClass().toString() + ")");

				if (!(result.getResult().getClass().isInstance(new Boolean(false)))) {

					// ֻ�кʹ�����ʽƥ���Map���ǵ�ǰ�е���ʾ��Ϣ
					if (result.getVarMap().equals(varMap)) {
						// remark2 = remark2+
						// fs[1]+"="+(Math.round(((Double)result.getResult()).doubleValue()*100)/100.0)
						// +"�� ";
						remark2 = remark2+ fs[1]+ "="+ (df.format(((Double) result.getResult()).doubleValue())) + "�� ";
					}
				}
				// �������޸�20070113
			}
			// modified by guoxh, 20070112
			// remark2 = remark2+
			// fs[1]+"="+((Double)result.getResult()).toString() +"�� ";
			// remark2 = remark2+
			// fs[1]+"="+(Math.round(((Double)result.getResult()).doubleValue()*100)/100.0)
			// +"�� ";
		}
		return "   ���У�" + remark2;
	}

	/**
	 * ����鷽��
	 * 
	 * @param contents �������� com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param fsdm ��ʽ���� �μ�Constants.CREPORTS_SYSTEM_FS_WANGSHANG,Constants. CREPORTS_SYSTEM_FS_SHANGMENG
	 * @return ����������Ϊ�ջ�û�г�Ա���ʾ���ͨ��
	 * @exception FrameException
	 *                ����쳣
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public List checkMain(Object contents, String fsdm) throws FrameException {
		List rtnList = new ArrayList();
		Formula v_formula;
		List parList = new ArrayList();
		try {
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) contents;
			// 1.��ȡȫ����ʽ
			// 2.ת����ʽ����
			parList = ResourceAdaptor.getAllFormulas(fsdm, qrd.getAppid(),qrd.getVersion(), this.idba);
			// 3.���÷���
			rtnList = this.checkMain(contents, parList);

		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtnList;
	}

	/**
	 * �����鷽��
	 * 
	 * @param contents �������ݣ���ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param formulas ��ʽ���ݣ���ԱΪcom.syax.creports.bo.config.Formula
	 * @return ����������Ϊ�ջ�û�г�Ա���ʾ���ͨ��
	 * @exception FrameException ����쳣
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List checkSingeTable(Object contents, List formulas)throws FrameException {
		List rtnList = new ArrayList();
		Formula v_formula;
		List parList = new ArrayList();
		try {
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) contents;
			// 1.��ȡȫ����ʽ
			parList = formulas;
			// 2.
			// 3.���˹�ʽ
			// /3.1.���˹�ʽ
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
				throw new FrameException("���ò�������,û���ҵ������!!!");
			}
			// /3.2.���ι��˹�ʽ
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
			// 4.���÷���
			rtnList = this.checkMain(contents, parList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtnList;

	}

	/**
	 * �����鷽��
	 * 
	 * @param contents ��������
	 * @param fsdm ��ʽ���� �μ�Constants.CREPORTS_SYSTEM_FS_WANGSHANG,Constants.CREPORTS_SYSTEM_FS_SHANGMENG
	 * @return ����������Ϊ�ջ�û�г�Ա���ʾ���ͨ��
	 * @exception FrameException ����쳣
	 */
	@SuppressWarnings("rawtypes")
	public List checkSingeTable(Object contents, String fsdm)
			throws FrameException {
		List rtnList = new ArrayList();
		List parList = new ArrayList();
		try {
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) contents;
			// 1.��ȡȫ����ʽ
			// 2.ת����ʽ����
			parList = ResourceAdaptor.getAllFormulas(fsdm, qrd.getAppid(),qrd.getVersion(), this.idba);
			// 3.���÷���
			rtnList = this.checkSingeTable(contents, parList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtnList;
	}

}
