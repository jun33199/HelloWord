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
	 * 实现Processor接口
	 * 
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
	 *             其他异常抛出由EJB的process方法处理。
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
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
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
	 * doShow初始化对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
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
	 * doQuery 用于返回页面索要查询的详尽信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
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
			String check="<a href=\"javascript:doOperate('''||a.basqwsh||''',''"+CodeConstant.QYSDSJMSBAJL_CZLX_CHECK+"'')\">审核</a>";
			String modify="<a href=\"javascript:doOperate('''||a.basqwsh||''',''"+CodeConstant.QYSDSJMSBAJL_CZLX_MODIFY+"'')\">修改</a>";
			String view="<a href=\"javascript:doOperate('''||a.basqwsh||''',''"+CodeConstant.QYSDSJMSBAJL_CZLX_VIEW+"'')\">查看</a>";
			String modify2="<a href=\"javascript:doOperate('''||a.basqwsh||''',''"+CodeConstant.QYSDSJMSBAJL_CZLX_BGZX+"'')\">变更</a>";
			String delete="<a href=\"javascript:doDelete('''||a.basqwsh||''','''||a.jmbasxdm||''')\">删除</a>";
			conn = SfDBResource.getConnection();				
			StringBuffer sb = new StringBuffer();
			sb.append(" select a.jsjdm||decode(c.yhdllx,'02','(证书用户)','') jsjdm,b.nsrmc nsrmc,d.swjgzzjgmc swjgzzjgmc,a.band band, ");
			sb.append(" A.BASQBH,  ");
			sb.append(" (select c.jmbasxmc from dmdb.sf_dm_jmbasxdm c where c.jmbasxdm=a.jmbasxdm) jmbasxmc, ");
			sb.append(" decode(a.sqlxdm,'0','网上申请','1','上门申请') sqlx, ");
			sb.append(" decode(a.sqzt,'2','待变更','3','提交未审核','4','审核已通过','5','审核未通过','6','已作废') sqzt, ");
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
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return list;
	}
	
	
	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
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
			sb.append(" decode(a.sqlxdm,'0','网上申请','1','上门申请') sqlx");
			
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
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return list;
	}
	
	
	/**
	 * 修改、审核时查询减免税类别
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
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}
	
	
	/**
	 * 新增页面信息
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		
		UserData ud=vo.getUserData();
		QysdsJmsbajlMainForm form = (QysdsJmsbajlMainForm) vo.getData();
		Connection conn = null;
		PreparedStatement pst = null;
		//获取备案年度
		String band=form.getAdd_band();
		//获取登陆用户的区县代码
		QysdsUtil.getAlertStrWhenAdd(form.getAdd_jsjdm(), band, ud);
		
		String qxdm=ud.getSsdwdm().substring(0, 2);
		try {
			conn = SfDBResource.getConnection();
			
			//调用公用方法，取得备案申请文书号和备案编号
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
			throw new ApplicationException("获取备案申请文书号失败，请重新操作！");
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}
	
	/**
	 * doDelete删除页面元素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
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
	 * doDelete删除页面元素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
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
		String zfbglxzw=(zfbglx.equals("0"))?"作废备案记录":"变更备案内容";
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
			//根据备案申请文书号取得计算机代码、备案年度、减免备案事项代码
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
					
				    /*取得该纳税人对应备案年度此备案事项中对应农林牧渔项目代码已通过审核的记录数*/
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
					
				    /*取得该纳税人对应备案年度此备案事项中对应农林牧渔项目代码已通过审核的记录数*/
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
					" select to_number(yz) yz,'税收优惠明细表'||hcwz hcwz from (  "+
					" 	select case when hc='7' then '0010' when hc='10' then '0020'  when hc='11' then '0030'  when hc='29' then '0040' "+
					" 	when hc='16' then '005001'  when hc='17' then '005002' when hc='18' then '005003' when hc='19' then '005004' "+
					" 	when hc='20' then '005005'  when hc='21' then '005006' when hc='22' then '005007' when hc='23' then '005008' "+
					" 	when hc='26' then '005009'  when hc='27' then '005010' when hc='30' then '0060'  when hc='31' then '0070' "+
					" 	when hc='35' then '0080' when hc='52' then '0090' when hc='53' then '0100' when hc='54' then '0110' when hc='55' then '0120' "+
					" 	when hc='39' then '013B' when hc='41' then '014B01' when hc='42' then '014B07' when hc='43' then '014B10' "+
					" 	when hc='49' then '0170' when hc='56' then '0180' when hc='58' then '0190' when hc='57' then '0200'  else '9999' end jmbasxdm, "+
					" 	case when hc='49' then '附报资料第2行' when hc='52' then '附报资料第5行' when hc='53' then '附报资料第6行' "+
					" 	when hc='54' then '附报资料第7行' when hc='55' then '附报资料第8行' when hc='56' then '附报资料第9行' "+
					" 	when hc='57' then '附报资料第10行' when hc='58' then '附报资料第11行' else '第'||hc||'行' end hcwz, "+
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
						returnStr=var_hcwz+"存在申报数据，无法"+zfbglxzw;
					}else{
						var_zxflag=true;
					}
				}else{
					var_zxflag=false;
					returnStr="操作失败，请与管理员联系！";
				}
				
				//执行作废操作
				if(var_zxflag){
					stmtZf=conn.createStatement();
					stmtZf.execute("update sfdb.sf_jl_qysdsjmsbajl set sqzt='6',zfr='"+yhid+"',zfrq=sysdate,zfsm='"+zfsm+"' ,htr=null,htrq=null where basqwsh='"+basqwsh+"'");
					returnStr="执行成功！";
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
				returnStr="执行成功！";
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
			returnStr="操作失败，请与管理员联系！";
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {		
			SfDBResource.freeConnection(conn);
		}

		return returnStr;
	}
	
	
	/**
	 * doBeforeCheck修改，审核，查看页面信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doBeforeCheck(VOPackage vo) throws BaseException {
		
		QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) vo.getData();
		Connection conn = null;
		try {
			conn = SfDBResource.getConnection();
			boolean sqzt = QysdsUtil.checkSqzt(qysdsJmsbajlMainForm.getBasqwsh(), conn);
			if (!sqzt) {
				throw new ApplicationException("此减免税备案申请已被纳税人撤回！");
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return null;
	}
}
