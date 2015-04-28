/**
 * @Title:       GrsdsJbxxProcessor.java
 * @Description: TODO
 * @date:        2014-12-2上午10:16:43
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.Grsds2014Constant;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.GrsdsUtil;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.web.GrsdsJbxxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-12-2
 */
public class GrsdsJbxxbProcessor implements Processor{

	public Object process(VOPackage vo) throws BaseException {
		try {
			switch (vo.getAction()) {
			
			// 查询条目
			case Grsds2014Constant.PROCESSOR_ACTION_QUERY: {
				return doQuery(vo);
			}
			
			//增加
			case Grsds2014Constant.PROCESSOR_ACTION_SAVE: {
				return doSave(vo);
			}
			// 删除
			case Grsds2014Constant.PROCESSOR_ACTION_DELETE: {
				return doDelete(vo);
			}

			default:
				throw new SystemException("no such method");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	
	/**
	 * @Description: TODO
	 * @param vo
	 * @return
	 */
	private Object doDelete(VOPackage vo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Description: TODO 保存:如果存在该记录就更新否则新增 
	 * @param vo
	 * @return
	 * @throws BaseException 
	 */
	private Object doSave(VOPackage vo) throws Exception {
		GrsdsJbxxActionForm jbxxForm = (GrsdsJbxxActionForm)vo.getData();
		Connection conn =null;
		try {
			conn = SfDBResource.getConnection();
			if(GrsdsUtil.hasItemJcxxQy(jbxxForm.getNd(), jbxxForm.getQyxx_jsjdm(), conn))
			{
				doUpdateQy(jbxxForm ,conn);
				if(GrsdsUtil.hasItemJcxxTzz(jbxxForm.getNd(), jbxxForm.getQyxx_jsjdm(), jbxxForm.getGrxx_sfzjlx(), jbxxForm.getGrxx_sfzjhm(), conn)){
					doUpdateTzf(jbxxForm ,conn);
				}else{
					doInsertTzf(jbxxForm, conn, vo.getUserData());
				}
			}else{
				doInsertQy(jbxxForm ,conn ,vo.getUserData());
				doInsertTzf(jbxxForm ,conn ,vo.getUserData());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			if(conn!=null)
				conn.close();
		}
		
		return jbxxForm;
	}
	
	/**
	 * @Description: TODO 更新基础信息企业
	 * @param jbxxbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doUpdateQy(GrsdsJbxxActionForm jbxxForm ,Connection conn) throws SQLException{
		String sql_updateCompanyInf = "update sbdb.SB_JL_GRSDS_JCXXB_QY set mc=? ,kjywrbh=?,dz=?,yzbm=?,djzclx=?,gjbzhydm=?,sdszsfs=?,zgswjgdm=? where jsjdm=? and nd=?";
		PreparedStatement ps_company = conn.prepareStatement(sql_updateCompanyInf);
		int i = 1;
		ps_company.setString(i++,jbxxForm.getQyxx_btzzxm());
		ps_company.setString(i++,jbxxForm.getQyxx_kjywrbh());
		ps_company.setString(i++,jbxxForm.getQyxx_dz());
		ps_company.setString(i++,jbxxForm.getQyxx_yzbm());
		ps_company.setString(i++,jbxxForm.getQyxx_djzclx());
		ps_company.setString(i++,jbxxForm.getQyxx_hy());
		ps_company.setString(i++,jbxxForm.getQyxx_sdszsfs());
		ps_company.setString(i++,jbxxForm.getQyxx_zgswjg());
		ps_company.setString(i++,jbxxForm.getQyxx_jsjdm());
		ps_company.setString(i++,jbxxForm.getNd());
		ps_company.executeUpdate();
	}
	
	/**
	 * @Description: TODO 更新基础信息投资者方
	 * @param jbxxbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doUpdateTzf(GrsdsJbxxActionForm jbxxForm ,Connection conn) throws SQLException{
		String sql_updateTzzInf = "update sbdb.SB_JL_GRSDS_JCXXB_TZF set xm=? ,nsrlx=? ,sfyjjnqk=? ,dzyx=? ,jnlxdz=? ,yzbm=? ,lxdh=? ,zy=? ,zw=? ,xl=? " +
		",sfcjrlsgl=? ,cjdjqk=? ,tzzlx=? ,yjwsddzlx=? ,yjwsddz=? ,yjwsdyzbm=? ,wzsnsrgj=? ,wzsnsrcsd=? ,wzsnsrxb=? ,wzsnsrcsrq=to_date(?,'yyyymmdd') ,wzsnsrldjyzhm=? " +
		",wzsnsrsfssxddygdfjm=? ,wzsnsrjnzw=? ,wzsnsrjwzw=? ,wzsnsrlhsj=to_date(?,'yyyymmdd') ,wzsnsrrzqx=? ,wzsnsryjljsj=to_date(?,'yyyymmdd') ,wzsnsryjljdd=? ,wzsnsrjnrzsgdwmc=? ,wzsnsrjnrzsgdwkjywrbm=? " +
		",wzsnsrjnrzsgdwdz=? ,wzsnsrjnrzsgdwyzbm=? ,wzsnsrjnspqydwmc=? ,wzsnsrjnspqydwkjywrbm=? ,wzsnsrjnspqydwdz=? ,wzsnsrjnspqydwyzbm=? ,wzsnsrjwpqdwmc=? ,wzsnsrjwpqdwdz=? " +
		",wzsnsrzfd=? ,wzsnsrjwzfggb=? ,rzsgdwmc=? ,rzsgdwnsrsbh=? ,wzsnsrnsrsbh=? where jsjdm=? and sfzjlxdm=? and sfzjhm=? and nd=?";
		PreparedStatement ps_tzz = conn.prepareStatement(sql_updateTzzInf);
		int j = 1;
		
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_xm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_nsrlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfyjjnqk());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_email());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_jnlxdz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_lxdh());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_zy());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_zw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_xl());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfcjrlsgl());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_cjdjqk());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_tzzlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_dzlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_gj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_csd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_xb());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_csrq());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_ldjyzhm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_sfssxddygdfjm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnzw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwzw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_lhsj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_rzqx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_yjljsj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_yjljdd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_kjywrbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_kjywrbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwpqdw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwpqdw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_zfd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwzfggb());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_rzsgdwmc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_rzsgdwnsrsbh());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_nsrsbh());
		ps_tzz.setString(j++ ,jbxxForm.getQyxx_jsjdm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfzjlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfzjhm());
		ps_tzz.setString(j++ ,jbxxForm.getNd());
		ps_tzz.executeUpdate();
	}
	
	
	/**
	 * @Description: TODO 新增基础信息企业
	 * @param jbxxbVO
	 * @param conn
	 * @param ud
	 * @throws SQLException 
	 */
	private void doInsertQy(GrsdsJbxxActionForm jbxxForm ,Connection conn ,UserData ud) throws SQLException{
		String sql_updateCompanyInf = "insert into sbdb.SB_JL_GRSDS_JCXXB_QY (mc,kjywrbh,dz,yzbm,djzclx,gjbzhydm,sdszsfs,zgswjgdm ,jsjdm ,nd ,lrr ,lrsj) values(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		PreparedStatement ps_company = conn.prepareStatement(sql_updateCompanyInf);
		int i = 1;
		ps_company.setString(i++,jbxxForm.getQyxx_btzzxm());
		ps_company.setString(i++,jbxxForm.getQyxx_kjywrbh());
		ps_company.setString(i++,jbxxForm.getQyxx_dz());
		ps_company.setString(i++,jbxxForm.getQyxx_yzbm());
		ps_company.setString(i++,jbxxForm.getQyxx_djzclx());
		ps_company.setString(i++,jbxxForm.getQyxx_hy());
		ps_company.setString(i++,jbxxForm.getQyxx_sdszsfs());
		ps_company.setString(i++,jbxxForm.getQyxx_zgswjg());
		ps_company.setString(i++,jbxxForm.getQyxx_jsjdm());
		ps_company.setString(i++,jbxxForm.getNd());
		ps_company.setString(i++,ud.yhid);
		ps_company.executeUpdate();
	}
	
	/**
	 * @Description: TODO 新增基础信息投资方
	 * @param jbxxbVO
	 * @param conn
	 * @param ud
	 * @throws SQLException 
	 */
	private void doInsertTzf(GrsdsJbxxActionForm jbxxForm ,Connection conn ,UserData ud) throws SQLException{
		String sql_updateTzzInf = "insert into sbdb.SB_JL_GRSDS_JCXXB_TZF(xm ,nsrlx ,sfyjjnqk ,dzyx ,jnlxdz ,yzbm ,lxdh ,zy ,zw ,xl ,sfcjrlsgl ,cjdjqk " +
		",tzzlx ,yjwsddzlx ,yjwsddz ,yjwsdyzbm ,wzsnsrgj ,wzsnsrcsd ,wzsnsrxb ,wzsnsrcsrq ,wzsnsrldjyzhm "+
		",wzsnsrsfssxddygdfjm ,wzsnsrjnzw ,wzsnsrjwzw ,wzsnsrlhsj ,wzsnsrrzqx ,wzsnsryjljsj ,wzsnsryjljdd ,wzsnsrjnrzsgdwmc ,wzsnsrjnrzsgdwkjywrbm "+
		",wzsnsrjnrzsgdwdz ,wzsnsrjnrzsgdwyzbm ,wzsnsrjnspqydwmc ,wzsnsrjnspqydwkjywrbm ,wzsnsrjnspqydwdz ,wzsnsrjnspqydwyzbm ,wzsnsrjwpqdwmc ,wzsnsrjwpqdwdz "+
		",wzsnsrzfd ,wzsnsrjwzfggb ,rzsgdwmc ,rzsgdwnsrsbh ,wzsnsrnsrsbh ,jsjdm ,sfzjlxdm ,sfzjhm ,nd ,cjr ,cjsj) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyymmdd'),?,?,?,?,to_date(?,'yyyymmdd'),?,to_date(?,'yyyymmdd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		PreparedStatement ps_tzz = conn.prepareStatement(sql_updateTzzInf);
		int j = 1;
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_xm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_nsrlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfyjjnqk());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_email());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_jnlxdz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_lxdh());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_zy());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_zw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_xl());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfcjrlsgl());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_cjdjqk());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_tzzlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_dzlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_yjwsd_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_gj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_csd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_xb());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_csrq());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_ldjyzhm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_sfssxddygdfjm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnzw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwzw());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_lhsj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_rzqx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_yjljsj());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_yjljdd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_kjywrbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnrzsgdw_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_kjywrbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jnspqydw_yzbm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwpqdw_mc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwpqdw_dz());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_zfd());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_jwzfggb());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_rzsgdwmc());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_rzsgdwnsrsbh());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_wzsnsr_nsrsbh());
		ps_tzz.setString(j++ ,jbxxForm.getQyxx_jsjdm());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfzjlx());
		ps_tzz.setString(j++ ,jbxxForm.getGrxx_sfzjhm());
		ps_tzz.setString(j++ ,jbxxForm.getNd());
		ps_tzz.setString(j++ ,ud.yhid);
		ps_tzz.executeUpdate();
	}
	
	/**
	 * @Description: TODO 查询信息：基本信息表
	 * @param vo
	 * @return
	 * @throws BaseException 
	 */
	private Object doQuery(VOPackage vo) throws Exception	{
		GrsdsJbxxActionForm grsdsJbxxActionForm = (GrsdsJbxxActionForm)vo.getData();
		Connection conn = null;
		try {
			conn = SfDBResource.getConnection();
			
			//如果当前有信息则取当年信息
			getYearQY(grsdsJbxxActionForm,Integer.parseInt(grsdsJbxxActionForm.getNd()) ,conn);
			getYearTzz(grsdsJbxxActionForm ,Integer.parseInt(grsdsJbxxActionForm.getNd()),conn);
				
//			//否则取前一年信息
//			}else if(getYearQY(grsdsJbxxActionForm ,Integer.parseInt(grsdsJbxxActionForm.getNd())-1 ,conn)){
//				getYearTzz(grsdsJbxxActionForm ,Integer.parseInt(grsdsJbxxActionForm.getNd())-1 ,conn);
//				
//			//如果还没有则取系统信息
//			}else{
//				//取得其他默认基本数据
//				getDefaultInf(grsdsJbxxActionForm ,conn);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			if(conn!=null)
			conn.close();
		}
		return grsdsJbxxActionForm;
	}
	
	/**
	 * @Description: TODO 如果都没有则从核心系统中取
	 * @param jcxxb
	 * @param jsjdm
	 * @param sfzjlx
	 * @param sfzjhm
	 * @throws Exception 
	 */
	private void getDefaultInf(GrsdsJbxxActionForm jcxxb ,Connection conn) throws Exception{
		String sqlQY= "select SWJGZZJGDM ,GJBZHYDM ,SWDJZH ,DJZCLXDM ,NSRMC from djdb.dj_jl_jbsj  where jsjdm=?";
		String sqlGR= "select TZZXM from sbdb.SB_JL_TZZSJ2014  where zjlxdm=? and zjhm=?";
		
		PreparedStatement psQy = conn.prepareStatement(sqlQY);
		psQy.setString(1, jcxxb.getQyxx_jsjdm());
		ResultSet rsQy = psQy.executeQuery();
		if(rsQy.next()){
			jcxxb.setQyxx_btzzxm(rsQy.getString("NSRMC")==null?"":rsQy.getString("NSRMC"));
			jcxxb.setQyxx_hy(rsQy.getString("GJBZHYDM")==null?"":rsQy.getString("GJBZHYDM"));
			jcxxb.setQyxx_zgswjg(rsQy.getString("SWJGZZJGDM")==null?"":rsQy.getString("SWJGZZJGDM"));
			jcxxb.setQyxx_kjywrbh(rsQy.getString("SWDJZH")==null?"":rsQy.getString("SWDJZH"));
			jcxxb.setQyxx_djzclx(rsQy.getString("DJZCLXDM")==null?"":rsQy.getString("DJZCLXDM"));
		}
		
		PreparedStatement psGr = conn.prepareStatement(sqlGR);
		psGr.setString(1, jcxxb.getGrxx_sfzjlx());
		psGr.setString(2, jcxxb.getGrxx_sfzjhm());
		ResultSet rsGr = psGr.executeQuery();
		if(rsGr.next()){
			jcxxb.setGrxx_xm(rsGr.getString("TZZXM")==null?"":rsGr.getString("TZZXM"));
		}
	}
	
	/**
	 * @Description: TODO 是否存在当年企业信息 如果存在返回true;否则返回false;
	 * @param jsjdm
	 * @return
	 * @throws Exception 
	 */
	private boolean getYearQY(GrsdsJbxxActionForm jbxxb ,int nd ,Connection conn) throws Exception
	{
		boolean result = false;
		String sql = "select JSJDM ,ND ,MC ,KJYWRBH ,DZ ,YZBM ,DJZCLX ,GJBZHYDM ,SDSZSFS,ZGSWJGDM from sbdb.SB_JL_GRSDS_JCXXB_QY where jsjdm=? and nd=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, jbxxb.getQyxx_jsjdm());
		ps.setString(2, String.valueOf(nd));
		ResultSet rs = ps.executeQuery();
		if(rs.next())   
		{
			result = true;
			jbxxb.setQyxx_btzzxm(rs.getString("MC")==null?"":rs.getString("MC"));     			        //名称
			jbxxb.setQyxx_kjywrbh(rs.getString("KJYWRBH")==null?"":rs.getString("KJYWRBH"));	  			//扣缴义务人编号
			jbxxb.setQyxx_dz(rs.getString("DZ")==null?"":rs.getString("DZ")); 			     			//地址
			jbxxb.setQyxx_yzbm(rs.getString("YZBM")==null?"":rs.getString("YZBM")); 			     		//邮政编码
			jbxxb.setQyxx_djzclx(rs.getString("DJZCLX")==null?"":rs.getString("DJZCLX")); 		     	//登记注册类型
			jbxxb.setQyxx_hy(rs.getString("GJBZHYDM")==null?"":rs.getString("GJBZHYDM")); 		     	//国家标准行业代码
			jbxxb.setQyxx_sdszsfs(rs.getString("SDSZSFS")==null?"":rs.getString("SDSZSFS")); 		     	//所得税征收方式
			jbxxb.setQyxx_zgswjg(rs.getString("ZGSWJGDM")==null?"":rs.getString("ZGSWJGDM")); 		     //主管税务机关
		}
		return result;
	}
	
	/**
	 * @Description: TODO 查询投资者
	 * @param jbxxbVO
	 * @param conn
	 * @param year
	 * @return
	 * @throws Exception 
	 */
	private void getYearTzz(GrsdsJbxxActionForm jbxxb ,int nd ,Connection conn) throws Exception
	{
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd");
		
		String sql = "select * from sbdb.SB_JL_GRSDS_JCXXB_TZF where jsjdm=? and sfzjlxdm=? and sfzjhm=? and nd=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, jbxxb.getQyxx_jsjdm());
		ps.setString(2, jbxxb.getGrxx_sfzjlx());
		ps.setString(3, jbxxb.getGrxx_sfzjhm());
		ps.setString(4, String.valueOf(nd));
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{										
			jbxxb.setGrxx_xm(rs.getString("XM")==null?"":rs.getString("XM"));	//投资方名称
			jbxxb.setGrxx_nsrlx(rs.getString("NSRLX")==null?"":rs.getString("NSRLX"));	//纳税人类型代码
			jbxxb.setGrxx_rzsgdwmc(rs.getString("RZSGDWMC")==null?"":rs.getString("RZSGDWMC"));	//任职受雇单位名称
			jbxxb.setGrxx_rzsgdwnsrsbh(rs.getString("RZSGDWNSRSBH")==null?"":rs.getString("RZSGDWNSRSBH"));	//任职受雇单位纳税人识别号
			jbxxb.setGrxx_sfyjjnqk(rs.getString("SFYJJNQK")==null?"":rs.getString("SFYJJNQK"));	//三费一金缴纳情况
			jbxxb.setGrxx_email(rs.getString("DZYX")==null?"":rs.getString("DZYX"));	//电子邮箱
			jbxxb.setGrxx_jnlxdz(rs.getString("JNLXDZ")==null?"":rs.getString("JNLXDZ"));	//境内联系地址
			jbxxb.setGrxx_yzbm(rs.getString("YZBM")==null?"":rs.getString("YZBM"));	//邮政编码
			jbxxb.setGrxx_lxdh(rs.getString("LXDH")==null?"":rs.getString("LXDH"));	//联系电话
			jbxxb.setGrxx_zy(rs.getString("ZY")==null?"":rs.getString("ZY"));	//职业
			jbxxb.setGrxx_zw(rs.getString("ZW")==null?"":rs.getString("ZW"));	//职务
			jbxxb.setGrxx_xl(rs.getString("XL")==null?"":rs.getString("XL"));	//学历
			jbxxb.setGrxx_sfcjrlsgl(rs.getString("SFCJRLSGL")==null?"":rs.getString("SFCJRLSGL"));	//是否残疾人、烈属、孤老
			jbxxb.setGrxx_cjdjqk(rs.getString("CJDJQK")==null?"":rs.getString("CJDJQK"));	//残疾等级情况
			jbxxb.setGrxx_tzzlx(rs.getString("TZZLX")==null?"":rs.getString("TZZLX"));	//投资者类型
			jbxxb.setGrxx_yjwsd_dzlx(rs.getString("YJWSDDZLX")==null?"":rs.getString("YJWSDDZLX"));	//有境外所得地址类型
			jbxxb.setGrxx_yjwsd_dz(rs.getString("YJWSDDZ")==null?"":rs.getString("YJWSDDZ"));	//有经境外所得地址
			jbxxb.setGrxx_yjwsd_yzbm(rs.getString("YJWSDYZBM")==null?"":rs.getString("YJWSDYZBM"));	//有境外所得邮政编码
			
			jbxxb.setGrxx_wzsnsr_nsrsbh(rs.getString("WZSNSRNSRSBH")==null?"":rs.getString("WZSNSRNSRSBH"));	//无住所纳税人纳税人识别号
			jbxxb.setGrxx_wzsnsr_gj(rs.getString("WZSNSRGJ")==null?"":rs.getString("WZSNSRGJ"));	//无住所纳税人国籍
			jbxxb.setGrxx_wzsnsr_csd(rs.getString("WZSNSRCSD")==null?"":rs.getString("WZSNSRCSD"));	//无住所纳税人出生地 
			jbxxb.setGrxx_wzsnsr_xb(rs.getString("WZSNSRXB")==null?"":rs.getString("WZSNSRXB"));	//无住所纳税人性别
			jbxxb.setGrxx_wzsnsr_csrq(rs.getDate("WZSNSRCSRQ")==null?"":time.format(rs.getDate("WZSNSRCSRQ")));	//无住所纳税人出生日期
			jbxxb.setGrxx_wzsnsr_ldjyzhm(rs.getString("WZSNSRLDJYZHM")==null?"":rs.getString("WZSNSRLDJYZHM"));	//无住所纳税人劳动就业证号码
			jbxxb.setGrxx_wzsnsr_sfssxddygdfjm(rs.getString("WZSNSRSFSSXDDYGDFJM")==null?"":rs.getString("WZSNSRSFSSXDDYGDFJM"));	//无住所纳税人是否税收协定缔约国对方居民
			jbxxb.setGrxx_wzsnsr_jnzw(rs.getString("WZSNSRJNZW")==null?"":rs.getString("WZSNSRJNZW"));	//无住所纳税人境内职务
			jbxxb.setGrxx_wzsnsr_jwzw(rs.getString("WZSNSRJWZW")==null?"":rs.getString("WZSNSRJWZW"));	//无住所纳税人境外职务
			jbxxb.setGrxx_wzsnsr_lhsj(rs.getDate("WZSNSRLHSJ")==null?"":time.format(rs.getDate("WZSNSRLHSJ")));	//无住所纳税人来华时间
			jbxxb.setGrxx_wzsnsr_rzqx(rs.getString("WZSNSRRZQX")==null?"":rs.getString("WZSNSRRZQX"));	//无住所纳税人任职期限
			jbxxb.setGrxx_wzsnsr_yjljsj(rs.getDate("WZSNSRYJLJSJ")==null?"":time.format(rs.getDate("WZSNSRYJLJSJ")));	//无住所纳税人预计离境时间
			jbxxb.setGrxx_wzsnsr_yjljdd(rs.getString("WZSNSRYJLJDD")==null?"":rs.getString("WZSNSRYJLJDD"));	//无住所纳税人预计离境地点
			jbxxb.setGrxx_wzsnsr_jnrzsgdw_mc(rs.getString("WZSNSRJNRZSGDWMC")==null?"":rs.getString("WZSNSRJNRZSGDWMC"));	//无住所纳税人境内任职受雇单位名称
			jbxxb.setGrxx_wzsnsr_jnrzsgdw_kjywrbm(rs.getString("WZSNSRJNRZSGDWKJYWRBM")==null?"":rs.getString("WZSNSRJNRZSGDWKJYWRBM"));	//无住所纳税人境内任职受雇单位 扣缴义务人编码
			jbxxb.setGrxx_wzsnsr_jnrzsgdw_dz(rs.getString("WZSNSRJNRZSGDWDZ")==null?"":rs.getString("WZSNSRJNRZSGDWDZ"));	//无住所纳税人境内任职受雇单位地址
			jbxxb.setGrxx_wzsnsr_jnrzsgdw_yzbm(rs.getString("WZSNSRJNRZSGDWYZBM")==null?"":rs.getString("WZSNSRJNRZSGDWYZBM"));	//无住所纳税人境内任职受雇单位邮政编码
			jbxxb.setGrxx_wzsnsr_jnspqydw_mc(rs.getString("WZSNSRJNSPQYDWMC")==null?"":rs.getString("WZSNSRJNSPQYDWMC"));	//无住所纳税人境内受聘签约单位名称
			jbxxb.setGrxx_wzsnsr_jnspqydw_kjywrbm(rs.getString("WZSNSRJNSPQYDWKJYWRBM")==null?"":rs.getString("WZSNSRJNSPQYDWKJYWRBM"));	//无住所纳税人境内受聘签约单位扣缴义务人编码
			jbxxb.setGrxx_wzsnsr_jnspqydw_dz(rs.getString("WZSNSRJNSPQYDWDZ")==null?"":rs.getString("WZSNSRJNSPQYDWDZ"));	//无住所纳税人境内受聘签约单位地址
			jbxxb.setGrxx_wzsnsr_jnspqydw_yzbm(rs.getString("WZSNSRJNSPQYDWYZBM")==null?"":rs.getString("WZSNSRJNSPQYDWYZBM"));	//无住所纳税人境内受聘签约单位邮政编码
			jbxxb.setGrxx_wzsnsr_jwpqdw_mc(rs.getString("WZSNSRJWPQDWMC")==null?"":rs.getString("WZSNSRJWPQDWMC"));	//无住所纳税人境外派遣单位名称
			jbxxb.setGrxx_wzsnsr_jwpqdw_dz(rs.getString("WZSNSRJWPQDWDZ")==null?"":rs.getString("WZSNSRJWPQDWDZ"));	//无住所纳税人境外派遣单位地址
			jbxxb.setGrxx_wzsnsr_zfd(rs.getString("WZSNSRZFD")==null?"":rs.getString("WZSNSRZFD"));	//无住所纳税人支付地
			jbxxb.setGrxx_wzsnsr_jwzfggb(rs.getString("WZSNSRJWZFGGB")==null?"":rs.getString("WZSNSRJWZFGGB"));	//无住所纳税人境外支付国国别	
		}
		
	}

}
