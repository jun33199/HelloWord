package com.lscdz.qysds.application.qysdsnb2014.vo.response;

import java.util.ArrayList;
import java.util.List;

import com.lscdz.qysds.application.qysdsnb2014.vo.inner.QysdsJmsbajl;
import com.lscdz.qysds.application.qysdsnb2014.vo.inner.QysdsZgswjg;
import com.lscdz.qysds.common.codetable.vo.gy_dm_gjdq;
import com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_gxjslymx;

public class QysdsNbInitVo {
	//企业所得税税务组织机构
	public List<QysdsZgswjg> qysdsZgswjg=new ArrayList<QysdsZgswjg>();
	//申请类型
	public List<QysdsNbSqlx> qysdsnbSqlxList=new ArrayList<QysdsNbSqlx>();
	//国家地区
	public List<gy_dm_gjdq> gjdmdzList=new ArrayList<gy_dm_gjdq>();
	//高新技术明
	public List<sb_dm_qysds_gxjslymx>  gxjslymxList=new ArrayList<sb_dm_qysds_gxjslymx>();
	//备案事项
	public List<QysdsJmsbajl> JmsbajlList=new ArrayList<QysdsJmsbajl>();

	public List<QysdsZgswjg> getQysdsZgswjg() {
		return qysdsZgswjg;
	}

	public void setQysdsZgswjg(List<QysdsZgswjg> qysdsZgswjg) {
		this.qysdsZgswjg = qysdsZgswjg;
	}
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

	public List<QysdsNbSqlx> getQysdsnbSqlxList() {
		return qysdsnbSqlxList;
	}
	public void setQysdsnbSqlxList(List<QysdsNbSqlx> qysdsnbSqlxList) {
		this.qysdsnbSqlxList = qysdsnbSqlxList;
	}
}
