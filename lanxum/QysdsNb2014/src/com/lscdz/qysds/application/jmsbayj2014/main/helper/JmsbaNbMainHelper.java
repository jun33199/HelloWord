package com.lscdz.qysds.application.jmsbayj2014.main.helper;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.request.JmsbaNbQueryMainReq;

public class JmsbaNbMainHelper {
	private String deploy_environment=ResourceManager.getTokenByName("DEPLOY_ENVIRONMENT");
	
	/**
	 * 拼接减免税备案sql
	 * @param vo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public StringBuffer doQuery(JmsbaNbQueryMainReq vo, Yh ud)
			throws FrameException {
		// String ssdwdm=ud.getSsdwdm();
		// String yhjb=ud.getJbdm();
		String ssdwdm = ud.getSsdwdm();
		String yhjb = ud.getJbdm();
		StringBuffer sqlWhere = new StringBuffer();
		
		if("INNER".equals(deploy_environment)){
			sqlWhere.append(
					"select a.jsjdm,b.nsrmc,d.swjgzzjgmc,d.swjgzzjgdm,a.band,A.BASQBH,a.basqwsh,(select c.jmbasxmc from dmdb.sf_dm_jmbasxdm_2014 c where c.jmbasxdm=a.jmbasxdm) jmbasxmc,a.jmbasxdm,a.sqlxdm,a.sqzt")
					.append(" from SFDB.SF_JL_QYSDSJMSBAJL_2014 a ,djdb.dj_jl_jbsj b,dmdb.gy_dm_swjgzzjg d ")
					.append(" where a.jsjdm=b.jsjdm and a.qh<>'2' and a.sqzt<>'1' and a.swjgzzjgdm=d.swjgzzjgdm ");
			String jsjdm = vo.getFilter_jsjdm();
			String nsrmc = vo.getFilter_nsrmc();
			String band = vo.getFilter_band();
			String sqlx = vo.getFilter_sqlx();
			String sqzt = vo.getFilter_sqzt();
			String jmsbasx = vo.getFilter_jmsbasx();
			String zgswjg = vo.getFilter_zgswjg();
	
			if (jsjdm != null && jsjdm.trim().length() > 0) {
				sqlWhere.append(" and a.jsjdm='" + jsjdm + "' ");
			}
			if (nsrmc != null && nsrmc.trim().length() > 0) {
				sqlWhere.append(" and b.nsrmc like '%" + nsrmc + "%' ");
			}
			if (band != null && band.trim().length() > 0) {
				sqlWhere.append(" and a.band='" + band + "' ");
			}
			if (sqlx != null && sqlx.trim().length() > 0) {
				sqlWhere.append(" and a.sqlxdm='" + sqlx + "' ");
			}
			if (sqzt != null && sqzt.trim().length() > 0) {
				sqlWhere.append(" and a.sqzt  in (" + sqzt + ") ");
			}
			if (jmsbasx != null && jmsbasx.trim().length() > 0) {
				sqlWhere.append(" and a.jmbasxdm='" + jmsbasx + "' ");
			}
			if (yhjb.equals("50")) {
				if (zgswjg != null && zgswjg.trim().length() > 0)
					sqlWhere.append(" and a.swjgzzjgdm like '"+ zgswjg.substring(0, 2) + "%' ");
			}
			if (yhjb.equals("40")) {
	
				if (zgswjg != null && zgswjg.trim().length() > 0)
					sqlWhere.append(" and a.swjgzzjgdm = '" + zgswjg + "' ");
				else
					sqlWhere.append(" and a.swjgzzjgdm like '" + ssdwdm.substring(0, 2) + "%' ");
			}
			if (yhjb.equals("30")) {
				sqlWhere.append(" and a.swjgzzjgdm = '" + ssdwdm + "' ");
			}
	
			sqlWhere.append("order by jmbasxdm");
			
		}else{
			
			sqlWhere.append(
					"select a.jsjdm,b.nsrmc,d.swjgzzjgmc,d.swjgzzjgdm,a.band,A.BASQBH,a.basqwsh,(select c.jmbasxmc from dmdb.sf_dm_jmbasxdm_2014 c where c.jmbasxdm=a.jmbasxdm) jmbasxmc,a.jmbasxdm,a.sqlxdm,a.sqzt")
					.append(" from SFDB.SF_JL_QYSDSJMSBAJL_2014 a ,djdb.dj_jl_jbsj b,aqdb.zk_jl_wsyh c ,dmdb.gy_dm_swjgzzjg d ")
					.append(" where a.jsjdm=b.jsjdm and a.jsjdm=c.yhid and a.qh<>'2' and a.swjgzzjgdm=d.swjgzzjgdm ");
			String band = vo.getFilter_band();
			String sqlx = vo.getFilter_sqlx();
			String sqzt = vo.getFilter_sqzt();
			String jmsbasx = vo.getFilter_jmsbasx();
	
			sqlWhere.append(" and a.jsjdm='" + ud.getYhid() + "' ");
			
			if (band != null && band.trim().length() > 0) {
				sqlWhere.append(" and a.band='" + band + "' ");
			}
			if (sqlx != null && sqlx.trim().length() > 0) {
				sqlWhere.append(" and a.sqlxdm='" + sqlx + "' ");
			}
			if (sqzt != null && sqzt.trim().length() > 0) {
				sqlWhere.append(" and a.sqzt  in (" + sqzt + ") ");
			}
			if (jmsbasx != null && jmsbasx.trim().length() > 0) {
				sqlWhere.append(" and a.jmbasxdm='" + jmsbasx + "' ");
			}	
			sqlWhere.append("order by jmbasxdm");
			
			
		}

		return sqlWhere;
	}

}
