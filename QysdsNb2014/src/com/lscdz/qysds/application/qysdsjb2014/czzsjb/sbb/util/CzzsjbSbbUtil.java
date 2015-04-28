package com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.qysds.application.qysdsjb2014.QysdsJb2014Contant;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.vo.CzzssdsjbSbbVo;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.vo.DmVo;
import com.lscdz.qysds.common.util.QysdsHelperUtil;

public class CzzsjbSbbUtil {


	/**
	 * 计算季报类型的税款所属日期
	 *
	 * @param curDate
	 *            日期
	 * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp 使用Key ＝
	 *         Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp 使用Key ＝ Skssrq.SKSSRQ_ND 得到
	 *         税款所属日期所在的年度 String
	 */
	public Map quarterSkssrq(Date curDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(curDate);
		int month = calendar.get(calendar.MONTH);
		int year = calendar.get(calendar.YEAR);

		int jd = month / 3;
		if (jd == 0) {
			year--;
			jd = 4;
		}
		String nd = new Integer(year).toString();
		Timestamp skssksrqDate = new Timestamp(
				new GregorianCalendar(year, 0, 1).getTime().getTime());
		Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
				(jd - 1) * 3 + 2, new GregorianCalendar(year, (jd - 1) * 3 + 2,
						1).getActualMaximum(calendar.DAY_OF_MONTH)).getTime()
				.getTime());
		Map retMap = new HashMap();
		retMap.put(QysdsJb2014Contant.SKSSKSRQ, skssksrqDate);
		retMap.put(QysdsJb2014Contant.SKSSJSRQ, skssjsrqDate);
		retMap.put(QysdsJb2014Contant.SKSSRQ_ND, nd);
		return retMap;
	}
	/**
	 * 查账征收季报A类初始化代码表
	 * @param czzssdsjbSbbVo
	 */
	public static void initCodeTable(CzzssdsjbSbbVo czzssdsjbSbbVo){
		//初始化免税收入项目代码表
		initMssrxmCodeList(czzssdsjbSbbVo);
		//初始化减征、免征项目代码表
		initJzmzxmCodeList(czzssdsjbSbbVo);
		//初始化减征、免征项目代码表
		initJmxmCodeList(czzssdsjbSbbVo);
	}   
	/**
	 * 初始化免税收入项目代码表
	 * @param czzssdsjbSbbVo
	 */
    public static void initMssrxmCodeList(CzzssdsjbSbbVo czzssdsjbSbbVo){
    	String sql="SELECT MSSRXMDM,LPAD('  ',2*(LEVEL - 1)) || MSSRXMMC MSSRXMMC, level "+
      "FROM DMDB.SB_DM_QYSDS_MSSRXM where zfbs='0' START WITH fjddm IS NULL CONNECT BY PRIOR MSSRXMDM = fjddm";
    	Statement st=null;
    	ResultSet rs=null;
    	Connection conn=null;
    	List mssrxmList=new ArrayList();
    	try {
			conn=ResourceManager.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				String dm=rs.getString("MSSRXMDM");
				String mc=rs.getString("MSSRXMMC");
				String level=rs.getString("level");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				dmvo.setLevel(level);
				mssrxmList.add(dmvo);	
			}
		} catch (FrameException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			QysdsHelperUtil.dbResClose(st, rs, conn);		
		}
		czzssdsjbSbbVo.setMssrxmList(mssrxmList);
    }
    /**
     * 初始化减征、免征项目代码表
     * @param czzssdsjbSbbVo
     */
    public static void initJzmzxmCodeList(CzzssdsjbSbbVo czzssdsjbSbbVo){
    	String sql="SELECT JZMZXMDM,LPAD('  ',2*(LEVEL - 1)) || JZMZXMMC JZMZXMMC, level "+
      "FROM DMDB.SB_DM_QYSDS_JZMZXM where zfbs='0' START WITH fjddm IS NULL CONNECT BY PRIOR JZMZXMDM = fjddm";
    	Statement st=null;
    	ResultSet rs=null;
    	Connection conn=null;
    	List jzmzxmList=new ArrayList();
    	try {
			conn=ResourceManager.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				String dm=rs.getString("JZMZXMDM");
				String mc=rs.getString("JZMZXMMC");
				String level=rs.getString("level");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				dmvo.setLevel(level);
				jzmzxmList.add(dmvo);	
			}
		} catch (FrameException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			QysdsHelperUtil.dbResClose(st, rs, conn);
		}
		czzssdsjbSbbVo.setJzmzxmList(jzmzxmList);
    }
    /**
     * 初始化减免项目代码表
     * @param czzssdsjbSbbVo
     */
    public static void initJmxmCodeList(CzzssdsjbSbbVo czzssdsjbSbbVo){
    	String sql="SELECT jmxmdm,LPAD('  ',2*(LEVEL - 1)) || jmxmmc jmxmmc, level "+
      "FROM DMDB.SB_DM_QYSDS_JMXM where zfbs='0' START WITH fjddm IS NULL CONNECT BY PRIOR jmxmdm = fjddm";
    	Statement st=null;
    	ResultSet rs=null;
    	Connection conn=null;
    	List jmxmList=new ArrayList();
    	try {
			conn=ResourceManager.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				String dm=rs.getString("jmxmdm");
				String mc=rs.getString("jmxmmc");
				String level=rs.getString("level");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				dmvo.setLevel(level);
				jmxmList.add(dmvo);	
			}
		} catch (FrameException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			QysdsHelperUtil.dbResClose(st, rs, conn);
		}
		czzssdsjbSbbVo.setJmxmList(jmxmList);
    }

}
