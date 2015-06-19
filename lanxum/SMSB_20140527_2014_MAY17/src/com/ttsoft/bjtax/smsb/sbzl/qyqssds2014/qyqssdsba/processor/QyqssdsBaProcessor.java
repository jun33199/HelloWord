package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor;

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

public class QyqssdsBaProcessor implements Processor {

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
		// ��������
		case 5:
			result = doAccept(vo);
			break;
		// �ܾ�����
		case 6:
			result = doRefuse(vo);
			break;
		// ɾ��
		case 7:
			result = doDelete(vo);
			break;
		// ����
		case 8:
			result = doCancle(vo);
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

		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();

		qyqssdsBaForm.setCjr(ud.yhid);
		qyqssdsBaForm.setLrr(ud.yhid);
		// qyqssdsBaForm.setBaShztMessage("����������������в�ѯ��");
		return qyqssdsBaForm;
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

		QyqssdsBaForm requestForm = (QyqssdsBaForm) vo.getData();
		QyqssdsBaForm form = new QyqssdsBaForm();

		UserData ud = (UserData) vo.getUserData();

		SWDJJBSJ djsj = null;

		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ(requestForm.getJsjdm(), ud);
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
		form.setJyqxjm("N");
		form.setGdjyjs("N");
		form.setYfdxgb("N");
		form.setYfxgpc("N");
		form.setYfgdqs("N");
		form.setQtyy("N");
		form.setCzlx(requestForm.getCzlx());
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();
			String sql = "SELECT *FROM SBDB.SB_JL_QYQSSDSBA_NSRJBXXB WHERE NSRJSJDM='"
					+ requestForm.getJsjdm() + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// ���������
				form.setJsjdm(rs.getString("NSRJSJDM") == null ? "" : rs
						.getString("NSRJSJDM"));
				// ��˰��ʶ���
				form.setNsrsbh(rs.getString("NSRSBH") == null ? "" : rs
						.getString("NSRSBH"));
				// ��˰������
				form.setNsrmc(rs.getString("NSRMC") == null ? "" : rs
						.getString("NSRMC"));
				// ������ʼ����
				form.setQsbaksrq(rs.getString("QSBAKSRQ").substring(0, 10) == null ? ""
						: rs.getString("QSBAKSRQ").substring(0, 10));
				// �����˻�������������Ա
				form.setQsllry(rs.getString("QSLLRY") == null ? "" : rs
						.getString("QSLLRY"));
				// ��ϵ�绰
				form.setLxdh(rs.getString("LXDH") == null ? "" : rs
						.getString("LXDH"));
				// ��ҵ�³̹涨�ľ�Ӫ���޽���
				form.setJyqxjm(rs.getString("JYQXJM") == null ? "" : rs
						.getString("JYQXJM"));
				// ��ҵ�ɶ��ᡢ�ɶ��������ƻ��������ɢ
				form.setGdjyjs(rs.getString("GDJYJS") == null ? "" : rs
						.getString("GDJYJS"));
				// ��ҵ����������Ӫҵִ�ա�����رջ��߱�����
				form.setYfdxgb(rs.getString("YFDXGB") == null ? "" : rs
						.getString("YFDXGB"));
				// ��ҵ������Ժ�������Խ�ɢ�������Ʋ�
				form.setYfxgpc(rs.getString("YFXGPC") == null ? "" : rs
						.getString("YFXGPC"));
				// �йط��ɡ���������涨����
				form.setYfgdqs(rs.getString("YFGDQS") == null ? "" : rs
						.getString("YFGDQS"));
				// ��ҵ������ԭ���ɢ���������
				form.setQtyy(rs.getString("QTYY") == null ? "" : rs
						.getString("QTYY"));
				// ��������:0,���ϣ�1,����
				form.setSqlx(rs.getString("SQLXDM") == null ? "" : rs
						.getString("SQLXDM"));
				String bashztbs = "";
				String shztts = rs.getString("BASHZTBS") == null ? "" : rs
						.getString("BASHZTBS");
				form.setBaShztbs(shztts);
				if (!"".equals(shztts)) {
					int key = Integer.parseInt(shztts);
					switch (key) {
					case 1:
						bashztbs = "���ύδ���";
						break;
					case 2:
						bashztbs = "�����ͨ��";
						break;
					case 3:
						bashztbs = "��˱�����";
						break;
					case 4:
						bashztbs = "����";
						break;

					default:
						break;
					}
				}
				// ���㱸�����״̬��ʶ 1�����ύδ��ˣ�2�������ͨ����3����˱����أ�4������
				form.setBaShztMessage(bashztbs);
				// �����걨���״̬��ʶ 1�����ύδ��ˣ�2�������ͨ����3����˱����أ�4������
				String sbShztbs = rs.getString("SBSHZTBS") == null ? "" : rs
						.getString("SBSHZTBS");
				form.setSbShztbs(sbShztbs);
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
		QyqssdsBaForm form = (QyqssdsBaForm) vo.getData();
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
			bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_HIS ")
					.append("(xh,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,SSJJLXMC,LXDH,"
							+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,"
							+ "BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
							+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					.append(" (select ").append(TinyTools.getXh(form.getJsjdm()))
					.append(" ,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,"
							+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
							+ "SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,"
							+ "REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
							+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,"
							+ "SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
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
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm ='").append(form.getJsjdm())
					.append("'");

			System.out.println("��ҵ����˰-������Ϣɾ��SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());


			if (st != null) {
				st.close();
			}
			// ������ʷ����
			String sql = "insert into SBDB.SB_JL_QYQSSDSBA_NSRJBXXB(nsrjsjdm,"
					+ "nsrsbh,"
					+ "nsrmc,"
					+ "version,"
					+ "SSJJLX,"
					+ "SSJJLXMC,"
					+ "LXDH,"
					+ "JYDZ,"
					+ "SSHY,"
					+ "SSHYMC,"
					+ "SWJGZZJGDM,"
					+ "SWJGZZJGMC,"
					+ "CJR,"
					+ "CJSJ,"
					+ "LRR,"
					+ "LRSJ,"
					+ "XTJB,"
					+ "BBMSF,"
					+ "REMARK1,"
					+ "REMARK2,"
					+ "QSLLRY,"
					+ "TBRQ,"
					+ "QSBAKSRQ,"
					+ "QSBAJSRQ,"
					+ "BASHZTBS,"
					+ "BASHTGRQ,"
					+ "SBSHZTBS,"
					+ "SBSHTGRQ,"
					+ "JYQXJM,"
					+ "GDJYJS,"
					+ "YFDXGB,"
					+ "YFXGPC,"
					+ "YFGDQS,"
					+ "QTYY,"
					+ "SQLXDM,"
					+ "QSSBKSRQ,"
					+ "QSSBJSRQ"
					+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?,?,?,?,sysdate,sysdate,sysdate,?,sysdate,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, form.getJsjdm());// ���������
			pst.setString(i++, djsj.getSwdjzh());// ��˰��ʶ���
			pst.setString(i++, djsj.getNsrmc());// ��˰������
			pst.setString(i++, CodeConstant.QYQSSDS_VERSION_2014);// �汾��
			pst.setString(i++, djsj.getDjzclxdm());// ������������
			pst.setString(i++, djsj.getDjzclxmc());// ����������������
			pst.setString(i++, form.getLxdh());// ��ϵ�绰
			pst.setString(i++, djsj.getJydz());// ��Ӫ��ַ
			pst.setString(i++, djsj.getGjbzhydm());// ������ҵ
			pst.setString(i++, djsj.getGjbzhymc());// ������ҵ����
			pst.setString(i++, djsj.getSwjgzzjgdm());// ˰�������֯��������
			pst.setString(i++, djsj.getSwjgzzjgmc());// ˰�������֯��������
			pst.setString(i++, ud.yhid);// ������
			pst.setString(i++, ud.yhid);// ¼����
			pst.setString(i++, null);// ϵͳ����
			pst.setString(i++, "0101,0102,0103,0104");// ����������
			pst.setString(i++, null);// ��ע1
			pst.setString(i++, null);// ��ע2
			pst.setString(i++, form.getQsllry());// ����������Ա
			pst.setString(i++, "2");// ���㱸�����״̬��ʶ 1�����ύδ��ˣ�2�������ͨ����3����˱����أ�4������
			pst.setString(i++, null);// �����걨���״̬��ʶ 1�����ύδ��ˣ�2�������ͨ����3����˱����أ�4������
			pst.setString(i++, null);// �����걨���ͨ������
			pst.setString(i++, form.getJyqxjm());// ��Ӫ���޽��� Y ���ǣ�N����
			pst.setString(i++, form.getGdjyjs());// ��������ɢ Y ���ǣ�N����
			pst.setString(i++, form.getYfdxgb());// ���������ر� Y ���ǣ�N����
			pst.setString(i++, form.getYfxgpc());// ���������Ʋ� Y ���ǣ�N����
			pst.setString(i++, form.getYfgdqs());// �����涨���� Y ���ǣ�N����
			pst.setString(i++, form.getQtyy());// ����ԭ�� Y ���ǣ�N����
			pst.setString(i++, "1");// �������ʹ��룬0���������룬1����������
			pst.setString(i++, null);// �����걨��ʼ����
			pst.setString(i++, null);// �����걨��������

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
	 * doAccept��������
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	private Object doAccept(VOPackage vo) throws BaseException {

		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) vo.getData();
		// ��ȡ���������
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = SfDBResource.getConnection();
		String showSql="select NSRJSJDM from sbdb.sb_jl_qyqssdsba_nsrjbxxb  where nsrjsjdm=?";
		
		try {
			ps = conn.prepareStatement(showSql);
			ps.setString(1, qyqssdsBaForm.getJsjdm());
			rs = ps.executeQuery();	
		
		if(rs.next()){
			String jsjdm = qyqssdsBaForm.getJsjdm();
			String sql = "UPDATE SBDB.SB_JL_QYQSSDSBA_NSRJBXXB SET BASHZTBS='2',BASHTGRQ=SYSDATE WHERE NSRJSJDM=?";
					
			try {
				conn = SfDBResource.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, qyqssdsBaForm.getJsjdm());
				rs = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
				throw ExceptionUtil.getBaseException(e);
			}finally{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			}
			
		}else{
			UserData ud = (UserData) vo.getUserData();
			SWDJJBSJ djsj = null;
			// �����ҵ�Ǽǻ�����Ϣ
			try {
				djsj = InterfaceDj.getJBSJ_New(qyqssdsBaForm.getJsjdm(), ud);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw ExceptionUtil.getBaseException(e);
			}

			int count = 0;
			try {
				//conn = SfDBResource.getConnection();
				StringBuffer bf = new StringBuffer();
				/**
				 * ������ʷ��
				 */
				Statement stmt = conn.createStatement();
				bf.delete(0, bf.length());
				bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_HIS ")
						.append("(xh,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,SSJJLXMC,LXDH,"
								+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,"
								+ "BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
								+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
						.append(" (select ").append(TinyTools.getXh(qyqssdsBaForm.getJsjdm()))
						.append(" ,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,"
								+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
								+ "SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,"
								+ "REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
								+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,"
								+ "SQLXDM,QSSBKSRQ,QSSBJSRQ ")
						.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
						.append(" where  t1.nsrjsjdm = ")
						.append(SBStringUtils.getSQLStr(qyqssdsBaForm.getJsjdm())).append(")");

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
						.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
						.append(" where  t1.nsrjsjdm ='").append(qyqssdsBaForm.getJsjdm())
						.append("'");

				System.out.println("��ҵ����˰-������Ϣɾ��SQL");
				System.out.println(bf.toString());

				st.execute(bf.toString());


				if (st != null) {
					st.close();
				}
				// ������ʷ����
				String sql = "insert into SBDB.SB_JL_QYQSSDSBA_NSRJBXXB(nsrjsjdm,"
						+ "nsrsbh,"
						+ "nsrmc,"
						+ "version,"
						+ "SSJJLX,"
						+ "SSJJLXMC,"
						+ "LXDH,"
						+ "JYDZ,"
						+ "SSHY,"
						+ "SSHYMC,"
						+ "SWJGZZJGDM,"
						+ "SWJGZZJGMC,"
						+ "CJR,"
						+ "CJSJ,"
						+ "LRR,"
						+ "LRSJ,"
						+ "XTJB,"
						+ "BBMSF,"
						+ "REMARK1,"
						+ "REMARK2,"
						+ "QSLLRY,"
						+ "TBRQ,"
						+ "QSBAKSRQ,"
						+ "QSBAJSRQ,"
						+ "BASHZTBS,"
						+ "BASHTGRQ,"
						+ "SBSHZTBS,"
						+ "SBSHTGRQ,"
						+ "JYQXJM,"
						+ "GDJYJS,"
						+ "YFDXGB,"
						+ "YFXGPC,"
						+ "YFGDQS,"
						+ "QTYY,"
						+ "SQLXDM,"
						+ "QSSBKSRQ,"
						+ "QSSBJSRQ"
						+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?,?,?,?,sysdate,sysdate,sysdate,?,sysdate,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				int i = 1;
				pst.setString(i++, qyqssdsBaForm.getJsjdm());// ���������
				pst.setString(i++, djsj.getSwdjzh());// ��˰��ʶ���
				pst.setString(i++, djsj.getNsrmc());// ��˰������
				pst.setString(i++, CodeConstant.QYQSSDS_VERSION_2014);// �汾��
				pst.setString(i++, djsj.getDjzclxdm());// ������������
				pst.setString(i++, djsj.getDjzclxmc());// ����������������
				pst.setString(i++, qyqssdsBaForm.getLxdh());// ��ϵ�绰
				pst.setString(i++, djsj.getJydz());// ��Ӫ��ַ
				pst.setString(i++, djsj.getGjbzhydm());// ������ҵ
				pst.setString(i++, djsj.getGjbzhymc());// ������ҵ����
				pst.setString(i++, djsj.getSwjgzzjgdm());// ˰�������֯��������
				pst.setString(i++, djsj.getSwjgzzjgmc());// ˰�������֯��������
				pst.setString(i++, ud.yhid);// ������
				pst.setString(i++, ud.yhid);// ¼����
				pst.setString(i++, null);// ϵͳ����
				pst.setString(i++, "0101,0102,0103,0104");// ����������
				pst.setString(i++, null);// ��ע1
				pst.setString(i++, null);// ��ע2
				pst.setString(i++, qyqssdsBaForm.getQsllry());// ����������Ա
				pst.setString(i++, "2");// ���㱸�����״̬��ʶ 1�����ύδ��ˣ�2�������ͨ����3����˱����أ�4������
				pst.setString(i++, null);// �����걨���״̬��ʶ 1�����ύδ��ˣ�2�������ͨ����3����˱����أ�4������
				pst.setString(i++, null);// �����걨���ͨ������
				pst.setString(i++, qyqssdsBaForm.getJyqxjm());// ��Ӫ���޽��� Y ���ǣ�N����
				pst.setString(i++, qyqssdsBaForm.getGdjyjs());// ��������ɢ Y ���ǣ�N����
				pst.setString(i++, qyqssdsBaForm.getYfdxgb());// ���������ر� Y ���ǣ�N����
				pst.setString(i++, qyqssdsBaForm.getYfxgpc());// ���������Ʋ� Y ���ǣ�N����
				pst.setString(i++, qyqssdsBaForm.getYfgdqs());// �����涨���� Y ���ǣ�N����
				pst.setString(i++, qyqssdsBaForm.getQtyy());// ����ԭ�� Y ���ǣ�N����
				pst.setString(i++, "1");// �������ʹ��룬0���������룬1����������
				pst.setString(i++, null);// �����걨��ʼ����
				pst.setString(i++, null);// �����걨��������

				count = pst.executeUpdate();
				if (pst != null) {
					pst.close();
				}
		
			}catch (Exception e) {
				e.printStackTrace();
				throw ExceptionUtil.getBaseException(e);
			} finally {
				SfDBResource.freeConnection(conn);
			}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}


		return qyqssdsBaForm;
	}

	/**
	 * doRefuse�ܾ�����
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	private Object doRefuse(VOPackage vo) throws BaseException {

		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) vo.getData();

		// ��ȡ���������
		String jsjdm = qyqssdsBaForm.getJsjdm();
		Map map=QyqssdsActionHelper.getShztbs(jsjdm);
		if(map.get(CodeConstant.SMSB_QYQSSDS2014_BASHZTBS).toString().equals("2")){
			throw new ApplicationException("���㱸�����״̬�������ͨ�������ܲ��أ�");
		}
		String sql = "UPDATE SBDB.SB_JL_QYQSSDSBA_NSRJBXXB SET BASHZTBS='3'  WHERE NSRJSJDM='"
				+ jsjdm+"'";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = SfDBResource.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
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
			SfDBResource.freeConnection(conn);
		}
		return qyqssdsBaForm;
	}

	/**
	 * doCancle����
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	private Object doCancle(VOPackage vo) throws BaseException {

		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) vo.getData();
		// ��ȡ���������
		String jsjdm = qyqssdsBaForm.getJsjdm();
		String sql = "UPDATE SBDB.SB_JL_QYQSSDSBA_NSRJBXXB SET BASHZTBS='4' WHERE NSRJSJDM='"
				+ jsjdm+"'";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = SfDBResource.getConnection();
			// ��ȡ���������ݿ�ӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			QyqssdsUtil2014.setQyqssdsReport(qd, qyqssdsBaForm);

			// ѭ��ɾ���ͻ�����д�����б��������ӵķ����
			for (int i = 0; i < CodeConstant.QYQSSDS_TABLE_ID_ALL.length; i++) {
				// ��ҵ����˰�����ڵ�����������
				QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
				table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_ALL[i]);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
				// set table
				qd.getTableContentList().put(table.getTableId(), table);
				// ����delete������������ɾ��
				iApp.deleteSingleTable(qd);
			}
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
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
			SfDBResource.freeConnection(conn);
		}
		return qyqssdsBaForm;
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

		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();

		Connection conn = null;
		Statement stmt = null;
		Statement st = null;
		conn = SfDBResource.getConnection();
		// ��ȡ���������
		String jsjdm = qyqssdsBaForm.getJsjdm();
		//by zhangj
		String sbshztbs=QyqssdsActionHelper.getShztbs(jsjdm).get(CodeConstant.SMSB_QYQSSDS2014_SBSHZTBS).toString();
		if("2".equals(sbshztbs)){//�����걨���״̬��ʾ�������ͨ��
			throw new ApplicationException("�����걨���״̬�������ͨ�������ȷϳ������걨��");
			//throw ExceptionUtil.getBaseException(new Exception(),"�����걨���״̬�������ͨ�������ȷϳ������걨��");
			//JOptionPane.showMessageDialog( null, "�����걨���״̬�������ͨ�������ȷϳ������걨��");
		}
			
		/**
		 * ɾ������
		 */
		StringBuffer bf = new StringBuffer();

		String sql = "DELETE FROM SBDB.SB_JL_QYQSSDSBA_NSRJBXXB  WHERE NSRJSJDM='"
				+ jsjdm+"'";
		try {

			// ��ȡ���������ݿ�ӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			QyqssdsUtil2014.setQyqssdsReport(qd, qyqssdsBaForm);

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
			bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_HIS ")
					.append("(xh,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,SSJJLXMC,LXDH,"
							+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,"
							+ "BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
							+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					.append(" (select ")
					.append(TinyTools.getXh(jsjdm))
					.append(",")
					.append(" nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,"
							+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
							+ "SWJGZZJGMC,CJR,CJSJ,'"+ud.getYhid()+"',LRSJ,XTJB,BBMSF,REMARK1,"
							+ "REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,'6',BASHTGRQ,"
							+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,"
							+ "SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(SBStringUtils.getSQLStr(jsjdm)).append(" ) ");

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
		return qyqssdsBaForm;
	}
}
