/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.main.processor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.FriendHelper;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateTimeUtil;
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

public class QysdsMainProcessor2009 implements Processor {

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

		QysdsNewForm form = (QysdsNewForm) vo.getData();

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

		QysdsNewForm form = (QysdsNewForm) vo.getData();
		
		UserData ud=(UserData)vo.getUserData();
		
		SWDJJBSJ djsj = null;
		
		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		
		/*-------add by lijn 20160619  �������ܷ�Χ������ ����˰Դ��ʶ �ض�Ӧ��������-�����ڱ��е��ܣ�-������������Ҫ����Щ�걨��-----------------*/
		String sybs = form.getJdlx();
		//System.out.println("��ȡ��Щ��"+sybs);
		//if("04".equals(sybs))
		//{sybs="01";}
		
		Connection conn = null;

		try {
			conn = SfDBResource.getConnection();
			// form.setSknd(this.getSbnd(form.getSbrq()));
			/* ͨ��˰����������ȡ��˰����� */
			form.setSknd(form.getSkssksrq().substring(0, 4));
			form = (QysdsNewForm) QysdsUtil2009.queryQysdsJbxx(conn, form,sybs);

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return form;
	}

	private Object doCheck(VOPackage vo) throws BaseException {
		QysdsNewForm form = (QysdsNewForm) vo.getData();
		UserData userData = vo.getUserData();
		Connection conn = null;
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			form.setQh("1");
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			QysdsUtil2009.setQysdsReport(report, form);
			Debug.out("-----------��������� "+form.getJsjdm()+"ȫ��У�� ����������Ϣ--------");
			Debug.out("Appid-" + report.getAppid());
			Debug.out("Bbqlx-" + report.getBbqlx());
			Debug.out("Nsrjsjdm-" + report.getNsrjsjdm());
			Debug.out("Nsrmc-" + report.getNsrmc());
			Debug.out("Qh-" + report.getQh());
			Debug.out("Version-" + report.getVersion());

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

			report = (QysdsReportsDeclare) iApp.query(report);
			Debug.out(report.getTableContentList().size());
			Debug.out(report.getTableContentList().size());
			// ��ȡУ��ӿ�
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);

			// ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List checkList = checker.checkMain(report,
					Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			if (checkList == null) {
				Debug.out("��˰��"+form.getJsjdm()+"��ҵ����˰����걨����ȫ��У��ͨ����");
			} else {
				Debug.out("��˰��"+form.getJsjdm()+"��ҵ����˰����걨���� �� "+checkList.size()+" ����ʽδ���ͨ���� ");
			}

