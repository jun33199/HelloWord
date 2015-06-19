package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.processor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.Jbxx;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.CAConstants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.QysdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.NsrJbxxBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.NsrJbxxdjbVO;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @version 1.0 ��˰�˻�����Ϣprocessor��
 */
public class NsrJbxxdjbProcessor implements Processor {
	public NsrJbxxdjbProcessor() {
	}


	/**
	 * ����ҵ���������ֵ����ҵ�����
	 * 
	 * @param vo
	 *            VOPackage
	 * @return java.lang.Object
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	public Object process(VOPackage vo) throws BaseException {
		// ����ҵ���������ֵ����ҵ�����
		try {
			switch (vo.getAction()) {
			// ��ѯ
			case QysdsksActionConstant.INT_ACTION_QUERY: {
				return doQuery((Map) vo.getData());
			}

				// ����
			case QysdsksActionConstant.INT_ACTION_SAVE: {
				return doSave(vo);
			}

			default:
				throw new SystemException("no such method");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	/**
	 * ��ѯ��˰�˻�����Ϣ
	 * 
	 * @param pData
	 *            Map
	 * @return Map
	 * @throws BaseException
	 */
	private Object doQuery(Map pData) throws BaseException {
		// ���ݿ����Ӷ���
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		NsrJbxxBO nsrJbxxBO = new NsrJbxxBO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
		try {

			// ������ݿ�����
			conn = DBResource.getConnection();

			// ���������
			String jsjdm = null;
			// ��ǰ����
			Timestamp curDate = null;
			// ˰��Ǽǻ�������ֵ����
			SWDJJBSJ djjbsj = (SWDJJBSJ) pData
					.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
			jsjdm = (String) pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
			curDate = (Timestamp) pData.get(QysdsksMapConstant.STRING_KEY_DATE);// �걨����

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(curDate);

			int year = calendar.get(calendar.YEAR);
			String nd = new Integer(year).toString(); // ���걨���
			nsrJbxxBO.setSbnd(nd);// �걨���

			// ȡ��˰����������Map
			Map skssrq = new HashMap();
			skssrq = Skssrq.yearSkssrq(curDate);
			// ȡ��˰��������ʼ�ͽ�������
			Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
			Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
			nsrJbxxBO.setSkssksrq(skssksrq.toString().substring(0, 11));// ˰��������ʼ����
			nsrJbxxBO.setSkssjsrq(skssjsrq.toString().substring(0, 11));// ˰��������������
			nsrJbxxBO.setSknd((String) skssrq.get(Skssrq.SKSSRQ_ND));// ˰�����

			nsrJbxxBO.setJsjdm(jsjdm);// ��˰�˼��������
			nsrJbxxBO.setNsrmc(djjbsj.getNsrmc());// ��˰������
			nsrJbxxBO.setNsrsbh(djjbsj.getSwdjzh());// ��˰��ʶ���

			nsrJbxxBO.setSsjjlxdm(djjbsj.getDjzclxdm()); // ������������-�Ǽ�ע�����ʹ���
			nsrJbxxBO.setSsjjlxmc(djjbsj.getDjzclxmc());// ������������-�Ǽ�ע����������
			nsrJbxxBO.setLxdh(djjbsj.getZcdzlxdh()); // ע���ַ��ϵ�绰
			nsrJbxxBO.setJydz(djjbsj.getJydz());// ��Ӫ��ַ
			nsrJbxxBO.setSshydm(djjbsj.getGjbzhydm());// ������ҵ����
			nsrJbxxBO.setSshymc(djjbsj.getGjbzhymc());// ������ҵ����

			nsrJbxxBO.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm()); // ˰�������֯��������
			nsrJbxxBO.setSwjgzzjgmc(djjbsj.getSwjgzzjgmc()); // ˰�������֯��������
			//nsrJbxxBO.setZczbje(djjbsj.getZczbje()+"");
			nsrJbxxBO.setLrr(jsjdm);// ¼����
			nsrJbxxBO.setCjr(jsjdm);// ������

			nsrJbxxBO.setCjrq(curDate.toString().substring(0, 11));// ��������
			nsrJbxxBO.setLrrq(curDate.toString().substring(0, 11));// ¼������

			// ��ȡ���������ݿ�ӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ���ð汾��
			//nsrJbxxBO.setVersion(iApp
			//		.getCurrentVersion(Constants.CREPORTS_APPID_QYSDS));
			//nsrJbxxBO.setVersion(Constant.CA_XSLTDM_NSRJBXXB);
			//��xslt�İ汾�뱨��İ汾���ֿ�
			nsrJbxxBO.setVersion(Constant.REPORT_VERSION_NSRJBXXB);
			
			// ϵͳ����
			nsrJbxxBO.setXtjb(Constant.SDS_1);

			// ����˰�ѽӿڻ����ҵ�����շ�ʽ
			ServiceProxy proxy = new ServiceProxy();
			System.out.println("˰����ڲ��������������=" + nsrJbxxBO.getJsjdm());
			System.out.println("˰����ڲ������걨����=" + curDate);
			System.out.println("˰����ڲ�����˰��������ʼ����=" + skssksrq);
			System.out.println("˰����ڲ�����˰��������������=" + skssjsrq);

			QysdsSet sdsInfo = proxy.getQysdsInfo(nsrJbxxBO.getJsjdm(),
					curDate, skssksrq, skssjsrq, "01");// �걨

			// �ж����շ�ʽ
			if (sdsInfo.getZsfs() != null) {

				System.out.println(nsrJbxxBO.getJsjdm() + "��ҵ���շ�ʽ��Ϊ�� :"
						+ sdsInfo.getZsfs().getZsfsdm());

				nsrJbxxBO.setZsfsdm(sdsInfo.getZsfs().getZsfsdm()); // ���շ�ʽ����

				nsrJbxxBO.setZsfsmc(sdsInfo.getZsfs().getZsfsmc()); // ���շ�ʽ����

				// modified by hazhengze 20051227 End
				if (nsrJbxxBO.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {

					throw new ApplicationException("����˰��Ϊ�����ʷ�ʽ������Ҫ���˰�˻�����Ϣ��!");

				} else if (nsrJbxxBO.getZsfsdm().equals(
						CodeConstant.ZSFSDM_DEZS)) { // ��������

					throw new ApplicationException("����˰��Ϊ�������շ�ʽ������Ҫ���˰�˻�����Ϣ��!");
				} else if (nsrJbxxBO.getZsfsdm().equals(
						CodeConstant.ZSFSDM_CZZS)) { // ��������

				}
			} else {
				// 20070208���շ�ʽ���ȡ��Ϊ������Ϊ�ǲ���������ҵ�ġ�
				// throw new ApplicationException(
				// "����˰��û�к˶���ҵ����˰���շ�ʽ����˶��������д������Ϣ��!");

				nsrJbxxBO.setZsfsdm(Constant.ZSFSDM_CZZS);
				nsrJbxxBO.setZsfsmc(Constant.ZSFSNAME_CZZS);
			}

			nsrJbxxBO.setCwkjzddm(CodeConstant.CWKJZD01);// ����ƶȴ���
			nsrJbxxBO.setCwkjzddm_old(CodeConstant.CWKJZD01);// ����ƶȴ����޸�ǰ

			nsrJbxxBO.setJmlxdm(CodeConstant.JMLXNO);// �������ʹ���
			nsrJbxxBO.setJmlxdm_old(CodeConstant.JMLXNO);// �������ʹ����޸�ǰ

			// ���ʹ�����ʽ
			nsrJbxxBO.setGzglxsdm(CodeConstant.GZGLXS01);
			// ���ʹ�����ʽ
			nsrJbxxBO.setGzglxsdm_old(CodeConstant.GZGLXS01);

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
					.append(" CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,ZCZE,ZCZBJE ")
					// from
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ")
					// where
					.append(" where  t1.nsrjsjdm = ? and t1.nd = ? ");

			System.out.println("��ҵ����˰-������Ϣ��ѯSQL");
			System.out.println(bf.toString());

			ps = conn.prepareStatement(bf.toString());
			System.out.println("1-" + nsrJbxxBO.getJsjdm());
			System.out.println("2-" + nsrJbxxBO.getSknd());
			ps.setString(1, nsrJbxxBO.getJsjdm());
			ps.setString(2, nsrJbxxBO.getSknd());
			rs = ps.executeQuery();

			if (rs.next()) {

				// ��˰�˼��������
				//nsrJbxxBO.setJsjdm(rs.getString("NSRJSJDM"));
				// ��˰��ʶ��ţ�˰��Ǽ�֤��
				// nsrJbxxBO.setNsrsbh(rs.getString("NSRSBH"));
				// ��˰������
				// nsrJbxxBO.setNsrmc(rs.getString("NSRMC"));
				// ˰�����
				//nsrJbxxBO.setSknd(rs.getString("ND"));
				// �걨���
				nsrJbxxBO.setSbnd(rs.getString("SBND"));
				// ������������
				// nsrJbxxBO.setSsjjlxdm(rs.getString("SSJJLX"));
				// ������������
				// nsrJbxxBO.setSsjjlxmc(rs.getString("SSJJLXMC"));
				// ��ϵ�绰
				nsrJbxxBO.setLxdh(rs.getString("LXDH"));
				// ��Ӫ��ַ
				// nsrJbxxBO.setJydz(rs.getString("JYDZ"));
				// ������ҵ
				// nsrJbxxBO.setSshydm(rs.getString("SSHY"));
				// ������ҵ
				// nsrJbxxBO.setSshymc(rs.getString("SSHYMC"));
				// �ƻ��ƶ� 00:һ��01:����02:����
				nsrJbxxBO.setCwkjzddm(rs.getString("CKZD"));
				// ����ƶȴ����޸�ǰ
				nsrJbxxBO.setCwkjzddm_old(rs.getString("CKZD"));
				// ˰�������֯��������
				// nsrJbxxBO.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				// ˰�������֯��������
				// nsrJbxxBO.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));

				// ��ҵ����˰���շ�ʽ
				// nsrJbxxBO.setZsfsdm(rs.getString("ZSFS"));
				// ��ҵ����˰���շ�ʽ
				// nsrJbxxBO.setZsfsmc(rs.getString("ZSFSMC"));

				// ��������
				nsrJbxxBO.setJmlxdm(rs.getString("JMLX"));
				// ��������
				nsrJbxxBO.setJmlxdm_old(rs.getString("JMLX"));

				// ���ʹ�����ʽ
				nsrJbxxBO.setGzglxsdm(rs.getString("GZGLXS"));
				// ���ʹ�����ʽ
				nsrJbxxBO.setGzglxsdm_old(rs.getString("GZGLXS"));

				nsrJbxxBO.setCjr(rs.getString("CJR"));
				nsrJbxxBO.setCjrq(StringUtils.getStrFromDate(rs
						.getTimestamp("CJSJ")));
				nsrJbxxBO.setCjr(rs.getString("LRR"));
				nsrJbxxBO.setLrrq(StringUtils.getStrFromDate(rs
						.getTimestamp("LRSJ")));
				nsrJbxxBO.setXtjb(rs.getString("XTJB"));
				nsrJbxxBO.setBbmsf(rs.getString("BBMSF"));
				nsrJbxxBO.setZczbje(rs.getString("ZCZBJE"));
				nsrJbxxBO.setZcze(rs.getString("ZCZE"));

			}

			/** ***********************************�Ѿ�¼���end*************************************************************** */

			
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "��ѯ����������ҵ������Ϣ����ʧ��");
		} finally {
			// �ر����ݿ�����
			DBResource.destroyConnection(conn);
		}

