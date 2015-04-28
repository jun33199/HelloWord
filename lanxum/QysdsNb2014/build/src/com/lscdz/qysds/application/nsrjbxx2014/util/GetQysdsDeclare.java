package com.lscdz.qysds.application.nsrjbxx2014.util;

import com.lscdz.qysds.common.service.qysds.Constants;
import com.lscdz.qysds.common.service.qysds.bo.qysds.Jbxx;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014;

public class GetQysdsDeclare {
	public static QysdsReportsDeclare getDeclareVo(QysdsReportsDeclare qysdsReportsVo,
			sb_jl_qysds_nsrjbxxb_2014 nsrjbxx_Vo2014){
		Jbxx jbxx=new Jbxx();
		qysdsReportsVo.setAppid(Constants.CREPORTS_APPID_QYSDS);
		qysdsReportsVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		qysdsReportsVo.setNsrjsjdm(nsrjbxx_Vo2014.getJsjdm());
		qysdsReportsVo.setQh("1");
		qysdsReportsVo.setSknd(nsrjbxx_Vo2014.getNd());
		jbxx.setJsjdm(nsrjbxx_Vo2014.getJsjdm());
		jbxx.setBbmsf(nsrjbxx_Vo2014.getBbmsf());
		jbxx.setNsrmc(nsrjbxx_Vo2014.getNsrmc());
		qysdsReportsVo.setJbxx(jbxx);
		return qysdsReportsVo;
		
	}
}
