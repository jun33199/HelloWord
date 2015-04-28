package com.lscdz.qysds.application.qysdsjb2014.base.util;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


import com.lscdz.qysds.application.qysdsjb2014.QysdsJb2014Contant;
import com.lscdz.qysds.application.qysdsjb2014.base.vo.QysdsjbBaseVo;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.QysdsInfo.bo.Zsfs;
import com.lscdz.qysds.common.service.QysdsInfo.processor.QysdsInfoServer;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.qysds.common.service.qysds.bo.qysds.Jbxx;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import com.lscdz.qysds.common.util.QysdsHelperUtil;

import yangjian.frame.dao.FrameCommonAccess;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
/**
 * 企业所得税季报工具类
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2015-4-2 下午04:15:24
 */
public class QysdsBaseUtil {
	public QysdsBaseUtil() {
	}

	/**
	 * 获取征收方式
	 * 
	 * @param jsjdm
	 *            String
	 * @param rq
	 *            Date
	 * @return Zsfs
	 * @throws FrameException
	 */
	public  Zsfs getZsfsInfo(String jsjdm, Date rq) throws FrameException {
		Connection con = null;	
		Zsfs zsfs=null;
		try {
			con = ResourceManager.getConnection();
			zsfs=new QysdsInfoServer().getZsfsInfo(jsjdm, rq, con);
			return zsfs;
		} catch (Exception ex) {
			throw new FrameException(ex.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(null, null, con);
		}

	}
	/**
	 * 得到给定日期的年份 为int型
	 * 
	 * @param date
	 *            给定的日期
	 * @return int 年份值
	 */
	public static int getYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return (calendar.get(Calendar.YEAR));
	}

