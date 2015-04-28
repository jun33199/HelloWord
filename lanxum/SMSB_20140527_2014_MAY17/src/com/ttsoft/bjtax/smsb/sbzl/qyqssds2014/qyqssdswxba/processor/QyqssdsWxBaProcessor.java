package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.syax.creports.Constants;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.web.QyqssdsWxBaForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsActionHelper;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsWxBaProcessor implements Processor {

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
		case 7:
			result = doDelete(vo);
			break;
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

		QyqssdsWxBaForm qyqssdsWxBaForm = (QyqssdsWxBaForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();

		qyqssdsWxBaForm.setCjr(ud.yhid);
		qyqssdsWxBaForm.setLrr(ud.yhid);
		// qyqssdsWxBaForm.setBaShztMessage("����������������в�ѯ��");
		return qyqssdsWxBaForm;
	}

	/**
	 * ���ݼ���������ѯ������Ϣ
	 * 
	 * @param vo
	 * @return
	 * @throws BaseException
	 * @throws com.syax.creports.exception.BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException {

		QyqssdsWxBaForm requestForm = (QyqssdsWxBaForm) vo.getData();
		QyqssdsWxBaForm form = new QyqssdsWxBaForm();

		UserData ud = (UserData) vo.getUserData();

		SWDJJBSJ djsj = null;

		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(requestForm.getJsjdm(), ud);
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		// ��������Ȩ�޹���
		QyqssdsUtil2014.getAlertStrWhenAdd(requestForm.getJsjdm(), ud);
		// ���������
		form.setJsjdm(requestForm.getJsjdm());
		// ��˰������
		form.setNsrmc(djsj.getNsrmc());
		// ��˰��ʶ���
		form.setNsrsbh(djsj.getSwdjzh());
		//form.setLxdh(djsj.getZcdzlxdh());
		/*-���¼���Ĭ����ѡ���-*/
		
		form.setCzlx(requestForm.getCzlx());
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();
			String sql = "SELECT * FROM  SBDB.SB_JL_QYQSSDS_WXBADJB WHERE JSJDM='"
					+ requestForm.getJsjdm() + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// ���������
				form.setJsjdm(rs.getString("JSJDM") == null ? "" : rs
						.getString("JSJDM"));
				// ��˰��ʶ���
				form.setNsrsbh(rs.getString("NSRSBH") == null ? "" : rs
						.getString("NSRSBH"));
				// ��˰������
				form.setNsrmc(rs.getString("NSRMC") == null ? "" : rs
						.getString("NSRMC"));
				// �������
				form.setQsbaksrq(rs.getString("TBRQ").substring(0, 10) == null ? ""
						: rs.getString("TBRQ").substring(0, 10));
				form.setSfwxjxba(rs.getString("SFWXJXBA") == null ? "" : rs
						.getString("SFWXJXBA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}

		return form;
	}

	/**
	 * ���汸����Ϣ
	 * 
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		QyqssdsWxBaForm form = (QyqssdsWxBaForm) vo.getData();
		UserData ud = (UserData) vo.getUserData();
		SWDJJBSJ djsj = null;
		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		Connection conn = null;
		int count = 0;
		try {			
			conn = SfDBResource.getConnection();
			StringBuffer bf = new StringBuffer();
			/**
			 * ������ʷ��
			 */
			Statement stmt = conn.createStatement();
			bf.delete(0, bf.length());
			bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDS_WXBADJB_HIS ")
					.append("(xh,jsjdm,nsrsbh,nsrmc,SSJJLX,SSJJLXMC,LXDH,"
							+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,SFWXJXBA,CJR,CJRQ,LRR,LRRQ,"
							+ "TBRQ) ")
					.append(" (select ").append(TinyTools.getXh(form.getJsjdm()))
					.append(" ,jsjdm,nsrsbh,nsrmc,SSJJLX,"
							+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
							+ "SWJGZZJGMC,SFWXJXBA,CJR,CJRQ,LRR,LRRQ,"
							+ "TBRQ ")
					.append(" from  SBDB.SB_JL_QYQSSDS_WXBADJB t1 ")
					.append(" where  t1.jsjdm = ")
					.append(SBStringUtils.getSQLStr(form.getJsjdm())).append(")");

			System.out.println("��ҵ��������˰-������Ϣ������ʷ����SQL");
			System.out.println(bf.toString());

			stmt.executeUpdate(bf.toString());
			if(stmt!=null){
				stmt.close();
			}
			
			/**
			 * ɾ������
			 */

			Statement st = conn.createStatement();

			bf.delete(0, bf.length());
			bf.append(" delete ")
					.append(" from  SBDB.SB_JL_QYQSSDS_WXBADJB t1 ")
					.append(" where  t1.jsjdm ='").append(form.getJsjdm())
					.append("'");

			System.out.println("��ҵ����˰-������Ϣɾ��SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());


			if (st != null) {
				st.close();
			}

			// ������ʷ����
			String sql = "insert into  SBDB.SB_JL_QYQSSDS_WXBADJB(jsjdm,"
					+ "nsrsbh,"
					+ "nsrmc,"
					+ "SSJJLX,"
					+ "SSJJLXMC,"
					+ "LXDH,"
					+ "JYDZ,"
					+ "SSHY,"
					+ "SSHYMC,"
					+ "SWJGZZJGDM,"
					+ "SWJGZZJGMC,"
					+ "SFWXJXBA,"
					+ "CJR,"
					+ "CJRQ,"
					+ "LRR,"
					+ "LRRQ,"
					+ "TBRQ"					
					+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,sysdate)";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, form.getJsjdm());// ���������
			pst.setString(i++, djsj.getSwdjzh());// ��˰��ʶ���
			pst.setString(i++, djsj.getNsrmc());// ��˰������
			pst.setString(i++, djsj.getDjzclxdm());// ������������
			pst.setString(i++, djsj.getDjzclxmc());// ����������������
			pst.setString(i++, djsj.getZcdzlxdh());// ��ϵ�绰
			pst.setString(i++, djsj.getJydz());// ��Ӫ��ַ
			pst.setString(i++, djsj.getGjbzhydm());// ������ҵ
			pst.setString(i++, djsj.getGjbzhymc());// ������ҵ����
			pst.setString(i++, djsj.getSwjgzzjgdm());// ˰�������֯��������
			pst.setString(i++, djsj.getSwjgzzjgmc());// ˰�������֯��������
			pst.setString(i++, form.getSfwxjxba());// ��ϵ�绰
			pst.setString(i++, ud.yhid);// ������
			pst.setString(i++, ud.yhid);// ¼����
			
			System.out.println(sql);
			count = pst.executeUpdate();
			if (pst != null) {
				pst.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			
			SfDBResource.freeConnection(conn);
		}

		return form;
	}


	/**
	 * doDeleteɾ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	private Object doDelete(VOPackage vo) throws BaseException {

		QyqssdsWxBaForm qyqssdsWxBaForm = (QyqssdsWxBaForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();

		Connection conn = null;
		Statement stmt = null;
		Statement st = null;
		conn = SfDBResource.getConnection();
		// ��ȡ���������
		String jsjdm = qyqssdsWxBaForm.getJsjdm();			
		/**
		 * ɾ������
		 */
		StringBuffer bf = new StringBuffer();

		String sql = "DELETE FROM  SBDB.SB_JL_QYQSSDS_WXBADJB  WHERE JSJDM='"
				+ jsjdm+"'";
		try {

			// ��ȡ���������ݿ�ӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			QyqssdsUtil2014.setQyqssdsReport(qd, qyqssdsWxBaForm);

			// ѭ��ɾ���ͻ�����д�����б��������ӵķ����
			for (int i = 0; i < CodeConstant.QYQSSDS_TABLE_ID_ALL.length; i++) {
				// ��ҵ����˰�����ڵ�����������
				QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
				table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_ALL[i]);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
				// set table
				qd.setTableContentList(new HashMap());
				qd.getTableContentList().put(table.getTableId(), table);
				// ����delete������������ɾ��
				iApp.deleteSingleTable(qd);
				iApp.updateCheckStatus(qd, "");
			}
			stmt = conn.createStatement();
			bf.delete(0, bf.length());
			/*---�޸���ʷ����¼����Ϊ��ǰ�����ˣ��޸ı������״̬��ʶΪ6�����ϣ�---*/
			/*--modified by huohb 2014-06-18--*/
			// ������ʷ����
			bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDS_WXBADJB_HIS ")
			.append("(xh,jsjdm,nsrsbh,nsrmc,SSJJLX,SSJJLXMC,LXDH,"
					+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,SFWXJXBA,CJR,CJRQ,LRR,LRRQ,"
					+ "TBRQ) ")
			.append(" (select ").append(TinyTools.getXh(jsjdm))
			.append(" ,jsjdm,nsrsbh,nsrmc,SSJJLX,"
					+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
					+ "SWJGZZJGMC,SFWXJXBA,CJR,CJRQ,LRR,LRRQ,"
					+ "TBRQ ")
			.append(" from  SBDB.SB_JL_QYQSSDS_WXBADJB t1 ")
			.append(" where  t1.jsjdm = ")
			.append(SBStringUtils.getSQLStr(jsjdm)).append(")");

			System.out.println("��ҵ��������˰-������Ϣ������ʷ����SQL");
			System.out.println(bf.toString());
			/*--�Ȳ�����ʷ����ɾ������--*/
			stmt.executeUpdate(bf.toString());
			// ɾ������
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}
		return qyqssdsWxBaForm;
	}
}
