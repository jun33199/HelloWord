package com.lscdz.qysds.application.qysdsnb2014.vo.response;

import java.util.ArrayList;
import java.util.List;

import com.lscdz.qysds.application.qysdsnb2014.vo.inner.QysdsZgswjg;

public class QysdsNbInitVo {
	//��ҵ����˰˰����֯����
	public List<QysdsZgswjg> qysdsZgswjg=new ArrayList<QysdsZgswjg>();

	public List<QysdsZgswjg> getQysdsZgswjg() {
		return qysdsZgswjg;
	}

	public void setQysdsZgswjg(List<QysdsZgswjg> qysdsZgswjg) {
		this.qysdsZgswjg = qysdsZgswjg;
	}
	//��������
	public List<QysdsNbSqlx> qysdsnbSqlxList=new ArrayList<QysdsNbSqlx>();
		
	public List<QysdsNbSqlx> getQysdsnbSqlxList() {
		return qysdsnbSqlxList;
	}
	public void setQysdsnbSqlxList(List<QysdsNbSqlx> qysdsnbSqlxList) {
		this.qysdsnbSqlxList = qysdsnbSqlxList;
	}
}
