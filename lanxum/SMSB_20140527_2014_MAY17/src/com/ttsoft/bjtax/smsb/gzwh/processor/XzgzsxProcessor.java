/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.gzwh.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsxpl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm;
import com.ttsoft.bjtax.smsb.model.client.nsrtogzsx;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: 北京地税核心征管系统－－上门申报
 * </p>
 * <p>
 * Description: 新增告知事项
 * </p>
 * 
 * @author 开发六组－－石岩峰
 * @version 1.1
 */

public class XzgzsxProcessor implements Processor {
	/**
	 * 页面元素列表数组
	 */
	private String names[] = { "jsjdm", "gzsxfcrq", "nsrmc", "gzsxksrq",
			"gzsxjsrq", "gzsxnr", "gzsxfcdw", "gzsxsdbz", "fzcbs", "bz", "lrr",
			"lrrdm", "swjgzzjgdm", "swjgzzjgdm2", "djzclxdm", "gjbzhydm",
			"cjrq", "lrrq", "qxdm", "ph", "gzsxnrbt", "gzsx_id" };

	/**
	 * 通用处理调度模块
	 * 
	 * @param vo
	 *            数据集对象（包括Form和UserData对象）
	 * @return 当前页面对应的Form对象
	 * @throws BaseException
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
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.SMSB_DELETEACTION:
			result = doDelete(vo);
			break;
		case CodeConstant.SMSB_UPDATEACTION:
			result = doUpdate(vo);
			break;
		default:
			throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
		}
		return result;
	}

	/**
	 * 页面初始化
	 * 
	 * @param vo
	 *            数据集对象（包括Form和UserData对象）
	 * @return 当前页面对应的Form对象
	 * @throws BaseException
	 */
	private Object doShow(VOPackage vo) throws BaseException {
		return null;
	}

