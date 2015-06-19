package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.CheckResult;
import com.syax.creports.bo.qysds.Jbxx;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.exception.BaseException;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.main.web.QysdsTabelInfo;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;

public class QysdsUtil2009 {
	
	public static void main(String[] args){
//		StringBuffer buffer=new StringBuffer();
//		//SELECT
//		buffer.append(" SELECT ");
//		//��ȡ�ֶ�
//		buffer.append(" NSRJSJDM,NSRSBH,NSRMC,ND,SSJJLX,");
//		buffer.append(" LXDH,JYDZ,SSHY,ZSFS,CKZD,GZGLXS,");
//		buffer.append(" JMLX,SWJGZZJGDM,");
//		//�Ӳ�ѯ-����˰�������֯�����ѯ˰�������֯��������
//		buffer.append(" (SELECT T2.SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC,");
//		buffer.append(" (SELECT T2.JSJDM FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJSJDM");
//		//FROM
//		buffer.append(" FROM SBDB.SB_JL_QYSDS_NSRJBXXB T1 WHERE T1.NSRJSJDM=? AND T1.ND=?");
//		
//		System.out.println("��ҵ����˰-������Ϣ��ѯSQL");
//		System.out.println(buffer.toString());
		
//		System.out.println(getTimestamp("2006-10-10"));
		
		System.out.println(preQuarter(SfDateUtil.getDate("20060428")));
		
	}
	/**
	 * ���ñ�����������Ϣ
	 * @param report
	 * @param form
	 */
	public static void setQysdsReport(QysdsReportsDeclare report,QysdsNewForm form){
		/**
		 * ������Ϣ
		 */
		Jbxx jbxx=new Jbxx();
		
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
		jbxx.setSsjjlx(form.getSsjjlx());
		/**
		 * ������Ϣ(JBXX)-��ϵ�绰
		 */
		jbxx.setLxdh(form.getLxdh());
		/**
		 * ������Ϣ(JBXX)-������ҵ
		 */
		jbxx.setSshy(form.getSshy());
		/**
		 * ������Ϣ(JBXX)-���շ�ʽ
		 */
		jbxx.setZsfs(form.getZsfs());
		/**
		 * ������Ϣ(JBXX)-�ƻ��ƶ�
		 */
		jbxx.setCkzd(form.getCkzd());
		/**
		 * ������Ϣ(JBXX)-���ʹ�����ʽ
		 */
		jbxx.setGzglxs("");
		/**
		 * ������Ϣ(JBXX)-��������
		 */
		jbxx.setJmlx("");
		
		jbxx.setBbmsf(GetJbxxBbmsf(form));
		
		report.setJbxx(jbxx);
		
		/**
		 * Ӧ�ñ��
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYSDS);
		/**
		 * �汾��
		 */
		report.setVersion(CodeConstant.QYSDS_VERSION_2009);
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
		report.setBbqlx(form.getBbqlx());
		/**
		 * �ں�
		 */
		report.setQh(form.getQh());
		/**
		 * ˰��������ʼ����
		 */
		if(form.getSkssksrq()!=null && !form.getSkssksrq().equals("")){
			report.setSkssksrq(new Date(TinyTools.stringToDate(form.getSkssksrq(),"yyyyMMdd").getTime()) );
		}
		/**
		 * ˰��������������
		 */
		if(form.getSkssjsrq()!=null && !form.getSkssjsrq().equals("")){
			report.setSkssjsrq(new Date(TinyTools.stringToDate(form.getSkssjsrq(),"yyyyMMdd").getTime()) );
		}
		/**
		 * �걨����
		 */
		if(form.getSbrq()!=null && !form.getSbrq().equals("")){
			report.setSbrq(new Date(TinyTools.stringToDate(form.getSbrq(),"yyyyMMdd").getTime()) );
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
		 * ˰����������
		 */
		report.setSwjsjdm(form.getSwjsjdm());
		/**
		 * ���ش���
		 */
		report.setQxdm(form.getQxdm());
		/**
		 * ¼����
		 */
		report.setLrr(form.getLrr());
		/**
		 * ¼������
		 */
		if(form.getLrrq()!=null && !form.getLrrq().equals("")){
//			report.setLrrq(new Date(TinyTools.stringToDate(form.getLrrq(),"yyyyMMdd").getTime()) );
		}
		report.setLrrq(new java.sql.Date(new java.util.Date().getTime()) );
		/**
		 * ������
		 */
		report.setCjr(form.getLrr());
		/**
		 * ��������
		 */
		if(form.getCjrq()!=null && !form.getCjrq().equals("")){
//			report.setCjrq(new Date(TinyTools.stringToDate(form.getCjrq(),"yyyyMMdd").getTime()));
		}
		report.setCjrq(new java.sql.Date(new java.util.Date().getTime()));
	}
	
	/**
	 * ��ѯ��ҵ����˰��˰�˻�����Ϣ
	 * @param conn ���ݿ�����
	 * @param form ��ҵ����˰Form����
	 * @return
	 * @throws SQLException
	 */
	public static String GetJbxxBbmsf(QysdsNewForm form) {
		
		String bbmsf="";
		Connection conn = null;
		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			StringBuffer buffer=new StringBuffer();
			//SELECT
			buffer.append(" SELECT ");
			//��ȡ�ֶ�
			buffer.append(" NSRJSJDM,NSRSBH,NSRMC,ND,BBMSF");
			//FROM
			buffer.append(" FROM SBDB.SB_JL_QYSDS_NSRJBXXB T1 WHERE T1.NSRJSJDM=? AND T1.ND=?");
			
			Debug.out("��ҵ����˰-������Ϣ��ѯSQL");
			System.out.println(buffer.toString());
			ps=conn.prepareStatement(buffer.toString());
			System.out.println("1-"+form.getJsjdm());
			System.out.println("2-"+form.getSknd());
			
			ps.setString(1,form.getJsjdm());
			ps.setString(2,form.getSknd());
			rs= ps.executeQuery();
			if(rs.next()){
				bbmsf=rs.getString("BBMSF");
			}else{
				throw new ApplicationException("û�и���˰�˵Ļ�����Ϣ�ǼǱ����ݣ�");
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		return bbmsf;
	}
	
	/**
	 * ��ѯ��ҵ����˰��˰�˻�����Ϣ
	 * @param conn ���ݿ�����
	 * @param form ��ҵ����˰Form����
	 * @return
	 * @throws SQLException
	 */
	public static Object queryQysdsJbxx(Connection conn,QysdsNewForm form,String sybs) throws Exception{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		StringBuffer buffer=new StringBuffer();
		//SELECT
		buffer.append(" SELECT ");
		//��ȡ�ֶ�
		buffer.append(" NSRJSJDM,NSRSBH,NSRMC,ND,SSJJLX,");
		buffer.append(" LXDH,JYDZ,SSHY,ZSFS,CKZD,GZGLXS,");
		buffer.append(" JMLX,SWJGZZJGDM,SUBSTR(SWJGZZJGDM,1,2) QXDM,");
		buffer.append(" TO_CHAR(SKSSSQQ,'YYYYMMDD') SKSSKSRQ,TO_CHAR(SKSSSQZ,'YYYYMMDD') SKSSJSRQ,");
		//�Ӳ�ѯ-����˰�������֯�����ѯ˰�������֯��������
		buffer.append(" (SELECT T2.SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC,");
		buffer.append(" (SELECT T2.JSJDM FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJSJDM");
		//FROM
		buffer.append(" FROM SBDB.SB_JL_QYSDS_NSRJBXXB T1 WHERE T1.NSRJSJDM=? AND T1.ND=?");
		
		Debug.out("��ҵ����˰-������Ϣ��ѯSQL");
		System.out.println(buffer.toString());
		
		ps=conn.prepareStatement(buffer.toString());
		System.out.println("1-"+form.getJsjdm());
		System.out.println("2-"+form.getSknd());
		
		ps.setString(1,form.getJsjdm());
		ps.setString(2,form.getSknd());
		rs= ps.executeQuery();
		if(rs.next()){
			//��˰�˼��������
			form.setJsjdm(rs.getString("NSRJSJDM"));
			//��˰��ʶ��ţ�˰��Ǽ�֤��
			form.setNsrsbh(rs.getString("NSRSBH"));
			//��˰������
			form.setNsrmc(rs.getString("NSRMC"));
			//���
			form.setSknd(rs.getString("ND"));
			//�ں�
			form.setQh("1");
			//����������
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			/*˰���������ڲ��ӻ�����Ϣ��ȡ��*/
//			//˰��������ʼ����
//			form.setSkssksrq(rs.getString("SKSSKSRQ"));
//			//˰��������������
//			form.setSkssjsrq(rs.getString("SKSSJSRQ"));
			
			form.setHid_skssksrq(form.getSkssksrq());
			form.setHid_skssjsrq(form.getSkssjsrq());
			
			//������������
			form.setSsjjlx(rs.getString("SSJJLX"));
			//��ϵ�绰
			form.setLxdh(rs.getString("LXDH"));
			//��Ӫ��ַ
			form.setJydz(rs.getString("JYDZ"));
			//������ҵ
			form.setSshy(rs.getString("SSHY"));
			//��ҵ����˰���շ�ʽ
			form.setZsfs(rs.getString("ZSFS"));
			//�ƻ��ƶ� 00:һ��01:����02:����
			form.setCkzd(rs.getString("CKZD"));
//			/**
//			 * ���ʹ�����ʽ
//			 * �ǹ�Ч����˰���ʡ�ȫ��۳�
//			 * ��  Ч����Ч�ҹ�
//			 */
//			String gzglxs=rs.getString("GZGLXS")==null?"":rs.getString("GZGLXS");
//			form.setGzglxs(rs.getString("GZGLXS"));
//			//11��30�������󣬷ǹ�Ч�ҹ��Ĳ��ٷ�Ϊ��˰���ʺ�ȫ��۳����� ��Ϊһ��
//			if(CodeConstant.GZGLXS01.equals(gzglxs) || CodeConstant.GZGLXS02.equals(gzglxs)){
////				if(CodeConstant.GZGLXS01.equals(gzglxs)){
//				form.setGzlx(CodeConstant.SMSB_GZGLLX_FGX);
//			}else if(CodeConstant.GZGLXS03.equals(gzglxs)){
//				form.setGzlx(CodeConstant.SMSB_GZGLLX_GX);
//			}
			//��������
//			form.setJmlx(rs.getString("JMLX"));
			//˰�������֯��������
			form.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
			//˰�������֯��������
			form.setZgswjgzzjgmc(rs.getString("SWJGZZJGMC"));
			//˰����������
			form.setSwjsjdm(rs.getString("SWJSJDM"));
			//���ش���
			form.setQxdm(rs.getString("QXDM"));
			//������˰��������д�������˰�걨��
			form.setTableList(getTableList(form.getCkzd(),sybs,form.getSknd()));
			//HTML������
			form.setLinkUrlHTML(getLinkUrlHtml(form.getTableList(),form));
			//���������map����
			form.setLinkMap(generateLinkMap(form.getTableList()));
			
		}else{
			throw new ApplicationException("û�и���˰�˵Ļ�����Ϣ�ǼǱ����ݣ�");
		}
		
		rs.close();
		ps.close();
		
		return form;
	}
	
	/**
	 * ��ѯ��˰�˻�����Ϣ(�ӵǼǱ��ȡ)
	 * @param conn ���ݿ�����
	 * @param form ��ҵ����˰Form����
	 * @return
	 * @throws SQLException
	 */
	public static Object queryNsrdjxx(Connection conn,QysdsNewForm form) throws Exception{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		StringBuffer buffer=new StringBuffer();
		//SELECT
		buffer.append(" SELECT ");
		//��ȡ�ֶ�
		buffer.append(" JSJDM,SWDJZH,NSRMC,DJZCLXDM,");
		buffer.append(" JYDZLXDM,JYDZ,GJBZHYDM,");
		buffer.append(" SWJGZZJGDM,SUBSTR(SWJGZZJGDM,1,2) QXDM,");
		//�Ӳ�ѯ-����˰�������֯�����ѯ˰�������֯��������
		buffer.append(" (SELECT T2.SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC,");
		buffer.append(" (SELECT T2.JSJDM FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJSJDM");
		//FROM
		buffer.append(" FROM DJDB.DJ_JL_JBSJ T1 WHERE T1.JSJDM=? ");
		
		System.out.println("��ҵ����˰-������Ϣ��ѯSQL");
		System.out.println(buffer.toString());
		
		ps=conn.prepareStatement(buffer.toString());
		ps.setString(1,form.getJsjdm());
		
		rs= ps.executeQuery();
		if(rs.next()){
			//��˰�˼��������
			form.setJsjdm(rs.getString("JSJDM"));
			//��˰��ʶ��ţ�˰��Ǽ�֤��
			form.setNsrsbh(rs.getString("SWDJZH"));
			//��˰������
			form.setNsrmc(rs.getString("NSRMC"));
			//������������
			form.setSsjjlx(rs.getString("DJZCLXDM"));
			//��ϵ�绰
			form.setLxdh(rs.getString("JYDZLXDM"));
			//��Ӫ��ַ
			form.setJydz(rs.getString("JYDZ"));
			//������ҵ
			form.setSshy(rs.getString("GJBZHYDM"));
			//˰�������֯��������
			form.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
			//˰�������֯��������
			form.setZgswjgzzjgmc(rs.getString("SWJGZZJGMC"));
			//˰����������
			form.setSwjsjdm(rs.getString("SWJSJDM"));
			//���ش���
			form.setQxdm(rs.getString("QXDM"));
		}else{
			throw new ApplicationException("û�и���˰�˵ĵǼ���Ϣ����û��Ȩ�޲鿴����˰����Ϣ��");
		}
		
		rs.close();
		ps.close();
		
		return form;
	}
	
	public static Object queryDjxxByInterfaceDJ(Connection conn,QysdsNewForm form,UserData ud) throws Exception{
		SWDJJBSJ djsj = null;
		try {
			// �����ҵ�Ǽǻ�����Ϣ
			djsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
			//��˰��ʶ���
			form.setNsrsbh(djsj.getSwdjzh()); 
			// ��˰������
			form.setNsrmc(djsj.getNsrmc()); 
			// ������������-�Ǽ�ע�����ʹ���
			form.setSsjjlx(djsj.getDjzclxdm());
			// ע���ַ��ϵ�绰
			form.setLxdh(djsj.getZcdzlxdh()); 
			// ��Ӫ��ַ
			form.setJydz(djsj.getJydz());
			// ������ҵ����
			form.setSshy(djsj.getGjbzhydm());
			// ˰�������֯��������
			form.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
			// ˰�������֯��������
			form.setZgswjgzzjgmc(djsj.getSwjgzzjgmc()); 
			// ���ش���
			form.setQxdm(djsj.getQxdm());
			form.setQxdm(djsj.getSwjgzzjgdm().substring(0, 2));
			return form;
		} catch (Exception ex1) {
			throw new ApplicationException("û�и���˰�˵ĵǼ���Ϣ����û��Ȩ�޲鿴����˰����Ϣ��");
		}
	}
	/**
	 * ����ҳ��List�еĿ�ֵ
	 * @param list
	 */
	public static List filterList(List list){
		List rtnList=new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			Iterator it=map.keySet().iterator();
			while(it.hasNext()){
				String key=(String)it.next();
				if (map.get(key)!=null && !"".equals((String)map.get(key))){
					rtnList.add(map);
					break;
				}
			}
		}
		return rtnList;
	}
	
	/**
	 * ���ݲƻ��ƶȶ�����˰��������д�������˰�걨��
	 * @param ckzd �ƻ��ƶ�
	 * @return 
	 */
	private static List getTableList(String ckzd,String sybs ,String sknd){
		
		List list=new ArrayList();
		
		QysdsTabelInfo zb =new QysdsTabelInfo();
		//����
		zb.setTableID(CodeConstant.TABLE_ID_2009_ZB);
		zb.setTableName(CodeConstant.TABLE_NAME_2009_ZB);
		zb.setURL("zbAction2009.do?actionType=Show");
		
		list.add(zb);
		
		//����ʹ�ã��ƻ��ƶ�Ϊ��TEST������ʾ���б�
		if(ckzd!=null && "99".equals(ckzd) ){
			//����һ��1��������ϸ��
			QysdsTabelInfo table_1_1 =new QysdsTabelInfo();
			table_1_1.setTableID(CodeConstant.TABLE_ID_2009_1_1);
			table_1_1.setTableName(CodeConstant.TABLE_NAME_2009_1_1);
			table_1_1.setURL("srmxbAction2009.do?actionType=Show");
			list.add(table_1_1);
			
			//����һ��2��������ҵ������ϸ��
			QysdsTabelInfo table_1_2 =new QysdsTabelInfo();
			table_1_2.setTableID(CodeConstant.TABLE_ID_2009_1_2);
			table_1_2.setTableName(CodeConstant.TABLE_NAME_2009_1_2);
			table_1_2.setURL("jrqysrmxAction2009.do?actionType=Show");
			list.add(table_1_2);
			
			//����һ��3����ҵ��λ��������塢������ҵ��λ������ϸ��
			QysdsTabelInfo table_1_3 =new QysdsTabelInfo();
			table_1_3.setTableID(CodeConstant.TABLE_ID_2009_1_3);
			table_1_3.setTableName(CodeConstant.TABLE_NAME_2009_1_3);
			table_1_3.setURL("srbsydw2009Action.do?actionType=Show");
			list.add(table_1_3);
			
			//�������1���ɱ�������ϸ��
			QysdsTabelInfo table_2_1 =new QysdsTabelInfo();
			table_2_1.setTableID(CodeConstant.TABLE_ID_2009_2_1);
			table_2_1.setTableName(CodeConstant.TABLE_NAME_2009_2_1);
			table_2_1.setURL("cbmxbybqyAction2009.do?actionType=Show");
			list.add(table_2_1);
			
			//�������2��������ҵ�ɱ�������ϸ��
			QysdsTabelInfo table_2_2 =new QysdsTabelInfo();
			table_2_2.setTableID(CodeConstant.TABLE_ID_2009_2_2);
			table_2_2.setTableName(CodeConstant.TABLE_NAME_2009_2_2);
			table_2_2.setURL("jrqycbfymxbAction2009.do?actionType=Show");
			list.add(table_2_2);
			
			//�����(3) ��ҵ��λ��������塢������ҵ��λ֧����ϸ��
			QysdsTabelInfo table_2_3 =new QysdsTabelInfo();
			table_2_3.setTableID(CodeConstant.TABLE_ID_2009_2_3);
			table_2_3.setTableName(CodeConstant.TABLE_NAME_2009_2_3);
			table_2_3.setURL("systmbfqyzcmxAction2009.do?actionType=Show");
			list.add(table_2_3);
		}
		
		//����˰��ѡ��һ����ҵ����û��ѡ��ʱ��ϵͳ�Զ����Ƹ���˰��������д�������˰�걨��ʽΪ
		if(ckzd==null || 
				(ckzd!=null && "".equals(ckzd) )
				||(ckzd!=null && CodeConstant.CWKJZD01.equals(ckzd) ) ){
			
			//����һ��1��������ϸ��
			QysdsTabelInfo table_1_1 =new QysdsTabelInfo();
			table_1_1.setTableID(CodeConstant.TABLE_ID_2009_1_1);
			table_1_1.setTableName(CodeConstant.TABLE_NAME_2009_1_1);
			table_1_1.setURL("srmxbAction2009.do?actionType=Show");
			list.add(table_1_1);
			
			//�������1���ɱ�������ϸ��
			QysdsTabelInfo table_2_1 =new QysdsTabelInfo();
			table_2_1.setTableID(CodeConstant.TABLE_ID_2009_2_1);
			table_2_1.setTableName(CodeConstant.TABLE_NAME_2009_2_1);
			table_2_1.setURL("cbmxbybqyAction2009.do?actionType=Show");
			list.add(table_2_1);
		}
		
		//����˰��ѡ�������ҵ����ƶ�ʱ��ϵͳ�Զ����Ƹ���˰��������д�������˰�걨��ʽΪ
		if(ckzd!=null && CodeConstant.CWKJZD02.equals(ckzd) ){
			
			//����һ��2��������ҵ������ϸ��
			QysdsTabelInfo table_1_2 =new QysdsTabelInfo();
			table_1_2.setTableID(CodeConstant.TABLE_ID_2009_1_2);
			table_1_2.setTableName(CodeConstant.TABLE_NAME_2009_1_2);
			table_1_2.setURL("jrqysrmxAction2009.do?actionType=Show");
			list.add(table_1_2);
			
			//�������2��������ҵ�ɱ�������ϸ��
			QysdsTabelInfo table_2_2 =new QysdsTabelInfo();
			table_2_2.setTableID(CodeConstant.TABLE_ID_2009_2_2);
			table_2_2.setTableName(CodeConstant.TABLE_NAME_2009_2_2);
			table_2_2.setURL("jrqycbfymxbAction2009.do?actionType=Show");
			list.add(table_2_2);
		
		}
		
		//����˰��ѡ����ҵ��λ��������塢������ҵ��λ����ƶ�ʱ��ϵͳ�Զ����Ƹ���˰��������д�������˰�걨��ʽΪ
		if(ckzd!=null && CodeConstant.CWKJZD03.equals(ckzd) ){
	
			//����һ��3����ҵ��λ��������塢������ҵ��λ������ϸ��
			QysdsTabelInfo table_1_3 =new QysdsTabelInfo();
			table_1_3.setTableID(CodeConstant.TABLE_ID_2009_1_3);
			table_1_3.setTableName(CodeConstant.TABLE_NAME_2009_1_3);
			table_1_3.setURL("srbsydw2009Action.do?actionType=Show");
			list.add(table_1_3);
			
			//�����(3) ��ҵ��λ��������塢������ҵ��λ֧����ϸ��
			QysdsTabelInfo table_2_3 =new QysdsTabelInfo();
			table_2_3.setTableID(CodeConstant.TABLE_ID_2009_2_3);
			table_2_3.setTableName(CodeConstant.TABLE_NAME_2009_2_3);
			table_2_3.setURL("systmbfqyzcmxAction2009.do?actionType=Show");
			list.add(table_2_3);

		}
		
		//������ ��˰������Ŀ��ϸ��
		QysdsTabelInfo table_3 =new QysdsTabelInfo();
		table_3.setTableID(CodeConstant.TABLE_ID_2009_3);
		table_3.setTableName(CodeConstant.TABLE_NAME_2009_3);
		table_3.setURL("nstzmxbAction2009.do?actionType=Show");
		list.add(table_3);
		
		//������ ��ҵ����˰�ֲ�������ϸ��
		QysdsTabelInfo table_4 =new QysdsTabelInfo();
		table_4.setTableID(CodeConstant.TABLE_ID_2009_4);
		table_4.setTableName(CodeConstant.TABLE_NAME_2009_4);
		table_4.setURL("qysdsmbksmxbAction2009.do?actionType=Show");
		list.add(table_4);
		
		//������ ˰���Ż���ϸ��
		QysdsTabelInfo table_5 =new QysdsTabelInfo();
		table_5.setTableID(CodeConstant.TABLE_ID_2009_5);
		table_5.setTableName(CodeConstant.TABLE_NAME_2009_5);
		table_5.setURL("ssyhmxAction2009.do?actionType=Show");
		list.add(table_5);
		

		
		//������ ��������˰���������ϸ��
		QysdsTabelInfo table_6 =new QysdsTabelInfo();
		table_6.setTableID(CodeConstant.TABLE_ID_2009_6);
		table_6.setTableName(CodeConstant.TABLE_NAME_2009_6);
		table_6.setURL("jwsdmxbAction2009.do?actionType=Show");
		list.add(table_6);
		
		//������ �Թ��ʼ�ֵ�����ʲ���˰������
		QysdsTabelInfo table_7 =new QysdsTabelInfo();
		table_7.setTableID(CodeConstant.TABLE_ID_2009_7);
		table_7.setTableName(CodeConstant.TABLE_NAME_2009_7);
		table_7.setURL("ygyjzjlnstzbAction.do?actionType=Show");
		list.add(table_7);
		
		//����� ���Ѻ�ҵ�������ѿ������˰������
		QysdsTabelInfo table_8 =new QysdsTabelInfo();
		table_8.setTableID(CodeConstant.TABLE_ID_2009_8);
		table_8.setTableName(CodeConstant.TABLE_NAME_2009_8);
		table_8.setURL("ggAction2009.do?actionType=Show");
		list.add(table_8);
		
		//����� �ʲ��۾ɡ�̯����˰������ϸ��
		QysdsTabelInfo table_9 =new QysdsTabelInfo();
		table_9.setTableID(CodeConstant.TABLE_ID_2009_9);
		table_9.setTableName(CodeConstant.TABLE_NAME_2009_9);
		table_9.setURL("zczjtxnstzmxbAction2009.do?actionType=Show");
		list.add(table_9);
		
		//������ʮ �ʲ���ֵ׼����Ŀ������ϸ��
		QysdsTabelInfo table_10 =new QysdsTabelInfo();
		table_10.setTableID(CodeConstant.TABLE_ID_2009_10);
		table_10.setTableName(CodeConstant.TABLE_NAME_2009_10);
		table_10.setURL("zcjztzmxAction2009.do?actionType=Show");
		list.add(table_10);
		
		//����ʮһ ��ȨͶ�����ã���ʧ����ϸ��
		QysdsTabelInfo table_11 =new QysdsTabelInfo();
		table_11.setTableID(CodeConstant.TABLE_ID_2009_11);
		table_11.setTableName(CodeConstant.TABLE_NAME_2009_11);
		table_11.setURL("gqtzsdmxbAction.do?actionType=Show");
		list.add(table_11);
		
		//˰Դ��ʶΪ�� ��˰����ȴ���2012���  ��ҵ����˰������˰�ܻ�������� 
		if(Integer.valueOf(sknd).intValue()>2012){
			if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSZJG)){
				//����ʮ�� ��ҵ����˰������˰�ܻ��������
				QysdsTabelInfo table_12 =new QysdsTabelInfo();
				table_12.setTableID(CodeConstant.TABLE_ID_2012_12);
				table_12.setTableName(CodeConstant.TABLE_NAME_2012_12);
				table_12.setURL("hznszjgfpbAction.do?actionType=Query");
				list.add(table_12);
			}
			
			if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
				list=new ArrayList();
			}
		}
		return list;
	}
	
	/**
	 * ����һ��HTMLƬ�ϣ�����Ϊ������
	 * @param list �����б�
	 * @return
	 * @throws BaseException 
	 */
	private static String getLinkUrlHtml(List list){
		StringBuffer buffer=new StringBuffer();
		
		//��3��չ�ֱ�����,ÿ�����չʾrow������ 
		int row=6;
		StringBuffer buffer1=new StringBuffer();
		StringBuffer buffer2=new StringBuffer();
		StringBuffer buffer3=new StringBuffer();
		
		buffer1.append("<TD>");
		buffer2.append("<TD>");
		buffer3.append("<TD>");
		
		for(int i=1;i<=list.size();i++){
			QysdsTabelInfo table=(QysdsTabelInfo)list.get(i-1);
			if(i<=row){
				buffer1.append("<DIV>");
				buffer1.append("<A HREF='");
				buffer1.append(table.getURL()+"'>");
				buffer1.append(table.getTableName());
				buffer1.append("</A>");
				buffer1.append("</DIV>");
			}
			if(i<=2*row && i>row){
				buffer2.append("<DIV>");
				buffer2.append("<A HREF='");
				buffer2.append(table.getURL()+"'>");
				buffer2.append(table.getTableName());
				buffer2.append("</A>");
				buffer2.append("</DIV>");
			}
			if( i>2*row){
				buffer3.append("<DIV>");
				buffer3.append("<A HREF='");
				buffer3.append(table.getURL()+"'>");
				buffer3.append(table.getTableName());
				buffer3.append("</A>");
				buffer3.append("</DIV>");
			}
		}
		
		buffer1.append("</TD>");
		buffer2.append("</TD>");
		buffer3.append("</TD>");
		
		buffer.append(buffer1.toString()+buffer2.toString()+buffer3.toString());
		
		return buffer.toString();
	}
	
	/**
	 * ����һ��HTMLƬ�ϣ�����Ϊ������
	 * @param list �����б�
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws BaseException 
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws BaseException 
	 */
	public static String getLinkUrlHtml(List list,QysdsNewForm form) throws Exception {
		StringBuffer buffer=new StringBuffer();
		QysdsReportsDeclare report=new QysdsReportsDeclare();
		setQysdsReport(report,form);
		Connection conn = null; 
		try {
			conn = SfDBResource.getConnection();
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			
			//��3��չ�ֱ�����,ÿ�����չʾrow������ 
			int row=7;
			StringBuffer buffer1=new StringBuffer();
			StringBuffer buffer2=new StringBuffer();
			StringBuffer buffer3=new StringBuffer();
			
			buffer1.append("<TD>");
			buffer2.append("<TD>");
			buffer3.append("<TD>");
			
			for(int i=1;i<=list.size();i++){
				QysdsTabelInfo table=(QysdsTabelInfo)list.get(i-1);
				QysdsReportsTableDeclare iTable=new QysdsReportsTableDeclare();
				iTable.setTableId(table.getTableID());
				report.getTableContentList().clear();
				report.getTableContentList().put(table.getTableID(),iTable);
				String shbz=iApp.queryCheckStatus(report);
				if(i<=row){
					buffer1.append("<DIV>");
					if(shbz.equals(Constants.QYSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYSDS_SHZT_ALL_PASS)){
						buffer1.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer1.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer1.append("<A ");
					buffer1.append("id='TableID_"+table.getTableID()+"' ");
					buffer1.append(" onClick='link2Table("+table.getTableID()+")'");
					buffer1.append(" HREF='"+table.getURL()+"'>");
					buffer1.append(table.getTableName());
					buffer1.append("</A>");
					buffer1.append("</DIV>");
				}
				if(i<=2*row && i>row){
					buffer2.append("<DIV>");
					if(shbz.equals(Constants.QYSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYSDS_SHZT_ALL_PASS)){
						buffer2.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer2.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer2.append("<A ");
					buffer2.append("id='TableID_"+table.getTableID()+"' ");
					buffer2.append(" onClick='link2Table("+table.getTableID()+")'");
					buffer2.append(" HREF='"+table.getURL()+"'>");
					buffer2.append(table.getTableName());
					buffer2.append("</A>");
					buffer2.append("</DIV>");
				}
				if( i>2*row){
					buffer3.append("<DIV>");
					if(shbz.equals(Constants.QYSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYSDS_SHZT_ALL_PASS)){
						buffer3.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer3.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer3.append("<A ");
					buffer3.append("id='TableID_"+table.getTableID()+"' ");
					buffer3.append(" onClick='link2Table("+table.getTableID()+")'");
					buffer3.append(" HREF='"+table.getURL()+"'>");
					buffer3.append(table.getTableName());
					buffer3.append("</A>");
					buffer3.append("</DIV>");
				}
			}
			
			buffer1.append("</TD>");
			buffer2.append("</TD>");
			buffer3.append("</TD>");
			
			buffer.append(buffer1.toString()+buffer2.toString()+buffer3.toString());
			
			return buffer.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			SfDBResource.freeConnection(conn);
		}
	}
	
	/**
	 * ���˷���table�����еĿո�
	 * @param table
	 * @return
	 */
	public static QysdsReportsTableDeclare cleanSpace(QysdsReportsTableDeclare table){
		Iterator it = table.getCellContentList().keySet().iterator();
		Map map=new HashMap();
		while(it.hasNext()){
			String key = (String)it.next();
			
			QysdsReportsItemDeclare item =(QysdsReportsItemDeclare)table.getCellContentList().get(key);
			if(!"".equals(item.getItemValue().trim())){
				map.put(key, item);
				Debug.out("key--"+key);
			}				
		}
		table.setCellContentList(map);
		return table;
		
	}
	
	/**
	 * �Ѵ������ʱ���˵��Ŀո�ԭ
	 * @param table
	 * @param a
	 * @return
	 */
	public static QysdsReportsTableDeclare putSpace(QysdsReportsTableDeclare table,int arrs[]) {
		System.out.println("**��ʾqysdsNewUtil�е�putSpace()**");
		for(int j=1;j<=arrs.length;j=j+2){			
			System.out.println("j___  "+j+"***"+arrs.length);
			for(int i=arrs[j-1];i<=arrs[j];i++){
				QysdsReportsItemDeclare item =(QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(i));
				if(item==null ){
					QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue("");
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				}else if(item!=null && item.getItemValue()!=null && "".equals(item.getItemValue().trim())){
					QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue("");
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				}				
			}
		}	
		return table;
	}
	
	/**
	 * ����У��ӿڷ��ص�У����List����ҳ����ʾ��Ϣ��HTML��
	 * @param list
	 * @return
	 */
	public static String getCheckResults(List list){
		
		StringBuffer buffer =new StringBuffer();
		
		//���У����ListΪnull,���䳤��Ϊ�㣬˵��У��ͨ�������ؿ��ַ���
		if(list==null || ( list!=null && list.size()==0)){
			return "";
		}
		
		buffer.append("<html><link href='../../../css/text.css' rel='stylesheet' type='text/css'><title>"+"У����</title>");
		buffer.append("<table border='1' cellspacing='0' class='table-99' align='center'>");
		buffer.append("<tr>");
		buffer.append("<td class='2-td1-center'>");
		buffer.append("У�����б�");
		buffer.append("</td>");
		buffer.append("</tr>");
		
		for(int i=0;i<list.size();i++){
			CheckResult checkResult=(CheckResult)list.get(i);
			buffer.append("<tr>");
			buffer.append("<td class='2-td2-align-left'>");
			buffer.append(String.valueOf(i+1)+"��  "+checkResult.getResultDescription().replaceAll("\"","&quot;")+"&nbsp;");
			buffer.append("</td>");
			buffer.append("</tr>");
		}
		
		buffer.append("</html>");
		System.out.println("-----------------------------buffer------------");
		System.out.println(buffer.toString());
		return buffer.toString();
	}
	
	/**
	 * ��ָ������ת��Ϊ Timestamp
	 * @param date  ָ�����ڸ�ʽΪ "20030908"
	 * @return  Timestamp
	 */
	public static Timestamp getTimestamp(String dateStr) {
		
		java.util.Date date = null; 
		
		String tmp = SBStringUtils.trim(dateStr);
		tmp = SBStringUtils.killNull(tmp);
		tmp = replaceAll(tmp,"-","");
//		System.out.println(tmp);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			date = sdf.parse(tmp);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
		}
		
		return second2Day(date);
	}
	
	
//	��java.util.Dateת��Ϊ��java.sql.Timestamp
	/**��java.util.Dateת��Ϊ��java.sql.Timestamp
	 * @param java.util.Date
	 * @return java.sql.Timestamp
	 */
	public static java.sql.Timestamp second2Day(java.util.Date date)
	{
		if (date==null)
			return new Timestamp(System.currentTimeMillis());
		java.sql.Timestamp tempStamp = null;
		
		try
		{
			String tempStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
			tempStamp = java.sql.Timestamp.valueOf(tempStr +" 00:00:00.000");
		}
		catch (Exception ex) {}
		
		return tempStamp;
	}
	
	
	
	/**
	 * �滻�ַ���
	 * @param original Դ�ַ���
	 * @param find �����ַ���
	 * @param replacement �滻�ַ���
	 * @return �滻����ַ���
	 */
	public final static String replaceAll(String original, String find,
			String replacement) {
		StringBuffer buffer = new StringBuffer(original);
		
		int idx = original.length();
		int offset = find.length();
		
		while ( (idx = original.lastIndexOf(find, idx - 1)) > -1) {
			buffer.replace(idx, idx + offset, replacement);
		}
		
		return buffer.toString();
	}
	
	/**
	 * ��ָ������ת��Ϊ Timestamp
	 * @param date  ָ�����ڸ�ʽΪ "20030908"
	 * @return  Timestamp
	 */
	public static Timestamp getNxetTimestamp(String dateStr) {
		
		java.util.Date date = null; 
		
		String tmp = SBStringUtils.trim(dateStr);
		tmp = SBStringUtils.killNull(tmp);
		tmp = replaceAll(tmp,"-","");
//		System.out.println(tmp);
		
		
		try {			    	
			java.util.Date dateD = SBStringUtils.getDate(tmp, "yyyyMMdd");
			
			date = TinyTools.addDay(1, dateD);    	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
		}
		
		return second2Day(date);
	}
	
	/**
	 * ���ر�����URL
	 * @param key ����Ϣ
	 * @return
	 */
	public static String getTableURL(QysdsNewForm form,String key){
		return (String)form.getLinkMap().get(key);
	}
	
	/**
	 * �жϵ�ǰ���Ƿ�Ϊ���һ�ű�
	 * @param form
	 * @param key
	 * @return
	 */
	public static String isLastTable(QysdsNewForm form,String key){
		QysdsTabelInfo table=(QysdsTabelInfo)form.getTableList().get(form.getTableList().size()-1);
		if(key.equalsIgnoreCase(table.getTableID())){
			return "yes";
		}else{
			return "no";
		}
	}
	
//	/**
//	* ȡ��һ�ʺ���ҵ��˰����,����ҳ��У��
//	* 
//	* @param form
//	* @throws BaseException
//	*/
//	public static String getZsfsdm(QysdsNewForm form) throws BaseException {
//	String re = "";
//	
//	// ��ǰʱ��
////	Timestamp sbrq = new Timestamp(System.currentTimeMillis());
//	//���걨ҳ��ȡ���걨���ں�˰����������
//	Timestamp sbrq = QysdsUtil2009.getNxetTimestamp(form.getSkssjsrq());
//	
////	Map getsbjd = this.quarterSkssrq(sbrq);
//	Timestamp skssksrq = QysdsUtil2009.getTimestamp(form.getSkssksrq());
//	Timestamp skssjsrq = QysdsUtil2009.getTimestamp(form.getSkssjsrq());
//	
//	
//	System.out.println(form.getJsjdm()+"sbrq��"+sbrq);
//	System.out.println(form.getJsjdm()+"skssksrq��"+skssksrq);
//	System.out.println(form.getJsjdm()+"skssjsrq��"+skssjsrq);
//	
//	ServiceProxy proxy = new ServiceProxy();
//	
//	String bblx = form.getBbqlx();
//	String jsjdm = form.getJsjdm();
//	
//	// ��ѯ˰�ѽӿ�
//	QysdsSet qysdsSet = null;
//	
//	
//	
//	try {
//	if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_YEAR)) {
//	qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYSDS_BBFS_NB);
//	} else if(bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR)){
//	
//	if(form.getQh()==null || (form.getQh()!=null && form.getQh().trim().equals(""))){
//	/*�ںŲ���Ϊ�գ����Ϊ���׳��쳣*/
//	throw new ApplicationException("ϵͳ�����쳣���ں�Ϊ�գ�����ϵͳ����Ա��ϵ��");
//	}
//	
//	System.out.println("form.getQh()::"+form.getQh());
//	
//	
//	if("4".equals(form.getQh())){
//	/*���Ϊ���ļ��ȣ���ȡ��ҵ����˰�϶���Ϣʱ���걨����ȡ*/
//	qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYSDS_BBFS_NB);
//	}else{
//	qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYSDS_BBFS_JB);
//	}
//	}			
//	
//	
//	} catch (com.ttsoft.framework.exception.BaseException e) {
//	e.printStackTrace();
//	throw ExceptionUtil.getBaseException(e);
//	}
//	
//	// 1����ѯ��ҵ���շ�ʽ
//	Zsfs zsfs = qysdsSet.getZsfs();
//	if (zsfs != null) {
//	
//	re = (zsfs.getZsfsdm()==null?"":zsfs.getZsfsdm());
//	
//	}
//	return re;
//	
//	}
	
	/**
	 * ���������Map��LinkMap��
	 */
	private static Map generateLinkMap(List list){
		
		Map linkMap=new HashMap();
		
		for (int i = 0; i < list.size(); i++) {
			
			//��ǰ��
			QysdsTabelInfo table=(QysdsTabelInfo)list.get(i);
			if( i!=list.size()-1 ){
				
					
				//��ǰ�����һ�ű�
				QysdsTabelInfo next_table=(QysdsTabelInfo)list.get(i+1);
				//Ϊ��ǰ��������һ�ű�����
				linkMap.put("N_"+table.getTableID(),next_table.getURL());				
				//Ϊ��һ�ű�������һ�ű�����
				linkMap.put("P_"+next_table.getTableID(),table.getURL());			
			}else{
				//���һ�ű�
				QysdsTabelInfo pre_table=(QysdsTabelInfo)list.get(i-1);
				linkMap.put("P_"+table.getTableID(),pre_table.getURL());			
			}
		}


		return linkMap;
	}
	
	/**
	 * ȡ�ô����������ڵļ���
	 * @param curDate ����
	 * @return String ����
	 */
	public static String preQuarter (java.util.Date curDate)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(curDate);
		int month = calendar.get(calendar.MONTH);
		int year = calendar.get(calendar.YEAR);
		System.out.println(month);
		int jd = month / 3;
		jd++;
		return new Integer(jd).toString();
	}
	
	
	
	
}
