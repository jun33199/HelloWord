package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.processor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.Jbxx;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.CAConstants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.QysdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.NsrJbxxBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.NsrJbxxdjbVO;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @version 1.0 纳税人基本信息processor类
 */
public class NsrJbxxdjbProcessor implements Processor {
	public NsrJbxxdjbProcessor() {
	}


	/**
	 * 根据业务操作类型值来做业务操作
	 * 
	 * @param vo
	 *            VOPackage
	 * @return java.lang.Object
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	public Object process(VOPackage vo) throws BaseException {
		// 根据业务操作类型值来做业务操作
		try {
			switch (vo.getAction()) {
			// 查询
			case QysdsksActionConstant.INT_ACTION_QUERY: {
				return doQuery((Map) vo.getData());
			}

				// 保存
			case QysdsksActionConstant.INT_ACTION_SAVE: {
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
	 * 查询纳税人基本信息
	 * 
	 * @param pData
	 *            Map
	 * @return Map
	 * @throws BaseException
	 */
	private Object doQuery(Map pData) throws BaseException {
		// 数据库连接对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		NsrJbxxBO nsrJbxxBO = new NsrJbxxBO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		try {

			// 获得数据库连接
			conn = DBResource.getConnection();

			// 计算机代码
			String jsjdm = null;
			// 当前日期
			Timestamp curDate = null;
			// 税务登记基本数据值对象
			SWDJJBSJ djjbsj = (SWDJJBSJ) pData
					.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
			jsjdm = (String) pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
			curDate = (Timestamp) pData.get(QysdsksMapConstant.STRING_KEY_DATE);// 申报日期

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(curDate);

			int year = calendar.get(calendar.YEAR);
			String nd = new Integer(year).toString(); // 求申报年度
			nsrJbxxBO.setSbnd(nd);// 申报年度

			// 取得税款所属日期Map
			Map skssrq = new HashMap();
			skssrq = Skssrq.yearSkssrq(curDate);
			// 取得税款所属开始和结束日期
			Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
			Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
			nsrJbxxBO.setSkssksrq(skssksrq.toString().substring(0, 11));// 税款所属开始日期
			nsrJbxxBO.setSkssjsrq(skssjsrq.toString().substring(0, 11));// 税款所属结束日期
			nsrJbxxBO.setSknd((String) skssrq.get(Skssrq.SKSSRQ_ND));// 税款年度

			nsrJbxxBO.setJsjdm(jsjdm);// 纳税人计算机代码
			nsrJbxxBO.setNsrmc(djjbsj.getNsrmc());// 纳税人名称
			nsrJbxxBO.setNsrsbh(djjbsj.getSwdjzh());// 纳税人识别号

			nsrJbxxBO.setSsjjlxdm(djjbsj.getDjzclxdm()); // 所属经济类型-登记注册类型代码
			nsrJbxxBO.setSsjjlxmc(djjbsj.getDjzclxmc());// 所属经济类型-登记注册类型名称
			nsrJbxxBO.setLxdh(djjbsj.getZcdzlxdh()); // 注册地址联系电话
			nsrJbxxBO.setJydz(djjbsj.getJydz());// 经营地址
			nsrJbxxBO.setSshydm(djjbsj.getGjbzhydm());// 所属行业代码
			nsrJbxxBO.setSshymc(djjbsj.getGjbzhymc());// 所属行业代码

			nsrJbxxBO.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm()); // 税务机关组织机构代码
			nsrJbxxBO.setSwjgzzjgmc(djjbsj.getSwjgzzjgmc()); // 税务机关组织机构名称
			//nsrJbxxBO.setZczbje(djjbsj.getZczbje()+"");
			nsrJbxxBO.setLrr(jsjdm);// 录入人
			nsrJbxxBO.setCjr(jsjdm);// 创建人

			nsrJbxxBO.setCjrq(curDate.toString().substring(0, 11));// 创建日期
			nsrJbxxBO.setLrrq(curDate.toString().substring(0, 11));// 录入日期

			// 获取报表处理数据库接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 设置版本号
			//nsrJbxxBO.setVersion(iApp
			//		.getCurrentVersion(Constants.CREPORTS_APPID_QYSDS));
			//nsrJbxxBO.setVersion(Constant.CA_XSLTDM_NSRJBXXB);
			//将xslt的版本与报表的版本区分开
			nsrJbxxBO.setVersion(Constant.REPORT_VERSION_NSRJBXXB);
			
			// 系统级别
			nsrJbxxBO.setXtjb(Constant.SDS_1);

			// 调用税费接口获得企业的征收方式
			ServiceProxy proxy = new ServiceProxy();
			System.out.println("税费入口参数，计算机代码=" + nsrJbxxBO.getJsjdm());
			System.out.println("税费入口参数，申报日期=" + curDate);
			System.out.println("税费入口参数，税款所属开始日期=" + skssksrq);
			System.out.println("税费入口参数，税款所属结束日期=" + skssjsrq);

