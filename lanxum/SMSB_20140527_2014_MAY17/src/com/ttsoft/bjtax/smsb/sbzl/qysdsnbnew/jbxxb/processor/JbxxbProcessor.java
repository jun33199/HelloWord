/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jbxxb.processor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.Jbxx;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jbxxb.web.JbxxbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:��ҵ����˰�걨
 * </p>
 * 
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JbxxbProcessor implements Processor {
	// /**
	// * ��ҵ����˰˰��
	// */
	// private final String QYSDS_SL = "0.33";
	//
	// /**
	// * ��ҵ����˰˰��
	// */
	// private final String QYSDS_SZ = "30";

	/**
	 * ʵ��Processor�ӿ�
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException
	 *             ҵ���쳣 1 ���������Ĳ������Ͳ���ʱ�׳� 2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	 *             �����쳣�׳���EJB��process��������
	 */

	public Object process(VOPackage vo) throws BaseException {

		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			result = doShow(vo);
			break;
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		// case CodeConstant.SMSB_DELETEACTION:
		// result = doDelete(vo);
		// break;
		default:
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}

		return result;
	}

	/**
	 * doShow��ʼ������ҳ����ϢҪ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doShow(VOPackage vo) throws BaseException {

		JbxxbForm jbxxbForm = (JbxxbForm) vo.getData();

		// ��ʼ��FORM�������걨���ڡ��걨�ڼ�
		initForm(jbxxbForm);

		UserData ud = (UserData) vo.getUserData();

		jbxxbForm.setCjr(ud.yhid);
		jbxxbForm.setLrr(ud.yhid);

		return jbxxbForm;
	}

	/**
	 * doQuery ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 * 
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		JbxxbForm jbxxbForm = (JbxxbForm) vo.getData();
		jbxxbForm.setNsrmc(""); // ��˰������
		jbxxbForm.setLxdh(""); // ע���ַ��ϵ�绰

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = SfDBResource.getConnection();

			UserData ud = (UserData) vo.getUserData();
			SWDJJBSJ djsj = null;
			try {
				// �����ҵ�Ǽǻ�����Ϣ

				djsj = InterfaceDj.getJBSJ_New(jbxxbForm.getJsjdm(), ud);

			} catch (Exception ex1) {
				throw ExceptionUtil.getBaseException(ex1);
			}
			jbxxbForm.setNsrsbh(djsj.getSwdjzh()); // ��˰��ʶ���
			jbxxbForm.setNsrmc(djsj.getNsrmc()); // ��˰������
			/**
			 * ҳ���Ϊ�걨��ȣ���Ҫת��Ϊ˰����ȣ�Ϊ�걨��ȵ�ǰһ��
			 * 
			 * @todo ҳ���Ϊ˰�����
			 */
			// Map dateMap = new HashMap();
			// dateMap = getSbrl(jbxxbForm.getSbnd() + "0601");
			// ��õ�ǰ���ݿ�ʱ��
			Timestamp ret = TinyTools.getDBTimestamp(conn);
			// ��õ�ǰ���ݿ�ʱ������
			String sbnd = String.valueOf(TinyTools.getYear(ret));
			System.out.println("���ݿ�ʱ����걨��ȣ�" + sbnd);
			jbxxbForm.setSbnd(sbnd);

			// jbxxbForm.setSknd(dateMap.get("ksrq")
			// .toString().substring(0, 4));// ��ȣ�ҳ��¼����ȵ�ǰһ��

			jbxxbForm.setSsjjlxdm(djsj.getDjzclxdm()); // ������������-�Ǽ�ע�����ʹ���
			jbxxbForm.setSsjjlxmc(djsj.getDjzclxmc());// ������������-�Ǽ�ע����������
			jbxxbForm.setLxdh(djsj.getZcdzlxdh()); // ע���ַ��ϵ�绰
			jbxxbForm.setJydz(djsj.getJydz());// ��Ӫ��ַ
			jbxxbForm.setSshydm(djsj.getGjbzhydm());// ������ҵ����
			jbxxbForm.setSshymc(djsj.getGjbzhymc());// ������ҵ����

			jbxxbForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); // ˰�������֯��������
			jbxxbForm.setSwjgzzjgmc(djsj.getSwjgzzjgmc()); // ˰�������֯��������

			jbxxbForm.setLrr(ud.getYhid());// ¼����
			jbxxbForm.setCjr(ud.getYhid());// ������

			// ����˰��������
			jbxxbForm.setSkssksrq(jbxxbForm.getSknd() + "0101");
			jbxxbForm.setSkssjsrq(jbxxbForm.getSknd() + "1231");

			java.util.Date time = SfDateUtil.getDate(jbxxbForm.getSbnd()
					+ "0601");// ƥ��˰�ѽӿڵ�rq������������
			// �걨����

			// ����˰�ѽӿڻ����ҵ�����շ�ʽ
			ServiceProxy proxy = new ServiceProxy();
			System.out.println("˰����ڲ��������������=" + jbxxbForm.getJsjdm());
			System.out.println("˰����ڲ������걨����=" + time);
			System.out.println("˰����ڲ�����˰��������ʼ����="
					+ SfDateUtil.getDate(jbxxbForm.getSkssksrq()));
			System.out.println("˰����ڲ�����˰��������������="
					+ SfDateUtil.getDate(jbxxbForm.getSkssjsrq()));

			QysdsSet sdsInfo = proxy.getQysdsInfo(jbxxbForm.getJsjdm(), time,
					SfDateUtil.getDate(jbxxbForm.getSkssksrq()), SfDateUtil
							.getDate(jbxxbForm.getSkssjsrq()),
					CodeConstant.SFGL_QYSDS_BBFS_NB);
			// �ж����շ�ʽ
			if (sdsInfo.getZsfs() != null) {

				System.out.println(jbxxbForm.getJsjdm() + "��ҵ���շ�ʽ��Ϊ�� :"
						+ sdsInfo.getZsfs().getZsfsdm());

				jbxxbForm.setZsfsdm(sdsInfo.getZsfs().getZsfsdm()); // ���շ�ʽ����

				jbxxbForm.setZsfsmc(sdsInfo.getZsfs().getZsfsmc()); // ���շ�ʽ����

				// modified by hazhengze 20051227 End
				if (jbxxbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {

					throw new ApplicationException("����˰��Ϊ�����ʷ�ʽ������Ҫ���˰�˻�����Ϣ��!");

				} else if (jbxxbForm.getZsfsdm().equals(
						CodeConstant.ZSFSDM_DEZS)) { // ��������

					throw new ApplicationException("����˰��Ϊ�������շ�ʽ������Ҫ���˰�˻�����Ϣ��!");
				} else if (jbxxbForm.getZsfsdm().equals(
						CodeConstant.ZSFSDM_CZZS)) { // ��������

				}
			} else {
				// 20070208���շ�ʽ���ȡ��Ϊ������Ϊ�ǲ���������ҵ�ġ�
				// throw new ApplicationException(
				// "����˰��û�к˶���ҵ����˰���շ�ʽ����˶��������д������Ϣ��!");

				jbxxbForm.setZsfsdm(CodeConstant.ZSFSDM_CZZS);
				jbxxbForm.setZsfsmc(CodeConstant.ZSFSNAME_CZZS);
			}

			jbxxbForm.setCwkjzddm(CodeConstant.CWKJZD01);// ����ƶȴ���
			jbxxbForm.setCwkjzddm_old(CodeConstant.CWKJZD01);// ����ƶȴ����޸�ǰ

			jbxxbForm.setJmlxdm(CodeConstant.JMLXNO);// �������ʹ���
			jbxxbForm.setJmlxdm_old(CodeConstant.JMLXNO);// �������ʹ����޸�ǰ

			// ���ʹ�����ʽ
			jbxxbForm.setGzglxsdm(CodeConstant.GZGLXS01);
			// ���ʹ�����ʽ
			jbxxbForm.setGzglxsdm_old(CodeConstant.GZGLXS01);

			/** **********************************�Ѿ�¼�����ҳ����Ϣstart************************************************************** */

			StringBuffer bf = new StringBuffer();
			// select
			bf
					.append(" select ")
					// ��ѯ�ֶ�
					.append(" NSRJSJDM,NSRSBH,NSRMC,ND,SBND,SSJJLX, ")
					// �Ӳ�ѯ-���ݵǼ�ע�����ʹ�����ѯ�Ǽ�ע����������
					// ������������-�Ǽ�ע������
					.append(
							" (SELECT DJZCLXMC FROM DMDB.DJ_DM_DJZCLX T2 WHERE T2.DJZCLXDM=T1.SSJJLX) AS SSJJLXMC, ")
					.append(" LXDH,JYDZ,SSHY, ")
					// �Ӳ�ѯ-����������ҵ������ѯ������ҵ����
					.append(
							" (SELECT GJBZHYMC FROM DMDB.GY_DM_GJBZHY T3 WHERE T3.GJBZHYDM=T1.SSHY) AS SSHYMC,")
					.append(" ZSFS, ")
					// �Ӳ�ѯ-������ҵ����˰���շ�ʽ������ѯ��ҵ����˰���շ�ʽ����
					.append(
							" (SELECT ZSFSMC FROM DMDB.SF_DM_ZSFS T4 WHERE T4.ZSFSDM=T1.ZSFS) AS ZSFSMC,")
					.append("CKZD,GZGLXS, ")
					.append(" JMLX,SWJGZZJGDM, ")
					// �Ӳ�ѯ-����˰�������֯�����ѯ˰�������֯��������
					.append(
							" (SELECT SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T5 WHERE T5.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC, ")
					.append(" CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF ")
					// from
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ")
					// where
					.append(" where  t1.nsrjsjdm = ? and t1.nd = ? ");

			System.out.println("��ҵ����˰-������Ϣ��ѯSQL");
			System.out.println(bf.toString());

			ps = conn.prepareStatement(bf.toString());
			System.out.println("1-" + jbxxbForm.getJsjdm());
			System.out.println("2-" + jbxxbForm.getSknd());
			ps.setString(1, jbxxbForm.getJsjdm());
			ps.setString(2, jbxxbForm.getSknd());
			rs = ps.executeQuery();

			if (rs.next()) {

				// ��˰�˼��������
				jbxxbForm.setJsjdm(rs.getString("NSRJSJDM"));
				// ��˰��ʶ��ţ�˰��Ǽ�֤��
				// jbxxbForm.setNsrsbh(rs.getString("NSRSBH"));
				// ��˰������
				// jbxxbForm.setNsrmc(rs.getString("NSRMC"));
				// ˰�����
				jbxxbForm.setSknd(rs.getString("ND"));
				// �걨���
				jbxxbForm.setSbnd(rs.getString("SBND"));
				// ������������
				// jbxxbForm.setSsjjlxdm(rs.getString("SSJJLX"));
				// ������������
				// jbxxbForm.setSsjjlxmc(rs.getString("SSJJLXMC"));
				// ��ϵ�绰
				jbxxbForm.setLxdh(rs.getString("LXDH"));
				// ��Ӫ��ַ
				// jbxxbForm.setJydz(rs.getString("JYDZ"));
				// ������ҵ
				// jbxxbForm.setSshydm(rs.getString("SSHY"));
				// ������ҵ
				// jbxxbForm.setSshymc(rs.getString("SSHYMC"));
				// �ƻ��ƶ� 00:һ��01:����02:����
				jbxxbForm.setCwkjzddm(rs.getString("CKZD"));
				// ����ƶȴ����޸�ǰ
				jbxxbForm.setCwkjzddm_old(rs.getString("CKZD"));
				// ˰�������֯��������
				// jbxxbForm.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				// ˰�������֯��������
				// jbxxbForm.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));

				// ��ҵ����˰���շ�ʽ
				// jbxxbForm.setZsfsdm(rs.getString("ZSFS"));
				// ��ҵ����˰���շ�ʽ
				// jbxxbForm.setZsfsmc(rs.getString("ZSFSMC"));

				// ��������
				jbxxbForm.setJmlxdm(rs.getString("JMLX"));
				// ��������
				jbxxbForm.setJmlxdm_old(rs.getString("JMLX"));

				// ���ʹ�����ʽ
				jbxxbForm.setGzglxsdm(rs.getString("GZGLXS"));
				// ���ʹ�����ʽ
				jbxxbForm.setGzglxsdm_old(rs.getString("GZGLXS"));

				jbxxbForm.setCjr(rs.getString("CJR"));
				jbxxbForm.setCjrq(SBStringUtils.getStrFromDate(rs
						.getTimestamp("CJSJ")));
				jbxxbForm.setCjr(rs.getString("LRR"));
				jbxxbForm.setLrrq(SBStringUtils.getStrFromDate(rs
						.getTimestamp("LRSJ")));
				jbxxbForm.setXtjb(rs.getString("XTJB"));
				jbxxbForm.setBbmsf(rs.getString("BBMSF"));

			}

			/** ***********************************�Ѿ�¼���end*************************************************************** */

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {

			SfDBResource.freeConnection(conn);
		}
		System.out.println(jbxxbForm.getJsjdm() + "��ҵ���շ�ʽΪ"
				+ jbxxbForm.getZsfsdm());

		return jbxxbForm;
	}

	/**
	 * doSave ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doSave(VOPackage vo) throws BaseException {

		JbxxbForm jbxxbForm = (JbxxbForm) vo.getData();

		Connection conn = null;
		SfDBAccess dbAgent = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		// sql buffer
		StringBuffer bf = new StringBuffer();

		// �����Ƿ�ƥ��
		boolean jm_type = false;

		/***********************************************************************
		 * �жϼ����������˰�Ѻ˶��ļ��������У�������throw new
		 * ApplicationException("����˰�˲����ܴ˼�������!");
		 * 
		 * 
		 * ����֮ǰ���� �޸�ǰ��ֵ���޸ĺ��ֵ �ж� ����ƶȡ����ʹ�����ʽ�����������Ƿ��޸Ĺ�����������е��걨�����ݽ���ɾ��
		 * 
		 * ����ɹ��� ������ƶȡ����ʹ�����ʽ���������Ͷ�Ӧ��form�е�
		 * 
		 * cwkjzddm cwkjzddm_old
		 * 
		 * gzglxsdm gzglxsdm_old
		 * 
		 * jmlxdm jmlxdm_old
		 * 
		 * ����Ϊһ��
		 * 
		 * ����
		 * 
		 * 
		 **********************************************************************/

		try {
			conn = SfDBResource.getConnection();
			st = conn.createStatement();
			dbAgent = new SfDBAccess(conn);
			UserData ud = (UserData) vo.getUserData();

			// ����˰�ѽӿ�
			ServiceProxy proxy = new ServiceProxy();

			// ��ȡ���������ݿ�ӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

			// ���ð汾��
			jbxxbForm.setVersion(iApp
					.getCurrentVersion(Constants.CREPORTS_APPID_QYSDS));

			jbxxbForm.setLrrq(SBStringUtils.getStrFromDate(TinyTools
					.getDBTimestamp(conn)));
			System.out.println("CJRQ=" + jbxxbForm.getCjrq());
			if (jbxxbForm.getCjrq() == null || "".equals(jbxxbForm.getCjrq())) {
				jbxxbForm.setCjrq(jbxxbForm.getLrrq());
			}

			// ����˰�ѱ����շ�ʽ�϶����е���˰��״̬
			try {
				String sql = "update sfdb.sf_jl_qysdszsfs_his set nsrzt='10',lrrq=sysdate where jsjdm='"
						+ jbxxbForm.getJsjdm() + "'";
				dbAgent.updateSQL(sql);
			} catch (BaseException ex1) {
				// �׳��쳣
				ex1.printStackTrace();
				new ApplicationException("���ݿ���¼�¼ʧ�ܣ�" + jbxxbForm.getJsjdm()
						+ ":" + ex1.getMessage());
			}

			/**
			 * TODO �жϼ����������˰�Ѻ˶��ļ��������У�������throw new
			 * ApplicationException("����˰�˲����ܴ˼�������!");
			 * 
			 * ����м�������ͬʱ��������������
			 * 
			 * 1�����û�м������ͣ������ж�,�����������ֻΪĬ��ֵ "����λ�޼�����������"
			 * 2�����Ϊ�����������ͣ���ֻ�ж��Ƿ��м��⣬�������ͣ�ͬʱ��õ�һ�������������
			 * 3�����Ϊ�����¼�����ҵ���������͵��ж�������ֻ�ж��Ƿ��������������ж�ʱ�����Ƿ����
			 * 4�����Ϊָ���ļ����������ж��Ƿ����ܸü������ͣ�ͬʱ��������������
			 * 
			 */
			String sqspbh = "����λ�޼�����������";

			if (("".equals(jbxxbForm.getJmlxdm()))
					|| (CodeConstant.JMLXNO.equals(jbxxbForm.getJmlxdm()))) {

				jbxxbForm.setSqspbh(sqspbh);

			} else if ((CodeConstant.JMLXOTHER.equals(jbxxbForm.getJmlxdm()))) {

				bf.delete(0, bf.length());

				bf
						.append(
								" select t1.sqspbh as sqspbh,t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
						.append(" where t1.jsjdm= ").append(
								SBStringUtils.getSQLStr(jbxxbForm.getJsjdm()))
						.append(" and t2.sqspbh=t1.sqspbh ").append(
								" and t2.jmsqsrq<= ").append(
								SBStringUtils.getSQLDate2(jbxxbForm
										.getSkssjsrq())).append(
								" and t2.jmszzrq>= ").append(
								SBStringUtils.getSQLDate2(jbxxbForm
										.getSkssjsrq())).append(
								" and t2.szsmdm='30' ").append(
								" order by t1.cjrq desc ");

				System.out.println("��ҵ����˰-��ѯ�������ͣ��Ƿ��м����ʸ�SQL");
				System.out.println(bf.toString());
				st = conn.createStatement();
				rs = st.executeQuery(bf.toString());

				if (rs.next()) {
					jm_type = true;
					sqspbh = rs.getString("sqspbh");
				}

				System.out.println("�����������:" + sqspbh);

				if (!jm_type) {
					jm_type = false;
				}
				// if (jm_type) {
				jbxxbForm.setSqspbh(sqspbh); // �����������
				// }

			} else if ((CodeConstant.JMLX9010.equals(jbxxbForm.getJmlxdm()))) {

				bf.delete(0, bf.length());

				bf
						.append(
								" select t1.sqspbh as sqspbh,t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
						.append(" where t1.jsjdm= ").append(
								SBStringUtils.getSQLStr(jbxxbForm.getJsjdm()))
						.append(" and t2.sqspbh=t1.sqspbh ").append(
								" and t2.szsmdm='30' ").append(
								" and  t1.jmslbdm= ").append(
								SBStringUtils.getSQLStr(jbxxbForm.getJmlxdm()))
						.append(" order by t1.cjrq desc ");

				System.out.println("��ҵ����˰-��ѯ�������ͣ��Ƿ��м����ʸ�SQL");
				System.out.println(bf.toString());
				st = conn.createStatement();
				rs = st.executeQuery(bf.toString());

				if (rs.next()) {
					jm_type = true;
					sqspbh = rs.getString("sqspbh");
				}

				System.out.println("�����������:" + sqspbh);

				jbxxbForm.setSqspbh(sqspbh); // �����������

				// 2007-04-16 ������¼�����ҵ�����϶��򣬸û�Ҳ�����и��¼�����ҵ����

				java.util.Date time = SfDateUtil.getDate(jbxxbForm.getSbnd()
						+ "0601");// ƥ��˰�ѽӿڵ�rq������������

				System.out.println("˰����ڲ��������������=" + jbxxbForm.getJsjdm());
				System.out.println("˰����ڲ������걨����=" + time);
				System.out.println("˰����ڲ�����˰��������ʼ����="
						+ SfDateUtil.getDate(jbxxbForm.getSkssksrq()));
				System.out.println("˰����ڲ�����˰��������������="
						+ SfDateUtil.getDate(jbxxbForm.getSkssjsrq()));

				QysdsSet sdsInfo = proxy.getQysdsInfo(jbxxbForm.getJsjdm(),
						time, SfDateUtil.getDate(jbxxbForm.getSkssksrq()),
						SfDateUtil.getDate(jbxxbForm.getSkssjsrq()),
						CodeConstant.SFGL_QYSDS_BBFS_NB);

				System.out.println("ȡ�õ�˰�ѽӿ���ϢsdsInfo="
						+ sdsInfo);
				
				// �ж����շ�ʽ
				if (sdsInfo != null) {
					/* ���¼�����ҵ�϶����� */
					Date gxqyrdrq = sdsInfo.getGxjsqy();

					System.out.println("ȡ�õ�˰�ѽӿ���Ϣgxqyrdrq="
							+ gxqyrdrq);
					
					if (gxqyrdrq != null) {

						jm_type = true;

					}
				}

				// 2007-04-16 ������¼�����ҵ�����϶��򣬸û�Ҳ�����и��¼�����ҵ����

				if (!jm_type) {
					jm_type = false;
				}

				if (!jm_type) {
					throw new ApplicationException(
							"ѡ��ļ���������˰����Ϣ�к˶��ļ������Ͳ�һ�£�����˰�˲����ܴ˼�������!��");
				}

			} else {

				bf.delete(0, bf.length());

				bf
						.append(
								" select t1.sqspbh as sqspbh, t1.jmslbdm as jmslbdm from spdb.sp_jl_ybnsdwjmsp t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
						.append(" where t1.jsjdm= ").append(
								SBStringUtils.getSQLStr(jbxxbForm.getJsjdm()))
						.append(" and t2.sqspbh=t1.sqspbh ").append(
								" and t2.jmsqsrq<= ").append(
								SBStringUtils.getSQLDate2(jbxxbForm
										.getSkssjsrq())).append(
								" and t2.jmszzrq>= ").append(
								SBStringUtils.getSQLDate2(jbxxbForm
										.getSkssjsrq())).append(
								" and t2.szsmdm='30' ").append(
								" and  t1.jmslbdm= ").append(
								SBStringUtils.getSQLStr(jbxxbForm.getJmlxdm()))
						.append(" order by t1.cjrq desc ");

				System.out.println("��ҵ����˰-������Ϣ������ʷ����SQL");
				System.out.println(bf.toString());

				rs = st.executeQuery(bf.toString());

				if (rs.next()) {
					jm_type = true;
					sqspbh = rs.getString("sqspbh");
				}

				System.out.println("�����������:" + sqspbh);

				if (!jm_type) {
					jm_type = false;
				}

				// if (jm_type) {
				jbxxbForm.setSqspbh(sqspbh); // �����������
				// }

				if (!jm_type) {
					throw new ApplicationException(
							"ѡ��ļ���������˰����Ϣ�к˶��ļ������Ͳ�һ�£�����˰�˲����ܴ˼�������!��");
				}

			}

			/**
			 * ������ʷ����
			 */

			bf.delete(0, bf.length());

			bf
					.append(" insert into sbdb.sb_jl_qysds_nsrjbxxb_his ")
					.append(
							" (XH,NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,DL,CYL,DE,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2) ")
					.append(" (select ")
					.append(TinyTools.getXh(jbxxbForm.getJsjdm()))
					.append(",")
					.append(
							" NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,DL,CYL,DE,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2 ")
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ").append(
							" where  t1.nsrjsjdm = ").append(
							SBStringUtils.getSQLStr(jbxxbForm.getJsjdm()))
					.append(" and t1.nd = ").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSknd()))
					.append(" ) ");

			System.out.println("��ҵ����˰-������Ϣ������ʷ����SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			/**
			 * ɾ������
			 */

			bf.delete(0, bf.length());

			bf.append(" delete ")

					// from
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ")
					// where
					.append(" where  t1.nsrjsjdm = ").append(
							SBStringUtils.getSQLStr(jbxxbForm.getJsjdm()))
					.append(" and t1.nd = ").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSknd()));

			System.out.println("��ҵ����˰-������Ϣɾ��SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			// ����form�еĹ��ʹ�����ʽ������ �������ƶȵ����Ƽ����Ӧ�ı���������
			if (CodeConstant.CWKJZD01.equals(jbxxbForm.getCwkjzddm())) {

				jbxxbForm.setCwkjzdmc(CodeConstant.CWKJZDMC01);

				// ����form�еĹ��ʹ�����ʽ������
				if (CodeConstant.GZGLXS01.equals(jbxxbForm.getGzglxsdm())) {

					jbxxbForm.setGzglxsmc(CodeConstant.GZGLXSMC01);
					jbxxbForm.setBbmsf(CodeConstant.BBMSF10);

				} else if (CodeConstant.GZGLXS03
						.equals(jbxxbForm.getGzglxsdm())) {

					jbxxbForm.setGzglxsmc(CodeConstant.GZGLXSMC03);
					jbxxbForm.setBbmsf(CodeConstant.BBMSF11);

				}

			} else if (CodeConstant.CWKJZD02.equals(jbxxbForm.getCwkjzddm())) {

				jbxxbForm.setCwkjzdmc(CodeConstant.CWKJZDMC02);

				// ����form�еĹ��ʹ�����ʽ������
				if (CodeConstant.GZGLXS01.equals(jbxxbForm.getGzglxsdm())) {

					jbxxbForm.setGzglxsmc(CodeConstant.GZGLXSMC01);
					jbxxbForm.setBbmsf(CodeConstant.BBMSF20);

				} else if (CodeConstant.GZGLXS03
						.equals(jbxxbForm.getGzglxsdm())) {

					jbxxbForm.setGzglxsmc(CodeConstant.GZGLXSMC03);
					jbxxbForm.setBbmsf(CodeConstant.BBMSF21);

				}

			} else if (CodeConstant.CWKJZD03.equals(jbxxbForm.getCwkjzddm())) {

				jbxxbForm.setCwkjzdmc(CodeConstant.CWKJZDMC03);

				// ����form�еĹ��ʹ�����ʽ������
				if (CodeConstant.GZGLXS01.equals(jbxxbForm.getGzglxsdm())) {

					jbxxbForm.setGzglxsmc(CodeConstant.GZGLXSMC01);
					jbxxbForm.setBbmsf(CodeConstant.BBMSF30);

				} else if (CodeConstant.GZGLXS03
						.equals(jbxxbForm.getGzglxsdm())) {

					jbxxbForm.setGzglxsmc(CodeConstant.GZGLXSMC03);
					jbxxbForm.setBbmsf(CodeConstant.BBMSF31);

				}

			}

			// ����form�еļ�����ҵ����˰�����͵�����
			if ("".equals(jbxxbForm.getJmlxdm())
					|| CodeConstant.JMLXNO.equals(jbxxbForm.getJmlxdm())) {

				jbxxbForm.setJmlxmc(CodeConstant.JMLXMCNO);

			} else if (CodeConstant.JMLXOTHER.equals(jbxxbForm.getJmlxdm())) {

				jbxxbForm.setJmlxmc(CodeConstant.JMLXMCOTHER);

			} else if (CodeConstant.JMLX9010.equals(jbxxbForm.getJmlxdm())) {

				jbxxbForm.setJmlxmc(CodeConstant.JMLXMC9010);

			} else if (CodeConstant.JMLX9020.equals(jbxxbForm.getJmlxdm())) {

				jbxxbForm.setJmlxmc(CodeConstant.JMLXMC9020);

			} else if (CodeConstant.JMLX9030.equals(jbxxbForm.getJmlxdm())) {

				jbxxbForm.setJmlxmc(CodeConstant.JMLXMC9030);

			} else if (CodeConstant.JMLX9090.equals(jbxxbForm.getJmlxdm())) {

				jbxxbForm.setJmlxmc(CodeConstant.JMLXMC9090);

			} else if (CodeConstant.JMLX9070.equals(jbxxbForm.getJmlxdm())) {

				jbxxbForm.setJmlxmc(CodeConstant.JMLXMC9070);

			}

			/**
			 * �����޸ĵ�����
			 */

			bf.delete(0, bf.length());

			bf
					.append(" insert into sbdb.sb_jl_qysds_nsrjbxxb ")
					.append(
							" (NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2) ")
					// ���������
					.append(" values(").append(
							SBStringUtils.getSQLStr(jbxxbForm.getJsjdm()))
					// ��˰��ʶ���
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getNsrsbh()))
					// ��˰������
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getNsrmc()))
					// �汾��
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getVersion()))
					// ˰�����
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSknd()))
					// �걨���
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSbnd()))
					// �����������
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSqspbh()))
					// �����������ʹ���
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSsjjlxdm()))
					// ����������������
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSsjjlxmc()))
					// ��ϵ�绰
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getLxdh()))
					// ��Ӫ��ַ
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getJydz()))
					// ������ҵ����
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSshydm()))
					// ������ҵ����
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSshymc()))
					// ���շ�ʽ����
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getZsfsdm()))
					// ���շ�ʽ����
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getZsfsmc()))
					// �ƻ��ƶȴ���
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getCwkjzddm()))
					// �ƻ��ƶ�����
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getCwkjzdmc()))
					// ���ʹ�����ʽ����
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getGzglxsdm()))
					// ���ʹ�����ʽ����
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getGzglxsmc()))
					// �������ʹ���
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getJmlxdm()))
					// ������������
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getJmlxmc()))
					// ������������˰��ֿ۷���
					.append(",").append("''")
					// ������������˰��ֿ۷�������
					.append(",").append("''")
					// ˰�������֯��������
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSwjgzzjgdm()))
					// ˰�������֯��������
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSwjgzzjgmc()))
					// ������
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getCjr()))
					// ����ʱ��
					.append(",").append(
							SBStringUtils.getSQLDate2(jbxxbForm.getCjrq()))
					// ¼����
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getLrr()))
					// ¼��ʱ��
					.append(",").append(
							SBStringUtils.getSQLDate2(jbxxbForm.getLrrq()))
					// ϵͳ����
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getXtjb()))
					// ����������
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getBbmsf()))
					// ˰������ʱ����
					.append(",").append(
							SBStringUtils.getSQLDate2(jbxxbForm.getSkssksrq()))
					// ˰������ʱ��ֹ
					.append(",").append(
							SBStringUtils.getSQLDate2(jbxxbForm.getSkssjsrq()))
					// ��ע1
					.append(",").append("''")
					// ��ע2
					.append(",").append("''")

					.append(") ");

			System.out.println("��ҵ����˰-������Ϣ����SQL\n");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			/**
			 * TODO �������ƶ������޸��ˣ���Ҫɾ����Ӧ���걨��
			 */
			if (!(jbxxbForm.getCwkjzddm().equals(jbxxbForm.getCwkjzddm_old()))) {

				QysdsReportsDeclare qd = new QysdsReportsDeclare();
				qd = this.setQysdsReport(jbxxbForm);

				// ��ҵ����˰�����ڵ�����������
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();

				// ԭѡ��Ϊ��ҵ��С��ҵ������ƶ�
				if (CodeConstant.CWKJZD01.equals(jbxxbForm.getCwkjzddm_old())) {
					// �������һ1
					table.setTableId(CodeConstant.TABLE_ID_SRBYBQY);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

					// ��������1
					table.setTableId(CodeConstant.TABLE_ID_CBMXBYBQY);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

					// �������ҵ��С��ҵ������ƶ�תΪ������ҵ�������ʮ��1
					if (CodeConstant.CWKJZD02.equals(jbxxbForm.getCwkjzddm())) {

						table.setTableId(CodeConstant.TABLE_ID_HZSSMXB);
						table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
						// set table
						qd.getTableContentList().put(table.getTableId(), table);
						// ����delete������������ɾ��
						iApp.deleteSingleTable(qd);
					}
				}

				// ԭѡ��Ϊ������ҵ����ƶ�
				if (CodeConstant.CWKJZD02.equals(jbxxbForm.getCwkjzddm_old())) {
					// �������һ2
					table.setTableId(CodeConstant.TABLE_ID_SRBJRQY);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

					// ��������2
					table.setTableId(CodeConstant.TABLE_ID_CBMXBJRQY);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

					// �������ʮ��2
					table.setTableId(CodeConstant.TABLE_ID_DZZBJTMXB);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

					// �������ʮ��3
					table.setTableId(CodeConstant.TABLE_ID_BXZBJ);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);
				}

				// ԭѡ��Ϊ��ҵ��λ��������塢������ҵ��λ����ƶ�
				if (CodeConstant.CWKJZD03.equals(jbxxbForm.getCwkjzddm_old())) {
					// �������һ3
					table.setTableId(CodeConstant.TABLE_ID_SRBSYDW);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

					// ��������3
					table.setTableId(CodeConstant.TABLE_ID_CBMXBSYDW);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

					// �������ҵ��λ��������塢������ҵ��λ����ƶ�תΪ������ҵ�������ʮ��1
					if (CodeConstant.CWKJZD02.equals(jbxxbForm.getCwkjzddm())) {
						// �������ʮ��1
						table.setTableId(CodeConstant.TABLE_ID_HZSSMXB);
						table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
						// set table
						qd.getTableContentList().put(table.getTableId(), table);
						// ����delete������������ɾ��
						iApp.deleteSingleTable(qd);
					}

				}

			}

			/**
			 * TODO ������ʹ�����ʽ�����޸��ˣ���Ҫɾ����Ӧ���걨��
			 */
			if (!(jbxxbForm.getGzglxsdm().equals(jbxxbForm.getGzglxsdm_old()))) {

				QysdsReportsDeclare qd = new QysdsReportsDeclare();
				qd = this.setQysdsReport(jbxxbForm);
				// ��ҵ����˰�����ڵ�����������
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();

				// ԭ��ѡ��ǹ�Ч�ҹ� (��˰���� ȫ��۳�) ����ѡ��Ч�ҹ�
				if ((CodeConstant.GZGLXS01.equals(jbxxbForm.getGzglxsdm_old()))
						&& (CodeConstant.GZGLXS03.equals(jbxxbForm
								.getGzglxsdm()))) {
					// ����ǹ�Ч�ı� ����ʮ��
					table.setTableId(CodeConstant.TABLE_ID_GZXJMXB_FGX);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

				}

				// ԭ��ѡ��Ч�ҹ� ����ѡ��ǹ�Ч�ҹ� (��˰���� ȫ��۳�)
				if ((CodeConstant.GZGLXS01.equals(jbxxbForm.getGzglxsdm()))
						&& (CodeConstant.GZGLXS03.equals(jbxxbForm
								.getGzglxsdm_old()))) {
					// �����Ч�ı� ����ʮ��
					table.setTableId(CodeConstant.TABLE_ID_TABLE_ID_GZXJMXB_GX);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

				}

			}

			/**
			 * TODO ����������������޸��ˣ���Ҫɾ����Ӧ���걨��
			 */

			if (!(jbxxbForm.getJmlxdm().equals(jbxxbForm.getJmlxdm_old()))) {

				/**
				 * ��������߼��������ݣ������������Ҫ����¼��
				 * 
				 */

				QysdsReportsDeclare qd = new QysdsReportsDeclare();
				qd = this.setQysdsReport(jbxxbForm);
				// ��ҵ����˰�����ڵ�����������
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();

				// ��������߼��������ݣ������������Ҫ����¼��
				table.setTableId(CodeConstant.TABLE_ID_MSSDMXB);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				// set table
				qd.getTableContentList().put(table.getTableId(), table);
				// ����delete������������ɾ��
				iApp.deleteSingleTable(qd);

			}

			/**
			 * ��������ԭʼֵ
			 */
			jbxxbForm.setCwkjzddm_old(jbxxbForm.getCwkjzddm());
			jbxxbForm.setGzglxsdm_old(jbxxbForm.getGzglxsdm());
			jbxxbForm.setJmlxdm_old(jbxxbForm.getJmlxdm());

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw new ApplicationException(ex.getMessage());
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return jbxxbForm;
	}

	// /**
	// * doDelete ����ɾ��ҳ���ύ���꾡������Ϣ
	// *
	// * @param vo
	// * ҵ�����
	// * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	// * @throws BaseException
	// * ��������������׳��쳣��Ϣ
	// */
	//
	// private Object doDelete(VOPackage vo) throws BaseException {
	//
	// JbxxbForm jbxxbForm = (JbxxbForm) vo.getData();
	//
	// Connection conn = null;
	// SfDBAccess dbAgent = null;
	// UserData ud = (UserData) vo.getUserData();
	//
	// try {
	// // conn = SfDBResource.getConnection();
	// // dbAgent = new SfDBAccess(conn);
	// //
	// // jbxxbForm.setNd(getSbnd(jbxxbForm.getSbrq()));
	// //
	// // // ɾ��������ʷ����˰����
	// // try {
	// // String strWhere = " jsjdm='" + jbxxbForm.getJsjdm() + "'"
	// // + " and qxdm='" + jbxxbForm.getQxdm() + "'" + " and nd='"
	// // + jbxxbForm.getNd() + "'";
	// // // ɾ����Ӫ���ɷ�����
	// // dbAgent.delete(strWhere, new Lygf());
	// //
	// // // ɾ����ҵ����˰�걨����
	// // dbAgent.delete(strWhere, new Qysdsnb());
	// // // ��¼ɾ����־
	// // TinyTools.makeLog4Qysds(ud, jbxxbForm.getJsjdm(), "��ҵ����˰�걨");
	// // } catch (BaseException ex1) {
	// // // �׳��쳣
	// // ex1.printStackTrace();
	// // new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
	// // }
	// //
	// // // �������ó�ʼ������
	// // initForm(jbxxbForm);
	// // // ����Ѽ�������
	// // jbxxbForm.getDataList().clear();
	// } catch (Exception ex) {
	// // �׳��쳣
	// ex.printStackTrace();
	// throw new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
	// } finally {
	// SfDBResource.freeConnection(conn);
	// }
	// return jbxxbForm;
	// }

	/**
	 * ��ʼ��
	 * 
	 * @param jbxxbForm
	 *            ��������
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private void initForm(JbxxbForm jbxxbForm) throws BaseException {

	}

	// /**
	// * �����걨����ȡ�õ�ǰǰ��0101-1231
	// *
	// * @param curSbrq
	// * �걨����
	// * @return dateMap
	// */
	// public Map getSbrl(String curSbrq) {
	// Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
	// GregorianCalendar calendar = new GregorianCalendar();
	// calendar.setTime(sbrq);
	// calendar.add(calendar.YEAR, -1);
	// int year = calendar.get(calendar.YEAR);
	// String nd = new Integer(year).toString();
	// Timestamp ksrq;
	// Timestamp jsrq;
	// Map retMap = new HashMap();
	// ksrq = new Timestamp(new GregorianCalendar(year, 0, 1).getTime()
	// .getTime());
	// jsrq = new Timestamp(new GregorianCalendar(year, 11, 31).getTime()
	// .getTime());
	//
	// Map dateMap = new HashMap();
	// dateMap.put("ksrq", ksrq);
	// dateMap.put("jsrq", jsrq);
	// return dateMap;
	// }

	/**
	 * ���ñ�����������Ϣ
	 * 
	 * @param report
	 * @param form
	 */
	public QysdsReportsDeclare setQysdsReport(JbxxbForm form) {

		QysdsReportsDeclare report = new QysdsReportsDeclare();
		/**
		 * ������Ϣ
		 */
		Jbxx jbxx = new Jbxx();

		/**
		 * ������Ϣ(JBXX)-���������
		 */
		jbxx.setJsjdm(form.getJsjdm());
		/**
		 * ������Ϣ(JBXX)-��˰������
		 */
		jbxx.setNsrmc(form.getNsrmc());
		/**
		 * ������Ϣ(JBXX)-������������
		 */
		jbxx.setSsjjlx(form.getSsjjlxdm());
		/**
		 * ������Ϣ(JBXX)-��ϵ�绰
		 */
		jbxx.setLxdh(form.getLxdh());
		/**
		 * ������Ϣ(JBXX)-������ҵ
		 */
		jbxx.setSshy(form.getSshydm());
		/**
		 * ������Ϣ(JBXX)-���շ�ʽ
		 */
		jbxx.setZsfs(form.getZsfsdm());
		/**
		 * ������Ϣ(JBXX)-�ƻ��ƶ�
		 */
		jbxx.setCkzd(form.getCwkjzddm());
		/**
		 * ������Ϣ(JBXX)-���ʹ�����ʽ
		 */
		jbxx.setGzglxs(form.getGzglxsdm());
		/**
		 * ������Ϣ(JBXX)-��������
		 */
		jbxx.setJmlx(form.getJmlxdm());

		report.setJbxx(jbxx);

		/**
		 * Ӧ�ñ��
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYSDS);
		/**
		 * �汾��
		 */
		report.setVersion("20060101");
		/**
		 * ��˰�˼��������
		 */
		report.setNsrjsjdm(form.getJsjdm());
		/**
		 * ��˰������
		 */
		report.setNsrmc(form.getNsrmc());
		/**
		 * ����������
		 */
		report.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		/**
		 * �ں�
		 */
		report.setQh("1");
		/**
		 * ˰��������ʼ����
		 */
		if (form.getSkssksrq() != null && !form.getSkssksrq().equals("")) {
			report.setSkssksrq(new Date(TinyTools.stringToDate(
					form.getSkssksrq(), "yyyyMMdd").getTime()));
		}
		/**
		 * ˰��������������
		 */
		if (form.getSkssjsrq() != null && !form.getSkssjsrq().equals("")) {
			report.setSkssjsrq(new Date(TinyTools.stringToDate(
					form.getSkssjsrq(), "yyyyMMdd").getTime()));
		}
		/**
		 * �걨����
		 */
		if (form.getLrrq() != null && !form.getLrrq().equals("")) {
			report.setSbrq(new Date(TinyTools.stringToDate(form.getLrrq(),
					"yyyyMMdd").getTime()));
		}
		/**
		 * ˰�����
		 */
		report.setSknd(form.getSknd());
		/**
		 * ˰�������֯��������
		 */
		report.setSwjgzzjgdm(form.getSwjgzzjgdm());
		/**
		 * ���ش���
		 */
		report.setQxdm(form.getSwjgzzjgdm().substring(2, 4));
		/**
		 * ¼����
		 */
		report.setLrr(form.getLrr());
		/**
		 * ¼������
		 */
		if (form.getLrrq() != null && !form.getLrrq().equals("")) {
			report.setLrrq(new Date(TinyTools.stringToDate(form.getLrrq(),
					"yyyyMMdd").getTime()));
		}
		/**
		 * ������
		 */
		report.setCjr(form.getLrr());
		/**
		 * ��������
		 */
		if (form.getCjrq() != null && !form.getCjrq().equals("")) {
			report.setCjrq(new Date(TinyTools.stringToDate(form.getCjrq(),
					"yyyyMMdd").getTime()));
		}

		return report;
	}

}
