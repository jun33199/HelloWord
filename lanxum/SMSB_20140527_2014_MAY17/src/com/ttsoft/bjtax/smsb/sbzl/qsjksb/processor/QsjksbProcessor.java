/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.db.v_kj_qsmx;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.sfgl.common.Constant;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.Cftsyhd;
import com.ttsoft.bjtax.sfgl.common.model.Grtszygsde;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.sfgl.model.orobj.Tszslmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.shenbao.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.SbjkmxDis;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.InterfaceSf4Sb;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.sbzl.qsjksb.web.QsjksbForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbGzsxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ApplicationException;

import java.sql.*;
import java.io.*;
import oracle.jdbc.driver.*;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description: ʵ��Ƿ˰�ɿ��걨���ܣ������ɿ���¼�룬ά����
 * </p>
 * 
 * @author Zhang Yijun
 * @version 1.1
 */

public class QsjksbProcessor implements Processor {

	public QsjksbProcessor() {
	}

	/**
	 * ʵ��Processor�ӿ�
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException
	 *             ҵ���쳣
	 */

	public Object process(VOPackage vo)
			throws com.ttsoft.framework.exception.BaseException {
		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			return doShow(vo);
		case CodeConstant.SMSB_QUERYACTION:
			return doQuery(vo);
		case CodeConstant.SMSB_SAVEACTION:
			return doSave(vo);

		default:
			return null;
		}

	}

	private Object doShow(VOPackage vo) throws BaseException {

		// ��ȡform����
		QsjksbForm form = (QsjksbForm) vo.getData();

		// �������ݿ�����
		Connection conn = null;
		ResultSet rs = null;

		// Ƿ˰�걨������Ȩ�� Ĭ��ֵ0Ϊ�Զ�����������ɽ𲻿��޸� 1Ϊ�޹̶���ȿ��޸����ɽ���
		String xgqx = "0";
		try {
			// ������ݿ�����
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);

			String sql = "select * from dmdb.sf_jl_qsglkzpz t where t.dyz='"
					+ form.getYhdm() + "' and t.pzlx='03' and t.sfyx='1'";
			rs = da.querySQL(sql);
			while (rs.next()) {
				xgqx = "1";
			}
			form.setXgqx(xgqx);
			return form;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	private Object doQuery(VOPackage vo) throws BaseException {

		// ��ȡform����
		QsjksbForm form = (QsjksbForm) vo.getData();
		// ���UserData
		UserData ud = vo.getUserData();
		String yhid = ud.yhid;
		String ssdwdm = ud.ssdwdm;
		// ����ҵ���־ 1 �걨 2 ��˰ 3 ��ѯ 9ȫ���� ���Ϊ�գ�Ĭ��1
		String clywbz = "1";
		// ���ɽ��������
		String znjjsrq = "";
		// Ƿ˰���ϼ�
		double qshj = 0;
		ResultSet rs = null;
		// �������ݿ�����
		Connection conn = null;
		if (form.getXgqx() == null) {

			// Ƿ˰�걨������Ȩ�� Ĭ��ֵ0Ϊ�Զ�����������ɽ𲻿��޸� 1Ϊ�޹̶���ȿ��޸����ɽ���
			String xgqx = "0";
			try {
				// ������ݿ�����
				conn = SfDBResource.getConnection();
				SfDBAccess da = new SfDBAccess(conn);

				String sql = "select * from dmdb.sf_jl_qsglkzpz t where t.dyz='"
						+ form.getYhdm() + "' and t.pzlx='03' and t.sfyx='1'";
				rs = da.querySQL(sql);
				while (rs.next()) {
					System.out.println("RS1    next");
					xgqx = "1";
				}
				form.setXgqx(xgqx);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			} finally {
				SfDBResource.freeConnection(conn);
			}
		}
		// ��ȡ�ƻ�ӿڻ�ȡǷ˰����
		ArrayList datalist = new ArrayList();
		Map map = this.getSzmc();
		Timestamp now = new Timestamp((new java.util.Date()).getTime());

		String qsycz = "";
		// ���ɽ��������
		String znjycz = "";

		try {
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			String sql2 = "select * from dmdb.sf_jl_qsglkzpz t where t.pzlx='11' and t.sfyx='1' ";
			rs = da.querySQL(sql2);
			while (rs.next()) {
				znjjsrq = rs.getString("DYZ").replaceAll("-", "");
			}

			/*
			 * ������ ����ֵ��jsjdm�� ��������� ����˰�˵ļ�������� author: ������Ա���� ssjg: ������Ա���ڻ���
			 * clywbz: ����ҵ���־��1 �걨 2 ��˰ 3 ��ѯ 9ȫ���� ���Ϊ�գ�Ĭ��1 tsszdm: ��˰˰�ִ���
			 * ���ֵ��qsye�α꣺ Ƿ˰����α�
			 */
			String querysql = "BEGIN jkdb.sb_pkg_tools.getnsrqianshuishuju(?,?,?,?,?,?); END;";
			// String querysql= "call
			// jkdb.sb_pkg_tools.getnsrqianshuishuju(?,?,?,?,?.?)";

			CallableStatement cs = conn.prepareCall(querysql); // ���ô洢����

			cs.setString(1, form.getJsjdm());
			cs.setString(2, yhid);
			cs.setString(3, ssdwdm);
			cs.setString(4, clywbz);
			cs.setString(5, "");
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();

			ResultSet rs1 = (ResultSet) cs.getObject(6);

			// print the results
			String dqxtsj = this.dateFormat(new Date());
			while (rs1.next()) {
				HashMap tmp = new HashMap();
				// �������ɽ�����
				String skxjrq = this.dateFormat(rs1.getDate("skxjrq"));
				// ��ǰϵͳʱ�䣬�����ǰϵͳʱ������޽����ڣ��򲻼������ɽ�

				if (Integer.parseInt(dqxtsj) <= Integer.parseInt(skxjrq)) {
					tmp.put("znjts", "0");
				} else if (znjjsrq == ""
						|| znjjsrq == null
						|| Integer.parseInt(skxjrq) >= Integer
								.parseInt(znjjsrq)) {
					// �������ɽ����������޽����ڵ���һ�쿪ʼ����
					tmp.put("znjts", String.valueOf(this.getZnjts(getDate(rs1
							.getDate("skxjrq"), 0))));
				} else {
					tmp.put("znjts", String.valueOf(this.getZnjts(new Date(
							Integer.parseInt(znjjsrq.substring(0, 4)) - 1900,
							Integer.parseInt(znjjsrq.substring(4, 6)) - 1,
							Integer.parseInt(znjjsrq.substring(6, 8)))) + 1));
				}
				tmp.put("szmc", map
						.get(rs1.getString("szsmdm").substring(0, 2)));
				tmp.put("szsmmc", rs1.getString("szsmmc"));
				// Ƿ˰�γ�����
				tmp.put("qsxclxdm", rs1.getString("qsxclxdm"));
				tmp.put("qsxclxmc", rs1.getString("qsxclxmc"));
				tmp.put("skssqsrq", this.dateFormat(rs1.getDate("skssqsrq")));
				tmp.put("skssjzrq", this.dateFormat(rs1.getDate("skssjzrq")));
				tmp.put("yskxjrq", this.dateFormat(rs1.getDate("skxjrq")));
				tmp.put("qjje", this.round(rs1.getDouble("qjje")));
				qshj = qshj + rs1.getDouble("qjje");
				// ���һλΪ�����ɽ��ʶ�������ɽ�Ϊ1������Ϊ0
				qsycz = String.valueOf(rs1.getString("qsxzdm") + ":"
						+ this.dateFormat(rs1.getDate("skxjrq")) + ":"
						+ rs1.getString("xh"))
						+ ":"
						+ this.round(rs1.getFloat("qjje"))
						+ ":"
						+ rs1.getString("szsmdm").substring(0, 2)
						+ ":"
						+ rs1.getString("szsmdm")
						+ ":"
						+ this.dateFormat(rs1.getDate("skssqsrq"))
						+ ":"
						+ this.dateFormat(rs1.getDate("skssjzrq"))
						+ ":"
						+ ""
						+ ":" + rs1.getString("yspzh") + ":" + "0";

				znjycz = String.valueOf(rs1.getString("qsxzdm") + ":"
						+ this.dateFormat(rs1.getDate("skxjrq")) + ":"
						+ rs1.getString("xh"))
						+ ":"
						+ this.round(rs1.getFloat("qjje"))
						+ ":"
						+ rs1.getString("szsmdm").substring(0, 2)
						+ ":"
						+ rs1.getString("znjszsmdm")
						+ ":"
						+ this.getXskssrq(rs1.getDate("skxjrq"), 1)
						+ ":"
						+ this.dateFormat(now)
						+ ":"
						+ this.getZnjts(rs1.getDate("skxjrq"))
						+ ":"
						+ rs1.getString("yspzh") + ":" + "1";
				tmp.put("qsycz", qsycz);
				tmp.put("znjycz", znjycz);
				tmp.put("szsmdm", rs1.getString("szsmdm"));
				tmp.put("znjsl", rs1.getString("znjsl"));
				// Ƿ˰����
				tmp.put("qslxdm", rs1.getString("qslxdm"));
				datalist.add(tmp);
			}

			rs1.close();
			cs.close();

			form.setDataList(datalist);
			form.setQshj(this.round(qshj));
			rs.close();
			return form;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
	}

	/**
	 * doSave ����¼������
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doSave(VOPackage vo) throws BaseException {

		// ormapping����
		Sbjkzb orObj = new Sbjkzb();
		// ��ȡform����
		QsjksbForm form = (QsjksbForm) vo.getData();

		Connection conn = null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		// ��ǰ����
		String dqrq = formatter.format(new Date());

		// ���UserData
		UserData ud = vo.getUserData();
		// ������form�е�columns һ���ı��������е�����Ϊ��ϸ���ֶ���
		String names[] = { "jsjdm", "nsrmc", "yhmc", "yhdm", "zh", "sklxdm",
				"sklxmc", "sbrq" };
		// �õ�¼�����ϸ����
		String[] columns = { "xclx", "xjrq", "qsxh", "qsje", "szdm", "szsmdm",
				"skssksrq", "skssjsrq", "kssl", "yspzh", "hznjbz", "jsje",
				"sjse" };
		try {

			BeanUtil.copyBeanToBean(names, form, orObj);
			
			System.out.println("================������Ȼ�˵ǼǴ���=================");
			SWDJJBSJ jbsj = null;
			ZRRJBSJ zrrJbsj = null;
			
			/*// ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
			SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);*/
			
			if (TinyTools.isCompany(form.getJsjdm())) {
				// ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
				jbsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
			} else {
				// ͨ����Ȼ�˵Ǽǽӿ�ȡ����˰�������Ϣ
				zrrJbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm(), ud);
			}

			Timestamp now = new Timestamp((new java.util.Date()).getTime());
			// added by qinw 20070517 ��Ʊ
			String strs = form.getMxstrings();
			String[] mxstrs = strs.split(";");
			List keyList = new ArrayList();
			Map sjMap = new HashMap();
			for (int i = 0; i < mxstrs.length; i++) {
				String key = processString(mxstrs[i], sjMap);
				// key.substring(0,3);
				if (key != null && key != "") {
					keyList.add(key);
				}
			}
			//System.out.println("keyKist =================" + keyList.size());
			for (int j = 0; j < keyList.size(); j++) {
				
				if (TinyTools.isCompany(form.getJsjdm())) {
					// ����������Ϣ
					// �Ǽ�ע������
					orObj.setDjzclxdm(jbsj.getDjzclxdm());
					// ���ұ�׼��ҵ����
					orObj.setGjbzhydm(jbsj.getGjbzhydm());
					// ˰�������֯����
					orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
					// ���ջ���
					orObj.setZsswjgzzjgdm(ud.getSsdwdm());
					// ������ϵ
					orObj.setLsgxdm(jbsj.getLsgxdm());
					// ��Ӫ��ַ��ϵ�绰
					orObj.setJydzlxdm(jbsj.getJydzlxdm());
				} else {
					// ����������Ϣ
					// �Ǽ�ע������,��Ȼ��Ϊ��410
					orObj.setDjzclxdm("410");
					// ���ұ�׼��ҵ����,��Ȼ��Ϊ��8190 ����δ�����ķ���
					orObj.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM);
					// ˰�������֯����
					orObj.setSwjgzzjgdm(zrrJbsj.getSwjgzzjgdm());
					// ���ջ���
					orObj.setZsswjgzzjgdm(ud.getSsdwdm());
					// ������ϵ
					orObj.setLsgxdm("");
					// ��Ӫ��ַ��ϵ�绰
					orObj.setJydzlxdm("");
				}
				
				// ¼����
				orObj.setLrr("smsb");
				if (ud != null) {
					orObj.setLrr(ud.getYhid());
				}
				// ¼������
				orObj.setLrrq(now);
				// ��������
				orObj.setCjrq(now);
				// ˰�����ͣ�ȡkey��ǰ��λ
				String sklx = (String) keyList.get(j);
				//System.out.println(sklx);
				// Ƿ˰��ϸ����޽�����
				String xjrq = sklx.substring(3, 11);
				// �Ƚϵ�ǰ�������޽�����
				if (Integer.parseInt(xjrq) <= Integer.parseInt(dqrq)) {
					orObj.setBz(dqrq);
				} else {
					orObj.setBz(xjrq);
				}
				// form.setSklxdm(sklx.substring(0,3));
				// orObj.setSklxdm(form.getSklxdm());
				orObj.setSklxdm(sklx.substring(0, 3));
				// �걨��ʽ����
				orObj.setFsdm(CodeConstant.FSDM_SMSB);
				// ������Դ
				orObj.setSjly(CodeConstant.SMSB_SJLY_BJQS);
				
				/*// ���ش���
				orObj.setQxdm(jbsj.getSwjgzzjgdm().substring(0, 2));*/
				
				System.out.println("==========������Ȼ�˵����ش���==========");
				if (TinyTools.isCompany(form.getJsjdm())) {
					// ���ش���
					orObj.setQxdm(jbsj.getSwjgzzjgdm().substring(0, 2));
				} else {
					// ���ش���
					orObj.setQxdm(zrrJbsj.getSwjgzzjgdm().substring(0, 2));
				}
				
				// pxList �Ѿ��ź������ϸlist
				List pxList = (List) sjMap.get(keyList.get(j));
				Object[] mxObject = pxList.toArray();
				ArrayList list = new ArrayList();
				int rows = mxObject.length;
				for (int i = 0; i < rows; i++) {
					String[] tmp = mxObject[i].toString().split(":");
					HashMap map = new HashMap();
					for (int k = 0; k < columns.length; k++) {
						map.put(columns[k], tmp[k]);
					}
					list.add(map);
				}
				// form.setDataList(list);
				conn = SfDBResource.getConnection();
				ArrayList dataList = this.getDataList(orObj, list, form
						.getJksType());

				// ����dataList��������޸Ļ���Ƿ˰��ϸ�������
				ArrayList qsList = this.getQsmxList(dataList);

				// ����dataList������������걨Ƿ˰���ձ������
				ArrayList dzList = this.getDzList(dataList);

				// ����dataList���������걨�ɿ�������ϸ������
				// ע���걨���Ϊ��ʱ�����������
				String sbzbsql = "";
				String sbmxsql = "";
				String ajzxjlmxsql = "";
				String ajbh = "";
				String wfwzlxdm = "";
				for (int i = 0; i < dataList.size(); i++) {
					Map tmp = (Map) (dataList.get(i));
					PreparedStatement statement = null;
					// sjseΪ0��� ��һ�� 2014.09.15
					if (!(tmp.get("sjse").equals("0")||tmp.get("sjse").equals("0.00"))) {
						// ���ݱ��浽�걨�ɿ�����
						sbzbsql = "insert into sbdb.sb_jl_sbjkzb (JKPZH, SKLXDM, JSJDM, FSDM, LSGXDM, YHDM, YHMC, ZH, DJZCLXDM, SWJGZZJGDM, ZSSWJGZZJGDM, GJBZHYDM, GKZZJGDM, YSKMDM, YSJCDM, SZDM, LRRQ, SBRQ, XJRQ, CLBJDM, SJJE, ZYRQ, RKJE, ZWBS, LRR, SBBH, JYDZLXDM, SKSSKSRQ, SKSSJSRQ, SJLY, ND, CJRQ, QXDM, SPHM, BZ) values ('"
								+ tmp.get("jkpzh")
								+ "','"
								+ tmp.get("sklxdm")
								+ "','"
								+ tmp.get("jsjdm")
								+ "','"
								+ tmp.get("fsdm")
								+ "','"
								+ tmp.get("lsgxdm")
								+ "','"
								+ tmp.get("yhdm")
								+ "','"
								+ tmp.get("yhmc")
								+ "','"
								+ tmp.get("zh")
								+ "','"
								+ tmp.get("djzclxdm")
								+ "','"
								+ tmp.get("swjgzzjgdm")
								+ "','"
								+ tmp.get("zsswjgzzjgdm")
								+ "','"
								+ tmp.get("gjbzhydm")
								+ "','"
								+ tmp.get("gkzzjgdm")
								+ "','"
								+ tmp.get("yskmdm")
								+ "','"
								+ tmp.get("ysjcdm")
								+ "','"
								+ tmp.get("szdm")
								+ "',to_date('"
								+ tmp.get("lrrq")
								+ "','yyyy-MM-dd hh24:mi:ss'),to_date('"
								+ tmp.get("sbrq")
								+ "','yyyy-MM-dd'),to_date('"
								+ tmp.get("xjrq")
								+ "','yyyy-MM-dd hh24:mi:ss'),'"
								+ tmp.get("clbjdm")
								+ "','"
								+ tmp.get("sjse")
								+ "',to_date('"
								+ tmp.get("zyrq")
								+ "','yyyy-MM-dd'),'"
								+ tmp.get("rkje")
								+ "','"
								+ tmp.get("zwbs")
								+ "','"
								+ tmp.get("lrr")
								+ "','"
								+ tmp.get("sbbh")
								+ "','"
								+ tmp.get("jydzlxdm")
								+ "',to_date('"
								+ tmp.get("skssksrq")
								+ "','yyyy-MM-dd'),to_date('"
								+ tmp.get("skssjsrq")
								+ "','yyyy-MM-dd'),'"
								+ tmp.get("sjly")
								+ "','"
								+ tmp.get("nd")
								+ "',to_date('"
								+ tmp.get("cjrq")
								+ "','yyyy-MM-dd hh24:mi:ss'),'"
								+ tmp.get("qxdm")
								+ "','"
								+ tmp.get("sphm")
								+ "','" + tmp.get("bz") + "')";
						statement = conn.prepareStatement(sbzbsql);
						statement.executeUpdate();

						// ���ݱ��浽�걨�ɿ���ϸ��
						sbmxsql = "insert into sbdb.sb_jl_sbjkmx (SZSMDM, JKPZH, JSJDM, YSKMDM, YSJCDM, KSSL, JSJE, SJSE, SKSSKSRQ, SKSSJSRQ, RKJE, SBBH, SJFC, QJFC, SWJGZZJGDM, ND, CJRQ, LRRQ, QXDM) values ('"
								+ tmp.get("szsmdm")
								+ "','"
								+ tmp.get("jkpzh")
								+ "','"
								+ tmp.get("jsjdm")
								+ "','"
								+ tmp.get("yskmdm")
								+ "','"
								+ tmp.get("ysjcdm")
								+ "','"
								+ tmp.get("kssl")
								+ "','"
								+ tmp.get("jsje")
								+ "','"
								+ tmp.get("sjse")
								+ "',to_date('"
								+ tmp.get("skssksrq")
								+ "','yyyy-MM-dd'),to_date('"
								+ tmp.get("skssjsrq")
								+ "','yyyy-MM-dd'),'"
								+ tmp.get("rkje")
								+ "','"
								+ tmp.get("sbbh")
								+ "','"
								+ tmp.get("sjfc")
								+ "','"
								+ tmp.get("qjfc")
								+ "','"
								+ tmp.get("swjgzzjgdm")
								+ "','"
								+ tmp.get("nd")
								+ "',to_date('"
								+ tmp.get("cjrq")
								+ "','yyyy-MM-dd hh24:mi:ss'),to_date('"
								+ tmp.get("lrrq")
								+ "','yyyy-MM-dd hh24:mi:ss'),'"
								+ tmp.get("qxdm") + "')";

						statement = conn.prepareStatement(sbmxsql);
						statement.executeUpdate();

						// ˰������Ϊ500���걨���ݱ��浽����ִ�м�¼��ϸ
						
						//System.out.println("tmp.get('yspzh')==============="+tmp.get("yspzh"));
						// jcdb.jc_jl_ajzxjlmx
						if (tmp.get("sklxdm").equals("500")&& !(tmp.get("yspzh").equals("null"))) {
							ajbh = tmp.get("yspzh").toString().substring(0,
									tmp.get("yspzh").toString().indexOf(","));
							wfwzlxdm = tmp.get("yspzh").toString().substring(
									tmp.get("yspzh").toString()
											.lastIndexOf(",") + 1,
									tmp.get("yspzh").toString().length());

							ajzxjlmxsql = "insert into jcdb.jc_jl_ajzxjlmx (AJBH,SZSMDM,JE,JKPZH,PMZE,SKLXDM,ZXFSDM,ZXBM,ZXRY,ZXRQ,XH,XJRQ,ZHGXSJ,WFWZLXDM,CZRQ,CZRY,CJRQ,LRRQ,SWJGZZJGDM,QXDM,BZ,RKPZH,RKBS,HZNJBZ) select '"
									+ ajbh
									+ "','"
									+ tmp.get("szsmdm")
									+ "',"
									+ tmp.get("sjse")
									+ ",'"
									+ tmp.get("jkpzh")
									+ "',"
									+ tmp.get("sjse")
									+ ",'500','01','"
									+ tmp.get("zsswjgzzjgdm")
									+ "','"
									+ ud.yhmc
									+ "',to_date(to_char(SYSDATE,'yyyymmdd'),'yyyymmdd')"
									+ ",decode(max(b.xh),null,0,max(b.xh)+1)"
									+ ",to_date('"
									+ tmp.get("xjrq")
									+ "','yyyy-mm-dd'),to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'"
									+ wfwzlxdm
									+ "',sysdate,'"
									+ ud.yhmc
									+ "',sysdate,sysdate,'"
									+ tmp.get("swjgzzjgdm")
									+ "','"
									+ tmp.get("qxdm")
									+ "','"
									+ "SBZS-BJQJSK"
									+ "','"
									+ tmp.get("jkpzh")
									+ "','"
									+ "0"
									+ "','"
									+ tmp.get("hznjbz")
									+ "' from jcdb.jc_jl_ajzxjlmx b where b.ajbh='"
									+ ajbh
									+ "' and b.szsmdm='"
									+ tmp.get("szsmdm")
									+ "' and b.wfwzlxdm='"
									+ wfwzlxdm + "'";
							//System.out.println("ajzxjlmxsql:"+ajzxjlmxsql);
							statement = conn.prepareStatement(ajzxjlmxsql);
							statement.executeUpdate();
						}
						statement.close();
					}
				}

				// ����qsList�����޸�Ƿ˰��ϸ������
				String qsmxsql = "";
				for (int i = 0; i < qsList.size(); i++) {
					Map tmp = (Map) (qsList.get(i));
					PreparedStatement statement = null;

					// �޸�Ƿ˰��ϸ������
					qsmxsql = "update jkdb.kj_jl_qsmx set qjje=qjje-'"
							+ tmp.get("sbje") + "' ,sbje=sbje+'"
							+ tmp.get("sbje") + "'where xh='" + tmp.get("xh")
							+ "'";
					statement = conn.prepareStatement(qsmxsql);

					statement.executeUpdate();
					statement.close();

				}

				// ����dzList���������걨Ƿ˰���ձ�����
				String qssbdzsql = "";
				for (int i = 0; i < dzList.size(); i++) {
					Map tmp = (Map) (dzList.get(i));
					PreparedStatement statement = null;

					// �޸�Ƿ˰��ϸ������
					qssbdzsql = "insert into jkdb.kj_jl_sb_qs_dz (JSJDM, JKPZH, QS_XH, SBBH, SBJE, ZNJ, ZNJJKPZH, QXDM) values ('"
							+ tmp.get("jsjdm")
							+ "','"
							+ tmp.get("jkpzh")
							+ "','"
							+ tmp.get("qsxh")
							+ "','"
							+ tmp.get("sbbh")
							+ "','"
							+ tmp.get("sbje")
							+ "','"
							+ tmp.get("znj")
							+ "','"
							+ tmp.get("znjjkpzh")
							+ "','"
							+ tmp.get("qxdm") + "')";
					statement = conn.prepareStatement(qssbdzsql);

					statement.executeUpdate();
					statement.close();
				}

				// ����dataListȡ��sbbhList�б���
				ArrayList sbbhList = this.getSbbhList(dataList);
				form.setSbbhList(sbbhList);
			}

		} catch (Exception ex1) {
			ex1.printStackTrace();
			throw new ApplicationException("��������ʧ�ܣ�");
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}

	/**
	 * ���ɲ����걨�ɿ������걨�ɿ���ϸ�������List��
	 * 
	 * @param
	 * @return ����list
	 * @throws Exception
	 */
	private ArrayList getDataList(Sbjkzb orObj, ArrayList list, int jkstype)
			throws Exception {
		HashMap tmp = new HashMap();
		// �޽���������
		int a = 15;
		int sbbhnum = 0;
		int jkpzhnum = 0;
		Date now = new Date();

		String tmpsbbh = this.getSbbh(orObj.getJsjdm());
		String tmpsbjkpzh = this.getSbJkpzh(orObj.getJsjdm());
		for (int i = 0; i < list.size(); i++) {
			tmp = (HashMap) list.get(i);
			tmp.put("sklxdm", orObj.getSklxdm());
			tmp.put("jsjdm", orObj.getJsjdm());
			tmp.put("fsdm", orObj.getFsdm());
			tmp.put("lsgxdm", orObj.getLsgxdm());
			tmp.put("yhdm", orObj.getYhdm());
			tmp.put("yhmc", orObj.getYhmc());
			tmp.put("zh", orObj.getZh());
			tmp.put("djzclxdm", orObj.getDjzclxdm());
			tmp.put("swjgzzjgdm", orObj.getSwjgzzjgdm());
			tmp.put("zsswjgzzjgdm", orObj.getZsswjgzzjgdm());
			tmp.put("gjbzhydm", orObj.getGjbzhydm());
			tmp.put("lrrq", this.datestrFormat(now));
			tmp.put("sbrq", this.dateFormat(orObj.getLrrq()));
			tmp.put("xjrq", orObj.getBz());
			tmp.put("zyrq", this.dateFormat(now));
			tmp.put("rkje", tmp.get("sjse"));
			tmp.put("lrr", orObj.getLrr());
			tmp.put("jydzlxdm", orObj.getJydzlxdm());
			tmp.put("sjly", orObj.getSjly());
			tmp.put("nd", String.valueOf(TinyTools.getYear(orObj.getSbrq())));
			tmp.put("cjrq", this.datestrFormat(now));
			tmp.put("qxdm", orObj.getQxdm());
			tmp.put("gkzzjgdm", CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
					"swjgzzjgdm", orObj.getSwjgzzjgdm(), "gkjhh"));
			tmp.put("clbjdm", "10");
			Ysjc ysjc = getYsjc(tmp.get("jsjdm").toString(), tmp.get("szsmdm")
					.toString(), this.datestrFormat1(tmp.get("skssjsrq")
					.toString()));
			tmp.put("ysjcdm", ysjc.getYsjcdm());
			Yskm yskm = null;
			if (!tmp.get("szsmdm").equals("null")) {
				yskm = JKAdapter.getInstance().getYskm(
						tmp.get("szsmdm").toString(),
						tmp.get("djzclxdm").toString(),
						tmp.get("gjbzhydm").toString(), ysjc.getYsjcdm());
			}
			if (yskm != null) {
				tmp.put("yskmdm", yskm.getYskmdm());
				if (tmp.get("ysjcdm").equals("21")) { // ����ǵط���
					BigDecimal big = new BigDecimal(tmp.get("sjse").toString());
					tmp.put("sjfc", this.getFc(big, yskm.getSjfcbl())); // �����оּ��ֳɽ��
					tmp.put("qjfc", this.getFc(big, yskm.getQxfcbl())); // �������ؼ��ֳɽ��
				}
			} else {
				tmp.put("yskmdm", "");
				tmp.put("sjfc", "0"); // �����оּ��ֳɽ��
				tmp.put("qjfc", "0"); // �������ؼ��ֳɽ��
			}
			tmp.put("zwbs", "00000000000000000000");
			tmp.put("bz", "��" + orObj.getBz().substring(0, 4) + "��"
					+ orObj.getBz().substring(4, 6) + "��"
					+ orObj.getBz().substring(6, 8) + "�գ������գ�ǰ�ɿ�");


			// ÿ8����¼����һ���걨���
			sbbhnum = sbbhnum + 1;
			if (sbbhnum % 8 == 0) {
				tmp.put("sbbh", tmpsbbh);
				tmpsbbh = this.getSbbh(tmp.get("jsjdm").toString());
				sbbhnum = 0;
			} else {
				tmp.put("sbbh", tmpsbbh);
			}
			if (jkstype == 2) {
				// ÿ8����¼����һ��15λ�걨�ɿ�� ������ˮ��
				jkpzhnum = jkpzhnum + 1;
				if (jkpzhnum % 8 == 0) {
					tmp.put("jkpzh", tmpsbjkpzh + String.valueOf(jkpzhnum));
					tmpsbjkpzh = this.getSbJkpzh(tmp.get("jsjdm").toString());
					jkpzhnum = 0;
				} else {
					tmp.put("jkpzh", tmpsbjkpzh + String.valueOf(jkpzhnum));
				}
				tmp.put("sphm", tmp.get("jkpzh"));
			} else {
				// һƱһ˰�ɿ�ƾ֤�ŵ�16λ��ˮ��Ϊ0
				tmp.put("jkpzh", this.getSbJkpzh(tmp.get("jsjdm").toString())
						+ "0");
				tmp.put("sphm", tmp.get("jkpzh"));
			}
		}
		return list;
	}

	/**
	 * �����޸�Ƿ˰��ϸ�������List��
	 * 
	 * @param
	 * @return ����list
	 * @throws Exception
	 */
	private ArrayList getQsmxList(ArrayList list) throws Exception {
		HashMap tmp = new HashMap();
		ArrayList qsList = new ArrayList();

		for (int i = 0; i < list.size(); i++) {
			tmp = (HashMap) list.get(i);
			HashMap map = new HashMap();
			if (i % 2 == 0) {
				map.put("xh", tmp.get("qsxh"));
				map.put("sbje", tmp.get("sjse"));
				qsList.add(map);
			}
		}
		return qsList;
	}

	/**
	 * �����޸�Ƿ˰��ϸ�������List��
	 * 
	 * @param
	 * @return ����list
	 * @throws Exception
	 */
	private ArrayList getDzList(ArrayList list) throws Exception {
		HashMap tmp1 = new HashMap();
		HashMap tmp2 = new HashMap();
		ArrayList dzList = new ArrayList();

		for (int i = 0; i < list.size(); i++) {
			tmp1 = (HashMap) list.get(i);
			HashMap map = new HashMap();
			if (i % 2 == 0) {
				tmp2 = (HashMap) list.get(i + 1);
				if (tmp1.get("qsxh").toString().equals(
						tmp2.get("qsxh").toString())) {
					map.put("jsjdm", tmp1.get("jsjdm"));
					map.put("jkpzh", tmp1.get("jkpzh"));
					map.put("qsxh", tmp1.get("qsxh"));
					map.put("sbbh", tmp1.get("sbbh"));
					map.put("sbje", tmp1.get("sjse"));
					if (!tmp2.get("sjse").toString().equals("0")) {
						map.put("znj", tmp2.get("sjse"));
						map.put("znjjkpzh", tmp2.get("jkpzh"));
					} else {
						map.put("znj", "");
						map.put("znjjkpzh", "");
					}
					map.put("qxdm", tmp1.get("qxdm"));
				}
				dzList.add(map);
			}
		}
		return dzList;
	}

	/**
	 * �����걨��ŵ�����List��
	 * 
	 * @param
	 * @return ����list
	 * @throws Exception
	 */
	private ArrayList getSbbhList(ArrayList list) throws Exception {
		HashMap tmp = new HashMap();
		ArrayList sbbhList = new ArrayList();
		String sbbh = "";

		for (int i = 0; i < list.size(); i++) {
			tmp = (HashMap) list.get(i);
			if (!tmp.get("sbbh").equals("") && tmp.get("sbbh") != null) {
				if (!tmp.get("sbbh").equals(sbbh)) {
					sbbhList.add(tmp.get("sbbh"));
					sbbh = tmp.get("sbbh").toString();
				}

			}
		}
		return sbbhList;
	}

	/**
	 * getSzmc ����ѯ������˰�����ƴ���map
	 * 
	 * @param null
	 * @return map
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	private Map getSzmc() throws BaseException {
		Connection conn = null;
		ResultSet rs = null;
		HashMap map = new HashMap();
		try {
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			String sql = "select t.szsmdm,t.szsmmc from dmdb.sb_dm_szsm t where length(t.szsmdm)=2 order by t.szsmdm";
			rs = da.querySQL(sql);
			while (rs.next()) {
				map.put(rs.getString("szsmdm"), rs.getString("szsmmc"));
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return map;
	}

	/**
	 * �ṩ��ȷ��С��λ�������봦��
	 * 
	 * @param v
	 *            ��Ҫ�������������
	 * @param scale
	 *            С���������λ
	 * @return ���������Ľ��
	 */

	private String round(double v) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("###0.00");
		String strValue = df.format(v);
		return strValue;
	}

	/**
	 * ��ʽ�������������ַ������͡�
	 * 
	 * @param date
	 *            ��Ҫ��ʽ����������date
	 * @return ��ʽ����Ľ��
	 */
	private String dateFormat(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}

	/**
	 * ��ʽ���ַ����������������͡�
	 * 
	 * @param date
	 *            ��Ҫ��ʽ����������date
	 * @return ��ʽ����Ľ��
	 */
	private String datestrFormat(Date dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dateStr);
	}

	private Date datestrFormat1(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return Timestamp.valueOf(sdf.format(SfDateUtil.getDate(dateStr)));
	}

	/**
	 * �������ɽ�������
	 * 
	 * @param date
	 *            ԭ˰���޽�����
	 * @return ���ɽ�����
	 */
	private int getZnjts(Date yxjrq) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp now = new Timestamp((new java.util.Date()).getTime());
		Date startTime = formatter.parse(formatter.format(yxjrq),
				new ParsePosition(0));
		Date endTime = formatter.parse(now.toString(), new ParsePosition(0));
		long l = Math.abs(endTime.getTime() - startTime.getTime());
		return (int) (l / 86400000);
	}

	/**
	 * ������˰���޽����ڡ�
	 * 
	 * @param
	 * @return ��˰���޽�����
	 */
	private String getXskssrq(Date date, int i) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, i);
		Date d = c.getTime();
		return this.dateFormat(d);
	}

	/**
	 * ͨ��˰��˰Ŀ������ȷ��Ԥ�㼶��<br>
	 * ��˰��Ϊ�Ļ���ҵ����ѻ�����Ͷ����ҵ����ʹ�÷�ʱ ͨ��˰�ѽӿ�ȡ����Ӧ��Ԥ�㼶���������Ϊ�ط���
	 * 
	 * @param jsjdm
	 *            ���������
	 * @param szsmdm
	 *            ˰��˰Ŀ����
	 * @param rq
	 *            ����
	 * @return Ysjc Ԥ�㼶����Ϣ
	 * @throws Exception
	 */
	private Ysjc getYsjc(String jsjdm, String szsmdm, Date rq) throws Exception {
		Ysjc ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
		// ����˰�ѹ���ӿڵõ�Ԥ�㼶��
		try {
			com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
			com.ttsoft.bjtax.sfgl.common.model.Ysjc sfysjc = sfServiceProxy
					.getYsjcInfo(jsjdm, szsmdm, rq);
			if (sfysjc == null) {
				// ���û�еõ��϶��������϶�Ϊ�ط���
				ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
			} else {
				ysjc = new Ysjc(sfysjc.getYsjcdm(), sfysjc.getYsjcmc());
			}

		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "��ѯԤ�㼶��ʧ��!");
		}

		return ysjc;
	}

	/**
	 * ����ֳɽ��
	 * 
	 * @param je
	 *            ʵ�ɽ��
	 * @param bl
	 *            �ֳɱ�����Ĭ����0��00��
	 * @return �ֳɽ��(����4λС��)�����ʵ�ɽ���ֳɱ���Ϊnull����Ż�0
	 */
	private BigDecimal getFc(BigDecimal je, BigDecimal bl) {
		// �ƻ�ӿ�û�з��طֳɱ���
		if (je == null || bl == null) {
			return new BigDecimal(0);
		}
		BigDecimal fc = je.multiply(bl).setScale(4, BigDecimal.ROUND_HALF_UP);
		return fc;
	}

	/**
	 * �õ��ɿ�ƾ֤�� 16λ<br>
	 * ��������:<br>
	 * ----���������(8λ)���꣨2λ�����£�2λ����3λ˳��ţ�1λ��ˮ��<br>
	 * ----˳���Ϊ��ǰ��������뱾�µļ�¼����1<br>
	 * ----��ˮ��ΪһƱ��˰�����һ��ƾ֤�ж����Ŀ��˳���<br>
	 * 
	 * @param jsjdm
	 *            ���������
	 * @param time
	 *            Ҫ����ҵ�ʱ��
	 * @return String �������һλ��ˮ�ŵĽɿ�ƾ֤��
	 * @throws Exception
	 *             �����쳣
	 */

	private String getSbJkpzh(String jsjdm) throws BaseException {
		String sbJkpzh = null;
		try {
			sbJkpzh = ServiceProxy.getJkpzh(jsjdm);

		} catch (BaseException ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "�����걨���ɽɿ�ƾ֤��ʧ�ܣ�");
		}
		return sbJkpzh;
	}

	/**
	 * �걨��ŵ�����,�걨��ŵ����ɹ���Ϊ�������������Ϸ������ĵ�ǰʱ���ʮ��λ�ַ���
	 * 
	 * ʹ���걨�Ľӿ������걨��š�
	 * 
	 * @param jsjdm
	 *            ���������
	 * @return sbbh
	 */
	private String getSbbh(String jsjdm) throws BaseException {
		String sbbh;
		try {
			sbbh = ServiceProxy.getSbbh(jsjdm);
		} catch (BaseException ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "�����걨�����걨���ʧ�ܣ�");
		}
		return sbbh;
	}

	private String processString(String a, Map map) {
		String[] strArray = a.split(":");
		String tmp = strArray[0];
		//for (int i = 0; i < strArray.length; i++) {
		//	System.out.println(strArray[i]);
		//}
		if (!tmp.equals("03")) {
			tmp = "700";
		} else {
			tmp = "500";
		}
		String key = tmp + strArray[1];
		//System.out.println("key===" + key);
		// int[] intArray = new int[strArray.length];
		// for (int i = 0; i < strArray.length; i++) {
		// intArray[i] = Integer.parseInt(strArray[i]);
		// }

		return putIntoMap(key, a, map);
	}

	private String putIntoMap(String key, String tmp, Map map) {
		List list = (List) map.get(key);
		if (list != null) {
			list.add(tmp);
			return "";
		} else {
			List newList = new ArrayList();
			newList.add(tmp);
			map.put(key, newList);
			//System.out.println(key);
			return key;
		}

	}

	// �������ں���ʱ�䣬dateΪҪ��������ڣ� iΪҪ�Ӻ������
	private Date getDate(Date date, int i) {
		GregorianCalendar g = new GregorianCalendar();
		g.setTime(date);
		g.add(g.DATE, i);
		return g.getTime();

	}

}
