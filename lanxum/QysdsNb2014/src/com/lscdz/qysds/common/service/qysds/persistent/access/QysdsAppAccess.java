package com.lscdz.qysds.common.service.qysds.persistent.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.sql.rowset.CachedRowSet;

import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsConstants;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsItemDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsTableDeclare;
import com.lscdz.qysds.common.service.qysds.db.DBAccess;
import com.lscdz.qysds.common.service.qysds.util.DateUtils;
import com.lscdz.qysds.common.service.qysds.util.ReportsUtils;
import com.lscdz.qysds.common.service.qysds.util.StringUtils;
import yangjian.frame.util.FrameException;
/**
 * 自定义报表-企业所得税数据管理器
 * 项目名称：QysdsNb2014   
 * 类名称：QysdsAppAccess   
 * 类描述：   描述所有和企业所得税申报表相关的数据操作和相关的工具操作
 * 创建人：wangcy 
 * 创建时间：2014-12-1 下午1:49:25   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 下午1:49:25   
 * 修改备注：   
 * @version  1.0
 */
public class QysdsAppAccess extends AppAccess implements IAppAccess {
	public QysdsAppAccess(DBAccess idba) {
		super(idba);
	}
	/**
	 * 获取当前应用活动版本号 
	 * @param appid String 应用ID
	 * @return String 当前应用活动版本号
	 */
	public String getCurrentVersion(String appid) {
		return QysdsReportsConstants.CREPORTS_VERSION_QYSDS;
	}

	/**
	 * 设置审核结果方法方法
	 * 
	 * @param obj 删除对象,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param checkFlag 标志位,参见QysdsReportsConstants. CREPORTS_CHECK_QYSDS_NOPASS和QysdsReportsConstants.CREPORTS_CHECK_QYSDS_PASS
	 * @exception FrameException 业务异常
	 */
	public void setCheckResult(Object obj, String checkFlag)throws FrameException {
		// 0.句柄申明
		QysdsReportsDeclare qrd = null;
		StringBuffer sb;
		String nsrjsjdm; // 纳税人计算机代码
		String nd; // 年度
		String bbqlx; // 报表期类型
		String qh; // 期号
		// 1.参数校验
		if (obj == null) {
			throw new FrameException("传入保存参数为空！");
		}
		// 2.初始化
		qrd = (QysdsReportsDeclare) obj;
		nsrjsjdm = qrd.getNsrjsjdm();
		nd = qrd.getSknd();
		bbqlx = qrd.getBbqlx();
		qh = qrd.getQh();
		// 3.业务过程
		try {
			// /3.0.进行保存参数的校验
			if (!qrd.checkValid(2)) {
				throw new FrameException("传入保存参数非法！");
			}
			// /3.1.更新本期申报数据
			// //3.1.0.
			sb = new StringBuffer();
			sb.append("UPDATE SBDB.SB_JL_QYSDSSBB_CZZB SET SHBZ=");
			sb.append(StringUtils.getSQLStr(checkFlag));
			sb.append(" WHERE JSJDM=");
			sb.append(StringUtils.getSQLStr(nsrjsjdm));
			sb.append(" AND BBQLX=");
			sb.append(StringUtils.getSQLStr(bbqlx));
			sb.append(" AND QH=");
			sb.append(StringUtils.getSQLStr(qh));
			sb.append(" AND SKND=");
			sb.append(StringUtils.getSQLStr(nd));
			this.idba.executeUpdate(sb.toString());
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("更新SBDB.SB_JL_QYSDSSBB_CZZB表发生异常！");
		}
		// 99.返回值
	}

	/**
	 * 删除方法
	 * 
	 * @param obj 删除对象,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @exception FrameException 业务异常
	 */
	public void delete(Object obj) throws FrameException {
		// 0.句柄申明
		QysdsReportsDeclare qrd = null;
		StringBuffer sb;
		String nsrjsjdm; // 纳税人计算机代码
		String nd; // 年度
		String bbqlx; // 报表期类型
		String qh; // 期号
		// 1.参数校验
		if (obj == null) {
			throw new FrameException("传入保存参数为空！");
		}
		// 2.初始化
		qrd = (QysdsReportsDeclare) obj;
		nsrjsjdm = qrd.getNsrjsjdm();
		nd = qrd.getSknd();
		bbqlx = qrd.getBbqlx();
		qh = qrd.getQh();

		// 3.业务过程
		try {
			// /3.0.进行保存参数的校验
			if (!qrd.checkValid(2)) {
				throw new FrameException("传入保存参数非法！");
			}
			// /3.1.删除本期申报数据			
			for(String sbdm:QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_ND_2014){
				// //3.1.0.
				sb = new StringBuffer();
				sb.append("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_ND_"+sbdm+" WHERE JSJDM=");
				sb.append(StringUtils.getSQLStr(nsrjsjdm));
				sb.append(" AND BBQLX=");
				sb.append(StringUtils.getSQLStr(bbqlx));
				sb.append(" AND QH=");
				sb.append(StringUtils.getSQLStr(qh));
				sb.append(" AND SKND=");
				sb.append(StringUtils.getSQLStr(nd));
				this.idba.executeUpdate(sb.toString());
				// //3.1.1.
				sb = new StringBuffer();
				sb.append("DELETE FROM SBDB.SB_JL_QYSDSSBB_ZB_ND_"+sbdm+" t WHERE JSJDM=");
				/** modify by lianglw 20071008 增加强制索引 start **/
				// sb.append("DELETE /*+ index (t PK_SB_JL_QYSDSSBB_ZB_ND)*/ FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE JSJDM=");
				/** modify by lianglw 20071008 增加强制索引 end **/
				sb.append(StringUtils.getSQLStr(nsrjsjdm));
				sb.append(" AND BBQLX=");
				sb.append(StringUtils.getSQLStr(bbqlx));
				sb.append(" AND QH=");
				sb.append(StringUtils.getSQLStr(qh));
				sb.append(" AND SKND=");
				sb.append(StringUtils.getSQLStr(nd));
				this.idba.executeUpdate(sb.toString());				
			}


			// /3.2.删除本期主数据
			sb = new StringBuffer();
			sb.append("DELETE FROM SBDB.SB_JL_QYSDSSBB_CZZB WHERE NSRJSJDM=");
			sb.append(StringUtils.getSQLStr(nsrjsjdm));
			sb.append(" AND BBQLX=");
			sb.append(StringUtils.getSQLStr(bbqlx));
			sb.append(" AND QH=");
			sb.append(StringUtils.getSQLStr(qh));
			sb.append(" AND SKND=");
			sb.append(StringUtils.getSQLStr(nd));
			this.idba.executeUpdate(sb.toString());

		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("删除表SBDB.SB_JL_QYSDSSBB_CB_ND、SBDB.SB_JL_QYSDSSBB_ZB_ND、SBDB.SB_JL_QYSDSSBB_CZZB发生异常！");
		}
		// 99.返回值
	}

