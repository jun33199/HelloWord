/**
 * @Title:       GrsdsNdsbbProcessor.java
 * @Description: TODO
 * @date:        2014-12-2上午10:17:04
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import utils.netAddresses;

import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.Grsds2014Constant;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.GrsdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.JbxxbVO;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.NdsbbVO;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.SbinfGrVO;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.SbinfQyVO;
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
public class GrsdsNdsbbProcessor implements Processor{

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

			default:
				throw new SystemException("no such method");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * @Description: TODO 保存
	 * @param vo
	 * @return
	 * @throws BaseException 
	 */
	private Object doSave(VOPackage vo) throws Exception {
		NdsbbVO ndsbbVO = (NdsbbVO)vo.getData();
		Connection conn=null;
		try {
			conn = DBResource.getConnection();
			if(GrsdsUtil.hasItemNdsbbQy(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1), ndsbbVO.getInf_qy().getBtzzxx_jsjdm(),conn))
			{
				doUpdateQy(ndsbbVO ,conn);
				if(GrsdsUtil.hasItemNdsbbTzz(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1), ndsbbVO.getInf_qy().getBtzzxx_jsjdm() ,ndsbbVO.getInf_gr().getTzzxx_sfzjlx() ,ndsbbVO.getInf_gr().getTzzxx_sfzjhm() ,conn)){
					doUpdateTzf(ndsbbVO ,conn);
				}else{
					doInsertTzf(ndsbbVO ,conn ,vo.getUserData());
				}
			}else{
				doInsertQy(ndsbbVO ,conn ,vo.getUserData());
				doInsertTzf(ndsbbVO ,conn ,vo.getUserData());
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
	 * @Description: TODO 新增投资方
	 * @param ndsbbVO
	 * @param conn
	 * @param userData
	 * @throws SQLException 
	 */
	private void doInsertTzf(NdsbbVO ndsbbVO, Connection conn, UserData userData) throws SQLException {
		
		SbinfGrVO grVO = ndsbbVO.getInf_gr();
		String sql = "insert into SBDB.SB_JL_GRSDS_NDSBB_TZZ (skssqq ,skssqz, xm , gj , nsrsbh , col_40 " +
				", col_41 , col_42 , col_43 , col_44 , col_45 , col_46 , col_47 , col_48 , col_49 " +
				", col_50 , col_51  ,jsjdm ,nd ,sfzjlxdm ,sfzjhm ,cjr ,cjsj ) values (to_date(?,'yyyymmdd')" +
				",to_date(?,'yyyymmdd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		PreparedStatement ps_gr = conn.prepareStatement(sql);
		int i=1;
		ps_gr.setString(i++, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1)+"0101");
		ps_gr.setString(i++, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1)+"1231");
		ps_gr.setString(i++, grVO.getTzzxx_name());
		ps_gr.setString(i++, grVO.getTzzxx_gj());
		ps_gr.setString(i++, grVO.getTzzxx_nsrsbh());
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_40()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_41()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_42()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_43()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_44()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_45()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_46()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_47()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_48()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_49()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_50()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_51()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setString(i++, ndsbbVO.getInf_qy().getBtzzxx_jsjdm());
		ps_gr.setString(i++, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
		ps_gr.setString(i++, grVO.getTzzxx_sfzjlx());
		ps_gr.setString(i++, grVO.getTzzxx_sfzjhm());
		ps_gr.setString(i++, userData.getYhid());
		ps_gr.executeUpdate();	
	}

	/**
	 * @Description: TODO 新增企业申报信息
	 * @param ndsbbVO
	 * @param conn
	 * @param userData
	 * @throws SQLException 
	 */
	private void doInsertQy(NdsbbVO ndsbbVO, Connection conn, UserData userData) throws SQLException {
		// TODO Auto-generated method stub
		SbinfQyVO qyVO = ndsbbVO.getInf_qy();
		String sql = "insert into SBDB.SB_JL_GRSDS_NDSBB_QY ( mc ,nsrsbh ,lx  ,npjzgrs  ,gzze  ,tzzrs  " +
				",col_1  ,col_2  ,col_3  ,col_4  ,col_5  ,col_6  ,col_7  ,col_8  ,col_9  ,col_10  ,col_11  " +
				",col_12  ,col_13  ,col_14  ,col_15  ,col_16  ,col_17  ,col_18  ,col_19  ,col_20  ,col_21  " +
				",col_22  ,col_23  ,col_24  ,col_25  ,col_26  ,col_27  ,col_28  ,col_29  ,col_30  ,col_31  " +
				",col_32  ,col_33  ,col_34  ,col_35  ,col_36  ,col_37  ,col_38  ,col_39  , jsjdm  ,nd  ,cjr ,cjrq)" +
				" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		PreparedStatement ps_qy = conn.prepareStatement(sql);
		int i=1;
		ps_qy.setString(i++, qyVO.getBtzzxx_name());
		ps_qy.setString(i++, qyVO.getBtzzxx_nsrsbh());
		ps_qy.setString(i++, qyVO.getBtzzxx_djzclx());
		ps_qy.setString(i++, qyVO.getBtzzxx_npjzgrs());
		ps_qy.setString(i++, qyVO.getBtzzxx_gzze());
		ps_qy.setString(i++, qyVO.getBtzzxx_tzzrs());
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_1()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_2()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_3()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_4()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_5()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_6()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_7()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_8()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_9()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_10()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_11()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_12()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_13()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_14()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_15()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_16()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_17()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_18()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_19()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_20()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_21()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_22()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_23()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_24()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_25()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_26()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_27()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_28()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_29()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_30()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_31()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_32()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_33()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_34()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_35()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_36()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_37()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_38()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_39()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setString(i++, qyVO.getBtzzxx_jsjdm());
		ps_qy.setString(i++, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
		ps_qy.setString(i++, userData.getYhid());
		ps_qy.executeUpdate();
	}

	/**
	 * @Description: TODO 更新主表 申报数据
	 * @param ndsbbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doUpdateQy(NdsbbVO ndsbbVO, Connection conn) throws SQLException {
		
		SbinfQyVO qyVO = ndsbbVO.getInf_qy();
		String sql = "update SBDB.SB_JL_GRSDS_NDSBB_QY set mc=? ,nsrsbh=? ,lx=? ,npjzgrs=? ,gzze=? ,tzzrs=? " +
				",col_1=? ,col_2=? ,col_3=? ,col_4=? ,col_5=? ,col_6=? ,col_7=? ,col_8=? ,col_9=? ,col_10=? ,col_11=? " +
				",col_12=? ,col_13=? ,col_14=? ,col_15=? ,col_16=? ,col_17=? ,col_18=? ,col_19=? ,col_20=? ,col_21=? " +
				",col_22=? ,col_23=? ,col_24=? ,col_25=? ,col_26=? ,col_27=? ,col_28=? ,col_29=? ,col_30=? ,col_31=? " +
				",col_32=? ,col_33=? ,col_34=? ,col_35=? ,col_36=? ,col_37=? ,col_38=? ,col_39=? where jsjdm=? and nd=?";
		
		PreparedStatement ps_qy = conn.prepareStatement(sql);
		int i=1;
		ps_qy.setString(i++, qyVO.getBtzzxx_name());
		ps_qy.setString(i++, qyVO.getBtzzxx_nsrsbh());
		ps_qy.setString(i++, qyVO.getBtzzxx_djzclx());
		ps_qy.setString(i++, qyVO.getBtzzxx_npjzgrs());
		ps_qy.setString(i++, qyVO.getBtzzxx_gzze());
		ps_qy.setString(i++, qyVO.getBtzzxx_tzzrs());
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_1()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_2()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_3()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_4()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_5()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_6()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_7()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_8()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_9()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_10()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_11()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_12()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_13()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_14()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_15()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_16()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_17()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_18()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_19()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_20()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_21()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_22()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_23()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_24()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_25()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_26()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_27()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_28()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_29()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_30()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_31()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_32()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_33()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_34()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_35()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_36()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_37()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_38()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(qyVO.getCol_39()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setString(i++, qyVO.getBtzzxx_jsjdm());
		ps_qy.setString(i++, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
		ps_qy.executeUpdate();
	}

	/**
	 * @Description: TODO 更新投资者申报数据
	 * @param ndsbbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doUpdateTzf(NdsbbVO ndsbbVO, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		SbinfGrVO grVO = ndsbbVO.getInf_gr();
		String sql = "update SBDB.SB_JL_GRSDS_NDSBB_TZZ set  xm=? , gj=? , nsrsbh=? , col_40=? " +
				", col_41=? , col_42=? , col_43=? , col_44=? , col_45=? , col_46=? , col_47=? , col_48=? , col_49=? " +
				", col_50=? , col_51=? where jsjdm=? and nd=? and sfzjlxdm=? and sfzjhm=?";
		
		PreparedStatement ps_gr = conn.prepareStatement(sql);
		int i=1;
		
		
		ps_gr.setString(i++, grVO.getTzzxx_name());
		ps_gr.setString(i++, grVO.getTzzxx_gj());
		ps_gr.setString(i++, grVO.getTzzxx_nsrsbh());
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_40()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_41()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_42()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_43()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_44()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_45()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_46()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_47()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_48()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_49()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_50()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(grVO.getCol_51()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setString(i++, ndsbbVO.getInf_qy().getBtzzxx_jsjdm());
		ps_gr.setString(i++, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
		ps_gr.setString(i++, grVO.getTzzxx_sfzjlx());
		ps_gr.setString(i++, grVO.getTzzxx_sfzjhm());
		ps_gr.executeUpdate();
	}


	
	/**
	 * @Description: TODO 查询年度申报表 
	 * @param vo 
	 * @return 
	 * @throws Exception 
	 */
	private Object doQuery(VOPackage vo) throws Exception {
		Connection conn=null;
		NdsbbVO ndsbbVO = (NdsbbVO)vo.getData();
		try{
			conn = DBResource.getConnection();
		
			//带出申报表——被投资者信息
			doQueryQySbb(ndsbbVO,conn);
			
			//带出申报表——投资者信息
			doQueryGrSbb(ndsbbVO,conn);
			}catch (Exception e) {
				throw ExceptionUtil.getBaseException(e);
			}finally{
				conn.close();
			}
		return ndsbbVO;
	}
	
	/**
	 * @Description: TODO 查询申报表——个人
	 * @param ndsbbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doQueryGrSbb(NdsbbVO ndsbbVO, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		SbinfGrVO sbgrvo = ndsbbVO.getInf_gr();
		
		String sqlgrxx = "select * from SBDB.SB_JL_GRSDS_NDSBB_TZZ  where jsjdm=? and nd=? and sfzjlxdm=? and sfzjhm=?";
		PreparedStatement ps_sb_gr = conn.prepareStatement(sqlgrxx);
		ps_sb_gr.setString(1, ndsbbVO.getInf_qy().getBtzzxx_jsjdm());
		ps_sb_gr.setString(2, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
		ps_sb_gr.setString(3, sbgrvo.getTzzxx_sfzjlx());
		ps_sb_gr.setString(4, sbgrvo.getTzzxx_sfzjhm());
		ResultSet rs_sb_gr = ps_sb_gr.executeQuery();
		
		//取投资者申报数据
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(rs_sb_gr.next()){
			sbgrvo.setSkssqq(rs_sb_gr.getDate("SKSSQQ")==null?"":sdf.format(rs_sb_gr.getDate("SKSSQQ")));
			sbgrvo.setSkssqz(rs_sb_gr.getDate("SKSSQZ")==null?"":sdf.format(rs_sb_gr.getDate("SKSSQZ")));
			sbgrvo.setTzzxx_name(rs_sb_gr.getString("XM")==null?"":rs_sb_gr.getString("XM"));
			sbgrvo.setTzzxx_gj(rs_sb_gr.getString("GJ")==null?"":rs_sb_gr.getString("GJ"));
			sbgrvo.setTzzxx_nsrsbh(rs_sb_gr.getString("NSRSBH")==null?"":rs_sb_gr.getString("NSRSBH"));
			sbgrvo.setCol_40(rs_sb_gr.getBigDecimal("COL_40")==null?"0.0000":String.valueOf(rs_sb_gr.getBigDecimal("COL_40").setScale(4,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_41(rs_sb_gr.getBigDecimal("COL_41")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_41").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_42(rs_sb_gr.getBigDecimal("COL_42")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_42").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_43(rs_sb_gr.getBigDecimal("COL_43")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_43").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_44(rs_sb_gr.getBigDecimal("COL_44")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_44").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_45(rs_sb_gr.getBigDecimal("COL_45")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_45").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_46(rs_sb_gr.getBigDecimal("COL_46")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_46").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_47(rs_sb_gr.getBigDecimal("COL_47")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_47").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_48(rs_sb_gr.getBigDecimal("COL_48")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_48").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_49(rs_sb_gr.getBigDecimal("COL_49")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_49").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_50(rs_sb_gr.getBigDecimal("COL_50")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_50").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbgrvo.setCol_51(rs_sb_gr.getBigDecimal("COL_51")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_51").setScale(2,BigDecimal.ROUND_HALF_UP)));	
			
		//若没有取出默认数据
		}else{
			String sqlgr = "select TZZXM ,NSRSBH ,GJ ,FPBL from sbdb.SB_JL_TZZSJ2014  where jsjdm=? and zjlxdm=? and zjhm=?";
			PreparedStatement ps_grxx = conn.prepareStatement(sqlgr);
			ps_grxx.setString(1, ndsbbVO.getInf_qy().getBtzzxx_jsjdm());
			ps_grxx.setString(2, sbgrvo.getTzzxx_sfzjlx());
			ps_grxx.setString(3, sbgrvo.getTzzxx_sfzjhm());
			ResultSet rs_grxx = ps_grxx.executeQuery();
			if(rs_grxx.next()){
				sbgrvo.setSkssqq((Calendar.getInstance().get(Calendar.YEAR)-1)+"0101");
				sbgrvo.setSkssqz((Calendar.getInstance().get(Calendar.YEAR)-1)+"1231");
				sbgrvo.setCol_40(rs_grxx.getBigDecimal("FPBL")==null?"0.0000":String.valueOf(rs_grxx.getBigDecimal("FPBL").setScale(4,BigDecimal.ROUND_HALF_UP)));
				sbgrvo.setTzzxx_name(rs_grxx.getString("TZZXM")==null?"":rs_grxx.getString("TZZXM"));
				sbgrvo.setTzzxx_nsrsbh(rs_grxx.getString("NSRSBH")==null?"":rs_grxx.getString("NSRSBH"));
				sbgrvo.setTzzxx_gj(rs_grxx.getString("GJ")==null?"":rs_grxx.getString("GJ"));
			}
		}
		
	}

	/**
	 * @Description: TODO 查询申报表 ——企业
	 * @param ndsbbVO
	 * @param conn
	 * @throws SQLException
	 */
	private void doQueryQySbb(NdsbbVO ndsbbVO ,Connection conn) throws SQLException
	{	
		SbinfQyVO sbqyvo = ndsbbVO.getInf_qy();
		
		String sqlqyxx = "select * from SBDB.SB_JL_GRSDS_NDSBB_QY  where jsjdm=? and nd=?";
		PreparedStatement ps_sb_qy = conn.prepareStatement(sqlqyxx);
		ps_sb_qy.setString(1, sbqyvo.getBtzzxx_jsjdm());
		ps_sb_qy.setString(2, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
		ResultSet rs_sb_qy = ps_sb_qy.executeQuery();
		
		//取得申报表的被投资者申报数据及信息，若信息取不到则从登记中带出
		if(rs_sb_qy.next()){
			sbqyvo.setBtzzxx_name(rs_sb_qy.getString("MC")==null?"":rs_sb_qy.getString("MC"));
			sbqyvo.setBtzzxx_jsjdm(rs_sb_qy.getString("JSJDM")==null?"":rs_sb_qy.getString("JSJDM"));
			sbqyvo.setBtzzxx_nsrsbh(rs_sb_qy.getString("NSRSBH")==null?"":rs_sb_qy.getString("NSRSBH"));
			sbqyvo.setBtzzxx_djzclx(rs_sb_qy.getString("LX")==null?"":rs_sb_qy.getString("LX"));
			sbqyvo.setBtzzxx_npjzgrs(rs_sb_qy.getString("NPJZGRS")==null?"":rs_sb_qy.getString("NPJZGRS"));
			sbqyvo.setBtzzxx_gzze(rs_sb_qy.getString("GZZE")==null?"":rs_sb_qy.getString("GZZE"));
			sbqyvo.setBtzzxx_tzzrs(rs_sb_qy.getString("TZZRS")==null?"":rs_sb_qy.getString("TZZRS"));
			sbqyvo.setCol_1(rs_sb_qy.getBigDecimal("COL_1")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_1").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_2(rs_sb_qy.getBigDecimal("COL_2")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_2").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_3(rs_sb_qy.getBigDecimal("COL_3")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_3").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_4(rs_sb_qy.getBigDecimal("COL_4")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_4").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_5(rs_sb_qy.getBigDecimal("COL_5")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_5").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_6(rs_sb_qy.getBigDecimal("COL_6")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_6").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_7(rs_sb_qy.getBigDecimal("COL_7")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_7").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_8(rs_sb_qy.getBigDecimal("COL_8")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_8").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_9(rs_sb_qy.getBigDecimal("COL_9")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_9").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_10(rs_sb_qy.getBigDecimal("COL_10")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_10").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_11(rs_sb_qy.getBigDecimal("COL_11")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_11").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_12(rs_sb_qy.getBigDecimal("COL_12")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_12").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_13(rs_sb_qy.getBigDecimal("COL_13")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_13").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_14(rs_sb_qy.getBigDecimal("COL_14")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_14").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_15(rs_sb_qy.getBigDecimal("COL_15")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_15").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_16(rs_sb_qy.getBigDecimal("COL_16")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_16").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_17(rs_sb_qy.getBigDecimal("COL_17")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_17").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_18(rs_sb_qy.getBigDecimal("COL_18")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_18").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_19(rs_sb_qy.getBigDecimal("COL_19")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_19").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_20(rs_sb_qy.getBigDecimal("COL_20")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_20").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_21(rs_sb_qy.getBigDecimal("COL_21")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_21").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_22(rs_sb_qy.getBigDecimal("COL_22")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_22").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_23(rs_sb_qy.getBigDecimal("COL_23")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_23").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_24(rs_sb_qy.getBigDecimal("COL_24")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_24").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_25(rs_sb_qy.getBigDecimal("COL_25")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_25").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_26(rs_sb_qy.getBigDecimal("COL_26")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_26").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_27(rs_sb_qy.getBigDecimal("COL_27")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_27").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_28(rs_sb_qy.getBigDecimal("COL_28")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_28").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_29(rs_sb_qy.getBigDecimal("COL_29")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_29").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_30(rs_sb_qy.getBigDecimal("COL_30")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_30").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_31(rs_sb_qy.getBigDecimal("COL_31")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_31").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_32(rs_sb_qy.getBigDecimal("COL_32")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_32").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_33(rs_sb_qy.getBigDecimal("COL_33")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_33").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_34(rs_sb_qy.getBigDecimal("COL_34")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_34").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_35(rs_sb_qy.getBigDecimal("COL_35")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_35").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_36(rs_sb_qy.getBigDecimal("COL_36")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_36").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_37(rs_sb_qy.getBigDecimal("COL_37")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_37").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_38(rs_sb_qy.getBigDecimal("COL_38")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_38").setScale(2,BigDecimal.ROUND_HALF_UP)));
			sbqyvo.setCol_39(rs_sb_qy.getBigDecimal("COL_39")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_39").setScale(2,BigDecimal.ROUND_HALF_UP)));
		
		//默认企业信息的初始值
		}else{
			String sqlqy = "select nsrmc ,swdjzh , djzclxdm from djdb.dj_jl_jbsj jbsj  where jbsj.jsjdm=?";
			PreparedStatement ps = conn.prepareStatement(sqlqy);
			ps.setString(1, sbqyvo.getBtzzxx_jsjdm());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				sbqyvo.setBtzzxx_name(rs.getString("nsrmc")==null?"":rs.getString("nsrmc"));
				sbqyvo.setBtzzxx_nsrsbh(rs.getString("swdjzh")==null?"":rs.getString("swdjzh"));
				//sbqyvo.setBtzzxx_djzclx(rs.getString("djzclx")==null?"":rs.getString("djzclx"));
			}
		}
		
		
	}



}