		System.out.println(nsrJbxxBO.getJsjdm() + "��ҵ���շ�ʽΪ"
				+ nsrJbxxBO.getZsfsdm());

		return nsrJbxxBO;
	}

	/**
	 * ������ҵ����˰����
	 * 
	 * @param data
	 *            Map
	 * @throws BaseException
	 */
	private Map doSave(VOPackage vop) throws BaseException {
		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();

		Connection conn = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		// sql buffer
		StringBuffer bf = new StringBuffer();
		// �����Ƿ�ƥ��
		boolean jm_type = false;

		NsrJbxxdjbVO nsrJbxxdjbVO = new NsrJbxxdjbVO();
		NsrJbxxBO nsrJbxxBO = new NsrJbxxBO();

		
		
		nsrJbxxBO = (NsrJbxxBO) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ); // BO
		String bbmsf_old=nsrJbxxBO.getBbmsf();
		
		// ˰��Ǽǻ�������ֵ����
		SWDJJBSJ djjbsj = (SWDJJBSJ) data
				.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);// �Ǽ�����

		DzyjsjVO dzyj = (DzyjsjVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);// ����ԭ��

		nsrJbxxdjbVO = (NsrJbxxdjbVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);// VO

		try {

			conn = DBResource.getConnection();
			st = conn.createStatement();

			// ��ȡ���������ݿ�ӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

			// ����˰�ѱ����շ�ʽ�϶����е���˰��״̬
			try {
				String sql = "update sfdb.sf_jl_qysdszsfs_his set nsrzt='10',lrrq=sysdate where jsjdm='"
						+ nsrJbxxBO.getJsjdm() + "'";
				st.executeUpdate(sql);
			} catch (Exception ex1) {
				// �׳��쳣
				ex1.printStackTrace();
				new ApplicationException("���ݿ���¼�¼ʧ�ܣ�" + nsrJbxxBO.getJsjdm()
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
			/*
			String sqspbh = "����λ�޼�����������";
			if (("".equals(nsrJbxxBO.getJmlxdm()))
					|| (CodeConstant.JMLXNO.equals(nsrJbxxBO.getJmlxdm()))) {

				nsrJbxxBO.setSqspbh(sqspbh);

			} else if ((CodeConstant.JMLXOTHER.equals(nsrJbxxBO.getJmlxdm()))) {

				bf.delete(0, bf.length());

				bf
						.append(
								" select t1.sqspbh as sqspbh,t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
						.append(" where t1.jsjdm= ").append(
								StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
						.append(" and t2.sqspbh=t1.sqspbh ").append(
								" and t2.jmsqsrq<= ").append(
								StringUtils
										.getSQLDate2(nsrJbxxBO.getSkssjsrq()))
						.append(" and t2.jmszzrq>= ").append(
								StringUtils
										.getSQLDate2(nsrJbxxBO.getSkssjsrq()))
						.append(" and t2.szsmdm='30' ").append(
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
				nsrJbxxBO.setSqspbh(sqspbh); // �����������
				// }

			} else if ((CodeConstant.JMLX9010.equals(nsrJbxxBO.getJmlxdm()))) {

				bf.delete(0, bf.length());

				bf
						.append(
								" select t1.sqspbh as sqspbh,t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
						.append(" where t1.jsjdm= ").append(
								StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
						.append(" and t2.sqspbh=t1.sqspbh ").append(
								" and t2.szsmdm='30' ").append(
								" and  t1.jmslbdm= ").append(
								StringUtils.getSQLStr(nsrJbxxBO.getJmlxdm()))
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

				// if (jm_type) {
				nsrJbxxBO.setSqspbh(sqspbh); // �����������
				// }

				// 2007-04-16 ������¼�����ҵ�����϶��򣬸û�Ҳ�����и��¼�����ҵ����

				java.util.Date time = new Timestamp(System.currentTimeMillis());// ƥ��˰�ѽӿڵ�rq������������

				Map skssrq = new HashMap();
				skssrq = Skssrq.yearSkssrq(time);
				// ȡ��˰��������ʼ�ͽ�������
				Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				System.out.println("˰����ڲ��������������=" + nsrJbxxBO.getJsjdm());
				System.out.println("˰����ڲ������걨����=" + time);
				System.out.println("˰����ڲ�����˰��������ʼ����=" + skssksrq);
				System.out.println("˰����ڲ�����˰��������������=" + skssjsrq);

				// ����˰�ѽӿڻ����ҵ�����շ�ʽ
				ServiceProxy proxy = new ServiceProxy();
				QysdsSet sdsIsnfo = proxy.getQysdsInfo(nsrJbxxBO.getJsjdm(),
						time, skssksrq, skssjsrq, "01");


				System.out.println("ȡ�õ�˰�ѽӿ���ϢsdsInfo="
						+ sdsIsnfo);
				
				// �ж����շ�ʽ
				if (sdsIsnfo != null) {
					// ���¼�����ҵ�϶����� /
					Date gxqyrdrq = sdsIsnfo.getGxjsqy();

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
								StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
						.append(" and t2.sqspbh=t1.sqspbh ").append(
								" and t2.jmsqsrq<= ").append(
								StringUtils
										.getSQLDate2(nsrJbxxBO.getSkssjsrq()))
						.append(" and t2.jmszzrq>= ").append(
								StringUtils
										.getSQLDate2(nsrJbxxBO.getSkssjsrq()))
						.append(" and t2.szsmdm='30' ").append(
								" and  t1.jmslbdm= ").append(
								StringUtils.getSQLStr(nsrJbxxBO.getJmlxdm()))
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
				nsrJbxxBO.setSqspbh(sqspbh); // �����������
				// }

				if (!jm_type) {
					throw new ApplicationException(
							"ѡ��ļ���������˰����Ϣ�к˶��ļ������Ͳ�һ�£�����˰�˲����ܴ˼�������!��");
				}

			}
			*/

			/**
			 * ������ʷ����
			 */

			bf.delete(0, bf.length());

			bf
					.append(" insert into sbdb.sb_jl_qysds_nsrjbxxb_his ")
					.append(
							" (XH,NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,DL,CYL,DE,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2,ZCZE,ZCZBJE) ")
					.append(" (select ")
					.append(TinyTools.getXh(nsrJbxxBO.getJsjdm()))
					.append(",")
					.append(
							" NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,DL,CYL,DE,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2,ZCZE,ZCZBJE ")
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ").append(
							" where  t1.nsrjsjdm = ").append(
							StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
					.append(" and t1.nd = ").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSknd())).append(
							" ) ");

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
							StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
					.append(" and t1.nd = ").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSknd()));

			System.out.println("��ҵ����˰-������Ϣɾ��SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			// ����form�еĹ��ʹ�����ʽ������ �������ƶȵ����Ƽ����Ӧ�ı���������
			if (CodeConstant.CWKJZD01.equals(nsrJbxxBO.getCwkjzddm())) {

				nsrJbxxBO.setCwkjzdmc(CodeConstant.CWKJZDMC01);

				nsrJbxxBO.setBbmsf(CodeConstant.BBMSF10_2008);
				
				// ����form�еĹ��ʹ�����ʽ������
				/*
				if (CodeConstant.GZGLXS01.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC01);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF10);

				} else if (CodeConstant.GZGLXS03
						.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC03);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF11);

				}
				*/

			} else if (CodeConstant.CWKJZD02.equals(nsrJbxxBO.getCwkjzddm())) {

				nsrJbxxBO.setCwkjzdmc(CodeConstant.CWKJZDMC02);

				nsrJbxxBO.setBbmsf(CodeConstant.BBMSF20_2008);

				// ����form�еĹ��ʹ�����ʽ������
				/*
				if (CodeConstant.GZGLXS01.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC01);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF20);

				} else if (CodeConstant.GZGLXS03
						.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC03);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF21);

				}
				*/

			} else if (CodeConstant.CWKJZD03.equals(nsrJbxxBO.getCwkjzddm())) {

				nsrJbxxBO.setCwkjzdmc(CodeConstant.CWKJZDMC03);

				nsrJbxxBO.setBbmsf(CodeConstant.BBMSF30_2008);

				// ����form�еĹ��ʹ�����ʽ������
				/*
				if (CodeConstant.GZGLXS01.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC01);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF30);

				} else if (CodeConstant.GZGLXS03
						.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC03);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF31);

				}
				*/

			}

			//String sybs=FriendHelper.getNsrSybs(djjbsj);
			String sybs = nsrJbxxdjbVO.getNsrjbxx().getSybs();
			System.out.println("����ʱ�����˰Դ��ʶ--�������ܷ�Χ����ģ��֮��"+sybs);
			if(Integer.valueOf(nsrJbxxBO.getSknd()).intValue()>2012)
			{
				/**
				 * ���˰Դ��ʶΪ�ܵ�����д�����
				 * ��ӱ�17 
				 * add by wangcy  2013-12-04
				 */
				if(CodeConstant.CODE_QYSDS_ZGFWJD_KSSZJG.equals(sybs)){
					nsrJbxxBO.setBbmsf(nsrJbxxBO.getBbmsf()+CodeConstant.BBMSF17_2012);
				}
				/**
				 * ���˰Դ��ʶΪ�ֵ�����д�����
				 * ȥ�����б�
				 * add by wangcy  2013-12-04
				 */
				if(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG.equals(sybs)){
					nsrJbxxBO.setBbmsf("");
				}
			}

			// ����form�еļ�����ҵ����˰�����͵�����
			/*
			if ("".equals(nsrJbxxBO.getJmlxdm())
					|| CodeConstant.JMLXNO.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMCNO);

			} else if (CodeConstant.JMLXOTHER.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMCOTHER);

			} else if (CodeConstant.JMLX9010.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMC9010);

			} else if (CodeConstant.JMLX9020.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMC9020);

			} else if (CodeConstant.JMLX9030.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMC9030);

			} else if (CodeConstant.JMLX9090.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMC9090);

			} else if (CodeConstant.JMLX9070.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMC9070);

			}
			*/

			/**
			 * �����޸ĵ�����
			 */

			bf.delete(0, bf.length());

			bf
					.append(" insert into sbdb.sb_jl_qysds_nsrjbxxb ")
					.append(
							" (NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2,ZCZE,ZCZBJE) ")
					// ���������
					.append(" values(").append(
							StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
					// ��˰��ʶ���
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getNsrsbh()))
					// ��˰������
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getNsrmc()))
					// �汾��
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getVersion()))
					// ˰�����
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSknd()))
					// �걨���
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSbnd()))
					// �����������
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSqspbh()))
					// �����������ʹ���
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSsjjlxdm()))
					// ����������������
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSsjjlxmc()))
					// ��ϵ�绰
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getLxdh()))
					// ��Ӫ��ַ
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getJydz()))
					// ������ҵ����
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSshydm()))
					// ������ҵ����
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSshymc()))
					// ���շ�ʽ����
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getZsfsdm()))
					// ���շ�ʽ����
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getZsfsmc()))
					// �ƻ��ƶȴ���
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getCwkjzddm()))
					// �ƻ��ƶ�����
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getCwkjzdmc()))
					// ���ʹ�����ʽ����
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getGzglxsdm()))
					// ���ʹ�����ʽ����
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getGzglxsmc()))
					// �������ʹ���
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getJmlxdm()))
					// ������������
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getJmlxmc()))
					// ������������˰��ֿ۷���
					.append(",").append("''")
					// ������������˰��ֿ۷�������
					.append(",").append("''")
					// ˰�������֯��������
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSwjgzzjgdm()))
					// ˰�������֯��������
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSwjgzzjgmc()))
					// ������
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getCjr()))
					// ����ʱ��
					.append(",").append(
							StringUtils.getSQLDate2(nsrJbxxBO.getCjrq()))
					// ¼����
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getLrr()))
					// ¼��ʱ��
					.append(",").append(
							StringUtils.getSQLDate2(nsrJbxxBO.getLrrq()))
					// ϵͳ����
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getXtjb()))
					// ����������
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getBbmsf()))
					// ˰������ʱ����
					.append(",").append(
							StringUtils.getSQLDate2(nsrJbxxBO.getSkssksrq()))
					// ˰������ʱ��ֹ
					.append(",").append(
							StringUtils.getSQLDate2(nsrJbxxBO.getSkssjsrq()))
					// ��ע1
					.append(",").append("''")
					// ��ע2
					.append(",").append("''")