	/**
	 * 删除方法,只处理具备表成员的数据
	 * 
	 * @param obj 删除对象,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @exception FrameException 业务异常
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public void deleteSingleTable(Object obj) throws FrameException {
		// 0.句柄申明
		QysdsReportsDeclare qrd = null;
		QysdsReportsTableDeclare qrtd = null;
		StringBuffer sb;
		String nsrjsjdm; // 纳税人计算机代码
		String nd; // 年度
		String bbqlx; // 报表期类型
		String qh; // 期号
		Connection conn;
		PreparedStatement pstat_nd;
		PreparedStatement pstat_nd_child;
		// PreparedStatement pstat_jd;
		// PreparedStatement pstat_jd_child;
		// 1.参数校验
		if (obj == null) {
			throw new FrameException("传入保存参数为空！无法保存申报主数据");
		}
		// 2.初始化
		conn = this.idba.getConn();
		qrd = (QysdsReportsDeclare) obj;
		nsrjsjdm = qrd.getNsrjsjdm();
		nd = qrd.getSknd();
		bbqlx = qrd.getBbqlx();
		qh = qrd.getQh();
		// 3.业务过程
		try {
			// /3.0.初始化数据操作
			boolean flag_nd = false;
			// boolean flag_jd = false;
			pstat_nd = conn.prepareStatement("DELETE  FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE JSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			// pstat_jd =
			// conn.prepareStatement("DELETE  FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE JSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			/** modify by lianglw 20071008 增加强制索引 start **/
			// pstat_nd =
			// conn.prepareStatement("DELETE  /*+ index (t PK_SB_JL_QYSDSSBB_ZB_ND)*/  FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE JSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			// pstat_jd =
			// conn.prepareStatement("DELETE  /*+ index (t PK_SB_JL_QYSDSSBB_ZB_JD)*/  FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE JSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			/** modify by lianglw 20071008 增加强制索引 end **/
			pstat_nd_child = conn.prepareStatement("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_ND WHERE JSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			// pstat_jd_child =
			// conn.prepareStatement("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE JSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			// /3.1.循环生成对象集合，区分主对象和从对象
			Iterator iterator = qrd.getTableContentList().keySet().iterator();
			String tID;
			while (iterator.hasNext()) {
				qrtd = (QysdsReportsTableDeclare) (qrd.getTableContentList().get(iterator.next()));
				tID = qrtd.getTableId();
				// Iterator iterator1 = qrtd.getCellContentList().keySet().
				// iterator();
				if (this.checkAndGetTableSpace(tID) == 0) { // 处理第一个存储空间
					flag_nd = true;
					pstat_nd.setString(1, nsrjsjdm);
					pstat_nd.setString(2, nd);
					pstat_nd.setString(3, bbqlx);
					pstat_nd.setString(4, qh);
					pstat_nd.setString(5, tID);
					pstat_nd.addBatch();
					pstat_nd_child.setString(1, nsrjsjdm);
					pstat_nd_child.setString(2, nd);
					pstat_nd_child.setString(3, bbqlx);
					pstat_nd_child.setString(4, qh);
					pstat_nd_child.setString(5, tID);
					pstat_nd_child.addBatch();
				}
			}
			// /3.2.执行数据操作
			if (flag_nd) {
				pstat_nd.executeBatch();
				pstat_nd_child.executeBatch();
			}

			// /3.3.关闭数据操作
			pstat_nd.close();
			pstat_nd_child.close();
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("删除表SBDB.SB_JL_QYSDSSBB_ZB_ND、SBDB.SB_JL_QYSDSSBB_CB_ND发生异常");
		}
		// 99.返回值
	}

	/**
	 * 查询方法
	 * 
	 * @param obj 查询参数,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return 查询结果,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @exception FrameException 业务异常
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public Object query(Object obj) throws FrameException {
		// 0.句柄申明
		QysdsReportsDeclare qrd = null;
		QysdsReportsTableDeclare qrtd = null;
		QysdsReportsItemDeclare qrid = null;
		StringBuffer sb;
		String nsrjsjdm; // 纳税人计算机代码
		String nd; // 年度
		String bbqlx; // 报表期类型
		String qh; // 期号
		CachedRowSet crs = null;
		// 1.参数校验
		qrd = (QysdsReportsDeclare) obj;
		if (obj == null) {
			throw new FrameException("传入保存参数为空！无法保存申报主数据");
		}
		// 2.初始化
		nsrjsjdm = qrd.getNsrjsjdm();
		nd = qrd.getSknd();
		bbqlx = qrd.getBbqlx();
		qh = qrd.getQh();
		// 3.业务过程
		try {
			String tid = null;
			String iid = null;
			String yzbs = null;
			// /3.-1.查询操作主表数据
			qrd = this.queryDeclareMainOperation(qrd);
			// /3.0.查询年度申报主数据
			sb = new StringBuffer();
			sb.append("SELECT JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,QXDM FROM SBDB.V_SB_JL_QYSDSSBB_ZB_ND t WHERE JSJDM=");
			/** modify by lianglw 20071008 增加强制索引 start **/
			// sb.append("SELECT /*+ index (t PK_SB_JL_QYSDSSBB_ZB_ND)*/ JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE JSJDM=");
			/** modify by lianglw 20071008 增加强制索引 end **/
			sb.append(StringUtils.getSQLStr(nsrjsjdm));
			sb.append(" AND BBQLX=");
			sb.append(StringUtils.getSQLStr(bbqlx));
			sb.append(" AND QH=");
			sb.append(StringUtils.getSQLStr(qh));
			sb.append(" AND SKND=");
			sb.append(StringUtils.getSQLStr(nd));
			System.out.println(sb.toString());
			crs = this.idba.executeQuery(sb.toString());
			//
			while (crs.next()) {
				tid = crs.getString("SBDM");
				if (qrd.getTableContentList().get(tid) == null) { // 如果不存在已有数据则生成新数据
					// --生成新表对象并设置对应值
					qrtd = new QysdsReportsTableDeclare();
					qrd.getTableContentList().put(tid, qrtd);
					qrtd.setTableId(tid);
					qrtd.setTableName(crs.getString("SBBM"));
					qrtd.setTbblx(crs.getString("TBBLX"));
				} else { // 如果不存在已有数据则取出
					qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().get(tid);
				}
				// --处理报表项对象并设置对应值
				yzbs = crs.getString("YZBS");
				if (QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE.equals(yzbs)) {
					qrid = new QysdsReportsItemDeclare();
					qrid.setItemID(crs.getString("HC"));
					qrid.setItemType(yzbs);
					qrid.setItemValue(crs.getString("YZ"));
					qrtd.getCellContentList().put(qrid.getItemID(), qrid);
				}
			}
			crs.close();
			// /3.2.查询年度申报从数据
			sb = new StringBuffer();
			sb.append("SELECT JSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ FROM SBDB.V_SB_JL_QYSDSSBB_CB_ND WHERE JSJDM=");
			sb.append(StringUtils.getSQLStr(nsrjsjdm));
			sb.append(" AND BBQLX=");
			sb.append(StringUtils.getSQLStr(bbqlx));
			sb.append(" AND QH=");
			sb.append(StringUtils.getSQLStr(qh));
			sb.append(" AND SKND=");
			sb.append(StringUtils.getSQLStr(nd));
			crs = this.idba.executeQuery(sb.toString());
			//
			while (crs.next()) {
				tid = crs.getString("SBDM");
				if (qrd.getTableContentList().get(tid) == null) { // 如果不存在已有数据则生成新数据
					// --生成新表对象并设置对应值
					qrtd = new QysdsReportsTableDeclare();
					qrd.getTableContentList().put(tid, qrtd);
					qrtd.setTableId(tid);
					qrtd.setTableName(crs.getString("SBBM"));
					qrtd.setTbblx(crs.getString("TBBLX"));
				} else { // 如果不存在已有数据则取出
					qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().get(tid);
				}
				// --处理报表项对象并设置对应值
				yzbs = crs.getString("YZBS");
				qrid = new QysdsReportsItemDeclare();
				qrid.setItemID(crs.getString("HC") + "." + crs.getString("ZHS"));
				qrid.setItemType(yzbs);
				qrid.setItemValue(crs.getString("YZ"));
				qrtd.getCellContentList().put(qrid.getItemID(), qrid);
			}
			crs.close();

		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("查询表SBDB.SB_JL_QYSDSSBB_ZB_ND、SBDB.SB_JL_QYSDSSBB_CB_ND发生异常！");
		}
		// 99.返回值
		return qrd;
	}

	/**
	 * 查询单表数据方法,只处理具备表成员的数据
	 * 
	 * @param obj 查询参数,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return 查询结果,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @exception FrameException 业务异常
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public Object querySingleTable(Object obj) throws FrameException {
		// 0.句柄申明
		QysdsReportsDeclare qrd = null;
		QysdsReportsTableDeclare qrtd = null;
		QysdsReportsItemDeclare qrid = null;
		StringBuffer sb;
		String nsrjsjdm; // 纳税人计算机代码
		String nd; // 年度
		String bbqlx; // 报表期类型
		String qh; // 期号
		CachedRowSet crs = null;
		// 1.参数校验
		qrd = (QysdsReportsDeclare) obj;
		if (obj == null) {
			throw new FrameException("传入保存参数为空！无法查询申报主数据");
		}
		if (!(qrd.getTableContentList().size() > 0)) {
			throw new FrameException("传入保存表参数为空！无法查询申报主数据");
		}
		// 2.初始化
		nsrjsjdm = qrd.getNsrjsjdm();
		nd = qrd.getSknd();
		bbqlx = qrd.getBbqlx();
		qh = qrd.getQh();
		// /2.0.处理表ID权限字段
		String tids = "";
		Iterator iterator = qrd.getTableContentList().keySet().iterator();
		sb = new StringBuffer();
		while (iterator.hasNext()) {
			qrtd = (QysdsReportsTableDeclare) (qrd.getTableContentList().get(iterator.next()));
			sb.append("'");
			sb.append(qrtd.getTableId());
			sb.append("'");
			sb.append(",");
		}
		sb.append("'-1'");
		tids = sb.toString();
		// 3.业务过程
		try {
			String tid = null;
			String iid = null;
			String yzbs = null;
			// /3.-1.查询操作主表数据
			qrd = this.queryDeclareMainOperation(qrd);
			// /3.0.查询年度申报主数据
			sb = new StringBuffer();
			sb.append("SELECT JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE JSJDM=");
			/** modify by lianglw 20071008 增加强制索引 start **/
			// sb.append("SELECT /*+ index (t PK_SB_JL_QYSDSSBB_ZB_ND)*/ JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE JSJDM=");
			/** modify by lianglw 20071008 增加强制索引 end **/
			sb.append(StringUtils.getSQLStr(nsrjsjdm));
			sb.append(" AND BBQLX=");
			sb.append(StringUtils.getSQLStr(bbqlx));
			sb.append(" AND QH=");
			sb.append(StringUtils.getSQLStr(qh));
			sb.append(" AND SKND=");
			sb.append(StringUtils.getSQLStr(nd));
			sb.append(" AND SBDM IN (");
			sb.append(tids);
			sb.append(")");
			crs = this.idba.executeQuery(sb.toString());
			//
			while (crs.next()) {
				tid = crs.getString("SBDM");
				if (qrd.getTableContentList().get(tid) == null) { // 如果不存在已有数据则生成新数据
					// --生成新表对象并设置对应值
					qrtd = new QysdsReportsTableDeclare();
					qrd.getTableContentList().put(tid, qrtd);
					qrtd.setTableId(tid);
					qrtd.setTableName(crs.getString("SBBM"));
					qrtd.setTbblx(crs.getString("TBBLX"));
				} else { // 如果不存在已有数据则取出
					qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().get(tid);
				}
				// --处理报表项对象并设置对应值
				yzbs = crs.getString("YZBS");
				if (QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE.equals(yzbs)) {
					qrid = new QysdsReportsItemDeclare();
					qrid.setItemID(crs.getString("HC"));
					qrid.setItemType(yzbs);
					qrid.setItemValue(crs.getString("YZ"));
					qrtd.getCellContentList().put(qrid.getItemID(), qrid);
				}
			}
			crs.close();

			// /3.2.查询年度申报从数据
			sb = new StringBuffer();
			sb.append("SELECT JSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ FROM SBDB.SB_JL_QYSDSSBB_CB_ND WHERE JSJDM=");
			sb.append(StringUtils.getSQLStr(nsrjsjdm));
			sb.append(" AND BBQLX=");
			sb.append(StringUtils.getSQLStr(bbqlx));
			sb.append(" AND QH=");
			sb.append(StringUtils.getSQLStr(qh));
			sb.append(" AND SKND=");
			sb.append(StringUtils.getSQLStr(nd));
			sb.append(" AND SBDM IN (");
			sb.append(tids);
			sb.append(")");

			crs = this.idba.executeQuery(sb.toString());
			//
			while (crs.next()) {
				tid = crs.getString("SBDM");
				if (qrd.getTableContentList().get(tid) == null) { // 如果不存在已有数据则生成新数据
					// --生成新表对象并设置对应值
					qrtd = new QysdsReportsTableDeclare();
					qrd.getTableContentList().put(tid, qrtd);
					qrtd.setTableId(tid);
					qrtd.setTableName(crs.getString("SBBM"));
					qrtd.setTbblx(crs.getString("TBBLX"));
				} else { // 如果不存在已有数据则取出
					qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList()
							.get(tid);
				}
				// --处理报表项对象并设置对应值
				yzbs = crs.getString("YZBS");
				qrid = new QysdsReportsItemDeclare();
				qrid.setItemID(crs.getString("HC") + "." + crs.getString("ZHS"));
				qrid.setItemType(yzbs);
				qrid.setItemValue(crs.getString("YZ"));
				qrtd.getCellContentList().put(qrid.getItemID(), qrid);
			}
			crs.close();

		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("查询表SBDB.SB_JL_QYSDSSBB_ZB_ND、SBDB.SB_JL_QYSDSSBB_CB_ND发生异常！");
		}
		// 99.返回值
		return qrd;

	}

	/**
	 * 保存方法
	 * 
	 * @param obj 保存对象,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @exception FrameException 业务异常
	 */

	public void save(Object obj) throws FrameException {
		// 0.句柄申明
		QysdsReportsDeclare qrd = null;
		// 1.参数校验
		if (obj == null) {
			throw new FrameException("传入保存参数为空！");
		}
		// 2.初始化
		qrd = (QysdsReportsDeclare) obj;
		// 3.业务过程
		try {
			// /3.0.进行保存参数的校验
			if (!qrd.checkValid(0)) {
				throw new FrameException("传入保存参数非法！");
			}
			// /3.1.清除全部本期数据
			this.delete(qrd);
			// /3.2.生成登记主表数据
			this.saveDeclareMainOperation(qrd);
			// /3.3.生成申报表数据
			// this.saveDeclareMainData(qrd);
			this.saveDeclareMainDataBatch(qrd);

		} catch (FrameException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("保存企业所得税年报数据发生异常！");
		}
		// 99.返回值
	}

	/**
	 * 保存方法,只处理具备表成员的数据
	 * 
	 * @param obj  保存对象,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @exception FrameException 业务异常
	 */
	public void saveSingleTable(Object obj) throws FrameException {
		// 0.句柄申明
		QysdsReportsDeclare qrd = null;
		// 1.参数校验
		if (obj == null) {
			throw new FrameException("传入保存参数为空！");
		}
		// 2.初始化
		qrd = (QysdsReportsDeclare) obj;
		// 3.业务过程
		try {
			// /3.0.进行保存参数的校验
			if (!qrd.checkValid(0)) {
				throw new FrameException("传入保存参数非法！");
			}
			// /3.1.清除全部本期数据
			this.deleteSingleTable(qrd);
			// /3.2.生成登记主表数据
			this.saveDeclareMainOperation(qrd);
			// /3.3.生成申报表数据
			// this.saveDeclareMainData(qrd);
			this.saveDeclareMainDataBatch(qrd);
		} catch (FrameException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			throw new FrameException("保存企业所得税年报数据发生异常！");
		}
		// 99.返回值
	}

	/**
	 * 更新审核状态信息表
	 * 
	 * @param obj  例如,成员为com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare
	 * @param shbz  审核标志
	 * @throws FrameException
	 * @return true：更新成功；false：更新失败
	 * @author wofei
	 */
	@SuppressWarnings("rawtypes")
	public boolean updateCheckStatus(Object obj, String shbz)
			throws FrameException {

		StringBuffer buffer = new StringBuffer();
		QysdsReportsDeclare report = (QysdsReportsDeclare) obj;
		QysdsReportsTableDeclare table = null;
		HashMap map = (HashMap) report.getTableContentList();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			table = (QysdsReportsTableDeclare) map.get(key);
		}

		if (table == null) {
			throw new FrameException("参数错误！查询审核状态时须传入申报表ID，请检查！");
		}

		buffer.append(" DELETE FROM SBDB.SB_JL_QYSDSSBB_TABLE_INFO");
		buffer.append(" WHERE JSJDM=");
		buffer.append(StringUtils.getSQLStr(report.getNsrjsjdm()));
		buffer.append(" AND BBQLX=");
		buffer.append(StringUtils.getSQLStr(report.getBbqlx()));
		buffer.append(" AND QH=");
		buffer.append(StringUtils.getSQLStr(report.getQh()));
		buffer.append(" AND SKND=");
		buffer.append(StringUtils.getSQLStr(report.getSknd()));
		buffer.append(" AND SBDM=");
		buffer.append(StringUtils.getSQLStr(table.getTableId()));

		this.idba.executeUpdate(buffer.toString());
		buffer.delete(0, buffer.length());

		// 如果审核状态为空，则删除记录后不再进行插入
		if ("".equals(shbz) || shbz == null) {
			return true;
		}

		buffer.append(" INSERT INTO SBDB.SB_JL_QYSDSSBB_TABLE_INFO");
		buffer.append(" (JSJDM,SBDM,BBQLX,QH,SKND,SHBZ) VALUES(");
		buffer.append(StringUtils.getSQLStr(report.getNsrjsjdm()) + ",");
		buffer.append(StringUtils.getSQLStr(table.getTableId()) + ",");
		buffer.append(StringUtils.getSQLStr(report.getBbqlx()) + ",");
		buffer.append(StringUtils.getSQLStr(report.getQh()) + ",");
		buffer.append(StringUtils.getSQLStr(report.getSknd()) + ",");
		buffer.append(StringUtils.getSQLStr(shbz) + ")");
		int rows = this.idba.executeUpdate(buffer.toString());

		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询审核状态
	 * 
	 * @param obj 成员为com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare
	 * @return 审核状态
	 * @throws FrameException
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public String queryCheckStatus(Object obj) throws FrameException,SQLException {
		StringBuffer buffer = new StringBuffer();
		QysdsReportsDeclare report = (QysdsReportsDeclare) obj;
		QysdsReportsTableDeclare table = null;
		HashMap map = (HashMap) report.getTableContentList();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			table = (QysdsReportsTableDeclare) map.get(key);
		}

		if (table == null) {
			throw new FrameException("参数错误！查询审核状态时须传入申报表ID，请检查！");
		}

		report.getNsrjsjdm();
		buffer.append(" SELECT SHBZ FROM SBDB.SB_JL_QYSDSSBB_TABLE_INFO");
		buffer.append(" WHERE JSJDM=");
		buffer.append(StringUtils.getSQLStr(report.getNsrjsjdm()));
		buffer.append(" AND BBQLX=");
		buffer.append(StringUtils.getSQLStr(report.getBbqlx()));
		buffer.append(" AND QH=");
		buffer.append(StringUtils.getSQLStr(report.getQh()));
		buffer.append(" AND SKND=");
		buffer.append(StringUtils.getSQLStr(report.getSknd()));
		buffer.append(" AND SBDM=");
		buffer.append(StringUtils.getSQLStr(table.getTableId()));

		CachedRowSet crs = this.idba.executeQuery(buffer.toString());

		while (crs.next()) {
			return crs.getString("SHBZ");
		}

		return "";
	}

	/**
	 * 查询企业所得税申报主数据
	 * 
	 * @param qrd QysdsReportsDeclare 入口参数
	 * @return QysdsReportsDeclare 出口参数
	 * @throws FrameException 系统异常
	 */
	private QysdsReportsDeclare queryDeclareMainOperation(QysdsReportsDeclare qrd) throws FrameException {
		// 0.句柄申明
		StringBuffer sb;
		String nsrjsjdm; // 纳税人计算机代码
		String nd; // 年度
		String bbqlx; // 报表期类型
		String qh; // 期号
		CachedRowSet crs = null;
		// 1.参数校验
		if (qrd == null) {
			throw new FrameException("传入保存参数为空！无法保存申报主数据");
		}
		// 2.初始化
		nsrjsjdm = qrd.getNsrjsjdm();
		nd = qrd.getSknd();
		bbqlx = qrd.getBbqlx();
		qh = qrd.getQh();
		// 3.业务过程
		try {
			// /3.0.查询申报数据
			sb = new StringBuffer();
			sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,TO_CHAR(SBRQ,'yyyymmdd') SBRQ,SBBBBH,TO_CHAR(SKSSSQQ,'yyyymmdd') SKSSSQQ,TO_CHAR(SKSSSQZ,'yyyymmdd') SKSSSQZ,SHBZ,SWJSJDM,SWJGZZJGDM,CJR,TO_CHAR(CJSJ,'yyyymmdd') CJSJ,LRR,TO_CHAR(LRSJ,'yyyymmdd') LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_CZZB WHERE NSRJSJDM=");
			sb.append(StringUtils.getSQLStr(nsrjsjdm));
			sb.append(" AND BBQLX=");
			sb.append(StringUtils.getSQLStr(bbqlx));
			sb.append(" AND QH=");
			sb.append(StringUtils.getSQLStr(qh));
			sb.append(" AND SKND=");
			sb.append(StringUtils.getSQLStr(nd));
			crs = this.idba.executeQuery(sb.toString());
			if (crs.next()) {
				qrd.setNsrjsjdm(crs.getString("NSRJSJDM"));
				qrd.setNsrmc(crs.getString("NSRMC"));
				qrd.setBbqlx(crs.getString("BBQLX"));
				qrd.setQh(crs.getString("QH"));
				qrd.setSknd(crs.getString("SKND"));
				qrd.setSbrq(DateUtils.getDateTime(crs.getString("SBRQ")));
				qrd.setVersion(crs.getString("SBBBBH"));
				qrd.setSkssksrq(DateUtils.getDateTime(crs.getString("SKSSSQQ")));
				qrd.setSkssjsrq(DateUtils.getDateTime(crs.getString("SKSSSQZ")));
				qrd.setSwjsjdm(crs.getString("SWJSJDM"));
				qrd.setSwjgzzjgdm(crs.getString("SWJGZZJGDM"));
				qrd.setLrr(crs.getString("LRR"));
				qrd.setLrrq(DateUtils.getDateTime(crs.getString("LRSJ")));
				qrd.setCjr(crs.getString("CJR"));
				qrd.setCjrq(DateUtils.getDateTime(crs.getString("CJSJ")));
				qrd.setQxdm(crs.getString("QXDM"));
				if (QysdsReportsConstants.CREPORTS_CHECK_QYSDS_PASS.equals(crs.getString("SHBZ"))) {
					qrd.setCheckFlag(true);
				} else {
					qrd.setCheckFlag(false);
				}
				crs.close();
			} else {
				crs.close();
				// throw new FrameException("错误的申报主数据查询参数！无法找到记录");
			}

		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("查询表发生异常！");
		}
		// 99.返回值
		return qrd;
	}

	/**
	 * 生成申报表主数据，如果存在主数据则进行更新
	 * 
	 * @param qrd 保存对象,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return QysdsReportsDeclare 处理完毕的对象
	 * @exception FrameException 业务异常
	 */
	private QysdsReportsDeclare saveDeclareMainOperation(QysdsReportsDeclare qrd)
			throws FrameException {
		// 0.句柄申明
		StringBuffer sb;
		String nsrjsjdm; // 纳税人计算机代码
		String nd; // 年度
		String bbqlx; // 报表期类型
		String qh; // 期号
		CachedRowSet crs = null;
		// 1.参数校验
		if (qrd == null) {
			throw new FrameException("传入保存参数为空！无法保存申报主数据");
		}
		// 2.初始化
		nsrjsjdm = qrd.getNsrjsjdm();
		nd = qrd.getSknd();
		bbqlx = qrd.getBbqlx();
		qh = qrd.getQh();
		// 3.业务过程
		try {
			// /3.0.查询是否存在其它申报数据
			sb = new StringBuffer();
			sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,SKSSSQQ,SKSSSQZ,SHBZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_CZZB  WHERE NSRJSJDM=");
			sb.append(StringUtils.getSQLStr(nsrjsjdm));
			sb.append(" AND BBQLX=");
			sb.append(StringUtils.getSQLStr(bbqlx));
			sb.append(" AND QH=");
			sb.append(StringUtils.getSQLStr(qh));
			sb.append(" AND SKND=");
			sb.append(StringUtils.getSQLStr(nd));
			crs = this.idba.executeQuery(sb.toString());
			// /3.2.根据3.1.的查询结果决定是否生成申报主数据
			if (crs.next()) { // 有数据执行更新
				sb = new StringBuffer();
				sb.append("UPDATE SBDB.SB_JL_QYSDSSBB_CZZB");
				sb.append(" SET NSRMC=");
				sb.append(StringUtils.getSQLStr(qrd.getNsrmc()));
				sb.append(",SBRQ=");
				sb.append(StringUtils.getSQLDate(DateUtils.getDate(qrd.getSbrq())));
				sb.append(",SBBBBH=");
				sb.append(StringUtils.getSQLStr(qrd.getVersion()));
				sb.append(",SKSSSQQ=");
				sb.append(StringUtils.getSQLDate(DateUtils.getDate(qrd.getSkssksrq())));
				sb.append(",SKSSSQZ=");
				sb.append(StringUtils.getSQLDate(DateUtils.getDate(qrd.getSkssjsrq())));
				sb.append(",SHBZ=");
				sb.append(StringUtils.getSQLStr(QysdsReportsConstants.CREPORTS_CHECK_QYSDS_NOPASS));
				sb.append(",SWJSJDM=");
				sb.append(StringUtils.getSQLStr(qrd.getSwjsjdm()));
				sb.append(",SWJGZZJGDM=");
				sb.append(StringUtils.getSQLStr(qrd.getSwjgzzjgdm()));
				sb.append(",LRR=");
				sb.append(StringUtils.getSQLStr(qrd.getLrr()));
				sb.append(",LRSJ=sysdate");
				sb.append(",QXDM=");
				sb.append(StringUtils.getSQLStr(qrd.getQxdm()));
				sb.append(" WHERE NSRJSJDM=");
				sb.append(StringUtils.getSQLStr(nsrjsjdm));
				sb.append(" AND BBQLX=");
				sb.append(StringUtils.getSQLStr(bbqlx));
				sb.append(" AND QH=");
				sb.append(StringUtils.getSQLStr(qh));
				sb.append(" AND SKND=");
				sb.append(StringUtils.getSQLStr(nd));
				this.idba.executeUpdate(sb.toString());
			} else { // 无数据执行插入
				sb = new StringBuffer();
				sb.append("INSERT INTO SBDB.SB_JL_QYSDSSBB_CZZB (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,SKSSSQQ,SKSSSQZ,SHBZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (");
				sb.append(StringUtils.getSQLStr(nsrjsjdm));
				sb.append(",");
				sb.append(StringUtils.getSQLStr(qrd.getNsrmc()));
				sb.append(",");
				sb.append(StringUtils.getSQLStr(bbqlx));
				sb.append(",");
				sb.append(StringUtils.getSQLStr(qh));
				sb.append(",");
				sb.append(StringUtils.getSQLStr(nd));
				sb.append(",");
				sb.append(StringUtils.getSQLDate(DateUtils.getDate(qrd.getSbrq())));
				sb.append(",");
				sb.append(StringUtils.getSQLStr(qrd.getVersion()));
				sb.append(",");
				sb.append(StringUtils.getSQLDate(DateUtils.getDate(qrd.getSkssksrq())));
				sb.append(",");
				sb.append(StringUtils.getSQLDate(DateUtils.getDate(qrd.getSkssjsrq())));
				sb.append(",");
				sb.append(StringUtils.getSQLStr(QysdsReportsConstants.CREPORTS_CHECK_QYSDS_NOPASS));
				sb.append(",");
				sb.append(StringUtils.getSQLStr(qrd.getSwjsjdm()));
				sb.append(",");
				sb.append(StringUtils.getSQLStr(qrd.getSwjgzzjgdm()));
				sb.append(",");
				sb.append(StringUtils.getSQLStr(qrd.getLrr()));
				sb.append(",sysdate");
				sb.append(",");
				sb.append(StringUtils.getSQLStr(qrd.getLrr()));
				sb.append(",sysdate");
				sb.append(",");
				sb.append(StringUtils.getSQLStr(qrd.getQxdm()));
				sb.append(")");
				this.idba.executeUpdate(sb.toString());
			}
			crs.close();
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("保存企业所得税年报数据发生异常！");
		}
		// 99.返回值
		return qrd;
	}

	/**
	 * 生成申报表主数据，如果存在主数据则进行更新
	 * 
	 * @param qrd 保存对象,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return QysdsReportsDeclare 处理完毕的对象
	 * @exception FrameException 业务异常
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private QysdsReportsDeclare saveDeclareMainData(QysdsReportsDeclare qrd)throws FrameException {
		// 0.句柄申明
		QysdsReportsTableDeclare qrtd = null;
		QysdsReportsItemDeclare qrid = null;
		StringBuffer sb;
		String nsrjsjdm; // 纳税人计算机代码
		String nd; // 年度
		String bbqlx; // 报表期类型
		String qh; // 期号
		Connection conn;
		PreparedStatement pstat_nd;
		PreparedStatement pstat_nd_child;
		// PreparedStatement pstat_jd;
		// PreparedStatement pstat_jd_child;
		// 1.参数校验
		if (qrd == null) {
			throw new FrameException("传入保存参数为空！无法保存申报主数据");
		}
		// 2.初始化
		conn = this.idba.getConn();
		nsrjsjdm = qrd.getNsrjsjdm();
		nd = qrd.getSknd();
		bbqlx = qrd.getBbqlx();
		qh = qrd.getQh();
		// 3.业务过程
		try {
			// /3.0.初始化数据操作
			boolean flag_nd = false;
			boolean flag_nd_child = false;
			// boolean flag_jd = false;
			// boolean flag_jd_child = false;
			pstat_nd = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_ND (JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			// pstat_jd =
			// conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_JD (JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstat_nd_child = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_ND (JSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			// pstat_jd_child =
			// conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_JD (JSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			// /3.1.循环生成对象集合，区分主对象和从对象
			Iterator iterator = qrd.getTableContentList().keySet().iterator();
			String tID;
			String tType;
			String iID; // 项编号
			String iType; // 项类型
			String iValue; // /项值
			while (iterator.hasNext()) {
				qrtd = (QysdsReportsTableDeclare) (qrd.getTableContentList().get(iterator.next()));
				tID = qrtd.getTableId();
				tType = qrtd.getTbblx();
				Iterator iterator1 = qrtd.getCellContentList().keySet().iterator();
				QysdsReportsItemDeclare qid;
				ArrayList tmpList_1 = new ArrayList(); // 临时父节点记录器
				ArrayList tmpList_2 = new ArrayList();
				if (this.checkAndGetTableSpace(tID) == 0) { // 处理第一个存储空间
					while (iterator1.hasNext()) {
						qid = (QysdsReportsItemDeclare) (qrtd.getCellContentList().get(iterator1.next()));
						iID = qid.getItemID();
						iValue = qid.getItemValue();
						String[] tmpiID = ReportsUtils.splitItemID(iID); // 获取拆分的id
						if (tmpiID == null) { // 按照没有子节点处理
							flag_nd = true;
							// JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
							// ,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
							pstat_nd.setString(1, nsrjsjdm);
							pstat_nd.setString(2,StringUtils.killNull(qrd.getNsrmc()));
							pstat_nd.setString(3, bbqlx);
							pstat_nd.setString(4, qh);
							pstat_nd.setString(5, nd);
							pstat_nd.setTimestamp(6, qrd.getSbrq());
							pstat_nd.setString(7, qrd.getVersion());
							pstat_nd.setString(8, tType);
							pstat_nd.setString(9, qrtd.getTableId());
							pstat_nd.setString(10,StringUtils.killNull(qrtd.getTableName()));
							pstat_nd.setString(11, iID);
							pstat_nd.setTimestamp(12, qrd.getSkssksrq());
							pstat_nd.setTimestamp(13, qrd.getSkssjsrq());
							pstat_nd.setString(14,QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE); // 标示为单行
							pstat_nd.setString(15, iValue);
							pstat_nd.setString(16, qrd.getSwjsjdm());
							pstat_nd.setString(17, qrd.getSwjgzzjgdm());
							pstat_nd.setString(18, qrd.getCjr());
							pstat_nd.setTimestamp(19, qrd.getCjrq());
							pstat_nd.setString(20, qrd.getLrr());
							pstat_nd.setTimestamp(21, qrd.getLrrq());
							pstat_nd.setString(22, qrd.getQxdm());
							pstat_nd.addBatch();
						} else { // 按照有子节点处理
							if (!tmpList_1.contains(tmpiID[0])) { // 如果已经生成过父节点则什么都不做，没有生成则生成父节点
								// JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
								// ,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
								flag_nd = true;
								tmpList_1.add(tmpiID[0]);
								pstat_nd.setString(1, nsrjsjdm);
								pstat_nd.setString(2,StringUtils.killNull(qrd.getNsrmc()));
								pstat_nd.setString(3, bbqlx);
								pstat_nd.setString(4, qh);
								pstat_nd.setString(5, nd);
								pstat_nd.setTimestamp(6, qrd.getSbrq());
								pstat_nd.setString(7, qrd.getVersion());
								pstat_nd.setString(8, tType);
								pstat_nd.setString(9, qrtd.getTableId());
								pstat_nd.setString(10, StringUtils.killNull(qrtd.getTableName()));
								pstat_nd.setString(11, tmpiID[0]);
								pstat_nd.setTimestamp(12, qrd.getSkssksrq());
								pstat_nd.setTimestamp(13, qrd.getSkssjsrq());
								pstat_nd.setString(14,QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_MULITLINES); // 标示为多行
								pstat_nd.setString(15, ""); // 因为有子域则本记录域值为空
								pstat_nd.setString(16, qrd.getSwjsjdm());
								pstat_nd.setString(17, qrd.getSwjgzzjgdm());
								pstat_nd.setString(18, qrd.getCjr());
								pstat_nd.setTimestamp(19, qrd.getCjrq());
								pstat_nd.setString(20, qrd.getLrr());
								pstat_nd.setTimestamp(21, qrd.getLrrq());
								pstat_nd.setString(22, qrd.getQxdm());
								pstat_nd.addBatch();
							}
							// JSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS
							// ,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ
							flag_nd_child = true;
							pstat_nd_child.setString(1, nsrjsjdm);
							pstat_nd_child.setString(2,StringUtils.killNull(qrd.getNsrmc()));
							pstat_nd_child.setString(3, bbqlx);
							pstat_nd_child.setString(4, qh);
							pstat_nd_child.setString(5, nd);
							pstat_nd_child.setString(6, qrd.getVersion());
							pstat_nd_child.setString(7, tType);
							pstat_nd_child.setString(8, qrtd.getTableId());
							pstat_nd_child.setString(9,StringUtils.killNull(qrtd.getTableName()));
							pstat_nd_child.setString(10, tmpiID[0]);
							pstat_nd_child.setString(11, tmpiID[1]);
							pstat_nd_child.setString(12, "");
							pstat_nd_child.setString(13, iValue); // 因为有子域则本记录域值为子节点域值
							pstat_nd_child.setString(14, qrd.getSwjgzzjgdm());
							pstat_nd_child.setString(15, qrd.getCjr());
							pstat_nd_child.setTimestamp(16, qrd.getCjrq());
							pstat_nd_child.setString(17, qrd.getLrr());
							pstat_nd_child.setTimestamp(18, qrd.getLrrq());
							pstat_nd_child.addBatch();
						}
					}
				}
			}
			// /3.2.执行数据操作
			if (flag_nd) {
				pstat_nd.executeBatch();
			}
			if (flag_nd_child) {
				pstat_nd_child.executeBatch();
			}
			// /3.3.关闭数据操作
			pstat_nd.close();
			pstat_nd_child.close();
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("保存企业所得税数据发生异常！");
		}
		// 99.返回值
		return qrd;
	}

	/**
	 * 增加按申报表批量保存处理方法 生成申报表主数据，如果存在主数据则进行更新，按申报表分批保存批量数据
	 * 
	 * @param qrd 保存对象,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return QysdsReportsDeclare 处理完毕的对象
	 * @exception FrameException 业务异常
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private QysdsReportsDeclare saveDeclareMainDataBatch(QysdsReportsDeclare qrd)throws FrameException {
		// 0.句柄申明
		QysdsReportsTableDeclare qrtd = null;
		QysdsReportsItemDeclare qrid = null;
		StringBuffer sb;
		String nsrjsjdm; // 纳税人计算机代码
		String nd; // 年度
		String bbqlx; // 报表期类型
		String qh; // 期号
		String bbmsf; //报表描述符
		Connection conn;
		PreparedStatement pstat_nd;
		PreparedStatement pstat_nd_child;
		// PreparedStatement pstat_jd;
		// PreparedStatement pstat_jd_child;
		// 1.参数校验
		if (qrd == null) {
			throw new FrameException("传入保存参数为空！无法保存申报主数据");
		}
		// 2.初始化
		conn = this.idba.getConn();
		nsrjsjdm = qrd.getNsrjsjdm();
		nd = qrd.getSknd();
		bbqlx = qrd.getBbqlx();
		qh = qrd.getQh();
		bbmsf=qrd.getJbxx().bbmsf;
		// 3.业务过程
		try {
			// /3.0.初始化数据操作
			boolean flag_nd = false;
			boolean flag_nd_child = false;
			// boolean flag_jd = false;
			// boolean flag_jd_child = false;
			// /3.1.循环生成对象集合，区分主对象和从对象
			Iterator iterator = qrd.getTableContentList().keySet().iterator();
			String tID;
			String tType;
			String iID; // 项编号
			String iType; // 项类型
			String iValue; // /项值
			while (iterator.hasNext()) {
				// 处理单表数据
				qrtd = (QysdsReportsTableDeclare) (qrd.getTableContentList().get(iterator.next()));
				tID = qrtd.getTableId();
				tType = qrtd.getTbblx();
				Iterator iterator1 = qrtd.getCellContentList().keySet().iterator();
				QysdsReportsItemDeclare qid;
				ArrayList tmpList_1 = new ArrayList(); // 临时父节点记录器
				ArrayList tmpList_2 = new ArrayList();
				
				if(tID==null||"".equals(tID)||!bbmsf.contains(tID)){
					continue;
				}
				// 创建prepareStatement对象
				pstat_nd = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_ND_"+tID+" (JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				// pstat_jd =
				// conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_JD (JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pstat_nd_child = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_ND_"+tID+" (JSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				// pstat_jd_child =
				// conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_JD (JSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				if (this.checkAndGetTableSpace(tID) == 0) { // 处理第一个存储空间
					while (iterator1.hasNext()) {
						qid = (QysdsReportsItemDeclare) (qrtd.getCellContentList().get(iterator1.next()));
						iID = qid.getItemID();
						iValue = qid.getItemValue();
						String[] tmpiID = ReportsUtils.splitItemID(iID); // 获取拆分的id
						if (tmpiID == null) { // 按照没有子节点处理
							flag_nd = true;
							// JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
							// ,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
							pstat_nd.setString(1, nsrjsjdm);
							pstat_nd.setString(2,StringUtils.killNull(qrd.getNsrmc()));
							pstat_nd.setString(3, bbqlx);
							pstat_nd.setString(4, qh);
							pstat_nd.setString(5, nd);
							pstat_nd.setTimestamp(6, qrd.getSbrq());
							pstat_nd.setString(7, qrd.getVersion());
							pstat_nd.setString(8, tType);
							pstat_nd.setString(9, qrtd.getTableId());
							pstat_nd.setString(10,StringUtils.killNull(qrtd.getTableName()));
							pstat_nd.setString(11, iID);
							pstat_nd.setTimestamp(12, qrd.getSkssksrq());
							pstat_nd.setTimestamp(13, qrd.getSkssjsrq());
							pstat_nd.setString(14,QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE); // 标示为单行
							pstat_nd.setString(15, iValue);
							pstat_nd.setString(16, qrd.getSwjsjdm());
							pstat_nd.setString(17, qrd.getSwjgzzjgdm());
							pstat_nd.setString(18, qrd.getCjr());
							pstat_nd.setTimestamp(19, qrd.getCjrq());
							pstat_nd.setString(20, qrd.getLrr());
							pstat_nd.setTimestamp(21, qrd.getLrrq());
							pstat_nd.setString(22, qrd.getQxdm());
							pstat_nd.addBatch();
						} else { // 按照有子节点处理
							if (!tmpList_1.contains(tmpiID[0])) { // 如果已经生成过父节点则什么都不做，没有生成则生成父节点
								// JSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
								// ,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
								flag_nd = true;
								tmpList_1.add(tmpiID[0]);
								pstat_nd.setString(1, nsrjsjdm);
								pstat_nd.setString(2,StringUtils.killNull(qrd.getNsrmc()));
								pstat_nd.setString(3, bbqlx);
								pstat_nd.setString(4, qh);
								pstat_nd.setString(5, nd);
								pstat_nd.setTimestamp(6, qrd.getSbrq());
								pstat_nd.setString(7, qrd.getVersion());
								pstat_nd.setString(8, tType);
								pstat_nd.setString(9, qrtd.getTableId());
								pstat_nd.setString(10, StringUtils.killNull(qrtd.getTableName()));
								pstat_nd.setString(11, tmpiID[0]);
								pstat_nd.setTimestamp(12, qrd.getSkssksrq());
								pstat_nd.setTimestamp(13, qrd.getSkssjsrq());
								pstat_nd.setString(14,QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_MULITLINES); // 标示为多行
								pstat_nd.setString(15, ""); // 因为有子域则本记录域值为空
								pstat_nd.setString(16, qrd.getSwjsjdm());
								pstat_nd.setString(17, qrd.getSwjgzzjgdm());
								pstat_nd.setString(18, qrd.getCjr());
								pstat_nd.setTimestamp(19, qrd.getCjrq());
								pstat_nd.setString(20, qrd.getLrr());
								pstat_nd.setTimestamp(21, qrd.getLrrq());
								pstat_nd.setString(22, qrd.getQxdm());
								pstat_nd.addBatch();
							}
							// JSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS
							// ,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ
							flag_nd_child = true;
							pstat_nd_child.setString(1, nsrjsjdm);
							pstat_nd_child.setString(2,StringUtils.killNull(qrd.getNsrmc()));
							pstat_nd_child.setString(3, bbqlx);
							pstat_nd_child.setString(4, qh);
							pstat_nd_child.setString(5, nd);
							pstat_nd_child.setString(6, qrd.getVersion());
							pstat_nd_child.setString(7, tType);
							pstat_nd_child.setString(8, qrtd.getTableId());
							pstat_nd_child.setString(9,StringUtils.killNull(qrtd.getTableName()));
							pstat_nd_child.setString(10, tmpiID[0]);
							pstat_nd_child.setString(11, tmpiID[1]);
							pstat_nd_child.setString(12, "");
							pstat_nd_child.setString(13, iValue); // 因为有子域则本记录域值为子节点域值
							pstat_nd_child.setString(14, qrd.getSwjgzzjgdm());
							pstat_nd_child.setString(15, qrd.getCjr());
							pstat_nd_child.setTimestamp(16, qrd.getCjrq());
							pstat_nd_child.setString(17, qrd.getLrr());
							pstat_nd_child.setTimestamp(18, qrd.getLrrq());
							pstat_nd_child.addBatch();
						}
					}
				}
				// /3.2.执行数据操作
				if (flag_nd) {
					pstat_nd.executeBatch();
				}
				if (flag_nd_child) {
					pstat_nd_child.executeBatch();
				}
				// /3.3.关闭数据操作
				pstat_nd.close();
				pstat_nd_child.close();
			}

		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("保存SBDB.SB_JL_QYSDSSBB_ZB_ND、SBDB.SB_JL_QYSDSSBB_CB_ND数据发生异常！");
		}
		// 99.返回值
		return qrd;
	}

	/**
	 * 获取当前表对应主表存储表名
	 * 
	 * @param tid
	 *            String 表 ID
	 * @return String 存储位置
	 * @throws FrameException
	 *             应用异常
	 */
	@SuppressWarnings("unused")
	private String getZbTableName(String tid) throws FrameException {
		String result = "";
		int tmp = this.checkAndGetTableSpace(tid);
		if (tmp == 0) {
			result = QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_ND_TABLENAME;
		} else if (tmp == 1) {
			result = QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_JD_TABLENAME;
		} else {
			throw new FrameException("无法找到当前表的主表存储空间！！！tid=" + tid);
		}
		return result;
	}

	/**
	 * 获取当前表对应从表存储表名
	 * 
	 * @param tid
	 *            String 表 ID
	 * @return String 存储位置
	 * @throws FrameException
	 *             应用异常
	 */
	@SuppressWarnings("unused")
	private String getCbTableName(String tid) throws FrameException {
		String result = "";
		int tmp = this.checkAndGetTableSpace(tid);
		if (tmp == 0) {
			result = QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_ND_CHILDTABLENAME;
		} else if (tmp == 1) {
			result = QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_JD_CHILDTABLENAME;
		} else {
			throw new FrameException("无法找到当前表的从表存储空间！！！tid=" + tid);
		}
		return result;
	}

	/**
	 * 根据表ID获取存储位置
	 * @param tid String 表 ID
	 * @return int 存储位置 0-年度表，1-季度表
	 */
	private int checkAndGetTableSpace(String tid) {
		int result = -1;
		for (int i = 0; i < QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_ND_2014.length; i++) {
			if (QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_ND_2014[i].equals(tid)) {
				result = 0;
				break;
			}
		}
		
		return result;
	}



}
