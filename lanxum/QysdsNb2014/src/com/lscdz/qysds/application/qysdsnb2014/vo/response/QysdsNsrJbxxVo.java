package com.lscdz.qysds.application.qysdsnb2014.vo.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lscdz.qysds.application.qysdsnb2014.vo.inner.QysdsJmsbajl;
import com.lscdz.util.PageInfo;

public class QysdsNsrJbxxVo extends PageInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	//纳税人基本信息2014
	public List<QysdsNsrjbxxVo2014> jbxxList2014=new ArrayList<QysdsNsrjbxxVo2014>();
	private String sfglsbFlag;//是否关联申报的标志
	/*//备案事项
	public List<QysdsJmsbajl> jmsbajlList=new ArrayList<QysdsJmsbajl>();
		
	public List<QysdsJmsbajl> getJmsbajlList() {
		return jmsbajlList;
	}

	public void setJmsbajlList(List<QysdsJmsbajl> jmsbajlList) {
		this.jmsbajlList = jmsbajlList;
	}*/

	public String getSfglsbFlag() {
		return sfglsbFlag;
	}

	public void setSfglsbFlag(String sfglsbFlag) {
		this.sfglsbFlag = sfglsbFlag;
	}

	public List<QysdsNsrjbxxVo2014> getJbxxList2014() {
		return jbxxList2014;
	}

	public void setJbxxList2014(List<QysdsNsrjbxxVo2014> jbxxList2014) {
		this.jbxxList2014 = jbxxList2014;
	}
	
}
