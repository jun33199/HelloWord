package com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.processor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.syax.creports.bo.qyqssds.QyqssdsBaJbxx;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.CAConstants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.QyqssdsConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.bo.NsrJbxxBo;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.xmlvo.QyqssdsNsrJbxxVo;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsBaProcessor implements Processor {

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
			// 删除
			case QysdsksActionConstant.INT_ACTION_DELETE: {
				doDelete(vo);
				return null;
			}

			default:
				throw new SystemException("no such method");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * 删除企业所得税数据
	 * 
	 * @param data
	 *            Map
	 * @throws BaseException
	 */
	private void doDelete(VOPackage vop) throws BaseException {

		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();

		Connection conn = null;
//		PreparedStatement ps = null;
		Statement st = null;
//		ResultSet rs = null;
		// sql buffer
		StringBuffer bf = new StringBuffer();
		NsrJbxxBo nsrJbxxBo = new NsrJbxxBo();
		nsrJbxxBo = (NsrJbxxBo) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ); // BO
		try {
			conn = DBResource.getConnection();
			st = conn.createStatement();

			// 获取报表处理数据库接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			qd = this.setQysdsReport(nsrJbxxBo);

			// 循环删除客户端填写的所有表，外加新添加的分配表，
			for (int i = 0; i < CodeConstant.QYQSSDS_TABLE_ID_ALL.length; i++) {
				// 企业所得税报表内单表申明对象
				QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
				table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_ALL[i]);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
				// set table
				qd.setTableContentList(new HashMap());
				qd.getTableContentList().put(table.getTableId(), table);
				// 调用delete方法进行数据删除
				iApp.deleteSingleTable(qd);
				iApp.updateCheckStatus(qd, "");
			}
			/**
			 * 插入历史数据
			 */

			bf.delete(0, bf.length());

			bf.append(" insert into SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_his ")
					.append(" (XH,NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					.append(" (select ")
					.append(TinyTools.getXh(nsrJbxxBo.getJsjdm()))
					.append(",")
					.append(" NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJsjdm()))
					.append(" ) ");

			System.out.println("企业清算所得税-基本信息插入历史数据SQL");
			System.out.println(bf.toString());
			st.execute(bf.toString());

			/**
			 * 删除数据
			 */

			bf.delete(0, bf.length());

			bf.append(" delete ")
			  .append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
			  .append(" where  t1.nsrjsjdm = ")
			  .append(StringUtils.getSQLStr(nsrJbxxBo.getJsjdm()));

			System.out.println("企业清算所得税-基本信息删除SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());
			if(st!=null){
				st.close();
			}
		} catch (Exception e) {
			// 抛出异常
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e, "删除企业清算所得税基本信息表失败");
			// throw new ApplicationException(e.getMessage());
		} finally {
			// 关闭数据库连接
			DBResource.destroyConnection(conn);
		}
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
		Statement st = null;
		// sql buffer
		StringBuffer bf = new StringBuffer();
		QyqssdsNsrJbxxVo qyqssdsNsrJbxxVo = new QyqssdsNsrJbxxVo();
		NsrJbxxBo nsrJbxxBo = new NsrJbxxBo();

		nsrJbxxBo = (NsrJbxxBo) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ); // BO
		nsrJbxxBo.setSqlxdm("0");
		DzyjsjVO dzyj = (DzyjsjVO) data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);// 电子原件

		qyqssdsNsrJbxxVo = (QyqssdsNsrJbxxVo) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);// VO

		try {

			conn = DBResource.getConnection();
			st = conn.createStatement();

			/**
			 * 插入历史数据
			 */
			bf.delete(0, bf.length());

			bf.append(" insert into SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_his ")
					.append(" (XH,NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					.append(" (select ")
					.append(TinyTools.getXh(nsrJbxxBo.getJsjdm()))
					.append(",")
					.append(" NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJsjdm()))
					.append(" ) ");

			System.out.println("企业清算所得税-基本信息插入历史数据SQL");
			System.out.println(bf.toString());
			st.execute(bf.toString());

			/**
			 * 删除数据
			 */

			bf.delete(0, bf.length());

			bf.append(" delete ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJsjdm()));

			System.out.println("企业清算所得税-基本信息删除SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			/**
			 * 插入修改的数据
			 */

			bf.delete(0, bf.length());
			bf.append(" insert into SBDB.SB_JL_QYQSSDSBA_NSRJBXXB ")
					.append(" (NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX,SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					// 计算机代码
					.append(" values(")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJsjdm()))
					// 纳税人识别号
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getNsrsbh()))
					// 纳税人名称
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getNsrmc()))
					// 版本号
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getVersion()))
					// 所属经济类型代码
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSsjjlxdm()))
					// 所属经济类型名称
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSsjjlxmc()))
					// 联系电话
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getLxdh()))
					// 经营地址
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJydz()))
					// 所属行业代码
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSshydm()))
					// 所属行业名称
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSshymc()))
					// 税务机关组织机构代码
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSwjgzzjgdm()))
					// 税务机关组织机构名称
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSwjgzzjgmc()))
					// 创建人
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getCjr()))
					// 创建日期
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getCjrq()))
					// 录入人
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getLrr()))
					// 录入日期
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getLrrq()))
					// 系统级别
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getXtjb()))
					// 报表描述符
					.append(",'")
					.append(CodeConstant.QYQSSDS_TABLE_STR)
					// 备注1
					.append("',")
					.append("''")
					// 备注2
					.append(",")
					.append("''")
					// 清算联络人员
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getQsllry()))
					// 填报日期
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getTbrq()))
					// 清算备案开始日期
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getQsbaksrq()))

					// 清算备案结束日期
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getQsbajsrq()))
					// 备案审核状态标识
					.append(",")
					.append("1")
					// 备案审核通过日期
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getBaShtgrq()))
					// 申报审核状态标识
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSbShztbs()))
					// 申报审核通过日期
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getSbShtgrq()))
					// 经营期限届满
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getJyqxjm()))
					// 东会决议解散
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getGdjyjs()))
					// 依法吊销关闭
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getYfdxgb()))
					// 依法宣告破产
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getYfxgpc()))
					// 依法规定清算
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getYfgdqs()))

					// 其他原因
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getQtyy()))
					// 申请类型代码默认为0
					.append(",")
					.append(StringUtils.getSQLStr(nsrJbxxBo.getSqlxdm()))
					// 清算申报开始日期
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getQssbksrq()))
					// 清算申报结束日期
					.append(",")
					.append(StringUtils.getSQLDate2(nsrJbxxBo.getQssbjsrq()))
					.append(") ");

			System.out.println("企业清算所得税-基本信息插入SQL\n");
			System.out.println(bf.toString());

			st.execute(bf.toString());

			 if (st != null) {
			 st.close();
			 }

			if (ud.getCaflag()) {

				System.out.println("===========签名开始==========");
				try {
					String ywid = qyqssdsNsrJbxxVo.getNsrjbxx().getJsjdm()
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
				retData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, nsrJbxxBo);// BO
				retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);// 电子原件
				retData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO,
						qyqssdsNsrJbxxVo);// VO

			}
			retData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qyqssdsNsrJbxxVo);// VO

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
	 * 查询纳税人基本信息
	 * 
	 * @param pData
	 *            Map
	 * @return Map
	 * @throws BaseException
	 */
	private Object doQuery(Map pData) throws BaseException {

		
		NsrJbxxBo nsrJbxxBO = new NsrJbxxBo();
		Connection conn = null;
		try {
			// 计算机代码
			String jsjdm = null;
			// 当前日期
			Timestamp curDate = null;
			// 税务登记基本数据值对象
			SWDJJBSJ djjbsj = (SWDJJBSJ) pData
					.get(QyqssdsConstant.OBJECT_KEY_DJSJ);
			jsjdm = (String) pData.get(QyqssdsConstant.STRING_KEY_JSJDM);
			curDate = (Timestamp) pData.get(QyqssdsConstant.STRING_KEY_DATE);// 申报日期

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(curDate);
			nsrJbxxBO.setJsjdm(jsjdm);// 纳税人计算机代码

			nsrJbxxBO.setNsrmc(djjbsj.getNsrmc());// 纳税人名称

			nsrJbxxBO.setNsrsbh(djjbsj.getSwdjzh());// 纳税人识别号

			nsrJbxxBO.setSsjjlxdm(djjbsj.getDjzclxdm()); // 所属经济类型-登记注册类型代码
			nsrJbxxBO.setSsjjlxmc(djjbsj.getDjzclxmc());// 所属经济类型-登记注册类型名称
			//nsrJbxxBO.setLxdh(djjbsj.getZcdzlxdh()); // 注册地址联系电话
			nsrJbxxBO.setJydz(djjbsj.getJydz());// 经营地址
			nsrJbxxBO.setSshydm(djjbsj.getGjbzhydm());// 所属行业代码
			nsrJbxxBO.setSshymc(djjbsj.getGjbzhymc());// 所属行业代码
			nsrJbxxBO.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm()); // 税务机关组织机构代码
			nsrJbxxBO.setSwjgzzjgmc(djjbsj.getSwjgzzjgmc()); // 税务机关组织机构名称
			nsrJbxxBO.setLrr(jsjdm);// 录入人
			nsrJbxxBO.setCjr(jsjdm);// 创建人
			nsrJbxxBO.setCjrq(curDate.toString().substring(0, 11));// 创建日期
			nsrJbxxBO.setLrrq(curDate.toString().substring(0, 11));// 录入日期
			nsrJbxxBO.setQsbaksrq(curDate.toString().substring(0, 11));// 默认当前日期

			nsrJbxxBO.setVersion(QyqssdsConstant.REPORT_VERSION_QYQSSDS_NSRJBXXB);

			// 系统级别
			nsrJbxxBO.setXtjb(QyqssdsConstant.QSSDS_1);

			conn = DBResource.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			StringBuffer bf = new StringBuffer();
			// select
			bf.append(" select ")
					// 查询字段
					.append(" NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX, ")
					// 子查询-根据登记注册类型代码表查询登记注册类型名称
					// 所属经济类型-登记注册类型
					.append(" (SELECT DJZCLXMC FROM DMDB.DJ_DM_DJZCLX T2 WHERE T2.DJZCLXDM=T1.SSJJLX) AS SSJJLXMC, ")
					.append(" LXDH,JYDZ,SSHY, ")
					// 子查询-根据所属行业代码表查询所属行业名称
					.append(" (SELECT GJBZHYMC FROM DMDB.GY_DM_GJBZHY T3 WHERE T3.GJBZHYDM=T1.SSHY) AS SSHYMC,")
					.append(" SWJGZZJGDM, ")
					// 子查询-根据税务机关组织代码查询税务机关组织机构名称
					.append(" (SELECT SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T5 WHERE T5.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC, ")
					.append(" CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					// from
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					// where
					.append(" where  t1.nsrjsjdm = ? ");

			System.out.println("企业清算所得税-基本信息查询SQL");
			System.out.println(bf.toString());

			ps = conn.prepareStatement(bf.toString());
			System.out.println("1-" + nsrJbxxBO.getJsjdm());
			ps.setString(1, nsrJbxxBO.getJsjdm());
			rs = ps.executeQuery();

			if (rs.next()) {

				// 纳税人计算机代码
				nsrJbxxBO.setJsjdm(rs.getString("NSRJSJDM"));
				// 纳税人识别号－税务登记证号
				nsrJbxxBO.setNsrsbh(rs.getString("NSRSBH"));
				// 纳税人名称
				nsrJbxxBO.setNsrmc(rs.getString("NSRMC"));
				// 税款年度
				nsrJbxxBO.setVersion(rs.getString("VERSION"));
				// 所属经济类型
				nsrJbxxBO.setSsjjlxdm(rs.getString("SSJJLX"));
				// 所属经济类型
				nsrJbxxBO.setSsjjlxmc(rs.getString("SSJJLXMC"));
				// 联系电话
				nsrJbxxBO.setLxdh(rs.getString("LXDH"));
				// 经营地址
				nsrJbxxBO.setJydz(rs.getString("JYDZ"));
				// 所属行业
				nsrJbxxBO.setSshydm(rs.getString("SSHY"));
				// 所属行业
				nsrJbxxBO.setSshymc(rs.getString("SSHYMC"));
				// 税务机关组织机构代码
				nsrJbxxBO.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				// 税务机关组织机构代码
				nsrJbxxBO.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
				nsrJbxxBO.setCjr(rs.getString("CJR"));
				if (rs.getTimestamp("CJSJ") != null) {
					nsrJbxxBO.setCjrq(StringUtils.getStrFromDate(rs
							.getTimestamp("CJSJ")));
				}
				nsrJbxxBO.setCjr(rs.getString("LRR"));
				if (rs.getTimestamp("LRSJ") != null) {
					nsrJbxxBO.setLrrq(StringUtils.getStrFromDate(rs
							.getTimestamp("LRSJ")));
				}

				nsrJbxxBO.setXtjb(rs.getString("XTJB"));
				nsrJbxxBO.setBbmsf(rs.getString("BBMSF"));
				nsrJbxxBO.setQsllry(rs.getString("QSLLRY"));
				if (rs.getTimestamp("TBRQ") != null) {
					nsrJbxxBO.setTbrq(StringUtils.getStrFromDate(rs
							.getTimestamp("TBRQ")));
				}
				if (rs.getTimestamp("QSBAKSRQ") != null) {
					nsrJbxxBO.setQsbaksrq(StringUtils.getStrFromDate(rs
							.getTimestamp("QSBAKSRQ")));
				}
				if (rs.getTimestamp("QSBAJSRQ") != null) {
					nsrJbxxBO.setQsbajsrq(StringUtils.getStrFromDate(rs
							.getTimestamp("QSBAJSRQ")));
				}
				nsrJbxxBO.setBaShztbs(rs.getString("BASHZTBS"));
				if (rs.getTimestamp("BASHTGRQ") != null) {
					nsrJbxxBO.setBaShtgrq(StringUtils.getStrFromDate(rs
							.getTimestamp("BASHTGRQ")));
				}
				nsrJbxxBO.setSbShztbs(rs.getString("SBSHZTBS"));
				if (rs.getTimestamp("SBSHTGRQ") != null) {
					nsrJbxxBO.setSbShtgrq(StringUtils.getStrFromDate(rs
							.getTimestamp("SBSHTGRQ")));
				}
				nsrJbxxBO.setJyqxjm(rs.getString("JYQXJM"));
				nsrJbxxBO.setGdjyjs(rs.getString("GDJYJS"));
				nsrJbxxBO.setYfdxgb(rs.getString("YFDXGB"));
				nsrJbxxBO.setYfxgpc(rs.getString("YFXGPC"));
				nsrJbxxBO.setYfgdqs(rs.getString("YFGDQS"));
				nsrJbxxBO.setQtyy(rs.getString("QTYY"));
				nsrJbxxBO.setSqlxdm(rs.getString("SQLXDM"));
				if (rs.getTimestamp("QSSBKSRQ") != null) {
					nsrJbxxBO.setQssbksrq(StringUtils.getStrFromDate(rs
							.getTimestamp("QSSBKSRQ")));
				}
				if (rs.getTimestamp("QSSBJSRQ") != null) {
					nsrJbxxBO.setQssbjsrq(StringUtils.getStrFromDate(rs
							.getTimestamp("QSSBJSRQ")));
				}

			}

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

			if (nsrJbxxBO.getBaShztbs() == null
					|| nsrJbxxBO.getBaShztbs().equals("")) {
				nsrJbxxBO.setBaShztMessage("未进行提交");
			} else if ("1".equals(nsrJbxxBO.getBaShztbs())) {
				nsrJbxxBO.setBaShztMessage("已提交未审核");
			} else if ("2".equals(nsrJbxxBO.getBaShztbs())) {
				nsrJbxxBO.setBaShztMessage("审核已通过");
			} else if ("3".equals(nsrJbxxBO.getBaShztbs())) {
				nsrJbxxBO.setBaShztMessage("您企业的清算所得税备案申请被驳回，请修改后重新上传或者进行删除");
			} else if ("4".equals(nsrJbxxBO.getBaShztbs())) {
				nsrJbxxBO.setBaShztMessage("撤销");
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "查询企业清算所得税基本信息数据失败");
		}finally{
			DBResource.destroyConnection(conn);
		}
		return nsrJbxxBO;
	}
	/**
	 * 设置报表对象基本信息
	 * 
	 * 
	 * @param nsrJbxxBO
	 */
	public QyqssdsReportsDeclare setQysdsReport(NsrJbxxBo form) {

		QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
		/**
		 * 基本信息
		 */
		QyqssdsBaJbxx jbxx = new QyqssdsBaJbxx();

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

		// /**
		// * 基本信息(JBXX)-工资管理形式
		// */
		// jbxx.setGzglxs("");
		// /**
		// * 基本信息(JBXX)-减免类型
		// */
		// jbxx.setJmlx("");

		// jbxx.setBbmsf(GetJbxxBbmsf(form));
		jbxx.setBbmsf(form.getBbmsf());
		// jbxx.setBbmsf("0101,0102,0103,0104");

		report.setJbxx(jbxx);

		/**
		 * 应用编号
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYQSSDS);
		/**
		 * 版本号 20140101
		 */
		report.setVersion(CodeConstant.QYQSSDS_VERSION_2014);
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
		report.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
		/**
		 * 期号
		 */
		report.setQh("1");
		/**
		 * 税款所属开始日期 改为 清算申报开始日期
		 */
		if (form.getQssbksrq() != null && !"".equals(form.getQssbksrq())) {
			report.setQssbksrq(new Date(TinyTools.stringToDate(
					form.getQssbksrq(), "yyyy-MM-dd").getTime()));

		}
		/**
		 * 税款所属结束日期 改为 清算申报结束日期
		 */
		if (form.getQssbjsrq() != null && !"".equals(form.getQssbjsrq())) {
			report.setQssbjsrq(new Date(TinyTools.stringToDate(
					form.getQssbjsrq(), "yyyy-MM-dd").getTime()));
		}
		/**
		 * 申报日期 改为清算填报当前日期
		 */
		if (form.getTbrq() != null && !"".equals(form.getTbrq())) {
			report.setSbrq(new Date(TinyTools.stringToDate(form.getTbrq(),
					"yyyyMMdd").getTime()));
		}

		/**
		 * 税款年度 为清算备案完成所在年度
		 */
		if (form.getQsbajsrq() != null && !"".equals(form.getQsbajsrq())) {
			report.setSknd(new Date(TinyTools.stringToDate(form.getQsbajsrq(),
					"yyyyMMdd").getTime()).toString().substring(0, 3));
		}
		/**
		 * 税务机关组织机构代码
		 */
		report.setSwjgzzjgdm(form.getSwjgzzjgdm());
		/**
		 * 税务计算机代码
		 */
		report.setSwjsjdm(form.getJsjdm());
		/**
		 * 区县代码
		 */
		report.setQxdm(form.getSwjgzzjgdm().substring(2, 4));
		/**
		 * 录入人
		 */
		report.setLrr(form.getLrr());

		/**
		 * 创建人
		 */
		report.setCjr(form.getLrr());

		return report;
	}
}
