package com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.common.model.UserData;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class QysdsJmsbajlMainProcessor implements Processor {
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
		case CodeConstant.SMSB_QUERYZFBG:
			result = doQueryZfbg(vo);
			break;	
		case CodeConstant.SMSB_ZFBGACTION:
			result = doZfbg(vo);
			break;			
		case CodeConstant.SMSB_QUERYACTION1:
			result = doChaxun(vo);
			break;
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.QYSDSJMSBAJL_BEFORCHECK:
			result = doBeforeCheck(vo);
			break;	
		case CodeConstant.SMSB_DELETEACTION:
			result = doDelete(vo);
			break;	
		default:
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}

		return result;
	}
	
	   private String nullToSpace(String value){
	    	if(value == null || value.length()<=0)
	    	return "";
	    	else 
	    	return value;
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
		
		QysdsJmsbajlMainForm form = (QysdsJmsbajlMainForm) vo.getData();
		UserData ud=vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		List list=new ArrayList();
		try {
			conn = SfDBResource.getConnection();		
			ps = conn.prepareStatement(" select jmbasxdm,jmbasxmc from dmdb.sf_dm_jmbasxdm order by jmbasxdm" );
			rs = ps.executeQuery();
			while (rs.next()) {	
				String jmbasxdm=rs.getString("JMBASXDM");
				String jmbasxmc=rs.getString("JMBASXMC");
				DmVo dmvo=new DmVo();
				dmvo.setDm(jmbasxdm);
				dmvo.setMc(jmbasxmc);
				list.add(dmvo);		
			}
			form.setFilter_jmsbasxList(list);
			
			list=new ArrayList();
			String sql="select swjgzzjgdm,swjgzzjgmc from dmdb.gy_dm_swjgzzjg where zxbs='0' ";
			String ssdwdm=ud.getSsdwdm();		
			String yhjb=ud.getYhjb();
			
			if(yhjb.equals("50")){
				sql+=" and ccbs='1' ";
			}
			if(yhjb.equals("40")){
				sql+=" and ccbs='2'  and jgznlx='1'  and swjgzzjgdm like '"+ssdwdm.substring(0,2)+"%'" ; 
			}
			if(yhjb.equals("30")){
				sql+=" and swjgzzjgdm ='"+ssdwdm+"'";
			}
			
			sql+=" order by swjgzzjgdm";
			
			ps1 = conn.prepareStatement(sql);
			rs1 = ps1.executeQuery();
			while (rs1.next()) {
				String swjgzzjgdm=rs1.getString("SWJGZZJGDM");
				String swjgzzjgmc=rs1.getString("SWJGZZJGMC");
				DmVo dmvo=new DmVo();				
				dmvo.setDm(swjgzzjgdm);
				dmvo.setMc(swjgzzjgmc);
				list.add(dmvo);		
			}
			form.setFilter_zgswjgList(list);
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs1 != null) {
				rs1.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
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
		
		QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) vo.getData();
		UserData ud=vo.getUserData();
		String ssdwdm=ud.getSsdwdm();		
		String yhjb=ud.getYhjb();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();

		try {
			String check="<a href=\"javascript:doOperate('''||a.basqwsh||''',''"+CodeConstant.QYSDSJMSBAJL_CZLX_CHECK+"'')\">���</a>";
			String modify="<a href=\"javascript:doOperate('''||a.basqwsh||''',''"+CodeConstant.QYSDSJMSBAJL_CZLX_MODIFY+"'')\">�޸�</a>";
			String view="<a href=\"javascript:doOperate('''||a.basqwsh||''',''"+CodeConstant.QYSDSJMSBAJL_CZLX_VIEW+"'')\">�鿴</a>";
			String modify2="<a href=\"javascript:doOperate('''||a.basqwsh||''',''"+CodeConstant.QYSDSJMSBAJL_CZLX_BGZX+"'')\">���</a>";
			String delete="<a href=\"javascript:doDelete('''||a.basqwsh||''','''||a.jmbasxdm||''')\">ɾ��</a>";
			conn = SfDBResource.getConnection();				
			StringBuffer sb = new StringBuffer();
			sb.append(" select a.jsjdm||decode(c.yhdllx,'02','(֤���û�)','') jsjdm,b.nsrmc nsrmc,d.swjgzzjgmc swjgzzjgmc,a.band band, ");
			sb.append(" A.BASQBH,  ");
			sb.append(" (select c.jmbasxmc from dmdb.sf_dm_jmbasxdm c where c.jmbasxdm=a.jmbasxdm) jmbasxmc, ");
			sb.append(" decode(a.sqlxdm,'0','��������','1','��������') sqlx, ");
			sb.append(" decode(a.sqzt,'2','�����','3','�ύδ���','4','�����ͨ��','5','���δͨ��','6','������') sqzt, ");
			sb.append(" case when a.sqzt='4' or a.sqzt='5' or a.sqzt='6' then '"+view+"'  ");
			sb.append(" when a.sqzt='3' and c.yhdllx='02' then '"+check+"'  ");
			sb.append(" when a.sqzt='3' and c.yhdllx<>'02' then '"+check+"&nbsp;"+modify+"'  ");
			sb.append("  when a.sqzt='2' then '"+modify2+"' ");
			sb.append(" else null ");
			sb.append(" end cz");
			sb.append(" from sfdb.sf_jl_qysdsjmsbajl a ,djdb.dj_jl_jbsj b,aqdb.zk_jl_wsyh c ,dmdb.gy_dm_swjgzzjg d ");
			sb.append(" where a.jsjdm=b.jsjdm and a.jsjdm=c.yhid and a.sqzt<>'1' and a.swjgzzjgdm=d.swjgzzjgdm ");
			
			String jsjdm=qysdsJmsbajlMainForm.getFilter_jsjdm();
			String nsrmc=qysdsJmsbajlMainForm.getFilter_nsrmc();
			String band=qysdsJmsbajlMainForm.getFilter_band();
			String sqlx=qysdsJmsbajlMainForm.getFilter_sqlx();
			String sqzt=qysdsJmsbajlMainForm.getFilter_sqzt();
			String jmsbasx=qysdsJmsbajlMainForm.getFilter_jmsbasx();
			String zgswjg=qysdsJmsbajlMainForm.getFilter_zgswjg();
			
			if(jsjdm != null && jsjdm.trim().length()>0){
	        	sb.append(" and a.jsjdm='"+jsjdm+"' ");        	
	        }
			if(nsrmc != null && nsrmc.trim().length()>0){
				sb.append(" and b.nsrmc like '%"+nsrmc+"%' ");    	
	        }
			if(band != null && band.trim().length()>0){
				sb.append(" and a.band='"+band+"' "); 	
	        }
			if(sqlx != null && sqlx.trim().length()>0){
				sb.append(" and a.sqlxdm='"+sqlx+"' ");
	        }
			if(sqzt != null && sqzt.trim().length()>0){
				sb.append(" and a.sqzt  in ("+sqzt+") ");
	        }
			if(jmsbasx != null && jmsbasx.trim().length()>0){
				sb.append(" and a.jmbasxdm='"+jmsbasx+"' ");	
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
			
			
			
			sb.append("order by jmbasxdm");
			
			
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			 
	            while (rs.next()) {
	                Map map = new HashMap();
	                map.put("COL_1", rs.getString("JSJDM"));
	                map.put("COL_2", rs.getString("NSRMC"));
	                map.put("COL_3", rs.getString("SWJGZZJGMC"));
	                map.put("COL_4", rs.getString("JMBASXMC"));
	                map.put("COL_5", rs.getString("BASQBH"));
	                map.put("COL_6", rs.getString("BAND"));
	                map.put("COL_7", rs.getString("SQLX"));
	                map.put("COL_8", rs.getString("SQZT"));
	                map.put("COL_9", rs.getString("CZ"));
	                list.add(map);
	            }
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
		return list;
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

	private Object doQueryZfbg(VOPackage vo) throws BaseException {
		
		QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) vo.getData();
		UserData ud=vo.getUserData();
		String ssdwdm=ud.getSsdwdm();		
		String yhjb=ud.getYhjb();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();

		try {
		
			conn = SfDBResource.getConnection();				
			StringBuffer sb = new StringBuffer();
			String jsjdm_href="'<a href=\"javascript:doOperate('''||a.basqwsh||''',''"+CodeConstant.QYSDSJMSBAJL_CZLX_ZFBG+"'')\">'||a.jsjdm||'</a>'";			
			sb.append(" select "+jsjdm_href+" jsjdm,b.nsrmc nsrmc,d.swjgzzjgmc swjgzzjgmc,a.band band, ");
			sb.append(" A.BASQBH,  ");
			sb.append(" (select c.jmbasxmc from dmdb.sf_dm_jmbasxdm c where c.jmbasxdm=a.jmbasxdm) jmbasxmc, ");
			sb.append(" decode(a.sqlxdm,'0','��������','1','��������') sqlx");
			
			sb.append(" from sfdb.sf_jl_qysdsjmsbajl a ,djdb.dj_jl_jbsj b,dmdb.gy_dm_swjgzzjg d ");
			sb.append(" where a.jsjdm=b.jsjdm  and a.sqzt<>'1' and a.swjgzzjgdm=d.swjgzzjgdm and a.sqzt='4' ");
			
			String basqbh=qysdsJmsbajlMainForm.getFilter_basqbh();
			String jsjdm=qysdsJmsbajlMainForm.getFilter_jsjdm();
			String nsrmc=qysdsJmsbajlMainForm.getFilter_nsrmc();
			String band=qysdsJmsbajlMainForm.getFilter_band();
			String sqlx=qysdsJmsbajlMainForm.getFilter_sqlx();			
			String jmsbasx=qysdsJmsbajlMainForm.getFilter_jmsbasx();
			String zgswjg=qysdsJmsbajlMainForm.getFilter_zgswjg();
			
			if(basqbh != null && basqbh.trim().length()>0){
	        	sb.append(" and a.basqbh='"+basqbh+"' ");        	
	        }			
			if(jsjdm != null && jsjdm.trim().length()>0){
	        	sb.append(" and a.jsjdm='"+jsjdm+"' ");        	
	        }
			if(nsrmc != null && nsrmc.trim().length()>0){
				sb.append(" and b.nsrmc like '%"+nsrmc+"%' ");    	
	        }
			if(band != null && band.trim().length()>0){
				sb.append(" and a.band='"+band+"' "); 	
	        }
			if(sqlx != null && sqlx.trim().length()>0){
				sb.append(" and a.sqlxdm='"+sqlx+"' ");
	        }			
			if(jmsbasx != null && jmsbasx.trim().length()>0){
				sb.append(" and a.jmbasxdm='"+jmsbasx+"' ");	
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
			sb.append("order by jmbasxdm");			
			
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			 
	            while (rs.next()) {
	                Map map = new HashMap();
	                map.put("COL_1", rs.getString("JSJDM"));
	                map.put("COL_2", rs.getString("NSRMC"));
	                map.put("COL_3", rs.getString("SWJGZZJGMC"));
	                map.put("COL_4", rs.getString("JMBASXMC"));
	                map.put("COL_5", rs.getString("BASQBH"));
	                map.put("COL_6", rs.getString("BAND"));
	                map.put("COL_7", rs.getString("SQLX"));	               
	                list.add(map);
	            }
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
		return list;
	}
	
	
	/**
	 * �޸ġ����ʱ��ѯ����˰���
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doChaxun(VOPackage vo) throws BaseException {
		
		QysdsJmsbajlMainForm form = (QysdsJmsbajlMainForm) vo.getData();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();
			
			String sql = "SELECT s.jmbasxdm FROM sfdb.sf_jl_qysdsjmsbajl s WHERE s.basqwsh = '"+form.getBasqwsh()+"'";
			
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				form.setJmbasxdm(rs.getString("JMBASXDM"));
			}
			if(rs != null){
				rs.close();
			}
			if(pst != null){
				pst.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();	
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}
	
	
	/**
	 * ����ҳ����Ϣ
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		
		UserData ud=vo.getUserData();
		QysdsJmsbajlMainForm form = (QysdsJmsbajlMainForm) vo.getData();
		Connection conn = null;
		PreparedStatement pst = null;
		//��ȡ�������
		String band=form.getAdd_band();
		//��ȡ��½�û������ش���
		QysdsUtil.getAlertStrWhenAdd(form.getAdd_jsjdm(), band, ud);
		
		String qxdm=ud.getSsdwdm().substring(0, 2);
		try {
			conn = SfDBResource.getConnection();
			
			//���ù��÷�����ȡ�ñ�����������źͱ������
			HashMap map = QysdsUtil.getBasqbh(qxdm,band,form.getAdd_jsjdm(),form.getAdd_jmsbasx(),conn);
			String basqwsh = (String) map.get("basqwsh");
			String basqbh = (String) map.get("basqbh");
			form.setBasqwsh(basqwsh);
			form.setBasqbh(basqbh);
			if(pst != null){
				pst.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();	
			throw new ApplicationException("��ȡ�������������ʧ�ܣ������²�����");
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}
	
	/**
	 * doDeleteɾ��ҳ��Ԫ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doDelete(VOPackage vo) throws BaseException {
		
		QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) vo.getData();
		Connection conn = null;

		Statement stmt1 = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		String basqwsh=qysdsJmsbajlMainForm.getBasqwsh();
		String jmbasxdm=qysdsJmsbajlMainForm.getJmbasxdm();
		List list=new ArrayList();
		try {
			conn = SfDBResource.getConnection();
			
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt1.execute("delete "+QysdsUtil.getTableNameByJmbasxdm(jmbasxdm)+" where basqwsh='"+basqwsh+"'");
			stmt2.execute("delete sfdb.sf_jl_qysdsjmsbajlzlqd where basqwsh='"+basqwsh+"'");
			stmt3.execute("delete sfdb.sf_jl_qysdsjmsbajl where basqwsh='"+basqwsh+"'");			
			
			
			if (stmt3 != null) {
				stmt3.close();
			}
			if (stmt2 != null) {
				stmt2.close();
			}
			if (stmt1 != null) {
				stmt1.close();
			}
		} catch (Exception ex) {
			
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {		
			SfDBResource.freeConnection(conn);
		}

		return list;
	}
	
	
	/**
	 * doDeleteɾ��ҳ��Ԫ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doZfbg(VOPackage vo) throws BaseException {
		
		QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) vo.getData();
		UserData ud=vo.getUserData();
		String yhid=ud.getYhid();
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2=null;
		PreparedStatement ps3=null;
		PreparedStatement ps4=null;
		Statement stmtZf = null;
		Statement stmtBg = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		
		String basqwsh=qysdsJmsbajlMainForm.getBasqwsh();
		String zfbglx=qysdsJmsbajlMainForm.getZfbglx();
		String zfbglxzw=(zfbglx.equals("0"))?"���ϱ�����¼":"�����������";
		String zfsm=qysdsJmsbajlMainForm.getZfsm();
			
		String returnStr="";
		String var_jsjdm="";
		String var_band="";
		String var_jmbasxdm="";
		String var_subcode="";
		int var_count=0;
		String var_jmbasxdm2="";
		float var_yz=0;
		String var_hcwz="";
		
	    boolean var_zxflag=false ; 	
		
		try {
			conn = SfDBResource.getConnection();
			//���ݱ������������ȡ�ü�������롢������ȡ����ⱸ���������
			ps1 = conn.prepareStatement(" select jsjdm ,band,jmbasxdm  from sfdb.sf_jl_qysdsjmsbajl a where a.basqwsh='"+basqwsh+"'");
			rs1 = ps1.executeQuery();
			while(rs1.next()){
				var_jsjdm=rs1.getString("jsjdm");
				var_band=rs1.getString("band");
				var_jmbasxdm=rs1.getString("jmbasxdm");
			}
			
			if(zfbglx.equals("0")){			
				
				if(var_jmbasxdm.equals("0050")){
					ps2=conn.prepareStatement("select a.nlmyjmxmdm subcode  from sfdb.sf_jl_qysdsjmsba_05 a where a.basqwsh='"+basqwsh+"'");
					rs2=ps2.executeQuery();
					while(rs2.next()){
						var_subcode=rs2.getString("subcode");
					}
					
				    /*ȡ�ø���˰�˶�Ӧ������ȴ˱��������ж�Ӧũ��������Ŀ������ͨ����˵ļ�¼��*/
			        ps3=conn.prepareStatement("select count(*) count from sfdb.sf_jl_qysdsjmsbajl a " +
			        		" where  a.jsjdm='"+var_jsjdm+"' and a.band='"+var_band+"'  and a.jmbasxdm='"+var_jmbasxdm+"' and a.sqzt='4'	" +
			        		" and (select b.nlmyjmxmdm from sfdb.sf_jl_qysdsjmsba_05 b where a.basqwsh=b.basqwsh and rownum=1)='"+var_subcode+"'");	
			        rs3=ps3.executeQuery();
			        while(rs3.next()){
			        	var_count=Integer.parseInt(rs3.getString("count"));
			        }
				}else if(var_jmbasxdm.equals("014B")){
					
					ps2=conn.prepareStatement(" select (select b.fjddm from dmdb.sf_dm_zysblx b where a.zysblxdm=b.zysblxdm)" +
							"  subcode from sfdb.sf_jl_qysdsjmsba_14b a where a.basqwsh='"+basqwsh+"'");
					rs2=ps2.executeQuery();
					while(rs2.next()){
						var_subcode=rs2.getString("subcode");
					}
					
				    /*ȡ�ø���˰�˶�Ӧ������ȴ˱��������ж�Ӧũ��������Ŀ������ͨ����˵ļ�¼��*/
			        ps3=conn.prepareStatement("select count(*) count from sfdb.sf_jl_qysdsjmsbajl a "+
					        " where  a.jsjdm='"+var_jsjdm+"' and a.band='"+var_band+"'  and a.jmbasxdm='"+var_jmbasxdm+"' and a.sqzt='4' "+
					        " and (select (select c.fjddm from dmdb.sf_dm_zysblx c where b.zysblxdm=c.zysblxdm) "+
					        " from sfdb.sf_jl_qysdsjmsba_14b b  where a.basqwsh=b.basqwsh and rownum=1)='"+var_subcode+"'");	
			        rs3=ps3.executeQuery();
			        while(rs3.next()){
			        	var_count=Integer.parseInt(rs3.getString("count"));
			        }
				}else{
					 ps3=conn.prepareStatement(" select count(*)  count from sfdb.sf_jl_qysdsjmsbajl a "+
					 		" where  a.jsjdm='"+var_jsjdm+"' and a.band='"+var_band+"' and a.jmbasxdm='"+var_jmbasxdm+"' and a.sqzt='4'");	
					 rs3=ps3.executeQuery();
					 while(rs3.next()){
			        	var_count=Integer.parseInt(rs3.getString("count"));
			        }
					
				}
				
				if(var_count>1){
					var_zxflag=true;
				}else if(var_count==1){
					var_jmbasxdm2=var_jmbasxdm+var_subcode;
					String sql=
					" select to_number(yz) yz,'˰���Ż���ϸ��'||hcwz hcwz from (  "+
					" 	select case when hc='7' then '0010' when hc='10' then '0020'  when hc='11' then '0030'  when hc='29' then '0040' "+
					" 	when hc='16' then '005001'  when hc='17' then '005002' when hc='18' then '005003' when hc='19' then '005004' "+
					" 	when hc='20' then '005005'  when hc='21' then '005006' when hc='22' then '005007' when hc='23' then '005008' "+
					" 	when hc='26' then '005009'  when hc='27' then '005010' when hc='30' then '0060'  when hc='31' then '0070' "+
					" 	when hc='35' then '0080' when hc='52' then '0090' when hc='53' then '0100' when hc='54' then '0110' when hc='55' then '0120' "+
					" 	when hc='39' then '013B' when hc='41' then '014B01' when hc='42' then '014B07' when hc='43' then '014B10' "+
					" 	when hc='49' then '0170' when hc='56' then '0180' when hc='58' then '0190' when hc='57' then '0200'  else '9999' end jmbasxdm, "+
					" 	case when hc='49' then '�������ϵ�2��' when hc='52' then '�������ϵ�5��' when hc='53' then '�������ϵ�6��' "+
					" 	when hc='54' then '�������ϵ�7��' when hc='55' then '�������ϵ�8��' when hc='56' then '�������ϵ�9��' "+
					" 	when hc='57' then '�������ϵ�10��' when hc='58' then '�������ϵ�11��' else '��'||hc||'��' end hcwz, "+
					" 	hc,yz from sbdb.sb_jl_qysdssbb_zb_nd  where nsrjsjdm='"+var_jsjdm+"' and sknd='"+var_band+"' and sbdm='10' "+
					" 	) where jmbasxdm='"+var_jmbasxdm2+"' and rownum<=1 ";
					ps4=conn.prepareStatement(sql);
					rs4=ps4.executeQuery();
					while(rs4.next()){
						var_yz=rs4.getFloat("yz");
						var_hcwz=rs4.getString("hcwz");
					}
					if(var_yz>0){
						var_zxflag=false;
						returnStr=var_hcwz+"�����걨���ݣ��޷�"+zfbglxzw;
					}else{
						var_zxflag=true;
					}
				}else{
					var_zxflag=false;
					returnStr="����ʧ�ܣ��������Ա��ϵ��";
				}
				
				//ִ�����ϲ���
				if(var_zxflag){
					stmtZf=conn.createStatement();
					stmtZf.execute("update sfdb.sf_jl_qysdsjmsbajl set sqzt='6',zfr='"+yhid+"',zfrq=sysdate,zfsm='"+zfsm+"' ,htr=null,htrq=null where basqwsh='"+basqwsh+"'");
					returnStr="ִ�гɹ���";
				}
			
			}else{
				stmtBg=conn.createStatement();
				stmtBg.addBatch("update sfdb.sf_jl_qysdsjmsbajl set sqzt='2',zfr=null,zfrq=null,zfsm=null,htr='"+yhid+"',htrq=sysdate where basqwsh='"+basqwsh+"'");
				if(var_jmbasxdm.equals("013B")){
					stmtBg.addBatch("UPDATE sfdb.sf_jl_qysdsjmsba_13b t SET  t.shbj ='1' ,t.YWCBABS = '1'  where t.basqwsh='"+basqwsh+"'" );
				}	
				if(var_jmbasxdm.equals("014B")){
					stmtBg.addBatch("UPDATE sfdb.sf_jl_qysdsjmsba_14b t SET  t.shbj ='1' ,t.YWCBABS = '1' where t.basqwsh='"+basqwsh+"'");
				}
				stmtBg.executeBatch();
				returnStr="ִ�гɹ���";
			}
 	
			
			if (rs1 != null) {
				rs1.close();
			}
			if (rs2 != null) {
				rs2.close();
			}				
			if (rs3 != null) {
				rs3.close();
			}
			if (rs4 != null) {
				rs4.close();
			}	
			if (ps1 != null) {
				ps1.close();
			}
			if (ps2 != null) {
				ps2.close();
			}	
			if (ps3 != null) {
				ps3.close();
			}
			if (ps4 != null) {
				ps4.close();
			}	
			if (stmtZf != null) {
				stmtZf.close();
			}	
			if (stmtBg != null) {
				stmtBg.close();
			}
		} catch (Exception ex) {
			returnStr="����ʧ�ܣ��������Ա��ϵ��";
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {		
			SfDBResource.freeConnection(conn);
		}

		return returnStr;
	}
	
	
	/**
	 * doBeforeCheck�޸ģ���ˣ��鿴ҳ����Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doBeforeCheck(VOPackage vo) throws BaseException {
		
		QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) vo.getData();
		Connection conn = null;
		try {
			conn = SfDBResource.getConnection();
			boolean sqzt = QysdsUtil.checkSqzt(qysdsJmsbajlMainForm.getBasqwsh(), conn);
			if (!sqzt) {
				throw new ApplicationException("�˼���˰���������ѱ���˰�˳��أ�");
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return null;
	}
}
