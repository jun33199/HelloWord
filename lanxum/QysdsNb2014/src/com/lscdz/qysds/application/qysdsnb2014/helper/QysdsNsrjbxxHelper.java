package com.lscdz.qysds.application.qysdsnb2014.helper;

import yangjian.frame.util.FrameException;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.qysdsnb2014.vo.request.NsrjbxxVo2014;

public class QysdsNsrjbxxHelper {
	/**
	 * 拼接纳税人基本信息2014sql
	 * @param vo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public static StringBuffer doQuery(NsrjbxxVo2014  vo,Yh ud)
			throws FrameException {
		//房山1301（暂时的？）
		String ssdwdm = ud.getSsdwdm();
		String yhjb = ud.getJbdm();
		StringBuffer sqlWhere = new StringBuffer();
		sqlWhere.append(" SELECT * FROM SBDB.SB_JL_QYSDS_NSRJBXXB_2014 ");
		sqlWhere.append(" WHERE 1=1 ");
		String jsjdm =vo.getJsjdm();
		String nsrmc = vo.getNsrmc();
		String sknd = vo.getNd();
		String zgswjgdm=vo.getZgswjgdm();
		String sqlx=vo.getSqlx();
		if (jsjdm != null && jsjdm.trim().length() > 0) {
			sqlWhere.append(" and jsjdm='" + jsjdm + "' ");
		}
		if (nsrmc != null && nsrmc.trim().length() > 0) {
			sqlWhere.append(" and nsrmc like '%" + nsrmc + "%' ");
		}
		if (sknd != null && sknd.trim().length() > 0) {
			sqlWhere.append(" and nd='" + sknd + "' ");
		}
		if(sqlx !=null && sqlx.trim().length() > 0){
			sqlWhere.append(" and sqlx='" +sqlx+"'");
		}
		if (yhjb.equals("50")) {
			if (zgswjgdm != null && zgswjgdm.trim().length() > 0)
				sqlWhere.append(" and swjgzzjgdm like '"+ zgswjgdm.substring(0, 2) + "%' ");
		}
		if (yhjb.equals("40")) {

			if (zgswjgdm != null && zgswjgdm.trim().length() > 0)
				sqlWhere.append(" and swjgzzjgdm = '" + zgswjgdm + "' ");
			else
				sqlWhere.append(" and swjgzzjgdm like '" + ssdwdm.substring(0, 2) + "%' ");
		}
		if (yhjb.equals("30")) {
			sqlWhere.append(" and swjgzzjgdm = '" + ssdwdm + "' ");
		}
		sqlWhere.append("order by nd");
		return sqlWhere;
	}
}
