package com.lscdz.qysds.common.service.qysds.check.formula;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.qysds.bo.config.Formula;
import com.lscdz.qysds.common.service.qysds.check.CheckConstants;
import com.lscdz.qysds.common.service.qysds.db.DBAccess;
import com.lscdz.qysds.common.service.qysds.util.StringUtils;

/**
 * 自定义报表
 * 项目名称：QysdsNb2014   
 * 类名称：ResourceAdaptor   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-12-1 下午1:37:15   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 下午1:37:15   
 * 修改备注：   
 * @version  1.0
 */
public class ResourceAdaptor {

	/**
	 * 获取一个应用的全部公式
	 * 
	 * @param fsdm
	 *            String,上门方式(CheckConstants.CREPORTS_SYSTEM_FS_SHANGMENG),网上方式(
	 *            CheckConstants.CREPORTS_SYSTEM_FS_WANGSHANG)
	 * @param appid String 应用编号
	 * @param version  版本号
	 * @param dba DBAccess 数据库管理器
	 * @return List 公式集合
	 * @throws FrameException 系统异常
	 */
	@SuppressWarnings("rawtypes")
	public static List getAllFormulas(String fsdm, String appid,String version, DBAccess dba) throws FrameException {
		List rtn = null;
		if (CheckConstants.CREPORTS_SYSTEM_FS_SHANGMENG.equals(fsdm)) {
			rtn = getAllFormulasByShangmen(appid, version, dba);
		} else if (CheckConstants.CREPORTS_SYSTEM_FS_WANGSHANG.equals(fsdm)) {
			rtn = getAllFormulasByWangshang(appid, version, dba);
		} else {
			throw new FrameException("错误的系统操作方式,无法当前应用获取公式!");
		}
		return rtn;
	}

	/**
	 * 获取一个应用的全部公式-网上方式; Notes: 建议废除；没有Remark1，Remark2数据信息（Guoxh,2007.01.05）
	 * 
	 * @param appid  String 应用编号
	 * @param version 版本号
	 * @param dba 数据操作器
	 * @return List 公式集合
	 * @throws FrameException 系统异常
	 */
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public static List getAllFormulasByWangshang(String appid, String version, DBAccess dba) throws FrameException {
		List rtn = new ArrayList();
		Properties prop = new Properties();
		InputStream in = null;
		try {
			if (CheckConstants.CREPORTS_APPID_QYSDS.equals(appid)) {
				in = ResourceAdaptor.class.getClassLoader().getSystemResourceAsStream(CheckConstants.PROPERTIES_FILE_NAME_FORMULA_QYSDS);
			} else {
				return null;
			}
			if (in != null) {
				prop.load(in);
			}
			in.close();
			Iterator iterator = prop.entrySet().iterator();
			int count = 0;
			Formula formula;
			while (iterator.hasNext()) {
				formula = new Formula();
				formula.setVersion(version);
				formula.setAppid(appid);
				formula.setFormulacode(String.valueOf(count++));
				formula.setFormulacontent((String) iterator.next());
				formula.setFormulatype("1");
				formula.setFormulalevel("0");
				rtn.add(formula);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtn;
	}

	/**
	 * 获取一个应用的全部公式-网上方式（调试用）;
	 * 
	 * @param appid String 应用编号
	 * @param version 版本号
	 * @param dba 数据操作器
	 * @return List 公式集合
	 * @throws FrameException 系统异常
	 */
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked", "unused" })
	public static List getAllDebugFormulasByWangshang(String appid,
			String version, DBAccess dba) throws FrameException {
		List rtn = new ArrayList();
		Properties prop = new Properties();
		InputStream in = null;
		try {
			if (CheckConstants.CREPORTS_APPID_QYSDS.equals(appid)) {
				in = ResourceAdaptor.class.getClassLoader().getSystemResourceAsStream(CheckConstants.PROPERTIES_FILE_NAME_FORMULA_QYSDS);
			} else {
				return null;
			}
			if (in != null) {
				prop.load(in);
			}
			in.close();
			Iterator iterator = prop.entrySet().iterator();
			Hashtable tmp = null;
			Iterator keys;
			System.out.println("------------------------------------------");
			while (iterator.hasNext()) {
				// Object obj=iterator.next();
				// System.out.println(obj);
				// System.out.println(obj.toString());
				// System.out.println(obj.getClass());
				// tmp=(Hashtable)obj;
				// keys=tmp.keySet().iterator();
				// while(keys.hasNext())
				// System.out.println(keys.next());
				rtn.add(iterator.next().toString());
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtn;
	}

	/**
	 * 获取一个应用的全部公式-上门方式;
	 * 
	 * @param appid String 应用编号
	 * @param version 版本号
	 * @param dba 数据操作器
	 * @return List 公式集合
	 * @throws FrameException  系统异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getAllFormulasByShangmen(String appid, String version,
			DBAccess dba) throws FrameException {
		List rtn = new ArrayList();
		try {
			String sql = "SELECT VERSION,ACTIVITY,APPCODE,FORMULACODE,FORMULACONTENT,FORMULATYPE,FORMULALEVEL,REMARK1,REMARK2 FROM DMDB.DM_JL_CRP_FORMULACODE WHERE APPCODE="
					+ StringUtils.getSQLStr(appid)
					+ " AND VERSION="
					+ StringUtils.getSQLStr(version)
					+ " AND ACTIVITY="
					+ StringUtils
							.getSQLStr(CheckConstants.CREPORTS_OBJ_ACTIVITY_INUSE);
			CachedRowSet crs = dba.executeQuery(sql);
			Formula formula;
			// VERSION,ACTIVITY,APPCODE,FORMULACODE,FORMULACONTENT,FORMULATYPE,FORMULALEVEL
			while (crs.next()) {
				formula = new Formula();
				formula.setVersion(crs.getString("VERSION"));
				formula.setAppid(crs.getString("APPCODE"));
				formula.setFormulacode(crs.getString("FORMULACODE"));
				formula.setFormulacontent(crs.getString("FORMULACONTENT"));
				formula.setRemark1(crs.getString("REMARK1"));
				formula.setRemark2(crs.getString("REMARK2"));
				formula.setFormulatype(crs.getString("FORMULATYPE"));
				formula.setFormulalevel(crs.getString("FORMULALEVEL"));
				rtn.add(formula);
			}
			crs.close();
		} catch (Exception e) {
			// e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return rtn;
	}

//	public static void main(String[] args) {
//		try {
//			ResourceAdaptor resourceadaptor = new ResourceAdaptor();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