	public java.lang.String getZsfsmc(String zsfsdm, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String ret = "";
		try {
			String sql = "select * from dmdb.sf_dm_zsfs where zsfsdm='"
					+ zsfsdm + "'";
//			SfDBAccess db = new SfDBAccess(con);
//			ResultSet rs = db.querySQL(sql);
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				// rs.first();
				ret = rs.getString("zsfsmc");
			}
//			rs.close();
			return ret;
		} catch (Exception ex) {
			return "";
		}finally{
			QysdsHelperUtil.dbResClose(pstmt, rs, con);
		}
	}

	/**
	 * @description 释放数据库资源
	 * @author gaoyh
	 * @modify_date 2014-03-15
	 * @param rs
	 * @param stmt
	 * @param con
	 */
	public void release(ResultSet rs, Statement stmt) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void releaseAll(ResultSet rs, Statement stmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 判断是否是新开户
	 * 
	 * @param skssksrq
	 * @param djJbsj
	 * @return
	 */
	public String getSfxkh(String skssnd, Djjbsj djJbsj,String qh) {
		String sfxkh = "N";
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(djJbsj.getKydjrq());
		// 开业登记年度
		int kydjnd = calendar.get(calendar.YEAR);
		if (kydjnd == Integer.parseInt(skssnd)) {
			sfxkh = "Y";
			;
		} else {
			sfxkh = "N";
		}
		//2014版改动，zhangj，20150212 start
		if("2015".equals(skssnd)&&"1".equals(qh)){
			if ((kydjnd == Integer.parseInt(skssnd))||(kydjnd == (Integer.parseInt(skssnd)-1))) {
				sfxkh = "Y";
			}
		}
		//zhangj，20150212 end
		return sfxkh;
	}

	/**
	 * 获取税款所属期所在年度的上一年度的征收方式代码
	 * 
	 * @param jsjdm
	 *            String
	 * @param skssrqq
	 *            Date
	 * @return String
	 * @throws FrameException
	 */
	public String getSyndZsfsDm(String jsjdm, String skssnd,String qh)
			throws FrameException, FrameException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String zsfsdm = QysdsJb2014Contant.ZSFSDM_CZZS;
		int rdnd = Integer.parseInt(skssnd);
		
        //2014版需求改动，zhangj，20150212 start
        if(2015==rdnd && "1".equals(qh)){
        	rdnd=2014;
        }
        //20150212 end
		
		try {
			con = ResourceManager.getConnection();
			sql.append("select * from sfdb.sf_jl_qysdszsfs_his where ");
			sql.append("jsjdm = '").append(jsjdm).append("' ");
			sql.append("and to_number(rdnd) < ").append(rdnd);
			sql.append(" order by rdnd desc, cjrq desc");

			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(sql.toString());

			if (rs.next()) {
				zsfsdm = rs.getString("ZSFSDM") == null ? QysdsJb2014Contant.ZSFSDM_CZZS
						: rs.getString("ZSFSDM");
			}
			return zsfsdm;
		} catch (Exception ex) {
			throw new FrameException("获取税款所属期所在年度的上一年度的征收方式代码失败！");
		} finally {
			QysdsHelperUtil.dbResClose(pstmt, rs, con);
		}
	}

	/**
	 * 查询查帐征收表关联分配表数据
	 * 
	 * @param conn
	 *            Connection
	 * @param form
	 *            ZfjgqysdsjbForm
	 * @throws FrameException
	 */
	public Map getSyndHsqjSbxx(String zsfsdm, String jsjdm, String skssnd,String qh)
			throws FrameException, FrameException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		int sknd = Integer.parseInt(skssnd);

        //2014版需求改动，zhangj，20150212 start
        if(2015==sknd&&"1".endsWith(qh)){
        	sknd=2014;
        }
        //20150212 end

        
		// 获取上一年度核定征收行6数据
		String syndZbh6 = "0.00";
		// 获取上一年度汇算清缴主表行9数据
		String syndZbh25 = "0.00";
		// 获取上一年度汇算清缴附表5行45、46、47的校验结果
		String syndFb5jyjg = "N";
		try {
			conn = ResourceManager.getConnection();
			if (QysdsJb2014Contant.ZSFSDM_CZZS.equals(zsfsdm)) {
				sql.append("select * from sbdb.sb_jl_qysdssbb_zb_nd where ");
				sql.append("nsrjsjdm = '").append(jsjdm).append("' ");
				sql.append("and bbqlx = '2' ");
				sql.append("and qh = '1' ");
				sql.append("and sknd = '").append(sknd - 1).append("' ");
				sql.append("and ((sbdm='1' and hc in ('9','25')) or (sbdm='10' and hc in ('45','46','47'))) ");
				System.out.println("sql:\n" + sql.toString());

				pstmt = conn.prepareStatement(sql.toString());
				rs = pstmt.executeQuery(sql.toString());

				String zbh25 = "0.00";
				String fb5h45 = "0.00";
				String fb5h46 = "0.00";
				String fb5h47 = "";

				while (rs.next()) {
					if (rs.getString("hc").equals("25")) {
						zbh25 = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
					}
					if (rs.getString("hc").equals("45")) {
						fb5h45 = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
					}
					if (rs.getString("hc").equals("46")) {
						fb5h46 = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
					}
					if (rs.getString("hc").equals("47")) {
						fb5h47 = rs.getString("yz") == null ? "" : rs.getString("yz");
					}
				}

				syndZbh25 = zbh25;
				if (Double.parseDouble(zbh25) <= 30 * 10000) {
					if (fb5h47.equals("01")) {// 工业企业
						if (Double.parseDouble(fb5h45) <= 100 && Double.parseDouble(fb5h46) <= 3000*10000) {
							syndFb5jyjg = "Y";
						} else {
							syndFb5jyjg = "N";
						}
					} else if (fb5h47.equals("02")) {// 其他企业
						if (Double.parseDouble(fb5h45) <= 80 && Double.parseDouble(fb5h46) <= 1000*10000) {
							syndFb5jyjg = "Y";
						} else {
							syndFb5jyjg = "N";
						}
					} else {
						syndFb5jyjg = "N";
					}
				} else {
					syndFb5jyjg = "N";
				}
				// 关闭数据库对象
				rs.close();
				pstmt.close();
			} else {
				sql1.append(
						"select * from sbdb.sb_jl_qysdssbb_zb_jd t where nsrjsjdm='")
						.append(jsjdm).append("' and  sbdm='24' and hc='6'")
						.append("and sknd = '").append(sknd - 1).append("' ");
				pstmt1 = conn.prepareStatement(sql1.toString());
				rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					if (rs1.getString("hc").equals("6")) {
						syndZbh6 = rs1.getString("yz") == null ? "0.00" : rs1.getString("yz");
					}
				}
			}
			Map map = new HashMap();
			map.put("syndZbh6", syndZbh6);
			map.put("syndZbh25", syndZbh25);
			map.put("syndFb5jyjg", syndFb5jyjg);
			
			return map;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FrameException("查询查帐征收表关联分配表数据失败！");
		} finally {
			QysdsHelperUtil.dbResClose(pstmt, rs, null);
			QysdsHelperUtil.dbResClose(pstmt1, rs1, conn);
		}
	}


	/**
	 * 替换字符串
	 * @param original 源字符串
	 * @param find 查找字符串
	 * @param replacement 替换字符串
	 * @return 替换后的字符串
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
	/**将java.util.Date转换为的java.sql.Timestamp
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
	 * 核定征收季报初始化代码表
	 * @param czzssdsjbSbbVo
	 */