			QysdsSet sdsInfo = proxy.getQysdsInfo(nsrJbxxBO.getJsjdm(),
					curDate, skssksrq, skssjsrq, "01");// 年报

			// 判断征收方式
			if (sdsInfo.getZsfs() != null) {

				System.out.println(nsrJbxxBO.getJsjdm() + "企业征收方式不为空 :"
						+ sdsInfo.getZsfs().getZsfsdm());

				nsrJbxxBO.setZsfsdm(sdsInfo.getZsfs().getZsfsdm()); // 征收方式代码

				nsrJbxxBO.setZsfsmc(sdsInfo.getZsfs().getZsfsmc()); // 征收方式名称

				// modified by hazhengze 20051227 End
				if (nsrJbxxBO.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {

					throw new ApplicationException("该纳税人为纯益率方式！不需要填报纳税人基本信息表!");

				} else if (nsrJbxxBO.getZsfsdm().equals(
						CodeConstant.ZSFSDM_DEZS)) { // 定额征收

					throw new ApplicationException("该纳税人为定额征收方式！不需要填报纳税人基本信息表!");
				} else if (nsrJbxxBO.getZsfsdm().equals(
						CodeConstant.ZSFSDM_CZZS)) { // 查账征收

				}
			} else {
				// 20070208征收方式如果取出为空则认为是查账征收企业的。
				// throw new ApplicationException(
				// "该纳税人没有核定企业所得税征收方式，请核定后继续填写基本信息表!");

				nsrJbxxBO.setZsfsdm(Constant.ZSFSDM_CZZS);
				nsrJbxxBO.setZsfsmc(Constant.ZSFSNAME_CZZS);
			}

			nsrJbxxBO.setCwkjzddm(CodeConstant.CWKJZD01);// 会计制度代码
			nsrJbxxBO.setCwkjzddm_old(CodeConstant.CWKJZD01);// 会计制度代码修改前

			nsrJbxxBO.setJmlxdm(CodeConstant.JMLXNO);// 减免类型代码
			nsrJbxxBO.setJmlxdm_old(CodeConstant.JMLXNO);// 减免类型代码修改前

			// 工资管理形式
			nsrJbxxBO.setGzglxsdm(CodeConstant.GZGLXS01);
			// 工资管理形式
			nsrJbxxBO.setGzglxsdm_old(CodeConstant.GZGLXS01);

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
					.append(" CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,ZCZE,ZCZBJE ")
					// from
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ")
					// where
					.append(" where  t1.nsrjsjdm = ? and t1.nd = ? ");

			System.out.println("企业所得税-基本信息查询SQL");
			System.out.println(bf.toString());

			ps = conn.prepareStatement(bf.toString());
			System.out.println("1-" + nsrJbxxBO.getJsjdm());
			System.out.println("2-" + nsrJbxxBO.getSknd());
			ps.setString(1, nsrJbxxBO.getJsjdm());
			ps.setString(2, nsrJbxxBO.getSknd());
			rs = ps.executeQuery();

			if (rs.next()) {

				// 纳税人计算机代码
				//nsrJbxxBO.setJsjdm(rs.getString("NSRJSJDM"));
				// 纳税人识别号－税务登记证号
				// nsrJbxxBO.setNsrsbh(rs.getString("NSRSBH"));
				// 纳税人名称
				// nsrJbxxBO.setNsrmc(rs.getString("NSRMC"));
				// 税款年度
				//nsrJbxxBO.setSknd(rs.getString("ND"));
				// 申报年度
				nsrJbxxBO.setSbnd(rs.getString("SBND"));
				// 所属经济类型
				// nsrJbxxBO.setSsjjlxdm(rs.getString("SSJJLX"));
				// 所属经济类型
				// nsrJbxxBO.setSsjjlxmc(rs.getString("SSJJLXMC"));
				// 联系电话
				nsrJbxxBO.setLxdh(rs.getString("LXDH"));
				// 经营地址
				// nsrJbxxBO.setJydz(rs.getString("JYDZ"));
				// 所属行业
				// nsrJbxxBO.setSshydm(rs.getString("SSHY"));
				// 所属行业
				// nsrJbxxBO.setSshymc(rs.getString("SSHYMC"));
				// 财会制度 00:一类01:二类02:三类
				nsrJbxxBO.setCwkjzddm(rs.getString("CKZD"));
				// 会计制度代码修改前
				nsrJbxxBO.setCwkjzddm_old(rs.getString("CKZD"));
				// 税务机关组织机构代码
				// nsrJbxxBO.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				// 税务机关组织机构代码
				// nsrJbxxBO.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));

				// 企业所得税征收方式
				// nsrJbxxBO.setZsfsdm(rs.getString("ZSFS"));
				// 企业所得税征收方式
				// nsrJbxxBO.setZsfsmc(rs.getString("ZSFSMC"));

				// 减免类型
				nsrJbxxBO.setJmlxdm(rs.getString("JMLX"));
				// 减免类型
				nsrJbxxBO.setJmlxdm_old(rs.getString("JMLX"));

				// 工资管理形式
				nsrJbxxBO.setGzglxsdm(rs.getString("GZGLXS"));
				// 工资管理形式
				nsrJbxxBO.setGzglxsdm_old(rs.getString("GZGLXS"));

				nsrJbxxBO.setCjr(rs.getString("CJR"));
				nsrJbxxBO.setCjrq(StringUtils.getStrFromDate(rs
						.getTimestamp("CJSJ")));
				nsrJbxxBO.setCjr(rs.getString("LRR"));
				nsrJbxxBO.setLrrq(StringUtils.getStrFromDate(rs
						.getTimestamp("LRSJ")));
				nsrJbxxBO.setXtjb(rs.getString("XTJB"));
				nsrJbxxBO.setBbmsf(rs.getString("BBMSF"));
				nsrJbxxBO.setZczbje(rs.getString("ZCZBJE"));
				nsrJbxxBO.setZcze(rs.getString("ZCZE"));

			}

			/** ***********************************已经录入过end*************************************************************** */

			
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "查询查账征收企业基本信息数据失败");
		} finally {
			// 关闭数据库连接
			DBResource.destroyConnection(conn);
		}

		System.out.println(nsrJbxxBO.getJsjdm() + "企业征收方式为"
				+ nsrJbxxBO.getZsfsdm());

		return nsrJbxxBO;
	}

	/**
	 * 保存企业所得税数据
	 * 
	 * @param data
	 *            Map
	 * @throws BaseException
	 */
	private Map doSave(VOPackage vop) throws BaseException {
		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();

		Connection conn = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		// sql buffer
		StringBuffer bf = new StringBuffer();
		// 减免是否匹配
		boolean jm_type = false;

		NsrJbxxdjbVO nsrJbxxdjbVO = new NsrJbxxdjbVO();
		NsrJbxxBO nsrJbxxBO = new NsrJbxxBO();

		
		
		nsrJbxxBO = (NsrJbxxBO) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ); // BO
		String bbmsf_old=nsrJbxxBO.getBbmsf();
		
		// 税务登记基本数据值对象
		SWDJJBSJ djjbsj = (SWDJJBSJ) data
				.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);// 登记数据

		DzyjsjVO dzyj = (DzyjsjVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);// 电子原件

		nsrJbxxdjbVO = (NsrJbxxdjbVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);// VO

		try {

			conn = DBResource.getConnection();
			st = conn.createStatement();

			// 获取报表处理数据库接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

			// 更新税费表征收方式认定表中的纳税人状态
			try {
				String sql = "update sfdb.sf_jl_qysdszsfs_his set nsrzt='10',lrrq=sysdate where jsjdm='"
						+ nsrJbxxBO.getJsjdm() + "'";
				st.executeUpdate(sql);
			} catch (Exception ex1) {
				// 抛出异常
				ex1.printStackTrace();
				new ApplicationException("数据库更新记录失败！" + nsrJbxxBO.getJsjdm()
						+ ":" + ex1.getMessage());
			}

			/**
			 * TODO 判断减免的类型在税费核定的减免类型中，不在则throw new
			 * ApplicationException("该纳税人不享受此减免类型!");
			 * 
			 * 如果有减免类型同时获得申请审批表号
			 * 
			 * 1、如果没有减免类型，不做判断,申请审批表号只为默认值 "本单位无减免审批事项"
			 * 2、如果为其他减免类型，则只判断是否有减免，不分类型，同时获得第一条申请审批表号
			 * 3、如果为“高新技术企业”减免类型的判断条件，只判断是否有审批件，不判断时间上是否过期
			 * 4、如果为指定的减免类型则判断是否享受该减免类型，同时获得申请审批表号
			 * 
			 */
			/*
			String sqspbh = "本单位无减免审批事项";
			if (("".equals(nsrJbxxBO.getJmlxdm()))
					|| (CodeConstant.JMLXNO.equals(nsrJbxxBO.getJmlxdm()))) {

				nsrJbxxBO.setSqspbh(sqspbh);

			} else if ((CodeConstant.JMLXOTHER.equals(nsrJbxxBO.getJmlxdm()))) {

				bf.delete(0, bf.length());

				bf
						.append(
								" select t1.sqspbh as sqspbh,t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
						.append(" where t1.jsjdm= ").append(
								StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
						.append(" and t2.sqspbh=t1.sqspbh ").append(
								" and t2.jmsqsrq<= ").append(
								StringUtils
										.getSQLDate2(nsrJbxxBO.getSkssjsrq()))
						.append(" and t2.jmszzrq>= ").append(
								StringUtils
										.getSQLDate2(nsrJbxxBO.getSkssjsrq()))
						.append(" and t2.szsmdm='30' ").append(
								" order by t1.cjrq desc ");

				System.out.println("企业所得税-查询减免类型，是否有减免资格SQL");
				System.out.println(bf.toString());
				st = conn.createStatement();
				rs = st.executeQuery(bf.toString());

				if (rs.next()) {
					jm_type = true;
					sqspbh = rs.getString("sqspbh");
				}

				System.out.println("申请审批表号:" + sqspbh);

				if (!jm_type) {
					jm_type = false;
				}
				// if (jm_type) {
				nsrJbxxBO.setSqspbh(sqspbh); // 申请审批表号
				// }

			} else if ((CodeConstant.JMLX9010.equals(nsrJbxxBO.getJmlxdm()))) {

				bf.delete(0, bf.length());

				bf
						.append(
								" select t1.sqspbh as sqspbh,t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
						.append(" where t1.jsjdm= ").append(
								StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
						.append(" and t2.sqspbh=t1.sqspbh ").append(
								" and t2.szsmdm='30' ").append(
								" and  t1.jmslbdm= ").append(
								StringUtils.getSQLStr(nsrJbxxBO.getJmlxdm()))
						.append(" order by t1.cjrq desc ");

				System.out.println("企业所得税-查询减免类型，是否有减免资格SQL");
				System.out.println(bf.toString());
				st = conn.createStatement();
				rs = st.executeQuery(bf.toString());

				if (rs.next()) {
					jm_type = true;
					sqspbh = rs.getString("sqspbh");
				}

				System.out.println("申请审批表号:" + sqspbh);

				// if (jm_type) {
				nsrJbxxBO.setSqspbh(sqspbh); // 申请审批表号
				// }

				// 2007-04-16 如果高新技术企业做了认定则，该户也允许有高新技术企业减免

				java.util.Date time = new Timestamp(System.currentTimeMillis());// 匹配税费接口的rq参数，无意义

				Map skssrq = new HashMap();
				skssrq = Skssrq.yearSkssrq(time);
				// 取得税款所属开始和结束日期
				Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				System.out.println("税费入口参数，计算机代码=" + nsrJbxxBO.getJsjdm());
				System.out.println("税费入口参数，申报日期=" + time);
				System.out.println("税费入口参数，税款所属开始日期=" + skssksrq);
				System.out.println("税费入口参数，税款所属结束日期=" + skssjsrq);

				// 调用税费接口获得企业的征收方式
				ServiceProxy proxy = new ServiceProxy();
				QysdsSet sdsIsnfo = proxy.getQysdsInfo(nsrJbxxBO.getJsjdm(),
						time, skssksrq, skssjsrq, "01");


				System.out.println("取得的税费接口信息sdsInfo="
						+ sdsIsnfo);
				
				// 判断征收方式
				if (sdsIsnfo != null) {
					// 高新技术企业认定日期 /
					Date gxqyrdrq = sdsIsnfo.getGxjsqy();

					System.out.println("取得的税费接口信息gxqyrdrq="
							+ gxqyrdrq);

					if (gxqyrdrq != null) {

						jm_type = true;

					}
				}

				// 2007-04-16 如果高新技术企业做了认定则，该户也允许有高新技术企业减免

				if (!jm_type) {
					jm_type = false;
				}

				if (!jm_type) {
					throw new ApplicationException(
							"选择的减免类型与税费信息中核定的减免类型不一致，该纳税人不享受此减免类型!！");
				}

			} else {

				bf.delete(0, bf.length());

				bf
						.append(
								" select t1.sqspbh as sqspbh, t1.jmslbdm as jmslbdm from spdb.sp_jl_ybnsdwjmsp t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
						.append(" where t1.jsjdm= ").append(
								StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
						.append(" and t2.sqspbh=t1.sqspbh ").append(
								" and t2.jmsqsrq<= ").append(
								StringUtils
										.getSQLDate2(nsrJbxxBO.getSkssjsrq()))
						.append(" and t2.jmszzrq>= ").append(
								StringUtils
										.getSQLDate2(nsrJbxxBO.getSkssjsrq()))
						.append(" and t2.szsmdm='30' ").append(
								" and  t1.jmslbdm= ").append(
								StringUtils.getSQLStr(nsrJbxxBO.getJmlxdm()))
						.append(" order by t1.cjrq desc ");

				System.out.println("企业所得税-基本信息插入历史数据SQL");
				System.out.println(bf.toString());

				rs = st.executeQuery(bf.toString());

				if (rs.next()) {
					jm_type = true;
					sqspbh = rs.getString("sqspbh");
				}

				System.out.println("申请审批表号:" + sqspbh);

				if (!jm_type) {
					jm_type = false;
				}

				// if (jm_type) {
				nsrJbxxBO.setSqspbh(sqspbh); // 申请审批表号
				// }

				if (!jm_type) {
					throw new ApplicationException(
							"选择的减免类型与税费信息中核定的减免类型不一致，该纳税人不享受此减免类型!！");
				}

			}
			*/

			/**
			 * 插入历史数据
			 */

			bf.delete(0, bf.length());

			bf
					.append(" insert into sbdb.sb_jl_qysds_nsrjbxxb_his ")
					.append(
							" (XH,NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,DL,CYL,DE,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2,ZCZE,ZCZBJE) ")
					.append(" (select ")
					.append(TinyTools.getXh(nsrJbxxBO.getJsjdm()))
					.append(",")
					.append(
							" NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,DL,CYL,DE,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2,ZCZE,ZCZBJE ")
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ").append(
							" where  t1.nsrjsjdm = ").append(
							StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
					.append(" and t1.nd = ").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSknd())).append(
							" ) ");

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
							StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
					.append(" and t1.nd = ").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSknd()));

			System.out.println("企业所得税-基本信息删除SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			// 补充form中的工资管理形式的名称 财务会计制度的名称及相对应的报表描述符
			if (CodeConstant.CWKJZD01.equals(nsrJbxxBO.getCwkjzddm())) {

				nsrJbxxBO.setCwkjzdmc(CodeConstant.CWKJZDMC01);

				nsrJbxxBO.setBbmsf(CodeConstant.BBMSF10_2008);
				
				// 补充form中的工资管理形式的名称
				/*
				if (CodeConstant.GZGLXS01.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC01);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF10);

				} else if (CodeConstant.GZGLXS03
						.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC03);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF11);

				}
				*/

			} else if (CodeConstant.CWKJZD02.equals(nsrJbxxBO.getCwkjzddm())) {

				nsrJbxxBO.setCwkjzdmc(CodeConstant.CWKJZDMC02);

				nsrJbxxBO.setBbmsf(CodeConstant.BBMSF20_2008);

				// 补充form中的工资管理形式的名称
				/*
				if (CodeConstant.GZGLXS01.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC01);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF20);

				} else if (CodeConstant.GZGLXS03
						.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC03);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF21);

				}
				*/

			} else if (CodeConstant.CWKJZD03.equals(nsrJbxxBO.getCwkjzddm())) {

				nsrJbxxBO.setCwkjzdmc(CodeConstant.CWKJZDMC03);

				nsrJbxxBO.setBbmsf(CodeConstant.BBMSF30_2008);

				// 补充form中的工资管理形式的名称
				/*
				if (CodeConstant.GZGLXS01.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC01);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF30);

				} else if (CodeConstant.GZGLXS03
						.equals(nsrJbxxBO.getGzglxsdm())) {

					nsrJbxxBO.setGzglxsmc(CodeConstant.GZGLXSMC03);
					nsrJbxxBO.setBbmsf(CodeConstant.BBMSF31);

				}
				*/

			}

			//String sybs=FriendHelper.getNsrSybs(djjbsj);
			String sybs = nsrJbxxdjbVO.getNsrjbxx().getSybs();
			System.out.println("保存时保存的税源标识--增加征管范围鉴定模块之后"+sybs);
			if(Integer.valueOf(nsrJbxxBO.getSknd()).intValue()>2012)
			{
				/**
				 * 如果税源标识为总的需填写分配表
				 * 添加表17 
				 * add by wangcy  2013-12-04
				 */
				if(CodeConstant.CODE_QYSDS_ZGFWJD_KSSZJG.equals(sybs)){
					nsrJbxxBO.setBbmsf(nsrJbxxBO.getBbmsf()+CodeConstant.BBMSF17_2012);
				}
				/**
				 * 如果税源标识为分的需填写分配表
				 * 去掉所有表
				 * add by wangcy  2013-12-04
				 */
				if(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG.equals(sybs)){
					nsrJbxxBO.setBbmsf("");
				}
			}

			// 补充form中的减免企业所得税的类型的名称
			/*
			if ("".equals(nsrJbxxBO.getJmlxdm())
					|| CodeConstant.JMLXNO.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMCNO);

			} else if (CodeConstant.JMLXOTHER.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMCOTHER);

			} else if (CodeConstant.JMLX9010.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMC9010);

			} else if (CodeConstant.JMLX9020.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMC9020);

			} else if (CodeConstant.JMLX9030.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMC9030);

			} else if (CodeConstant.JMLX9090.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMC9090);

			} else if (CodeConstant.JMLX9070.equals(nsrJbxxBO.getJmlxdm())) {

				nsrJbxxBO.setJmlxmc(CodeConstant.JMLXMC9070);

			}
			*/

			/**
			 * 插入修改的数据
			 */

			bf.delete(0, bf.length());

			bf
					.append(" insert into sbdb.sb_jl_qysds_nsrjbxxb ")
					.append(
							" (NSRJSJDM,NSRSBH,NSRMC,VERSION,ND,SBND,SQSPBH,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,ZSFS,ZSFSMC,CKZD,CKZDMC,GZGLXS,GZGLXSMC,JMLX,JMLXMC,JWSDDK,JWSDDKMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,SKSSSQQ,SKSSSQZ,REMARK1,REMARK2,ZCZE,ZCZBJE) ")
					// 计算机代码
					.append(" values(").append(
							StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
					// 纳税人识别号
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getNsrsbh()))
					// 纳税人名称
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getNsrmc()))
					// 版本号
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getVersion()))
					// 税款年度
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSknd()))
					// 申报年度
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSbnd()))
					// 申请审批编号
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSqspbh()))
					// 所属经济类型代码
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSsjjlxdm()))
					// 所属经济类型名称
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSsjjlxmc()))
					// 联系电话
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getLxdh()))
					// 经营地址
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getJydz()))
					// 所属行业代码
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSshydm()))
					// 所属行业名称
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSshymc()))
					// 征收方式代码
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getZsfsdm()))
					// 征收方式代码
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getZsfsmc()))
					// 财会制度代码
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getCwkjzddm()))
					// 财会制度名称
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getCwkjzdmc()))
					// 工资管理形式代码
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getGzglxsdm()))
					// 工资管理形式名称
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getGzglxsmc()))
					// 减免类型代码
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getJmlxdm()))
					// 减免类型名称
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getJmlxmc()))
					// 境外所得已纳税款抵扣方法
					.append(",").append("''")
					// 境外所得已纳税款抵扣方法名称
					.append(",").append("''")
					// 税务机关组织机构代码
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSwjgzzjgdm()))
					// 税务机关组织机构名称
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getSwjgzzjgmc()))
					// 创建人
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getCjr()))
					// 创建时间
					.append(",").append(
							StringUtils.getSQLDate2(nsrJbxxBO.getCjrq()))
					// 录入人
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getLrr()))
					// 录入时间
					.append(",").append(
							StringUtils.getSQLDate2(nsrJbxxBO.getLrrq()))
					// 系统级别
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getXtjb()))
					// 报表描述符
					.append(",").append(
							StringUtils.getSQLStr(nsrJbxxBO.getBbmsf()))
					// 税款所属时期起
					.append(",").append(
							StringUtils.getSQLDate2(nsrJbxxBO.getSkssksrq()))
					// 税款所属时期止
					.append(",").append(
							StringUtils.getSQLDate2(nsrJbxxBO.getSkssjsrq()))
					// 备注1
					.append(",").append("''")
					// 备注2
					.append(",").append("''")
