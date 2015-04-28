package com.lscdz.qysds.common.service.sfgl.processor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.sfgl.ISfglService;
import com.lscdz.qysds.common.service.sfgl.SfglConstant;
import com.lscdz.qysds.common.service.sfgl.bo.Ysjc;
import com.lscdz.qysds.common.service.sfgl.bo.Yskm;
import com.lscdz.qysds.common.service.sfgl.util.DateUtil;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;


public class SfglService implements ISfglService{

	@Override
	/**
	 * 插入减免数据
	 * 
	 * @param table
	 * @return
	 */
	public void insertJmProce(Connection con,QysdsReportsDeclare declarein)throws FrameException{

		// 报表对象
		QysdsReportsDeclare declare = declarein;
//		Connection con = null;
		CallableStatement st = null;
		String sql = "";
		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		try {
			String jsjdm = declare.getNsrjsjdm();
			String jmlx = SfglConstant.JMLX_SP;
			String szsmdm = SfglConstant.SZSM_QYSDSCODE;
			Timestamp sbrq = now;
			String fsdm = SfglConstant.FSDM_WSSB;
			String jzbz = SfglConstant.SMSB_JZBZ;
			String lrr = declare.getNsrjsjdm();
			Timestamp skssjsrq = declare.getSkssjsrq();
			Timestamp skssksrq = declare.getSkssksrq();
			String swjgzzjgdm = declare.getSwjgzzjgdm();
			String qxdm = declare.getSwjgzzjgdm().substring(0, 2);
			String djzclxdm = declare.getJbxx().getSsjjlx();
			String gjbzhydm = declare.getJbxx().getSshy();
			String nd = declare.getSknd();
			String ysjcdm = getYsjc(con,declare.getNsrjsjdm(),
					SfglConstant.SZSM_QYSDSCODE, now).getYsjcdm();
//			String yskmdm = JKAdapter.getInstance().getYskm(
//					SfglConstant.SZSM_QYSDSCODE, declare.getJbxx().getSsjjlx(),
//					declare.getJbxx().getSshy(), ysjcdm).getYskmdm();
//			String yskmdm=getYskmdm(SfglConstant.SZSM_QYSDSCODE, declare.getJbxx().getSsjjlx(),declare.getJbxx().getSshy(), ysjcdm);
			String yskmdm=getYskm(con,SfglConstant.SZSM_QYSDSCODE, declare.getJbxx().getSsjjlx(),declare.getJbxx().getSshy(), ysjcdm).getYskmdm();

			// 创建数据库连接
//			con = ResourceManager.getConnection();
			sql = "BEGIN sbdb.sb_pkg_sbjmtools.insertjmproce(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";

			st = con.prepareCall(sql);

			System.out.println("-------------调用插入减免申报存储过程参数----------------"); 
			System.out.println("1-jsjdm--" + jsjdm);
			System.out.println("2-jmlx--" + jmlx);
			System.out.println("3-szsmdm--" + szsmdm);
			System.out.println("4-sbrq--" + sbrq);
			System.out.println("5-fsdm--" + fsdm);
			System.out.println("6-jzbz--" + jzbz);
			System.out.println("7-lrr--" + lrr);
			System.out.println("8-skssjsrq--" + skssjsrq);
			System.out.println("9-skssksrq--" + skssksrq);
			System.out.println("10-swjgzzjgdm--" + swjgzzjgdm);
			System.out.println("11-qxdm--" + qxdm);
			System.out.println("12-djzclxdm--" + djzclxdm);
			System.out.println("13-gjbzhydm--" + gjbzhydm);
			System.out.println("14-nd--" + nd);
			System.out.println("15-ysjcdm--" + ysjcdm);
			System.out.println("16-yskmdm--" + yskmdm);
			
			st.setString(1, jsjdm);
			st.setString(2, jmlx);
			st.setString(3, szsmdm);
			st.setTimestamp(4, sbrq);
			st.setString(5, fsdm);

			st.setString(6, jzbz);
			st.setString(7, lrr);
			st.setTimestamp(8, skssjsrq);
			st.setTimestamp(9, skssksrq);
			st.setString(10, swjgzzjgdm);

			st.setString(11, qxdm);
			st.setString(12, djzclxdm);
			st.setString(13, gjbzhydm);
			st.setString(14, nd);
			st.setString(15, ysjcdm);

			st.setString(16, yskmdm);

			st.execute();

		} catch (Exception e) {

			e.printStackTrace();
			throw new FrameException("插入减免税失败！"+e.getMessage());
		} finally
        {

			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    }

	}
	
