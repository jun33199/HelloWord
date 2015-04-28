package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util;

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
import com.syax.creports.bo.qyqssds.QyqssdsBaJbxx;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsItemDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.exception.BaseException;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.util.DateUtils;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.web.QyqssdsTabelInfo2014;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;

public class QyqssdsUtil2014 {
	
	public static void main(String[] args){

		System.out.println(preQuarter(SfDateUtil.getDate("20060428")));
		
		System.out.println(new Date(TinyTools.stringToDate("2014-01-01","yyyy-MM-dd").getTime()));
	}
	/**
	 * ���ñ�����������Ϣ
	 * @param report
	 * @param form
	 */
	public static void setQyqssdsReport(QyqssdsReportsDeclare report,QyqssdsBaseForm form){
		/**
		 * ������Ϣ
		 */
		QyqssdsBaJbxx jbxx=new QyqssdsBaJbxx();
		
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
		
//		/**
//		 * ������Ϣ(JBXX)-���ʹ�����ʽ
//		 */
//		jbxx.setGzglxs("");
//		/**
//		 * ������Ϣ(JBXX)-��������
//		 */
//		jbxx.setJmlx("");
		
		jbxx.setBbmsf(GetJbxxBbmsf(form));
//		jbxx.setBbmsf("0101,0102,0103,0104");
		
		report.setJbxx(jbxx);
		
		/**
		 * Ӧ�ñ��
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYQSSDS);
		/**
		 * �汾��
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
		 * ˰��������ʼ����   
		 * ��Ϊ �����걨��ʼ����
		 */
		System.out.println("************"+form.getQssbksrq());
		if(form.getQssbksrq()!=null && !form.getQssbksrq().equals("")){
			report.setQssbksrq(new Date(TinyTools.stringToDate(form.getQssbksrq(),"yyyy-MM-dd").getTime()) );
			System.out.println(new Date(TinyTools.stringToDate(form.getQssbksrq(),"yyyy-MM-dd").getTime()) );
			System.out.println("************"+report.getQssbksrq());
		}
		/**
		 * ˰��������������
		 * ��Ϊ �����걨��������
		 */
		if(form.getQssbjsrq()!=null && !form.getQssbjsrq().equals("")){
			report.setQssbjsrq(new Date(TinyTools.stringToDate(form.getQssbjsrq(),"yyyy-MM-dd").getTime()) );
		}
		/**
		 * �걨����
		 * ��Ϊ�������ǰ����
		 */
		if(form.getTbrq()!=null && !form.getTbrq().equals("")){
			report.setSbrq(new Date(TinyTools.stringToDate(form.getTbrq(),"yyyyMMdd").getTime()) );
			System.out.println(form.getTbrq());
			
		}
		
		/**
		 * ˰�����  Ϊ���㱸������������
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
	public static String GetJbxxBbmsf(QyqssdsBaseForm form) {
		
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
			buffer.append(" NSRJSJDM,NSRSBH,NSRMC,BBMSF");
			//FROM
			buffer.append(" FROM SBDB.SB_JL_QYQSSDSBA_NSRJBXXB T1 WHERE T1.NSRJSJDM=? ");
			
			Debug.out("��ҵ����˰-������Ϣ��ѯSQL");
			System.out.println(buffer.toString());
			ps=conn.prepareStatement(buffer.toString());
			System.out.println("1-"+form.getJsjdm());
			
			ps.setString(1,form.getJsjdm());
			
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
	public static Object queryQyqssdsJbxx(Connection conn,QyqssdsBaseForm form) throws Exception{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		StringBuffer buffer=new StringBuffer();
		//SELECT
		buffer.append(" SELECT ");
		//��ȡ�ֶ�
		buffer.append(" NSRJSJDM,NSRSBH,NSRMC,SSJJLX, ");
		buffer.append(" LXDH,JYDZ,SSHY,QSLLRY, ");
		buffer.append(" SWJGZZJGDM,SUBSTR(SWJGZZJGDM,1,2) QXDM, BASHZTBS, ");
		buffer.append(" TO_CHAR(BASHTGRQ,'YYYYMMDD') BASHTGRQ, ");
		buffer.append(" TO_CHAR(QSSBKSRQ,'YYYY-MM-DD') QSSBKSRQ, ");
		buffer.append(" TO_CHAR(QSSBJSRQ,'YYYY-MM-DD') QSSBJSRQ,SQLXDM,SBSHZTBS,  ");
		//�Ӳ�ѯ-����˰�������֯�����ѯ˰�������֯��������
		buffer.append(" (SELECT T2.SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC, ");
		buffer.append(" (SELECT T2.JSJDM FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJSJDM ");
		//FROM
		buffer.append(" FROM SBDB.SB_JL_QYQSSDSBA_NSRJBXXB T1 WHERE T1.NSRJSJDM=?");
		
		Debug.out("��ҵ����˰-������Ϣ��ѯSQL");
		System.out.println(buffer.toString());
		
		ps=conn.prepareStatement(buffer.toString());
		System.out.println("1-"+form.getJsjdm());
		ps.setString(1,form.getJsjdm());
	
		rs= ps.executeQuery();
		if(rs.next()){
			//��˰�˼��������
			form.setJsjdm(rs.getString("NSRJSJDM"));
			//��˰��ʶ��ţ�˰��Ǽ�֤��
			form.setNsrsbh(rs.getString("NSRSBH"));
			//��˰������
			form.setNsrmc(rs.getString("NSRMC"));
			
			//�ں�
			form.setQh("1");
			//����������
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			/*˰���������ڲ��ӻ�����Ϣ��ȡ��*/
			//˰��������ʼ����
			form.setBaShtgrq(rs.getString("BASHTGRQ"));	
			form.setBaShztbs(rs.getString("BASHZTBS"));	
			if(!form.getBaShztbs().equals("2")){
				throw new ApplicationException("����˰�˵���ҵ��������˰�������ݻ�û�����ͨ��������������ݣ�");
			}
			if(form.getBaShtgrq()!=null){
				form.setQssbksrq(DateUtils.toHyphenDate(form.getBaShtgrq()));
			}
			
			String qssbksrq=rs.getString("QSSBKSRQ");
			String qssbjsrq=rs.getString("QSSBJSRQ");
			form.setIsBcsbrq("0");
			if(qssbksrq!=null && qssbksrq.length()>0){
				form.setQssbksrq(qssbksrq);
				form.setIsBcsbrq("1");
			}
			if(qssbjsrq!=null && qssbjsrq.length()>0){
				form.setQssbjsrq(qssbjsrq);
			}
			//������������
			form.setSsjjlx(rs.getString("SSJJLX"));
			//��ϵ�绰
			form.setLxdh(rs.getString("LXDH"));
			//��Ӫ��ַ
			form.setJydz(rs.getString("JYDZ"));
			//������ҵ
			form.setSshy(rs.getString("SSHY"));
			//�����������������Ա
			form.setQsllry(rs.getString("QSLLRY"));
			//˰�������֯��������
			form.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
			//˰�������֯��������
			form.setZgswjgzzjgmc(rs.getString("SWJGZZJGMC"));
			//˰����������
			form.setSwjsjdm(rs.getString("SWJSJDM"));
			//���ش���
			form.setQxdm(rs.getString("QXDM"));
			//������˰��������д�������˰�걨��
			form.setTableList(getTableList());
			//HTML������
			form.setLinkUrlHTML(getLinkUrlHtml(form.getTableList(),form));
			//���������map����
			form.setLinkMap(generateLinkMap(form.getTableList()));
			//�������ʹ���
			form.setSqlx(rs.getString("SQLXDM"));
			//�걨���״̬��ʶ
			form.setSbShztbs(rs.getString("SBSHZTBS")==null?"":rs.getString("SBSHZTBS"));
			
		}else{
			throw new ApplicationException("û�и���˰�˵���ҵ��������˰�������ݣ�");
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
	public static Object queryNsrdjxx(Connection conn,QyqssdsBaseForm form) throws Exception{
		
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
	
	public static Object queryDjxxByInterfaceDJ(Connection conn,QyqssdsBaseForm form,UserData ud) throws Exception{
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
	 * ������д�������˰�걨��
	 * @param 
	 * @return 
	 */
	private static List getTableList(){
		
		List list=new ArrayList();
		
		QyqssdsTabelInfo2014 qyqssdsZb =new QyqssdsTabelInfo2014();
		//����
		qyqssdsZb.setTableID(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
		qyqssdsZb.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_ZB);
		qyqssdsZb.setURL("qyqssdsZbAction2014.do?actionType=Show");
		list.add(qyqssdsZb);

		//����һ �ʲ�����������ϸ��
		QyqssdsTabelInfo2014 qyqssds_table_1 =new QyqssdsTabelInfo2014();
		qyqssds_table_1.setTableID(CodeConstant.QYQSSDS_TABLE_ID_2014_1);
		qyqssds_table_1.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_1);
		qyqssds_table_1.setURL("qyqssdsFb1Action2014.do?actionType=Show");
		list.add(qyqssds_table_1);
		
		//����� ��ծ�峥������ϸ��
		QyqssdsTabelInfo2014 qyqssds_table_2 =new QyqssdsTabelInfo2014();
		qyqssds_table_2.setTableID(CodeConstant.QYQSSDS_TABLE_ID_2014_2);
		qyqssds_table_2.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_2);
		qyqssds_table_2.setURL("qyqssdsFb2Action2014.do?actionType=Show");
		list.add(qyqssds_table_2);
		
		//������  ʣ��Ʋ�����ͷ�����ϸ��
		QyqssdsTabelInfo2014 qyqssds_table_3 =new QyqssdsTabelInfo2014();
		qyqssds_table_3.setTableID(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
		qyqssds_table_3.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
		qyqssds_table_3.setURL("qyqssdsFb3Action2014.do?actionType=Show");
		list.add(qyqssds_table_3);
		
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
			QyqssdsTabelInfo2014 table=(QyqssdsTabelInfo2014)list.get(i-1);
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
	public static String getLinkUrlHtml(List list,QyqssdsBaseForm form) throws Exception {
		StringBuffer buffer=new StringBuffer();
		QyqssdsReportsDeclare report=new QyqssdsReportsDeclare();
		setQyqssdsReport(report,form);
		Connection conn = null; 
		try {
			conn = SfDBResource.getConnection();
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			
			//��3��չ�ֱ�����,ÿ�����չʾrow������ 
			int row=2;
			StringBuffer buffer1=new StringBuffer();
			StringBuffer buffer2=new StringBuffer();
			StringBuffer buffer3=new StringBuffer();
			
			buffer1.append("<TD>");
			buffer2.append("<TD>");
			buffer3.append("<TD>");
			
			for(int i=1;i<=list.size();i++){
				QyqssdsTabelInfo2014 table=(QyqssdsTabelInfo2014)list.get(i-1);
				QyqssdsReportsTableDeclare iTable=new QyqssdsReportsTableDeclare();
				iTable.setTableId(table.getTableID());
				report.getTableContentList().clear();
				report.getTableContentList().put(table.getTableID(),iTable);
				String shbz=iApp.queryCheckStatus(report);
				if(i<=row){
					buffer1.append("<DIV>");
					if(shbz.equals(Constants.QYQSSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYQSSDS_SHZT_ALL_PASS)){
						buffer1.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer1.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer1.append("<A ");
					buffer1.append("id='TableID_"+table.getTableID()+"' ");
					buffer1.append(" onClick='return link2Table(\""+table.getTableID()+"\");'");
					buffer1.append(" HREF='"+table.getURL()+"'>");
					buffer1.append(table.getTableName());
					buffer1.append("</A>");
					buffer1.append("</DIV>");
				}
				if(i<=2*row && i>row){
					buffer2.append("<DIV>");
					if(shbz.equals(Constants.QYQSSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYQSSDS_SHZT_ALL_PASS)){
						buffer2.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer2.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer2.append("<A ");
					buffer2.append("id='TableID_"+table.getTableID()+"' ");
					buffer2.append(" onClick='return link2Table(\""+table.getTableID()+"\");'");
					buffer2.append(" HREF='"+table.getURL()+"'>");
					buffer2.append(table.getTableName());
					buffer2.append("</A>");
					buffer2.append("</DIV>");
				}
				if( i>2*row){
					buffer3.append("<DIV>");
					if(shbz.equals(Constants.QYQSSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYQSSDS_SHZT_ALL_PASS)){
						buffer3.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer3.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer3.append("<A ");
					buffer3.append("id='TableID_"+table.getTableID()+"' ");
					buffer3.append(" onClick='return link2Table(\""+table.getTableID()+"\");'");
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
	public static QyqssdsReportsTableDeclare cleanSpace(QyqssdsReportsTableDeclare table){
		Iterator it = table.getCellContentList().keySet().iterator();
		Map map=new HashMap();
		while(it.hasNext()){
			String key = (String)it.next();
			
			QyqssdsReportsItemDeclare item =(QyqssdsReportsItemDeclare)table.getCellContentList().get(key);
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
	public static QyqssdsReportsTableDeclare putSpace(QyqssdsReportsTableDeclare table,int arrs[]) {
		System.out.println("**��ʾqyqssdsNewUtil�е�putSpace()**");
		for(int j=1;j<=arrs.length;j=j+2){			
			System.out.println("j___  "+j+"***"+arrs.length);
			for(int i=arrs[j-1];i<=arrs[j];i++){
				QyqssdsReportsItemDeclare item =(QyqssdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(i));
				if(item==null ){
					QyqssdsReportsItemDeclare item1 = new QyqssdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue("");
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				}else if(item!=null && item.getItemValue()!=null && "".equals(item.getItemValue().trim())){
					QyqssdsReportsItemDeclare item1 = new QyqssdsReportsItemDeclare();
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
	public static String getTableURL(QyqssdsBaseForm form,String key){
		return (String)form.getLinkMap().get(key);
	}
	
	/**
	 * �жϵ�ǰ���Ƿ�Ϊ���һ�ű�
	 * @param form
	 * @param key
	 * @return
	 */
	public static String isLastTable(QyqssdsBaseForm form,String key){
		QyqssdsTabelInfo2014 table=(QyqssdsTabelInfo2014)form.getTableList().get(form.getTableList().size()-1);
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
//	public static String getZsfsdm(QyqssdsNewForm form) throws BaseException {
//	String re = "";
//	
//	// ��ǰʱ��
////	Timestamp sbrq = new Timestamp(System.currentTimeMillis());
//	//���걨ҳ��ȡ���걨���ں�˰����������
//	Timestamp sbrq = QyqssdsUtil2009.getNxetTimestamp(form.getSkssjsrq());
//	
////	Map getsbjd = this.quarterSkssrq(sbrq);
//	Timestamp skssksrq = QyqssdsUtil2009.getTimestamp(form.getSkssksrq());
//	Timestamp skssjsrq = QyqssdsUtil2009.getTimestamp(form.getSkssjsrq());
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
//	QyqssdsSet qyqssdsSet = null;
//	
//	
//	
//	try {
//	if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS)) {
//	qyqssdsSet = proxy.getQyqssdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYQSSDS_BBFS_NB);
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
//	qyqssdsSet = proxy.getQyqssdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYQSSDS_BBFS_NB);
//	}else{
//	qyqssdsSet = proxy.getQyqssdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYQSSDS_BBFS_JB);
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
//	Zsfs zsfs = qyqssdsSet.getZsfs();
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
			QyqssdsTabelInfo2014 table=(QyqssdsTabelInfo2014)list.get(i);
			if( i!=list.size()-1 ){
				
					
				//��ǰ�����һ�ű�
				QyqssdsTabelInfo2014 next_table=(QyqssdsTabelInfo2014)list.get(i+1);
				//Ϊ��ǰ��������һ�ű�����
				linkMap.put("N_"+table.getTableID(),next_table.getURL());				
				//Ϊ��һ�ű�������һ�ű�����
				linkMap.put("P_"+next_table.getTableID(),table.getURL());			
			}else{
				//���һ�ű�
				QyqssdsTabelInfo2014 pre_table=(QyqssdsTabelInfo2014)list.get(i-1);
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
	
	
	/**
	 * ����Ȩ�޹���
	 * @param qxdm
	 * @param band
	 * @param yhid
	 * @param jmbasxdm
	 * @param conn
	 * @return
	 * @throws BaseException
	 * @throws com.ttsoft.framework.exception.BaseException 
	 */
	public static void getAlertStrWhenAdd(String jsjdm,UserData ud) throws com.ttsoft.framework.exception.BaseException

{
		
		
		SWDJJBSJ djsj = null;
		try {
			// �����ҵ�Ǽǻ�����Ϣ
			djsj = InterfaceDj.getJBSJ_New(jsjdm, ud);
		} catch (Exception ex1) {
			throw ExceptionUtil.getBaseException(ex1);
		}		
		String nsrSwjgzzjgdm=djsj.getSwjgzzjgdm();
		String ssdwdm=ud.getSsdwdm();		
		String yhjb=ud.getYhjb();
		
		if(yhjb.equals("40")){
			if(!ssdwdm.substring(0,2).equals(nsrSwjgzzjgdm.substring(0,2))){
				throw new ApplicationException("����˰�˲��������ܻ������ܶԸ���˰�˽��б�����");
			}
		}
		if(yhjb.equals("30")){
			if(!ssdwdm.equals(nsrSwjgzzjgdm)){
				throw new ApplicationException("����˰�˲��������ܻ������ܶԸ���˰�˽��б�����");
			}
		}
		
		
	}
	
	/**
	 * �������б��е��걨��ʼ���ں��걨��������
	 * @param conn ���ݿ�����
	 * @param form ��ҵ����˰Form����
	 * @return
	 * @throws SQLException
	 */
	public static Object updateAllDate(Connection conn,QyqssdsBaseForm form) throws Exception{
		
		PreparedStatement ps=null;
		
		StringBuffer bufferZbNd=new StringBuffer();
		StringBuffer bufferNsrjbxxb=new StringBuffer();
		StringBuffer bufferCzzb=new StringBuffer();
		bufferZbNd.append("update SBDB.SB_JL_QYQSSDSSBB_ZB_ND set qssbksrq=to_date(?,'YYYY-MM-DD'),qssbjsrq=to_date(?,'YYYY-MM-DD') where nsrjsjdm=?");
		bufferNsrjbxxb.append("update SBDB.SB_JL_QYQSSDSBA_NSRJBXXB set qssbksrq=to_date(?,'YYYY-MM-DD'),qssbjsrq=to_date(?,'YYYY-MM-DD') where nsrjsjdm=?");
		bufferCzzb.append("update SBDB.SB_JL_QYQSSDSSBB_CZZB set qssbksrq=to_date(?,'YYYY-MM-DD'),qssbjsrq=to_date(?,'YYYY-MM-DD') where nsrjsjdm=?");
		ps=conn.prepareStatement(bufferZbNd.toString());
		ps.setString(1, form.getQssbksrq());
		ps.setString(2, form.getQssbjsrq());
		ps.setString(3,form.getJsjdm());
		ps.executeUpdate();
		
		ps=conn.prepareStatement(bufferNsrjbxxb.toString());
		ps.setString(1, form.getQssbksrq());
		ps.setString(2, form.getQssbjsrq());
		ps.setString(3,form.getJsjdm());
		ps.executeUpdate();
		
		
		ps=conn.prepareStatement(bufferCzzb.toString());
		ps.setString(1, form.getQssbksrq());
		ps.setString(2, form.getQssbjsrq());
		ps.setString(3,form.getJsjdm());
		ps.executeUpdate();
		
		ps.close();
		
		return form;
	}
		/**
    * �÷������Լ���Ƿ���������ڣ��Ƿ���Ӧ����
    * @Description: TODO
    * @param checkBean
    * @return
    */
   public static boolean checkJd(CheckBean checkBean, String subject)
   {   	
	   
	try {
		if(subject.equals("6")){
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject6, checkBean);
		}else{
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject5, checkBean);
		}
		
	} catch (com.ttsoft.framework.exception.BaseException e) {

		return false;
	}			
   	return true;
   }
	
}
