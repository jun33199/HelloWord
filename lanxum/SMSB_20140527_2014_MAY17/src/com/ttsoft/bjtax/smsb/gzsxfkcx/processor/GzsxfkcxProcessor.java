package com.ttsoft.bjtax.smsb.gzsxfkcx.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; //import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx; //import com.ttsoft.bjtax.shenbao.util.DBResource;
//import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm; //import com.ttsoft.bjtax.smsb.model.hylxmodel;
import com.ttsoft.bjtax.smsb.model.client.djlxmodel;
import com.ttsoft.bjtax.smsb.model.client.hylxmodel;
import com.ttsoft.bjtax.smsb.model.client.nsrtogzsx;
import com.ttsoft.bjtax.smsb.model.client.nsrztmodel;
import com.ttsoft.bjtax.smsb.model.client.scjxmodel;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.model.client.swjgtolist;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class GzsxfkcxProcessor implements Processor {
	/**
	 * 通用处理调度模块 wcl2009.4.7添加
	 */
	private static final String DJ_JBSJ = "JBSJ";
	private static final int SESSION_ID = 0;

	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}
		switch (vo.getAction()) {
		case CodeConstant.SMSB_GZSXFKJSJSDMQUERY:
			result = dofkjsjdmQuery(vo);
			break;
		case CodeConstant.SMSB_GZSXFKTJQUERY:
			result = dofktjQuery(vo);
			break;
		case CodeConstant.SMSB_GZSXYYDQUERY:
			result = doyydqdquery(vo);
			break;
		case CodeConstant.SMSB_GZSXWYDQUERY:
			result = dowydqdquery(vo);
			break;
		case CodeConstant.SMSB_DQJSLIST:
			result = dogetdqjslist(vo);
			break;
		case CodeConstant.SMSB_HYLXLIST:
			result = dogethylxlist(vo);
			break;
		case CodeConstant.SMSB_DJLXLIST:
			result = dogetdjlxlist(vo);
			break;
		case CodeConstant.SMSB_NSRZTLIST:
			result = dogetnsrztlist(vo);
			break;
		case CodeConstant.SMSB_SCJXLIST:
			result = dogetscjxlist(vo);
			break;
		case CodeConstant.SMSB_CXDQJSLIST:
			result = dogetcxdqjslist(vo);
			break;
		default:
			throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
		}
		return result;
	}

	/**
	 * 保存后台处理 获得所处街乡的list
	 */
	private Object dogetcxdqjslist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm theForm = (GzsxwhForm) vo.getData();
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
						+ "  b.swjgzzjgdm='" + userdata.getSsdwdm()+ "'";
			} else {
				// 根据分局查税务所
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm like '"
						+ gxswjgzzjgdm.substring(0, 2)
						+ "%' "
						+ " and b.swjgzzjgdm!='" + gxswjgzzjgdm + "' order by b.swjgzzjgdm";
			}
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				model1 = new swdwmodel();
				model1.setSwdwid(rs.getString(1));
				model1.setSwdwmc(rs.getString(2));
				list1.add(model1);
			}
			theForm.setCxswdwlilst(list1);
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}

			sql = "select b.scjxdm,b.scjxmc from dmdb.dj_dm_scjx b where b.qxdm='"
					+ gxswjgzzjgdm + "' order by b.scjxdm";
			//System.out.println(sql + "-------------查所处街乡的sql");
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			scjxmodel model = null;
			List list = new ArrayList();
			while (rs.next()) {
				// 构造model的数据
				model = new scjxmodel();
				model.setJxid(rs.getString(1));
				model.setJxmc(rs.getString(2));
				list.add(model);
			}
			theForm.setScjxlilst(list);
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

	/**
	 * 保存后台处理 获得所处街乡的list
	 */
	private Object dogetscjxlist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm theForm = (GzsxwhForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		UserData userdata = (UserData) vo.getUserData();
		try {
			String qxdm = userdata.gxswjgzzjgdm.substring(0, 2) + "00";
			// 根据登录用户的信息获取登陆的居所的信息
			sql = "select b.scjxdm,b.scjxmc from dmdb.dj_dm_scjx b where b.qxdm='"
					+ qxdm + "'";
			//System.out.println(sql + "---查所处街乡的sql不过这个事这行不到了 --");
			con = SfDBResource.getConnection();// 在申报模块中添加获取数据库连接的类
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			scjxmodel model = null;
			List list = new ArrayList();
			while (rs.next()) {
				// 构造model的数据
				model = new scjxmodel();
				model.setJxid(rs.getString(1));
				model.setJxmc(rs.getString(2));
				list.add(model);
			}
			theForm.setScjxlilst(list);
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

	/**
	 * 保存后台处理 获得纳税人状态的list
	 */
	private Object dogetnsrztlist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm theForm = (GzsxwhForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		try {
			// 根据登录用户的信息获取登陆的居所的信息
			sql = "select b.nsrztdm,b.nsrztmc from dmdb.dj_dm_nsrzt b order by b.nsrztdm";
			//System.out.println(sql+"--------查纳税人状态的sql");
			con = SfDBResource.getConnection();// 在申报模块中添加获取数据库连接的类
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			nsrztmodel model = null;
			List list = new ArrayList();
			while (rs.next()) {
				// 构造model的数据
				model = new nsrztmodel();
				model.setNsrztid(rs.getString(1));
				model.setNsrztmc(rs.getString(2));
				list.add(model);
			}
			theForm.setNsrztlilst(list);
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

	/**
	 * 保存后台处理 获得登记类执行的list
	 */
	private Object dogetdjlxlist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm theForm = (GzsxwhForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		try {
			// 根据登录用户的信息获取登陆的居所的信息
			sql = "select b.djzclxdm,b.djzclxmc from dmdb.dj_dm_djzclx b order by b.djzclxdm";
			//System.out.println(sql+"--------查企业登记类型的sql");
			con = SfDBResource.getConnection();// 在申报模块中添加获取数据库连接的类
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			djlxmodel model = null;
			List list = new ArrayList();
			while (rs.next()) {
				// 构造model的数据
				model = new djlxmodel();
				model.setDjlxid(rs.getString(1));
				model.setDjlxmc(rs.getString(2));
				list.add(model);
			}
			theForm.setDjlxlilst(list);
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

	/**
	 * 保存后台处理 获得行业类执行的list
	 */
	private Object dogethylxlist(VOPackage vo) throws BaseException {
		GzsxwhForm theForm = (GzsxwhForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		try {
			// 根据登录用户的信息获取登陆的居所的信息
			sql = "select b.gjbzhydm,b.gjbzhymc from dmdb.gy_dm_gjbzhy b order by b.gjbzhydm";
			//System.out.println(sql+"--------查企业行业类型的sql");
			con = SfDBResource.getConnection();// 在申报模块中添加获取数据库连接的类
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			hylxmodel model = null;
			List list = new ArrayList();
			while (rs.next()) {
				// 构造model的数据
				model = new hylxmodel();
				if(rs.getString(1).length()==4){
				model.setHylxid(rs.getString(1));
				model.setHylxmc(rs.getString(2));
				}
				list.add(model);
			}
			theForm.setHylxlilst(list);
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

	/**
	 * 保存后台处理 获得地区居所的list
	 */
	private Object dogetdqjslist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm theForm = (GzsxwhForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		UserData userdata = (UserData) vo.getUserData();
		try {
			swdwmodel model = null;
			swdwmodel model1 = null;
			List list = new ArrayList();
			List list1 = new ArrayList();
			con = SfDBResource.getConnection();
			String gxswjgzzjgdm = userdata.getSsdwdm();
			// 根据登录用户的信息获取登陆的居所的信息
			//gxswjgzzjgdm="0604";
			if ("90".equals(gxswjgzzjgdm.substring(0, 2))) {
				// 查的是搜有分局
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm like '%00' "
						+ " and b.swjgzzjgdm!='9000'";
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				while (rs.next()) {
					model = new swdwmodel();
					model.setSwdwid(rs.getString(1));
					model.setSwdwmc(rs.getString(2));
					list.add(model);
				}
				theForm.setSwdwlilst(list);
				return theForm;
			} else {
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm='"
						+ gxswjgzzjgdm.substring(0, 2) + "00'";
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				//System.out.println(sql + "-------------查登陆税务机关的sql");
				while (rs.next()) {
					model = new swdwmodel();					
					model.setSwdwid(rs.getString(1));
					model.setSwdwmc(rs.getString(2));					
					list.add(model);
				}
				theForm.setSwdwlilst(list);
				
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
	 * 保存后台处理 通过计算机代码查询告知事项反馈
	 */
	private Object dofkjsjdmQuery(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm theForm = (GzsxwhForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String sql1 = null;
		PreparedStatement cstmt = null;
		String gjbzhydm = null;
		String djzclxdm = null;
		String swjgzzjgdm = null;
		try {
			sql1 = "select b.gjbzhydm,b.djzclxdm,b.swjgzzjgdm,b.nsrmc from djdb.dj_jl_jbsj b where b.jsjdm ='"
					+ theForm.getJsjdm() + "'";
			//System.out.println(sql1+"通过计算机代码的方式是执行的第一个sql，查询的是纳税人的基本信息");
			con = SfDBResource.getConnection();// 在申报模块中添加获取数据库连接的类
			cstmt = con.prepareStatement(sql1);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				// 构造model的数据
				gjbzhydm = rs.getString(1);
				djzclxdm = rs.getString(2);
				swjgzzjgdm = rs.getString(3);
				theForm.setNsrmc(rs.getString(4));
			}
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}
			sql
					.append("select  Distinct b.gzsx_id,b.nsrmc,b.gzsxfcrq ,b.gzsxnr from sbdb.sb_jl_gzsx b where ( "
							+ " b.jsjdm='"
							+ theForm.getJsjdm()
							+ "' or ("
							+ "b.swjgzzjgdm2='"
							+ swjgzzjgdm.substring(0, 2)
							+ "00' and (b.gjbzhydm='"
							+ gjbzhydm
							+ "' or b.gjbzhydm='0') and (b.djzclxdm='"
							+ djzclxdm
							+ "' or b.djzclxdm='0') ) or (b.swjgzzjgdm2='"
							+ swjgzzjgdm
							+ "' and (b.gjbzhydm='"
							+ gjbzhydm
							+ "' or b.gjbzhydm='0') and (b.djzclxdm='"
							+ djzclxdm
							+ "' or b.djzclxdm='0')) or (b.swjgzzjgdm='"+swjgzzjgdm+"' and (b.gjbzhydm='"
							+ gjbzhydm
							+ "' or b.gjbzhydm='0') and (b.djzclxdm='"
							+ djzclxdm
							+ "' or b.djzclxdm='0'))"+" or (b.nsrmc='全部纳税人' and (b.swjgzzjgdm='"
							+ swjgzzjgdm.substring(0, 2)
							+ "00' or b.swjgzzjgdm='"+swjgzzjgdm+"' or b.swjgzzjgdm like '90%')))");

			if (!"".equals(theForm.getGzqsrq().trim())
					&& !"".equals(theForm.getGzjzrq().trim()))
				sql.append(" and b.gzsxfcrq between to_date('"
						+ theForm.getGzqsrq() + "','yyyyMMdd') and to_date('"
						+ theForm.getGzjzrq() + "','yyyyMMdd')");
			//System.out.println("dofkjsjdmQuery222-------" + sql);
			// con = SfDBResource.getConnection();// 在申报模块中添加获取数据库连接的类
			cstmt = con.prepareStatement(sql.toString());
			rs = cstmt.executeQuery();
			swjgtolist model = null;
			List list1 = new ArrayList();
			String temp = new String();
			while (rs.next()) {
				// 构造model的数据
				model = new swjgtolist();
				model.setGzsx_id(rs.getString(1));
				model.setSwjgzzjg(rs.getString(2));
				model.setGzsxfcrq(rs.getString(3));
				//System.out.println(rs.getString(4));
				if (rs.getString(4).length() < 20) {
					temp = rs.getString(4);
				} else {
					temp = rs.getString(4).substring(0, 20);
				}
				model.setGzsxnrbt(temp);
				model.setGzsxnr(rs.getString(4).replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r\n", "<br>"));
				model.setJsjdm(theForm.getJsjdm());
				list1.add(model);

			}
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}
			sql1 = "select a.jsjdm,a.gzsx_id,a.ydsj,a.fknr from sbdb.sb_jl_gzfk a where a.jsjdm='"
					+ theForm.getJsjdm() + "'";
			//System.out.println("dofkjsjdmQuery3333-------" + sql1);
			cstmt = con.prepareStatement(sql1);
			rs = cstmt.executeQuery();
			nsrtogzsx model1 = null;
			List list2 = new ArrayList();
			while (rs.next()) {
				// 构造model的数据
				model1 = new nsrtogzsx();
				model1.setJsjdm(rs.getString(1));
				model1.setGzsx_id(rs.getString(2));
				model1.setYdsj(rs.getString(3));
				if (rs.getString(4) == null) {
					model1.setFknr("该纳税人只阅读，但未提供反馈内容！");
				} else {
					model1.setFknr(rs.getString(4).replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r\n", "<br>"));
				}
				list2.add(model1);
			}
			List list3 = new ArrayList();
			boolean a = true;
			for (int i = 0; i < list1.size(); i++) {
				model = (swjgtolist) list1.get(i);
				for (int j = 0; j < list2.size(); j++) {
					model1 = (nsrtogzsx) list2.get(j);
					if (model1.getJsjdm().equals(model.getJsjdm())
							&& model1.getGzsx_id().equals(model.getGzsx_id())) {
						model.setYdsj(model1.getYdsj());
						model.setFknr(model1.getFknr());
						break;
					}
					model.setYdsj("未阅读!");
					model.setFknr("无反馈!");
				}
				list3.add(model);
			}

			theForm.setJsjdmgzsxlilst(list3);
			theForm.setJlcount(list3.size() + "");
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

	/**
	 * 保存后台处理 通过条件查询告知事项反馈
	 */
	private Object dofktjQuery(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm theForm = (GzsxwhForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement cstmt = null;
		// 靠搞这么复杂有啥用？
		try {
			// 修改sql
			StringBuffer sql = new StringBuffer();
			sql
					.append("select distinct b.gzsx_id,b.gzsxnrbt,b.nsrmc,b.gzsxfcrq ,b.gzsxnr,b.djzclxdm,b.gjbzhydm,b.swjgzzjgdm2,b.jsjdm from sbdb.sb_jl_gzsx b where 1=1");
			if (!"".equals(theForm.getDqjs().trim())) {
				if ("00".equals(theForm.getDqjs().substring(2, 4)))
					sql.append(" and (b.swjgzzjgdm='" + theForm.getDqjs() + "' or b.swjgzzjgdm like '90%' or b.swjgzzjgdm='"+theForm.getCxdqjs1()+"')");
				else
					sql
							.append(" and b.swjgzzjgdm2='" + theForm.getDqjs()
									+ "'");
			} else {
				sql.append(" and b.swjgzzjgdm='"
						+ vo.getUserData().gxswjgzzjgdm.substring(0, 2) + "00"
						+ "'");
			}
			int Qylxlength;
			int Hylblength;
			if (theForm.getQylx1().trim().length() != 5
					&& theForm.getHylb1().trim().length() == 5) {
				// 说明行业类别是有条件的
				sql.append("and (b.djzclxdm ='0' ");
				Qylxlength = theForm.getQylx1().split("\\,").length;
				for (int i = 1; i < Qylxlength; i++) {
					sql.append(" or b.djzclxdm ='"
							+ theForm.getQylx1().split("\\,")[i] + "' ");
				}
				sql.append(" )");
			}

			if (theForm.getQylx1().trim().length() == 5
					&& theForm.getHylb1().trim().length() != 5) {
				sql.append("and (b.gjbzhydm ='0' ");
				Hylblength = theForm.getHylb1().split("\\,").length;
				for (int i = 1; i < Hylblength; i++) {
					sql.append(" or b.gjbzhydm ='"
							+ theForm.getHylb1().split("\\,")[i] + "' ");
				}
				sql.append(" )");
				// sql.append("and( b.gjbzhydm ='"+theForm.getHylb()+"' or
				// b.gjbzhydm ='0')");
			}

			if (theForm.getQylx1().trim().length() != 5
					&& theForm.getHylb1().trim().length() != 5) {
				Qylxlength = theForm.getQylx1().split("\\,").length;
				Hylblength = theForm.getHylb1().split("\\,").length;
				sql.append("and ((b.gjbzhydm='0' and b.djzclxdm='0')");
				sql.append(" or (b.djzclxdm='0' and (b.gjbzhydm='' ");
				for (int i = 1; i < Hylblength; i++) {
					sql.append(" or b.gjbzhydm ='"
							+ theForm.getHylb1().split("\\,")[i] + "' ");
				}
				sql.append(" ))");
				sql.append(" or (b.gjbzhydm ='0' and (b.djzclxdm='' ");
				for (int i = 1; i < Qylxlength; i++) {
					sql.append(" or b.djzclxdm ='"
							+ theForm.getQylx1().split("\\,")[i] + "' ");
				}
				sql.append(" ))");
				sql.append(" or ((b.djzclxdm='' ");
				for (int i = 1; i < Qylxlength; i++) {
					sql.append(" or b.djzclxdm ='"
							+ theForm.getQylx1().split("\\,")[i] + "' ");
				}
				sql.append(" ) and ( b.gjbzhydm='' ");
				for (int i = 1; i < Hylblength; i++) {
					sql.append(" or b.gjbzhydm ='"
							+ theForm.getHylb1().split("\\,")[i] + "' ");
				}
				sql.append(" )))");
			}
			// if
			// (!"".equals(theForm.getQylx().trim())&&"".equals(theForm.getHylb().trim()))
			// sql.append(" and (b.djzclxdm ='"+theForm.getQylx()+"' or
			// b.djzclxdm ='0')");
			//				
			// if
			// (!"".equals(theForm.getHylb().trim())&&"".equals(theForm.getQylx().trim()))
			// sql.append("and( b.gjbzhydm ='"+theForm.getHylb()+"' or
			// b.gjbzhydm ='0')");
			//				
			// if
			// (!"".equals(theForm.getHylb().trim())&&!"".equals(theForm.getQylx().trim()))
			// {
			// sql.append(" and (b.djzclxdm='" + theForm.getQylx() + "' "+" and
			// b.gjbzhydm='" + theForm.getHylb() + "' or (b.gjbzhydm='0' and
			// b.djzclxdm='0')"
			// +" or (b.djzclxdm='"+theForm.getQylx() +"' and b.gjbzhydm='0')"
			// +" or (b.djzclxdm='0' and b.gjbzhydm='"+
			// theForm.getHylb()+"'))");
			// sql.append(" and b.gjbzhydm='" + theForm.getHylb() + "'");
			// }
			if (!"".equals(theForm.getGzqsrq().trim())
					&& !"".equals(theForm.getGzjzrq().trim()))
				sql
						.append(" and b.gzsxfcrq between to_date('"
								+ theForm.getGzqsrq()
								+ "','yyyyMMdd') and to_date('"
								+ theForm.getGzjzrq()
								+ "','yyyyMMdd') or ( b.gzsxfcrq between to_date('"
								+ theForm.getGzqsrq()
								+ "','yyyyMMdd') and to_date('"
								+ theForm.getGzjzrq()
								+ "','yyyyMMdd') and b.nsrmc='全部纳税人' and (b.swjgzzjgdm='"
								+ theForm.getDqjs().substring(0, 2)
								+ "00' or b.swjgzzjgdm like '90%'))");
			else
				sql.append(" or ( b.nsrmc='全部纳税人' and (b.swjgzzjgdm='"
						+ theForm.getDqjs().substring(0, 2)
						+ "00' or b.swjgzzjgdm like '90%'))");
			con = SfDBResource.getConnection();// 在申报模块中添加获取数据库连接的类
			//System.out.println("dofkt11111jQuery--------------" + sql);
			cstmt = con.prepareStatement(sql.toString());
			rs = cstmt.executeQuery();
			//System.out.println("dofkt22222jQuery--------------" + sql);
			swjgtolist model = null;
			List list = new ArrayList();
			String yydlink = "<a href=/webapp/smsb/gzsxfkcxAction.do?actionType=getydqd&gzsxnrbt=";
			String wydlink = "<a href=/webapp/smsb/gzsxfkcxAction.do?actionType=getwydqd&gzsxnrbt=";
			String gasxnrlink = "<a href=/webapp/smsb/gzsxfkcxAction.do?actionType=getgzsxnr&gzsxnr=";
			String temp = new String();
			StringBuffer ydcs = new StringBuffer();
			if ("".equals(theForm.getDqjs()))
				ydcs.append("$-");
			else
				ydcs.append("$" + theForm.getDqjs());

			if ("".equals(theForm.getNsrzt()))
				ydcs.append("$-");
			else
				ydcs.append("$" + theForm.getNsrzt());

			if ("".equals(theForm.getJxdm1()))
				ydcs.append("$-");
			else
				ydcs.append("$" + theForm.getJxdm1());

			if ("".equals(theForm.getHylb1()))
				ydcs.append("$-");
			else
				ydcs.append("$" + theForm.getHylb1());

			if ("".equals(theForm.getQylx1()))
				ydcs.append("$-");
			else
				ydcs.append("$" + theForm.getQylx1());

			if ("".equals(theForm.getGzqsrq()))
				ydcs.append("$-");
			else
				ydcs.append("$" + theForm.getGzqsrq());

			if ("".equals(theForm.getGzjzrq()))
				ydcs.append("$-");
			else
				ydcs.append("$" + theForm.getGzjzrq());
			ydcs.append("$" + theForm.getCxdqjs1());
			while (rs.next()) {
				// 构造model的数据
				model = new swjgtolist();
				model.setGzsx_id(rs.getString(1));
				if (rs.getString(5).length() < 20) {
					temp = rs.getString(5);
				} else {
					temp = rs.getString(5).substring(0, 20);
				}
				model.setGzsxnrbt(temp);
				model.setSwjgzzjg(rs.getString(3));
				model.setGzsxfcrq(rs.getString(4));
				model.setGzsxnr(rs.getString(5).replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r\n", "<br>") );
				model.setYydlink("已阅读：" + temp + "...的清单");
				model.setWydlink("未阅读：" + temp + "...的清单");
				model.setYdcs(rs.getString(1) + "$" + temp.replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r\n", "")
						+ ydcs.toString() + "$" + rs.getString(6) + "$"
						+ rs.getString(7) + "$" + rs.getString(8) + "$"
						+ rs.getString(9) + "$" + rs.getString(3));

				list.add(model);
			}

			//System.out.println("dofktj5555555Query--------------" + sql);
			theForm.setTjgzsxlilst(list);
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

	/**
	 * 保存后台处理 通过已阅读的连接查看阅读的清单
	 */
	private Object doyydqdquery(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm theForm = (GzsxwhForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement cstmt = null;
		int beginIndex = 20 * (theForm.getPgNum() - 1) + 1;
		int endIndex = 20 * theForm.getPgNum();
		try {
			StringBuffer sql1 = new StringBuffer();
			sql1.append("SELECT * FROM " + " ( "
					+ "   SELECT ORIGINAL.* , ROWNUM RN " + "   FROM (");

			String[] ydcs = theForm.getYdcs().split("\\$");
			StringBuffer sql = new StringBuffer();
			StringBuffer sql2 = new StringBuffer();
			sql2
					.append("select count(1) from sbdb.sb_jl_gzfk a where a.gzsx_id='"
							+ ydcs[0]
							+ "' and exists (select 1 from djdb.dj_jl_jbsj b where a.jsjdm=b.jsjdm");
			sql
					.append("select a.jsjdm,a.nsrmc,a.ydsj,a.fknr from sbdb.sb_jl_gzfk a where a.gzsx_id='"
							+ ydcs[0]
							+ "' and exists (select 1 from djdb.dj_jl_jbsj b where a.jsjdm=b.jsjdm");
			if (!"-".equals(ydcs[3].trim())) {
				sql.append(" and  b.nsrzt='" + ydcs[3] + "'");
				sql2.append(" and  b.nsrzt='" + ydcs[3] + "'");
			}
			if (!"-".equals(ydcs[4].trim())) {
				sql.append(" and  b.scjxdm='" + ydcs[4] + "'");
				sql2.append(" and  b.scjxdm='" + ydcs[4] + "'");
			}
			if (!"0".equals(ydcs[11].trim())) {
				sql.append("  and b.gjbzhydm='" + ydcs[11] + "'");
				sql2.append("  and b.gjbzhydm='" + ydcs[11] + "'");
			}
			if (!"0".equals(ydcs[10].trim())) {
				sql.append(" and b.djzclxdm='" + ydcs[10] + "'");
				sql2.append(" and b.djzclxdm='" + ydcs[10] + "'");
			}
			sql.append(" and b.swjgzzjgdm='" + ydcs[9] + "')");
			sql2.append(" and b.swjgzzjgdm='" + ydcs[9] + "')");
			System.out.println("doyydqdquery--------------" + sql);
			sql1.append(sql + ") ORIGINAL" + "   WHERE ROWNUM<= " + endIndex
					+ " ) " + "WHERE RN >=" + beginIndex);
			con = SfDBResource.getConnection();// 在申报模块中添加获取数据库连接的类
			if (theForm.getJlcount() == null) {
				//System.out.println("doyydqdquery--------------" + sql2);
				cstmt = con.prepareStatement(sql2.toString());
				rs = cstmt.executeQuery();
				while (rs.next()) {
					// 构造model的数据
					theForm.setJlcount(rs.getString(1));
					if (Integer.parseInt(rs.getString(1)) % 10 == 0) {
						theForm
								.setPgSum(Integer.parseInt(rs.getString(1)) / 20);
					} else {
						theForm
								.setPgSum(Integer.parseInt(rs.getString(1)) / 20 + 1);
					}
				}
				if (cstmt != null) {
					cstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			}
			nsrtogzsx model = null;
			List list = new ArrayList();
			//System.out.println("doyydqdquery--------------" + sql1);
			cstmt = con.prepareStatement(sql1.toString());
			rs = cstmt.executeQuery();

			while (rs.next()) {
				// 构造model的数据
				model = new nsrtogzsx();
				model.setJsjdm(rs.getString(1));
				model.setNsrmc(rs.getString(2));
				model.setYdsj(rs.getString(3));
				if (rs.getString(4) == null||"".equals(rs.getString(4).trim())) {
					model.setFknr("该纳税人只阅读，但未提供反馈内容！");
				} else {
					model.setFknr(rs.getString(4).replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r\n", "<br>"));
				}
				// model.setGzsxnrbt(theForm.getGzsxnrbt());
				list.add(model);
			}
			theForm.setTjgzsxlilst(list);
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

	/**
	 * 保存后台处理 通过未阅读的连接查看阅读的清单
	 */
	private Object dowydqdquery(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm theForm = (GzsxwhForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement cstmt = null;
		int beginIndex = 20 * (theForm.getPgNum() - 1) + 1;
		int endIndex = 20 * theForm.getPgNum();

		try {
			con = SfDBResource.getConnection();
			nsrtogzsx model = null;
			List list = new ArrayList();
			String[] ydcs = theForm.getYdcs().split("\\$");
			if (ydcs[12].equals("0") && !"*".equals(ydcs[13])) {
				String sql = "select a.jsjdm,a.nsrmc from sbdb.sb_jl_gzfk a where  a.gzsx_id='"
						+ ydcs[0] + "'";
				model = new nsrtogzsx();
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				if (rs.next()) {
					// 构造model的数据
					theForm.setJlcount("0");
				} else {
					theForm.setJlcount("1");
					model.setJsjdm(ydcs[13]);
					model.setNsrmc(ydcs[14]);
					list.add(model);
				}
				theForm.setTjgzsxlilst(list);
				return theForm;

				// 执行的是查找这个所里面的没在告知事项反馈表里面的数据

			} else {
				StringBuffer sql1 = new StringBuffer();
				sql1.append("SELECT * FROM " + " ( "
						+ "   SELECT ORIGINAL.* , ROWNUM RN " + "   FROM (");

				StringBuffer sql = new StringBuffer();
				StringBuffer sql2 = new StringBuffer();
				sql2
						.append("select count(1) from djdb.dj_jl_jbsj a where not exists (select b.jsjdm from sbdb.sb_jl_gzfk b where b.gzsx_id='"
								+ ydcs[0]
								+ "' and a.jsjdm=b.jsjdm) and a.swjgzzjgdm='"
								+ ydcs[9] + "'");
				sql
						.append("select a.jsjdm,a.nsrmc from djdb.dj_jl_jbsj a where not exists (select b.jsjdm from sbdb.sb_jl_gzfk b where b.gzsx_id='"
								+ ydcs[0]
								+ "' and a.jsjdm=b.jsjdm) and a.swjgzzjgdm='"
								+ ydcs[9] + "'");
				if (!"0".equals(ydcs[10].trim())) {
					sql.append(" and a.djzclxdm='" + ydcs[10] + "'");
					sql2.append(" and a.djzclxdm='" + ydcs[10] + "'");
				}
				if (!"0".equals(ydcs[11].trim())) {
					sql.append(" and a.gjbzhydm='" + ydcs[11] + "'");
					sql2.append(" and a.gjbzhydm='" + ydcs[11] + "'");
				}
				if (!"-".equals(ydcs[3].trim())) {
					sql.append(" and a.nsrzt='" + ydcs[3] + "'");
					sql2.append(" and a.nsrzt='" + ydcs[3] + "'");
				}
				if (!"-".equals(ydcs[4].trim())) {
					sql.append(" and a.scjxdm='" + ydcs[4] + "'");
					sql2.append(" and a.scjxdm='" + ydcs[4] + "'");
				}
				//System.out.println("dowyydqdqsqluery--------------" + sql);
				sql1.append(sql + ") ORIGINAL" + "   WHERE ROWNUM<= "
						+ endIndex + " ) " + "WHERE RN >=" + beginIndex);
				// 在申报模块中添加获取数据库连接的类
				//System.out.println("dowyydqdqsql2uery--------------" + sql2);
				if (theForm.getJlcount() == null) {
					cstmt = con.prepareStatement(sql2.toString());
					rs = cstmt.executeQuery();
					while (rs.next()) {
						// 构造model的数据
						theForm.setJlcount(rs.getString(1));
						if (Integer.parseInt(rs.getString(1)) % 10 == 0) {
							theForm
									.setPgSum(Integer.parseInt(rs.getString(1)) / 20);
						} else {
							theForm
									.setPgSum(Integer.parseInt(rs.getString(1)) / 20 + 1);
						}
					}
					if (cstmt != null) {
						cstmt.close();
					}
					if (rs != null) {
						rs.close();
					}
				}
				//System.out.println("dowyydqdqusq11ery--------------" + sql1);
				cstmt = con.prepareStatement(sql1.toString());
				rs = cstmt.executeQuery();
				while (rs.next()) {
					// 构造model的数据
					model = new nsrtogzsx();
					model.setJsjdm(rs.getString(1));
					model.setNsrmc(rs.getString(2));
					model.setGzsxnrbt(theForm.getGzsxnrbt());
					list.add(model);
				}
				theForm.setTjgzsxlilst(list);
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
	// /**
	// * 保存后台处理 查看告知事项详细内容
	// */
	// private Object dogzsxfknrquery(VOPackage vo) throws BaseException {
	// Connection conn = null;
	// GzsxwhForm theForm = (GzsxwhForm) vo.getData();
	// Connection con = null;
	// ResultSet rs = null;
	// String sql = "";
	// Statement cstmt = null;
	// // 靠搞这么复杂有啥用？
	// try {
	//
	// sql = "select a. from 税局反馈表 a where a.纳税人状态=' "
	// + theForm.getNsrzt()
	// + "',and a.街乡代码='"
	// + theForm.getJxdm()
	// + "',and a.jsjdm not in(select b.jsjsdm from 数据反馈表 b where b.gzsx_id=' "
	// + theForm.getGzsx_id() + "')";
	//
	// con = DBResource.getConnection();// 在申报模块中添加获取数据库连接的类
	// cstmt = con.createStatement();
	// rs = cstmt.executeQuery(sql);
	// nsrtogzsx model = null;
	// List list = new ArrayList();
	// while (rs.next()) {
	// // 构造model的数据
	// model = new nsrtogzsx();
	// model.setJsjsdm(rs.getString(1));
	// model.setNsrmc(rs.getString(2));
	// model.setGzsxnrbt(theForm.getGzsxnrbt());
	// list.add(model);
	// }
	// theForm.setTjgzsxlilst(list);
	// return theForm;
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// return null;
	// } finally {
	// try {
	// if (rs != null) {
	// rs.close();
	// }
	// } catch (Exception exx) {
	// }
	// try {
	// if (cstmt != null) {
	// cstmt.close();
	// }
	// } catch (Exception exx) {
	// }
	// try {
	// if (con != null) {
	// con.close();
	// }
	// } catch (Exception exx) {
	// }
	// }
	//
	// }
}