	/**
	 * 通过税种税目代码来确定预算级次<br>
	 * 当税种为文化事业建设费或外商投资企业土地使用费时 通过税费接口取得相应的预算级次其他情况为地方级
	 * 
	 * @param jsjdm
	 *            计算机代码
	 * @param szsmdm
	 *            税种税目代码
	 * @param rq
	 *            日期 java.util.Date
	 * @return Ysjc 预算级次信息
	 * @throws Exception
	 */
	private Ysjc getYsjc(Connection con,String jsjdm, String szsmdm, java.util.Date rq)throws Exception{
		Ysjc ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF); // 其他情况都为地方级
		// if(szsmdm.substring(0, 2).equals(SzsmdmConstant.WHSYJSF) ||
		// szsmdm.substring(0, 2).equals(SzsmdmConstant.CSJTFWSYF))
		// {
		// 调用税费管理接口得到预算级次
		try {
			//com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
			//com.ttsoft.bjtax.sfgl.common.model.Ysjc sfysjc = sfServiceProxy.getYsjcInfo(jsjdm, szsmdm, rq);
			Ysjc sfysjc=getYsjcInfo(con,jsjdm, szsmdm, rq);
			if (sfysjc != null) {
				ysjc = new Ysjc(sfysjc.getYsjcdm(), sfysjc.getYsjcmc());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			//throw ExceptionUtil.getBaseException(ex);
			throw new Exception("获取预算级次失败！"+ex.getMessage());
		}
		// }

		return ysjc;
	}
	
	   /**
     * 查询预算级次 G2G2.2
     * @param jsjdm String，长度8位字符，计算机代码；
     * @param smszdm String，长度9位字符，税种税目代码；
     * @param rq Date，日期
     * @return yshc 预算级次的form
     * @throws BaseException
     */
	private Ysjc getYsjcInfo(Connection conn,String jsjdm, String smszdm, Date rq) throws Exception{
//        Connection conn = null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Ysjc ret = new Ysjc();

        try
        {
//            conn = ResourceManager.getConnection();
            StringBuffer sql=new StringBuffer();
            sql.append("select distinct b.YSJCDM,b.YSJCMC,a.rdrq from SFDB.SF_JL_TSYSJCMX a,dmdb.SF_DM_YSJC b where a.jsjdm='")
            	.append(jsjdm)
            	.append("' and a.szsmdm like '")
            	.append(smszdm.substring(0,2))
            	.append("%' and a.rdrq<=to_date('")
            	.append(DateUtil.getDate(rq))
            	.append(" 23:59:59','yyyy-MM-dd hh24:mi:ss') order by a.rdrq desc");
            System.out.println(sql);
            ps=conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            if(rs.next()){
                ret.setYsjcdm(rs.getString("YSJCDM"));
                ret.setYsjcmc(rs.getString("YSJCMC"));
            }else{
                return null;
            }
            return ret;
        }catch (Exception ex){
        	ex.printStackTrace();
            throw new FrameException("获取预算级次信息失败！");
        }finally
        {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    }

    }

    

	/**
	 * 获取预算科目接口方法
	 * 
	 * @param szsmdm
	 *            String
	 * @param djzclxdm
	 *            String
	 * @param gjbzhydm
	 *            String
	 * @param ysjcdm
	 *            String
	 * @return Yskm
	 * @throws ZwclException
	 */

	private Yskm getYskm(Connection con,String szsmdm, String djzclxdm, String gjbzhydm,
			String ysjcdm) throws FrameException {
		// 判断入口参数
		if (szsmdm == null || szsmdm.length() < 2) {
			throw new FrameException("税种税目代码输入非法！");
		}
//		Connection con = null;
		CallableStatement st = null;
		String sql = "";
		Yskm yskm = new Yskm();
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String curdate = df.format(now);
			String nd = curdate.substring(0, 4);
			System.out.println("====nd===" + nd);

//			con = ResourceLocator.getConnection();
//			con = ResourceManager.getConnection();
			sql = "BEGIN Jkdb.sb_pkg_tools.getyusuankemu(?, ?, ?, ?,?,?, ?, ?, ?,?); END;";

			st = con.prepareCall(sql);
			st.setString(1, szsmdm);
			st.setString(2, djzclxdm);
			st.setString(3, gjbzhydm);
			st.setString(4, ysjcdm);
			st.setString(5, nd);
			st.registerOutParameter(6, Types.VARCHAR);
			st.registerOutParameter(7, Types.VARCHAR);
			st.registerOutParameter(8, Types.NUMERIC);
			st.registerOutParameter(9, Types.NUMERIC);
			st.registerOutParameter(10, Types.NUMERIC);

			st.execute();

			yskm.setYskmdm(st.getString(6));
			yskm.setYskmmc(st.getString(7));
			yskm.setZyfcbl(st.getBigDecimal(8));
			yskm.setSjfcbl(st.getBigDecimal(9));
			yskm.setQxfcbl(st.getBigDecimal(10));
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("帐务处理接口：获取预算科目代码失败！");
		} finally
        {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
	    }
		return yskm;
	}
}