			// ȫ�����ͨ��
			if (checkList == null
					|| (checkList != null && checkList.size() == 0)) {
				/*-------2009��  �޼���-------*/
//				Timestamp t1, t2;
//
//				t1 = new Timestamp(System.currentTimeMillis());
//
//				// ��������
//				// this.insertJm(declare);
//				this.insertJmProce(report, userData);
//
//				t2 = new Timestamp(System.currentTimeMillis());
//
//				Debug.out("��������걨�����ݺ�ʱ��"
//						+ (t2.getTime() - t1.getTime()));
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

	private void initForm(QysdsNewForm form) throws BaseException {

		form.setSbrq(SfDateUtil.getDate());
		form.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		form.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		form.setZsfsdm("");
		// �걨�ڼ�
		Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(form.getSbrq()));
		try {
			String ksrq = DateTimeUtil.timestampToString((Timestamp) qj
					.get(Skssrq.SKSSKSRQ), DateTimeUtil.JAVA_DATEFORMAT);

			String jsrq = DateTimeUtil.timestampToString((Timestamp) qj
					.get(Skssrq.SKSSJSRQ), DateTimeUtil.JAVA_DATEFORMAT);
			form.setSknd((String) qj.get(Skssrq.SKSSRQ_ND));
			form.setSkssksrq(ksrq);
			form.setSkssjsrq(jsrq);
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}
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

	/**
	 * �����������
	 * 
	 * @param table
	 * @return
	 */
	public void insertJmProce(QysdsReportsDeclare declarein, UserData userData)
			throws BaseException {

		// �������
		QysdsReportsDeclare declare = declarein;

		Connection con = null;
		CallableStatement st = null;
		String sql = "";

		Timestamp now = new Timestamp(System.currentTimeMillis());

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		try {
			String jsjdm = declare.getNsrjsjdm();
			String jmlx = CodeConstant.JMLX_SP;
			String szsmdm = CodeConstant.SZSM_QYSDSCODE;
			// String sbrq = df.format(now);
			Timestamp sbrq = now;
			String fsdm = CodeConstant.FSDM_SMSB;
			String jzbz = CodeConstant.SMSB_JZBZ;
			// String lrr = declare.getNsrjsjdm();
			String lrr = userData.getYhid();
			// String skssjsrq = df.format(declare.getSkssjsrq());
			// String skssksrq = df.format(declare.getSkssksrq());
			java.sql.Date skssjsrq = declare.getSkssjsrq();
			java.sql.Date skssksrq = declare.getSkssksrq();
			String swjgzzjgdm = declare.getSwjgzzjgdm();
			String qxdm = declare.getSwjgzzjgdm().substring(0, 2);
			String djzclxdm = declare.getJbxx().getSsjjlx();
			String gjbzhydm = declare.getJbxx().getSshy();
			String nd = declare.getSknd();

			Ysjc ysjc = null;
			try {
				ysjc = JksUtil
						.getYsjc(jsjdm, CodeConstant.SZSM_QYSDSCODE, sbrq);
			} catch (Exception e) {
				throw new ApplicationException("�ü���������Ԥ�㼶�δ���û�м�¼��");
			}
			if (ysjc != null) {
				Debug.out("���� =" + ysjc.getYsjcdm());
			} else {
				throw new ApplicationException("�ü���������Ԥ�㼶�δ���û�м�¼��");
			}
			String ysjcdm = ysjc.getYsjcdm();

			com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
			try {
				Debug.out("djsj.getDjzclxdm()--" + djzclxdm);
				Debug.out("djsj.getGjbzhydm()--" + gjbzhydm);
				Debug.out("ysjc.getYsjcdm()--" + ysjc.getYsjcdm());
				yskm = JKAdapter.getInstance().getYskm(
						CodeConstant.SZSM_QYSDSCODE, djzclxdm, gjbzhydm,
						ysjc.getYsjcdm());

			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
			}
			if (yskm != null) {
				Debug.out("Ԥ���Ŀ =" + yskm.getYskmdm());
			} else {
				throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
			}

			String yskmdm = yskm.getYskmdm();

			// �������ݿ�����
			con = SfDBResource.getConnection();
			sql = "BEGIN sbdb.sb_pkg_sbjmtools.insertjmproce(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";

			st = con.prepareCall(sql);

			Debug.out("-------------���ò�������걨�洢���̲���----------------");
			Debug.out("1-jsjdm--" + jsjdm);
			Debug.out("2-jmlx--" + jmlx);
			Debug.out("3-szsmdm--" + szsmdm);
			Debug.out("4-sbrq--" + sbrq);
			Debug.out("5-fsdm--" + fsdm);
			Debug.out("6-jzbz--" + jzbz);
			Debug.out("7-lrr--" + lrr);
			Debug.out("8-skssjsrq--" + skssjsrq);
			Debug.out("9-skssksrq--" + skssksrq);
			Debug.out("10-swjgzzjgdm--" + swjgzzjgdm);
			Debug.out("11-qxdm--" + qxdm);
			Debug.out("12-djzclxdm--" + djzclxdm);
			Debug.out("13-gjbzhydm--" + gjbzhydm);
			Debug.out("14-nd--" + nd);
			Debug.out("15-ysjcdm--" + ysjcdm);
			Debug.out("16-yskmdm--" + yskmdm);

			st.setString(1, jsjdm);
			st.setString(2, jmlx);
			st.setString(3, szsmdm);
			st.setTimestamp(4, sbrq);
			st.setString(5, fsdm);

			st.setString(6, jzbz);
			st.setString(7, lrr);
			st.setDate(8, skssjsrq);
			st.setDate(9, skssksrq);
			st.setString(10, swjgzzjgdm);

			st.setString(11, qxdm);
			st.setString(12, djzclxdm);
			st.setString(13, gjbzhydm);
			st.setString(14, nd);
			st.setString(15, ysjcdm);

			st.setString(16, yskmdm);

			st.execute();

		} catch (Exception e) {

			e.printStackTrace();
			throw new ApplicationException("��������걨���ݳ���.");

		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				st = null;
			}

			// �ͷ����ݿ�����
			SfDBResource.freeConnection(con);
		}

	}

}
