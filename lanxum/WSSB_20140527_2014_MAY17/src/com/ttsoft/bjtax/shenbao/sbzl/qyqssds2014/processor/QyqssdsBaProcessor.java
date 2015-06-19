package com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.processor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.syax.creports.bo.qyqssds.QyqssdsBaJbxx;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.CAConstants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.QyqssdsConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.bo.NsrJbxxBo;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.xmlvo.QyqssdsNsrJbxxVo;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsBaProcessor implements Processor {

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
			// ɾ��
			case QysdsksActionConstant.INT_ACTION_DELETE: {
				doDelete(vo);
				return null;
			}

			default:
				throw new SystemException("no such method");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * ɾ����ҵ����˰����
	 * 
	 * @param data
	 *            Map
	 * @throws BaseException
	 */
	private void doDelete(VOPackage vop) throws BaseException {

		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();

		Connection conn = null;
//		PreparedStatement ps = null;
		Statement st = null;
//		ResultSet rs = null;
		// sql buffer
		StringBuffer bf = new StringBuffer();
		NsrJbxxBo nsrJbxxBo = new NsrJbxxBo();
		nsrJbxxBo = (NsrJbxxBo) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ); // BO
		try {
			conn = DBResource.getConnection();
			st = conn.createStatement();

			// ��ȡ���������ݿ�ӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			qd = this.setQysdsReport(nsrJbxxBo);

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
			/**
			 * ������ʷ����
			 */

			bf.delete(0, bf.length());

			bf.append(" insert into SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_his ")
					.append(" (XH,NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					.append(" (select ")
					.append(TinyTools.getXh(nsrJbxxBo.getJsjdm()))
					.append(",")
					.append(" NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJsjdm()))
					.append(" ) ");

			System.out.println("��ҵ��������˰-������Ϣ������ʷ����SQL");
			System.out.println(bf.toString());
			st.execute(bf.toString());

			/**
			 * ɾ������
			 */

			bf.delete(0, bf.length());

			bf.append(" delete ")
			  .append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
			  .append(" where  t1.nsrjsjdm = ")
			  .append(StringUtils.getSQLStr(nsrJbxxBo.getJsjdm()));

			System.out.println("��ҵ��������˰-������Ϣɾ��SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());
			if(st!=null){
				st.close();
			}
		} catch (Exception e) {
			// �׳��쳣
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e, "ɾ����ҵ��������˰������Ϣ��ʧ��");
			// throw new ApplicationException(e.getMessage());
		} finally {
			// �ر����ݿ�����
			DBResource.destroyConnection(conn);
		}
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
		Statement st = null;
		// sql buffer
		StringBuffer bf = new StringBuffer();
		QyqssdsNsrJbxxVo qyqssdsNsrJbxxVo = new QyqssdsNsrJbxxVo();
		NsrJbxxBo nsrJbxxBo = new NsrJbxxBo();

		nsrJbxxBo = (NsrJbxxBo) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ); // BO
		nsrJbxxBo.setSqlxdm("0");
		DzyjsjVO dzyj = (DzyjsjVO) data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);// ����ԭ��

		qyqssdsNsrJbxxVo = (QyqssdsNsrJbxxVo) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);// VO

		try {

			conn = DBResource.getConnection();
			st = conn.createStatement();

			/**
			 * ������ʷ����
			 */
			bf.delete(0, bf.length());

			bf.append(" insert into SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_his ")
					.append(" (XH,NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					.append(" (select ")
					.append(TinyTools.getXh(nsrJbxxBo.getJsjdm()))
					.append(",")
					.append(" NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJsjdm()))
					.append(" ) ");

			System.out.println("��ҵ��������˰-������Ϣ������ʷ����SQL");
			System.out.println(bf.toString());
			st.execute(bf.toString());

			/**
			 * ɾ������
			 */

			bf.delete(0, bf.length());

			bf.append(" delete ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJsjdm()));

			System.out.println("��ҵ��������˰-������Ϣɾ��SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			/**
			 * �����޸ĵ�����
			 */

			bf.delete(0, bf.length());
			bf.append(" insert into SBDB.SB_JL_QYQSSDSBA_NSRJBXXB ")
					.append(" (NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					// ���������
					.append(" values(")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJsjdm()))
					// ��˰��ʶ���
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getNsrsbh()))
					// ��˰������
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getNsrmc()))
					// �汾��
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getVersion()))
					// �����������ʹ���
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSsjjlxdm()))
					// ����������������
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSsjjlxmc()))
					// ��ϵ�绰
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getLxdh()))
					// ��Ӫ��ַ
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJydz()))
					// ������ҵ����
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSshydm()))
					// ������ҵ����
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSshymc()))
					// ˰�������֯��������
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSwjgzzjgdm()))
					// ˰�������֯��������
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSwjgzzjgmc()))
					// ������
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getCjr()))
					// ��������
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getCjrq()))
					// ¼����
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getLrr()))
					// ¼������
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getLrrq()))
					// ϵͳ����
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getXtjb()))
					// ����������
					.append(",'")
					.append(CodeConstant.QYQSSDS_TABLE_STR)
					// ��ע1
					.append("',")
					.append("''")
					// ��ע2
					.append(",")
					.append("''")
					// ����������Ա
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getQsllry()))
					// �����
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getTbrq()))
					// ���㱸����ʼ����
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getQsbaksrq()))

					// ���㱸����������
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getQsbajsrq()))
					// �������״̬��ʶ
					.append(",")
					.append("1")
					// �������ͨ������
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getBaShtgrq()))
					// �걨���״̬��ʶ
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSbShztbs()))
					// �걨���ͨ������
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getSbShtgrq()))
					// ��Ӫ���޽���
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJyqxjm()))
					// ��������ɢ
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getGdjyjs()))
					// ���������ر�
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getYfdxgb()))
					// ���������Ʋ�
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getYfxgpc()))
					// �����涨����
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getYfgdqs()))

					// ����ԭ��
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getQtyy()))
					// �������ʹ���Ĭ��Ϊ0
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSqlxdm()))
					// �����걨��ʼ����
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getQssbksrq()))
					// �����걨��������
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getQssbjsrq()))
					.append(") ");

			System.out.println("��ҵ��������˰-������Ϣ����SQL\n");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			 if (st != null) {
			 st.close();
			 }

			if (ud.getCaflag()) {

				System.out.println("===========ǩ����ʼ==========");
				try {
					String ywid = qyqssdsNsrJbxxVo.getNsrjbxx().getJsjdm()
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
				retData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, nsrJbxxBo);// BO
				retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);// ����ԭ��
				retData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO,
						qyqssdsNsrJbxxVo);// VO

			}
			retData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qyqssdsNsrJbxxVo);// VO

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
	 * ��ѯ��˰�˻�����Ϣ
	 * 
	 * @param pData
	 *            Map
	 * @return Map
	 * @throws BaseException
	 */
	private Object doQuery(Map pData) throws BaseException {

		
		NsrJbxxBo nsrJbxxBO = new NsrJbxxBo();
		Connection conn = null;
		try {
			// ���������
			String jsjdm = null;
			// ��ǰ����
			Timestamp curDate = null;
			// ˰��Ǽǻ�������ֵ����
			SWDJJBSJ djjbsj = (SWDJJBSJ) pData
					.get(QyqssdsConstant.OBJECT_KEY_DJSJ);
			jsjdm = (String) pData.get(QyqssdsConstant.STRING_KEY_JSJDM);
			curDate = (Timestamp) pData.get(QyqssdsConstant.STRING_KEY_DATE);// �걨����

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(curDate);
			nsrJbxxBO.setJsjdm(jsjdm);// ��˰�˼��������

			nsrJbxxBO.setNsrmc(djjbsj.getNsrmc());// ��˰������

			nsrJbxxBO.setNsrsbh(djjbsj.getSwdjzh());// ��˰��ʶ���

			nsrJbxxBO.setSsjjlxdm(djjbsj.getDjzclxdm()); // ������������-�Ǽ�ע�����ʹ���
			nsrJbxxBO.setSsjjlxmc(djjbsj.getDjzclxmc());// ������������-�Ǽ�ע����������
			//nsrJbxxBO.setLxdh(djjbsj.getZcdzlxdh()); // ע���ַ��ϵ�绰
			nsrJbxxBO.setJydz(djjbsj.getJydz());// ��Ӫ��ַ
			nsrJbxxBO.setSshydm(djjbsj.getGjbzhydm());// ������ҵ����
			nsrJbxxBO.setSshymc(djjbsj.getGjbzhymc());// ������ҵ����
			nsrJbxxBO.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm()); // ˰�������֯��������
			nsrJbxxBO.setSwjgzzjgmc(djjbsj.getSwjgzzjgmc()); // ˰�������֯��������
			nsrJbxxBO.setLrr(jsjdm);// ¼����
			nsrJbxxBO.setCjr(jsjdm);// ������
			nsrJbxxBO.setCjrq(curDate.toString().substring(0, 11));// ��������
			nsrJbxxBO.setLrrq(curDate.toString().substring(0, 11));// ¼������
			nsrJbxxBO.setQsbaksrq(curDate.toString().substring(0, 11));// Ĭ�ϵ�ǰ����

			nsrJbxxBO.setVersion(QyqssdsConstant.REPORT_VERSION_QYQSSDS_NSRJBXXB);

			// ϵͳ����
			nsrJbxxBO.setXtjb(QyqssdsConstant.QSSDS_1);

			conn = DBResource.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			StringBuffer bf = new StringBuffer();
			// select
			bf.append(" select ")
					// ��ѯ�ֶ�
					.append(" NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX, ")
					// �Ӳ�ѯ-���ݵǼ�ע�����ʹ�����ѯ�Ǽ�ע����������
					// ������������-�Ǽ�ע������
					.append(" (SELECT DJZCLXMC FROM DMDB.DJ_DM_DJZCLX T2 WHERE T2.DJZCLXDM=T1.SSJJLX) AS SSJJLXMC, ")
					.append(" LXDH,JYDZ,SSHY, ")
					// �Ӳ�ѯ-����������ҵ������ѯ������ҵ����
					.append(" (SELECT GJBZHYMC FROM DMDB.GY_DM_GJBZHY T3 WHERE T3.GJBZHYDM=T1.SSHY) AS SSHYMC,")
					.append(" SWJGZZJGDM, ")
					// �Ӳ�ѯ-����˰�������֯�����ѯ˰�������֯��������
					.append(" (SELECT SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T5 WHERE T5.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC, ")
					.append(" CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					// from
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					// where
					.append(" where  t1.nsrjsjdm = ? ");

			System.out.println("��ҵ��������˰-������Ϣ��ѯSQL");
			System.out.println(bf.toString());

			ps = conn.prepareStatement(bf.toString());
			System.out.println("1-" + nsrJbxxBO.getJsjdm());
			ps.setString(1, nsrJbxxBO.getJsjdm());
			rs = ps.executeQuery();

			if (rs.next()) {

				// ��˰�˼��������
				nsrJbxxBO.setJsjdm(rs.getString("NSRJSJDM"));
				// ��˰��ʶ��ţ�˰��Ǽ�֤��
				nsrJbxxBO.setNsrsbh(rs.getString("NSRSBH"));
				// ��˰������
				nsrJbxxBO.setNsrmc(rs.getString("NSRMC"));
				// ˰�����
				nsrJbxxBO.setVersion(rs.getString("VERSION"));
				// ������������
				nsrJbxxBO.setSsjjlxdm(rs.getString("SSJJLX"));
				// ������������
				nsrJbxxBO.setSsjjlxmc(rs.getString("SSJJLXMC"));
				// ��ϵ�绰
				nsrJbxxBO.setLxdh(rs.getString("LXDH"));
				// ��Ӫ��ַ
				nsrJbxxBO.setJydz(rs.getString("JYDZ"));
				// ������ҵ
				nsrJbxxBO.setSshydm(rs.getString("SSHY"));
				// ������ҵ
				nsrJbxxBO.setSshymc(rs.getString("SSHYMC"));
				// ˰�������֯��������
				nsrJbxxBO.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				// ˰�������֯��������
				nsrJbxxBO.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
				nsrJbxxBO.setCjr(rs.getString("CJR"));
				if (rs.getTimestamp("CJSJ") != null) {
					nsrJbxxBO.setCjrq(StringUtils.getStrFromDate(rs
							.getTimestamp("CJSJ")));
				}
				nsrJbxxBO.setCjr(rs.getString("LRR"));
				if (rs.getTimestamp("LRSJ") != null) {
					nsrJbxxBO.setLrrq(StringUtils.getStrFromDate(rs
							.getTimestamp("LRSJ")));
				}

				nsrJbxxBO.setXtjb(rs.getString("XTJB"));
				nsrJbxxBO.setBbmsf(rs.getString("BBMSF"));
				nsrJbxxBO.setQsllry(rs.getString("QSLLRY"));
				if (rs.getTimestamp("TBRQ") != null) {
					nsrJbxxBO.setTbrq(StringUtils.getStrFromDate(rs
							.getTimestamp("TBRQ")));
				}
				if (rs.getTimestamp("QSBAKSRQ") != null) {
					nsrJbxxBO.setQsbaksrq(StringUtils.getStrFromDate(rs
							.getTimestamp("QSBAKSRQ")));
				}
				if (rs.getTimestamp("QSBAJSRQ") != null) {
					nsrJbxxBO.setQsbajsrq(StringUtils.getStrFromDate(rs
							.getTimestamp("QSBAJSRQ")));
				}
				nsrJbxxBO.setBaShztbs(rs.getString("BASHZTBS"));
				if (rs.getTimestamp("BASHTGRQ") != null) {
					nsrJbxxBO.setBaShtgrq(StringUtils.getStrFromDate(rs
							.getTimestamp("BASHTGRQ")));
				}
				nsrJbxxBO.setSbShztbs(rs.getString("SBSHZTBS"));
				if (rs.getTimestamp("SBSHTGRQ") != null) {
					nsrJbxxBO.setSbShtgrq(StringUtils.getStrFromDate(rs
							.getTimestamp("SBSHTGRQ")));
				}
				nsrJbxxBO.setJyqxjm(rs.getString("JYQXJM"));
				nsrJbxxBO.setGdjyjs(rs.getString("GDJYJS"));
				nsrJbxxBO.setYfdxgb(rs.getString("YFDXGB"));
				nsrJbxxBO.setYfxgpc(rs.getString("YFXGPC"));
				nsrJbxxBO.setYfgdqs(rs.getString("YFGDQS"));
				nsrJbxxBO.setQtyy(rs.getString("QTYY"));
				nsrJbxxBO.setSqlxdm(rs.getString("SQLXDM"));
				if (rs.getTimestamp("QSSBKSRQ") != null) {
					nsrJbxxBO.setQssbksrq(StringUtils.getStrFromDate(rs
							.getTimestamp("QSSBKSRQ")));
				}
				if (rs.getTimestamp("QSSBJSRQ") != null) {
					nsrJbxxBO.setQssbjsrq(StringUtils.getStrFromDate(rs
							.getTimestamp("QSSBJSRQ")));
				}

			}

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

			if (nsrJbxxBO.getBaShztbs() == null
					|| nsrJbxxBO.getBaShztbs().equals("")) {
				nsrJbxxBO.setBaShztMessage("δ�����ύ");
			} else if ("1".equals(nsrJbxxBO.getBaShztbs())) {
				nsrJbxxBO.setBaShztMessage("���ύδ���");
			} else if ("2".equals(nsrJbxxBO.getBaShztbs())) {
				nsrJbxxBO.setBaShztMessage("�����ͨ��");
			} else if ("3".equals(nsrJbxxBO.getBaShztbs())) {
				nsrJbxxBO.setBaShztMessage("����ҵ����������˰�������뱻���أ����޸ĺ������ϴ����߽���ɾ��");
			} else if ("4".equals(nsrJbxxBO.getBaShztbs())) {
				nsrJbxxBO.setBaShztMessage("����");
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ��������˰������Ϣ����ʧ��");
		}finally{
			DBResource.destroyConnection(conn);
		}
		return nsrJbxxBO;
	}
	/**
	 * ���ñ�����������Ϣ
	 * 
	 * 
	 * @param nsrJbxxBO
	 */
	public QyqssdsReportsDeclare setQysdsReport(NsrJbxxBo form) {

		QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
		/**
		 * ������Ϣ
		 */
		QyqssdsBaJbxx jbxx = new QyqssdsBaJbxx();

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

		// /**
		// * ������Ϣ(JBXX)-���ʹ�����ʽ
		// */
		// jbxx.setGzglxs("");
		// /**
		// * ������Ϣ(JBXX)-��������
		// */
		// jbxx.setJmlx("");

		// jbxx.setBbmsf(GetJbxxBbmsf(form));
		jbxx.setBbmsf(form.getBbmsf());
		// jbxx.setBbmsf("0101,0102,0103,0104");

		report.setJbxx(jbxx);

		/**
		 * Ӧ�ñ��
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYQSSDS);
		/**
		 * �汾�� 20140101
		 */
		report.setVersion(CodeConstant.QYQSSDS_VERSION_2014);
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
		report.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
		/**
		 * �ں�
		 */
		report.setQh("1");
		/**
		 * ˰��������ʼ���� ��Ϊ �����걨��ʼ����
		 */
		if (form.getQssbksrq() != null && !"".equals(form.getQssbksrq())) {
			report.setQssbksrq(new Date(TinyTools.stringToDate(
					form.getQssbksrq(), "yyyy-MM-dd").getTime()));

		}
		/**
		 * ˰�������������� ��Ϊ �����걨��������
		 */
		if (form.getQssbjsrq() != null && !"".equals(form.getQssbjsrq())) {
			report.setQssbjsrq(new Date(TinyTools.stringToDate(
					form.getQssbjsrq(), "yyyy-MM-dd").getTime()));
		}
		/**
		 * �걨���� ��Ϊ�������ǰ����
		 */
		if (form.getTbrq() != null && !"".equals(form.getTbrq())) {
			report.setSbrq(new Date(TinyTools.stringToDate(form.getTbrq(),
					"yyyyMMdd").getTime()));
		}

		/**
		 * ˰����� Ϊ���㱸������������
		 */
		if (form.getQsbajsrq() != null && !"".equals(form.getQsbajsrq())) {
			report.setSknd(new Date(TinyTools.stringToDate(form.getQsbajsrq(),
					"yyyyMMdd").getTime()).toString().substring(0, 3));
		}
		/**
		 * ˰�������֯��������
		 */
		report.setSwjgzzjgdm(form.getSwjgzzjgdm());
		/**
		 * ˰����������
		 */
		report.setSwjsjdm(form.getJsjdm());
		/**
		 * ���ش���
		 */
		report.setQxdm(form.getSwjgzzjgdm().substring(2, 4));
		/**
		 * ¼����
		 */
		report.setLrr(form.getLrr());

		/**
		 * ������
		 */
		report.setCjr(form.getLrr());

		return report;
	}
}
