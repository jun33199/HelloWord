/**
 * @Title:       GrsdsNdsbbProcessor.java
 * @Description: TODO
 * @date:        2014-12-2上午10:17:04
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.Grsds2014Constant;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.GrsdsUtil;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.web.GrsdsNdsbbActionForm;
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
//
//	/**
//	 * @Description: TODO 保存
//	 * @param vo
//	 * @return
//	 * @throws BaseException 
//	 */
	private Object doSave(VOPackage vo) throws Exception {
		GrsdsNdsbbActionForm ndsbbForm = (GrsdsNdsbbActionForm)vo.getData();
		Connection conn=null;
		try {
			conn = SfDBResource.getConnection();
			if(GrsdsUtil.hasItemNdsbbQy(ndsbbForm.getNd(), ndsbbForm.getBtzzxx_jsjdm(),conn))
			{
				doUpdateQy(ndsbbForm ,conn);
				if(GrsdsUtil.hasItemNdsbbTzz(ndsbbForm.getNd(), ndsbbForm.getBtzzxx_jsjdm() ,ndsbbForm.getTzzxx_sfzjlx() ,ndsbbForm.getTzzxx_sfzjhm() ,conn)){
					doUpdateTzf(ndsbbForm ,conn);
				}else{
					doInsertTzf(ndsbbForm ,conn ,vo.getUserData());
				}
			}else{
				doInsertQy(ndsbbForm ,conn ,vo.getUserData());
				doInsertTzf(ndsbbForm ,conn ,vo.getUserData());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			if(conn!=null)
			conn.close();
		}
		return ndsbbForm;
	}

	/**
	 * @Description: TODO 新增投资方
	 * @param ndsbbVO
	 * @param conn
	 * @param userData
	 * @throws SQLException 
	 */
	private void doInsertTzf(GrsdsNdsbbActionForm ndsbbForm, Connection conn, UserData userData) throws SQLException {
		
		String sql = "insert into SBDB.SB_JL_GRSDS_NDSBB_TZZ (skssqq ,skssqz, xm , gj , nsrsbh , col_40 " +
				", col_41 , col_42 , col_43 , col_44 , col_45 , col_46 , col_47 , col_48 , col_49 " +
				", col_50 , col_51  ,jsjdm ,nd ,sfzjlxdm ,sfzjhm ,cjr ,cjsj ) values (to_date(?,'yyyymmdd')" +
				",to_date(?,'yyyymmdd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		PreparedStatement ps_gr = conn.prepareStatement(sql);
		int i=1;
		ps_gr.setString(i++, ndsbbForm.getSkssqq());
		ps_gr.setString(i++, ndsbbForm.getSkssqz());
		ps_gr.setString(i++, ndsbbForm.getTzzxx_name());
		ps_gr.setString(i++, ndsbbForm.getTzzxx_gj());
		ps_gr.setString(i++, ndsbbForm.getTzzxx_nsrsbh());
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_40()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_41()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_42()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_43()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_44()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_45()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_46()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_47()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_48()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_49()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_50()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_51()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setString(i++, ndsbbForm.getBtzzxx_jsjdm());
		ps_gr.setString(i++, ndsbbForm.getNd());
		ps_gr.setString(i++, ndsbbForm.getTzzxx_sfzjlx());
		ps_gr.setString(i++, ndsbbForm.getTzzxx_sfzjhm());
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
	private void doInsertQy(GrsdsNdsbbActionForm ndsbbForm, Connection conn, UserData userData) throws SQLException {
		
		String sql = "insert into SBDB.SB_JL_GRSDS_NDSBB_QY ( mc ,nsrsbh ,lx  ,npjzgrs  ,gzze  ,tzzrs  " +
				",col_1  ,col_2  ,col_3  ,col_4  ,col_5  ,col_6  ,col_7  ,col_8  ,col_9  ,col_10  ,col_11  " +
				",col_12  ,col_13  ,col_14  ,col_15  ,col_16  ,col_17  ,col_18  ,col_19  ,col_20  ,col_21  " +
				",col_22  ,col_23  ,col_24  ,col_25  ,col_26  ,col_27  ,col_28  ,col_29  ,col_30  ,col_31  " +
				",col_32  ,col_33  ,col_34  ,col_35  ,col_36  ,col_37  ,col_38  ,col_39  , jsjdm  ,nd  ,cjr ,cjrq)" +
				" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		PreparedStatement ps_qy = conn.prepareStatement(sql);
		int i=1;
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_name());
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_nsrsbh());
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_djzclx());
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_npjzgrs());
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_gzze());
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_tzzrs());
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_1()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_2()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_3()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_4()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_5()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_6()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_7()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_8()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_9()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_10()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_11()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_12()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_13()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_14()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_15()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_16()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_17()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_18()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_19()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_20()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_21()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_22()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_23()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_24()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_25()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_26()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_27()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_28()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_29()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_30()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_31()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_32()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_33()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_34()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_35()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_36()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_37()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_38()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_39()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_jsjdm());
		ps_qy.setString(i++, ndsbbForm.getNd());
		ps_qy.setString(i++, userData.getYhid());
		ps_qy.executeUpdate();
	}

	/**
	 * @Description: TODO 更新主表 申报数据
	 * @param ndsbbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doUpdateQy(GrsdsNdsbbActionForm ndsbbForm, Connection conn) throws SQLException {
		
		
		String sql = "update SBDB.SB_JL_GRSDS_NDSBB_QY set mc=? ,nsrsbh=? ,lx=? ,npjzgrs=? ,gzze=? ,tzzrs=? " +
				",col_1=? ,col_2=? ,col_3=? ,col_4=? ,col_5=? ,col_6=? ,col_7=? ,col_8=? ,col_9=? ,col_10=? ,col_11=? " +
				",col_12=? ,col_13=? ,col_14=? ,col_15=? ,col_16=? ,col_17=? ,col_18=? ,col_19=? ,col_20=? ,col_21=? " +
				",col_22=? ,col_23=? ,col_24=? ,col_25=? ,col_26=? ,col_27=? ,col_28=? ,col_29=? ,col_30=? ,col_31=? " +
				",col_32=? ,col_33=? ,col_34=? ,col_35=? ,col_36=? ,col_37=? ,col_38=? ,col_39=? where jsjdm=? and nd=?";
		
		PreparedStatement ps_qy = conn.prepareStatement(sql);
		int i=1;
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_name());
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_nsrsbh());
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_djzclx());
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_npjzgrs());
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_gzze());
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_tzzrs());
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_1()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_2()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_3()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_4()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_5()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_6()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_7()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_8()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_9()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_10()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_11()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_12()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_13()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_14()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_15()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_16()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_17()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_18()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_19()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_20()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_21()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_22()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_23()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_24()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_25()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_26()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_27()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_28()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_29()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_30()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_31()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_32()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_33()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_34()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_35()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_36()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_37()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_38()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_39()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_qy.setString(i++, ndsbbForm.getBtzzxx_jsjdm());
		ps_qy.setString(i++, ndsbbForm.getNd());
		ps_qy.executeUpdate();
	}

	/**
	 * @Description: TODO 更新投资者申报数据
	 * @param ndsbbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doUpdateTzf(GrsdsNdsbbActionForm ndsbbForm, Connection conn) throws SQLException {
		
		String sql = "update SBDB.SB_JL_GRSDS_NDSBB_TZZ set skssqq=to_date(?,'yyyymmdd') , skssqz=to_date(?,'yyyymmdd') ,  xm=? , gj=? , nsrsbh=? , col_40=? " +
				", col_41=? , col_42=? , col_43=? , col_44=? , col_45=? , col_46=? , col_47=? , col_48=? , col_49=? " +
				", col_50=? , col_51=? where jsjdm=? and nd=? and sfzjlxdm=? and sfzjhm=?";
		
		PreparedStatement ps_gr = conn.prepareStatement(sql);
		int i=1;
		
		
		ps_gr.setString(i++, ndsbbForm.getSkssqq());
		ps_gr.setString(i++, ndsbbForm.getSkssqz());
		ps_gr.setString(i++, ndsbbForm.getTzzxx_name());
		ps_gr.setString(i++, ndsbbForm.getTzzxx_gj());
		ps_gr.setString(i++, ndsbbForm.getTzzxx_nsrsbh());
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_40()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_41()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_42()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_43()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_44()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_45()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_46()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_47()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_48()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_49()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_50()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setBigDecimal(i++, new BigDecimal(ndsbbForm.getCol_51()).setScale(4, BigDecimal.ROUND_HALF_UP));
		ps_gr.setString(i++, ndsbbForm.getBtzzxx_jsjdm());
		ps_gr.setString(i++, ndsbbForm.getNd());
		ps_gr.setString(i++, ndsbbForm.getTzzxx_sfzjlx());
		ps_gr.setString(i++, ndsbbForm.getTzzxx_sfzjhm());
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
		GrsdsNdsbbActionForm ndsbbForm = (GrsdsNdsbbActionForm)vo.getData();
		try{
			conn = SfDBResource.getConnection();
		
			//带出申报表——被投资者信息
			doQueryQySbb(ndsbbForm,conn);
			
			//带出申报表——投资者信息
			doQueryGrSbb(ndsbbForm,conn);
			}catch (Exception e) {
				throw ExceptionUtil.getBaseException(e);
			}finally{
				conn.close();
			}
		return ndsbbForm;
	}
	
	/**
	 * @Description: TODO 查询申报表——个人
	 * @param ndsbbVO
	 * @param conn
	 * @throws SQLException 
	 */
	private void doQueryGrSbb(GrsdsNdsbbActionForm ndsbbForm, Connection conn) throws SQLException {
		
		String sqlgrxx = "select * from SBDB.SB_JL_GRSDS_NDSBB_TZZ  where jsjdm=? and nd=? and sfzjlxdm=? and sfzjhm=?";
		PreparedStatement ps_sb_gr = conn.prepareStatement(sqlgrxx);
		ps_sb_gr.setString(1, ndsbbForm.getBtzzxx_jsjdm());
		ps_sb_gr.setString(2, ndsbbForm.getNd());
		ps_sb_gr.setString(3, ndsbbForm.getTzzxx_sfzjlx());
		ps_sb_gr.setString(4, ndsbbForm.getTzzxx_sfzjhm());
		ResultSet rs_sb_gr = ps_sb_gr.executeQuery();
		
		//取投资者申报数据
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(rs_sb_gr.next()){
			ndsbbForm.setSkssqq(rs_sb_gr.getDate("SKSSQQ")==null?"":sdf.format(rs_sb_gr.getDate("SKSSQQ")));
			ndsbbForm.setSkssqz(rs_sb_gr.getDate("SKSSQZ")==null?"":sdf.format(rs_sb_gr.getDate("SKSSQZ")));
			ndsbbForm.setTzzxx_name(rs_sb_gr.getString("XM")==null?"":rs_sb_gr.getString("XM"));
			ndsbbForm.setTzzxx_gj(rs_sb_gr.getString("GJ")==null?"":rs_sb_gr.getString("GJ"));
			ndsbbForm.setTzzxx_nsrsbh(rs_sb_gr.getString("NSRSBH")==null?"":rs_sb_gr.getString("NSRSBH"));
			ndsbbForm.setCol_40(rs_sb_gr.getBigDecimal("COL_40")==null?"0.0000":String.valueOf(rs_sb_gr.getBigDecimal("COL_40").setScale(4,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_41(rs_sb_gr.getBigDecimal("COL_41")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_41").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_42(rs_sb_gr.getBigDecimal("COL_42")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_42").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_43(rs_sb_gr.getBigDecimal("COL_43")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_43").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_44(rs_sb_gr.getBigDecimal("COL_44")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_44").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_45(rs_sb_gr.getBigDecimal("COL_45")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_45").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_46(rs_sb_gr.getBigDecimal("COL_46")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_46").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_47(rs_sb_gr.getBigDecimal("COL_47")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_47").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_48(rs_sb_gr.getBigDecimal("COL_48")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_48").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_49(rs_sb_gr.getBigDecimal("COL_49")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_49").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_50(rs_sb_gr.getBigDecimal("COL_50")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_50").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_51(rs_sb_gr.getBigDecimal("COL_51")==null?"0.00":String.valueOf(rs_sb_gr.getBigDecimal("COL_51").setScale(2,BigDecimal.ROUND_HALF_UP)));	
			
		//若没有取出默认数据
		}else{
			String sqlgr = "select TZZXM ,NSRSBH ,GJ ,FPBL from sbdb.SB_JL_TZZSJ2014  where jsjdm=? and zjlxdm=? and zjhm=?";
			PreparedStatement ps_grxx = conn.prepareStatement(sqlgr);
			ps_grxx.setString(1, ndsbbForm.getBtzzxx_jsjdm());
			ps_grxx.setString(2, ndsbbForm.getTzzxx_sfzjlx());
			ps_grxx.setString(3, ndsbbForm.getTzzxx_sfzjhm());
			ResultSet rs_grxx = ps_grxx.executeQuery();
			if(rs_grxx.next()){
				ndsbbForm.setSkssqq(ndsbbForm.getNd()+"0101");
				ndsbbForm.setSkssqz(ndsbbForm.getNd()+"1231");
				ndsbbForm.setCol_40(rs_grxx.getBigDecimal("FPBL")==null?"0.00":String.valueOf(rs_grxx.getBigDecimal("FPBL").setScale(2,BigDecimal.ROUND_HALF_UP)));
				ndsbbForm.setTzzxx_name(rs_grxx.getString("TZZXM")==null?"":rs_grxx.getString("TZZXM"));
				ndsbbForm.setTzzxx_nsrsbh(rs_grxx.getString("NSRSBH")==null?"":rs_grxx.getString("NSRSBH"));
				ndsbbForm.setTzzxx_gj(rs_grxx.getString("GJ")==null?"":rs_grxx.getString("GJ"));
			}
		}
		
	}

	/**
	 * @Description: TODO 查询申报表 ——企业
	 * @param ndsbbVO
	 * @param conn
	 * @throws SQLException
	 */
	private void doQueryQySbb(GrsdsNdsbbActionForm ndsbbForm ,Connection conn) throws SQLException
	{	
		String sqlqyxx = "select * from SBDB.SB_JL_GRSDS_NDSBB_QY  where jsjdm=? and nd=?";
		PreparedStatement ps_sb_qy = conn.prepareStatement(sqlqyxx);
		ps_sb_qy.setString(1, ndsbbForm.getBtzzxx_jsjdm());
		ps_sb_qy.setString(2, ndsbbForm.getNd());
		ResultSet rs_sb_qy = ps_sb_qy.executeQuery();
		
		//取得申报表的被投资者申报数据及信息，若信息取不到则从登记中带出
		if(rs_sb_qy.next()){
			ndsbbForm.setBtzzxx_name(rs_sb_qy.getString("MC")==null?"":rs_sb_qy.getString("MC"));
			ndsbbForm.setBtzzxx_jsjdm(rs_sb_qy.getString("JSJDM")==null?"":rs_sb_qy.getString("JSJDM"));
			ndsbbForm.setBtzzxx_nsrsbh(rs_sb_qy.getString("NSRSBH")==null?"":rs_sb_qy.getString("NSRSBH"));
			ndsbbForm.setBtzzxx_djzclx(rs_sb_qy.getString("LX")==null?"":rs_sb_qy.getString("LX"));
			ndsbbForm.setBtzzxx_npjzgrs(rs_sb_qy.getString("NPJZGRS")==null?"":rs_sb_qy.getString("NPJZGRS"));
			ndsbbForm.setBtzzxx_gzze(rs_sb_qy.getString("GZZE")==null?"":rs_sb_qy.getString("GZZE"));
			ndsbbForm.setBtzzxx_tzzrs(rs_sb_qy.getString("TZZRS")==null?"":rs_sb_qy.getString("TZZRS"));
			ndsbbForm.setCol_1(rs_sb_qy.getBigDecimal("COL_1")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_1").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_2(rs_sb_qy.getBigDecimal("COL_2")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_2").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_3(rs_sb_qy.getBigDecimal("COL_3")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_3").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_4(rs_sb_qy.getBigDecimal("COL_4")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_4").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_5(rs_sb_qy.getBigDecimal("COL_5")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_5").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_6(rs_sb_qy.getBigDecimal("COL_6")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_6").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_7(rs_sb_qy.getBigDecimal("COL_7")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_7").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_8(rs_sb_qy.getBigDecimal("COL_8")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_8").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_9(rs_sb_qy.getBigDecimal("COL_9")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_9").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_10(rs_sb_qy.getBigDecimal("COL_10")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_10").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_11(rs_sb_qy.getBigDecimal("COL_11")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_11").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_12(rs_sb_qy.getBigDecimal("COL_12")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_12").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_13(rs_sb_qy.getBigDecimal("COL_13")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_13").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_14(rs_sb_qy.getBigDecimal("COL_14")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_14").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_15(rs_sb_qy.getBigDecimal("COL_15")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_15").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_16(rs_sb_qy.getBigDecimal("COL_16")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_16").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_17(rs_sb_qy.getBigDecimal("COL_17")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_17").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_18(rs_sb_qy.getBigDecimal("COL_18")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_18").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_19(rs_sb_qy.getBigDecimal("COL_19")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_19").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_20(rs_sb_qy.getBigDecimal("COL_20")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_20").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_21(rs_sb_qy.getBigDecimal("COL_21")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_21").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_22(rs_sb_qy.getBigDecimal("COL_22")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_22").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_23(rs_sb_qy.getBigDecimal("COL_23")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_23").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_24(rs_sb_qy.getBigDecimal("COL_24")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_24").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_25(rs_sb_qy.getBigDecimal("COL_25")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_25").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_26(rs_sb_qy.getBigDecimal("COL_26")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_26").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_27(rs_sb_qy.getBigDecimal("COL_27")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_27").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_28(rs_sb_qy.getBigDecimal("COL_28")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_28").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_29(rs_sb_qy.getBigDecimal("COL_29")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_29").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_30(rs_sb_qy.getBigDecimal("COL_30")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_30").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_31(rs_sb_qy.getBigDecimal("COL_31")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_31").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_32(rs_sb_qy.getBigDecimal("COL_32")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_32").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_33(rs_sb_qy.getBigDecimal("COL_33")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_33").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_34(rs_sb_qy.getBigDecimal("COL_34")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_34").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_35(rs_sb_qy.getBigDecimal("COL_35")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_35").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_36(rs_sb_qy.getBigDecimal("COL_36")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_36").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_37(rs_sb_qy.getBigDecimal("COL_37")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_37").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_38(rs_sb_qy.getBigDecimal("COL_38")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_38").setScale(2,BigDecimal.ROUND_HALF_UP)));
			ndsbbForm.setCol_39(rs_sb_qy.getBigDecimal("COL_39")==null?"0.00":String.valueOf(rs_sb_qy.getBigDecimal("COL_39").setScale(2,BigDecimal.ROUND_HALF_UP)));
		
		//默认企业信息的初始值
		}else{
			String sqlqy = "select nsrmc ,swdjzh , djzclxdm from djdb.dj_jl_jbsj jbsj  where jbsj.jsjdm=?";
			PreparedStatement ps = conn.prepareStatement(sqlqy);
			ps.setString(1, ndsbbForm.getBtzzxx_jsjdm());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ndsbbForm.setBtzzxx_name(rs.getString("nsrmc")==null?"":rs.getString("nsrmc"));
				ndsbbForm.setBtzzxx_nsrsbh(rs.getString("swdjzh")==null?"":rs.getString("swdjzh"));
				//sbqyvo.setBtzzxx_djzclx(rs.getString("djzclx")==null?"":rs.getString("djzclx"));
			}
		}
		
		
	}



}
