package com.lscdz.qysds.application.qysdsnb2014.vo.response;

import java.util.ArrayList;
import java.util.List;
import com.lscdz.qysds.common.codetable.vo.gy_dm_gjdq;
import com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_gxjslymx;

public class QysdsWsInitVo {
	//国家地区
	public List<gy_dm_gjdq> gjdmdzList=new ArrayList<gy_dm_gjdq>();
	//高新技术明
	public List<sb_dm_qysds_gxjslymx>  gxjslymxList=new ArrayList<sb_dm_qysds_gxjslymx>();
		
	public List<sb_dm_qysds_gxjslymx> getGxjslymxList() {
		return gxjslymxList;
	}
	public void setGxjslymxList(List<sb_dm_qysds_gxjslymx> gxjslymxList) {
		this.gxjslymxList = gxjslymxList;
	}	

	public List<gy_dm_gjdq> getGjdmdzList() {
		return gjdmdzList;
	}

	public void setGjdmdzList(List<gy_dm_gjdq> gjdmdzList) {
		this.gjdmdzList = gjdmdzList;
	}
}
