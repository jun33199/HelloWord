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
 * �Զ��屨��
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�ResourceAdaptor   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-12-1 ����1:37:15   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-12-1 ����1:37:15   
 * �޸ı�ע��   
 * @version  1.0
 */
public class ResourceAdaptor {

	/**
	 * ��ȡһ��Ӧ�õ�ȫ����ʽ
	 * 
	 * @param fsdm
	 *            String,���ŷ�ʽ(CheckConstants.CREPORTS_SYSTEM_FS_SHANGMENG),���Ϸ�ʽ(
	 *            CheckConstants.CREPORTS_SYSTEM_FS_WANGSHANG)
	 * @param appid String Ӧ�ñ��
	 * @param version  �汾��
	 * @param dba DBAccess ���ݿ������
	 * @return List ��ʽ����
	 * @throws FrameException ϵͳ�쳣
	 */
	@SuppressWarnings("rawtypes")
	public static List getAllFormulas(String fsdm, String appid,String version, DBAccess dba) throws FrameException {
		List rtn = null;
		if (CheckConstants.CREPORTS_SYSTEM_FS_SHANGMENG.equals(fsdm)) {
			rtn = getAllFormulasByShangmen(appid, version, dba);
		} else if (CheckConstants.CREPORTS_SYSTEM_FS_WANGSHANG.equals(fsdm)) {
			rtn = getAllFormulasByWangshang(appid, version, dba);
		} else {
			throw new FrameException("�����ϵͳ������ʽ,�޷���ǰӦ�û�ȡ��ʽ!");
		}
		return rtn;
	}

	/**
	 * ��ȡһ��Ӧ�õ�ȫ����ʽ-���Ϸ�ʽ; Notes: ����ϳ���û��Remark1��Remark2������Ϣ��Guoxh,2007.01.05��
	 * 
	 * @param appid  String Ӧ�ñ��
	 * @param version �汾��
	 * @param dba ���ݲ�����
	 * @return List ��ʽ����
	 * @throws FrameException ϵͳ�쳣
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
	 * ��ȡһ��Ӧ�õ�ȫ����ʽ-���Ϸ�ʽ�������ã�;
	 * 
	 * @param appid String Ӧ�ñ��
	 * @param version �汾��
	 * @param dba ���ݲ�����
	 * @return List ��ʽ����
	 * @throws FrameException ϵͳ�쳣
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
	 * ��ȡһ��Ӧ�õ�ȫ����ʽ-���ŷ�ʽ;
	 * 
	 * @param appid String Ӧ�ñ��
	 * @param version �汾��
	 * @param dba ���ݲ�����
	 * @return List ��ʽ����
	 * @throws FrameException  ϵͳ�쳣
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
