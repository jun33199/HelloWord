package com.lscdz.qysds.application.qysdsnb2014.vo.response;

import java.util.ArrayList;
import java.util.List;

import com.lscdz.qysds.application.qysdsnb2014.vo.inner.QysdsZgswjg;

public class QysdsNbInitVo {
	//企业所得税税务组织机构
	public List<QysdsZgswjg> qysdsZgswjg=new ArrayList<QysdsZgswjg>();

	public List<QysdsZgswjg> getQysdsZgswjg() {
		return qysdsZgswjg;
	}

	public void setQysdsZgswjg(List<QysdsZgswjg> qysdsZgswjg) {
		this.qysdsZgswjg = qysdsZgswjg;
	}
	//申请类型
	public List<QysdsNbSqlx> qysdsnbSqlxList=new ArrayList<QysdsNbSqlx>();
		
	public List<QysdsNbSqlx> getQysdsnbSqlxList() {
		return qysdsnbSqlxList;
	}
	public void setQysdsnbSqlxList(List<QysdsNbSqlx> qysdsnbSqlxList) {
		this.qysdsnbSqlxList = qysdsnbSqlxList;
	}
}
