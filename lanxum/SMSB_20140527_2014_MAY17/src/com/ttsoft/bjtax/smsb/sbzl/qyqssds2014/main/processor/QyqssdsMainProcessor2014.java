package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * ��Ŀ���ƣ�smsb �����ƣ�QyqssdsMainProcessor2014 �������� ��ҵ����˰�����걨������ģ�� �����ˣ�wangcy
 * ����ʱ�䣺2014-2-14 ����4:08:41 �޸��ˣ�wangcy �޸�ʱ�䣺2014-2-14 ����4:08:41 �޸ı�ע��
 * 
 * @version 1.0
 */
public class QyqssdsMainProcessor2014 implements Processor {

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
		case CodeConstant.SMSB_CHECKACTION:
			result = doCheck(vo);
			break;
		case 10:
			result = doAccept(vo);
			break;
		case 11:
			result = doRefuse(vo);
			break;
		case 12:
			result = doSave(vo);
			break;
		case 13:
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

		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();
		// ��ʼ��FORM�������걨���ڡ��걨�ڼ�
		initForm(form);
		return form;
	}

	/**
	 * doQuery ��ѯ��ҵ�Ļ�����Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 * 
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();
		initForm(form);

		UserData ud = (UserData) vo.getUserData();

		SWDJJBSJ djsj = null;

		System.out.println("================="+form.getJsjdm()+"=================");
		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		form.setNsrmc(djsj.getNsrmc());
		Connection conn = null;
		try {
			conn = SfDBResource.getConnection();
			
			form = (QyqssdsBaseForm) QyqssdsUtil2014.queryQyqssdsJbxx(conn,form);
			if(form.getSbShztbs().equals("")){
				form.setSbShztMessage("δ�ύ");
			}else if(form.getSbShztbs().equals("1")){
				form.setSbShztMessage("���ύδ���");
			}else if(form.getSbShztbs().equals("2")){
				form.setSbShztMessage("�����ͨ��");
			}else if(form.getSbShztbs().equals("3")){
				form.setSbShztMessage("��˱�����");
			}else if(form.getSbShztbs().equals("4")){
				form.setSbShztMessage("����");
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
	 * doDelete �����걨��Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 * 
	 */

	private Object doDelete(VOPackage vo) throws BaseException {

		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();

		Connection conn = null;
		//������ʷ��statement
		Statement stmt = null;
		//ɾ������statement
		Statement st = null;
		conn = SfDBResource.getConnection();
		// ��ȡ���������
		String jsjdm = form.getJsjdm();
		/**
		 * ɾ������
		 */
		StringBuffer bf = new StringBuffer();

		String sql = "update SBDB.SB_JL_QYQSSDSBA_NSRJBXXB set SBSHZTBS=null,SBSHTGRQ=null,QSSBKSRQ=null,QSSBJSRQ=null WHERE NSRJSJDM='"
				+ jsjdm+"'";
		try {

			// ��ȡ���������ݿ�ӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			QyqssdsUtil2014.setQyqssdsReport(qd,form);

			// ѭ��ɾ���ͻ�����д�����б�
			for (int i = 0; i < CodeConstant.QYQSSDS_TABLE_ID_ALL.length; i++) {
				// ��ҵ����˰�����ڵ�����������
				QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
				//System.out.println(CodeConstant.QYQSSDS_TABLE_ID_ALL[i]);
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
			/*--�޸���ʷ���д����¼����Ϊ��ǰ�ˣ��걨���״̬��ʶΪ6�����ϣ�--*/
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
							+ "SWJGZZJGMC,CJR,CJSJ,'"+vo.getUserData().getYhid()+"',LRSJ,XTJB,BBMSF,REMARK1,"
							+ "REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,"
							+ "BASHTGRQ,'6',SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,"
							+ "SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(SBStringUtils.getSQLStr(jsjdm)).append(" ) ");

			System.out.println("��ҵ��������˰-������Ϣ������ʷ����SQL");
			System.out.println(bf.toString());
			/*--�Ȳ�����ʷ����ִ��ɾ������--*/
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
		return form;
	}
	
	/**
	 * doSave �����걨��Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 * 
	 */

	private Object doSave(VOPackage vo) throws BaseException {

		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();
		StringBuffer sql = new StringBuffer();
//		sql.append("update SBDB.SB_JL_QYQSSDSBA_NSRJBXXB set qssbksrq=to_date(?,'YYYY-MM-DD'),qssbjsrq=to_date(?,'YYYY-MM-DD') where nsrjsjdm=?");
		Connection conn = null;
		//PreparedStatement ps = null;
		try {
			conn = SfDBResource.getConnection();
//			ps = conn.prepareStatement(sql.toString());
//			ps.setString(1, form.getQssbksrq());//�����걨��ʼ����
//			ps.setString(2, form.getQssbjsrq());//�����걨��������
//			ps.setString(3, form.getJsjdm());//���������
//			int count =ps.executeUpdate();
			QyqssdsUtil2014.updateAllDate(conn, form);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
//			if (ps != null) {
//				try {
//					ps.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
			SfDBResource.freeConnection(conn);
		}

		return form;
	}
	/**
	 * doAccept ��������
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 * 
	 */

	private Object doAccept(VOPackage vo) throws BaseException {

		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();
		StringBuffer sql = new StringBuffer();
		sql.append("update SBDB.SB_JL_QYQSSDSBA_NSRJBXXB set SBSHTGRQ=sysdate,SBSHZTBS='2' where nsrjsjdm=?");
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = SfDBResource.getConnection();
			/*--modified by huohb 2014-06-13--*/
			//����ȫ��У��
			form=(QyqssdsBaseForm)doCheck(vo);
			//checkList��Ϊ�յ�����±�ʾУ��û��ͨ������Ҫreturn������ʾ�������ͨ��
			if((form!=null)&&(form.getCheckList()==null||form.getCheckList().size()==0)){
				
			}else{
				//return������ִ�����ͨ��
				return form;
			}
			
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, form.getJsjdm());
			int count =ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}

		return form;
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

		QyqssdsBaseForm qyqssdsBaForm = (QyqssdsBaseForm) vo.getData();

		// ��ȡ���������
		String jsjdm = qyqssdsBaForm.getJsjdm();
		String sql = "UPDATE SBDB.SB_JL_QYQSSDSBA_NSRJBXXB SET SBSHZTBS='3'  WHERE NSRJSJDM='"
				+ jsjdm+"'";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = SfDBResource.getConnection();
			stmt = conn.createStatement();
			int count=stmt.executeUpdate(sql);
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

	private Object doCheck(VOPackage vo) throws BaseException {
		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();
		UserData userData = vo.getUserData();
		Connection conn = null;
		QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			form.setQh("1");
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			QyqssdsUtil2014.setQyqssdsReport(report, form);
			Debug.out("-----------��������� " + form.getJsjdm()
					+ "ȫ��У�� ����������Ϣ--------");
			Debug.out("Appid-" + report.getAppid());
			Debug.out("Bbqlx-" + report.getBbqlx());
			Debug.out("Nsrjsjdm-" + report.getNsrjsjdm());
			Debug.out("Nsrmc-" + report.getNsrmc());
			Debug.out("Qh-" + report.getQh());
			Debug.out("Version-" + report.getVersion());

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);

			report = (QyqssdsReportsDeclare) iApp.query(report);
			Debug.out(report.getTableContentList().size());
			Debug.out(report.getTableContentList().size());
			// ��ȡУ��ӿ�
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYQSSDS);

			// ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List checkList = checker.checkMain(report,
					Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			if (checkList == null) {
				Debug.out("��˰��" + form.getJsjdm() + "��ҵ����˰����걨����ȫ��У��ͨ����");
			} else {
				Debug.out("��˰��" + form.getJsjdm() + "��ҵ����˰����걨���� �� "
						+ checkList.size() + " ����ʽδ���ͨ���� ");
			}

			// ȫ�����ͨ��
			if (checkList == null
					|| (checkList != null && checkList.size() == 0)) {

			}
			form.setCheckList(checkList);
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		return form;
	}

	/**
	 * ��ʼ��
	 * 
	 * @param form
	 *            ��������
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private void initForm(QyqssdsBaseForm form) throws BaseException {

		form.setTbrq(SfDateUtil.getDate());
		form.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		form.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		// �����걨�ڼ�
		
		//���˹���д
		form.setQssbksrq(TinyTools.Date2String(new Date(), "yyyy-MM-dd"));
		form.setQssbjsrq(TinyTools.Date2String(new Date(), "yyyy-MM-dd"));
		
	}

	/**
	 * �����걨���
	 * 
	 * @param sbrq
	 *            �걨����
	 * @return String ���
	 */

	private String getSbnd(String sbrq) {

		Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
		Debug.out("------------------------getSbnd---"
				+ (String) qj.get(Skssrq.SKSSRQ_ND));
		return (String) qj.get(Skssrq.SKSSRQ_ND);
	}

	/**
	 * �����걨����ȡ�õ�ǰǰ��0101-1231
	 * 
	 * @param curSbrq
	 *            �걨����
	 * @return dateMap
	 */
	private Map getSbrl(String curSbrq) {
		Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(sbrq);
		calendar.add(calendar.YEAR, -1);
		int year = calendar.get(calendar.YEAR);
		String nd = new Integer(year).toString();
		Timestamp ksrq;
		Timestamp jsrq;
		Map retMap = new HashMap();
		ksrq = new Timestamp(new GregorianCalendar(year, 0, 1).getTime()
				.getTime());
		jsrq = new Timestamp(new GregorianCalendar(year, 11, 31).getTime()
				.getTime());

		Map dateMap = new HashMap();
		dateMap.put("ksrq", ksrq);
		dateMap.put("jsrq", jsrq);
		return dateMap;
	}

}