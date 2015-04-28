/**
 * @Title:       GrsdsJbxxProcessor.java
 * @Description: TODO
 * @date:        2014-12-2����10:16:43
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.Grsds2014Constant;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.GrsdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.GrsdsGrxxVO;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.GrsdsQyxxVO;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.JbxxbVO;
import com.ttsoft.bjtax.shenbao.util.DBResource;
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
			
			// ��ѯ��Ŀ
			case Grsds2014Constant.PROCESSOR_ACTION_QUERY: {
				return doQuery(vo);
			}
			
			//����
			case Grsds2014Constant.PROCESSOR_ACTION_SAVE: {
				return doSave(vo);
			}
			// ɾ��
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
	 * @Description: TODO ����:������ڸü�¼�͸��·������� 
	 * @param vo
	 * @return
	 * @throws BaseException 
	 */
	private Object doSave(VOPackage vo) throws Exception {
		JbxxbVO jbxxbVO = (JbxxbVO)vo.getData();
		Connection conn =null;
		try {
			conn = DBResource.getConnection();
			if(GrsdsUtil.hasItemJcxxQy(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1), jbxxbVO.getQyxxvo().getQyxx_jsjdm(), conn))
			//(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1), jbxxbVO.getQuery_jsjdm(), jbxxbVO.getQuery_sfzjlx(), jbxxbVO.getQuery_sfzjhm(), conn))
			{
				doUpdateQy(jbxxbVO ,conn);
				if(GrsdsUtil.hasItemJcxxTzz(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1), jbxxbVO.getQyxxvo().getQyxx_jsjdm(), jbxxbVO.getGrxx().getGrxx_sfzjlx(), jbxxbVO.getGrxx().getGrxx_sfzjhm(), conn)){
					doUpdateTzf(jbxxbVO ,conn);
				}else{
					doInsertTzf(jbxxbVO, conn, vo.getUserData());
				}
			}else{
				doInsertQy(jbxxbVO ,conn ,vo.getUserData());
				doInsertTzf(jbxxbVO ,conn ,vo.getUserData());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			if(conn!=null)
				conn.close();
		}
		
		return null;
	}
	
	/**
	 * @Description: TODO ���»�����Ϣ��ҵ
	 * @param jbxxbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doUpdateQy(JbxxbVO jbxxbVO ,Connection conn) throws SQLException{
		String sql_updateCompanyInf = "update sbdb.SB_JL_GRSDS_JCXXB_QY set mc=? ,kjywrbh=?,dz=?,yzbm=?,djzclx=?,gjbzhydm=?,sdszsfs=?,zgswjgdm=? where jsjdm=? and nd=?";
		PreparedStatement ps_company = conn.prepareStatement(sql_updateCompanyInf);
		int i = 1;
		GrsdsQyxxVO qyxxVO = jbxxbVO.getQyxxvo();
		ps_company.setString(i++,qyxxVO.getQyxx_btzzxm());
		ps_company.setString(i++,qyxxVO.getQyxx_kjywrbh());
		ps_company.setString(i++,qyxxVO.getQyxx_dz());
		ps_company.setString(i++,qyxxVO.getQyxx_yzbm());
		ps_company.setString(i++,qyxxVO.getQyxx_djzclx());
		ps_company.setString(i++,qyxxVO.getQyxx_hy());
		ps_company.setString(i++,qyxxVO.getQyxx_sdszsfs());
		ps_company.setString(i++,qyxxVO.getQyxx_zgswjg());
		ps_company.setString(i++,jbxxbVO.getQyxxvo().getQyxx_jsjdm());
		ps_company.setString(i++,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
		ps_company.executeUpdate();
	}
	
	/**
	 * @Description: TODO ���»�����ϢͶ���߷�
	 * @param jbxxbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doUpdateTzf(JbxxbVO jbxxbVO ,Connection conn) throws SQLException{
		String sql_updateTzzInf = "update sbdb.SB_JL_GRSDS_JCXXB_TZF set xm=? ,nsrlx=? ,sfyjjnqk=? ,dzyx=? ,jnlxdz=? ,yzbm=? ,lxdh=? ,zy=? ,zw=? ,xl=? " +
		",sfcjrlsgl=? ,cjdjqk=? ,tzzlx=? ,yjwsddzlx=? ,yjwsddz=? ,yjwsdyzbm=? ,wzsnsrgj=? ,wzsnsrcsd=? ,wzsnsrxb=? ,wzsnsrcsrq=to_date(?,'yyyymmdd') ,wzsnsrldjyzhm=? " +
		",wzsnsrsfssxddygdfjm=? ,wzsnsrjnzw=? ,wzsnsrjwzw=? ,wzsnsrlhsj=to_date(?,'yyyymmdd') ,wzsnsrrzqx=? ,wzsnsryjljsj=to_date(?,'yyyymmdd') ,wzsnsryjljdd=? ,wzsnsrjnrzsgdwmc=? ,wzsnsrjnrzsgdwkjywrbm=? " +
		",wzsnsrjnrzsgdwdz=? ,wzsnsrjnrzsgdwyzbm=? ,wzsnsrjnspqydwmc=? ,wzsnsrjnspqydwkjywrbm=? ,wzsnsrjnspqydwdz=? ,wzsnsrjnspqydwyzbm=? ,wzsnsrjwpqdwmc=? ,wzsnsrjwpqdwdz=? " +
		",wzsnsrzfd=? ,wzsnsrjwzfggb=? ,rzsgdwmc=? ,rzsgdwnsrsbh=? ,wzsnsrnsrsbh=? where jsjdm=? and sfzjlxdm=? and sfzjhm=? and nd=?";
		PreparedStatement ps_tzz = conn.prepareStatement(sql_updateTzzInf);
		int j = 1;
		GrsdsGrxxVO grxxVO = jbxxbVO.getGrxx();
		
		ps_tzz.setString(j++ ,grxxVO.getGrxx_xm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_nsrlx());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_sfyjjnqk());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_email());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_jnlxdz());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_yzbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_lxdh());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_zy());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_zw());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_xl());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_sfcjrlsgl());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_cjdjqk());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_tzzlx());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_yjwsd_dzlx());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_yjwsd_dz());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_yjwsd_yzbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_gj());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_csd());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_xb());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_csrq());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_ldjyzhm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_sfssxddygdfjm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnzw());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jwzw());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_lhsj());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_rzqx());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_yjljsj());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_yjljdd());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnrzsgdw_mc());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnrzsgdw_kjywrbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnrzsgdw_dz());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnrzsgdw_yzbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnspqydw_mc());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnspqydw_kjywrbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnspqydw_dz());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnspqydw_yzbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jwpqdw_mc());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jwpqdw_dz());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_zfd());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jwzfggb());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_rzsgdwmc());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_rzsgdwnsrsbh());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_nsrsbh());
		ps_tzz.setString(j++ ,jbxxbVO.getQyxxvo().getQyxx_jsjdm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_sfzjlx());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_sfzjhm());
		ps_tzz.setString(j++ ,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
		ps_tzz.executeUpdate();
	}
	
	
	/**
	 * @Description: TODO ����������Ϣ��ҵ
	 * @param jbxxbVO
	 * @param conn
	 * @param ud
	 * @throws SQLException 
	 */
	private void doInsertQy(JbxxbVO jbxxbVO ,Connection conn ,UserData ud) throws SQLException{
		String sql_updateCompanyInf = "insert into sbdb.SB_JL_GRSDS_JCXXB_QY (mc,kjywrbh,dz,yzbm,djzclx,gjbzhydm,sdszsfs,zgswjgdm ,jsjdm ,nd ,lrr ,lrsj) values(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		PreparedStatement ps_company = conn.prepareStatement(sql_updateCompanyInf);
		int i = 1;
		GrsdsQyxxVO qyxxVO = jbxxbVO.getQyxxvo();
		ps_company.setString(i++,qyxxVO.getQyxx_btzzxm());
		ps_company.setString(i++,qyxxVO.getQyxx_kjywrbh());
		ps_company.setString(i++,qyxxVO.getQyxx_dz());
		ps_company.setString(i++,qyxxVO.getQyxx_yzbm());
		ps_company.setString(i++,qyxxVO.getQyxx_djzclx());
		ps_company.setString(i++,qyxxVO.getQyxx_hy());
		ps_company.setString(i++,qyxxVO.getQyxx_sdszsfs());
		ps_company.setString(i++,qyxxVO.getQyxx_zgswjg());
		ps_company.setString(i++,jbxxbVO.getQyxxvo().getQyxx_jsjdm());
		ps_company.setString(i++,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
		ps_company.setString(i++,ud.yhid);
		ps_company.executeUpdate();
	}
	
	/**
	 * @Description: TODO ����������ϢͶ�ʷ�
	 * @param jbxxbVO
	 * @param conn
	 * @param ud
	 * @throws SQLException 
	 */
	private void doInsertTzf(JbxxbVO jbxxbVO ,Connection conn ,UserData ud) throws SQLException{
		String sql_updateTzzInf = "insert into sbdb.SB_JL_GRSDS_JCXXB_TZF(xm ,nsrlx ,sfyjjnqk ,dzyx ,jnlxdz ,yzbm ,lxdh ,zy ,zw ,xl ,sfcjrlsgl ,cjdjqk " +
		",tzzlx ,yjwsddzlx ,yjwsddz ,yjwsdyzbm ,wzsnsrgj ,wzsnsrcsd ,wzsnsrxb ,wzsnsrcsrq ,wzsnsrldjyzhm "+
		",wzsnsrsfssxddygdfjm ,wzsnsrjnzw ,wzsnsrjwzw ,wzsnsrlhsj ,wzsnsrrzqx ,wzsnsryjljsj ,wzsnsryjljdd ,wzsnsrjnrzsgdwmc ,wzsnsrjnrzsgdwkjywrbm "+
		",wzsnsrjnrzsgdwdz ,wzsnsrjnrzsgdwyzbm ,wzsnsrjnspqydwmc ,wzsnsrjnspqydwkjywrbm ,wzsnsrjnspqydwdz ,wzsnsrjnspqydwyzbm ,wzsnsrjwpqdwmc ,wzsnsrjwpqdwdz "+
		",wzsnsrzfd ,wzsnsrjwzfggb ,rzsgdwmc ,rzsgdwnsrsbh ,wzsnsrnsrsbh ,jsjdm ,sfzjlxdm ,sfzjhm ,nd ,cjr ,cjsj) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyymmdd'),?,?,?,?,to_date(?,'yyyymmdd'),?,to_date(?,'yyyymmdd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		PreparedStatement ps_tzz = conn.prepareStatement(sql_updateTzzInf);
		int j = 1;
		GrsdsGrxxVO grxxVO = jbxxbVO.getGrxx();
		ps_tzz.setString(j++ ,grxxVO.getGrxx_xm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_nsrlx());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_sfyjjnqk());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_email());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_jnlxdz());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_yzbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_lxdh());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_zy());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_zw());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_xl());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_sfcjrlsgl());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_cjdjqk());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_tzzlx());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_yjwsd_dzlx());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_yjwsd_dz());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_yjwsd_yzbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_gj());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_csd());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_xb());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_csrq());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_ldjyzhm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_sfssxddygdfjm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnzw());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jwzw());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_lhsj());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_rzqx());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_yjljsj());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_yjljdd());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnrzsgdw_mc());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnrzsgdw_kjywrbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnrzsgdw_dz());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnrzsgdw_yzbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnspqydw_mc());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnspqydw_kjywrbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnspqydw_dz());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jnspqydw_yzbm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jwpqdw_mc());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jwpqdw_dz());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_zfd());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_jwzfggb());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_rzsgdwmc());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_rzsgdwnsrsbh());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_wzsnsr_nsrsbh());
		ps_tzz.setString(j++ ,jbxxbVO.getQyxxvo().getQyxx_jsjdm());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_sfzjlx());
		ps_tzz.setString(j++ ,grxxVO.getGrxx_sfzjhm());
		ps_tzz.setString(j++ ,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
		ps_tzz.setString(j++ ,ud.yhid);
		//System.out.println("1111111111111111"+j);
		ps_tzz.executeUpdate();
	}
	
	
	/**
	 * @Description: TODO ��ѯ��Ϣ��������Ϣ��
	 * @param vo
	 * @return
	 * @throws BaseException 
	 */
	private Object doQuery(VOPackage vo) throws Exception
	{
		JbxxbVO jbxxbVO = (JbxxbVO)vo.getData();
		Connection conn = null;
		try {
			conn = DBResource.getConnection();
			if(getYearQY(jbxxbVO.getQyxxvo().getQyxx_jsjdm() ,conn ,jbxxbVO.getQyxxvo() ,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1))){
				if(!getYearTzz(jbxxbVO,conn ,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1))){
					if(!getYearTzz(jbxxbVO,conn ,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-2)))
					{
						getGrDefaultInf(jbxxbVO ,conn);
					}
				}
			}else if(getYearQY(jbxxbVO.getQyxxvo().getQyxx_jsjdm() ,conn ,jbxxbVO.getQyxxvo() ,String.valueOf(Calendar.YEAR-2))){
				if(!getYearTzz(jbxxbVO,conn ,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-2))){
					getGrDefaultInf(jbxxbVO ,conn);
				}
			}else{
				//ȡ������Ĭ�ϻ�������
				getQyDefaultInf(jbxxbVO ,conn);
				getGrDefaultInf(jbxxbVO ,conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			if(conn!=null)
			conn.close();
		}
			
		return jbxxbVO;
	}
	
	/**
	 * @Description: TODO �����û����Ӻ���ϵͳ��ȡ
	 * @param jcxxb
	 * @param jsjdm
	 * @param sfzjlx
	 * @param sfzjhm
	 * @throws Exception 
	 */
	private void getQyDefaultInf(JbxxbVO jbxxbVO ,Connection conn) throws Exception{
		String sqlQY= "select SWJGZZJGDM ,GJBZHYDM ,SWDJZH ,DJZCLXDM ,NSRMC from djdb.dj_jl_jbsj  where jsjdm=?";
		GrsdsQyxxVO grsdsQyxxVO = jbxxbVO.getQyxxvo();
		PreparedStatement psQy = conn.prepareStatement(sqlQY);
		psQy.setString(1, grsdsQyxxVO.getQyxx_jsjdm());
		ResultSet rsQy = psQy.executeQuery();
		if(rsQy.next()){
			grsdsQyxxVO.setQyxx_btzzxm(rsQy.getString("NSRMC")==null?"":rsQy.getString("NSRMC"));
			grsdsQyxxVO.setQyxx_hy(rsQy.getString("GJBZHYDM")==null?"":rsQy.getString("GJBZHYDM"));
			grsdsQyxxVO.setQyxx_zgswjg(rsQy.getString("SWJGZZJGDM")==null?"":rsQy.getString("SWJGZZJGDM"));
			grsdsQyxxVO.setQyxx_kjywrbh(rsQy.getString("SWDJZH")==null?"":rsQy.getString("SWDJZH"));
			grsdsQyxxVO.setQyxx_djzclx(rsQy.getString("DJZCLXDM")==null?"":rsQy.getString("DJZCLXDM"));
		}
	}
	private void getGrDefaultInf(JbxxbVO jbxxbVO ,Connection conn) throws Exception{
		String sqlGR= "select TZZXM from sbdb.SB_JL_TZZSJ2014  where zjlxdm=? and zjhm=?";
		GrsdsGrxxVO grsdsGrxxVO = jbxxbVO.getGrxx();
		PreparedStatement psGr = conn.prepareStatement(sqlGR);
		psGr.setString(1, grsdsGrxxVO.getGrxx_sfzjlx());
		psGr.setString(2, grsdsGrxxVO.getGrxx_sfzjhm());
		ResultSet rsGr = psGr.executeQuery();
		if(rsGr.next()){
			grsdsGrxxVO.setGrxx_xm(rsGr.getString("TZZXM")==null?"":rsGr.getString("TZZXM"));
		}
	}
	
	/**
	 * @Description: TODO �Ƿ���ڵ�����ҵ��Ϣ ������ڷ���true;���򷵻�false;
	 * @param jsjdm
	 * @return
	 * @throws Exception 
	 */
	private boolean getYearQY(String jsjdm ,Connection conn ,GrsdsQyxxVO grsdsQyxxVO ,String year) throws Exception
	{
		boolean result = false;
		String sql = "select JSJDM ,ND ,MC ,KJYWRBH ,DZ ,YZBM ,DJZCLX ,GJBZHYDM ,SDSZSFS,ZGSWJGDM from sbdb.SB_JL_GRSDS_JCXXB_QY where jsjdm=? and nd=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, jsjdm);
		ps.setString(2, year);
		ResultSet rs = ps.executeQuery();
		if(rs.next())   
		{
			result = true;
			grsdsQyxxVO.setQyxx_btzzxm(rs.getString("MC")==null?"":rs.getString("MC"));     			        //����
			grsdsQyxxVO.setQyxx_kjywrbh(rs.getString("KJYWRBH")==null?"":rs.getString("KJYWRBH"));	  			//�۽������˱��
			grsdsQyxxVO.setQyxx_dz(rs.getString("DZ")==null?"":rs.getString("DZ")); 			     			//��ַ
			grsdsQyxxVO.setQyxx_yzbm(rs.getString("YZBM")==null?"":rs.getString("YZBM")); 			     		//��������
			grsdsQyxxVO.setQyxx_djzclx(rs.getString("DJZCLX")==null?"":rs.getString("DJZCLX")); 		     	//�Ǽ�ע������
			grsdsQyxxVO.setQyxx_hy(rs.getString("GJBZHYDM")==null?"":rs.getString("GJBZHYDM")); 		     	//���ұ�׼��ҵ����
			grsdsQyxxVO.setQyxx_sdszsfs(rs.getString("SDSZSFS")==null?"":rs.getString("SDSZSFS")); 		     	//����˰���շ�ʽ
			grsdsQyxxVO.setQyxx_zgswjg(rs.getString("ZGSWJGDM")==null?"":rs.getString("ZGSWJGDM")); 		     //����˰�����
		}
		
		grsdsQyxxVO.setQyxx_jsjdm(jsjdm);
		return result;
	}
	
	/**
	 * @Description: TODO ��ѯͶ����
	 * @param jbxxbVO
	 * @param conn
	 * @param year
	 * @return
	 * @throws Exception 
	 */
	private boolean getYearTzz(JbxxbVO jbxxbVO ,Connection conn ,String year) throws Exception
	{
		boolean result = false;
		GrsdsGrxxVO tzzVO = jbxxbVO.getGrxx();
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd");
		
		String jsjdm = jbxxbVO.getQyxxvo().getQyxx_jsjdm();
		String sfzjlx = jbxxbVO.getGrxx().getGrxx_sfzjlx();
		String sfzjhm = jbxxbVO.getGrxx().getGrxx_sfzjhm();
		
		String sql = "select * from sbdb.SB_JL_GRSDS_JCXXB_TZF where jsjdm=? and sfzjlxdm=? and sfzjhm=? and nd=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, jsjdm);
		ps.setString(2, sfzjlx);
		ps.setString(3, sfzjhm);
		ps.setString(4, year);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			result = true;
			tzzVO.setGrxx_nd(year);												//���
			tzzVO.setGrxx_xm(rs.getString("XM")==null?"":rs.getString("XM"));	//Ͷ�ʷ�����
			tzzVO.setGrxx_nsrlx(rs.getString("NSRLX")==null?"":rs.getString("NSRLX"));	//��˰�����ʹ���
			tzzVO.setGrxx_rzsgdwmc(rs.getString("RZSGDWMC")==null?"":rs.getString("RZSGDWMC"));	//��ְ�ܹ͵�λ����
			tzzVO.setGrxx_rzsgdwnsrsbh(rs.getString("RZSGDWNSRSBH")==null?"":rs.getString("RZSGDWNSRSBH"));	//��ְ�ܹ͵�λ��˰��ʶ���
			tzzVO.setGrxx_sfyjjnqk(rs.getString("SFYJJNQK")==null?"":rs.getString("SFYJJNQK"));	//����һ��������
			tzzVO.setGrxx_email(rs.getString("DZYX")==null?"":rs.getString("DZYX"));	//��������
			tzzVO.setGrxx_jnlxdz(rs.getString("JNLXDZ")==null?"":rs.getString("JNLXDZ"));	//������ϵ��ַ
			tzzVO.setGrxx_yzbm(rs.getString("YZBM")==null?"":rs.getString("YZBM"));	//��������
			tzzVO.setGrxx_lxdh(rs.getString("LXDH")==null?"":rs.getString("LXDH"));	//��ϵ�绰
			tzzVO.setGrxx_zy(rs.getString("ZY")==null?"":rs.getString("ZY"));	//ְҵ
			tzzVO.setGrxx_zw(rs.getString("ZW")==null?"":rs.getString("ZW"));	//ְ��
			tzzVO.setGrxx_xl(rs.getString("XL")==null?"":rs.getString("XL"));	//ѧ��
			tzzVO.setGrxx_sfcjrlsgl(rs.getString("SFCJRLSGL")==null?"":rs.getString("SFCJRLSGL"));	//�Ƿ�м��ˡ�����������
			tzzVO.setGrxx_cjdjqk(rs.getString("CJDJQK")==null?"":rs.getString("CJDJQK"));	//�м��ȼ����
			tzzVO.setGrxx_tzzlx(rs.getString("TZZLX")==null?"":rs.getString("TZZLX"));	//Ͷ��������
			tzzVO.setGrxx_yjwsd_dzlx(rs.getString("YJWSDDZLX")==null?"":rs.getString("YJWSDDZLX"));	//�о������õ�ַ����
			tzzVO.setGrxx_yjwsd_dz(rs.getString("YJWSDDZ")==null?"":rs.getString("YJWSDDZ"));	//�о��������õ�ַ
			tzzVO.setGrxx_yjwsd_yzbm(rs.getString("YJWSDYZBM")==null?"":rs.getString("YJWSDYZBM"));	//�о���������������
			
			tzzVO.setGrxx_wzsnsr_nsrsbh(rs.getString("WZSNSRNSRSBH")==null?"":rs.getString("WZSNSRNSRSBH"));	//��ס����˰����˰��ʶ���
			tzzVO.setGrxx_wzsnsr_gj(rs.getString("WZSNSRGJ")==null?"":rs.getString("WZSNSRGJ"));	//��ס����˰�˹���
			tzzVO.setGrxx_wzsnsr_csd(rs.getString("WZSNSRCSD")==null?"":rs.getString("WZSNSRCSD"));	//��ס����˰�˳����� 
			tzzVO.setGrxx_wzsnsr_xb(rs.getString("WZSNSRXB")==null?"":rs.getString("WZSNSRXB"));	//��ס����˰���Ա�
			tzzVO.setGrxx_wzsnsr_csrq(rs.getDate("WZSNSRCSRQ")==null?"":time.format(rs.getDate("WZSNSRCSRQ")));	//��ס����˰�˳�������
			tzzVO.setGrxx_wzsnsr_ldjyzhm(rs.getString("WZSNSRLDJYZHM")==null?"":rs.getString("WZSNSRLDJYZHM"));	//��ס����˰���Ͷ���ҵ֤����
			tzzVO.setGrxx_wzsnsr_sfssxddygdfjm(rs.getString("WZSNSRSFSSXDDYGDFJM")==null?"":rs.getString("WZSNSRSFSSXDDYGDFJM"));	//��ס����˰���Ƿ�˰��Э����Լ���Է�����
			tzzVO.setGrxx_wzsnsr_jnzw(rs.getString("WZSNSRJNZW")==null?"":rs.getString("WZSNSRJNZW"));	//��ס����˰�˾���ְ��
			tzzVO.setGrxx_wzsnsr_jwzw(rs.getString("WZSNSRJWZW")==null?"":rs.getString("WZSNSRJWZW"));	//��ס����˰�˾���ְ��
			tzzVO.setGrxx_wzsnsr_lhsj(rs.getDate("WZSNSRLHSJ")==null?"":time.format(rs.getDate("WZSNSRLHSJ")));	//��ס����˰������ʱ��
			tzzVO.setGrxx_wzsnsr_rzqx(rs.getString("WZSNSRRZQX")==null?"":rs.getString("WZSNSRRZQX"));	//��ס����˰����ְ����
			tzzVO.setGrxx_wzsnsr_yjljsj(rs.getDate("WZSNSRYJLJSJ")==null?"":time.format(rs.getDate("WZSNSRYJLJSJ")));	//��ס����˰��Ԥ���뾳ʱ��
			tzzVO.setGrxx_wzsnsr_yjljdd(rs.getString("WZSNSRYJLJDD")==null?"":rs.getString("WZSNSRYJLJDD"));	//��ס����˰��Ԥ���뾳�ص�
			tzzVO.setGrxx_wzsnsr_jnrzsgdw_mc(rs.getString("WZSNSRJNRZSGDWMC")==null?"":rs.getString("WZSNSRJNRZSGDWMC"));	//��ס����˰�˾�����ְ�ܹ͵�λ����
			tzzVO.setGrxx_wzsnsr_jnrzsgdw_kjywrbm(rs.getString("WZSNSRJNRZSGDWKJYWRBM")==null?"":rs.getString("WZSNSRJNRZSGDWKJYWRBM"));	//��ס����˰�˾�����ְ�ܹ͵�λ �۽������˱���
			tzzVO.setGrxx_wzsnsr_jnrzsgdw_dz(rs.getString("WZSNSRJNRZSGDWDZ")==null?"":rs.getString("WZSNSRJNRZSGDWDZ"));	//��ס����˰�˾�����ְ�ܹ͵�λ��ַ
			tzzVO.setGrxx_wzsnsr_jnrzsgdw_yzbm(rs.getString("WZSNSRJNRZSGDWYZBM")==null?"":rs.getString("WZSNSRJNRZSGDWYZBM"));	//��ס����˰�˾�����ְ�ܹ͵�λ��������
			tzzVO.setGrxx_wzsnsr_jnspqydw_mc(rs.getString("WZSNSRJNSPQYDWMC")==null?"":rs.getString("WZSNSRJNSPQYDWMC"));	//��ס����˰�˾�����ƸǩԼ��λ����
			tzzVO.setGrxx_wzsnsr_jnspqydw_kjywrbm(rs.getString("WZSNSRJNSPQYDWKJYWRBM")==null?"":rs.getString("WZSNSRJNSPQYDWKJYWRBM"));	//��ס����˰�˾�����ƸǩԼ��λ�۽������˱���
			tzzVO.setGrxx_wzsnsr_jnspqydw_dz(rs.getString("WZSNSRJNSPQYDWDZ")==null?"":rs.getString("WZSNSRJNSPQYDWDZ"));	//��ס����˰�˾�����ƸǩԼ��λ��ַ
			tzzVO.setGrxx_wzsnsr_jnspqydw_yzbm(rs.getString("WZSNSRJNSPQYDWYZBM")==null?"":rs.getString("WZSNSRJNSPQYDWYZBM"));	//��ס����˰�˾�����ƸǩԼ��λ��������
			tzzVO.setGrxx_wzsnsr_jwpqdw_mc(rs.getString("WZSNSRJWPQDWMC")==null?"":rs.getString("WZSNSRJWPQDWMC"));	//��ס����˰�˾�����ǲ��λ����
			tzzVO.setGrxx_wzsnsr_jwpqdw_dz(rs.getString("WZSNSRJWPQDWDZ")==null?"":rs.getString("WZSNSRJWPQDWDZ"));	//��ס����˰�˾�����ǲ��λ��ַ
			tzzVO.setGrxx_wzsnsr_zfd(rs.getString("WZSNSRZFD")==null?"":rs.getString("WZSNSRZFD"));	//��ס����˰��֧����
			tzzVO.setGrxx_wzsnsr_jwzfggb(rs.getString("WZSNSRJWZFGGB")==null?"":rs.getString("WZSNSRJWZFGGB"));	//��ס����˰�˾���֧��������	
		}
		
		tzzVO.setGrxx_sfzjlx(sfzjlx);
		tzzVO.setGrxx_sfzjhm(sfzjhm);
		return result;
	}

}
