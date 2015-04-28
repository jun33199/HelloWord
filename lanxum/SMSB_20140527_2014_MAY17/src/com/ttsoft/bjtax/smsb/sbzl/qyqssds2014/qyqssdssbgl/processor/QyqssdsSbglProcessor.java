package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdssbgl.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdssbgl.web.QyqssdsSbglForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsSbglProcessor implements Processor {
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
		case CodeConstant.SMSB_ADDACTION:
			result = doAdd(vo);
			break;
		case CodeConstant.SMSB_DELETEACTION:
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

		QyqssdsSbglForm form = (QyqssdsSbglForm) vo.getData();
		UserData ud = vo.getUserData();
		Connection conn = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = SfDBResource.getConnection();
			
			list = new ArrayList();
			String sql = "select swjgzzjgdm,swjgzzjgmc from dmdb.gy_dm_swjgzzjg where zxbs='0' ";
			String ssdwdm = ud.getSsdwdm();
			String yhjb = ud.getYhjb();

			if (yhjb.equals("50")) {
				sql += " and ccbs='1' ";
			}
			if (yhjb.equals("40")) {
				sql += " and ccbs='2'  and jgznlx='1'  and swjgzzjgdm like '"
						+ ssdwdm.substring(0, 2) + "%'";
			}
			if (yhjb.equals("30")) {
				sql += " and swjgzzjgdm ='" + ssdwdm + "'";
			}
			sql += " order by swjgzzjgdm";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String swjgzzjgdm = rs.getString("SWJGZZJGDM");
				String swjgzzjgmc = rs.getString("SWJGZZJGMC");
				DmVo dmvo = new DmVo();
				dmvo.setDm(swjgzzjgdm);
				dmvo.setMc(swjgzzjgmc);
				list.add(dmvo);
			}
			form.setFilter_zgswjgList(list);
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			SfDBResource.freeConnection(conn);
		}

		return form;
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
		
		QyqssdsSbglForm qyqssdsBaglForm = (QyqssdsSbglForm) vo.getData();
		UserData ud=vo.getUserData();
		String ssdwdm=ud.getSsdwdm();		
		String yhjb=ud.getYhjb();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();

		try {
			String check="<a href=\"javascript:doOperate('''||a.nsrjsjdm||''',''"+CodeConstant.QYQSSDSBAGL_CZLX_CHECK+"'')\">���</a>";
			String view="<a href=\"javascript:doView('''||a.nsrjsjdm||''',''"+CodeConstant.QYQSSDSBAGL_CZLX_VIEW+"'')\">�鿴</a>";
			String delete="<a href=\"javascript:doDelete('''||a.nsrjsjdm||''')\">ɾ��</a>";
			String add="<a href=\"javascript:doAdd('''||a.nsrjsjdm||''',''"+CodeConstant.QYQSSDSBAGL_CZLX_ADD+"'')\">����</a>";
			conn = SfDBResource.getConnection();				
			StringBuffer sb = new StringBuffer();
			sb.append(" select a.nsrjsjdm || decode(c.yhdllx, '02', '(֤���û�)', '') jsjdm, ");
			sb.append(" a.nsrmc nsrmc,  ");
			sb.append(" d.swjgzzjgmc swjgzzjgmc, ");
			sb.append(" TO_CHAR(a.QSBAKSRQ,'YYYY') qsband, ");
			sb.append(" decode(a.sqlxdm,'0','��������','1','��������') sqlx, ");
			sb.append(" decode(a.sbshztbs, '1','�ύδ���', '2','�����ͨ��','3', '��˱�����','��δ�ύ' ) sbshztbs, ");
			//sb.append(" case when  a.sbshztbs = '2' or a.sbshztbs = '3' then '"+view+"'  ");
			//sb.append(" when a.sbshztbs = '1'  then '"+check+"'  ");
			//sb.append(" when a.sbshztbs <> '2' then '"+delete+"' ");
			sb.append(" case when a.sbshztbs = '1'  then '"+check+"&nbsp;"+delete+"'  ");
			sb.append(" when a.sbshztbs = '2'  then '"+view+"'  ");
			sb.append(" when a.sbshztbs = '3'  then '"+view+"&nbsp;"+delete+"'  ");
			sb.append(" when a.sbshztbs = '4'  then '"+delete+"'  ");
			sb.append(" else '"+add+"' ");
			sb.append(" end cz");
			sb.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB a ,djdb.dj_jl_jbsj b,aqdb.zk_jl_wsyh c ,dmdb.gy_dm_swjgzzjg d ");
			sb.append(" where a.nsrjsjdm=b.jsjdm and a.nsrjsjdm=c.yhid and a.bashztbs = '2' and a.swjgzzjgdm=d.swjgzzjgdm ");
			
			String jsjdm=qyqssdsBaglForm.getFilter_jsjdm();
			String nsrmc=qyqssdsBaglForm.getFilter_nsrmc();
			String band=qyqssdsBaglForm.getFilter_band();
			String sqlx=qyqssdsBaglForm.getFilter_sqlx();
			String sqzt=qyqssdsBaglForm.getFilter_sqzt();
			String zgswjg=qyqssdsBaglForm.getFilter_zgswjg();
			
			if(jsjdm != null && jsjdm.trim().length()>0){
	        	sb.append(" and a.nsrjsjdm='"+jsjdm+"' ");        	
	        }
			if(nsrmc != null && nsrmc.trim().length()>0){
				sb.append(" and b.nsrmc like '%"+nsrmc+"%' ");    	
	        }
			if(band != null && band.trim().length()>0){
				sb.append(" and TO_CHAR(a.QSBAKSRQ,'YYYY')='"+band+"' "); 	
	        }
			if(sqlx != null && sqlx.trim().length()>0){
				sb.append(" and a.sqlxdm='"+sqlx+"' ");
	        }
			if(sqzt != null && sqzt.trim().length()>0){
				sb.append(" and a.sbshztbs  in ("+sqzt+") ");
	        }
			
			if(yhjb.equals("50")){
				if(zgswjg != null && zgswjg.trim().length()>0)
				sb.append(" and a.swjgzzjgdm like '"+zgswjg.substring(0,2)+"%' ");	
	        }
			if(yhjb.equals("40")){
				
				if(zgswjg != null && zgswjg.trim().length()>0)
					sb.append(" and a.swjgzzjgdm = '"+zgswjg+"' ");	
				else
					sb.append(" and a.swjgzzjgdm like '"+ssdwdm.substring(0,2)+"%' ");	
			}
			if(yhjb.equals("30")){
				sb.append(" and a.swjgzzjgdm = '"+ssdwdm+"' ");
			}
			
			
			
			System.out.println(sb.toString());
			
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			 
	            while (rs.next()) {
	                Map map = new HashMap();
	                map.put("COL_1", rs.getString("JSJDM"));
	                map.put("COL_2", rs.getString("NSRMC"));
	                map.put("COL_3", rs.getString("SWJGZZJGMC"));
	                map.put("COL_4", rs.getString("qsband"));
	                map.put("COL_5", rs.getString("SQLX"));
	                map.put("COL_6", rs.getString("sbshztbs"));
	                map.put("COL_7", rs.getString("CZ"));
	                list.add(map);
	            }
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			SfDBResource.freeConnection(conn);
		}
		return list;
	}
	
	private Object doAdd(VOPackage vo) throws BaseException {
		QyqssdsSbglForm qyqssdsBaglForm = (QyqssdsSbglForm) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();
			String showSql="select NSRJSJDM from sbdb.sb_jl_qyqssdsba_nsrjbxxb  where nsrjsjdm=?";
			ps = conn.prepareStatement(showSql);
			ps.setString(1, qyqssdsBaglForm.getFilter_jsjdm());
			rs = ps.executeQuery();
			if(rs.next()){
				qyqssdsBaglForm.setIsExistedSb(true);
				return qyqssdsBaglForm;

			}else{
				 		
				qyqssdsBaglForm.setIsExistedSb(false);
				return qyqssdsBaglForm;
			}

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}
		
	}
	private Object doDelete(VOPackage vo) throws BaseException {

		QyqssdsSbglForm form = (QyqssdsSbglForm) vo.getData();

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
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			QyqssdsUtil2014.setQyqssdsReport(qd,form);

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
			/*--�޸���ʷ���д����¼����Ϊ��ǰ�ˣ��걨���״̬��ʶΪ5��ɾ����--*/
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
							+ "REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
							+ "'5',SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,"
							+ "SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(SBStringUtils.getSQLStr(jsjdm)).append(" ) ");

			System.out.println("��ҵ��������˰-������Ϣ������ʷ����SQL");
			System.out.println(bf.toString());

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

	
}