//,ZCZBJE,ZCJE
					.append(",").append(StringUtils.getSQLStr(nsrJbxxBO.getZcze()))
                    .append(",").append(StringUtils.getSQLStr(nsrJbxxBO.getZczbje()))
					.append(") ");

			System.out.println("��ҵ����˰-������Ϣ����SQL\n");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			/**
			 * TODO �������ƶ������޸��ˣ���Ҫɾ����Ӧ���걨��
			 */
			if (!(nsrJbxxBO.getCwkjzddm().equals(nsrJbxxBO.getCwkjzddm_old()))) {

				QysdsReportsDeclare qd = new QysdsReportsDeclare();
				qd = this.setQysdsReport(nsrJbxxBO);

				// ��ҵ����˰�����ڵ�����������
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();

				// ԭѡ��Ϊ��ҵ��С��ҵ������ƶ�
				if (CodeConstant.CWKJZD01.equals(nsrJbxxBO.getCwkjzddm_old())) {
					// �������һ1
					table.setTableId(CodeConstant.TABLE_ID_FB1_1_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

					// ��������1
					table.setTableId(CodeConstant.TABLE_ID_FB2_1_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

				}

				// ԭѡ��Ϊ������ҵ����ƶ�
				if (CodeConstant.CWKJZD02.equals(nsrJbxxBO.getCwkjzddm_old())) {
					// �������һ2
					table.setTableId(CodeConstant.TABLE_ID_FB1_2_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

					// ��������2
					table.setTableId(CodeConstant.TABLE_ID_FB2_2_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

				}

				// ԭѡ��Ϊ��ҵ��λ��������塢������ҵ��λ����ƶ�
				if (CodeConstant.CWKJZD03.equals(nsrJbxxBO.getCwkjzddm_old())) {
					// �������һ3
					table.setTableId(CodeConstant.TABLE_ID_FB1_3_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

					// ��������3
					table.setTableId(CodeConstant.TABLE_ID_FB2_3_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// ����delete������������ɾ��
					iApp.deleteSingleTable(qd);

				}

			}

			
			System.out.println("˰�����: "+nsrJbxxBO.getSknd());
			/**
			 * ��������仯ɾ�����е��걨���� ��˰����ȴ���2012(����ԭ���߼�����)
			 * add by wangcy 2013-12-07
			 */
			if(Integer.valueOf(nsrJbxxBO.getSknd()).intValue()>2012){
				QysdsUtil qysdsUtil=new QysdsUtil();
				if(qysdsUtil.DoQueryData(nsrJbxxBO).equals("1") && !bbmsf_old.equals(nsrJbxxBO.getBbmsf())){
					System.out.println("˰�����"+nsrJbxxBO.getSknd() +"�����仯,ɾ����ǰ�������!");
					QysdsReportsDeclare qd = new QysdsReportsDeclare();
					qd = this.setQysdsReport(nsrJbxxBO);
					
					// ��ҵ����˰�����ڵ�����������
					QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
	
					//ѭ��ɾ���ͻ�����д�����б��������ӵķ����
					for(int i=0;i<CodeConstant.TABLE_ID_ALL.length;i++){
						table.setTableId(CodeConstant.TABLE_ID_ALL[i]);
						table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
						// set table
						qd.getTableContentList().put(table.getTableId(), table);
						// ����delete������������ɾ��
						iApp.deleteSingleTable(qd);
					}
	
					/**
					 * ���ݿͻ���Ҫ�� ���б仯�����ݽ���ɾ��
					 */
					
					bf.delete(0, bf.length());
					bf.append(" delete ")
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ")
					.append(" where  t1.nsrjsjdm = ").append(StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
					.append(" and t1.nd = ").append(StringUtils.getSQLStr(nsrJbxxBO.getSknd()));

					System.out.println("��ҵ����˰-������Ϣɾ��SQL");
					System.out.println(bf.toString());

					st.execute(bf.toString());
					//ɾ����ʶ �����½��ֶ� ��999�ڽ��б�ʶ  
					nsrJbxxdjbVO.getNsrjbxx().setQueryFlag("999");
					
//					//ɾ����ҵ����˰�걨
//					table.setTableId(CodeConstant.TABLE_ID_CZZSSDSNB_2012);
//					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
//					// set table
//					qd.getTableContentList().put(table.getTableId(), table);
//					// ����delete������������ɾ��
//					iApp.deleteSingleTable(qd);
//					
//					//ɾ����ҵ����˰�걨�����
//					table.setTableId(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
//					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
//					// set table
//					qd.getTableContentList().put(table.getTableId(), table);
//					// ����delete������������ɾ��
//					iApp.deleteSingleTable(qd);
				}
			}
			/**
			 * TODO ������ʹ�����ʽ�����޸��ˣ���Ҫɾ����Ӧ���걨��
			 */
			/*if (!(nsrJbxxBO.getGzglxsdm().equals(nsrJbxxBO.getGzglxsdm_old()))) {

				QysdsReportsDeclare qd = new QysdsReportsDeclare();
				qd = this.setQysdsReport(nsrJbxxBO);
				// ��ҵ����˰�����ڵ�����������
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();

				// ԭ��ѡ��ǹ�Ч�ҹ� (��˰���� ȫ��۳�) ����ѡ��Ч�ҹ�
				if ((CodeConstant.GZGLXS01.equals(nsrJbxxBO.getGzglxsdm_old()))
						&& (CodeConstant.GZGLXS03.equals(nsrJbxxBO
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
				if ((CodeConstant.GZGLXS01.equals(nsrJbxxBO.getGzglxsdm()))
						&& (CodeConstant.GZGLXS03.equals(nsrJbxxBO
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
			*/

			/**
			 * TODO ����������������޸��ˣ���Ҫɾ����Ӧ���걨��
			 */

			/*if (!(nsrJbxxBO.getJmlxdm().equals(nsrJbxxBO.getJmlxdm_old()))) {

			
				 //��������߼��������ݣ������������Ҫ����¼��
				 

				QysdsReportsDeclare qd = new QysdsReportsDeclare();
				qd = this.setQysdsReport(nsrJbxxBO);
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
			*/

			/**
			 * ��������ԭʼֵ
			 */
			nsrJbxxBO.setCwkjzddm_old(nsrJbxxBO.getCwkjzddm());
			nsrJbxxBO.setGzglxsdm_old(nsrJbxxBO.getGzglxsdm());
			nsrJbxxBO.setJmlxdm_old(nsrJbxxBO.getJmlxdm());

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

			if (ud.getCaflag()) {

				System.out.println("===========ǩ����ʼ==========");
				try {
					String ywid = nsrJbxxdjbVO.getNsrjbxx().getJsjdm()
							+ "+"
							+ DjStringUtil
									.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
					System.out.println("======ywid:" + ywid);
					dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
					System.out.println("===========ǩ������==========");
				} catch (Exception ex) {
					System.out.println("===========ǩ��ʧ��==========");
					throw ExceptionUtil.getBaseException(ex);

				}
				retData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, nsrJbxxBO);// BO
				retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);// ����ԭ��
				retData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, nsrJbxxdjbVO);// VO

			}
			retData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, nsrJbxxdjbVO);// VO
		} catch (Exception e) {
			// �׳��쳣
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		} finally {
			// �ر����ݿ�����
			DBResource.destroyConnection(conn);
		}

		return retData;
	}

	/**
	 * ���ñ�����������Ϣ
	 * 
	 * 
	 * @param nsrJbxxBO
	 */
	public QysdsReportsDeclare setQysdsReport(NsrJbxxBO nsrJbxxBO) {

		QysdsReportsDeclare report = new QysdsReportsDeclare();
		/**
		 * ������Ϣ
		 */
		Jbxx jbxx = new Jbxx();

		/**
		 * ������Ϣ(JBXX)-���������
		 */
		jbxx.setJsjdm(nsrJbxxBO.getJsjdm());
		/**
		 * ������Ϣ(JBXX)-��˰������
		 */
		jbxx.setNsrmc(nsrJbxxBO.getNsrmc());
		/**
		 * ������Ϣ(JBXX)-������������
		 */
		jbxx.setSsjjlx(nsrJbxxBO.getSsjjlxdm());
		/**
		 * ������Ϣ(JBXX)-��ϵ�绰
		 */
		jbxx.setLxdh(nsrJbxxBO.getLxdh());
		/**
		 * ������Ϣ(JBXX)-������ҵ
		 */
		jbxx.setSshy(nsrJbxxBO.getSshydm());
		/**
		 * ������Ϣ(JBXX)-���շ�ʽ
		 */
		jbxx.setZsfs(nsrJbxxBO.getZsfsdm());
		/**
		 * ������Ϣ(JBXX)-�ƻ��ƶ�
		 */
		jbxx.setCkzd(nsrJbxxBO.getCwkjzddm());
		/**
		 * ������Ϣ(JBXX)-���ʹ�����ʽ
		 */
		jbxx.setGzglxs(nsrJbxxBO.getGzglxsdm());
		/**
		 * ������Ϣ(JBXX)-��������
		 */
		jbxx.setJmlx(nsrJbxxBO.getJmlxdm());

		report.setJbxx(jbxx);

		/**
		 * Ӧ�ñ��
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYSDS);
		/**
		 * �汾��
		 */
		report.setVersion(nsrJbxxBO.getVersion());
		/**
		 * ��˰�˼��������
		 */
		report.setNsrjsjdm(nsrJbxxBO.getJsjdm());
		/**
		 * ��˰������
		 */
		report.setNsrmc(nsrJbxxBO.getNsrmc());
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
		if (nsrJbxxBO.getSkssksrq() != null
				&& !nsrJbxxBO.getSkssksrq().equals("")) {
			report.setSkssksrq(new Date(TinyTools.stringToDate(
					nsrJbxxBO.getSkssksrq(), "yyyyMMdd").getTime()));
		}
		/**
		 * ˰��������������
		 */
		if (nsrJbxxBO.getSkssjsrq() != null
				&& !nsrJbxxBO.getSkssjsrq().equals("")) {
			report.setSkssjsrq(new Date(TinyTools.stringToDate(
					nsrJbxxBO.getSkssjsrq(), "yyyyMMdd").getTime()));
		}
		/**
		 * �걨����
		 */
		if (nsrJbxxBO.getLrrq() != null && !nsrJbxxBO.getLrrq().equals("")) {
			report.setSbrq(new Date(TinyTools.stringToDate(nsrJbxxBO.getLrrq(),
					"yyyyMMdd").getTime()));
		}
		/**
		 * ˰�����
		 */
		report.setSknd(nsrJbxxBO.getSknd());
		/**
		 * ˰�������֯��������
		 */
		report.setSwjgzzjgdm(nsrJbxxBO.getSwjgzzjgdm());
		/**
		 * ���ش���
		 */
		report.setQxdm(nsrJbxxBO.getSwjgzzjgdm().substring(2, 4));
		/**
		 * ¼����
		 */
		report.setLrr(nsrJbxxBO.getLrr());
		/**
		 * ¼������
		 */
		if (nsrJbxxBO.getLrrq() != null && !nsrJbxxBO.getLrrq().equals("")) {
			report.setLrrq(new Date(TinyTools.stringToDate(nsrJbxxBO.getLrrq(),
					"yyyyMMdd").getTime()));
		}
		/**
		 * ������
		 */
		report.setCjr(nsrJbxxBO.getLrr());
		/**
		 * ��������
		 */
		if (nsrJbxxBO.getCjrq() != null && !nsrJbxxBO.getCjrq().equals("")) {
			report.setCjrq(new Date(TinyTools.stringToDate(nsrJbxxBO.getCjrq(),
					"yyyyMMdd").getTime()));
		}

		return report;
	}
}
