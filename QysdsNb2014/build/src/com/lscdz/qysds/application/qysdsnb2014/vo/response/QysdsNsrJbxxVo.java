package com.lscdz.qysds.application.qysdsnb2014.vo.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.lscdz.util.PageInfo;

public class QysdsNsrJbxxVo extends PageInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	//纳税人基本信息2014
	public List<QysdsNsrjbxxVo2014> jbxxList2014=new ArrayList<QysdsNsrjbxxVo2014>();
	
	public List<QysdsNsrjbxxVo2014> getJbxxList2014() {
		return jbxxList2014;
	}

	public void setJbxxList2014(List<QysdsNsrjbxxVo2014> jbxxList2014) {
		this.jbxxList2014 = jbxxList2014;
	}
	
}
