package com.lscdz.qysds.application.jmsba2014.main.vo.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaBasxVo;
import com.lscdz.util.PageInfo;

public class JmsbaNbQueryMainRes extends PageInfo implements Serializable{
	/**
	 * ���к�
	 */
	private static final long serialVersionUID = 2341234577633894321L;

	//����˰���������б�
	List<JmsbaBasxVo> jmsbaBasxList=new ArrayList<JmsbaBasxVo>();

	public List<JmsbaBasxVo> getJmsbaBasxList() {
		return jmsbaBasxList;
	}

	public void setJmsbaBasxList(List<JmsbaBasxVo> jmsbaBasxList) {
		this.jmsbaBasxList = jmsbaBasxList;
	}
	
	
	
}