	/**
	 * 查询
	 * 
	 * @param vo
	 *            数据集对象（包括Form和UserData对象）
	 * @return 当前页面对应的Form对象
	 * @throws BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException {
		return null;
	}

	/**
	 * 保存后台处理
	 * 
	 * @param vo
	 *            数据集对象（包括Form和UserData对象）
	 * @return 当前页面对应的Form对象
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm gf = (GzsxwhForm) vo.getData();
		try {
			Gzsx g = new Gzsx(); // 告知事项表ORMapping值对象
			UserData userData = vo.getUserData();
			g.setGzsxfcrq(new Timestamp(System.currentTimeMillis()));
			g.setNsrmc(gf.getMxNsrmc());
			g.setGzsxksrq(SfTimeUtil.getTimestamp((gf.getMxGzqsrq())));
			g.setGzsxjsrq(SfTimeUtil.getTimestamp((gf.getMxGzjzrq())));
			g.setGzsxnr(gf.getMxGzsxxxxx());

			g.setSwjgzzjgdm(userData.getSsdwdm()); // 所属单位代码
			g.setGzsxfcdw(userData.getSsdwmc());
			// g.setGzsxsdbz("");未送达
			g.setFzcbs(gf.getMxGzlx());
			g.setBz(""); // 备注
			g.setLrr(userData.getYhmc());
			g.setLrrdm(userData.getYhid());
			g.setCjrq(SfTimeUtil.getNowTimestamp());
			g.setLrrq(SfTimeUtil.getNowTimestamp());
			g.setQxdm(InterfaceDj.getQxdm(userData));
			// 群发相关域赋默认值
			g.setDjzclxdm("0");
			g.setGjbzhydm("0");
			g.setSwjgzzjgdm2("0");
			// 2009.4.8针对告知事项变更升级wcl修改
			String gzsxid = (String) getsequenceid();
                       if(gf.getMxChooseTypeRadioHidden().equals("1")||gf.getMxChooseTypeRadioHidden().equals("2")){
			if (gf.getMxGzsxxxxx().length() < 20) {
				g.setGzsxnrbt(gf.getMxGzsxxxxx());

			} else {
                                
				g.setGzsxnrbt(gf.getMxGzsxxxxx().substring(0, 20));

			}
  } else {
  if (gf.getMxGzsxxxxx().length() < 20) {
				g.setGzsxnrbt(new String(gf.getMxGzsxxxxx().getBytes("ISO8859_1"),"GBK"));

			} else {
                                g.setGzsxnrbt(new String(gf.getMxGzsxxxxx().getBytes("ISO8859_1"),"GBK").substring(0, 20));
				
}
}
			g.setGzsx_id(gzsxid);
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			// 插入数据库
			insertGzsx(g, da, gf, conn, userData);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return gf;

	}

	/**
	 * 获取sequence号
	 */
	private Object getsequenceid() throws BaseException {
		Connection conn = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		// 靠搞这么复杂有啥用？
		try {

			sql = "select SBDB.SEQ_SB_GZBH.nextval from dual";

			con = SfDBResource.getConnection();// 在申报模块中添加获取数据库连接的类
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			nsrtogzsx model = null;
			List list = new ArrayList();
			if(rs.next()) {
				return rs.getString(1);
			}else{
				throw new ApplicationException("告知事项编号为获取到，发布告知事项失败！请重试！");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("告知事项编号为获取到，发布告知事项失败！请重试！");
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
	 * 删除
	 * 
	 * @param vo
	 *            数据集对象（包括Form和UserData对象）
	 * @return 当前页面对应的Form对象
	 * @throws BaseException
	 */
	private Object doDelete(VOPackage vo) throws BaseException {
		return null;
	}

	/**
	 * 修改后台处理
	 * 
	 * @param vo
	 *            数据集对象（包括Form和UserData对象）
	 * @return 当前页面对应的Form对象
	 * @throws BaseException
	 */
	private Object doUpdate(VOPackage vo) throws BaseException {
		Connection conn = null;
		GzsxwhForm gf = (GzsxwhForm) vo.getData();
		try {

			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			Gzsx g = new Gzsx(); // 插库用
			String primaryKey = gf.getModifyIndex();
			String strJsjdm = primaryKey.substring(0, primaryKey.indexOf("|"));
			String strGzsxfcrq = primaryKey.substring(
					primaryKey.indexOf("|") + 1, primaryKey.length());
			// 去掉毫秒
			strGzsxfcrq = strGzsxfcrq.substring(0, strGzsxfcrq.length() - 2);

			Vector gzV = new Vector();
			gzV.add(" JSJDM = '" + strJsjdm + "' ");
			gzV.add(" GZSXFCRQ = to_date('" + strGzsxfcrq
					+ "','YYYY-mm-dd hh24:mi:ss') ");
			List sourceGzsxList = da.query(Gzsx.class, gzV);
			Gzsx sourceGzsx = (Gzsx) sourceGzsxList.get(0);
			BeanUtil.copyBeanToBean(names, sourceGzsx, g);

			g.setGzsxksrq(stringToTimestamp(gf.getMxGzqsrq()));
			g.setGzsxjsrq(stringToTimestamp(gf.getMxGzjzrq()));
			g.setFzcbs(gf.getMxGzlx());
			g.setGzsxnr(gf.getMxGzsxxxxx());
			// 记录更改人信息
			UserData userData = vo.getUserData();
			g.setSwjgzzjgdm(userData.getSsdwdm()); // 所属单位代码
			g.setGzsxfcdw(userData.getSsdwmc());
			g.setLrr(userData.getYhmc());
			g.setLrrdm(userData.getYhid());
			g.setLrrq(SfTimeUtil.getNowTimestamp());
			g.setQxdm(InterfaceDj.getQxdm(userData));

			// 更新数据
			da.delete(sourceGzsxList);
			insertGzsx(g, da, gf, conn, userData);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return gf;
	}

	/**
	 * 将日期字串(format:yyyyMMdd eg. 20030611)转化为Timestamp型日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @return Timestamp型日期
	 */
	private Timestamp stringToTimestamp(String strDate) {
		// int year = Integer.parseInt(strDate.substring(0, 4)) - 1900;
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6)) - 1;
		int date = Integer.parseInt(strDate.substring(6, 8));

		Calendar gCalendar = GregorianCalendar.getInstance();
		gCalendar.set(gCalendar.YEAR, year);
		gCalendar.set(gCalendar.MONTH, month);
		gCalendar.set(gCalendar.DATE, date);
		gCalendar.set(gCalendar.HOUR_OF_DAY, 0);
		gCalendar.set(gCalendar.MINUTE, 0);
		gCalendar.set(gCalendar.SECOND, 0);
		gCalendar.set(gCalendar.MILLISECOND, 0);
		Timestamp t = new Timestamp(gCalendar.getTime().getTime());
		return t;
	}

	/**
	 * 插入告知事项处理
	 * 
	 * @param g
	 *            告知时象表对象
	 * @param da
	 *            数据库对象
	 * @param gf
	 *            当前页面的Form对象
	 * @param conn
	 *            数据库连接
	 * @param userData
	 *            用户信息对象
	 * @throws BaseException
	 */
//	private void insertGzsx(Gzsx g, SfDBAccess da, GzsxwhForm gf,
//			Connection conn, UserData userData) throws BaseException {
//
//		// ------------------------------
//
//		// try {
//		// Statement sttest=conn.createStatement();
//		// String sql="insert into sbdb.sb_jl_gzsx()
//		// values('*',to_date('20090101','yyyymmdd'),'yy',to_date('20090101','yyyymmdd'),"
//		// +
//		// "to_date('20090101','yyyymmdd'),'问题描述问题描述告知事项的发送最大是690字汉字。在页面上控制的是2000个汉字以内。但是大于690汉字的告知事项是发送不了的。告知发送时会弹出告知内容字数的统计，请您参照提示字数。4月29日，谷歌全球副总裁刘允对未来的互联网时代进行了预测。他认为，与今天相比，2018年的互联网信息时代，人们的生活方式会发生翻天覆地的改变。云计算和开放互助的信息世界不仅把人们更紧密地联系在一起，也会开创出前所未有的商业价值和商业机会。演讲中，刘允总结了十年来，互联网给人们生活带来的改变。这些改变表现在信息来源、沟通渠道、娱乐方式、和商业活动等各个层面。刘允指出，今天全球每天发生超过20亿次的搜索、有将近1200亿封的电子邮件和即时通讯在全球传递。同时，全球超过85%的互联网用户在网上进行购物。就在不久前，有3300多万人共同在线收看了2009年春节联欢晚会。这一系列的数字都表明，过去的十年，人们的生活已经被互联网彻底颠覆。在谈到2018的互联网信息时代时，刘允描绘了一个开放互动的世界：十年之后，信息就像是智能雨，你可以决定它在什么地方下、下多长时间、下多少量。智能的内容过滤系统可以帮你摆脱无效信息的轰炸。同时，全球有将近7000种语言，十年后可以实现无缝式的直译，无论是语音还是文字，无论是源头是哪种的语言，接收方都能以自己熟悉的语言去接收和认知，这是未来最大的沟通方式的改变。问题描述告知事项的发送最大是690字汉字。在页面上控制的是2000个汉字以内。但是大于690汉字的告知事项是发送不了的。告知发送时会弹出告知内容字数的统计，请您参照提示字数。4月29日，谷歌全球副总裁刘允对未来的互联网时代进行了预测。他认为，与今天相比，2018年的互联网信息时代，人们的生活方式会发生翻天覆地的改变。云计算和开放互助的信息世界不仅把人们更紧密地联系在一起，也会开创出前所未有的商业价值和商业机会。演讲中，刘允总结了十年来，互联网给人们生活带来的改变。这些改变表现在信息来源、沟通渠道、娱乐方式、和商业活动等各个层面。刘允指出，今天全球每天发生超过20亿次的搜索、有将近1200亿封的电子邮件和即时通讯在全球传递。同时，全球超过85%的互联网用户在网上进行购物。就在不久前，有3300多万人共同在线收看了2009年春节联欢晚会。这一系列的数字都表明，过去的十年，人们的生活已经被互联网彻底颠覆。在谈到2018的互联网信息时代时，刘允描绘了一个开放互动的世界：十年之后，信息就像是智能雨，你可以决定它在什么地方下、下多长时间、下多少量。智能的内容过滤系统可以帮你摆脱无效信息的轰炸。同时，全球有将近7000种语言，十年后可以实现无缝式的直译，无论是语音还是文字，无论是源头是哪种的语言，接收方都能以自己熟悉的语言去接收和认知，这是未来最大的沟通方式的改变。告知事项的发送最大是690字汉字。在页面上控制的是2000个汉字以内。但是大于690汉字的告知事项是发送不了的。告知发送时会弹出告知内容字数的统计，请您参照提示字数。4月29日，谷歌全球副总裁刘允对未来的互联网时代进行了预测。他认为，与今天相比，2018年的互联网信息时代，人们的生活方式会发生翻天覆地的改变。云计算和开放互助的信息世界不仅把人们更紧密地联系在一起，也会开创出前所未有的商业价值和商业机会。
//		// 演讲中，刘允总结了十年来，互联网给人们生活带来的改变。这些改变表现在信息来源、沟通渠道、娱乐方式、和商业活动等各个层面。刘允指出，今天全球每天发生超过20亿次的搜索、有将近1200亿封的电子邮件和即时通讯在全球传递。同时，全球超过85%的互联网用户在网上进行购物。就在不久前，有3300多万人共同在线收看了2009年春节联欢晚会。这一系列的数字都表明，过去的十年，人们的生活已经被互联网彻底颠覆。在谈到2018的互联网信息时代时，刘允描绘了一个开放互动的世界：十年之后，信息就像是智能雨，你可以决定它在什么地方下、下多长时间、下多少量。智能的内容过滤系统可以帮你摆脱无效信息的轰炸。同时，全球有将近7000种语言，十年后可以实现无缝式的直译，无论是语音还是文字，无论是源头是哪种的语言，接收方都能以自己熟悉的语言去接收和认知，这是未来最大的沟通方式的改变。在这样一个奇妙的世界里，商业活动将全部依赖网络来实现。刘允表示：手机凭借实时、随时、随地、随身的特性，会成为最重要的个人终端，共同接入云计算的平台。商业活动会变得越来越容易和精准，把手机指向货品的时候，手机可以告诉你它的全部信息，你可以即时地在你的手机里进行搜索，并和其它商店里的商品进行比较，最后，你可以用手机完成整个的商业过程。','','','1','','','','1','1','1','1',to_date('20090101','yyyymmdd'),to_date('20090101','yyyymmdd'),'1','','','')";
//		// sttest.execute(sql);
//		// sttest.ex
//		// } catch (SQLException e1) {
//		// // TODO Auto-generated catch block
//		// e1.printStackTrace();
//		// }
//		// -------------------------------
//
//		// 并发访问控制
//		parallelControl(g, da, conn);
//
//		if (gf.getMxChooseTypeRadioHidden().equals("1")) {
//			// 告知所有纳税人
//			// 2009.4.1增加306行，数据库做了修改了
//			if ((gf.getMxJsjdm().trim()).equals("*")) {
//				g.setJsjdm("*");
//				g.setNsrmc("全部纳税人");
//				// 插入数据
//				da.insert(g);
//				// 告知一组纳税人。
//			} else if (gf.getMxJsjdm().indexOf(",") > 0) {
//				StringTokenizer st = new StringTokenizer(
//						gf.getMxJsjdm().trim(), ",");
//				String tmpJsjdm = null;
//				List insertArrayJsjdmList = new ArrayList();
//				try {
//					while (st.hasMoreTokens()) {
//						tmpJsjdm = st.nextToken();
//
//						com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();
//						HashMap ghdwMap = djProxy.getDjInfo(tmpJsjdm, userData);
//						SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
//						Gzsx tempG = new Gzsx();
//						BeanUtil.copyBeanToBean(names, g, tempG);
//						tempG.setJsjdm(tmpJsjdm);
//						tempG.setNsrmc(swdjjbsj.getNsrmc());
//						insertArrayJsjdmList.add(tempG);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					// 系统捕捉异常，根据异常类型抛出
//					throw new ApplicationException("计算机代码" + tmpJsjdm
//							+ "不存在或您没有足够的权限处理此计算机代码");
//				}
//				da.insert(insertArrayJsjdmList);
//				// 告知特定纳税人
//			} else {
//				g.setJsjdm(gf.getMxJsjdm());
//				// 插入数据
//				da.insert(g);
//			}
//		} else if (gf.getMxChooseTypeRadioHidden().equals("2")) {
//			// -------------------------------
//			g.setJsjdm("*");
//			// 群发条件加入
//			if (gf.getMxJhfs().equals("0")) { // 结合方式:"与"
//				if (gf.getMxQylx().equals("0") && gf.getMxHylb().equals("0")
//						&& gf.getMxDqjs().equals("0")) {
//					// 插入数据
//					g.setNsrmc("全部纳税人");
//					da.insert(g);
//				} else {
//					// 针对交集人群
//					// 赋纳税人名称
//					g.setNsrmc(getNsrmcCollection(gf, "与"));
//					g.setDjzclxdm(gf.getMxQylx());
//					g.setGjbzhydm(gf.getMxHylb());
//					g.setSwjgzzjgdm2(gf.getMxDqjs());
//					// 插入数据
//					da.insert(g);
//
//				}
//			} else if (gf.getMxJhfs().equals("1")) { // 结合方式:"或"
//				if (gf.getMxQylx().equals("0") && gf.getMxHylb().equals("0")
//						&& gf.getMxDqjs().equals("0")) {
//					// 插入数据
//					g.setNsrmc("全部纳税人");
//					da.insert(g);
//				} else {
//					Gzsx tempG = new Gzsx();
//					String tempSql = new String();
//					// 赋纳税人名称
//					g.setNsrmc(getNsrmcCollection(gf, "或"));
//					// 企业类型输入
//					if (!gf.getMxQylx().equals("0")) {
//						g.setDjzclxdm(gf.getMxQylx());
//						g.setGjbzhydm("0");
//						g.setSwjgzzjgdm2("0");
//						da.insert(g);
//					}
//					// 行业类别输入
//					if (!gf.getMxHylb().equals("0")) {
//						g.setDjzclxdm("0");
//						g.setGjbzhydm(gf.getMxHylb());
//						g.setSwjgzzjgdm2("0");
//						da.insert(g);
//					}
//					// 地区局所输入
//					if (!gf.getMxDqjs().equals("0")) {
//						g.setDjzclxdm("0");
//						g.setGjbzhydm("0");
//						g.setSwjgzzjgdm2(gf.getMxDqjs());
//						da.insert(g);
//					}
//				}
//			}
//
//		} else if (gf.getMxChooseTypeRadioHidden().equals("3")) {
//			// 计算机代码导入方式
//
//			// 插入批量临时表
//			String ph = this.insertGzsxpl(gf.getJsjdmList(), da, g
//					.getSwjgzzjgdm());
//			// 得到gzsx表列表
//			// List list = this.getGzsxList(da, ph, g);
//			// 2009.4.2wcl修改将取得列表的方法修改。如下
//			List list = this.getGzsxList(da, ph, g);
//			// 插入告知事项
//			da.insert(list);
//		}
//
//	}
	private void insertGzsx(Gzsx g, SfDBAccess da, GzsxwhForm gf,
			Connection conn, UserData userData) throws BaseException {

		// ------------------------------

		// try {
		// Statement sttest=conn.createStatement();
		// String sql="insert into sbdb.sb_jl_gzsx()
		// values('*',to_date('20090101','yyyymmdd'),'yy',to_date('20090101','yyyymmdd'),"
		// +
		// "to_date('20090101','yyyymmdd'),'问题描述问题描述告知事项的发送最大是690字汉字。在页面上控制的是2000个汉字以内。但是大于690汉字的告知事项是发送不了的。告知发送时会弹出告知内容字数的统计，请您参照提示字数。4月29日，谷歌全球副总裁刘允对未来的互联网时代进行了预测。他认为，与今天相比，2018年的互联网信息时代，人们的生活方式会发生翻天覆地的改变。云计算和开放互助的信息世界不仅把人们更紧密地联系在一起，也会开创出前所未有的商业价值和商业机会。演讲中，刘允总结了十年来，互联网给人们生活带来的改变。这些改变表现在信息来源、沟通渠道、娱乐方式、和商业活动等各个层面。刘允指出，今天全球每天发生超过20亿次的搜索、有将近1200亿封的电子邮件和即时通讯在全球传递。同时，全球超过85%的互联网用户在网上进行购物。就在不久前，有3300多万人共同在线收看了2009年春节联欢晚会。这一系列的数字都表明，过去的十年，人们的生活已经被互联网彻底颠覆。在谈到2018的互联网信息时代时，刘允描绘了一个开放互动的世界：十年之后，信息就像是智能雨，你可以决定它在什么地方下、下多长时间、下多少量。智能的内容过滤系统可以帮你摆脱无效信息的轰炸。同时，全球有将近7000种语言，十年后可以实现无缝式的直译，无论是语音还是文字，无论是源头是哪种的语言，接收方都能以自己熟悉的语言去接收和认知，这是未来最大的沟通方式的改变。问题描述告知事项的发送最大是690字汉字。在页面上控制的是2000个汉字以内。但是大于690汉字的告知事项是发送不了的。告知发送时会弹出告知内容字数的统计，请您参照提示字数。4月29日，谷歌全球副总裁刘允对未来的互联网时代进行了预测。他认为，与今天相比，2018年的互联网信息时代，人们的生活方式会发生翻天覆地的改变。云计算和开放互助的信息世界不仅把人们更紧密地联系在一起，也会开创出前所未有的商业价值和商业机会。演讲中，刘允总结了十年来，互联网给人们生活带来的改变。这些改变表现在信息来源、沟通渠道、娱乐方式、和商业活动等各个层面。刘允指出，今天全球每天发生超过20亿次的搜索、有将近1200亿封的电子邮件和即时通讯在全球传递。同时，全球超过85%的互联网用户在网上进行购物。就在不久前，有3300多万人共同在线收看了2009年春节联欢晚会。这一系列的数字都表明，过去的十年，人们的生活已经被互联网彻底颠覆。在谈到2018的互联网信息时代时，刘允描绘了一个开放互动的世界：十年之后，信息就像是智能雨，你可以决定它在什么地方下、下多长时间、下多少量。智能的内容过滤系统可以帮你摆脱无效信息的轰炸。同时，全球有将近7000种语言，十年后可以实现无缝式的直译，无论是语音还是文字，无论是源头是哪种的语言，接收方都能以自己熟悉的语言去接收和认知，这是未来最大的沟通方式的改变。告知事项的发送最大是690字汉字。在页面上控制的是2000个汉字以内。但是大于690汉字的告知事项是发送不了的。告知发送时会弹出告知内容字数的统计，请您参照提示字数。4月29日，谷歌全球副总裁刘允对未来的互联网时代进行了预测。他认为，与今天相比，2018年的互联网信息时代，人们的生活方式会发生翻天覆地的改变。云计算和开放互助的信息世界不仅把人们更紧密地联系在一起，也会开创出前所未有的商业价值和商业机会。
		// 演讲中，刘允总结了十年来，互联网给人们生活带来的改变。这些改变表现在信息来源、沟通渠道、娱乐方式、和商业活动等各个层面。刘允指出，今天全球每天发生超过20亿次的搜索、有将近1200亿封的电子邮件和即时通讯在全球传递。同时，全球超过85%的互联网用户在网上进行购物。就在不久前，有3300多万人共同在线收看了2009年春节联欢晚会。这一系列的数字都表明，过去的十年，人们的生活已经被互联网彻底颠覆。在谈到2018的互联网信息时代时，刘允描绘了一个开放互动的世界：十年之后，信息就像是智能雨，你可以决定它在什么地方下、下多长时间、下多少量。智能的内容过滤系统可以帮你摆脱无效信息的轰炸。同时，全球有将近7000种语言，十年后可以实现无缝式的直译，无论是语音还是文字，无论是源头是哪种的语言，接收方都能以自己熟悉的语言去接收和认知，这是未来最大的沟通方式的改变。在这样一个奇妙的世界里，商业活动将全部依赖网络来实现。刘允表示：手机凭借实时、随时、随地、随身的特性，会成为最重要的个人终端，共同接入云计算的平台。商业活动会变得越来越容易和精准，把手机指向货品的时候，手机可以告诉你它的全部信息，你可以即时地在你的手机里进行搜索，并和其它商店里的商品进行比较，最后，你可以用手机完成整个的商业过程。','','','1','','','','1','1','1','1',to_date('20090101','yyyymmdd'),to_date('20090101','yyyymmdd'),'1','','','')";
		// sttest.execute(sql);
		// sttest.ex
		// } catch (SQLException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// -------------------------------

		// 并发访问控制
		
		parallelControl(g, da, conn);
         //第一种发送方式
		if (gf.getMxChooseTypeRadioHidden().equals("1")) {
			// 告知所有纳税人
			// 2009.4.1增加306行，数据库做了修改了
			if ((gf.getMxJsjdm().trim()).equals("*")) {
				List list=new ArrayList();
				g.setJsjdm("*");
				g.setNsrmc("全部纳税人");
				// 插入数据
				list.add(g);
				insert(conn,list);//直接就插入了
				// 告知一组纳税人。
			} else if (gf.getMxJsjdm().indexOf(",") > 0) {
				StringTokenizer st = new StringTokenizer(
						gf.getMxJsjdm().trim(), ",");
				String tmpJsjdm = null;
				List insertArrayJsjdmList = new ArrayList();
				try {
					while (st.hasMoreTokens()) {
						tmpJsjdm = st.nextToken();

						com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();
						HashMap ghdwMap = djProxy.getDjInfo(tmpJsjdm, userData);
						SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
						Gzsx tempG = new Gzsx();
						BeanUtil.copyBeanToBean(names, g, tempG);
						tempG.setJsjdm(tmpJsjdm);
						tempG.setNsrmc(swdjjbsj.getNsrmc());
						insertArrayJsjdmList.add(tempG);
					}
				} catch (Exception e) {
					e.printStackTrace();
					// 系统捕捉异常，根据异常类型抛出
					throw new ApplicationException("计算机代码" + tmpJsjdm
							+ "不存在或您没有足够的权限处理此计算机代码");
				}
				insert(conn,insertArrayJsjdmList);
				// 告知特定纳税人
			} else {
				List list=new ArrayList();
				g.setJsjdm(gf.getMxJsjdm());
				// 插入数据
				list.add(g);
				insert(conn,list);
			}
		} 
		//第二种发送方式
		
		else if (gf.getMxChooseTypeRadioHidden().equals("2")) {
			// -------------------------------
			g.setJsjdm("*");
			// 群发条件加入
			if (gf.getMxJhfs().equals("0")) { // 结合方式:"与"
				if (gf.getMxQylx().equals("0") && gf.getMxHylb().equals("0")
						&& gf.getMxDqjs().equals("0")) {
					// 插入数据
					g.setNsrmc("全部纳税人");
					List list=new ArrayList();
					list.add(g);
					insert(conn,list);
				} else {
					// 针对交集人群
					// 赋纳税人名称
					g.setNsrmc(getNsrmcCollection(gf, "与"));
					g.setDjzclxdm(gf.getMxQylx());
					g.setGjbzhydm(gf.getMxHylb());
					g.setSwjgzzjgdm2(gf.getMxDqjs());
					// 插入数据
					List list=new ArrayList();
					list.add(g);
					insert(conn,list);

				}
			} else if (gf.getMxJhfs().equals("1")) { // 结合方式:"或"
				if (gf.getMxQylx().equals("0") && gf.getMxHylb().equals("0")
						&& gf.getMxDqjs().equals("0")) {
					// 插入数据
					g.setNsrmc("全部纳税人");
					List list=new ArrayList();
					list.add(g);
					insert(conn,list);
					//da.insert(g);
				} else {
					Gzsx tempG = new Gzsx();
					String tempSql = new String();
					// 赋纳税人名称
					g.setNsrmc(getNsrmcCollection(gf, "或"));
					// 企业类型输入
					if (!gf.getMxQylx().equals("0")) {
						g.setDjzclxdm(gf.getMxQylx());
						g.setGjbzhydm("0");
						g.setSwjgzzjgdm2("0");
						List list=new ArrayList();
						list.add(g);
						insert(conn,list);
						//da.insert(g);
					}
					// 行业类别输入
					if (!gf.getMxHylb().equals("0")) {
						g.setDjzclxdm("0");
						g.setGjbzhydm(gf.getMxHylb());
						g.setSwjgzzjgdm2("0");
						List list=new ArrayList();
						list.add(g);
						insert(conn,list);
						//da.insert(g);
					}
					// 地区局所输入
					if (!gf.getMxDqjs().equals("0")) {
						g.setDjzclxdm("0");
						g.setGjbzhydm("0");
						g.setSwjgzzjgdm2(gf.getMxDqjs());
						List list=new ArrayList();
						list.add(g);
						insert(conn,list);
						//da.insert(g);
					}
				}
			}

		} 
		
		//第三种发送方式
		else if (gf.getMxChooseTypeRadioHidden().equals("3")) {
			// 计算机代码导入方式

			// 插入批量临时表
			String ph = this.insertGzsxpl(gf.getJsjdmList(), da, g
					.getSwjgzzjgdm());
			// 得到gzsx表列表
			// List list = this.getGzsxList(da, ph, g);
			// 2009.4.2wcl修改将取得列表的方法修改。如下
			List list = this.getGzsxList(da, ph, g);
			// 插入告知事项
			//List list1=new ArrayList();
			//list.add(g);
			insert(conn,list);
			//da.insert(list);
		}

	}

	private void insert(Connection conn, List list) throws BaseException{
		Statement sttest=null;
		try {
			for(int i=0;i<list.size();i++){
				Gzsx g=(Gzsx)list.get(i);
				sttest = conn.createStatement();
				
				/*发出日期*/
				String fcrq=TinyTools.Date2String(new Date(g.getGzsxfcrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*创建日期*/
				String cjrq=TinyTools.Date2String(new Date(g.getCjrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*录入日期*/
				String lrrq=TinyTools.Date2String(new Date(g.getLrrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*开始日期*/
				String ksrq=TinyTools.Date2String(new Date(g.getGzsxksrq().getTime()));
				/*结束日期*/
				String jsrq=TinyTools.Date2String(new Date(g.getGzsxjsrq().getTime()));
				
				String sql = "insert into sbdb.sb_jl_gzsx(JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,DJZCLXDM,GJBZHYDM,SWJGZZJGDM2,CJRQ,LRRQ,QXDM,PH,GZSX_ID,GZSXNRBT) " +
				"values('"+g.getJsjdm()+"',to_date('"+fcrq+"','yyyy-MM-dd HH24:mi:ss'),'"+g.getNsrmc()+"',"+
				"to_date('"+ksrq+"','yyyy-MM-dd')"+","+
				"to_date('"+jsrq+"','yyyy-MM-dd')"+",'"+
				g.getGzsxnr()+"','"+
				g.getGzsxfcdw()+"','"+
				(g.getGzsxsdbz()==null?"":g.getGzsxsdbz())+"','"+
				g.getFzcbs()+"','"+
				g.getBz()+"','"+
				g.getLrr()+"','"+
				g.getLrrdm()+"','"+
				g.getSwjgzzjgdm()+"','"+
				g.getDjzclxdm()+"','"+
				g.getGjbzhydm()+"','"+
				g.getSwjgzzjgdm2()+"',to_date('"+cjrq+"','yyyy-MM-dd HH24:mi:ss'),to_date('"+lrrq+"','yyyy-MM-dd HH24:mi:ss')"+
				",'"+
				g.getQxdm()+"','"+
				(g.getPh()==null?"":g.getPh())+"','"+
				g.getGzsx_id()+"','"+
				g.getGzsxnrbt()+"')";
				//System.out.println("---sql"+sql);
				sttest.execute(sql);
                                if(sttest!=null){
				try {
					sttest.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			}
			

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new ApplicationException("发布告知事项失败，数据库操作失败\n详细信息："+e1.getMessage());
		}finally{
			if(sttest!=null){
				try {
					sttest.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 赋群发的对象信息
	 * 
	 * @param gf
	 *            当前From对象
	 * @param split
	 *            分隔符
	 * @return String
	 */
	private String getNsrmcCollection(GzsxwhForm gf, String split) {
		StringBuffer sb = new StringBuffer();

		if (!gf.getMxQylx().equals("0")) {
			List list = CodeManager.getCodeList("ZQWH_DJZCLX",
					CodeConstants.CODE_MAP_BEANLIST).getRecords();
			for (int i = 0; i < list.size(); i++) {
				LabelValueBean lvBean = (LabelValueBean) list.get(i);
				if (gf.getMxQylx().equals(lvBean.getValue())) {
					sb.append(lvBean.getLabel() + " " + split + " ");
					break;
				}
			}
		}
		// 行业类别输入
		if (!gf.getMxHylb().equals("0")) {
			List list = CodeManager.getCodeList("ZBZL_GJBZHY",
					CodeConstants.CODE_MAP_BEANLIST).getRecords();
			for (int i = 0; i < list.size(); i++) {
				LabelValueBean lvBean = (LabelValueBean) list.get(i);
				if (gf.getMxHylb().equals(lvBean.getValue())) {
					sb.append(lvBean.getLabel() + " " + split + " ");
					break;
				}
			}
		}
		// 地区局所输入
		if (!gf.getMxDqjs().equals("0")) {
			List list = CodeManager.getCodeList("GZWH_SWJGZZJG",
					CodeConstants.CODE_MAP_BEANLIST).getRecords();
			for (int i = 0; i < list.size(); i++) {
				LabelValueBean lvBean = (LabelValueBean) list.get(i);
				if (gf.getMxDqjs().equals(lvBean.getValue())) {
					sb.append(lvBean.getLabel() + " " + split + " ");
					break;
				}
			}
		}
		String returnString = sb.toString();
		returnString = returnString.substring(0, returnString.length() - 3);
		return returnString;
	}

	/**
	 * 并发访问控制
	 * 
	 * @param g
	 *            告知事项表对象
	 * @param da
	 *            数据访问对象
	 * @param conn
	 *            数据库连接对象
	 * @throws BaseException
	 */
	private void parallelControl(Gzsx g, SfDBAccess da, Connection conn)
			throws BaseException {
		SfDBUtils sfDB = new SfDBUtils(conn);
		try {
			sfDB.executeSql("LOCK TABLE SBDB.SB_JL_GZSX IN EXCLUSIVE MODE ");
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			throw new ApplicationException("系统正忙，请稍后再试！");
		}
		try {
			for (int i = 0; i < 20; i++) {
				Vector gzV = new Vector();
				String strGzsxfcrq = g.getGzsxfcrq().toString();
				strGzsxfcrq = strGzsxfcrq
						.substring(0, strGzsxfcrq.indexOf("."));
				gzV.add(" GZSXFCRQ = to_date('" + strGzsxfcrq
						+ "','YYYY-mm-dd hh24:mi:ss') ");
				List list = da.query(Gzsx.class, gzV);
				if (list.size() == 0) {
					return;
				} else {
					Timestamp t = g.getGzsxfcrq();
					g.setGzsxfcrq(new Timestamp(t.getTime() + 1000));
				}
			}
			throw new ApplicationException("系统正忙，请稍后再试！");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 将列表中的计算机代码插入到批量表中 然后使用该批量表和登记基本信息表比较 判断是否有不合法的计算机代码
	 * 
	 * @param list
	 *            待插入的数据list
	 * @param da
	 *            数据库访问对象
	 * @param swjgzzjgdm
	 *            税务机关组织机构代码
	 * @return 批号
	 * @throws BaseException
	 */
	private String insertGzsxpl(List list, SfDBAccess da, String swjgzzjgdm)
			throws BaseException {
		List gzsxpl = new ArrayList();
		String ph = this.getPh(swjgzzjgdm);
		for (int i = 0; i < list.size(); i++) {
			// 生成包含Gzsxpl的list
			Gzsxpl temp = new Gzsxpl();
			temp.setPh(ph);
			temp.setJsjdm((String) list.get(i));
			gzsxpl.add(temp);
		}
		// 插入数据
		try {
			da.insert(gzsxpl);
		} catch (BaseException ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		return ph;

	}

	/**
	 * 得到临时表中的批号
	 * 
	 * @param swjgzzjgdm
	 *            税务机关组织机构代码
	 * @return 临时表中的批号
	 */
	private String getPh(String swjgzzjgdm) {
		return swjgzzjgdm + TinyTools.Date2String(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 根据临时表内容生成告知事项列表
	 * 
	 * @param da
	 *            数据库访问对象
	 * @param ph
	 *            临时表中的批号
	 * @param g
	 *            告知事项表对象
	 * @return 待插入的告知事项list
	 */
	// private List getGzsxList (SfDBAccess da, String ph, Gzsx g)
	// {
	// List list = new ArrayList();
	// try
	// {
	// //联合登记基本信息表得到纳税人基本信息
	// ResultSet ret = da.querySQL("select a.jsjdm,b.nsrmc,b.DJZCLXDM," +
	// " b.GJBZHYDM,b.SWJGZZJGDM " +
	// " from sbdb.SB_JL_GZSXPL a, djdb.dj_jl_jbsj b " +
	// " where a.jsjdm=b.jsjdm");
	// while (ret.next())
	// {
	// Gzsx temp = new Gzsx();
	// BeanUtil.copyBeanToBean(names, g, temp);
	//
	// temp.setGzsxnr(new String(g.getGzsxnr().getBytes("ISO8859_1"),
	// "GBK"));
	// temp.setJsjdm(ret.getString("jsjdm"));
	// temp.setNsrmc(ret.getString("nsrmc"));
	// temp.setDjzclxdm(ret.getString("djzclxdm"));
	// temp.setGjbzhydm(ret.getString("gjbzhydm"));
	// temp.setSwjgzzjgdm(ret.getString("swjgzzjgdm"));
	// temp.setQxdm(temp.getSwjgzzjgdm().substring(0, 2));
	// temp.setPh(ph);
	// list.add(temp);
	// }
	// //删除临时表纪录
	// da.querySQL("delete from sbdb.sb_jl_gzsxpl where ph='" + ph + "'");
	// return list;
	// }
	// catch (Exception ex)
	// {
	// return null;
	// }
	// }
	/**
	 * 根据临时表内容生成告知事项列表
	 * 
	 * @param da
	 *            数据库访问对象
	 * @param ph
	 *            临时表中的批号
	 * @param g
	 *            告知事项表对象
	 * @return 待插入的告知事项list wcl2009.4.2修改将原来的方法屏蔽
	 */
	private List getGzsxList(SfDBAccess da, String ph, Gzsx g) {
		List list = new ArrayList();
		try {
			// 联合登记基本信息表得到纳税人基本信息
			ResultSet ret = da.querySQL("select a.jsjdm,b.nsrmc,b.DJZCLXDM,"
					+ " b.GJBZHYDM,b.SWJGZZJGDM "
					+ " from sbdb.SB_JL_GZSXPL a, djdb.dj_jl_jbsj b "
					+ " where a.jsjdm=b.jsjdm");
			while (ret.next()) {
				Gzsx temp = new Gzsx();
				BeanUtil.copyBeanToBean(names, g, temp);

				temp.setGzsxnr(new String(g.getGzsxnr().getBytes("ISO8859_1"),
						"GBK"));
				temp.setJsjdm(ret.getString("jsjdm"));
				temp.setNsrmc(ret.getString("nsrmc"));
				temp.setDjzclxdm(ret.getString("djzclxdm"));
				temp.setGjbzhydm(ret.getString("gjbzhydm"));
				temp.setSwjgzzjgdm(ret.getString("swjgzzjgdm"));
				temp.setQxdm(temp.getSwjgzzjgdm().substring(0, 2));
				temp.setPh(ph);
				list.add(temp);
			}
			// 删除临时表纪录
			da.querySQL("delete from sbdb.sb_jl_gzsxpl where ph='" + ph + "'");
			return list;
		} catch (Exception ex) {
			return null;
		}
	}
}
