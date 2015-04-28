/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jbxxb.processor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.Jbxx;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jbxxb.web.JbxxbForm2009;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.FriendHelper;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税年报
 * </p>
 * 
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JbxxbProcessor2009 implements Processor {
	// /**
	// * 企业所得税税率
	// */
	// private final String QYSDS_SL = "0.33";
	//
	// /**
	// * 企业所得税税种
	// */
	// private final String QYSDS_SZ = "30";

	/**
	 * 实现Processor接口
	 * 
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
	 *             其他异常抛出由EJB的process方法处理。
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
		// case CodeConstant.SMSB_DELETEACTION:
		// result = doDelete(vo);
		// break;
		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
	}

	/**
	 * doShow初始化对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doShow(VOPackage vo) throws BaseException {

		JbxxbForm2009 jbxxbForm = (JbxxbForm2009) vo.getData();

		// 初始化FORM：设置申报日期、申报期间
		initForm(jbxxbForm);

		UserData ud = (UserData) vo.getUserData();

		jbxxbForm.setCjr(ud.yhid);
		jbxxbForm.setLrr(ud.yhid);

		return jbxxbForm;
	}

	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 * 
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		JbxxbForm2009 jbxxbForm = (JbxxbForm2009) vo.getData();
		jbxxbForm.setNsrmc(""); // 纳税人名称
		jbxxbForm.setLxdh(""); // 注册地址联系电话

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = SfDBResource.getConnection();

			UserData ud = (UserData) vo.getUserData();
			SWDJJBSJ djsj = null;
			try {
				// 获得企业登记基本信息

				djsj = InterfaceDj.getJBSJ_New(jbxxbForm.getJsjdm(), ud);

			} catch (Exception ex1) {
				throw ExceptionUtil.getBaseException(ex1);
			}
			
			jbxxbForm.setNsrsbh(djsj.getSwdjzh()); // 纳税人识别号
			jbxxbForm.setNsrmc(djsj.getNsrmc()); // 纳税人名称
			//根据企业登记基本信息获取税源标识
			//jbxxbForm.setSybs(FriendHelper.getNsrSybs(djsj));
			
			
			/**
			 * 页面的为申报年度，需要转换为税款年度，为申报年度的前一年
			 * 
			 * @todo 页面的为税款年度
			 */
			// Map dateMap = new HashMap();
			// dateMap = getSbrl(jbxxbForm.getSbnd() + "0601");
			// 获得当前数据库时间
			Timestamp ret = TinyTools.getDBTimestamp(conn);
			// 获得当前数据库时间的年度
			String sbnd = String.valueOf(TinyTools.getYear(ret));
			System.out.println("数据库时间的申报年度：" + sbnd);
			jbxxbForm.setSbnd(sbnd);

			// jbxxbForm.setSknd(dateMap.get("ksrq")
			// .toString().substring(0, 4));// 年度，页面录入年度的前一年

			jbxxbForm.setSsjjlxdm(djsj.getDjzclxdm()); // 所属经济类型-登记注册类型代码
			jbxxbForm.setSsjjlxmc(djsj.getDjzclxmc());// 所属经济类型-登记注册类型名称
			jbxxbForm.setLxdh(djsj.getZcdzlxdh()); // 注册地址联系电话
			jbxxbForm.setJydz(djsj.getJydz());// 经营地址
			jbxxbForm.setSshydm(djsj.getGjbzhydm());// 所属行业代码
			jbxxbForm.setSshymc(djsj.getGjbzhymc());// 所属行业代码

			jbxxbForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); // 税务机关组织机构代码
			jbxxbForm.setSwjgzzjgmc(djsj.getSwjgzzjgmc()); // 税务机关组织机构名称

			jbxxbForm.setLrr(ud.getYhid());// 录入人
			jbxxbForm.setCjr(ud.getYhid());// 创建人

			// 设置税款所属期
			jbxxbForm.setSkssksrq(jbxxbForm.getSknd() + "0101");
			jbxxbForm.setSkssjsrq(jbxxbForm.getSknd() + "1231");

			java.util.Date time = SfDateUtil.getDate(jbxxbForm.getSbnd()
					+ "0601");// 匹配税费接口的rq参数，无意义
			// 申报日期

			// 调用税费接口获得企业的征收方式
			ServiceProxy proxy = new ServiceProxy();
			System.out.println("税费入口参数，计算机代码=" + jbxxbForm.getJsjdm());
			System.out.println("税费入口参数，申报日期=" + time);
			System.out.println("税费入口参数，税款所属开始日期="
					+ SfDateUtil.getDate(jbxxbForm.getSkssksrq()));
			System.out.println("税费入口参数，税款所属结束日期="
					+ SfDateUtil.getDate(jbxxbForm.getSkssjsrq()));

			QysdsSet sdsInfo = proxy.getQysdsInfo(jbxxbForm.getJsjdm(), time,
					SfDateUtil.getDate(jbxxbForm.getSkssksrq()), SfDateUtil
							.getDate(jbxxbForm.getSkssjsrq()),
					CodeConstant.SFGL_QYSDS_BBFS_NB);
			// 判断征收方式
			if (sdsInfo.getZsfs() != null) {

				System.out.println(jbxxbForm.getJsjdm() + "企业征收方式不为空 :"
						+ sdsInfo.getZsfs().getZsfsdm());

				jbxxbForm.setZsfsdm(sdsInfo.getZsfs().getZsfsdm()); // 征收方式代码

				jbxxbForm.setZsfsmc(sdsInfo.getZsfs().getZsfsmc()); // 征收方式名称

				// modified by hazhengze 20051227 End
				if (jbxxbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {

					throw new ApplicationException("该纳税人为纯益率方式！不需要填报纳税人基本信息表!");

				} else if (jbxxbForm.getZsfsdm().equals(
						CodeConstant.ZSFSDM_DEZS)) { // 定额征收

					throw new ApplicationException("该纳税人为定额征收方式！不需要填报纳税人基本信息表!");
				} else if (jbxxbForm.getZsfsdm().equals(
						CodeConstant.ZSFSDM_CZZS)) { // 查账征收

				}
			} else {
				// 20070208征收方式如果取出为空则认为是查账征收企业的。
				// throw new ApplicationException(
				// "该纳税人没有核定企业所得税征收方式，请核定后继续填写基本信息表!");

				jbxxbForm.setZsfsdm(CodeConstant.ZSFSDM_CZZS);
				jbxxbForm.setZsfsmc(CodeConstant.ZSFSNAME_CZZS);
			}

			jbxxbForm.setCwkjzddm(CodeConstant.CWKJZD01);// 会计制度代码
			jbxxbForm.setCwkjzddm_old(CodeConstant.CWKJZD01);// 会计制度代码修改前

			jbxxbForm.setJmlxdm(CodeConstant.JMLXNO);// 减免类型代码
			jbxxbForm.setJmlxdm_old(CodeConstant.JMLXNO);// 减免类型代码修改前

//			// 工资管理形式
//			jbxxbForm.setGzglxsdm(CodeConstant.GZGLXS01);
//			// 工资管理形式
//			jbxxbForm.setGzglxsdm_old(CodeConstant.GZGLXS01);

			/** **********************************已经录入过的页面信息start************************************************************** */

			StringBuffer bf = new StringBuffer();
			// select
			bf
					.append(" select ")
					// 查询字段
					.append(" NSRJSJDM,NSRSBH,NSRMC,ND,SBND,SSJJLX, ")
					// 子查询-根据登记注册类型代码表查询登记注册类型名称
					// 所属经济类型-登记注册类型
					.append(
							" (SELECT DJZCLXMC FROM DMDB.DJ_DM_DJZCLX T2 WHERE T2.DJZCLXDM=T1.SSJJLX) AS SSJJLXMC, ")
					.append(" LXDH,JYDZ,SSHY, ")
					// 子查询-根据所属行业代码表查询所属行业名称
					.append(
							" (SELECT GJBZHYMC FROM DMDB.GY_DM_GJBZHY T3 WHERE T3.GJBZHYDM=T1.SSHY) AS SSHYMC,")
					.append(" ZSFS, ")
					// 子查询-根据企业所得税征收方式代码表查询企业所得税征收方式名称
					.append(
							" (SELECT ZSFSMC FROM DMDB.SF_DM_ZSFS T4 WHERE T4.ZSFSDM=T1.ZSFS) AS ZSFSMC,")
					.append("CKZD,GZGLXS, ")
					.append(" JMLX,SWJGZZJGDM, ")
					// 子查询-根据税务机关组织代码查询税务机关组织机构名称
					.append(
							" (SELECT SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T5 WHERE T5.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC, ")
					.append(" CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,ZCZBJE,ZCZE ")
					// from
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ")
					// where
					.append(" where  t1.nsrjsjdm = ? and t1.nd = ? ");

			System.out.println("企业所得税-基本信息查询SQL");
			System.out.println(bf.toString());

			ps = conn.prepareStatement(bf.toString());
			System.out.println("1-" + jbxxbForm.getJsjdm());
			System.out.println("2-" + jbxxbForm.getSknd());
			ps.setString(1, jbxxbForm.getJsjdm());
			ps.setString(2, jbxxbForm.getSknd());
			rs = ps.executeQuery();

			if (rs.next()) {

				// 纳税人计算机代码
				jbxxbForm.setJsjdm(rs.getString("NSRJSJDM"));
				// 纳税人识别号－税务登记证号
				// jbxxbForm.setNsrsbh(rs.getString("NSRSBH"));
				// 纳税人名称
				// jbxxbForm.setNsrmc(rs.getString("NSRMC"));
				// 税款年度
				jbxxbForm.setSknd(rs.getString("ND"));
				// 申报年度
				jbxxbForm.setSbnd(rs.getString("SBND"));
				// 所属经济类型
				// jbxxbForm.setSsjjlxdm(rs.getString("SSJJLX"));
				// 所属经济类型
				// jbxxbForm.setSsjjlxmc(rs.getString("SSJJLXMC"));
				// 联系电话
				jbxxbForm.setLxdh(rs.getString("LXDH"));
				// 经营地址
				// jbxxbForm.setJydz(rs.getString("JYDZ"));
				// 所属行业
				// jbxxbForm.setSshydm(rs.getString("SSHY"));
				// 所属行业
				// jbxxbForm.setSshymc(rs.getString("SSHYMC"));
				// 财会制度 00:一类01:二类02:三类
				jbxxbForm.setCwkjzddm(rs.getString("CKZD"));
				// 会计制度代码修改前
				jbxxbForm.setCwkjzddm_old(rs.getString("CKZD"));
				// 税务机关组织机构代码
				// jbxxbForm.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				// 税务机关组织机构代码
				// jbxxbForm.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));

				// 企业所得税征收方式
				// jbxxbForm.setZsfsdm(rs.getString("ZSFS"));
				// 企业所得税征收方式
				// jbxxbForm.setZsfsmc(rs.getString("ZSFSMC"));

//				// 减免类型
//				jbxxbForm.setJmlxdm(rs.getString("JMLX"));
//				// 减免类型
//				jbxxbForm.setJmlxdm_old(rs.getString("JMLX"));
//
//				// 工资管理形式
//				jbxxbForm.setGzglxsdm(rs.getString("GZGLXS"));
//				// 工资管理形式
//				jbxxbForm.setGzglxsdm_old(rs.getString("GZGLXS"));

				jbxxbForm.setCjr(rs.getString("CJR"));
				jbxxbForm.setCjrq(SBStringUtils.getStrFromDate(rs
						.getTimestamp("CJSJ")));
				jbxxbForm.setCjr(rs.getString("LRR"));
				jbxxbForm.setLrrq(SBStringUtils.getStrFromDate(rs
						.getTimestamp("LRSJ")));
				jbxxbForm.setXtjb(rs.getString("XTJB"));
				jbxxbForm.setBbmsf(rs.getString("BBMSF"));
				jbxxbForm.setZczbje(rs.getString("ZCZBJE"));
				jbxxbForm.setZcze(rs.getString("ZCZE"));

			}
			
			else{
				jbxxbForm.setBbmsf("");
			}
			//查询是否保存过汇算清缴数据
			jbxxbForm.setQueryFlag(DoQueryData(jbxxbForm));
			/** ***********************************已经录入过end*************************************************************** */

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			Debug.out("jbxx processor query free connection jsjdm "+jbxxbForm.getJsjdm());
			SfDBResource.freeConnection(conn);
		}
		System.out.println(jbxxbForm.getJsjdm() + "企业征收方式为"
				+ jbxxbForm.getZsfsdm());

		return jbxxbForm;
	}

	/**
	 * doSave 用于存储页面提交的详尽处理信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doSave(VOPackage vo) throws BaseException {

		JbxxbForm2009 jbxxbForm = (JbxxbForm2009) vo.getData();

		Connection conn = null;
		SfDBAccess dbAgent = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		// sql buffer
		StringBuffer bf = new StringBuffer();

		// 减免是否匹配
		boolean jm_type = false;

		/***********************************************************************
		 * 判断减免的类型在税费核定的减免类型中，不在则throw new
		 * ApplicationException("该纳税人不享受此减免类型!");
		 * 
		 * 
		 * 保存之前根据 修改前的值和修改后的值 判断 会计制度、工资管理形式、减免类型是否修改过，有则对已有的申报表数据进行删除
		 * 
		 * 保存成功后 将会计制度、工资管理形式、减免类型对应的form中的
		 * 
		 * cwkjzddm cwkjzddm_old
		 * 
		 * gzglxsdm gzglxsdm_old
		 * 
		 * jmlxdm jmlxdm_old
		 * 
		 * 设置为一致
		 * 
		 * 返回
		 * 
		 * 
		 **********************************************************************/

		try {
			conn = SfDBResource.getConnection();
			st = conn.createStatement();
			dbAgent = new SfDBAccess(conn);
			UserData ud = (UserData) vo.getUserData();

			// 调用税费接口
			ServiceProxy proxy = new ServiceProxy();

			// 获取报表处理数据库接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

			// 设置版本号
			jbxxbForm.setVersion(CodeConstant.QYSDS_VERSION_2009);

			jbxxbForm.setLrrq(SBStringUtils.getStrFromDate(TinyTools
					.getDBTimestamp(conn)));
			System.out.println("CJRQ=" + jbxxbForm.getCjrq());
			if (jbxxbForm.getCjrq() == null || "".equals(jbxxbForm.getCjrq())) {
				jbxxbForm.setCjrq(jbxxbForm.getLrrq());
			}

			// 更新税费表征收方式认定表中的纳税人状态
			try {
				String sql = "update sfdb.sf_jl_qysdszsfs_his set nsrzt='10',lrrq=sysdate where jsjdm='"
						+ jbxxbForm.getJsjdm() + "'";
				dbAgent.updateSQL(sql);
			} catch (BaseException ex1) {
				// 抛出异常
				ex1.printStackTrace();
				new ApplicationException("数据库更新记录失败！" + jbxxbForm.getJsjdm()
						+ ":" + ex1.getMessage());
			}

			/**
			 * 插入历史数据
			 */

			bf.delete(0, bf.length());

			bf
					.append(" insert into sbdb.sb_jl_qysds_nsrjbxxb_his ")
					.append(
							" (XH,NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,DL,CYL,DE,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2,ZCZBJE,ZCZE) ")
					.append(" (select ")
					.append(TinyTools.getXh(jbxxbForm.getJsjdm()))
					.append(",")
					.append(
							" NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,DL,CYL,DE,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2,ZCZBJE,ZCZE ")
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ").append(
							" where  t1.nsrjsjdm = ").append(
							SBStringUtils.getSQLStr(jbxxbForm.getJsjdm()))
					.append(" and t1.nd = ").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSknd()))
					.append(" ) ");

			System.out.println("企业所得税-基本信息插入历史数据SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			/**
			 * 删除数据
			 */

			bf.delete(0, bf.length());

			bf.append(" delete ")

					// from
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ")
					// where
					.append(" where  t1.nsrjsjdm = ").append(
							SBStringUtils.getSQLStr(jbxxbForm.getJsjdm()))
					.append(" and t1.nd = ").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSknd()));

			System.out.println("企业所得税-基本信息删除SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());
			
			String bbmsf_old=jbxxbForm.getBbmsf();
			SWDJJBSJ djsj = null;
			try {
				// 获得企业登记基本信息

				djsj = InterfaceDj.getJBSJ_New(jbxxbForm.getJsjdm(), ud);

			} catch (Exception ex1) {
				throw ExceptionUtil.getBaseException(ex1);
			}
			//String sybs=FriendHelper.getNsrSybs(djsj);
			 String sybs = jbxxbForm.getSybs();
			// 补充form中的工资管理形式的名称 财务会计制度的名称及相对应的报表描述符
			if (CodeConstant.CWKJZD01.equals(jbxxbForm.getCwkjzddm())) {

				jbxxbForm.setCwkjzdmc(CodeConstant.CWKJZDMC01);
				jbxxbForm.setBbmsf(CodeConstant.BBMSF_2009_10);
				
			} else if (CodeConstant.CWKJZD02.equals(jbxxbForm.getCwkjzddm())) {

				jbxxbForm.setCwkjzdmc(CodeConstant.CWKJZDMC02);
				jbxxbForm.setBbmsf(CodeConstant.BBMSF_2009_20);

			} else if (CodeConstant.CWKJZD03.equals(jbxxbForm.getCwkjzddm())) {

				jbxxbForm.setCwkjzdmc(CodeConstant.CWKJZDMC03);
				jbxxbForm.setBbmsf(CodeConstant.BBMSF_2009_30);
			}

			//税款年度大于2012年特殊处理
			if(Integer.valueOf(jbxxbForm.getSknd()).intValue()>2012)
			{
				if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSZJG)){
					jbxxbForm.setBbmsf(jbxxbForm.getBbmsf()+CodeConstant.BBMSF_2012_17);
				}
				if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
					jbxxbForm.setBbmsf("");
				}
			}
			/**
			 * 插入修改的数据
			 */

			bf.delete(0, bf.length());

			bf
					.append(" insert into sbdb.sb_jl_qysds_nsrjbxxb ")
					.append(
							" (NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2,ZCZBJE,ZCZE) ")
					// 计算机代码
					.append(" values(").append(
							SBStringUtils.getSQLStr(jbxxbForm.getJsjdm()))
					// 纳税人识别号
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getNsrsbh()))
					// 纳税人名称
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getNsrmc()))
					// 版本号
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getVersion()))
					// 税款年度
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSknd()))
					// 申报年度
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSbnd()))
					// 申请审批编号
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSqspbh()))
					// 所属经济类型代码
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSsjjlxdm()))
					// 所属经济类型名称
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSsjjlxmc()))
					// 联系电话
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getLxdh()))
					// 经营地址
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getJydz()))
					// 所属行业代码
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSshydm()))
					// 所属行业名称
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSshymc()))
					// 征收方式代码
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getZsfsdm()))
					// 征收方式代码
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getZsfsmc()))
					// 财会制度代码
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getCwkjzddm()))
					// 财会制度名称
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getCwkjzdmc()))
					// 工资管理形式代码
					.append(",").append(
							SBStringUtils.getSQLStr(null))
					// 工资管理形式名称
					.append(",").append(
							SBStringUtils.getSQLStr(null))
					// 减免类型代码
					.append(",").append(
							SBStringUtils.getSQLStr(null))
					// 减免类型名称
					.append(",").append(
							SBStringUtils.getSQLStr(null))
					// 境外所得已纳税款抵扣方法
					.append(",").append("''")
					// 境外所得已纳税款抵扣方法名称
					.append(",").append("''")
					// 税务机关组织机构代码
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSwjgzzjgdm()))
					// 税务机关组织机构名称
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getSwjgzzjgmc()))
					// 创建人
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getCjr()))
					// 创建时间
					.append(",").append(
							SBStringUtils.getSQLDate2(jbxxbForm.getCjrq()))
					// 录入人
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getLrr()))
					// 录入时间
					.append(",").append(
							SBStringUtils.getSQLDate2(jbxxbForm.getLrrq()))
					// 系统级别
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getXtjb()))
					// 报表描述符
					.append(",").append(
							SBStringUtils.getSQLStr(jbxxbForm.getBbmsf()))
					// 税款所属时期起
					.append(",").append(
							SBStringUtils.getSQLDate2(jbxxbForm.getSkssksrq()))
					// 税款所属时期止
					.append(",").append(
							SBStringUtils.getSQLDate2(jbxxbForm.getSkssjsrq()))
					// 备注1
					.append(",").append("''")
					// 备注2
					.append(",").append("''")
					//注册资本金额
					.append(",").append(jbxxbForm.getZczbje())
					// 资产总额
					.append(",").append(jbxxbForm.getZcze())

					.append(") ");

			Debug.out("企业所得税-基本信息插入SQL\n");
			Debug.out(bf.toString());

			st.execute(bf.toString());

			/**
			 * TODO 如果会计制度重新修改了，需要删除对应的申报表
			 */
			if (!(jbxxbForm.getCwkjzddm().equals(jbxxbForm.getCwkjzddm_old()))) {

				QysdsReportsDeclare qd = new QysdsReportsDeclare();
				qd = this.setQysdsReport(jbxxbForm);

				// 企业所得税报表内单表申明对象
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();

				// 原选择为企业（小企业）会计制度
				if (CodeConstant.CWKJZD01.equals(jbxxbForm.getCwkjzddm_old())) {
					// 清除附表一1
					table.setTableId(CodeConstant.TABLE_ID_2009_1_1);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

					// 清除附表二1
					table.setTableId(CodeConstant.TABLE_ID_2009_2_1);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

				}

				// 原选择为金融企业会计制度
				if (CodeConstant.CWKJZD02.equals(jbxxbForm.getCwkjzddm_old())) {
					// 清除附表一2
					table.setTableId(CodeConstant.TABLE_ID_2009_1_2);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

					// 清除附表二2
					table.setTableId(CodeConstant.TABLE_ID_2009_2_2);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

				}

				// 原选择为事业单位、社会团体、民办非企业单位会计制度
				if (CodeConstant.CWKJZD03.equals(jbxxbForm.getCwkjzddm_old())) {
					// 清除附表一3
					table.setTableId(CodeConstant.TABLE_ID_2009_1_3);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

					// 清除附表二3
					table.setTableId(CodeConstant.TABLE_ID_2009_2_3);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);
				}

			}
			
			
			System.out.println("税款年度: "+jbxxbForm.getSknd());
			/**
			 * 如果发生变化删除所有的年报数据  且税款年度大于2012(保持原来逻辑不变)
			 * add by wangcy 2013-12-07
			 */
			if(Integer.valueOf(jbxxbForm.getSknd()).intValue()>2012){
				if(!bbmsf_old.equals(jbxxbForm.getBbmsf())){
					System.out.println("税款年度"+jbxxbForm.getSknd() +"发生变化,删除当前年度数据!");
					QysdsReportsDeclare qd = new QysdsReportsDeclare();
					qd = this.setQysdsReport(jbxxbForm);
					
					// 企业所得税报表内单表申明对象
					QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
	
					//循环删除客户端填写的所有表，外加新添加的分配表，
					for(int i=0;i<CodeConstant.TABLE_ID_ALL.length;i++){
						table.setTableId(CodeConstant.TABLE_ID_ALL[i]);
						table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
						// set table
						qd.getTableContentList().put(table.getTableId(), table);
						// 调用delete方法进行数据删除
						iApp.deleteSingleTable(qd);
					}
	
//					//删除企业所得税年报
//					table.setTableId(CodeConstant.TABLE_ID_CZZSSDSNB_2012);
//					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
//					// set table
//					qd.getTableContentList().put(table.getTableId(), table);
//					// 调用delete方法进行数据删除
//					iApp.deleteSingleTable(qd);
//					
//					
//					//删除企业所得税年报分配表
//					table.setTableId(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
//					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
//					// set table
//					qd.getTableContentList().put(table.getTableId(), table);
//					// 调用delete方法进行数据删除
//					iApp.deleteSingleTable(qd);
				}
			}

			/**
			 * 重新设置原始值
			 */
			jbxxbForm.setCwkjzddm_old(jbxxbForm.getCwkjzddm());
			jbxxbForm.setGzglxsdm_old(jbxxbForm.getGzglxsdm());
			jbxxbForm.setJmlxdm_old(jbxxbForm.getJmlxdm());

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw new ApplicationException(ex.getMessage());
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return jbxxbForm;
	}

	// /**
	// * doDelete 用于删除页面提交的详尽处理信息
	// *
	// * @param vo
	// * 业务参数
	// * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	// * @throws BaseException
	// * 当其他情况都会抛出异常信息
	// */
	//
	// private Object doDelete(VOPackage vo) throws BaseException {
	//
	// JbxxbForm2009 jbxxbForm = (JbxxbForm2009) vo.getData();
	//
	// Connection conn = null;
	// SfDBAccess dbAgent = null;
	// UserData ud = (UserData) vo.getUserData();
	//
	// try {
	// // conn = SfDBResource.getConnection();
	// // dbAgent = new SfDBAccess(conn);
	// //
	// // jbxxbForm.setNd(getSbnd(jbxxbForm.getSbrq()));
	// //
	// // // 删除已有历史所得税数据
	// // try {
	// // String strWhere = " jsjdm='" + jbxxbForm.getJsjdm() + "'"
	// // + " and qxdm='" + jbxxbForm.getQxdm() + "'" + " and nd='"
	// // + jbxxbForm.getNd() + "'";
	// // // 删除联营、股份数据
	// // dbAgent.delete(strWhere, new Lygf());
	// //
	// // // 删除企业所得税年报数据
	// // dbAgent.delete(strWhere, new Qysdsnb());
	// // // 记录删除日志
	// // TinyTools.makeLog4Qysds(ud, jbxxbForm.getJsjdm(), "企业所得税年报");
	// // } catch (BaseException ex1) {
	// // // 抛出异常
	// // ex1.printStackTrace();
	// // new ApplicationException("数据库删除操作失败！");
	// // }
	// //
	// // // 重新设置初始化数据
	// // initForm(jbxxbForm);
	// // // 清楚已加载数据
	// // jbxxbForm.getDataList().clear();
	// } catch (Exception ex) {
	// // 抛出异常
	// ex.printStackTrace();
	// throw new ApplicationException("数据库删除操作失败！");
	// } finally {
	// SfDBResource.freeConnection(conn);
	// }
	// return jbxxbForm;
	// }

	/**
	 * 初始化
	 * 
	 * @param jbxxbForm
	 *            主表数据
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private void initForm(JbxxbForm2009 jbxxbForm) throws BaseException {

	}

	// /**
	// * 根据申报日期取得当前前年0101-1231
	// *
	// * @param curSbrq
	// * 申报日期
	// * @return dateMap
	// */
	// public Map getSbrl(String curSbrq) {
	// Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
	// GregorianCalendar calendar = new GregorianCalendar();
	// calendar.setTime(sbrq);
	// calendar.add(calendar.YEAR, -1);
	// int year = calendar.get(calendar.YEAR);
	// String nd = new Integer(year).toString();
	// Timestamp ksrq;
	// Timestamp jsrq;
	// Map retMap = new HashMap();
	// ksrq = new Timestamp(new GregorianCalendar(year, 0, 1).getTime()
	// .getTime());
	// jsrq = new Timestamp(new GregorianCalendar(year, 11, 31).getTime()
	// .getTime());
	//
	// Map dateMap = new HashMap();
	// dateMap.put("ksrq", ksrq);
	// dateMap.put("jsrq", jsrq);
	// return dateMap;
	// }

	/**
	 * 设置报表对象基本信息
	 * 
	 * @param report
	 * @param form
	 */
	public QysdsReportsDeclare setQysdsReport(JbxxbForm2009 form) {

		QysdsReportsDeclare report = new QysdsReportsDeclare();
		/**
		 * 基本信息
		 */
		Jbxx jbxx = new Jbxx();

		/**
		 * 基本信息(JBXX)-计算机代码
		 */
		jbxx.setJsjdm(form.getJsjdm());
		/**
		 * 基本信息(JBXX)-纳税人名称
		 */
		jbxx.setNsrmc(form.getNsrmc());
		/**
		 * 基本信息(JBXX)-所属经济类型
		 */
		jbxx.setSsjjlx(form.getSsjjlxdm());
		/**
		 * 基本信息(JBXX)-联系电话
		 */
		jbxx.setLxdh(form.getLxdh());
		/**
		 * 基本信息(JBXX)-所属行业
		 */
		jbxx.setSshy(form.getSshydm());
		/**
		 * 基本信息(JBXX)-征收方式
		 */
		jbxx.setZsfs(form.getZsfsdm());
		/**
		 * 基本信息(JBXX)-财会制度
		 */
		jbxx.setCkzd(form.getCwkjzddm());
		/**
		 * 基本信息(JBXX)-工资管理形式
		 */
		jbxx.setGzglxs("");
		/**
		 * 基本信息(JBXX)-减免类型
		 */
		jbxx.setJmlx("");

		report.setJbxx(jbxx);

		/**
		 * 应用编号
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYSDS);
		/**
		 * 版本号
		 */
		report.setVersion(CodeConstant.QYSDS_VERSION_2009);
		/**
		 * 纳税人计算机代码
		 */
		report.setNsrjsjdm(form.getJsjdm());
		/**
		 * 纳税人名称
		 */
		report.setNsrmc(form.getNsrmc());
		/**
		 * 报表期类型
		 */
		report.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		/**
		 * 期号
		 */
		report.setQh("1");
		/**
		 * 税款所属开始日期
		 */
		if (form.getSkssksrq() != null && !form.getSkssksrq().equals("")) {
			report.setSkssksrq(new Date(TinyTools.stringToDate(
					form.getSkssksrq(), "yyyyMMdd").getTime()));
		}
		/**
		 * 税款所属结束日期
		 */
		if (form.getSkssjsrq() != null && !form.getSkssjsrq().equals("")) {
			report.setSkssjsrq(new Date(TinyTools.stringToDate(
					form.getSkssjsrq(), "yyyyMMdd").getTime()));
		}
		/**
		 * 申报日期
		 */
		if (form.getLrrq() != null && !form.getLrrq().equals("")) {
			report.setSbrq(new Date(TinyTools.stringToDate(form.getLrrq(),
					"yyyyMMdd").getTime()));
		}
		/**
		 * 税款年度
		 */
		report.setSknd(form.getSknd());
		/**
		 * 税务机关组织机构代码
		 */
		report.setSwjgzzjgdm(form.getSwjgzzjgdm());
		/**
		 * 区县代码
		 */
		report.setQxdm(form.getSwjgzzjgdm().substring(2, 4));
		/**
		 * 录入人
		 */
		report.setLrr(form.getLrr());
		/**
		 * 录入日期
		 */
		if (form.getLrrq() != null && !form.getLrrq().equals("")) {
			report.setLrrq(new Date(TinyTools.stringToDate(form.getLrrq(),
					"yyyyMMdd").getTime()));
		}
		/**
		 * 创建人
		 */
		report.setCjr(form.getLrr());
		/**
		 * 创建日期
		 */
		if (form.getCjrq() != null && !form.getCjrq().equals("")) {
			report.setCjrq(new Date(TinyTools.stringToDate(form.getCjrq(),
					"yyyyMMdd").getTime()));
		}

		return report;
	}

    /**
	 * @decription 级联查询是否填写过汇算清缴数据
	 * @author wangcy
	 * @modify_date 2013-12-10
	 * @param pData
	 * @throws BaseException
     * @throws com.ttsoft.framework.exception.BaseException 
	 */
	public String DoQueryData(JbxxbForm2009 form)  
	{
		Connection conn = null;
		PreparedStatement queryPstmtZb = null;
		PreparedStatement queryPstmtCb = null;
		ResultSet queryRsZb = null;
		ResultSet queryRsCb = null;
		String  queryFlag = "";

		StringBuffer querySqlZb = new StringBuffer();
		StringBuffer querySqlCb = new StringBuffer();
		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 获取税款所属季度、年度   因为是年报  所以期号  为1
			String qh = "1";
			String nd = form.getSkssksrq().substring(0, 4);
			querySqlZb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM= '");

			querySqlZb.append(form.getJsjdm()).append("' ");
			querySqlZb.append(" AND BBQLX= '");
			querySqlZb.append(Constants.CREPORTS_IBBQLX_TYPE_YEAR).append("' ");
			querySqlZb.append(" AND QH= '");
			querySqlZb.append(qh).append("' ");
			querySqlZb.append(" AND SKND= '");
			querySqlZb.append(nd).append("' ");


			System.out.println(querySqlZb.toString());
			queryPstmtZb = conn.prepareStatement(querySqlZb.toString());
			queryRsZb = queryPstmtZb.executeQuery(querySqlZb.toString());

			querySqlCb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_ND WHERE NSRJSJDM= '");
			querySqlCb.append(form.getJsjdm()).append("' ");
			querySqlCb.append(" AND BBQLX= '");
			querySqlCb.append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR).append("' ");
			querySqlCb.append(" AND QH= '");
			querySqlCb.append(qh).append("' ");
			querySqlCb.append(" AND SKND= '");
			querySqlCb.append(nd).append("' ");

			queryPstmtCb = conn.prepareStatement(querySqlCb.toString());
			queryRsCb = queryPstmtCb.executeQuery(querySqlCb.toString());
			System.out.println(querySqlCb.toString());
			String queryFlagZb = "0";
			String queryFlagCb = "0";
			
			if(queryRsZb.next()){ queryFlagZb = "1"; }
			if(queryRsCb.next()){ queryFlagCb = "1"; }

			if(queryFlagZb.equals("1") || queryFlagCb.equals("1")){
				queryFlag="1";
			}else{
				queryFlag="0";
			}
			
		} catch (Exception localException) {
			localException.printStackTrace();
		} finally {
			this.release(queryRsZb, queryPstmtZb);
			this.release(queryRsCb, queryPstmtCb);
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return queryFlag;
	}
	
    /**
	 * @description 释放数据库资源
	 * @author wangcy
	 * @modify_date 2013-12-08
	 * @param rs
	 * @param stmt
	 * @param con
	 */
    public void release(ResultSet rs, Statement stmt){
		if(rs!=null){
			  try{
			      rs.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		if(stmt!=null){
			  try{
			      stmt.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
	}
}