//	public static void initCodeTable(HdzssdsjbForm hdzssdsjbForm) {
//		// 国家标准行业
//		List gjbzhy = CodeTableUtil.getCodeTableList(CodeTableKey.GJBZHY);
//		ArrayList gjbzhyList = new ArrayList();
//		for (int i = 0; i < gjbzhy.size(); i++) {
//			GJBZHY gjbzhyObject = (GJBZHY) gjbzhy.get(i);
//			String gjbzhydm = gjbzhyObject.getGjbzhydm();
//			if (gjbzhydm != null && !gjbzhydm.equals("")
//					&& gjbzhydm.length() == 4) {
//				gjbzhyList.add(gjbzhyObject);
//				// System.out.println("gjbzhydm: "+gjbzhyObject.getGjbzhydm()+" mc:"+gjbzhyObject.getGjbzhymc());
//			}
//
//		}
//		hdzssdsjbForm.setGjbzhy(gjbzhyList);
//	}
    
	/**
	 * 取得传入日期所在的季度
	 * @param curDate 日期
	 * @return String 季度
	 */
	public static String preQuarter (java.util.Date curDate)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(curDate);
		int month = calendar.get(calendar.MONTH);
		int year = calendar.get(calendar.YEAR);
		//System.out.println(month);
		int jd = month / 3;
		jd++;
		return new Integer(jd).toString();
	}
	
	public static Object queryDjxxByInterfaceDJ(Connection conn,QysdsjbBaseVo form) throws Exception{
		Djjbsj djsj = null;
		try {
			// 获得企业登记基本信息
			djsj= ServiceManager.getInstance().getDjjbsjService().query(conn, form.getJsjdm());
			//纳税人识别号
			form.setNsrsbh(djsj.getSwdjzh()); 
			// 纳税人名称
			form.setNsrmc(djsj.getNsrmc()); 
			// 所属经济类型-登记注册类型代码
			form.setSsjjlx(djsj.getDjzclxdm());
			// 所属行业代码
			form.setSshy(djsj.getGjbzhydm());
			// 税务机关组织机构代码
			form.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
			return form;
		} catch (Exception ex1) {
			throw new FrameException("没有该纳税人的登记信息或者没有权限查看该纳税人信息！");
		}
	}
	public static void setDeclareData(QysdsReportsDeclare declare,QysdsjbBaseVo qysdsjbBaseVo) throws FrameException{
		Connection conn=null;
		Djjbsj djsj=null;
		try {
			conn=ResourceManager.getConnection();
			// 获得企业登记基本信息
			djsj= ServiceManager.getInstance().getDjjbsjService().query(conn, qysdsjbBaseVo.getJsjdm());	
		} catch (FrameException e) {
			e.printStackTrace();
			throw e;
		}finally{
			QysdsHelperUtil.dbResClose(null, null, conn);
		}
		Jbxx jbxx=new Jbxx();//	基本信息	
		jbxx.setJsjdm(djsj.getJsjdm());
		jbxx.setNsrmc(djsj.getNsrmc());
		jbxx.setSsjjlx(djsj.getDjzclxdm());//基本信息(JBXX)-所属经济类型
		jbxx.setSshy(djsj.getGjbzhydm());//基本信息(JBXX)-所属行业
		jbxx.setZsfs(qysdsjbBaseVo.getZsfsdm());//基本信息(JBXX)-征收方式
		declare.setJbxx(jbxx);
		declare.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
		declare.setQxdm(djsj.getSwjgzzjgdm().substring(0,2));
		// 录入人
		declare.setLrr(qysdsjbBaseVo.getLrr());
		// 申报日期
		declare.setSbrq(FrameCommonAccess.getDBDate());

		// 创建人
		declare.setCjr(qysdsjbBaseVo.getLrr());

		// 录入日期
		declare.setLrrq(FrameCommonAccess.getDBDate());

		// 创建日期
		declare.setCjrq(FrameCommonAccess.getDBDate());
	}
	public static void qysdsCheckAll(Connection conn,CheckBean checkBean,QysdsjbBaseVo qysdsjbBaseVo){
		try{
			checkBean.setJsjdm(qysdsjbBaseVo.getJsjdm());
			checkBean.setSkssrqq(qysdsjbBaseVo.getSkssksrq());
			checkBean.setSkssrqz(qysdsjbBaseVo.getSkssjsrq());
			checkBean.setCheckJdlx(true);
			checkBean.setCheckQsq(true);
			checkBean.setCheckZfba(true);
			checkBean.setJdlxCheckStyle("2");
			ServiceManager.getInstance().getQysdsCheckServer().check(conn, checkBean);
			qysdsjbBaseVo.setJdlx(checkBean.getJdlx());
		}catch(Exception e){
			//鉴定类型抛出异常后，需要给鉴定类型赋值，前台判空用
			checkBean.setJdlx("-1");
		}
	}	
}