//,ZCZBJE,ZCJE
					.append(",").append(StringUtils.getSQLStr(nsrJbxxBO.getZcze()))
                    .append(",").append(StringUtils.getSQLStr(nsrJbxxBO.getZczbje()))
					.append(") ");

			System.out.println("企业所得税-基本信息插入SQL\n");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			/**
			 * TODO 如果会计制度重新修改了，需要删除对应的申报表
			 */
			if (!(nsrJbxxBO.getCwkjzddm().equals(nsrJbxxBO.getCwkjzddm_old()))) {

				QysdsReportsDeclare qd = new QysdsReportsDeclare();
				qd = this.setQysdsReport(nsrJbxxBO);

				// 企业所得税报表内单表申明对象
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();

				// 原选择为企业（小企业）会计制度
				if (CodeConstant.CWKJZD01.equals(nsrJbxxBO.getCwkjzddm_old())) {
					// 清除附表一1
					table.setTableId(CodeConstant.TABLE_ID_FB1_1_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

					// 清除附表二1
					table.setTableId(CodeConstant.TABLE_ID_FB2_1_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

				}

				// 原选择为金融企业会计制度
				if (CodeConstant.CWKJZD02.equals(nsrJbxxBO.getCwkjzddm_old())) {
					// 清除附表一2
					table.setTableId(CodeConstant.TABLE_ID_FB1_2_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

					// 清除附表二2
					table.setTableId(CodeConstant.TABLE_ID_FB2_2_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

				}

				// 原选择为事业单位、社会团体、民办非企业单位会计制度
				if (CodeConstant.CWKJZD03.equals(nsrJbxxBO.getCwkjzddm_old())) {
					// 清除附表一3
					table.setTableId(CodeConstant.TABLE_ID_FB1_3_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

					// 清除附表二3
					table.setTableId(CodeConstant.TABLE_ID_FB2_3_2008);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

				}

			}

			
			System.out.println("税款年度: "+nsrJbxxBO.getSknd());
			/**
			 * 如果发生变化删除所有的年报数据 且税款年度大于2012(保持原来逻辑不变)
			 * add by wangcy 2013-12-07
			 */
			if(Integer.valueOf(nsrJbxxBO.getSknd()).intValue()>2012){
				QysdsUtil qysdsUtil=new QysdsUtil();
				if(qysdsUtil.DoQueryData(nsrJbxxBO).equals("1") && !bbmsf_old.equals(nsrJbxxBO.getBbmsf())){
					System.out.println("税款年度"+nsrJbxxBO.getSknd() +"发生变化,删除当前年度数据!");
					QysdsReportsDeclare qd = new QysdsReportsDeclare();
					qd = this.setQysdsReport(nsrJbxxBO);
					
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
	
					/**
					 * 根据客户的要求 如有变化将数据进行删除
					 */
					
					bf.delete(0, bf.length());
					bf.append(" delete ")
					.append(" from sbdb.sb_jl_qysds_nsrjbxxb t1 ")
					.append(" where  t1.nsrjsjdm = ").append(StringUtils.getSQLStr(nsrJbxxBO.getJsjdm()))
					.append(" and t1.nd = ").append(StringUtils.getSQLStr(nsrJbxxBO.getSknd()));

					System.out.println("企业所得税-基本信息删除SQL");
					System.out.println(bf.toString());

					st.execute(bf.toString());
					//删除标识 不在新建字段 用999在进行标识  
					nsrJbxxdjbVO.getNsrjbxx().setQueryFlag("999");
					
//					//删除企业所得税年报
//					table.setTableId(CodeConstant.TABLE_ID_CZZSSDSNB_2012);
//					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
//					// set table
//					qd.getTableContentList().put(table.getTableId(), table);
//					// 调用delete方法进行数据删除
//					iApp.deleteSingleTable(qd);
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
			 * TODO 如果工资管理形式重新修改了，需要删除对应的申报表
			 */
			/*if (!(nsrJbxxBO.getGzglxsdm().equals(nsrJbxxBO.getGzglxsdm_old()))) {

				QysdsReportsDeclare qd = new QysdsReportsDeclare();
				qd = this.setQysdsReport(nsrJbxxBO);
				// 企业所得税报表内单表申明对象
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();

				// 原来选择非工效挂钩 (计税工资 全额扣除) 现在选择工效挂钩
				if ((CodeConstant.GZGLXS01.equals(nsrJbxxBO.getGzglxsdm_old()))
						&& (CodeConstant.GZGLXS03.equals(nsrJbxxBO
								.getGzglxsdm()))) {
					// 清除非工效的表 附表十二
					table.setTableId(CodeConstant.TABLE_ID_GZXJMXB_FGX);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

				}

				// 原来选择工效挂钩 现在选择非工效挂钩 (计税工资 全额扣除)
				if ((CodeConstant.GZGLXS01.equals(nsrJbxxBO.getGzglxsdm()))
						&& (CodeConstant.GZGLXS03.equals(nsrJbxxBO
								.getGzglxsdm_old()))) {
					// 清除工效的表 附表十二
					table.setTableId(CodeConstant.TABLE_ID_TABLE_ID_GZXJMXB_GX);
					table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
					// set table
					qd.getTableContentList().put(table.getTableId(), table);
					// 调用delete方法进行数据删除
					iApp.deleteSingleTable(qd);

				}

			}
			*/

			/**
			 * TODO 如果减免类型重新修改了，需要删除对应的申报表
			 */

			/*if (!(nsrJbxxBO.getJmlxdm().equals(nsrJbxxBO.getJmlxdm_old()))) {

			
				 //清除附表七减免表的数据，减免的数据需要重新录入
				 

				QysdsReportsDeclare qd = new QysdsReportsDeclare();
				qd = this.setQysdsReport(nsrJbxxBO);
				// 企业所得税报表内单表申明对象
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();

				// 清除附表七减免表的数据，减免的数据需要重新录入
				table.setTableId(CodeConstant.TABLE_ID_MSSDMXB);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				// set table
				qd.getTableContentList().put(table.getTableId(), table);
				// 调用delete方法进行数据删除
				iApp.deleteSingleTable(qd);

			}
			*/

			/**
			 * 重新设置原始值
			 */
			nsrJbxxBO.setCwkjzddm_old(nsrJbxxBO.getCwkjzddm());
			nsrJbxxBO.setGzglxsdm_old(nsrJbxxBO.getGzglxsdm());
			nsrJbxxBO.setJmlxdm_old(nsrJbxxBO.getJmlxdm());

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

			if (ud.getCaflag()) {

				System.out.println("===========签名开始==========");
				try {
					String ywid = nsrJbxxdjbVO.getNsrjbxx().getJsjdm()
							+ "+"
							+ DjStringUtil
									.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
					System.out.println("======ywid:" + ywid);
					dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
					System.out.println("===========签名结束==========");
				} catch (Exception ex) {
					System.out.println("===========签名失败==========");
					throw ExceptionUtil.getBaseException(ex);

				}
				retData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, nsrJbxxBO);// BO
				retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);// 电子原件
				retData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, nsrJbxxdjbVO);// VO

			}
			retData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, nsrJbxxdjbVO);// VO
		} catch (Exception e) {
			// 抛出异常
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		} finally {
			// 关闭数据库连接
			DBResource.destroyConnection(conn);
		}

		return retData;
	}

	/**
	 * 设置报表对象基本信息
	 * 
	 * 
	 * @param nsrJbxxBO
	 */
	public QysdsReportsDeclare setQysdsReport(NsrJbxxBO nsrJbxxBO) {

		QysdsReportsDeclare report = new QysdsReportsDeclare();
		/**
		 * 基本信息
		 */
		Jbxx jbxx = new Jbxx();

		/**
		 * 基本信息(JBXX)-计算机代码
		 */
		jbxx.setJsjdm(nsrJbxxBO.getJsjdm());
		/**
		 * 基本信息(JBXX)-纳税人名称
		 */
		jbxx.setNsrmc(nsrJbxxBO.getNsrmc());
		/**
		 * 基本信息(JBXX)-所属经济类型
		 */
		jbxx.setSsjjlx(nsrJbxxBO.getSsjjlxdm());
		/**
		 * 基本信息(JBXX)-联系电话
		 */
		jbxx.setLxdh(nsrJbxxBO.getLxdh());
		/**
		 * 基本信息(JBXX)-所属行业
		 */
		jbxx.setSshy(nsrJbxxBO.getSshydm());
		/**
		 * 基本信息(JBXX)-征收方式
		 */
		jbxx.setZsfs(nsrJbxxBO.getZsfsdm());
		/**
		 * 基本信息(JBXX)-财会制度
		 */
		jbxx.setCkzd(nsrJbxxBO.getCwkjzddm());
		/**
		 * 基本信息(JBXX)-工资管理形式
		 */
		jbxx.setGzglxs(nsrJbxxBO.getGzglxsdm());
		/**
		 * 基本信息(JBXX)-减免类型
		 */
		jbxx.setJmlx(nsrJbxxBO.getJmlxdm());

		report.setJbxx(jbxx);

		/**
		 * 应用编号
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYSDS);
		/**
		 * 版本号
		 */
		report.setVersion(nsrJbxxBO.getVersion());
		/**
		 * 纳税人计算机代码
		 */
		report.setNsrjsjdm(nsrJbxxBO.getJsjdm());
		/**
		 * 纳税人名称
		 */
		report.setNsrmc(nsrJbxxBO.getNsrmc());
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
		if (nsrJbxxBO.getSkssksrq() != null
				&& !nsrJbxxBO.getSkssksrq().equals("")) {
			report.setSkssksrq(new Date(TinyTools.stringToDate(
					nsrJbxxBO.getSkssksrq(), "yyyyMMdd").getTime()));
		}
		/**
		 * 税款所属结束日期
		 */
		if (nsrJbxxBO.getSkssjsrq() != null
				&& !nsrJbxxBO.getSkssjsrq().equals("")) {
			report.setSkssjsrq(new Date(TinyTools.stringToDate(
					nsrJbxxBO.getSkssjsrq(), "yyyyMMdd").getTime()));
		}
		/**
		 * 申报日期
		 */
		if (nsrJbxxBO.getLrrq() != null && !nsrJbxxBO.getLrrq().equals("")) {
			report.setSbrq(new Date(TinyTools.stringToDate(nsrJbxxBO.getLrrq(),
					"yyyyMMdd").getTime()));
		}
		/**
		 * 税款年度
		 */
		report.setSknd(nsrJbxxBO.getSknd());
		/**
		 * 税务机关组织机构代码
		 */
		report.setSwjgzzjgdm(nsrJbxxBO.getSwjgzzjgdm());
		/**
		 * 区县代码
		 */
		report.setQxdm(nsrJbxxBO.getSwjgzzjgdm().substring(2, 4));
		/**
		 * 录入人
		 */
		report.setLrr(nsrJbxxBO.getLrr());
		/**
		 * 录入日期
		 */
		if (nsrJbxxBO.getLrrq() != null && !nsrJbxxBO.getLrrq().equals("")) {
			report.setLrrq(new Date(TinyTools.stringToDate(nsrJbxxBO.getLrrq(),
					"yyyyMMdd").getTime()));
		}
		/**
		 * 创建人
		 */
		report.setCjr(nsrJbxxBO.getLrr());
		/**
		 * 创建日期
		 */
		if (nsrJbxxBO.getCjrq() != null && !nsrJbxxBO.getCjrq().equals("")) {
			report.setCjrq(new Date(TinyTools.stringToDate(nsrJbxxBO.getCjrq(),
					"yyyyMMdd").getTime()));
		}

		return report;
	}
}
