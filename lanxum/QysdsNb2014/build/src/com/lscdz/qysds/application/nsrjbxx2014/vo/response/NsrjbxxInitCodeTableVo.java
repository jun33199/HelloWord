package com.lscdz.qysds.application.nsrjbxx2014.vo.response;

import java.util.ArrayList;
import java.util.List;


import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaSqlx;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNbSqlx;
import com.lscdz.qysds.common.codetable.vo.gy_dm_gjbzhy;
import com.lscdz.qysds.common.codetable.vo.gy_dm_gjdq;
import com.lscdz.qysds.common.codetable.vo.gy_dm_zjlx;
import com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_qtkjzz;

public class NsrjbxxInitCodeTableVo {
	//证件类型代码表
	public List<gy_dm_zjlx> zjlxList = new ArrayList<gy_dm_zjlx>();
	//国籍
	public List<gy_dm_gjdq> gjdmdzList=new ArrayList<gy_dm_gjdq>();
	//国家标准行业
	public List<gy_dm_gjbzhy> gjbzhyList=new ArrayList<gy_dm_gjbzhy>();
	//其他会计制度
	public List<sb_dm_qysds_qtkjzz> qtkjzzList=new ArrayList<sb_dm_qysds_qtkjzz>();
	//税款年度
	public String sknd;
	//申请类型
	public List<QysdsNbSqlx> qysdsnbSqlxList=new ArrayList<QysdsNbSqlx>();
	
	public List<QysdsNbSqlx> getQysdsnbSqlxList() {
		return qysdsnbSqlxList;
	}
	public void setQysdsnbSqlxList(List<QysdsNbSqlx> qysdsnbSqlxList) {
		this.qysdsnbSqlxList = qysdsnbSqlxList;
	}
	public String getSknd() {
		return sknd;
	}
	public void setSknd(String sknd) {
		this.sknd = sknd;
	}
	public List<sb_dm_qysds_qtkjzz> getQtkjzzList() {
		return qtkjzzList;
	}
	public void setQtkjzzList(List<sb_dm_qysds_qtkjzz> qtkjzzList) {
		this.qtkjzzList = qtkjzzList;
	}
	public List<gy_dm_gjbzhy> getGjbzhyList() {
		return gjbzhyList;
	}
	public void setGjbzhyList(List<gy_dm_gjbzhy> gjbzhyList) {
		this.gjbzhyList = gjbzhyList;
	}
	public List<gy_dm_zjlx> getZjlxList() {
		return zjlxList;
	}
	public void setZjlxList(List<gy_dm_zjlx> zjlxList) {
		this.zjlxList = zjlxList;
	}
	public List<gy_dm_gjdq> getGjdmdzList() {
		return gjdmdzList;
	}
	public void setGjdmdzList(List<gy_dm_gjdq> gjdmdzList) {
		this.gjdmdzList = gjdmdzList;
	}
	
}
