package com.ttsoft.bjtax.smsb.gdzys.cx.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.cx.web.GdzysJmszmCxForm;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * <p>
 * Title:北京地税核心征管系统--耕地占用税业务查询
 * </p>
 * <p>
 * Description:减免税证明查询Processor
 * </p>
 * 
 * @author 开发部-霍洪波
 * @version 1.0
 */
public class GdzysJmszmCxProcessor implements Processor {

	public Object process(VOPackage vo) throws BaseException {
		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		// 获取分局数据
		case CodeConstant.SMSB_DQJSLIST:
			result = dogetdqjslist(vo);
			break;
		// 获取税务所数据
		case CodeConstant.SMSB_CXDQJSLIST:
			result = dogetcxdqjslist(vo);
			break;
		// 查询
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		default:
			throw new UnsupportedOperationException(
					"Method process() not yet implemented.");
		}
		return result;
	}

	private Object doQuery(VOPackage vo) {
		// 获取form
		GdzysJmszmCxForm form = (GdzysJmszmCxForm) vo.getData();

		// 连接数据库
		PreparedStatement ps = null;
		Connection conn = null;
		// 获取基本数据sql

		try {
			int count = 0;// 查询总记录条数
			int currentPage = form.getCurrentPage();//当前页数
			int startNum = (currentPage - 1) * (form.getPageSize()) + 1;//开始记录数（用于分页rownum）
			int endNum = currentPage * (form.getPageSize());//结束记录数（用于分页rownum）

			// 获取连接
			conn = SfDBResource.getConnection();

			StringBuffer buffer = new StringBuffer();
			// 查询所有记录数的数目sql
			buffer.append("SELECT count(*) totalcount from(SELECT b.sbbxlh,a.jmszmbh,a.kjrq,a.qxdm,");
			buffer.append("(SELECT COUNT(*) FROM SBDB.Sb_JL_GDZYS_JMSZMDYRZ c WHERE c.sbbxlh=a.sbbxlh AND c.jmszmbh=a.jmszmbh) dycs,");
			buffer.append("(SELECT swjgzzjgmc FROM  dmdb.gy_dm_swjgzzjg d WHERE d.swjgzzjgdm=a.qxdm) kjjg,");
			buffer.append("a.dyry,a.dyrq,a.zfbz,a.jzbz FROM SBDB.SB_JL_GDZYS_JMSZM a,SBDB.SB_JL_GDZYS_SBB b ");
			buffer.append(" WHERE a.sbbxlh=b.sbbxlh");
			//开始日期
			if (form.getYstart() != null
					&& form.getYstart().trim().length() > 0) {
				buffer.append(" and a.kjrq >=to_date(?,'yyyyMMdd') ");
			}
			//终止日期
			if (form.getYend() != null && form.getYend().trim().length() > 0) {
				buffer.append(" and a.kjrq<to_date(?,'yyyyMMdd') ");
			}
			//分局代码
			if (form.getDqjs() != null && form.getDqjs().trim().length() > 0) {
				buffer.append(" and b.qxdm=? ");
			}
			//税务所代码
			if (form.getCxdqjs() != null
					&& form.getCxdqjs().trim().length() > 0) {
				buffer.append(" and a.qxdm=? ");
			}
			//减免税证明状态（正常和撤销）
			if (!form.getStatus().equals("全部")
					&& !form.getStatus().trim().equals("")) {
				buffer.append(" and a.zfbz=? ");
			}
			//减免税证明编号
			if (form.getJmszmbh() != null
					&& form.getJmszmbh().trim().length() > 0) {
				buffer.append(" and a.jmszmbh=? ");
			}

			buffer.append(")");

			ps = conn.prepareStatement(buffer.toString());
			int i = 1;
			//如果条件有值的话，就给它们设值
			if (form.getYstart() != null
					&& form.getYstart().trim().length() > 0) {
				ps.setString(i, form.getYstart().trim());
				i++;
			}
			if (form.getYend() != null && form.getYend().trim().length() > 0) {
				ps.setString(i, form.getYend().trim());
				i++;
			}

			if (form.getDqjs() != null && form.getDqjs().trim().length() > 0) {
				ps.setString(i, form.getDqjs().trim().substring(0, 2));
				i++;
			}

			if (form.getCxdqjs() != null
					&& form.getCxdqjs().trim().length() > 0) {
				ps.setString(i, form.getCxdqjs().trim());
				i++;
			}

			if (!form.getStatus().equals("全部")
					&& !form.getStatus().trim().equals("")) {
				ps.setString(i, form.getStatus().trim());
				i++;
			}
			if (form.getJmszmbh() != null
					&& form.getJmszmbh().trim().length() > 0) {
				ps.setString(i, form.getJmszmbh().trim());
				i++;
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // 取得所有条数的计数
				count = rs.getInt(1);
			}
			System.out.println("###########count=" + count);
			//把所有记录条数值放到form中
			form.setTotalRowCount(count);
			//关闭连接
			rs.close();

			buffer = new StringBuffer();
			//查询出所有记录的sql
			buffer.append("SELECT *FROM (SELECT ROWNUM r,b.sbbxlh,a.jmszmbh,b.jsjdm,b.nsrmc,a.kjrq,a.qxdm,");
			buffer.append("(SELECT COUNT(*) FROM SBDB.Sb_JL_GDZYS_JMSZMDYRZ c WHERE c.sbbxlh=a.sbbxlh AND c.jmszmbh=a.jmszmbh) dycs,");
			buffer.append("(SELECT swjgzzjgmc FROM  dmdb.gy_dm_swjgzzjg d WHERE d.swjgzzjgdm=a.qxdm) kjjg,");
			buffer.append("a.dyry,a.dyrq,a.zfbz,a.jzbz FROM SBDB.SB_JL_GDZYS_JMSZM a,SBDB.SB_JL_GDZYS_SBB b ");
			buffer.append("WHERE a.sbbxlh=b.sbbxlh ");
			//开始日期
			if (form.getYstart() != null
					&& form.getYstart().trim().length() > 0) {
				buffer.append(" and a.kjrq >=to_date(?,'yyyyMMdd') ");
			}
			//终止日期
			if (form.getYend() != null && form.getYend().trim().length() > 0) {
				buffer.append(" and a.kjrq<to_date(?,'yyyyMMdd') ");
			}
			//分局代码
			if (form.getDqjs() != null && form.getDqjs().trim().length() > 0) {
				buffer.append(" and b.qxdm=? ");
			}
			//税务所代码
			if (form.getCxdqjs() != null
					&& form.getCxdqjs().trim().length() > 0) {
				buffer.append(" and a.qxdm=? ");
			}
			//减免税证明的状态（正常和撤销）
			if (!form.getStatus().equals("全部")
					&& !form.getStatus().trim().equals("")) {
				buffer.append(" and a.zfbz=? ");
			}
			//减免税证明编号
			if (form.getJmszmbh() != null
					&& form.getJmszmbh().trim().length() > 0) {
				buffer.append(" and a.jmszmbh=? ");
			}

			buffer.append("AND ROWNUM<=?) WHERE r>=?");
			System.out.println(buffer.toString() + "查询的sql#########");

			ps = conn.prepareStatement(buffer.toString());
			i = 1;

			if (form.getYstart() != null
					&& form.getYstart().trim().length() > 0) {
				ps.setString(i, form.getYstart().trim());
				i++;
			}
			if (form.getYend() != null && form.getYend().trim().length() > 0) {
				ps.setString(i, form.getYend().trim());
				i++;
			}

			if (form.getDqjs() != null && form.getDqjs().trim().length() > 0) {
				ps.setString(i, form.getDqjs().trim().substring(0, 2));
				i++;
			}

			if (form.getCxdqjs() != null
					&& form.getCxdqjs().trim().length() > 0) {
				ps.setString(i, form.getCxdqjs().trim());
				i++;
			}

			if (!form.getStatus().equals("全部")
					&& !form.getStatus().trim().equals("")) {
				ps.setString(i, form.getStatus().trim());
				i++;
			}
			if (form.getJmszmbh() != null
					&& form.getJmszmbh().trim().length() > 0) {
				ps.setString(i, form.getJmszmbh().trim());
				i++;
			}
			//终止记录数值（分页rownum）
			ps.setInt(i++, endNum);
			//开始记录数值（分页rownum）
			ps.setInt(i++, startNum);
			//结果集
			ResultSet rst = ps.executeQuery();
			// 存放结果集集合
			List list = new ArrayList();
			// 存放每条记录的集合（相当于model）
			Map map = null;
			while (rst.next()) {
				map = new HashMap();
				// 申报序列号
				map.put("sbbxlh",
						rst.getString("sbbxlh") == null ? "" : rst
								.getString("sbbxlh"));
				// 减免税证明编号
				map.put("jmszmbh",
						rst.getString("jmszmbh") == null ? "" : rst
								.getString("jmszmbh"));
				//计算机代码
				map.put("jsjdm", rst.getString("jsjdm")==null?"":rst.getString("jsjdm"));
				//纳税人名称
				map.put("nsrmc", rst.getString("nsrmc")==null?"":rst.getString("nsrmc"));
				//取得开具日期的timestamp
				Timestamp ts=rst.getTimestamp("kjrq");
				//取得打印日期的timestamp
				Timestamp tsdyrq=rst.getTimestamp("dyrq");
				String kjrq="";
				String dyrq="";
				if(ts!=null){
					//取得开具日期的时间long值
					long mil =ts.getTime();
					Date date =new Date(mil);
					DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					kjrq=df.format(date);
				}
				if(tsdyrq!=null){
					//取得打印日期的时间long值
					long mdyrq=tsdyrq.getTime();
					Date date1=new Date(mdyrq);
					DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					dyrq=df.format(date1);
				}
				// 开具日期
				map.put("kjrq",
						kjrq);
				// 开具机关
				map.put("kjjg",
						rst.getString("kjjg") == null ? "" : rst
								.getString("kjjg"));
				// 打印次数
				map.put("dycs",
						rst.getString("dycs") == null ? "" : rst
								.getString("dycs"));
				// 打印日期
				map.put("dyrq",
						dyrq);
				// 打印人员
				map.put("dyry",
						rst.getString("dyry") == null ? "" : rst
								.getString("dyry"));
				// 类型（'0'代表正常，'1'代表已撤销）
				map.put("lx", rst.getString("zfbz") == null ? "" : (rst
						.getString("zfbz").equals("0") ? "正常" : "已撤销"));
				// 是否已记账（'000000'代表未记账，其他代表已记账）
				map.put("jzbz", rst.getString("jzbz") == null ? "" : (rst
						.getString("jzbz").equals("000000") ? "未记账" : "已记账"));
				// 将记录添加到list集合中
				list.add(map);
			}

			form.setDataList(list);
			rst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}

	/**
	 * 获取分局数据list
	 */
	private Object dogetdqjslist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GdzysJmszmCxForm theForm = (GdzysJmszmCxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		UserData userdata = (UserData) vo.getUserData();
		try {
			swdwmodel model = null;
			swdwmodel model1 = null;
			List list = new ArrayList();
			con = SfDBResource.getConnection();
			String gxswjgzzjgdm = userdata.getSsdwdm();
			// 根据登录用户的信息获取登陆的居所的信息
			// 90是市局，可以查询所有分局
			if ("90".equals(gxswjgzzjgdm.substring(0, 2))) {
				// 查的是所有分局
				//sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm like '%00' "
					//	+ " and b.swjgzzjgdm!='9000' order by b.swjgzzjgdm ";
				sql="SELECT b.qxdm||'00',b.qxfjmc FROM  DMDB.GD_DM_QXFJDM b where b.qxdm!='90' and (b.zxbz='0' or b.zxbz is null)  order by qxdm";
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				while (rs.next()) {
					model = new swdwmodel();
					model.setSwdwid(rs.getString(1));
					model.setSwdwmc(rs.getString(2));
					list.add(model);
				}
				theForm.setSwdwlist(list);
				return theForm;
			} else {
				//sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm='"
						//+ gxswjgzzjgdm.substring(0, 2)
						//+ "00' order by b.swjgzzjgdm ";
				sql="select b.qxdm||'00',b.qxfjmc from dmdb.gd_dm_qxfjdm b where b.qxdm='"
						+gxswjgzzjgdm.substring(0,2)
						+"' and (b.zxbz='0' or zxbz is null) order by b.qxdm";
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				// System.out.println(sql + "-------------查登陆税务机关的sql");
				while (rs.next()) {
					model = new swdwmodel();
					model.setSwdwid(rs.getString(1));
					model.setSwdwmc(rs.getString(2));
					list.add(model);
				}
				theForm.setSwdwlist(list);
				return theForm;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}

	}

	/**
	 * 获取税务所信息list
	 */
	private Object dogetcxdqjslist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GdzysJmszmCxForm theForm = (GdzysJmszmCxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		UserData userdata = (UserData) vo.getUserData();
		try {

			swdwmodel model1 = null;

			List list1 = new ArrayList();
			con = SfDBResource.getConnection();
			String gxswjgzzjgdm = theForm.getDqjs();
			// 根据登录用户的信息获取登陆的居所的信息
			if ("30".equals(userdata.yhjb)) {
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where "
						+ "  b.swjgzzjgdm='"
						+ userdata.getSsdwdm()
						+ "' and (zxbs='0' or zxbs is null) order by b.swjgzzjgdm ";
			} else {
				// 根据分局查税务所
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm like '"
						+ gxswjgzzjgdm.substring(0, 2)
						+ "%' "
						+ " and b.swjgzzjgdm!='"
						+ gxswjgzzjgdm
						+ "' and (zxbs='0' or zxbs is null) order by b.swjgzzjgdm ";
			}

			System.out.println("###########sql=" + sql);

			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				model1 = new swdwmodel();
				model1.setSwdwid(rs.getString(1));
				model1.setSwdwmc(rs.getString(2));
				list1.add(model1);
			}
			theForm.setCxswdwlist(list1);
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}

			return theForm;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}

	}

}
