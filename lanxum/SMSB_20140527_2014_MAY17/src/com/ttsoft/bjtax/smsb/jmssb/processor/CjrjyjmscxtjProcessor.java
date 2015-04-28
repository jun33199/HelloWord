package com.ttsoft.bjtax.smsb.jmssb.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.jmssb.web.CjrjyjmsInfo;
import com.ttsoft.bjtax.smsb.jmssb.web.CjrjyjmscxtjForm;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * Title: 北京地税综合管理系统
 * Description: 上门申报-补充申报资料录入-安置残疾人就业企业营业税减免税查询统计表
 * Copyright: Copyright (c) 2005
 * Company: SYAX
 * @author xinyy
 * @version 1.0
 */
public class CjrjyjmscxtjProcessor implements Processor {

	/**
	 * Processor Dispacher
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @throws BaseException
	 *             Excetion throwable
	 */
	public Object process(VOPackage vo) throws BaseException {
		switch (vo.getAction()) {
			case CodeConstant.SMSB_SHOWACTION:
				return doShow(vo);
			case CodeConstant.SMSB_QUERYACTION:
				return doQuery(vo);
			default:
				throw new ApplicationException("未找到符合条件的操作");
		}
	}

	/**
	 * 初始化方法
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doShow(VOPackage vo) throws BaseException {
		Debug.out("Call CjrjyjmscxtjProcessor_doShow......");
		// 申明句柄
		CjrjyjmscxtjForm cForm = null;
		UserData userData = null;
		SfDBAccess da = null;
		Connection conn = null;
		// 初始化VO数据对象
		try {
			userData = vo.getUserData();
			cForm = (CjrjyjmscxtjForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		
		// 开始业务
		try {
			// /初始化工具
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /根据userData的用户级别决定分局组织机构代码的值列表
			cForm.setZgswjgList(this.getZgswjgList(userData, da));
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return cForm;
	}
	
	/**
	 * 查询方法
	 * 
	 * @param vo
	 *            Value Object
	 * @return PageForm
	 * @exception BaseException
	 *                BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException {
		Debug.out("Call CjrjyjmscxtjProcessor_doQuery......");
		// 申明句柄
		CjrjyjmscxtjForm cForm = null;
		UserData userData = null;
		SfDBAccess da = null;
		Connection conn = null;
		// 满足条件的总数据集
		List allDataList = new ArrayList();
		// 获取合计数据集
		List totalList = new ArrayList();
		// 分页当前显示的数据集
		List showDataList = new ArrayList();
		
		// 初始化VO数据对象
		try {
			userData = vo.getUserData();
			cForm = (CjrjyjmscxtjForm) vo.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		
		// 开始业务
		try {
			// 初始化工具
			conn = SfDBResource.getConnection();
			da = new SfDBAccess(conn);
			// /根据userData的用户级别决定分局组织机构代码的值列表
			cForm.setZgswjgList(this.getZgswjgList(userData, da));
			// 执行查询
			allDataList = this.queryCjrjms(userData, cForm, da, "list");
			if(allDataList.size() > 0) {
				totalList = this.queryCjrjms(userData, cForm, da, "total");
				// 获取总页数数据
				cForm.setTotalpage(this.getPageTotalCount(allDataList.size()));
				// 整理查询结果
				showDataList = this.getPageDataList(allDataList, cForm);
				
				// 加入页面对象
				cForm.setNjzyysxeTotal(totalList.get(0).toString());
				cForm.setSjjzyyseTotal(totalList.get(1).toString());
				cForm.setAzcjzgrsTotal(totalList.get(2).toString());
				cForm.setDataList(showDataList);
				cForm.setMessage("");
			} else {
				cForm.setNjzyysxeTotal("0.00");
				cForm.setSjjzyyseTotal("0.00");
				cForm.setAzcjzgrsTotal("0");
				cForm.setMessage("没有满足查询条件的数据！");
			}
			// 清空数据
			allDataList = null;
			totalList = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return cForm;
	}
	
	/**
	 * 获取所有符合条件的税务所
	 * 
	 * @param userData
	 *            用户对象
	 * @param da
	 *            数据库操作对象
	 * @return 符合条件的税务所List
	 * @throws BaseException
	 *             异常
	 */
	private List getZgswjgList(UserData ud, SfDBAccess da) throws BaseException {
		// 句柄申明
		String sql = null;
		List tmpList = new ArrayList();
		String swjgzzjgdm = ud.getSsdwdm();
		ResultSet rs = null;
		String fjdm = ud.getSsdwdm().substring(0, 2) + "00";
		// 根据用户级别决定使用某一个sql
		String yhjb = ud.getYhjb();
		if (CodeConstants.JBDM_SWSJ.equals(yhjb)) { // 税务所级
			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' and swjgzzjgdm="
					+ SBStringUtils.getSQLStr(swjgzzjgdm);
		} else if (CodeConstants.JBDM_FJ.equals(yhjb)) { // 分局级
			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' and qxfjdm="
					+ SBStringUtils.getSQLStr(fjdm);
			tmpList.add(fjdm + "|全部");
		} else if (CodeConstants.JBDM_SJ.equals(yhjb)) { // 市局级
			sql = "select swjgzzjgdm||'|'||swjgzzjgmc from dmdb.gy_dm_swjgzzjg where ccbs='2' and zxbs='0' order by swjgzzjgdm asc";
			tmpList.add("0000|全部");
		}
		// 执行查询并整理数据
		try {
			rs = da.querySQL(sql);
			while (rs.next()) {
				tmpList.add(rs.getString(1));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		// 4.返回值
		return tmpList;
	}
	
	/**
	 * 获取要查询的SQL语句
	 * 
	 * @param cForm
	 * @param queryType 查询类别("list"-->查询遍历数据；"total"-->查询总计数据)
	 * @return
	 */
	private String getQuerySql(CjrjyjmscxtjForm cForm, String queryType) {
		Debug.out("Call CjrjyjmscxtjProcessor_getQuerySql......");
		// 句柄申明
		StringBuffer sqlString = new StringBuffer();
		String queryJsjdm = cForm.getQueryJsjdm();
		String queryQymc = cForm.getQueryQymc();
		String queryZgswjgdm = cForm.getQueryZgswjgdm().substring(0,
				cForm.getQueryZgswjgdm().indexOf("|"));
		String queryCxqsrq = cForm.getQueryCxqsrq();
		String queryCxjzrq = cForm.getQueryCxjzrq();
		// 如果截止日期为空，置为当前时间
		if("".equals(SBStringUtils.killNull(queryCxjzrq))) {
			queryCxjzrq = (new SimpleDateFormat("yyyyMMdd")).format(new Date()).toString();
		}
		
		// 基础查询语句
		if(queryType == "list") {
			sqlString.append("SELECT (SELECT A.JSJDM FROM DJDB.DJ_JL_JBSJ A WHERE A.JSJDM = B.JSJDM) JSJDM, (SELECT A.NSRMC FROM DJDB.DJ_JL_JBSJ A WHERE A.JSJDM = B.JSJDM) NSRMC, C.SQSPBH, B.NJYYSXE, TO_CHAR(B.JMKSRQ, 'YYYY-MM-DD'), TO_CHAR(B.JMJZRQ, 'YYYY-MM-DD'), C.BYSJJZYYSYE, C.CJRZGRS");
		} else if(queryType == "total") {
			sqlString.append("SELECT SUM(NVL(B.NJYYSXE,0)), SUM(NVL(C.BYSJJZYYSYE,0)), SUM(C.CJRZGRS)");
		}
		sqlString.append(" FROM SPDB.SP_JL_FLQYMYYS B, SBDB.SB_JL_JM_AZCJRJMSB C ");
		sqlString.append(" WHERE B.JSJDM = C.JSJDM AND B.SWJGZZJGDM = C.SWJGZZJGDM AND B.SQSPBH = C.SQSPBH ");
		sqlString.append(" AND C.CJRQ >= TO_DATE('" + queryCxqsrq + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') AND C.CJRQ <= TO_DATE('" + queryCxjzrq + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ");
		
		// 加入录入的查询条件
		if (!"".equals(SBStringUtils.killNull(queryJsjdm))) {
			sqlString.append(" AND B.JSJDM = ");
			sqlString.append(SBStringUtils.getSQLStr(queryJsjdm));
		}
		if (!"".equals(SBStringUtils.killNull(queryQymc))) {
			sqlString.append(" AND B.JSJDM IN (SELECT D.JSJDM FROM DJDB.DJ_JL_JBSJ D WHERE D.NSRMC = ");
			sqlString.append(SBStringUtils.getSQLStr(queryQymc));
			sqlString.append(")");
		}
		if (!"0000".equals(SBStringUtils.killNull(queryZgswjgdm))) {
			sqlString.append(" AND B.SWJGZZJGDM = ");
			sqlString.append(SBStringUtils.getSQLStr(queryZgswjgdm));
		}
		sqlString.append(" ORDER BY C.CJRQ DESC, C.JSJDM");
		return sqlString.toString();
	}
	
	/**
	 * 查询安置残疾人就业企业营业税减免税情况
	 * 
	 * @param userData
	 * @param cForm
	 * @param da
	 * @param queryType 查询类别("list"-->查询遍历数据；"total"-->查询总计数据)
	 * @return
	 * @throws BaseException
	 */
	private List queryCjrjms(UserData userData, CjrjyjmscxtjForm cForm, SfDBAccess da, String queryType) 
		throws BaseException {
		Debug.out("Call CjrjyjmscxtjProcessor_queryCjrjms......");
		// 句柄申明
		String sql = null;
		List tmpList = new ArrayList();
		ResultSet rs = null;
		CjrjyjmsInfo cjrjyInfo = null;
		// 整理并创建查询用sql
		sql = this.getQuerySql(cForm, queryType);
		try {
			// 执行SQL
			rs = da.querySQL(sql);
			if(queryType == "list") {
				int count = 1;
				while(rs.next()) {
					cjrjyInfo = new CjrjyjmsInfo();
					cjrjyInfo.setIndexId(count++);
					cjrjyInfo.setJsjdm(rs.getString(1));
					cjrjyInfo.setQymc(rs.getString(2));
					cjrjyInfo.setSpbbh(rs.getString(3));
					cjrjyInfo.setNjzyysxe(rs.getString(4));
					cjrjyInfo.setJzksrq(rs.getString(5));
					cjrjyInfo.setJzjzrq(rs.getString(6));
					cjrjyInfo.setSjjzyyse(rs.getString(7));
					cjrjyInfo.setAzcjzgrs(rs.getString(8));
					tmpList.add(cjrjyInfo);
				}
			} else if(queryType == "total") {
				while(rs.next()) {
					tmpList.add(rs.getString(1));
					tmpList.add(rs.getString(2));
					tmpList.add(rs.getString(3));
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		return tmpList;
	}

	/**
	 * 获取页数
	 * 
	 * @param rsCount
	 *            查询结果集build
	 * @return 页数
	 */
	private String getPageTotalCount(int rsCount) {
		// 句柄申明
		String countTotal = "0";
		// 开始业务
		int pageCount = 0;
		if ((rsCount % CodeConstant.SD_PG_LENGTH) == 0) {
			pageCount = (rsCount / CodeConstant.SD_PG_LENGTH);
		} else {
			pageCount = (rsCount / CodeConstant.SD_PG_LENGTH) + 1;
		}
		countTotal = String.valueOf(pageCount);
		// 返回值
		return countTotal;
	}
	
	/**
	 * 获取当前分页数据集
	 * 
	 * @param tmpList
	 *            完整数据集
	 * @param pf
	 *            页面对象
	 * @return 当前分页数据集
	 */
	private List getPageDataList(List tmpList, CjrjyjmscxtjForm pf) {
		// 1.申明句柄
		List dataList = new ArrayList();
		// 2.初始化参数值
		int startIndex = this.getPageStartIndex(pf.getNextPage(), pf
				.getTotalpage());
		int endIndex = this
				.getPageEndIndex(pf.getNextPage(), pf.getTotalpage());
		// 3.开始业务
		for (int i = startIndex; i < endIndex; i++) {
			if (i < tmpList.size()) {
				dataList.add(tmpList.get(i));
			}
		}
		tmpList = null;
		// 99.返回值
		return dataList;
	}
	
	/**
	 * 获取当前页开始index
	 * 
	 * @param nextPage
	 *            下一页
	 * @param countTotal
	 *            总页数
	 * @return 开始index
	 */
	private int getPageStartIndex(String nextPage, String countTotal) {
		// 1.句柄申明
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int start = -1;
		// 2.开始业务
		start = (iNextPage - 1) * CodeConstant.SD_PG_LENGTH;
		// 99.返回值
		return start;
	}
	
	/**
	 * 获取当前页结束index
	 * 
	 * @param nextPage
	 *            下一页
	 * @param countTotal
	 *            总页数
	 * @return 结束index
	 */
	private int getPageEndIndex(String nextPage, String countTotal) {
		// 1.句柄申明
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int end = -1;
		// 2.开始业务
		end = iNextPage * CodeConstant.SD_PG_LENGTH;
		// 99.返回值
		return end;
	}
	
}
